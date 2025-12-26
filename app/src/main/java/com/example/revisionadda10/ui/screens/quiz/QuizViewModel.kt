package com.example.revisionadda10.ui.screens.quiz

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.ToneGenerator
import android.os.Build
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.revisionadda10.R
import com.example.revisionadda10.data.model.MCQ
import com.example.revisionadda10.data.model.MCQSet
import com.example.revisionadda10.data.model.MCQProgress
import com.example.revisionadda10.data.repository.MCQSetGenerator
import com.example.revisionadda10.data.repository.MockData
import com.example.revisionadda10.data.repository.ProgressRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
    chapterId: String,
    private val context: Context? = null
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(QuizState())
    val uiState: StateFlow<QuizState> = _uiState.asStateFlow()
    
    private var timerJob: Job? = null
    private val timePerQuestion = 90L // 90 seconds (1:30 minutes) per question
    private var mediaPlayer: MediaPlayer? = null
    private var toneGenerator: ToneGenerator? = null
    private var hasPlayedWarningSound = false
    private var hasPlayedMidWarning = false // For 30 seconds warning
    private var audioManager: AudioManager? = null
    private var audioFocusRequest: AudioFocusRequest? = null
    
    private val progressRepository: ProgressRepository? = context?.let { ProgressRepository(it) }
    private val currentSubjectId = subjectId
    private val currentChapterId = chapterId
    private var quizStartTime: Long = 0L
    private var currentSetNumber: Int = 1
    
    init {
        loadQuestions(subjectId, chapterId)
        quizStartTime = System.currentTimeMillis()
        context?.let {
            audioManager = it.getSystemService(Context.AUDIO_SERVICE) as? AudioManager
            // Initialize ToneGenerator for beep sounds
            try {
                toneGenerator = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
            } catch (e: Exception) {
                android.util.Log.e("QuizViewModel", "Error initializing ToneGenerator", e)
            }
        }
    }
    
    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
        releaseMediaPlayer()
        releaseToneGenerator()
        releaseAudioFocus()
    }
    
    private fun requestAudioFocus(): Boolean {
        return try {
            if (audioManager == null) {
                android.util.Log.w("QuizViewModel", "AudioManager is null")
                return true // Continue anyway
            }
            
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val audioAttributes = AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build()
                
                audioFocusRequest = AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)
                    .setAudioAttributes(audioAttributes)
                    .setAcceptsDelayedFocusGain(true)
                    .build()
                
                val result = audioManager?.requestAudioFocus(audioFocusRequest!!)
                android.util.Log.d("QuizViewModel", "Audio focus requested: $result")
                result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED
            } else {
                @Suppress("DEPRECATION")
                val result = audioManager?.requestAudioFocus(
                    null,
                    AudioManager.STREAM_MUSIC,
                    AudioManager.AUDIOFOCUS_GAIN_TRANSIENT
                )
                android.util.Log.d("QuizViewModel", "Audio focus requested (legacy): $result")
                result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED
            }
        } catch (e: Exception) {
            android.util.Log.e("QuizViewModel", "Error requesting audio focus", e)
            true // Continue anyway
        }
    }
    
    private fun releaseAudioFocus() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                audioFocusRequest?.let {
                    audioManager?.abandonAudioFocusRequest(it)
                }
            } else {
                @Suppress("DEPRECATION")
                audioManager?.abandonAudioFocus(null)
            }
            audioFocusRequest = null
        } catch (e: Exception) {
            android.util.Log.e("QuizViewModel", "Error releasing audio focus", e)
        }
    }
    
    private fun playTimerAudio() {
        android.util.Log.d("QuizViewModel", "=== playTimerAudio() called ===")
        
        // Use ToneGenerator for AI-generated beep sound
        playBeepSound()
        
        // Also try MediaPlayer as fallback if file exists
        if (context != null) {
            try {
                val resId = context!!.resources.getIdentifier("timeraudio", "raw", context!!.packageName)
                if (resId != 0) {
                    playMediaPlayerAudio()
                }
            } catch (e: Exception) {
                android.util.Log.d("QuizViewModel", "Audio file not found, using beep only")
            }
        }
    }
    
    private fun playBeepSound() {
        try {
            if (toneGenerator == null) {
                try {
                    toneGenerator = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
                } catch (e: Exception) {
                    android.util.Log.e("QuizViewModel", "Error creating ToneGenerator", e)
                    return
                }
            }
            
            android.util.Log.d("QuizViewModel", "Playing beep sound...")
            
            // Play a beep tone (TONE_CDMA_ALERT_CALL_GUARD = 97)
            // This creates a pleasant beep sound
            toneGenerator?.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 500)
            
            android.util.Log.d("QuizViewModel", "✓ Beep sound played")
        } catch (e: Exception) {
            android.util.Log.e("QuizViewModel", "Error playing beep sound", e)
        }
    }
    
    private fun playMediaPlayerAudio() {
        if (context == null) return
        
        viewModelScope.launch(Dispatchers.Main) {
            try {
                releaseMediaPlayer()
                
                val player = MediaPlayer.create(context, R.raw.timeraudio)
                if (player != null) {
                    mediaPlayer = player
                    mediaPlayer?.setOnCompletionListener {
                        releaseMediaPlayer()
                    }
                    mediaPlayer?.start()
                }
            } catch (e: Exception) {
                android.util.Log.e("QuizViewModel", "Error in playMediaPlayerAudio", e)
                releaseMediaPlayer()
            }
        }
    }
    
    private fun releaseToneGenerator() {
        try {
            toneGenerator?.release()
            toneGenerator = null
        } catch (e: Exception) {
            android.util.Log.e("QuizViewModel", "Error releasing ToneGenerator", e)
        }
    }
    
    private fun releaseMediaPlayer() {
        try {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
        } catch (e: Exception) {
            android.util.Log.e("QuizViewModel", "Error releasing MediaPlayer", e)
            e.printStackTrace()
        }
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
        hasPlayedWarningSound = false
        hasPlayedMidWarning = false
        currentSetNumber = set.setNumber
        quizStartTime = System.currentTimeMillis()
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
        releaseMediaPlayer()
        releaseToneGenerator()
        hasPlayedWarningSound = false
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
        releaseMediaPlayer()
        releaseToneGenerator()
        hasPlayedWarningSound = false
        hasPlayedMidWarning = false
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
            // Quiz completed - save progress
            saveProgress(currentState)
            _uiState.value = currentState.copy(
                showResult = true,
                isTimerRunning = false
            )
        }
    }
    
    /**
     * Save progress after quiz completion
     */
    private fun saveProgress(state: QuizState) {
        if (progressRepository == null || state.selectedSet == null) return
        
        val timeTaken = (System.currentTimeMillis() - quizStartTime) / 1000 // in seconds
        val totalQuestions = state.questions.size
        val correctAnswers = state.score
        val wrongAnswers = totalQuestions - correctAnswers
        val percentage = if (totalQuestions > 0) {
            (correctAnswers.toFloat() / totalQuestions) * 100f
        } else 0f
        
        val subject = when (currentSubjectId) {
            "maths" -> MockData.getMathsSubject()
            "science" -> MockData.getScienceSubject()
            "social_science" -> MockData.getSocialScienceSubject()
            else -> MockData.getMathsSubject()
        }
        val chapter = subject.chapters.find { it.id == currentChapterId }
        val chapterTitle = chapter?.title ?: "Unknown Chapter"
        
        val progress = MCQProgress(
            subjectId = currentSubjectId,
            chapterId = currentChapterId,
            chapterTitle = chapterTitle,
            totalQuestions = totalQuestions,
            correctAnswers = correctAnswers,
            wrongAnswers = wrongAnswers,
            score = correctAnswers,
            percentage = percentage,
            dateCompleted = System.currentTimeMillis(),
            timeTaken = timeTaken,
            setNumber = currentSetNumber
        )
        
        progressRepository.saveProgress(progress)
    }
    
    fun previousQuestion() {
        timerJob?.cancel()
        releaseMediaPlayer()
        releaseToneGenerator()
        hasPlayedWarningSound = false
        hasPlayedMidWarning = false
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
        releaseMediaPlayer()
        releaseToneGenerator()
        hasPlayedWarningSound = false
        hasPlayedMidWarning = false
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
            android.util.Log.d("QuizViewModel", "Timer started with remaining: $remaining seconds (1:30 minutes)")
            
            while (remaining > 0 && _uiState.value.isTimerRunning && !_uiState.value.showResult) {
                delay(1000)
                remaining--
                
                // Log every 10 seconds for debugging
                if (remaining % 10 == 0L) {
                    android.util.Log.d("QuizViewModel", "Timer: $remaining seconds remaining")
                }
                
                // For 1:30 minute (90 seconds) timer:
                // Play warning at 30 seconds (1 minute left)
                if (remaining == 30L && !hasPlayedMidWarning) {
                    android.util.Log.d("QuizViewModel", "⏰ 30 seconds remaining (1 minute left) - Playing mid warning...")
                    playTimerAudio()
                    hasPlayedMidWarning = true
                }
                
                // Play warning sound at 10 seconds remaining (only once per question)
                if (remaining == 10L && !hasPlayedWarningSound) {
                    android.util.Log.d("QuizViewModel", "⏰ 10 seconds remaining - Playing final warning audio...")
                    playTimerAudio()
                    hasPlayedWarningSound = true
                }
                
                _uiState.value = _uiState.value.copy(
                    timeRemaining = remaining,
                    isTimerRunning = remaining > 0
                )
            }
            
            // Timer expired - play audio
            if (remaining == 0L && !_uiState.value.showResult) {
                android.util.Log.d("QuizViewModel", "⏰ Timer EXPIRED (0 seconds) - Playing final audio...")
                playTimerAudio()
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
                            hasPlayedWarningSound = false
                            hasPlayedMidWarning = false
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

