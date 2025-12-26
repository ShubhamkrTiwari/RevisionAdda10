package com.example.revisionadda10.data.repository

import com.example.revisionadda10.data.model.MCQ
import com.example.revisionadda10.data.model.MCQSet

object MCQSetGenerator {
    
    /**
     * Generates 6-8 sets of 10 questions each from the given MCQs
     * If there are fewer questions, creates sets with available questions
     */
    fun generateMCQSets(chapterId: String, allMCQs: List<MCQ>): List<MCQSet> {
        if (allMCQs.isEmpty()) return emptyList()
        
        val sets = mutableListOf<MCQSet>()
        val questionsPerSet = 10
        // For Science, Maths, and Social Science chapters, generate exactly 7 sets if enough questions available
        val calculatedSets = (allMCQs.size + questionsPerSet - 1) / questionsPerSet
        val numberOfSets = if ((chapterId.startsWith("science_") || chapterId.startsWith("maths_") || chapterId.startsWith("social_")) && allMCQs.size >= 70) {
            7 // Exactly 7 sets for Science, Maths, and Social Science chapters
        } else {
            minOf(8, maxOf(6, calculatedSets))
        }
        
        // Shuffle questions to distribute them across sets
        val shuffledQuestions = allMCQs.shuffled()
        
        for (setIndex in 0 until numberOfSets) {
            val startIndex = setIndex * questionsPerSet
            val endIndex = minOf(startIndex + questionsPerSet, shuffledQuestions.size)
            
            if (startIndex < shuffledQuestions.size) {
                val setQuestions = shuffledQuestions.subList(startIndex, endIndex)
                sets.add(
                    MCQSet(
                        id = "${chapterId}_set_${setIndex + 1}",
                        title = "Set ${setIndex + 1}",
                        questions = setQuestions,
                        setNumber = setIndex + 1
                    )
                )
            }
        }
        
        return sets
    }
    
    /**
     * Generates additional MCQs if needed to fill sets
     */
    fun generateAdditionalMCQs(chapterId: String, baseMCQs: List<MCQ>, requiredCount: Int): List<MCQ> {
        val additional = mutableListOf<MCQ>()
        val baseCount = baseMCQs.size
        
        if (baseCount >= requiredCount) return emptyList()
        
        // Generate variations of existing questions or new questions
        for (i in baseCount until requiredCount) {
            additional.add(
                MCQ(
                    id = "${chapterId}_q${i + 1}",
                    question = "Question ${i + 1}: This is a practice question. Select the correct option.",
                    options = listOf("Option A", "Option B", "Option C", "Option D"),
                    correctAnswerIndex = i % 4,
                    explanation = "This is a practice question for better understanding."
                )
            )
        }
        
        return additional
    }
}

