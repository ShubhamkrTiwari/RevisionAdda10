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
import com.example.revisionadda10.data.repository.CBSE2026PredictedQuestions
import com.example.revisionadda10.data.repository.MockData
import com.example.revisionadda10.data.repository.PYQQuestions
import com.example.revisionadda10.ui.ads.AdBannerCard

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
    
    // Get exactly 50 unique questions for full subject (CBSE 2026 pattern)
    // Priority: Use CBSE 2026 Predicted Questions (50 questions) - exact board pattern
    val aiQuestions = remember(subjectId) {
        // Get CBSE 2026 Predicted Questions (50 questions total, distributed by subject)
        val predicted2026Questions = CBSE2026PredictedQuestions.getPredictedQuestionsForSubject(subjectId)
        
        // If we have 50 questions from 2026 predictions, use them
        if (predicted2026Questions.size >= 50) {
            predicted2026Questions.take(50).distinctBy { it.question }
        } else {
            // If less than 50, supplement with PYQ 2025 and other years
            val pyq2025Questions = PYQQuestions.getPYQQuestionsForYear(subjectId, "2025")
            val pyq2024Questions = PYQQuestions.getPYQQuestionsForYear(subjectId, "2024")
            
            // Combine predicted 2026 + PYQ 2025 + PYQ 2024, remove duplicates
            val combinedQuestions = (predicted2026Questions + pyq2025Questions + pyq2024Questions)
                .distinctBy { it.question.trim().lowercase() }
                .take(50)
            
            // If still less than 50, add questions from other years
            if (combinedQuestions.size < 50) {
                val additionalQuestions = (2023 downTo 2019).flatMap { year ->
                    PYQQuestions.getPYQQuestionsForYear(subjectId, year.toString())
                }.distinctBy { it.question.trim().lowercase() }
                
                (combinedQuestions + additionalQuestions)
                    .distinctBy { it.question.trim().lowercase() }
                    .take(50)
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
            // AI-Generated Questions Section - Full Screen Scroll
            if (aiQuestions.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Info Card - Enhanced Design
                    item {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .shadow(
                                    elevation = 8.dp,
                                    shape = RoundedCornerShape(20.dp),
                                    spotColor = primaryColor.copy(alpha = 0.4f)
                                ),
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surface
                            )
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        brush = Brush.linearGradient(
                                            colors = listOf(
                                                primaryColor.copy(alpha = 0.2f),
                                                primaryColor.copy(alpha = 0.08f)
                                            )
                                        )
                                    )
                            ) {
                                Column(
                                    modifier = Modifier.padding(20.dp),
                                    verticalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                                    ) {
                                        Surface(
                                            shape = RoundedCornerShape(12.dp),
                                            color = primaryColor.copy(alpha = 0.2f),
                                            modifier = Modifier.size(56.dp),
                                            shadowElevation = 4.dp
                                        ) {
                                            Box(
                                                contentAlignment = Alignment.Center,
                                                modifier = Modifier.fillMaxSize()
                                            ) {
                                                Text(
                                                    text = "🎯",
                                                    style = MaterialTheme.typography.headlineMedium
                                                )
                                            }
                                        }
                                        Column(
                                            verticalArrangement = Arrangement.spacedBy(4.dp)
                                        ) {
                                            Text(
                                                text = "90% Match Paper",
                                                style = MaterialTheme.typography.headlineSmall,
                                                fontWeight = FontWeight.Bold,
                                                color = primaryColor
                                            )
                                            Text(
                                                text = "CBSE Board Exam 2026",
                                                style = MaterialTheme.typography.bodyMedium,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                                fontWeight = FontWeight.Medium
                                            )
                                        }
                                    }
                                    Surface(
                                        shape = RoundedCornerShape(12.dp),
                                        color = primaryColor.copy(alpha = 0.1f),
                                        modifier = Modifier.padding(top = 4.dp)
                                    ) {
                                        Text(
                                            text = "50 AI-Generated questions that could appear in CBSE Board 2026. Based on 90% weightage topics, latest syllabus, and previous year patterns. These questions match the exact board exam format.",
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = MaterialTheme.colorScheme.onSurface,
                                            lineHeight = TextUnit(22f, TextUnitType.Sp),
                                            modifier = Modifier.padding(16.dp)
                                        )
                                    }
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                                        modifier = Modifier.padding(top = 4.dp)
                                    ) {
                                        Surface(
                                            shape = RoundedCornerShape(10.dp),
                                            color = primaryColor.copy(alpha = 0.15f)
                                        ) {
                                            Text(
                                                text = "✅ 50 Questions",
                                                style = MaterialTheme.typography.labelMedium,
                                                fontWeight = FontWeight.Medium,
                                                color = primaryColor,
                                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                                            )
                                        }
                                        Surface(
                                            shape = RoundedCornerShape(10.dp),
                                            color = primaryColor.copy(alpha = 0.15f)
                                        ) {
                                            Text(
                                                text = "📊 80 Marks",
                                                style = MaterialTheme.typography.labelMedium,
                                                fontWeight = FontWeight.Medium,
                                                color = primaryColor,
                                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                                            )
                                        }
                                        Surface(
                                            shape = RoundedCornerShape(10.dp),
                                            color = primaryColor.copy(alpha = 0.15f)
                                        ) {
                                            Text(
                                                text = "⏱️ 3 Hours",
                                                style = MaterialTheme.typography.labelMedium,
                                                fontWeight = FontWeight.Medium,
                                                color = primaryColor,
                                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                    
                    items(aiQuestions.size) { index ->
                        QuestionCard(
                            question = aiQuestions[index],
                            index = index + 1,
                            showAnswer = showAnswers,
                            primaryColor = primaryColor
                        )
                    }
                    
                    // Banner Ad
                    item {
                        AdBannerCard(
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }
            } else {
                // Fallback to WebView if no questions available
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
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
                elevation = 6.dp,
                shape = RoundedCornerShape(20.dp),
                spotColor = primaryColor.copy(alpha = 0.3f)
            ),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Enhanced Question Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = primaryColor,
                        modifier = Modifier.size(40.dp),
                        shadowElevation = 4.dp
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = "$index",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Text(
                            text = "Q$index",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = getQuestionTypeLabel(question.type),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = primaryColor,
                    shadowElevation = 4.dp
                ) {
                    Text(
                        text = "${question.marks}M",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                    )
                }
            }
            
            Divider(
                color = primaryColor.copy(alpha = 0.2f),
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            
            // Enhanced Question Text
            Text(
                text = question.question,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = TextUnit(24f, TextUnitType.Sp)
            )
            
            // Enhanced Hints
            if (question.hints.isNotEmpty()) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    color = primaryColor.copy(alpha = 0.1f),
                    shadowElevation = 3.dp
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = primaryColor.copy(alpha = 0.2f),
                                modifier = Modifier.size(32.dp)
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Text(
                                        text = "💡",
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                }
                            }
                            Text(
                                text = "Hints:",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = primaryColor
                            )
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            question.hints.forEach { hint ->
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    Surface(
                                        shape = RoundedCornerShape(4.dp),
                                        color = primaryColor,
                                        modifier = Modifier.size(6.dp)
                                    ) {}
                                    Text(
                                        text = hint,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        modifier = Modifier.weight(1f),
                                        lineHeight = TextUnit(22f, TextUnitType.Sp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
            
            // Enhanced Answer (Show/Hide)
            if (showAnswer && question.answer != null) {
                AnimatedVisibility(visible = showAnswer) {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        color = primaryColor.copy(alpha = 0.12f),
                        shadowElevation = 4.dp
                    ) {
                        Column(
                            modifier = Modifier.padding(18.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Surface(
                                    shape = RoundedCornerShape(12.dp),
                                    color = primaryColor,
                                    modifier = Modifier.size(36.dp),
                                    shadowElevation = 4.dp
                                ) {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        Text(
                                            text = "✓",
                                            style = MaterialTheme.typography.titleLarge,
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                                Text(
                                    text = "Answer:",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = primaryColor
                                )
                            }
                            Divider(
                                color = primaryColor.copy(alpha = 0.3f),
                                thickness = 1.5.dp
                            )
                            Text(
                                text = question.answer,
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface,
                                lineHeight = TextUnit(24f, TextUnitType.Sp),
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            } else if (!showAnswer && question.answer != null) {
                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = primaryColor.copy(alpha = 0.08f),
                    modifier = Modifier.fillMaxWidth(),
                    shadowElevation = 2.dp
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "👁️",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(end = 10.dp)
                        )
                        Text(
                            text = "Tap 'Show Answers' in top bar to view solution",
                            style = MaterialTheme.typography.bodyMedium,
                            color = primaryColor,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center
                        )
                    }
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
