package com.example.revisionadda10.ui.screens.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.revisionadda10.data.model.MCQ
import com.example.revisionadda10.data.model.MCQSet
import com.example.revisionadda10.data.repository.MCQSetGenerator
import com.example.revisionadda10.data.repository.MockData
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class QuizState(
    val mcqSets: List<MCQSet> = emptyList(),
    val selectedSet: MCQSet? = null,
    val questions: List<MCQ> = emptyList(),
    val currentQuestionIndex: Int = 0,
    val selectedAnswerIndex: Int? = null,
    val score: Int = 0,
    val showResult: Boolean = false,
    val showSetSelection: Boolean = true,
    val answeredQuestions: Set<Int> = emptySet(),
    val timeRemaining: Long = 60, // Time in seconds per question
    val isTimerRunning: Boolean = false,
    val timerExpired: Boolean = false
)

class QuizViewModel(
    subjectId: String,
    chapterId: String
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(QuizState())
    val uiState: StateFlow<QuizState> = _uiState.asStateFlow()
    
    private var timerJob: Job? = null
    private val timePerQuestion = 90L // 90 seconds (1:30 minutes) per question
    
    init {
        loadQuestions(subjectId, chapterId)
    }
    
    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
    
    private fun loadQuestions(subjectId: String, chapterId: String) {
        val subject = when (subjectId) {
            "maths" -> MockData.getMathsSubject()
            "science" -> MockData.getScienceSubject()
            "social_science" -> MockData.getSocialScienceSubject()
            "social" -> MockData.getSocialScienceSubject() // Fallback for compatibility
            else -> MockData.getMathsSubject()
        }
        
        val chapter = subject.chapters.find { it.id == chapterId }
        val allMCQs = chapter?.mcqs ?: emptyList()
        
        // Generate sets of 10 questions each (6-8 sets)
        val mcqSets = MCQSetGenerator.generateMCQSets(chapterId, allMCQs)
        
        _uiState.value = _uiState.value.copy(
            mcqSets = mcqSets,
            selectedSet = null,
            questions = emptyList(),
            currentQuestionIndex = 0,
            selectedAnswerIndex = null,
            score = 0,
            showResult = false,
            showSetSelection = true,
            answeredQuestions = emptySet()
        )
    }
    
    fun selectSet(set: MCQSet) {
        timerJob?.cancel()
        _uiState.value = _uiState.value.copy(
            selectedSet = set,
            questions = set.questions,
            showSetSelection = false,
            currentQuestionIndex = 0,
            selectedAnswerIndex = null,
            score = 0,
            showResult = false,
            answeredQuestions = emptySet(),
            timeRemaining = timePerQuestion,
            isTimerRunning = true,
            timerExpired = false
        )
        startTimer()
    }
    
    fun goBackToSetSelection() {
        timerJob?.cancel()
        _uiState.value = _uiState.value.copy(
            showSetSelection = true,
            showResult = false,
            selectedSet = null,
            questions = emptyList(),
            currentQuestionIndex = 0,
            selectedAnswerIndex = null,
            score = 0,
            answeredQuestions = emptySet(),
            timeRemaining = timePerQuestion,
            isTimerRunning = false,
            timerExpired = false
        )
    }
    
    fun selectAnswer(answerIndex: Int) {
        _uiState.value = _uiState.value.copy(
            selectedAnswerIndex = answerIndex
        )
    }
    
    fun submitAnswer() {
        timerJob?.cancel()
        val currentState = _uiState.value
        val currentQuestion = currentState.questions.getOrNull(currentState.currentQuestionIndex)
        
        if (currentQuestion != null && currentState.selectedAnswerIndex != null) {
            val isCorrect = currentState.selectedAnswerIndex == currentQuestion.correctAnswerIndex
            val newScore = if (isCorrect) currentState.score + 1 else currentState.score
            
            val newAnsweredQuestions = currentState.answeredQuestions + currentState.currentQuestionIndex
            
            _uiState.value = currentState.copy(
                score = newScore,
                answeredQuestions = newAnsweredQuestions,
                isTimerRunning = false
            )
        }
    }
    
    fun nextQuestion() {
        timerJob?.cancel()
        val currentState = _uiState.value
        if (currentState.currentQuestionIndex < currentState.questions.size - 1) {
            _uiState.value = currentState.copy(
                currentQuestionIndex = currentState.currentQuestionIndex + 1,
                selectedAnswerIndex = null,
                timeRemaining = timePerQuestion,
                isTimerRunning = true,
                timerExpired = false
            )
            startTimer()
        } else {
            _uiState.value = currentState.copy(
                showResult = true,
                isTimerRunning = false
            )
        }
    }
    
    fun previousQuestion() {
        timerJob?.cancel()
        val currentState = _uiState.value
        if (currentState.currentQuestionIndex > 0) {
            val previousIndex = currentState.currentQuestionIndex - 1
            val previousQuestion = currentState.questions.getOrNull(previousIndex)
            val previousAnswer = if (previousQuestion != null && previousIndex in currentState.answeredQuestions) {
                // Find the correct answer index for previous question
                previousQuestion.correctAnswerIndex
            } else null
            
            val wasAnswered = previousIndex in currentState.answeredQuestions
            
            _uiState.value = currentState.copy(
                currentQuestionIndex = previousIndex,
                selectedAnswerIndex = previousAnswer,
                timeRemaining = if (wasAnswered) 0 else timePerQuestion,
                isTimerRunning = !wasAnswered,
                timerExpired = false
            )
            
            // Restart timer only if question wasn't answered
            if (!wasAnswered) {
                startTimer()
            }
        }
    }
    
    fun resetQuiz() {
        timerJob?.cancel()
        _uiState.value = QuizState(
            questions = _uiState.value.questions,
            currentQuestionIndex = 0,
            selectedAnswerIndex = null,
            score = 0,
            showResult = false,
            answeredQuestions = emptySet(),
            timeRemaining = timePerQuestion,
            isTimerRunning = true,
            timerExpired = false
        )
        startTimer()
    }
    
    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            var remaining = _uiState.value.timeRemaining
            while (remaining > 0 && _uiState.value.isTimerRunning && !_uiState.value.showResult) {
                delay(1000)
                remaining--
                _uiState.value = _uiState.value.copy(
                    timeRemaining = remaining,
                    isTimerRunning = remaining > 0
                )
            }
            
            // Timer expired
            if (remaining == 0L && !_uiState.value.showResult) {
                val currentState = _uiState.value
                val currentQuestion = currentState.questions.getOrNull(currentState.currentQuestionIndex)
                
                // Auto-submit if answer is selected, otherwise auto-move to next
                if (currentQuestion != null) {
                    if (currentState.selectedAnswerIndex != null && 
                        currentState.currentQuestionIndex !in currentState.answeredQuestions) {
                        // Auto-submit the selected answer
                        val isCorrect = currentState.selectedAnswerIndex == currentQuestion.correctAnswerIndex
                        val newScore = if (isCorrect) currentState.score + 1 else currentState.score
                        val newAnsweredQuestions = currentState.answeredQuestions + currentState.currentQuestionIndex
                        
                        _uiState.value = currentState.copy(
                            score = newScore,
                            answeredQuestions = newAnsweredQuestions,
                            timerExpired = true,
                            isTimerRunning = false
                        )
                    } else {
                        // Auto-move to next question if no answer selected
                        if (currentState.currentQuestionIndex < currentState.questions.size - 1) {
                            _uiState.value = currentState.copy(
                                currentQuestionIndex = currentState.currentQuestionIndex + 1,
                                selectedAnswerIndex = null,
                                timeRemaining = timePerQuestion,
                                isTimerRunning = true,
                                timerExpired = false
                            )
                            startTimer()
                        } else {
                            _uiState.value = currentState.copy(
                                showResult = true,
                                timerExpired = true,
                                isTimerRunning = false
                            )
                        }
                    }
                }
            }
        }
    }
    
    fun pauseTimer() {
        timerJob?.cancel()
        _uiState.value = _uiState.value.copy(isTimerRunning = false)
    }
    
    fun resumeTimer() {
        if (!_uiState.value.showResult && _uiState.value.timeRemaining > 0) {
            _uiState.value = _uiState.value.copy(isTimerRunning = true)
            startTimer()
        }
    }
}

