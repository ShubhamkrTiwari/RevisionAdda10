package com.example.revisionadda10.ui.screens.pyqs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.revisionadda10.data.model.ExamQuestion
import com.example.revisionadda10.data.model.QuestionType
import com.example.revisionadda10.data.repository.MockData
import com.example.revisionadda10.data.repository.PYQQuestions
import com.example.revisionadda10.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PYQsScreen(
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
    
    // All years from 2019 to 2026 (2026 is AI-predicted questions)
    val pyqYears = listOf("2026", "2025", "2024", "2023", "2022", "2021", "2020", "2019")
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Column {
                        Text(
                            text = "Previous Year Questions",
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
                )
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
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
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
                                            primaryColor.copy(alpha = 0.15f),
                                            primaryColor.copy(alpha = 0.05f)
                                        )
                                    )
                                )
                        ) {
                            Column(
                                modifier = Modifier.padding(24.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Surface(
                                    shape = RoundedCornerShape(16.dp),
                                    color = primaryColor.copy(alpha = 0.2f),
                                    modifier = Modifier.size(80.dp),
                                    shadowElevation = 4.dp
                                ) {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        Text(
                                            text = "📚",
                                            style = MaterialTheme.typography.displayMedium
                                        )
                                    }
                                }
                                Text(
                                    text = "CBSE Board Exam Papers",
                                    style = MaterialTheme.typography.headlineMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = primaryColor,
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = "Previous Year Question Papers",
                                    style = MaterialTheme.typography.titleLarge,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Medium
                                )
                                Surface(
                                    shape = RoundedCornerShape(16.dp),
                                    color = primaryColor,
                                    modifier = Modifier.padding(top = 4.dp),
                                    shadowElevation = 4.dp
                                ) {
                                    Text(
                                        text = "2019 - 2026",
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White,
                                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
                                    )
                                }
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    modifier = Modifier.padding(top = 8.dp)
                                ) {
                                    Surface(
                                        shape = RoundedCornerShape(10.dp),
                                        color = primaryColor.copy(alpha = 0.1f)
                                    ) {
                                        Text(
                                            text = "✅ Complete Solutions",
                                            style = MaterialTheme.typography.labelMedium,
                                            fontWeight = FontWeight.Medium,
                                            color = primaryColor,
                                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                                        )
                                    }
                                    Surface(
                                        shape = RoundedCornerShape(10.dp),
                                        color = primaryColor.copy(alpha = 0.1f)
                                    ) {
                                        Text(
                                            text = "📝 All Sections",
                                            style = MaterialTheme.typography.labelMedium,
                                            fontWeight = FontWeight.Medium,
                                            color = primaryColor,
                                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                                        )
                                    }
                                }
                                Surface(
                                    shape = RoundedCornerShape(12.dp),
                                    color = primaryColor.copy(alpha = 0.08f),
                                    modifier = Modifier.padding(top = 8.dp)
                                ) {
                                    Text(
                                        text = "💡 Tap on any year card below to expand and view all questions with detailed solutions",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = primaryColor,
                                        fontWeight = FontWeight.Medium,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                                        lineHeight = TextUnit(20f, TextUnitType.Sp)
                                    )
                                }
                            }
                        }
                    }
                }
                
                items(pyqYears) { year ->
                    PYQYearCard(
                        year = year,
                        subjectId = subjectId,
                        subjectName = subject.name,
                        navController = navController,
                        primaryColor = primaryColor
                    )
                }
            }
        }
    }
}

@Composable
fun PYQYearCard(
    year: String,
    subjectId: String,
    subjectName: String,
    navController: NavController,
    primaryColor: Color
) {
    var expanded by remember { mutableStateOf(false) }
    var showAnswers by remember { mutableStateOf(false) }
    
    // Get AI-generated PYQ questions for this year
    val pyqQuestions = remember(year, subjectId) {
        PYQQuestions.getPYQQuestionsForYear(subjectId, year)
    }
    
    // Note: PDF URLs from CBSE website are not always available
    // Questions are available in the expanded view below
    // PDF button is disabled as official PDFs may not be accessible
    val pdfUrl = "" // Empty - PDFs not available online
    val pdfAvailable = false // Set to false as CBSE PDFs return 404
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(16.dp),
                spotColor = primaryColor.copy(alpha = 0.4f)
            )
            .clickable { expanded = !expanded },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column {
            // Header - Enhanced Layout
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                primaryColor.copy(alpha = 0.25f),
                                primaryColor.copy(alpha = 0.12f)
                            )
                        )
                    )
                    .padding(16.dp)
            ) {
                // Top Row: Year Badge and Title - Compact
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        // Enhanced Year Badge
                        Surface(
                            shape = RoundedCornerShape(14.dp),
                            color = primaryColor,
                            modifier = Modifier.size(56.dp),
                            shadowElevation = 4.dp
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Text(
                                    text = year,
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                        }
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(2.dp)
                        ) {
                            Text(
                                text = if (year == "2026") "CBSE Class 10 - $year (AI Predicted)" else "CBSE Class 10 - $year",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = if (year == "2026") "AI-Generated Predicted Questions (90% Weightage Topics)" else "$subjectName Question Paper",
                                style = MaterialTheme.typography.bodySmall,
                                color = if (year == "2026") primaryColor else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                    // Enhanced Expand/Collapse Button
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = if (expanded) primaryColor else primaryColor.copy(alpha = 0.2f),
                        modifier = Modifier
                            .size(44.dp),
                        shadowElevation = if (expanded) 4.dp else 2.dp
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = if (expanded) "▲" else "▼",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = if (expanded) Color.White else primaryColor
                            )
                        }
                    }
                }
                
                // Second Row: Stats and Action Buttons - Compact
                if (pyqQuestions.isNotEmpty()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Compact Stats
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = primaryColor.copy(alpha = 0.15f)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp)
                                ) {
                                    Text(
                                        text = "📊",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                    Text(
                                        text = "${pyqQuestions.size} Q",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = primaryColor
                                    )
                                }
                            }
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = primaryColor.copy(alpha = 0.15f)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp)
                                ) {
                                    Text(
                                        text = "⭐",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                    Text(
                                        text = "${pyqQuestions.sumOf { it.marks }} M",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = primaryColor
                                    )
                                }
                            }
                        }
                        
                        // Compact Action Buttons
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            // Enhanced Show/Hide Answers Button
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = if (showAnswers) primaryColor else primaryColor.copy(alpha = 0.15f),
                                modifier = Modifier.clickable { showAnswers = !showAnswers },
                                shadowElevation = if (showAnswers) 4.dp else 2.dp
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                                ) {
                                    Text(
                                        text = if (showAnswers) "✓" else "👁️",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                    Text(
                                        text = if (showAnswers) "Hide Answers" else "Show Answers",
                                        style = MaterialTheme.typography.labelMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = if (showAnswers) Color.White else primaryColor
                                    )
                                }
                            }
                        }
                    }
                }
            }
            
            // Expanded Questions - Compact Layout
            AnimatedVisibility(
                visible = expanded && pyqQuestions.isNotEmpty(),
                enter = expandVertically(animationSpec = androidx.compose.animation.core.spring()) + fadeIn(),
                exit = shrinkVertically(animationSpec = androidx.compose.animation.core.spring()) + fadeOut()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f))
                        .padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Compact Paper Header
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(2.dp, RoundedCornerShape(10.dp)),
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = primaryColor.copy(alpha = 0.15f)
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = "CBSE CLASS 10",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = primaryColor,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = "$subjectName - $year",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onSurface,
                                textAlign = TextAlign.Center
                            )
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                modifier = Modifier.padding(top = 2.dp)
                            ) {
                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = primaryColor.copy(alpha = 0.12f)
                                ) {
                                    Text(
                                        text = "⏱️ 3H",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Medium,
                                        color = primaryColor,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                    )
                                }
                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = primaryColor.copy(alpha = 0.12f)
                                ) {
                                    Text(
                                        text = "📊 80M",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Medium,
                                        color = primaryColor,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                    )
                                }
                            }
                        }
                    }
                    
                    // Organize questions by sections
                    val sectionA = pyqQuestions.filter { it.type == QuestionType.VERY_SHORT_ANSWER || it.type == QuestionType.ASSERTION_REASON }
                    val sectionB = pyqQuestions.filter { it.type == QuestionType.SHORT_ANSWER }
                    val sectionC = pyqQuestions.filter { it.type == QuestionType.LONG_ANSWER }
                    val sectionD = pyqQuestions.filter { it.type == QuestionType.CASE_BASED }
                    
                    // Section A: Very Short Answer Questions (1 mark each)
                    if (sectionA.isNotEmpty()) {
                        ExamSectionCard(
                            sectionTitle = "SECTION A",
                            sectionDescription = "Very Short Answer Type Questions (1 mark each)",
                            questions = sectionA,
                            showAnswer = showAnswers,
                            primaryColor = primaryColor,
                            startQuestionNumber = 1
                        )
                    }
                    
                    // Section B: Short Answer Questions (2-3 marks each)
                    if (sectionB.isNotEmpty()) {
                        ExamSectionCard(
                            sectionTitle = "SECTION B",
                            sectionDescription = "Short Answer Type Questions (2-3 marks each)",
                            questions = sectionB,
                            showAnswer = showAnswers,
                            primaryColor = primaryColor,
                            startQuestionNumber = sectionA.size + 1
                        )
                    }
                    
                    // Section C: Long Answer Questions (4-5 marks each)
                    if (sectionC.isNotEmpty()) {
                        ExamSectionCard(
                            sectionTitle = "SECTION C",
                            sectionDescription = "Long Answer Type Questions (4-5 marks each)",
                            questions = sectionC,
                            showAnswer = showAnswers,
                            primaryColor = primaryColor,
                            startQuestionNumber = sectionA.size + sectionB.size + 1
                        )
                    }
                    
                    // Section D: Case-Based Questions (4-5 marks each)
                    if (sectionD.isNotEmpty()) {
                        ExamSectionCard(
                            sectionTitle = "SECTION D",
                            sectionDescription = "Case-Based Questions (4-5 marks each)",
                            questions = sectionD,
                            showAnswer = showAnswers,
                            primaryColor = primaryColor,
                            startQuestionNumber = sectionA.size + sectionB.size + sectionC.size + 1
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PYQQuestionCard(
    question: ExamQuestion,
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
                spotColor = primaryColor.copy(alpha = 0.3f)
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Enhanced Question Number Badge
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = primaryColor,
                        modifier = Modifier.size(44.dp),
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
                        verticalArrangement = Arrangement.spacedBy(4.dp)
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
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontWeight = FontWeight.Medium
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
                thickness = 1.5.dp,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            
            Text(
                text = question.question,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = TextUnit(24f, TextUnitType.Sp)
            )
            
            if (question.hints.isNotEmpty()) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    color = primaryColor.copy(alpha = 0.1f),
                    shadowElevation = 2.dp
                ) {
                    Column(
                        modifier = Modifier.padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = primaryColor.copy(alpha = 0.2f),
                                modifier = Modifier.size(28.dp)
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Text(
                                        text = "💡",
                                        style = MaterialTheme.typography.bodyMedium
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
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            question.hints.forEach { hint ->
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
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
                                        lineHeight = TextUnit(20f, TextUnitType.Sp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
            
            if (showAnswer && question.answer != null) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    color = primaryColor.copy(alpha = 0.12f),
                    shadowElevation = 3.dp
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = primaryColor,
                                modifier = Modifier.size(32.dp),
                                shadowElevation = 4.dp
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Text(
                                        text = "✓",
                                        style = MaterialTheme.typography.bodyLarge,
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
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            lineHeight = TextUnit(22f, TextUnitType.Sp),
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            } else if (!showAnswer && question.answer != null) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = primaryColor.copy(alpha = 0.08f),
                    modifier = Modifier.fillMaxWidth(),
                    shadowElevation = 2.dp
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "👁️",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(
                            text = "Tap 'Show Answers' button above to view solution",
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

@Composable
fun ExamSectionCard(
    sectionTitle: String,
    sectionDescription: String,
    questions: List<ExamQuestion>,
    showAnswer: Boolean,
    primaryColor: Color,
    startQuestionNumber: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(18.dp),
                spotColor = primaryColor.copy(alpha = 0.35f)
            ),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Enhanced Section Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                primaryColor.copy(alpha = 0.2f),
                                primaryColor.copy(alpha = 0.1f)
                            )
                        ),
                        shape = RoundedCornerShape(14.dp)
                    )
                    .padding(14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
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
                                text = sectionTitle.takeLast(1),
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = sectionTitle,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = primaryColor
                        )
                        Text(
                            text = sectionDescription,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = primaryColor,
                    shadowElevation = 4.dp
                ) {
                    Text(
                        text = "${questions.size} Q",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                    )
                }
            }
            
            Divider(
                color = primaryColor.copy(alpha = 0.3f),
                thickness = 2.dp,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            
            // Questions in this section with compact spacing
            questions.forEachIndexed { index, question ->
                if (index > 0) {
                    Spacer(modifier = Modifier.height(8.dp))
                }
                PYQQuestionCard(
                    question = question,
                    index = startQuestionNumber + index,
                    showAnswer = showAnswer,
                    primaryColor = primaryColor
                )
            }
        }
    }
}

fun getQuestionTypeLabel(type: QuestionType): String {
    return when (type) {
        QuestionType.VERY_SHORT_ANSWER -> "Very Short (1M)"
        QuestionType.SHORT_ANSWER -> "Short (2-3M)"
        QuestionType.LONG_ANSWER -> "Long (4-5M)"
        QuestionType.CASE_BASED -> "Case-Based (4-5M)"
        QuestionType.ASSERTION_REASON -> "Assertion-Reason (1M)"
    }
}


