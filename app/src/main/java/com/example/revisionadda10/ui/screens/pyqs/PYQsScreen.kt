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
    
    // All years from 2019 to 2025
    val pyqYears = listOf("2025", "2024", "2023", "2022", "2021", "2020", "2019")
    
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
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(
                                elevation = 8.dp,
                                shape = RoundedCornerShape(20.dp),
                                spotColor = primaryColor.copy(alpha = 0.3f)
                            ),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = primaryColor.copy(alpha = 0.2f)
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(
                                text = "📚",
                                style = MaterialTheme.typography.displaySmall
                            )
                            Text(
                                text = "CBSE Board Exam Papers",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold,
                                color = primaryColor,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = "Previous Year Question Papers",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center
                            )
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = primaryColor.copy(alpha = 0.15f),
                                modifier = Modifier.padding(top = 4.dp)
                            ) {
                                Text(
                                    text = "2019 - 2025",
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = primaryColor,
                                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                                )
                            }
                            Text(
                                text = "Tap on any year card to expand and view questions",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(top = 4.dp)
                            )
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
    
    // Generate PDF URL
    val pdfUrl = when (subjectId) {
        "maths" -> "https://www.cbse.gov.in/cbsenew/question-paper/class-10/mathematics/$year.pdf"
        "science" -> "https://www.cbse.gov.in/cbsenew/question-paper/class-10/science/$year.pdf"
        else -> "https://www.cbse.gov.in/cbsenew/question-paper/class-10/$year.pdf"
    }
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
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
        Column {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                primaryColor.copy(alpha = 0.2f),
                                primaryColor.copy(alpha = 0.1f)
                            )
                        )
                    )
                    .clickable { expanded = !expanded }
                    .padding(24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = primaryColor.copy(alpha = 0.25f),
                        modifier = Modifier.size(64.dp),
                        shadowElevation = 4.dp
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = year,
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold,
                                color = primaryColor
                            )
                        }
                    }
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = "CBSE Class 10 - $year",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "$subjectName Question Paper",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        if (pyqQuestions.isNotEmpty()) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                                modifier = Modifier.padding(top = 4.dp)
                            ) {
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = primaryColor.copy(alpha = 0.15f)
                                ) {
                                    Text(
                                        text = "${pyqQuestions.size} Questions",
                                        style = MaterialTheme.typography.labelMedium,
                                        fontWeight = FontWeight.Medium,
                                        color = primaryColor,
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                    )
                                }
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = primaryColor.copy(alpha = 0.15f)
                                ) {
                                    Text(
                                        text = "${pyqQuestions.sumOf { it.marks }} Marks",
                                        style = MaterialTheme.typography.labelMedium,
                                        fontWeight = FontWeight.Medium,
                                        color = primaryColor,
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                    )
                                }
                            }
                        }
                    }
                }
                
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (pyqQuestions.isNotEmpty()) {
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = if (showAnswers) primaryColor else primaryColor.copy(alpha = 0.1f),
                            modifier = Modifier.clickable { showAnswers = !showAnswers }
                        ) {
                            Text(
                                text = if (showAnswers) "✓ Hide Answers" else "Show Answers",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = if (showAnswers) Color.White else primaryColor,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                            )
                        }
                    }
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = primaryColor,
                        modifier = Modifier.clickable { navController.navigate(Screen.PDFViewer.createRoute(pdfUrl)) }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = "📄",
                                style = MaterialTheme.typography.labelMedium
                            )
                            Text(
                                text = "View PDF",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = primaryColor.copy(alpha = 0.15f),
                        modifier = Modifier.clickable { expanded = !expanded }
                    ) {
                        Text(
                            text = if (expanded) "▲ Collapse" else "▼ Expand",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = primaryColor,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                        )
                    }
                }
            }
            
            // Expanded Questions - Organized by Sections (Like Actual Exam Paper)
            AnimatedVisibility(
                visible = expanded && pyqQuestions.isNotEmpty(),
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    // Paper Header
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = primaryColor.copy(alpha = 0.15f)
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "CBSE CLASS 10",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = primaryColor,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = "$subjectName - $year",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = "Time: 3 Hours | Maximum Marks: 80",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Medium,
                                color = primaryColor,
                                textAlign = TextAlign.Center
                            )
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
                spotColor = primaryColor.copy(alpha = 0.2f)
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
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
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = primaryColor.copy(alpha = 0.25f),
                        modifier = Modifier.size(40.dp),
                        shadowElevation = 2.dp
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = "$index",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = primaryColor
                            )
                        }
                    }
                    Column {
                        Text(
                            text = "Question $index",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = getQuestionTypeLabel(question.type),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = primaryColor,
                    shadowElevation = 2.dp
                ) {
                    Text(
                        text = "${question.marks} Marks",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                    )
                }
            }
            
            Divider(color = primaryColor.copy(alpha = 0.2f))
            
            Text(
                text = question.question,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = TextUnit(22f, TextUnitType.Sp)
            )
            
            if (question.hints.isNotEmpty()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = primaryColor.copy(alpha = 0.08f)
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "💡",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "Hints:",
                                style = MaterialTheme.typography.labelLarge,
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
                                    Text(
                                        text = "•",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = primaryColor,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = hint,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                            }
                        }
                    }
                }
            }
            
            if (showAnswer && question.answer != null) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(
                            elevation = 2.dp,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = primaryColor.copy(alpha = 0.12f)
                    )
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
                                shape = RoundedCornerShape(20.dp),
                                color = primaryColor,
                                modifier = Modifier.size(32.dp)
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Text(
                                        text = "✓",
                                        style = MaterialTheme.typography.titleMedium,
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
                        Divider(color = primaryColor.copy(alpha = 0.3f))
                        Text(
                            text = question.answer,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface,
                            lineHeight = TextUnit(22f, TextUnitType.Sp)
                        )
                    }
                }
            } else if (!showAnswer && question.answer != null) {
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "💡 Tap 'Show Answers' button above to view solution",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(14.dp)
                    )
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Section Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = sectionTitle,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = primaryColor
                    )
                    Text(
                        text = sectionDescription,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = primaryColor.copy(alpha = 0.15f)
                ) {
                    Text(
                        text = "${questions.size} Q",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = primaryColor,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                    )
                }
            }
            
            Divider(
                color = primaryColor.copy(alpha = 0.4f),
                thickness = 2.dp
            )
            
            // Questions in this section
            questions.forEachIndexed { index, question ->
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


