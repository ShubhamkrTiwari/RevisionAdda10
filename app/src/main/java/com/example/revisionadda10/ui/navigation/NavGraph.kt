package com.example.revisionadda10.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.revisionadda10.ui.screens.chapter.ChapterDetailScreen
import com.example.revisionadda10.ui.screens.dashboard.DashboardScreen
import com.example.revisionadda10.ui.screens.matchpaper.MatchPaperScreen
import com.example.revisionadda10.ui.screens.pdf.PDFViewerScreen
import com.example.revisionadda10.ui.screens.pyqs.PYQsScreen
import com.example.revisionadda10.ui.screens.quiz.QuizScreen
import com.example.revisionadda10.ui.screens.subject.SubjectDetailScreen
import com.example.revisionadda10.ui.screens.video.VideoPlayerScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Dashboard.route
    ) {
        composable(Screen.Dashboard.route) {
            DashboardScreen(navController = navController)
        }
        
        composable(Screen.SubjectDetail.route) { backStackEntry ->
            val subjectId = backStackEntry.arguments?.getString("subjectId") ?: ""
            SubjectDetailScreen(
                navController = navController,
                subjectId = subjectId
            )
        }
        
        composable(Screen.ChapterDetail.route) { backStackEntry ->
            val subjectId = backStackEntry.arguments?.getString("subjectId") ?: ""
            val chapterId = backStackEntry.arguments?.getString("chapterId") ?: ""
            ChapterDetailScreen(
                navController = navController,
                subjectId = subjectId,
                chapterId = chapterId
            )
        }
        
        composable(Screen.VideoPlayer.route) { backStackEntry ->
            val videoId = backStackEntry.arguments?.getString("videoId") ?: ""
            VideoPlayerScreen(
                navController = navController,
                videoId = videoId
            )
        }
        
        composable(Screen.Quiz.route) { backStackEntry ->
            val subjectId = backStackEntry.arguments?.getString("subjectId") ?: ""
            val chapterId = backStackEntry.arguments?.getString("chapterId") ?: ""
            QuizScreen(
                navController = navController,
                subjectId = subjectId,
                chapterId = chapterId
            )
        }
        
        composable(Screen.PYQs.route) { backStackEntry ->
            val subjectId = backStackEntry.arguments?.getString("subjectId") ?: ""
            PYQsScreen(
                navController = navController,
                subjectId = subjectId
            )
        }
        
        composable(Screen.MatchPaper.route) { backStackEntry ->
            val subjectId = backStackEntry.arguments?.getString("subjectId") ?: ""
            MatchPaperScreen(
                navController = navController,
                subjectId = subjectId
            )
        }
        
        composable(Screen.PDFViewer.route) { backStackEntry ->
            val pdfUrl = backStackEntry.arguments?.getString("pdfUrl")
                ?.replace("_SLASH_", "/")
                ?.replace("_COLON_", ":") ?: ""
            PDFViewerScreen(
                navController = navController,
                pdfUrl = pdfUrl
            )
        }
    }
}

