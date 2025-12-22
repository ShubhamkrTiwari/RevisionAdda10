package com.example.revisionadda10.data.model

data class ExamQuestion(
    val id: String,
    val question: String,
    val marks: Int,
    val type: QuestionType,
    val answer: String? = null,
    val hints: List<String> = emptyList()
)

enum class QuestionType {
    VERY_SHORT_ANSWER,  // 1 mark
    SHORT_ANSWER,       // 2-3 marks
    LONG_ANSWER,        // 4-5 marks
    CASE_BASED,         // 4-5 marks
    ASSERTION_REASON    // 1 mark
}

