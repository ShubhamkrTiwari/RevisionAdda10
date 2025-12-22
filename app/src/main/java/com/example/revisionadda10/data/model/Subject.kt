package com.example.revisionadda10.data.model

data class Subject(
    val id: String,
    val name: String,
    val icon: String,
    val chapters: List<Chapter>
)

data class Chapter(
    val id: String,
    val title: String,
    val revisionNotes: List<String>,
    val keyPoints: List<String>,
    val mindMapImageUrl: String? = null,
    val youtubeVideoId: String? = null,
    val pyqYears: List<String> = emptyList(),
    val mcqs: List<MCQ> = emptyList()
)

data class MCQ(
    val id: String,
    val question: String,
    val options: List<String>,
    val correctAnswerIndex: Int,
    val explanation: String? = null
)

