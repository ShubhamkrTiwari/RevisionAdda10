package com.example.revisionadda10.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.revisionadda10.ui.screens.MainScreen
import com.example.revisionadda10.ui.screens.chapter.ChapterDetailScreen
import com.example.revisionadda10.ui.screens.formulasheet.FormulaSheetScreen
import com.example.revisionadda10.ui.screens.matchpaper.MatchPaperScreen
import com.example.revisionadda10.ui.screens.progress.SubjectProgressDetailScreen
import com.example.revisionadda10.ui.screens.pdf.PDFViewerScreen
import com.example.revisionadda10.ui.screens.pyqs.PYQsScreen
import com.example.revisionadda10.ui.screens.quiz.QuizScreen
import com.example.revisionadda10.ui.screens.game.GameScreen
import com.example.revisionadda10.ui.screens.settings.SettingsScreen
import com.example.revisionadda10.ui.screens.splash.SplashScreen
import com.example.revisionadda10.ui.screens.subject.SubjectDetailScreen
import com.example.revisionadda10.ui.screens.video.VideoPlayerScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    onThemeChange: ((Boolean) -> Unit)? = null
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        
        composable(Screen.Dashboard.route) {
            MainScreen(
                navController = navController,
                onThemeChange = onThemeChange
            )
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
        
        composable(Screen.FormulaSheet.route) { backStackEntry ->
            val subjectId = backStackEntry.arguments?.getString("subjectId") ?: ""
            val chapterId = backStackEntry.arguments?.getString("chapterId") ?: ""
            FormulaSheetScreen(
                navController = navController,
                subjectId = subjectId,
                chapterId = chapterId
            )
        }
        
        composable(Screen.Progress.route) {
            MainScreen(
                navController = navController,
                onThemeChange = onThemeChange
            )
        }
        
        composable(Screen.Settings.route) {
            MainScreen(
                navController = navController,
                onThemeChange = onThemeChange
            )
        }
        
        composable(Screen.Game.route) {
            MainScreen(
                navController = navController,
                onThemeChange = onThemeChange
            )
        }
        
        composable(Screen.SubjectProgress.route) { backStackEntry ->
            val subjectId = backStackEntry.arguments?.getString("subjectId") ?: ""
            SubjectProgressDetailScreen(
                navController = navController,
                subjectId = subjectId
            )
        }
    }
}

