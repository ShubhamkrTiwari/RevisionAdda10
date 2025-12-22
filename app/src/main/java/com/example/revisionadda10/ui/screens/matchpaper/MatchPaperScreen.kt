package com.example.revisionadda10.ui.screens.matchpaper

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.revisionadda10.data.model.ExamQuestion
import com.example.revisionadda10.data.model.QuestionType
import com.example.revisionadda10.data.repository.AIGeneratedQuestions
import com.example.revisionadda10.data.repository.MockData
import com.example.revisionadda10.data.repository.PYQQuestions

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun MatchPaperScreen(
    navController: NavController,
    subjectId: String
) {
    val subject = when (subjectId) {
        "maths" -> MockData.getMathsSubject()
        "science" -> MockData.getScienceSubject()
        "social_science" -> MockData.getSocialScienceSubject()
        else -> MockData.getMathsSubject()
    }
    
    val primaryColor = when (subjectId) {
        "maths" -> Color(0xFF2196F3)
        "science" -> Color(0xFF4CAF50)
        "social_science" -> Color(0xFFFF9800)
        else -> Color(0xFF2196F3)
    }
    
    // CBSE Board Exam Paper URLs - Using more accessible resources
    // These URLs are more likely to work without 403 errors
    val paperUrls = mapOf(
        "maths" to mapOf(
            "maths_1" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "maths_2" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "maths_3" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "maths_4" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "maths_5" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "maths_6" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "maths_7" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "maths_8" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "maths_9" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "maths_10" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "maths_11" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "maths_12" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "maths_13" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "maths_14" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "maths_15" to "https://www.learncbse.in/cbse-sample-papers-class-10/"
        ),
        "science" to mapOf(
            "science_1" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "science_2" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "science_3" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "science_4" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "science_5" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "science_6" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "science_7" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "science_8" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "science_9" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "science_10" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "science_11" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "science_12" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "science_13" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "science_14" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "science_15" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "science_16" to "https://www.learncbse.in/cbse-sample-papers-class-10/"
        ),
        "social_science" to mapOf(
            "social_1" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "social_2" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "social_3" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "social_4" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "social_5" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "social_6" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "social_7" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "social_8" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "social_9" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "social_10" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "social_11" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "social_12" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "social_13" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "social_14" to "https://www.learncbse.in/cbse-sample-papers-class-10/",
            "social_15" to "https://www.learncbse.in/cbse-sample-papers-class-10/"
        )
    )
    
    // Alternative: Popular CBSE resources that are more accessible
    val cbseResources = listOf(
        "https://www.learncbse.in/cbse-sample-papers-class-10/",
        "https://www.cbsesamplepapers.info/",
        "https://www.selfstudys.com/cbse/class-10/sample-papers",
        "https://www.vedantu.com/cbse/previous-year-question-paper-class-10",
        "https://www.toppr.com/guides/cbse/class-10/"
    )
    
    // Use LearnCBSE as primary source (more accessible, less likely to block)
    val paperUrl = "https://www.learncbse.in/cbse-sample-papers-class-10/"
    
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var showAnswers by remember { mutableStateOf(false) }
    val context = LocalContext.current
    
    // Get exactly 30 unique questions for full subject (CBSE 2026 pattern)
    // Priority: Use PYQ 2025 questions (30 questions), then supplement with AI-generated if needed
    val aiQuestions = remember(subjectId) {
        // First, get PYQ questions for 2025 (exactly 30 questions)
        val pyqQuestions = PYQQuestions.getPYQQuestionsForYear(subjectId, "2025")
        
        // If we have 30 questions from PYQ, use them
        if (pyqQuestions.size >= 30) {
            pyqQuestions.take(30).distinctBy { it.question }
        } else {
            // If PYQ has less than 30, supplement with AI-generated questions
            val allChapters = subject.chapters
            val aiGeneratedQuestions = allChapters.flatMapIndexed { index, chapter ->
                val chapterQuestions = AIGeneratedQuestions.getQuestionsForChapter(subjectId, chapter.id)
                chapterQuestions.mapIndexed { qIndex, question ->
                    ExamQuestion(
                        id = "${question.id}_ch${index + 1}",
                        question = question.question,
                        marks = question.marks,
                        type = question.type,
                        answer = question.answer,
                        hints = question.hints
                    )
                }
            }
            
            // Combine PYQ and AI questions, remove duplicates by question text
            val combinedQuestions = (pyqQuestions + aiGeneratedQuestions)
                .distinctBy { it.question.trim().lowercase() }
                .take(30)
            
            // If still less than 30, pad with unique questions from other years
            if (combinedQuestions.size < 30) {
                val additionalQuestions = (2024 downTo 2019).flatMap { year ->
                    PYQQuestions.getPYQQuestionsForYear(subjectId, year.toString())
                }.distinctBy { it.question.trim().lowercase() }
                
                (combinedQuestions + additionalQuestions)
                    .distinctBy { it.question.trim().lowercase() }
                    .take(30)
            } else {
                combinedQuestions
            }
        }
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Column {
                        Text(
                            text = "🎯 90% Match Paper",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = subject.name,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                        )
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
                ),
                actions = {
                    if (aiQuestions.isNotEmpty()) {
                        TextButton(
                            onClick = { showAnswers = !showAnswers }
                        ) {
                            Text(if (showAnswers) "Hide Answers" else "Show Answers")
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                // Info Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = primaryColor.copy(alpha = 0.15f)
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "📚",
                                style = MaterialTheme.typography.headlineSmall
                            )
                            Text(
                                text = "CBSE Board Exam Papers",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = primaryColor
                            )
                        }
                        Text(
                            text = "Access CBSE Class 10 previous year question papers and sample papers that match 90% with actual board exam pattern.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "🤖 AI-Generated: Possible questions for CBSE Board Exam 2026 based on latest pattern and trends.",
                            style = MaterialTheme.typography.bodySmall,
                            color = primaryColor,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                
                // AI-Generated Questions Section
                if (aiQuestions.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        item {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = primaryColor.copy(alpha = 0.2f)
                                )
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "🤖 AI-PREDICTED QUESTIONS",
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.Bold,
                                        color = primaryColor
                                    )
                                    Text(
                                        text = "CBSE Board Exam 2026 - ${subject.name}",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                    Text(
                                        text = "Full Subject Paper (All Chapters)",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = primaryColor,
                                        fontWeight = FontWeight.Medium,
                                        modifier = Modifier.padding(top = 4.dp)
                                    )
                                    Text(
                                        text = "Total Questions: ${aiQuestions.size} | Total Marks: ${aiQuestions.sumOf { it.marks }}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = primaryColor,
                                        fontWeight = FontWeight.Medium,
                                        modifier = Modifier.padding(top = 4.dp)
                                    )
                                }
                            }
                        }
                        
                        itemsIndexed(aiQuestions) { index, question ->
                            QuestionCard(
                                question = question,
                                index = index + 1,
                                showAnswer = showAnswers,
                                primaryColor = primaryColor
                            )
                        }
                    }
                } else {
                    // Fallback to WebView if no questions available
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                    ) {
                    AndroidView(
                        factory = { context ->
                            WebView(context).apply {
                                webViewClient = object : WebViewClient() {
                                    override fun onPageFinished(view: WebView?, url: String?) {
                                        isLoading = false
                                        errorMessage = null
                                    }
                                    
                                    override fun onReceivedError(
                                        view: WebView?,
                                        errorCode: Int,
                                        description: String?,
                                        failingUrl: String?
                                    ) {
                                        isLoading = false
                                        if (errorCode == android.webkit.WebViewClient.ERROR_HOST_LOOKUP) {
                                            errorMessage = "No internet connection. Please check your network."
                                        } else if (errorCode == android.webkit.WebViewClient.ERROR_TIMEOUT) {
                                            errorMessage = "Connection timeout. Please try again."
                                        } else {
                                            errorMessage = "Unable to load. Tap 'Open in Browser' to view papers."
                                        }
                                    }
                                    
                                    override fun onReceivedHttpError(
                                        view: WebView?,
                                        request: android.webkit.WebResourceRequest?,
                                        errorResponse: android.webkit.WebResourceResponse?
                                    ) {
                                        if (errorResponse?.statusCode == 403 || errorResponse?.statusCode == 401) {
                                            isLoading = false
                                            errorMessage = "Access denied by website. Please use 'Open in Browser' button to view papers directly."
                                        } else if (errorResponse?.statusCode == 404) {
                                            isLoading = false
                                            errorMessage = "Page not found. Please try 'Open in Browser' for alternative resources."
                                        }
                                    }
                                }
                                settings.apply {
                                    javaScriptEnabled = true
                                    domStorageEnabled = true
                                    loadWithOverviewMode = true
                                    useWideViewPort = true
                                    builtInZoomControls = true
                                    displayZoomControls = false
                                    // Set user agent to avoid 403 errors
                                    userAgentString = "Mozilla/5.0 (Linux; Android 10; Mobile) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.120 Mobile Safari/537.36"
                                    // Enable other settings
                                    allowContentAccess = true
                                    allowFileAccess = true
                                    databaseEnabled = true
                                    setSupportZoom(true)
                                }
                                // Load with headers to avoid 403
                                val headers = mapOf(
                                    "User-Agent" to "Mozilla/5.0 (Linux; Android 10; Mobile) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.120 Mobile Safari/537.36",
                                    "Accept" to "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
                                    "Accept-Language" to "en-US,en;q=0.5"
                                )
                                loadUrl(paperUrl, headers)
                            }
                        },
                        modifier = Modifier.fillMaxSize()
                    )
                    
                    // Loading indicator
                    if (isLoading) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.8f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                CircularProgressIndicator(color = primaryColor)
                                Text(
                                    text = "Loading CBSE Board Papers...",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                    
                    // Error message
                    errorMessage?.let { error ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.surface),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                                modifier = Modifier.padding(24.dp)
                            ) {
                                Text(
                                    text = "⚠️",
                                    style = MaterialTheme.typography.displayMedium
                                )
                                Text(
                                    text = error,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.padding(horizontal = 16.dp)
                                )
                                Text(
                                    text = "Alternative Resources:",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = primaryColor
                                )
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    cbseResources.take(3).forEach { url ->
                                        Text(
                                            text = "• ${url}",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    Button(
                                        onClick = { 
                                            isLoading = true
                                            errorMessage = null
                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = primaryColor
                                        )
                                    ) {
                                        Text("Retry")
                                    }
                                    OutlinedButton(
                                        onClick = {
                                            val intent = android.content.Intent(android.content.Intent.ACTION_VIEW, android.net.Uri.parse(paperUrl))
                                            intent.addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK)
                                            try {
                                                context.startActivity(intent)
                                            } catch (e: Exception) {
                                                // Handle error
                                            }
                                        },
                                        colors = ButtonDefaults.outlinedButtonColors(
                                            contentColor = primaryColor
                                        )
                                    ) {
                                        Text("Open in Browser")
                                    }
                                }
                            }
                        }
                    }
                    }
                }
            }
        }
    }
}

@Composable
fun QuestionCard(
    question: com.example.revisionadda10.data.model.ExamQuestion,
    index: Int,
    showAnswer: Boolean,
    primaryColor: Color
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(16.dp),
                spotColor = primaryColor.copy(alpha = 0.2f)
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Question Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = primaryColor.copy(alpha = 0.2f),
                        modifier = Modifier.size(32.dp)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = "Q$index",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = primaryColor
                            )
                        }
                    }
                    Text(
                        text = getQuestionTypeLabel(question.type),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = primaryColor,
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text(
                        text = "${question.marks}M",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
            
            // Question Text
            Text(
                text = question.question,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(top = 4.dp)
            )
            
            // Hints
            if (question.hints.isNotEmpty()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = primaryColor.copy(alpha = 0.05f)
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "💡 Hints:",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = primaryColor
                        )
                        question.hints.forEach { hint ->
                            Text(
                                text = "• $hint",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
            
            // Answer (Show/Hide)
            if (showAnswer && question.answer != null) {
                AnimatedVisibility(visible = showAnswer) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = primaryColor.copy(alpha = 0.1f)
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = "✓",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = primaryColor
                                )
                                Text(
                                    text = "Answer:",
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = primaryColor
                                )
                            }
                            Text(
                                text = question.answer,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface,
                                lineHeight = TextUnit(20f, TextUnitType.Sp)
                            )
                        }
                    }
                }
            } else if (!showAnswer && question.answer != null) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Tap 'Show Answers' in top bar to view solution",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        }
    }
}

fun getQuestionTypeLabel(type: QuestionType): String {
    return when (type) {
        QuestionType.VERY_SHORT_ANSWER -> "Very Short Answer (1 mark)"
        QuestionType.SHORT_ANSWER -> "Short Answer (2-3 marks)"
        QuestionType.LONG_ANSWER -> "Long Answer (4-5 marks)"
        QuestionType.CASE_BASED -> "Case-Based (4-5 marks)"
        QuestionType.ASSERTION_REASON -> "Assertion-Reason (1 mark)"
    }
}

