package com.example.revisionadda10.ui.screens.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.revisionadda10.data.repository.MockData
import com.example.revisionadda10.ui.navigation.Screen
import com.example.revisionadda10.ui.ads.rememberAdManager
import com.example.revisionadda10.ui.ads.rememberRewardedAdManager
import com.example.revisionadda10.ui.ads.AdBannerCard
import androidx.compose.ui.platform.LocalContext
import android.app.Activity
import kotlinx.coroutines.delay

fun formatTime(seconds: Long): String {
    val minutes = seconds / 60
    val secs = seconds % 60
    return if (minutes > 0) {
        String.format("%d:%02d", minutes, secs)
    } else {
        String.format("%ds", secs)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    navController: NavController,
    subjectId: String,
    chapterId: String
) {
    val context = LocalContext.current
    val applicationContext = context.applicationContext
    val viewModel: QuizViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                return QuizViewModel(subjectId, chapterId, applicationContext) as T
            }
        }
    )
    
    val uiState by viewModel.uiState.collectAsState()
    val adManager = rememberAdManager()
    
    // Load interstitial ad when screen opens
    LaunchedEffect(Unit) {
        android.util.Log.d("QuizScreen", "Loading interstitial ad...")
        adManager.loadInterstitialAd()
    }
    
    // Continuously show ads at regular intervals (30 seconds)
    LaunchedEffect(Unit) {
        if (context is Activity) {
            // Initial delay before first ad
            delay(8000) // Wait 8 seconds for ad to load
            
            // Continuous loop to show ads every 30 seconds
            while (true) {
                // Wait for ad to be loaded before showing
                var attempts = 0
                while (attempts < 15 && !adManager.isInterstitialAdLoaded()) {
                    delay(1000)
                    attempts++
                    android.util.Log.d("QuizScreen", "Waiting for ad to load... attempt $attempts")
                }
                
                // Show the ad if loaded, otherwise load it
                if (adManager.isInterstitialAdLoaded()) {
                    android.util.Log.d("QuizScreen", "Showing interstitial ad")
                    adManager.showInterstitialAd(context as Activity)
                } else {
                    android.util.Log.d("QuizScreen", "Ad not loaded, loading new ad")
                    adManager.loadInterstitialAd()
                }
                
                // Wait 30 seconds before showing next ad
                delay(30000)
            }
        }
    }
    
    // Show interstitial ad when quiz is completed
    LaunchedEffect(uiState.showResult) {
        if (uiState.showResult && context is Activity) {
            adManager.showInterstitialAd(context as Activity)
        }
    }
    
    val subject = when (subjectId) {
        "maths" -> MockData.getMathsSubject()
        "science" -> MockData.getScienceSubject()
        "social_science" -> MockData.getSocialScienceSubject()
        else -> MockData.getMathsSubject()
    }
    val chapter = subject.chapters.find { it.id == chapterId }
    val primaryColor = when (subjectId) {
        "maths" -> Color(0xFF2196F3)
        "science" -> Color(0xFF4CAF50)
        "social_science" -> Color(0xFFFF9800)
        else -> Color(0xFF2196F3)
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Column {
                        Text(
                            text = "MCQ Practice",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        if (chapter != null) {
                            Text(
                                text = chapter.title,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                            )
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        if (uiState.showSetSelection) {
            MCQSetSelectionScreen(
                mcqSets = uiState.mcqSets,
                onSetSelected = { viewModel.selectSet(it) },
                primaryColor = primaryColor,
                modifier = Modifier.padding(paddingValues)
            )
        } else if (uiState.questions.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("No questions available for this chapter")
            }
        } else if (uiState.showResult) {
            QuizResultScreen(
                score = uiState.score,
                totalQuestions = uiState.questions.size,
                onRetry = { viewModel.resetQuiz() },
                onBack = { viewModel.goBackToSetSelection() },
                onViewProgress = {
                    navController.navigate(Screen.Progress.route)
                },
                context = context
            )
        } else {
            QuizQuestionScreen(
                uiState = uiState,
                onAnswerSelected = { viewModel.selectAnswer(it) },
                onSubmit = { viewModel.submitAnswer() },
                onNext = { viewModel.nextQuestion() },
                onPrevious = { viewModel.previousQuestion() },
                primaryColor = primaryColor,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}

@Composable
fun QuizQuestionScreen(
    uiState: QuizState,
    onAnswerSelected: (Int) -> Unit,
    onSubmit: () -> Unit,
    onNext: () -> Unit,
    onPrevious: () -> Unit,
    primaryColor: Color,
    modifier: Modifier = Modifier
) {
    val currentQuestion = uiState.questions[uiState.currentQuestionIndex]
    val isAnswered = uiState.currentQuestionIndex in uiState.answeredQuestions
    var showExplanation by remember { mutableStateOf(false) }
    
    // Update showExplanation when answer is submitted
    LaunchedEffect(isAnswered) {
        if (isAnswered) {
            showExplanation = true
        }
    }
    
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .padding(bottom = 80.dp), // Space for sticky buttons
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
        // Progress Bar
        LinearProgressIndicator(
            progress = { (uiState.currentQuestionIndex + 1).toFloat() / uiState.questions.size },
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.primary
        )
        
        // Timer and Question Info Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Question ${uiState.currentQuestionIndex + 1} of ${uiState.questions.size}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            // Timer Display
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = when {
                        uiState.timeRemaining <= 10 -> Color(0xFFF44336).copy(alpha = 0.2f)
                        uiState.timeRemaining <= 20 -> Color(0xFFFF9800).copy(alpha = 0.2f)
                        else -> MaterialTheme.colorScheme.secondaryContainer
                    }
                ),
                modifier = Modifier.shadow(4.dp, RoundedCornerShape(12.dp))
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "⏱️",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = formatTime(uiState.timeRemaining),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = when {
                            uiState.timeRemaining <= 10 -> Color(0xFFF44336)
                            uiState.timeRemaining <= 20 -> Color(0xFFFF9800)
                            else -> MaterialTheme.colorScheme.onSecondaryContainer
                        }
                    )
                }
            }
        }
        
        // Score Display
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Text(
                text = "Score: ${uiState.score}/${uiState.questions.size}",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        
        // Timer Expired Warning
        if (uiState.timerExpired) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF44336).copy(alpha = 0.2f)
                )
            ) {
                Text(
                    text = "⏰ Time's up! Answer submitted automatically.",
                    modifier = Modifier.padding(12.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFFF44336)
                )
            }
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // Question
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Text(
                text = currentQuestion.question,
                modifier = Modifier.padding(20.dp),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }
        
        // Options
        currentQuestion.options.forEachIndexed { index, option ->
            val isSelected = uiState.selectedAnswerIndex == index
            val isCorrect = index == currentQuestion.correctAnswerIndex
            val isWrong = isSelected && !isCorrect && isAnswered
            
            Card(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (!isAnswered) {
                        onAnswerSelected(index)
                    }
                },
                colors = CardDefaults.cardColors(
                    containerColor = when {
                        isAnswered && isCorrect -> Color(0xFF4CAF50).copy(alpha = 0.3f)
                        isWrong -> Color(0xFFF44336).copy(alpha = 0.3f)
                        isSelected -> MaterialTheme.colorScheme.primaryContainer
                        else -> MaterialTheme.colorScheme.surface
                    }
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = if (isSelected) 4.dp else 2.dp
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${'A' + index}. $option",
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    
                    if (isAnswered) {
                        Icon(
                            imageVector = if (isCorrect) Icons.Default.CheckCircle else Icons.Default.Close,
                            contentDescription = null,
                            tint = if (isCorrect) Color(0xFF4CAF50) else Color(0xFFF44336)
                        )
                    }
                }
            }
        }
        
        // Explanation
        if (isAnswered && currentQuestion.explanation != null) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Explanation:",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = currentQuestion.explanation,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }
        }
        
        // Banner Ad
        AdBannerCard(
            modifier = Modifier.padding(vertical = 8.dp)
        )
        
        // Add bottom padding for sticky buttons
        Spacer(modifier = Modifier.height(80.dp))
    }
    
    // Sticky Navigation Buttons at Bottom
    Surface(
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .fillMaxWidth()
            .shadow(8.dp, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (uiState.currentQuestionIndex > 0) {
                OutlinedButton(
                    onClick = onPrevious,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Previous")
                }
            }
            
            // Show Submit button only if not answered and answer is selected
            if (!isAnswered && uiState.selectedAnswerIndex != null) {
                Button(
                    onClick = {
                        onSubmit()
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = primaryColor
                    )
                ) {
                    Text("Submit Answer")
                }
            }
            
            // Show Next button if question is answered (always show after submit)
            if (isAnswered) {
                Button(
                    onClick = onNext,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4CAF50)
                    )
                ) {
                    Text(
                        if (uiState.currentQuestionIndex < uiState.questions.size - 1) "Next Question →" else "Finish Quiz ✓"
                    )
                }
            }
        }
    }
    }
}

@Composable
fun QuizResultScreen(
    score: Int,
    totalQuestions: Int,
    onRetry: () -> Unit,
    onBack: () -> Unit,
    onViewProgress: () -> Unit = {},
    context: android.content.Context
) {
    val rewardedAdManager = rememberRewardedAdManager()
    var hasShownRewardedAd by remember { mutableStateOf(false) }
    
    // Load rewarded ad when result screen opens
    LaunchedEffect(Unit) {
        android.util.Log.d("QuizResult", "Loading rewarded ad...")
        rewardedAdManager.loadRewardedAd()
    }
    
    // Show rewarded ad automatically when quiz result shows
    LaunchedEffect(Unit) {
        if (context is Activity && !hasShownRewardedAd) {
            delay(3000) // Wait 3 seconds for ad to load
            
            // Wait for ad to be loaded
            var attempts = 0
            while (attempts < 10 && !rewardedAdManager.isRewardedAdLoaded()) {
                delay(500)
                attempts++
                android.util.Log.d("QuizResult", "Waiting for rewarded ad to load... attempt $attempts")
            }
            
            if (rewardedAdManager.isRewardedAdLoaded()) {
                android.util.Log.d("QuizResult", "Showing rewarded ad")
                rewardedAdManager.showRewardedAd(
                    activity = context as Activity,
                    onRewardEarned = { reward ->
                        android.util.Log.d("QuizResult", "User earned reward: ${reward.amount} ${reward.type}")
                        hasShownRewardedAd = true
                    },
                    onAdDismissed = {
                        hasShownRewardedAd = true
                    },
                    onAdFailed = {
                        hasShownRewardedAd = true
                    }
                )
            } else {
                android.util.Log.d("QuizResult", "Rewarded ad not loaded, will retry")
                hasShownRewardedAd = true
            }
        }
    }
    val percentage = (score * 100) / totalQuestions
    val performance = when {
        percentage >= 80 -> "Excellent! 🎉"
        percentage >= 60 -> "Good Job! 👍"
        percentage >= 40 -> "Keep Practicing! 💪"
        else -> "Don't Give Up! 📚"
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Quiz Completed!",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(
                modifier = Modifier.padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "$score / $totalQuestions",
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                
                Text(
                    text = "$percentage%",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = performance,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = onBack,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Back")
                }
                
                Button(
                    onClick = onRetry,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Retry Quiz")
                }
            }
            
            TextButton(
                onClick = onViewProgress,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("📊 View Progress")
            }
        }
    }
}

@Composable
fun MCQSetSelectionScreen(
    mcqSets: List<com.example.revisionadda10.data.model.MCQSet>,
    onSetSelected: (com.example.revisionadda10.data.model.MCQSet) -> Unit,
    primaryColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        primaryColor.copy(alpha = 0.1f),
                        MaterialTheme.colorScheme.surface
                    )
                )
            )
    ) {
        if (mcqSets.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No MCQ sets available",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = primaryColor.copy(alpha = 0.15f)
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "📝 MCQ Practice Sets",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold,
                                color = primaryColor
                            )
                            Text(
                                text = "Select a set to start practicing",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = "Total ${mcqSets.size} Sets • ${mcqSets.sumOf { it.questions.size }} Questions",
                                style = MaterialTheme.typography.bodySmall,
                                color = primaryColor,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
                
                items(mcqSets) { set ->
                    MCQSetCard(
                        mcqSet = set,
                        onClick = { onSetSelected(set) },
                        primaryColor = primaryColor
                    )
                }
            }
        }
    }
}

@Composable
fun MCQSetCard(
    mcqSet: com.example.revisionadda10.data.model.MCQSet,
    onClick: () -> Unit,
    primaryColor: Color
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(16.dp),
                spotColor = primaryColor.copy(alpha = 0.3f)
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.weight(1f)
            ) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = primaryColor.copy(alpha = 0.2f),
                    modifier = Modifier.size(56.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = mcqSet.title,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = primaryColor
                        )
                    }
                }
                Column {
                    Text(
                        text = mcqSet.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "${mcqSet.questions.size} Questions",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = primaryColor,
                modifier = Modifier.padding(start = 12.dp)
            ) {
                Text(
                    text = "Start →",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}

