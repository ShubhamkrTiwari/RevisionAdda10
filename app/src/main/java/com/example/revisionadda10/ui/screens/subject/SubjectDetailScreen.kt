package com.example.revisionadda10.ui.screens.subject

// AndroidX Compose Animation
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.core.animateFloatAsState

// AndroidX Compose Foundation
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.PaddingValues

// AndroidX Compose Material 3
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults

// AndroidX Compose Runtime
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch

// AndroidX Compose UI
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

// AndroidX Navigation
import androidx.navigation.NavController

// Android
import android.app.Activity

// App specific
import com.example.revisionadda10.data.model.Chapter
import com.example.revisionadda10.data.repository.MCQSetGenerator
import com.example.revisionadda10.data.repository.MockData
import com.example.revisionadda10.data.repository.ProgressRepository
import com.example.revisionadda10.ui.navigation.Screen
import com.example.revisionadda10.ui.ads.AdBannerCard
import com.example.revisionadda10.ui.ads.rememberAdManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectDetailScreen(
    navController: NavController,
    subjectId: String
) {
    val subject = remember(subjectId) {
        when (subjectId) {
            "maths" -> MockData.getMathsSubject()
            "science" -> MockData.getScienceSubject()
            "social_science" -> MockData.getSocialScienceSubject()
            else -> MockData.getMathsSubject()
        }
    }
    
    val primaryColor = when (subjectId) {
        "maths" -> Color(0xFF2196F3)
        "science" -> Color(0xFF4CAF50)
        "social_science" -> Color(0xFFFF9800)
        else -> Color(0xFF2196F3)
    }
    val adManager = rememberAdManager()
    val context = LocalContext.current
    
    // Load interstitial ad when screen opens
    LaunchedEffect(Unit) {
        adManager.loadInterstitialAd()
    }
    
    // Helper function to navigate with ad
    val navigateWithAd: (String) -> Unit = { route ->
        if (context is Activity && adManager.isInterstitialAdLoaded()) {
            adManager.showInterstitialAd(context as Activity) {
                navController.navigate(route)
            }
        } else {
            navController.navigate(route)
        }
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Column {
                        Text(
                            text = subject.name,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${subject.chapters.size} Chapters",
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
                // Top Section: 90% Match Paper and Previous Year Questions - Enhanced UI
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // 90% Match Paper Card - Enhanced
                        Card(
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    // Navigate to full subject match paper
                                    navController.navigate(
                                        Screen.MatchPaper.createRoute(subjectId)
                                    )
                                }
                                .shadow(
                                    elevation = 10.dp,
                                    shape = RoundedCornerShape(24.dp),
                                    spotColor = primaryColor.copy(alpha = 0.4f)
                                ),
                            shape = RoundedCornerShape(24.dp),
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
                                                primaryColor.copy(alpha = 0.1f)
                                            )
                                        )
                                    )
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(24.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    Surface(
                                        shape = RoundedCornerShape(16.dp),
                                        color = primaryColor.copy(alpha = 0.25f),
                                        modifier = Modifier.size(72.dp),
                                        shadowElevation = 6.dp
                                    ) {
                                        Box(
                                            contentAlignment = Alignment.Center,
                                            modifier = Modifier.fillMaxSize()
                                        ) {
                                            Text(
                                                text = "🎯",
                                                style = MaterialTheme.typography.displayMedium
                                            )
                                        }
                                    }
                                    Text(
                                        text = "90% Match Paper",
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.Bold,
                                        color = primaryColor,
                                        textAlign = TextAlign.Center
                                    )
                                    Text(
                                        text = "CBSE Board Papers",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        fontWeight = FontWeight.Medium,
                                        textAlign = TextAlign.Center
                                    )
                                    Surface(
                                        shape = RoundedCornerShape(12.dp),
                                        color = primaryColor.copy(alpha = 0.15f),
                                        modifier = Modifier.padding(top = 4.dp)
                                    ) {
                                        Text(
                                            text = "AI-Predicted",
                                            style = MaterialTheme.typography.labelMedium,
                                            fontWeight = FontWeight.Bold,
                                            color = primaryColor,
                                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                                        )
                                    }
                                }
                            }
                        }
                        
                        // Previous Year Questions Card - Enhanced
                        Card(
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    // Navigate to full subject PYQs
                                    navController.navigate(
                                        Screen.PYQs.createRoute(subjectId)
                                    )
                                }
                                .shadow(
                                    elevation = 10.dp,
                                    shape = RoundedCornerShape(24.dp),
                                    spotColor = primaryColor.copy(alpha = 0.4f)
                                ),
                            shape = RoundedCornerShape(24.dp),
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
                                                primaryColor.copy(alpha = 0.1f)
                                            )
                                        )
                                    )
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(24.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    Surface(
                                        shape = RoundedCornerShape(16.dp),
                                        color = primaryColor.copy(alpha = 0.25f),
                                        modifier = Modifier.size(72.dp),
                                        shadowElevation = 6.dp
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
                                        text = "Previous Year",
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.Bold,
                                        color = primaryColor,
                                        textAlign = TextAlign.Center
                                    )
                                    Text(
                                        text = "Questions (PYQs)",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        fontWeight = FontWeight.Medium,
                                        textAlign = TextAlign.Center
                                    )
                                    Surface(
                                        shape = RoundedCornerShape(12.dp),
                                        color = primaryColor.copy(alpha = 0.15f),
                                        modifier = Modifier.padding(top = 4.dp)
                                    ) {
                                        Text(
                                            text = "2019-2025",
                                            style = MaterialTheme.typography.labelMedium,
                                            fontWeight = FontWeight.Bold,
                                            color = primaryColor,
                                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                
                // Chapters List
                itemsIndexed(subject.chapters) { index, chapter ->
                    ChapterItem(
                        chapter = chapter,
                        subjectId = subjectId,
                        navController = navController,
                        index = index + 1,
                        primaryColor = primaryColor,
                        navigateWithAd = navigateWithAd,
                        context = context
                    )
                }
                
                // Show ad after chapters
                item {
                    AdBannerCard(
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ChapterItem(
    chapter: Chapter,
    subjectId: String,
    navController: NavController,
    index: Int,
    primaryColor: Color,
    navigateWithAd: (String) -> Unit,
    context: android.content.Context
) {
    var expanded by remember { mutableStateOf(false) }
    var isCompleted by remember { mutableStateOf(false) }
    var bestPercentage by remember { mutableStateOf(0f) }
    val progressRepository = remember { ProgressRepository(context) }
    val coroutineScope = rememberCoroutineScope()
    
    // Check completion status
    LaunchedEffect(subjectId, chapter.id) {
        coroutineScope.launch {
            isCompleted = progressRepository.isChapterCompleted(subjectId, chapter.id)
            val bestScore = progressRepository.getBestScore(subjectId, chapter.id)
            bestPercentage = bestScore?.percentage ?: 0f
        }
    }
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(16.dp),
                spotColor = primaryColor.copy(alpha = 0.2f)
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                primaryColor.copy(alpha = 0.1f),
                                primaryColor.copy(alpha = 0.05f)
                            )
                        )
                    )
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = primaryColor.copy(alpha = 0.2f),
                        modifier = Modifier.size(40.dp)
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
                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = chapter.title,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            // Completion Badge
                            if (isCompleted) {
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = Color(0xFF4CAF50).copy(alpha = 0.2f)
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "✓",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = Color(0xFF4CAF50),
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = if (bestPercentage > 0) "${bestPercentage.toInt()}%" else "Done",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = Color(0xFF4CAF50),
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            if (chapter.mcqs.isNotEmpty()) {
                                Chip(
                                    text = "${chapter.mcqs.size} MCQs",
                                    color = primaryColor
                                )
                            }
                            if (chapter.pyqYears.isNotEmpty()) {
                                Chip(
                                    text = "${chapter.pyqYears.size} PYQs",
                                    color = primaryColor
                                )
                            }
                        }
                    }
                }
                
                IconButton(
                    onClick = { expanded = !expanded },
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            color = primaryColor.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(12.dp)
                        ),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = primaryColor
                    )
                ) {
                    ChevronIcon(expanded = expanded, color = primaryColor)
                }
            }
            
            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically(expandFrom = Alignment.Top) + fadeIn(initialAlpha = 0.7f),
                exit = shrinkVertically(shrinkTowards = Alignment.Top) + fadeOut()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f))
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Section Header
                    Text(
                        text = "Chapter Resources",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    
                    // Revision Notes
                    FeatureButton(
                        text = "Revision Notes • Key Points & Mind Maps",
                        icon = "📚",
                        onClick = {
                            navigateWithAd(
                                Screen.ChapterDetail.createRoute(subjectId, chapter.id)
                            )
                        },
                        color = primaryColor
                    )
                    
                    // Important Dates & Years (Only for Social Science)
                    if (subjectId == "social_science" && chapter.importantDates.isNotEmpty()) {
                        FeatureButton(
                            text = "Important Dates & Years • ${chapter.importantDates.size} Dates",
                            icon = "📅",
                            onClick = {
                                navigateWithAd(
                                    Screen.ImportantDates.createRoute(subjectId, chapter.id)
                                )
                            },
                            color = Color(0xFFFF6B35) // Orange-red color for dates
                        )
                    }
                    
                    // Formula Sheet - AI Generated (Only for Maths and Science)
                    if (subjectId != "social_science") {
                        FeatureButton(
                            text = "Formula Sheet • Quick Reference",
                            icon = "📐",
                            onClick = {
                                navigateWithAd(
                                    Screen.FormulaSheet.createRoute(subjectId, chapter.id)
                                )
                            },
                            color = Color(0xFF9C27B0) // Purple color for formulas
                        )
                    }
                    
                    // MCQ Practice
                    if (chapter.mcqs.isNotEmpty()) {
                        val mcqSets = MCQSetGenerator.generateMCQSets(chapter.id, chapter.mcqs)
                        val completionText = if (isCompleted && bestPercentage > 0) {
                            " • Best: ${bestPercentage.toInt()}%"
                        } else {
                            ""
                        }
                        FeatureButton(
                            text = "MCQ Practice • ${mcqSets.size} Sets • ${chapter.mcqs.size} Questions$completionText",
                            icon = if (isCompleted) "✅" else "📝",
                            onClick = {
                                navigateWithAd(
                                    Screen.Quiz.createRoute(subjectId, chapter.id)
                                )
                            },
                            color = if (isCompleted) Color(0xFF4CAF50) else Color(0xFF34C759) // Green color for MCQs
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Chip(
    text: String,
    color: Color
) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = color.copy(alpha = 0.15f)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = color,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}

@Composable
fun FeatureButton(
    text: String,
    icon: String,
    onClick: () -> Unit,
    color: Color
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .clip(RoundedCornerShape(16.dp))
            .shadow(
                elevation = if (isPressed) 2.dp else 6.dp,
                shape = RoundedCornerShape(16.dp),
                spotColor = color.copy(alpha = 0.3f),
                ambientColor = color.copy(alpha = 0.1f)
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    onClick()
                }
            ),
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = if (isPressed) 1.dp else 2.dp,
        shadowElevation = if (isPressed) 1.dp else 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            color.copy(alpha = 0.05f),
                            color.copy(alpha = 0.02f)
                        )
                    )
                )
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            // Icon Container
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = color.copy(alpha = 0.15f),
                modifier = Modifier.size(48.dp),
                shadowElevation = if (isPressed) 0.dp else 2.dp
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = icon,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.scale(1.2f)
                    )
                }
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // Text Content
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = text.split(" ")[0],
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = color,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                if (text.contains("•")) {
                    Text(
                        text = text.substringAfter("•").trim(),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                } else {
                    Text(
                        text = text.split(" ").drop(1).joinToString(" "),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            
            // Chevron Icon
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null,
                tint = color.copy(alpha = 0.7f),
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
private fun ChevronIcon(expanded: Boolean, color: Color) {
    val rotationAngle by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "chevronRotation"
    )
    
    Icon(
        imageVector = Icons.Default.ArrowDropDown,
        contentDescription = if (expanded) "Collapse" else "Expand",
        tint = color,
        modifier = Modifier
            .size(24.dp)
            .rotate(rotationAngle)
            .graphicsLayer {
                rotationZ = rotationAngle
            }
    )
}
