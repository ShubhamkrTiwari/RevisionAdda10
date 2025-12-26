package com.example.revisionadda10.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Dashboard : Screen("dashboard")
    object SubjectDetail : Screen("subject_detail/{subjectId}") {
        fun createRoute(subjectId: String) = "subject_detail/$subjectId"
    }
    object ChapterDetail : Screen("chapter_detail/{subjectId}/{chapterId}") {
        fun createRoute(subjectId: String, chapterId: String) = "chapter_detail/$subjectId/$chapterId"
    }
    object VideoPlayer : Screen("video_player/{videoId}") {
        fun createRoute(videoId: String) = "video_player/$videoId"
    }
    object Quiz : Screen("quiz/{subjectId}/{chapterId}") {
        fun createRoute(subjectId: String, chapterId: String) = "quiz/$subjectId/$chapterId"
    }
    object PYQs : Screen("pyqs/{subjectId}") {
        fun createRoute(subjectId: String) = "pyqs/$subjectId"
    }
    object MatchPaper : Screen("match_paper/{subjectId}") {
        fun createRoute(subjectId: String) = "match_paper/$subjectId"
    }
    object PDFViewer : Screen("pdf_viewer/{pdfUrl}") {
        fun createRoute(pdfUrl: String) = "pdf_viewer/${pdfUrl.replace("/", "_SLASH_").replace(":", "_COLON_")}"
    }
    object FormulaSheet : Screen("formula_sheet/{subjectId}/{chapterId}") {
        fun createRoute(subjectId: String, chapterId: String) = "formula_sheet/$subjectId/$chapterId"
    }
    object Progress : Screen("progress")
    object Settings : Screen("settings")
    object Game : Screen("game")
    object SubjectProgress : Screen("subject_progress/{subjectId}") {
        fun createRoute(subjectId: String) = "subject_progress/$subjectId"
    }
}

