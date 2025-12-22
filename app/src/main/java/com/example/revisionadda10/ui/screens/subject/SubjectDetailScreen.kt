package com.example.revisionadda10.ui.screens.subject

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.revisionadda10.data.model.Chapter
import com.example.revisionadda10.data.repository.MCQSetGenerator
import com.example.revisionadda10.data.repository.MockData
import com.example.revisionadda10.ui.navigation.Screen
import com.example.revisionadda10.ui.ads.AdBannerCard
import com.example.revisionadda10.ui.ads.rememberAdManager
import androidx.compose.ui.platform.LocalContext
import android.app.Activity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectDetailScreen(
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
                // Top Section: 90% Match Paper and Previous Year Questions
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // 90% Match Paper Card
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
                                    elevation = 8.dp,
                                    shape = RoundedCornerShape(20.dp),
                                    spotColor = primaryColor.copy(alpha = 0.3f)
                                ),
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = primaryColor.copy(alpha = 0.15f)
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = "🎯",
                                    style = MaterialTheme.typography.displaySmall
                                )
                                Text(
                                    text = "90% Match Paper",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = primaryColor
                                )
                                Text(
                                    text = "CBSE Board Papers",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                        
                        // Previous Year Questions Card
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
                                    elevation = 8.dp,
                                    shape = RoundedCornerShape(20.dp),
                                    spotColor = primaryColor.copy(alpha = 0.3f)
                                ),
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = primaryColor.copy(alpha = 0.15f)
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = "📄",
                                    style = MaterialTheme.typography.displaySmall
                                )
                                Text(
                                    text = "Previous Year",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = primaryColor
                                )
                                Text(
                                    text = "Questions (PYQs)",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
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
                        navigateWithAd = navigateWithAd
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
    navigateWithAd: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    
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
                        Text(
                            text = chapter.title,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
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
                
                TextButton(
                    onClick = { expanded = !expanded },
                    modifier = Modifier
                        .background(
                            color = primaryColor.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    Text(
                        text = if (expanded) "▲" else "▼",
                        style = MaterialTheme.typography.titleMedium,
                        color = primaryColor,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            
            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Revision Notes
                    FeatureButton(
                        text = "📝 Revision Notes & Key Points",
                        icon = "📝",
                        onClick = {
                            navigateWithAd(
                                Screen.ChapterDetail.createRoute(subjectId, chapter.id)
                            )
                        },
                        color = primaryColor
                    )
                    
                    // YouTube Lecture
                    if (chapter.youtubeVideoId != null) {
                        FeatureButton(
                            text = "▶️ YouTube Lecture",
                            icon = "▶️",
                            onClick = {
                                navController.navigate(
                                    Screen.VideoPlayer.createRoute(chapter.youtubeVideoId)
                                )
                            },
                            color = primaryColor
                        )
                    }
                    
                    // MCQ Practice
                    if (chapter.mcqs.isNotEmpty()) {
                        val mcqSets = MCQSetGenerator.generateMCQSets(chapter.id, chapter.mcqs)
                        FeatureButton(
                            text = "✅ MCQ Practice (${mcqSets.size} Sets • ${chapter.mcqs.size} Questions)",
                            icon = "✅",
                            onClick = {
                                navigateWithAd(
                                    Screen.Quiz.createRoute(subjectId, chapter.id)
                                )
                            },
                            color = primaryColor
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
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = color.copy(alpha = 0.1f),
            contentColor = color
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 2.dp,
            pressedElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = icon,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(end = 12.dp)
            )
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

