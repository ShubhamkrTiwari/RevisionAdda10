package com.example.revisionadda10.data.model

data class MCQSet(
    val id: String,
    val title: String,
    val questions: List<MCQ>,
    val setNumber: Int = 1
)

