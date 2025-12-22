package com.example.revisionadda10.ui.screens.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.revisionadda10.data.model.MCQ
import com.example.revisionadda10.data.model.MCQSet
import com.example.revisionadda10.data.repository.MCQSetGenerator
import com.example.revisionadda10.data.repository.MockData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class QuizState(
    val mcqSets: List<MCQSet> = emptyList(),
    val selectedSet: MCQSet? = null,
    val questions: List<MCQ> = emptyList(),
    val currentQuestionIndex: Int = 0,
    val selectedAnswerIndex: Int? = null,
    val score: Int = 0,
    val showResult: Boolean = false,
    val showSetSelection: Boolean = true,
    val answeredQuestions: Set<Int> = emptySet()
)

class QuizViewModel(
    subjectId: String,
    chapterId: String
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(QuizState())
    val uiState: StateFlow<QuizState> = _uiState.asStateFlow()
    
    init {
        loadQuestions(subjectId, chapterId)
    }
    
    private fun loadQuestions(subjectId: String, chapterId: String) {
        val subject = when (subjectId) {
            "maths" -> MockData.getMathsSubject()
            "science" -> MockData.getScienceSubject()
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
        _uiState.value = _uiState.value.copy(
            selectedSet = set,
            questions = set.questions,
            showSetSelection = false,
            currentQuestionIndex = 0,
            selectedAnswerIndex = null,
            score = 0,
            showResult = false,
            answeredQuestions = emptySet()
        )
    }
    
    fun goBackToSetSelection() {
        _uiState.value = _uiState.value.copy(
            showSetSelection = true,
            showResult = false,
            selectedSet = null,
            questions = emptyList(),
            currentQuestionIndex = 0,
            selectedAnswerIndex = null,
            score = 0,
            answeredQuestions = emptySet()
        )
    }
    
    fun selectAnswer(answerIndex: Int) {
        _uiState.value = _uiState.value.copy(
            selectedAnswerIndex = answerIndex
        )
    }
    
    fun submitAnswer() {
        val currentState = _uiState.value
        val currentQuestion = currentState.questions.getOrNull(currentState.currentQuestionIndex)
        
        if (currentQuestion != null && currentState.selectedAnswerIndex != null) {
            val isCorrect = currentState.selectedAnswerIndex == currentQuestion.correctAnswerIndex
            val newScore = if (isCorrect) currentState.score + 1 else currentState.score
            
            val newAnsweredQuestions = currentState.answeredQuestions + currentState.currentQuestionIndex
            
            _uiState.value = currentState.copy(
                score = newScore,
                answeredQuestions = newAnsweredQuestions
            )
        }
    }
    
    fun nextQuestion() {
        val currentState = _uiState.value
        if (currentState.currentQuestionIndex < currentState.questions.size - 1) {
            _uiState.value = currentState.copy(
                currentQuestionIndex = currentState.currentQuestionIndex + 1,
                selectedAnswerIndex = null
            )
        } else {
            _uiState.value = currentState.copy(
                showResult = true
            )
        }
    }
    
    fun previousQuestion() {
        val currentState = _uiState.value
        if (currentState.currentQuestionIndex > 0) {
            val previousIndex = currentState.currentQuestionIndex - 1
            val previousQuestion = currentState.questions.getOrNull(previousIndex)
            val previousAnswer = if (previousQuestion != null && previousIndex in currentState.answeredQuestions) {
                // Find the correct answer index for previous question
                previousQuestion.correctAnswerIndex
            } else null
            
            _uiState.value = currentState.copy(
                currentQuestionIndex = previousIndex,
                selectedAnswerIndex = previousAnswer
            )
        }
    }
    
    fun resetQuiz() {
        _uiState.value = QuizState(
            questions = _uiState.value.questions,
            currentQuestionIndex = 0,
            selectedAnswerIndex = null,
            score = 0,
            showResult = false,
            answeredQuestions = emptySet()
        )
    }
}

