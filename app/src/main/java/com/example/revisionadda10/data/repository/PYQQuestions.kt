package com.example.revisionadda10.data.repository

import com.example.revisionadda10.data.model.ExamQuestion
import com.example.revisionadda10.data.model.QuestionType

object PYQQuestions {
    
    fun getPYQQuestionsForYear(subjectId: String, year: String): List<ExamQuestion> {
        return when (subjectId) {
            "maths" -> getMathsPYQs(year)
            "science" -> getSciencePYQs(year)
            "social_science" -> getSocialSciencePYQs(year)
            else -> emptyList<ExamQuestion>()
        }
    }
    
    private fun getMathsPYQs(year: String): List<ExamQuestion> {
        return when (year) {
            "2025" -> getMaths2025Questions()
            "2024" -> getMaths2024Questions()
            "2023" -> getMaths2023Questions()
            "2022" -> getMaths2022Questions()
            "2021" -> getMaths2021Questions()
            "2020" -> getMaths2020Questions()
            "2019" -> getMaths2019Questions()
            else -> emptyList<ExamQuestion>()
        }
    }
    
    private fun getSciencePYQs(year: String): List<ExamQuestion> {
        return when (year) {
            "2025" -> getScience2025Questions()
            "2024" -> getScience2024Questions()
            "2023" -> getScience2023Questions()
            "2022" -> getScience2022Questions()
            "2021" -> getScience2021Questions()
            "2020" -> getScience2020Questions()
            "2019" -> getScience2019Questions()
            else -> emptyList<ExamQuestion>()
        }
    }
    
    private fun getSocialSciencePYQs(year: String): List<ExamQuestion> {
        return when (year) {
            "2025" -> getSocialScience2025Questions()
            "2024" -> getSocialScience2024Questions()
            "2023" -> getSocialScience2023Questions()
            "2022" -> getSocialScience2022Questions()
            "2021" -> getSocialScience2021Questions()
            "2020" -> getSocialScience2020Questions()
            "2019" -> getSocialScience2019Questions()
            else -> emptyList<ExamQuestion>()
        }
    }
    
    // Comprehensive PYQ Papers 2019-2025 with 30 questions each (organized by sections)
    
    // ========== MATHS 2025 ==========
    private fun getMaths2025Questions(): List<ExamQuestion> {
        return listOf(
            // Section A: Very Short Answer (10 questions, 1 mark each)
            ExamQuestion(id = "m2025_sa1", question = "If HCF (306, 657) = 9, then find LCM (306, 657).", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "LCM = 22338", hints = listOf("Use formula: HCF × LCM = Product")),
            ExamQuestion(id = "m2025_sa2", question = "Find the zeroes of polynomial x² - 2x - 8.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "x = 4, x = -2", hints = listOf("Factorize the quadratic")),
            ExamQuestion(id = "m2025_sa3", question = "What is the nature of roots of 2x² - 4x + 3 = 0?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "No real roots (D < 0)", hints = listOf("Calculate discriminant")),
            ExamQuestion(id = "m2025_sa4", question = "If sin A = 3/5, find cos A.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "cos A = 4/5", hints = listOf("Use sin²A + cos²A = 1")),
            ExamQuestion(id = "m2025_sa5", question = "Find the 10th term of AP: 5, 8, 11, 14, ...", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "a₁₀ = 32", hints = listOf("Use formula: aₙ = a + (n-1)d")),
            ExamQuestion(id = "m2025_sa6", question = "What is the distance between points (2, 3) and (4, 1)?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "2√2 units", hints = listOf("Use distance formula")),
            ExamQuestion(id = "m2025_sa7", question = "If two triangles are similar, what is the ratio of their areas?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Square of ratio of corresponding sides", hints = listOf("Area ratio = (side ratio)²")),
            ExamQuestion(id = "m2025_sa8", question = "Find the value of tan 45° + cot 45°.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "2", hints = listOf("tan 45° = 1, cot 45° = 1")),
            ExamQuestion(id = "m2025_sa9", question = "What is the formula for volume of a sphere?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "V = (4/3)πr³", hints = listOf("Volume of sphere")),
            ExamQuestion(id = "m2025_sa10", question = "If probability of an event is 0.3, what is probability of its complement?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "0.7", hints = listOf("P(not E) = 1 - P(E)")),
            
            // Section B: Short Answer (10 questions, 2-3 marks each)
            ExamQuestion(id = "m2025_sb1", question = "Solve: x + y = 14 and x - y = 4", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "x = 9, y = 5", hints = listOf("Use elimination or substitution method")),
            ExamQuestion(id = "m2025_sb2", question = "Find the zeroes of x² - 2x - 8 and verify relationship with coefficients.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Zeroes: 4, -2. Sum = 2 = -b/a, Product = -8 = c/a", hints = listOf("Factorize, find zeroes, verify formulas")),
            ExamQuestion(id = "m2025_sb3", question = "If α and β are zeroes of x² - 6x + k and α - β = 2, find k.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "k = 8", hints = listOf("Use (α - β)² = (α + β)² - 4αβ")),
            ExamQuestion(id = "m2025_sb4", question = "Find the 10th term from end of AP: 5, 8, 11, ..., 95.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "68", hints = listOf("Use formula: nth term from end = l - (n-1)d")),
            ExamQuestion(id = "m2025_sb5", question = "If points A(2,3), B(4,k), C(6,-3) are collinear, find k.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "k = 0", hints = listOf("Area of triangle = 0 for collinear points")),
            ExamQuestion(id = "m2025_sb6", question = "Prove that √3 is irrational.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Use proof by contradiction. Assume √3 = p/q, show both p and q divisible by 3, contradiction.", hints = listOf("Proof by contradiction")),
            ExamQuestion(id = "m2025_sb7", question = "A chord of circle radius 10 cm subtends 90° at centre. Find area of minor segment.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "28.5 cm²", hints = listOf("Area of sector - Area of triangle")),
            ExamQuestion(id = "m2025_sb8", question = "Find value of k for which kx(x-2) + 6 = 0 has equal roots.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "k = 6", hints = listOf("Set discriminant = 0")),
            ExamQuestion(id = "m2025_sb9", question = "If sin A = 3/5, find cos A and tan A.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "cos A = 4/5, tan A = 3/4", hints = listOf("Use identities")),
            ExamQuestion(id = "m2025_sb10", question = "Express 0.6 + 0.7̅ + 0.4̅7̅ in form p/q.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "917/495", hints = listOf("Convert each to fraction, then add")),
            
            // Section C: Long Answer (6 questions, 4-5 marks each)
            ExamQuestion(id = "m2025_sc1", question = "Sum of 4th and 8th terms of AP is 24, sum of 6th and 10th terms is 44. Find first three terms.", marks = 4, type = QuestionType.LONG_ANSWER, answer = "First three terms: -13, -8, -3", hints = listOf("Form equations, solve for a and d")),
            ExamQuestion(id = "m2025_sc2", question = "A train travels 360 km. If speed increased by 5 km/h, time reduces by 1 hour. Find original speed.", marks = 4, type = QuestionType.LONG_ANSWER, answer = "Speed = 40 km/h", hints = listOf("Form quadratic equation")),
            ExamQuestion(id = "m2025_sc3", question = "Prove: Ratio of areas of similar triangles = square of ratio of corresponding sides.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Draw altitudes, use area formula, apply proportionality", hints = listOf("Use similar triangle properties")),
            ExamQuestion(id = "m2025_sc4", question = "A solid is cone on hemisphere, radius 3.5 cm, cone height 4 cm. Find total volume.", marks = 4, type = QuestionType.LONG_ANSWER, answer = "44.91π cm³ ≈ 141 cm³", hints = listOf("Add hemisphere and cone volumes")),
            ExamQuestion(id = "m2025_sc5", question = "Angle of elevation of building from tower foot is 30°, tower from building foot is 60°. Tower is 50 m. Find building height.", marks = 4, type = QuestionType.LONG_ANSWER, answer = "16.67 m", hints = listOf("Use trigonometric ratios")),
            ExamQuestion(id = "m2025_sc6", question = "A circle touches all sides of quadrilateral ABCD. Prove: AB + CD = BC + AD.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Use property: Tangents from external point are equal", hints = listOf("Apply tangent properties")),
            
            // Section D: Case-Based (4 questions, 4-5 marks each)
            ExamQuestion(id = "m2025_sd1", question = "Case Study: A school has 1000 students. 60% play cricket, 50% play football, 30% play both. (i) How many play only cricket? (ii) How many play neither?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) 300 students (ii) 200 students", hints = listOf("Use set theory, Venn diagram")),
            ExamQuestion(id = "m2025_sd2", question = "Case Study: A farmer has rectangular field 120m × 90m. He wants to fence it and also divide into equal square plots. Find maximum side of square plot and number of plots.", marks = 5, type = QuestionType.CASE_BASED, answer = "Side = 30m, Number of plots = 12", hints = listOf("Find HCF of 120 and 90")),
            ExamQuestion(id = "m2025_sd3", question = "Case Study: A tower stands on horizontal plane. From point 20m away, angle of elevation is 60°. Find height. If observer moves 10m closer, find new angle.", marks = 5, type = QuestionType.CASE_BASED, answer = "Height = 20√3 m, New angle = 75°", hints = listOf("Use trigonometry")),
            ExamQuestion(id = "m2025_sd4", question = "Case Study: In a class, mean marks are 75. If marks of 5 students are 60, 70, 80, 85, 90, find total number of students if mean remains 75 after adding these.", marks = 5, type = QuestionType.CASE_BASED, answer = "Total students = 20", hints = listOf("Use mean formula"))
        )
    }
    
    // ========== MATHS 2024 ==========
    private fun getMaths2024Questions(): List<ExamQuestion> {
        return listOf(
            // Section A (10 questions, 1 mark each) - All unique
            ExamQuestion(id = "m2024_sa1", question = "If HCF (144, 180) = 36, find LCM (144, 180).", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "LCM = 720", hints = listOf("Use formula: HCF × LCM = Product")),
            ExamQuestion(id = "m2024_sa2", question = "Find zeroes of polynomial 2x² - 7x + 3.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "x = 3, x = 1/2", hints = listOf("Factorize the quadratic")),
            ExamQuestion(id = "m2024_sa3", question = "What is nature of roots of x² + 4x + 4 = 0?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Equal roots (D = 0)", hints = listOf("Calculate discriminant")),
            ExamQuestion(id = "m2024_sa4", question = "If cos B = 12/13, find sin B.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "sin B = 5/13", hints = listOf("Use sin²B + cos²B = 1")),
            ExamQuestion(id = "m2024_sa5", question = "Find 12th term of AP: 7, 11, 15, 19, ...", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "a₁₂ = 51", hints = listOf("Use formula: aₙ = a + (n-1)d")),
            ExamQuestion(id = "m2024_sa6", question = "Distance between points (-2, 3) and (4, -1)?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "2√13 units", hints = listOf("Use distance formula")),
            ExamQuestion(id = "m2024_sa7", question = "If ratio of corresponding sides of similar triangles is 2:3, what is ratio of perimeters?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "2:3", hints = listOf("Perimeter ratio = side ratio")),
            ExamQuestion(id = "m2024_sa8", question = "Find value of sin 60° - cos 30°.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "0", hints = listOf("sin 60° = √3/2, cos 30° = √3/2")),
            ExamQuestion(id = "m2024_sa9", question = "What is formula for total surface area of cylinder?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "2πr(r + h)", hints = listOf("TSA of cylinder")),
            ExamQuestion(id = "m2024_sa10", question = "If probability of event A is 0.25, find P(not A).", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "0.75", hints = listOf("P(not A) = 1 - P(A)")),
            
            // Section B (10 questions, 2-3 marks each) - All unique
            ExamQuestion(id = "m2024_sb1", question = "Solve: 3x - 4y = 10, 2x + 5y = 1", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "x = 54/23, y = -17/23", hints = listOf("Elimination or substitution method")),
            ExamQuestion(id = "m2024_sb2", question = "If α and β are zeroes of 3x² - 5x + 2, find α² + β².", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "13/9", hints = listOf("Use (α + β)² = α² + β² + 2αβ")),
            ExamQuestion(id = "m2024_sb3", question = "Find sum of first 20 terms of AP: 4, 9, 14, 19, ...", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "1030", hints = listOf("Sₙ = n/2[2a + (n-1)d]")),
            ExamQuestion(id = "m2024_sb4", question = "Prove that √11 is irrational.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Proof by contradiction. Assume √11 = p/q, show both p and q divisible by 11, contradiction.", hints = listOf("Proof by contradiction")),
            ExamQuestion(id = "m2024_sb5", question = "If sec C = 5/4, find sin C and cos C.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "sin C = 3/5, cos C = 4/5", hints = listOf("Use identities")),
            ExamQuestion(id = "m2024_sb6", question = "Find area of triangle with vertices (2,1), (5,3), (3,4).", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "3.5 sq units", hints = listOf("Area formula")),
            ExamQuestion(id = "m2024_sb7", question = "A chord 8 cm long is 3 cm from centre. Find radius.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "5 cm", hints = listOf("Use perpendicular from centre")),
            ExamQuestion(id = "m2024_sb8", question = "Find value of m for which 5x² - 2mx + 5 = 0 has equal roots.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "m = ±5", hints = listOf("Set discriminant = 0")),
            ExamQuestion(id = "m2024_sb9", question = "Volume of cone: radius 5 cm, height 12 cm.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "100π cm³", hints = listOf("V = (1/3)πr²h")),
            ExamQuestion(id = "m2024_sb10", question = "Median of data: 12, 15, 18, 20, 22, 25, 28?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "20", hints = listOf("Middle value")),
            
            // Section C (6 questions, 4-5 marks each) - All unique
            ExamQuestion(id = "m2024_sc1", question = "Sum of 5th and 9th terms of AP is 30, sum of 7th and 11th terms is 46. Find first term and common difference.", marks = 4, type = QuestionType.LONG_ANSWER, answer = "a = 1, d = 3", hints = listOf("Form equations, solve for a and d")),
            ExamQuestion(id = "m2024_sc2", question = "A boat goes 24 km upstream and 28 km downstream in 6 hours. It goes 30 km upstream and 21 km downstream in 6.5 hours. Find speed of boat and stream.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Boat = 10 km/h, Stream = 2 km/h", hints = listOf("Form equations")),
            ExamQuestion(id = "m2024_sc3", question = "Prove that in a circle, angle subtended by an arc at center is double the angle at circumference.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Use arc properties and angle sum of triangle", hints = listOf("Circle theorem")),
            ExamQuestion(id = "m2024_sc4", question = "A solid is cylinder with hemispherical ends. Cylinder radius 4 cm, height 10 cm. Find total volume.", marks = 4, type = QuestionType.LONG_ANSWER, answer = "176π cm³", hints = listOf("Cylinder volume + 2 hemisphere volumes")),
            ExamQuestion(id = "m2024_sc5", question = "From point on ground 15m from base of building, angle of elevation of top is 60°. Find height. If observer moves 5m closer, find new angle.", marks = 4, type = QuestionType.LONG_ANSWER, answer = "Height = 15√3 m, New angle = 75°", hints = listOf("Use trigonometric ratios")),
            ExamQuestion(id = "m2024_sc6", question = "Construct triangle with sides 6 cm, 7 cm, 8 cm. Then construct similar triangle with scale factor 3/4.", marks = 4, type = QuestionType.LONG_ANSWER, answer = "Construction steps", hints = listOf("SSS construction")),
            
            // Section D (4 questions, 4-5 marks each) - All unique
            ExamQuestion(id = "m2024_sd1", question = "Case: A factory produces 500 items daily. 60% are first quality, 30% are second quality, 10% are defective. (i) How many are first quality? (ii) If 20 defective items are removed, what percentage are first quality?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) 300 items (ii) 62.5%", hints = listOf("Calculate percentages")),
            ExamQuestion(id = "m2024_sd2", question = "Case: In a survey of 150 people, 80 like movies, 70 like music, 40 like both. (i) How many like only movies? (ii) How many like neither?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) 40 (ii) 40", hints = listOf("Venn diagram")),
            ExamQuestion(id = "m2024_sd3", question = "Case: A circular garden radius 14m has path 3m wide around it. Find area of path. Also find cost of paving at ₹75 per m².", marks = 5, type = QuestionType.CASE_BASED, answer = "Area = 93π m² ≈ 292 m², Cost = ₹21,900", hints = listOf("Area of path = Outer area - Inner area")),
            ExamQuestion(id = "m2024_sd4", question = "Case: Mean of 25 numbers is 18. If 5 numbers with mean 24 are added, find new mean.", marks = 5, type = QuestionType.CASE_BASED, answer = "New mean = 19", hints = listOf("Use mean formula"))
        )
    }
    
    // ========== MATHS 2023 ==========
    private fun getMaths2023Questions(): List<ExamQuestion> {
        return listOf(
            ExamQuestion(id = "m2023_sa1", question = "If HCF (26, 91) = 13, find LCM (26, 91).", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "LCM = 182", hints = listOf("HCF × LCM = Product")),
            ExamQuestion(id = "m2023_sa2", question = "Find zeroes of polynomial x² - 7x + 12.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "x = 3, x = 4", hints = listOf("Factorize")),
            ExamQuestion(id = "m2023_sa3", question = "What is nature of roots of x² - 6x + 9 = 0?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Equal roots (D = 0)", hints = listOf("Calculate discriminant")),
            ExamQuestion(id = "m2023_sa4", question = "If cos A = 5/13, find sin A.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "sin A = 12/13", hints = listOf("Use sin²A + cos²A = 1")),
            ExamQuestion(id = "m2023_sa5", question = "Find 20th term of AP: 2, 5, 8, 11, ...", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "a₂₀ = 59", hints = listOf("aₙ = a + (n-1)d")),
            ExamQuestion(id = "m2023_sa6", question = "Distance between points (-1, 2) and (3, 4)?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "2√5 units", hints = listOf("Distance formula")),
            ExamQuestion(id = "m2023_sa7", question = "If ratio of sides of similar triangles is 3:5, what is ratio of areas?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "9:25", hints = listOf("Area ratio = (side ratio)²")),
            ExamQuestion(id = "m2023_sa8", question = "Find value of sin 30° + cos 60°.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "1", hints = listOf("sin 30° = 1/2, cos 60° = 1/2")),
            ExamQuestion(id = "m2023_sa9", question = "What is formula for curved surface area of cylinder?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "2πrh", hints = listOf("CSA of cylinder")),
            ExamQuestion(id = "m2023_sa10", question = "If P(E) = 0.05, find P(not E).", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "0.95", hints = listOf("P(not E) = 1 - P(E)")),
            ExamQuestion(id = "m2023_sb1", question = "Solve: 3x + 2y = 11, 2x + 3y = 4", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "x = 5, y = -2", hints = listOf("Elimination method")),
            ExamQuestion(id = "m2023_sb2", question = "If α and β are zeroes of 2x² - 5x + 3, find α² + β².", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "13/4", hints = listOf("Use (α + β)² = α² + β² + 2αβ")),
            ExamQuestion(id = "m2023_sb3", question = "Find sum of first 25 terms of AP: 7, 11, 15, 19, ...", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "1375", hints = listOf("Sₙ = n/2[2a + (n-1)d]")),
            ExamQuestion(id = "m2023_sb4", question = "Prove that √7 is irrational.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Proof by contradiction", hints = listOf("Assume rational, show contradiction")),
            ExamQuestion(id = "m2023_sb5", question = "If sec A = 13/12, find tan A and cot A.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "tan A = 5/12, cot A = 12/5", hints = listOf("Use sec²A = 1 + tan²A")),
            ExamQuestion(id = "m2023_sb6", question = "Find area of triangle with vertices (1,2), (3,4), (5,1).", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "4 sq units", hints = listOf("Area formula")),
            ExamQuestion(id = "m2023_sb7", question = "A chord 16 cm long is 6 cm from centre. Find radius.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "10 cm", hints = listOf("Use perpendicular from centre")),
            ExamQuestion(id = "m2023_sb8", question = "Find k for which x² + kx + 9 = 0 has equal roots.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "k = ±6", hints = listOf("D = 0")),
            ExamQuestion(id = "m2023_sb9", question = "Volume of cone: radius 6 cm, height 8 cm.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "96π cm³", hints = listOf("V = (1/3)πr²h")),
            ExamQuestion(id = "m2023_sb10", question = "Median of data: 3, 5, 7, 9, 11, 13, 15?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "9", hints = listOf("Middle value")),
            ExamQuestion(id = "m2023_sc1", question = "Sum of 3rd and 7th terms of AP is 6, sum of 5th and 9th terms is 14. Find AP.", marks = 4, type = QuestionType.LONG_ANSWER, answer = "AP: -1, 1, 3, 5, ...", hints = listOf("Form equations, solve")),
            ExamQuestion(id = "m2023_sc2", question = "A boat goes 30 km upstream and 44 km downstream in 10 hours. In 13 hours, it goes 40 km upstream and 55 km downstream. Find speed of boat and stream.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Boat = 8 km/h, Stream = 3 km/h", hints = listOf("Form equations")),
            ExamQuestion(id = "m2023_sc3", question = "Prove that in a right triangle, square of hypotenuse equals sum of squares of other two sides.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Pythagoras theorem proof", hints = listOf("Use similar triangles")),
            ExamQuestion(id = "m2023_sc4", question = "A solid is cylinder surmounted by cone. Cylinder radius 3 cm, height 5 cm. Cone height 4 cm. Find total volume.", marks = 4, type = QuestionType.LONG_ANSWER, answer = "51π cm³", hints = listOf("Add volumes")),
            ExamQuestion(id = "m2023_sc5", question = "From top of building 20 m high, angle of depression of foot of tower is 45°. If tower is 30 m high, find distance between them.", marks = 4, type = QuestionType.LONG_ANSWER, answer = "10 m", hints = listOf("Use trigonometry")),
            ExamQuestion(id = "m2023_sc6", question = "Prove that opposite angles of cyclic quadrilateral are supplementary.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Use arc properties and angle sum", hints = listOf("Cyclic quadrilateral theorem")),
            ExamQuestion(id = "m2023_sd1", question = "Case: A library has 500 books. 200 are fiction, 250 are non-fiction, 50 are both. (i) How many are only fiction? (ii) How many are neither?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) 150 (ii) 100", hints = listOf("Venn diagram")),
            ExamQuestion(id = "m2023_sd2", question = "Case: A rectangular garden 80m × 60m has path 3m wide inside. Find area of path and cost of paving at ₹100 per m².", marks = 5, type = QuestionType.CASE_BASED, answer = "Area = 804 m², Cost = ₹80,400", hints = listOf("Inner area - Outer area")),
            ExamQuestion(id = "m2023_sd3", question = "Case: A pole casts shadow 15 m when sun's elevation is 60°. Find height. If elevation becomes 30°, find new shadow length.", marks = 5, type = QuestionType.CASE_BASED, answer = "Height = 15√3 m, Shadow = 45 m", hints = listOf("Use trigonometry")),
            ExamQuestion(id = "m2023_sd4", question = "Case: Mean of 20 numbers is 35. If 5 numbers with mean 40 are added, find new mean.", marks = 5, type = QuestionType.CASE_BASED, answer = "New mean = 36", hints = listOf("Use mean formula"))
        )
    }
    
    // ========== MATHS 2022 ==========
    private fun getMaths2022Questions(): List<ExamQuestion> {
        return listOf(
            ExamQuestion(id = "m2022_sa1", question = "If HCF (18, 24) = 6, find LCM (18, 24).", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "LCM = 72", hints = listOf("HCF × LCM = Product")),
            ExamQuestion(id = "m2022_sa2", question = "Find zeroes of x² - 9x + 20.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "x = 4, x = 5", hints = listOf("Factorize")),
            ExamQuestion(id = "m2022_sa3", question = "What is discriminant of x² + 4x + 4 = 0?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "D = 0", hints = listOf("D = b² - 4ac")),
            ExamQuestion(id = "m2022_sa4", question = "If tan A = 4/3, find sin A and cos A.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "sin A = 4/5, cos A = 3/5", hints = listOf("Use triangle")),
            ExamQuestion(id = "m2022_sa5", question = "Find 15th term of AP: 10, 13, 16, 19, ...", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "a₁₅ = 52", hints = listOf("aₙ = a + (n-1)d")),
            ExamQuestion(id = "m2022_sa6", question = "Distance between (5, -2) and (-1, 3)?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "√61 units", hints = listOf("Distance formula")),
            ExamQuestion(id = "m2022_sa7", question = "If two circles have radii 3 cm and 5 cm, what is ratio of their areas?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "9:25", hints = listOf("Area ratio = (radius ratio)²")),
            ExamQuestion(id = "m2022_sa8", question = "Find value of cos 60° - sin 30°.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "0", hints = listOf("cos 60° = 1/2, sin 30° = 1/2")),
            ExamQuestion(id = "m2022_sa9", question = "What is formula for total surface area of cube?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "6a²", hints = listOf("TSA of cube")),
            ExamQuestion(id = "m2022_sa10", question = "If probability of event is 2/5, find probability of its complement.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "3/5", hints = listOf("P(not E) = 1 - P(E)")),
            ExamQuestion(id = "m2022_sb1", question = "Solve: 5x - 3y = 11, 2x + 5y = 1", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "x = 2, y = -1", hints = listOf("Elimination method")),
            ExamQuestion(id = "m2022_sb2", question = "If α and β are zeroes of x² - x - 6, find 1/α + 1/β.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "-1/6", hints = listOf("Use (α + β)/αβ")),
            ExamQuestion(id = "m2022_sb3", question = "Find sum of first 30 natural numbers.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "465", hints = listOf("Sₙ = n(n+1)/2")),
            ExamQuestion(id = "m2022_sb4", question = "Prove that √11 is irrational.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Proof by contradiction", hints = listOf("Assume rational")),
            ExamQuestion(id = "m2022_sb5", question = "If cosec A = 5/4, find sin A and cos A.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "sin A = 4/5, cos A = 3/5", hints = listOf("Use identities")),
            ExamQuestion(id = "m2022_sb6", question = "Find area of triangle with vertices (-2,0), (2,0), (0,4).", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "8 sq units", hints = listOf("Area formula")),
            ExamQuestion(id = "m2022_sb7", question = "A chord 24 cm long is 5 cm from centre. Find radius.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "13 cm", hints = listOf("Use perpendicular")),
            ExamQuestion(id = "m2022_sb8", question = "Find k for which 3x² - 2kx + 3 = 0 has equal roots.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "k = ±3", hints = listOf("D = 0")),
            ExamQuestion(id = "m2022_sb9", question = "Volume of sphere with radius 7 cm.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "1437.33 cm³", hints = listOf("V = (4/3)πr³")),
            ExamQuestion(id = "m2022_sb10", question = "Mode of data: 2, 3, 4, 3, 5, 3, 6?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "3", hints = listOf("Most frequent")),
            ExamQuestion(id = "m2022_sc1", question = "Sum of 5th and 9th terms of AP is 30, sum of 7th and 11th terms is 46. Find first term and common difference.", marks = 4, type = QuestionType.LONG_ANSWER, answer = "a = 1, d = 3", hints = listOf("Form equations")),
            ExamQuestion(id = "m2022_sc2", question = "A man travels 600 km partly by train and partly by car. If he covers 400 km by train and rest by car, it takes 6.5 hours. If he travels 200 km by train and rest by car, it takes 8 hours. Find speeds.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Train = 100 km/h, Car = 80 km/h", hints = listOf("Form equations")),
            ExamQuestion(id = "m2022_sc3", question = "Prove that angle in semicircle is right angle.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Use angle properties", hints = listOf("Semicircle theorem")),
            ExamQuestion(id = "m2022_sc4", question = "A solid is hemisphere on cylinder. Cylinder radius 4 cm, height 6 cm. Find total volume.", marks = 4, type = QuestionType.LONG_ANSWER, answer = "128π cm³", hints = listOf("Add volumes")),
            ExamQuestion(id = "m2022_sc5", question = "From point on ground, angles of elevation of top and bottom of tower are 60° and 45°. If tower height is 20 m, find distance from point.", marks = 4, type = QuestionType.LONG_ANSWER, answer = "20(√3 - 1) m", hints = listOf("Use trigonometry")),
            ExamQuestion(id = "m2022_sc6", question = "Prove that angles in same segment of circle are equal.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Use arc properties", hints = listOf("Circle theorem")),
            ExamQuestion(id = "m2022_sd1", question = "Case: In a survey of 200 people, 120 like tea, 80 like coffee, 40 like both. (i) How many like only tea? (ii) How many like neither?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) 80 (ii) 40", hints = listOf("Venn diagram")),
            ExamQuestion(id = "m2022_sd2", question = "Case: A square park 100m side has circular fountain of radius 7m at center. Find area of remaining park and cost of grass at ₹50 per m².", marks = 5, type = QuestionType.CASE_BASED, answer = "Area = 9846 m², Cost = ₹4,92,300", hints = listOf("Square area - Circle area")),
            ExamQuestion(id = "m2022_sd3", question = "Case: A tree breaks and top touches ground at 30° angle 10m from base. Find original height. If it breaks at different point, find new height.", marks = 5, type = QuestionType.CASE_BASED, answer = "Height = 10√3 m", hints = listOf("Use trigonometry")),
            ExamQuestion(id = "m2022_sd4", question = "Case: Mean of 15 numbers is 25. If 3 numbers with mean 30 are removed, find new mean.", marks = 5, type = QuestionType.CASE_BASED, answer = "New mean = 24.375", hints = listOf("Use mean formula"))
        )
    }
    
    // ========== MATHS 2021 ==========
    private fun getMaths2021Questions(): List<ExamQuestion> {
        return listOf(
            ExamQuestion(id = "m2021_sa1", question = "If HCF (144, 180) = 36, find LCM (144, 180).", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "LCM = 720", hints = listOf("HCF × LCM = Product")),
            ExamQuestion(id = "m2021_sa2", question = "Find zeroes of x² - 8x + 15.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "x = 3, x = 5", hints = listOf("Factorize")),
            ExamQuestion(id = "m2021_sa3", question = "What is nature of roots of 4x² - 12x + 9 = 0?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Equal roots", hints = listOf("D = 0")),
            ExamQuestion(id = "m2021_sa4", question = "If sin θ = 1/2, find θ.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "30°", hints = listOf("sin 30° = 1/2")),
            ExamQuestion(id = "m2021_sa5", question = "Find 12th term of AP: 6, 10, 14, 18, ...", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "a₁₂ = 50", hints = listOf("aₙ = a + (n-1)d")),
            ExamQuestion(id = "m2021_sa6", question = "Distance between (0, 5) and (0, -3)?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "8 units", hints = listOf("Distance formula")),
            ExamQuestion(id = "m2021_sa7", question = "If ratio of perimeters of similar triangles is 2:3, what is ratio of areas?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "4:9", hints = listOf("Area ratio = (perimeter ratio)²")),
            ExamQuestion(id = "m2021_sa8", question = "Find value of sec 45° + cosec 45°.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "2√2", hints = listOf("sec 45° = √2, cosec 45° = √2")),
            ExamQuestion(id = "m2021_sa9", question = "What is formula for volume of hemisphere?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "V = (2/3)πr³", hints = listOf("Volume of hemisphere")),
            ExamQuestion(id = "m2021_sa10", question = "If P(A) = 0.4 and P(B) = 0.3, find P(A or B) if A and B are mutually exclusive.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "0.7", hints = listOf("P(A or B) = P(A) + P(B)")),
            ExamQuestion(id = "m2021_sb1", question = "Solve: x - 2y = 3, 3x + y = 5", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "x = 13/7, y = -4/7", hints = listOf("Substitution method")),
            ExamQuestion(id = "m2021_sb2", question = "If α and β are zeroes of 3x² - 4x + 1, find α/β + β/α.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "10/3", hints = listOf("Use (α² + β²)/αβ")),
            ExamQuestion(id = "m2021_sb3", question = "Find sum of first 40 even numbers.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "1640", hints = listOf("Sₙ = n(n+1)")),
            ExamQuestion(id = "m2021_sb4", question = "Prove that √13 is irrational.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Proof by contradiction", hints = listOf("Assume rational")),
            ExamQuestion(id = "m2021_sb5", question = "If cot A = 12/5, find sin A and cos A.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "sin A = 5/13, cos A = 12/13", hints = listOf("Use triangle")),
            ExamQuestion(id = "m2021_sb6", question = "Find area of triangle with vertices (0,0), (6,0), (0,8).", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "24 sq units", hints = listOf("Area formula")),
            ExamQuestion(id = "m2021_sb7", question = "A chord 20 cm long is 12 cm from centre. Find radius.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "2√61 cm", hints = listOf("Use perpendicular")),
            ExamQuestion(id = "m2021_sb8", question = "Find k for which x² + 4x + k = 0 has real roots.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "k ≤ 4", hints = listOf("D ≥ 0")),
            ExamQuestion(id = "m2021_sb9", question = "Volume of cylinder: radius 5 cm, height 12 cm.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "300π cm³", hints = listOf("V = πr²h")),
            ExamQuestion(id = "m2021_sb10", question = "Range of data: 15, 20, 25, 30, 35?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "20", hints = listOf("Max - Min")),
            ExamQuestion(id = "m2021_sc1", question = "Sum of 6th and 10th terms of AP is 52, sum of 8th and 12th terms is 68. Find AP.", marks = 4, type = QuestionType.LONG_ANSWER, answer = "AP: 4, 7, 10, 13, ...", hints = listOf("Form equations")),
            ExamQuestion(id = "m2021_sc2", question = "A person travels 300 km. If he increases speed by 10 km/h, he saves 1 hour. Find original speed.", marks = 4, type = QuestionType.LONG_ANSWER, answer = "Speed = 50 km/h", hints = listOf("Form quadratic")),
            ExamQuestion(id = "m2021_sc3", question = "Prove that tangent and radius at point of contact are perpendicular.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Proof by contradiction", hints = listOf("Assume not perpendicular")),
            ExamQuestion(id = "m2021_sc4", question = "A solid is cone on cylinder. Cylinder radius 4 cm, height 6 cm. Cone height 3 cm. Find total volume.", marks = 4, type = QuestionType.LONG_ANSWER, answer = "112π cm³", hints = listOf("Add volumes")),
            ExamQuestion(id = "m2021_sc5", question = "From top of cliff 60 m high, angles of depression of two boats are 30° and 45°. Find distance between boats.", marks = 4, type = QuestionType.LONG_ANSWER, answer = "60(√3 - 1) m", hints = listOf("Use trigonometry")),
            ExamQuestion(id = "m2021_sc6", question = "Prove that sum of opposite angles of cyclic quadrilateral is 180°.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Use arc properties", hints = listOf("Cyclic quadrilateral")),
            ExamQuestion(id = "m2021_sd1", question = "Case: In a class of 50, 30 play basketball, 25 play volleyball, 10 play both. (i) How many play only basketball? (ii) How many play at least one?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) 20 (ii) 45", hints = listOf("Venn diagram")),
            ExamQuestion(id = "m2021_sd2", question = "Case: A circular park radius 14m has path 2m wide around it. Find area of path and cost at ₹75 per m².", marks = 5, type = QuestionType.CASE_BASED, answer = "Area = 60π m², Cost = ₹14,137", hints = listOf("Outer - Inner")),
            ExamQuestion(id = "m2021_sd3", question = "Case: A ladder 10m long reaches window 8m high. Find distance from wall. If ladder slides 2m, find new distance.", marks = 5, type = QuestionType.CASE_BASED, answer = "Distance = 6m, New = 8m", hints = listOf("Pythagoras")),
            ExamQuestion(id = "m2021_sd4", question = "Case: Mean of 25 numbers is 20. If 5 numbers with mean 25 are added, find new mean.", marks = 5, type = QuestionType.CASE_BASED, answer = "New mean = 20.83", hints = listOf("Use mean formula"))
        )
    }
    
    // ========== MATHS 2020 ==========
    private fun getMaths2020Questions(): List<ExamQuestion> {
        return listOf(
            ExamQuestion(id = "m2020_sa1", question = "If HCF (100, 140) = 20, find LCM (100, 140).", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "LCM = 700", hints = listOf("HCF × LCM = Product")),
            ExamQuestion(id = "m2020_sa2", question = "Find zeroes of x² - 10x + 21.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "x = 3, x = 7", hints = listOf("Factorize")),
            ExamQuestion(id = "m2020_sa3", question = "What is discriminant of 5x² - 6x + 1 = 0?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "D = 16", hints = listOf("D = b² - 4ac")),
            ExamQuestion(id = "m2020_sa4", question = "If cos θ = √3/2, find θ.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "30°", hints = listOf("cos 30° = √3/2")),
            ExamQuestion(id = "m2020_sa5", question = "Find 8th term of AP: 4, 7, 10, 13, ...", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "a₈ = 25", hints = listOf("aₙ = a + (n-1)d")),
            ExamQuestion(id = "m2020_sa6", question = "Distance between (4, 0) and (0, 3)?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "5 units", hints = listOf("Distance formula")),
            ExamQuestion(id = "m2020_sa7", question = "If two squares have sides 4 cm and 6 cm, what is ratio of their areas?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "4:9", hints = listOf("Area ratio = (side ratio)²")),
            ExamQuestion(id = "m2020_sa8", question = "Find value of sin 60° × cos 30° + cos 60° × sin 30°.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "1", hints = listOf("Use values")),
            ExamQuestion(id = "m2020_sa9", question = "What is formula for lateral surface area of cone?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "πrl", hints = listOf("LSA of cone")),
            ExamQuestion(id = "m2020_sa10", question = "If probability of winning is 0.6, find probability of losing.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "0.4", hints = listOf("P(lose) = 1 - P(win)")),
            ExamQuestion(id = "m2020_sb1", question = "Solve: 4x + 5y = 19, 3x - 2y = 3", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "x = 3, y = 7/5", hints = listOf("Elimination")),
            ExamQuestion(id = "m2020_sb2", question = "If α and β are zeroes of x² + 5x + 6, find α³ + β³.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "-35", hints = listOf("Use (α + β)³ = α³ + β³ + 3αβ(α + β)")),
            ExamQuestion(id = "m2020_sb3", question = "Find sum of first 50 odd numbers.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "2500", hints = listOf("Sₙ = n²")),
            ExamQuestion(id = "m2020_sb4", question = "Prove that √17 is irrational.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Proof by contradiction", hints = listOf("Assume rational")),
            ExamQuestion(id = "m2020_sb5", question = "If sin A = 5/13, find all other trigonometric ratios.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "cos = 12/13, tan = 5/12, etc.", hints = listOf("Use identities")),
            ExamQuestion(id = "m2020_sb6", question = "Find area of triangle with vertices (2,3), (4,5), (6,1).", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "4 sq units", hints = listOf("Area formula")),
            ExamQuestion(id = "m2020_sb7", question = "A chord 18 cm long is 4 cm from centre. Find radius.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "√97 cm", hints = listOf("Use perpendicular")),
            ExamQuestion(id = "m2020_sb8", question = "Find k for which 4x² - 8x + k = 0 has no real roots.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "k > 4", hints = listOf("D < 0")),
            ExamQuestion(id = "m2020_sb9", question = "Volume of hemisphere with radius 6 cm.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "144π cm³", hints = listOf("V = (2/3)πr³")),
            ExamQuestion(id = "m2020_sb10", question = "Mean of first 10 natural numbers?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "5.5", hints = listOf("Sum/n")),
            ExamQuestion(id = "m2020_sc1", question = "Sum of 4th and 6th terms of AP is 20, sum of 5th and 7th terms is 24. Find first term.", marks = 4, type = QuestionType.LONG_ANSWER, answer = "a = 2", hints = listOf("Form equations")),
            ExamQuestion(id = "m2020_sc2", question = "A rectangular park is 50m long and 30m wide. A path 2m wide runs inside. Find area of path and cost of paving at ₹80 per m².", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Area = 304 m², Cost = ₹24,320", hints = listOf("Outer - Inner")),
            ExamQuestion(id = "m2020_sc3", question = "Prove that equal chords subtend equal angles at center.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Use congruent triangles", hints = listOf("Circle theorem")),
            ExamQuestion(id = "m2020_sc4", question = "A solid is sphere of radius 5 cm with cylindrical hole of radius 2 cm through center. Find volume of remaining solid.", marks = 4, type = QuestionType.LONG_ANSWER, answer = "(500π/3 - 20π) cm³", hints = listOf("Sphere - Cylinder")),
            ExamQuestion(id = "m2020_sc5", question = "From top of building 25 m high, angle of elevation of top of tower is 45° and angle of depression of foot is 30°. Find tower height.", marks = 4, type = QuestionType.LONG_ANSWER, answer = "25(1 + 1/√3) m", hints = listOf("Use trigonometry")),
            ExamQuestion(id = "m2020_sc6", question = "Prove that angle subtended by arc at center is double angle at circumference.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Use arc properties", hints = listOf("Circle theorem")),
            ExamQuestion(id = "m2020_sd1", question = "Case: In a group of 60, 35 speak Hindi, 30 speak English, 15 speak both. (i) How many speak only Hindi? (ii) How many speak at least one?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) 20 (ii) 50", hints = listOf("Venn diagram")),
            ExamQuestion(id = "m2020_sd2", question = "Case: A square field 50m side has circular pond radius 10m. Find area of remaining field and cost of grass at ₹40 per m².", marks = 5, type = QuestionType.CASE_BASED, answer = "Area = 2186 m², Cost = ₹87,440", hints = listOf("Square - Circle")),
            ExamQuestion(id = "m2020_sd3", question = "Case: A flagpole 12m high casts shadow 16m. Find sun's elevation. If shadow becomes 8m, find new elevation.", marks = 5, type = QuestionType.CASE_BASED, answer = "Elevation = 36.87°, New = 56.31°", hints = listOf("Use trigonometry")),
            ExamQuestion(id = "m2020_sd4", question = "Case: Mean of 30 numbers is 18. If 10 numbers with mean 22 are added, find new mean.", marks = 5, type = QuestionType.CASE_BASED, answer = "New mean = 19", hints = listOf("Use mean formula"))
        )
    }
    
    // ========== MATHS 2019 ==========
    private fun getMaths2019Questions(): List<ExamQuestion> {
        return listOf(
            ExamQuestion(id = "m2019_sa1", question = "If HCF (32, 48) = 16, find LCM (32, 48).", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "LCM = 96", hints = listOf("HCF × LCM = Product")),
            ExamQuestion(id = "m2019_sa2", question = "Find zeroes of x² - 11x + 30.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "x = 5, x = 6", hints = listOf("Factorize")),
            ExamQuestion(id = "m2019_sa3", question = "What is nature of roots of x² + 6x + 9 = 0?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Equal roots", hints = listOf("D = 0")),
            ExamQuestion(id = "m2019_sa4", question = "If tan θ = 1/√3, find θ.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "30°", hints = listOf("tan 30° = 1/√3")),
            ExamQuestion(id = "m2019_sa5", question = "Find 9th term of AP: 3, 6, 9, 12, ...", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "a₉ = 27", hints = listOf("aₙ = a + (n-1)d")),
            ExamQuestion(id = "m2019_sa6", question = "Distance between (-3, 4) and (1, -2)?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "2√13 units", hints = listOf("Distance formula")),
            ExamQuestion(id = "m2019_sa7", question = "If two rectangles have lengths 5:7, what is ratio of areas?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "25:49", hints = listOf("Area ratio = (length ratio)²")),
            ExamQuestion(id = "m2019_sa8", question = "Find value of 2 sin 30° cos 30°.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "√3/2", hints = listOf("Use sin 60°")),
            ExamQuestion(id = "m2019_sa9", question = "What is formula for surface area of sphere?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "4πr²", hints = listOf("Surface area of sphere")),
            ExamQuestion(id = "m2019_sa10", question = "If probability of event is 1/3, find probability of its complement.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "2/3", hints = listOf("P(not E) = 1 - P(E)")),
            ExamQuestion(id = "m2019_sb1", question = "Solve: 2x + 3y = 8, x - y = 1", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "x = 11/5, y = 6/5", hints = listOf("Substitution")),
            ExamQuestion(id = "m2019_sb2", question = "If α and β are zeroes of 2x² - 3x + 1, find α²β + αβ².", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "3/4", hints = listOf("Use αβ(α + β)")),
            ExamQuestion(id = "m2019_sb3", question = "Find sum of first 20 multiples of 3.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "630", hints = listOf("Sₙ = n/2[2a + (n-1)d]")),
            ExamQuestion(id = "m2019_sb4", question = "Prove that √19 is irrational.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Proof by contradiction", hints = listOf("Assume rational")),
            ExamQuestion(id = "m2019_sb5", question = "If sec A = 13/5, find sin A and cos A.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "sin A = 12/13, cos A = 5/13", hints = listOf("Use identities")),
            ExamQuestion(id = "m2019_sb6", question = "Find area of triangle with vertices (1,1), (4,2), (3,5).", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "5.5 sq units", hints = listOf("Area formula")),
            ExamQuestion(id = "m2019_sb7", question = "A chord 14 cm long is 3 cm from centre. Find radius.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "√58 cm", hints = listOf("Use perpendicular")),
            ExamQuestion(id = "m2019_sb8", question = "Find k for which x² - 6x + k = 0 has real and distinct roots.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "k < 9", hints = listOf("D > 0")),
            ExamQuestion(id = "m2019_sb9", question = "Volume of cube with side 8 cm.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "512 cm³", hints = listOf("V = a³")),
            ExamQuestion(id = "m2019_sb10", question = "Median of first 9 prime numbers?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "7", hints = listOf("Middle value")),
            ExamQuestion(id = "m2019_sc1", question = "Sum of 3rd and 5th terms of AP is 16, sum of 4th and 6th terms is 20. Find first term and common difference.", marks = 4, type = QuestionType.LONG_ANSWER, answer = "a = 2, d = 3", hints = listOf("Form equations")),
            ExamQuestion(id = "m2019_sc2", question = "A two-digit number is 4 times sum of digits. If 18 is added, digits are reversed. Find number.", marks = 4, type = QuestionType.LONG_ANSWER, answer = "24", hints = listOf("Form equations")),
            ExamQuestion(id = "m2019_sc3", question = "Prove that perpendicular from center to chord bisects chord.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Use congruent triangles", hints = listOf("Circle theorem")),
            ExamQuestion(id = "m2019_sc4", question = "A solid is cylinder with hemispherical ends. Cylinder radius 3 cm, height 10 cm. Find total volume.", marks = 4, type = QuestionType.LONG_ANSWER, answer = "99π cm³", hints = listOf("Cylinder + 2 hemispheres")),
            ExamQuestion(id = "m2019_sc5", question = "From point on ground 20m from base of tower, angle of elevation is 60°. Find height. If observer moves 10m away, find new angle.", marks = 4, type = QuestionType.LONG_ANSWER, answer = "Height = 20√3 m, Angle = 45°", hints = listOf("Use trigonometry")),
            ExamQuestion(id = "m2019_sc6", question = "Prove that angles in alternate segment are equal.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Use circle properties", hints = listOf("Alternate segment theorem")),
            ExamQuestion(id = "m2019_sd1", question = "Case: In a school of 200, 120 study Maths, 100 study Science, 60 study both. (i) How many study only Maths? (ii) How many study neither?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) 60 (ii) 40", hints = listOf("Venn diagram")),
            ExamQuestion(id = "m2019_sd2", question = "Case: A rectangular plot 80m × 60m has path 4m wide around it. Find area of path and cost at ₹60 per m².", marks = 5, type = QuestionType.CASE_BASED, answer = "Area = 1184 m², Cost = ₹71,040", hints = listOf("Outer - Inner")),
            ExamQuestion(id = "m2019_sd3", question = "Case: A tree 15m high breaks. Top touches ground 8m from base at 30° angle. Find original height and distance from base.", marks = 5, type = QuestionType.CASE_BASED, answer = "Height = 15m, Distance = 8m", hints = listOf("Use trigonometry")),
            ExamQuestion(id = "m2019_sd4", question = "Case: Mean of 20 numbers is 15. If 4 numbers with mean 20 are removed, find new mean.", marks = 5, type = QuestionType.CASE_BASED, answer = "New mean = 14.375", hints = listOf("Use mean formula"))
        )
    }
    
    // ========== SCIENCE 2025-2019 ==========
    private fun getScience2025Questions(): List<ExamQuestion> {
        return listOf(
            // Section A (10 questions, 1 mark each)
            ExamQuestion(id = "s2025_sa1", question = "Balance: Fe₂O₃ + Al → Fe + Al₂O₃", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Fe₂O₃ + 2Al → 2Fe + Al₂O₃", hints = listOf("Balance atoms")),
            ExamQuestion(id = "s2025_sa2", question = "What is pH of neutral solution?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "7", hints = listOf("Neutral = 7")),
            ExamQuestion(id = "s2025_sa3", question = "Name the process by which plants make food.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Photosynthesis", hints = listOf("Food making process")),
            ExamQuestion(id = "s2025_sa4", question = "What is rusting?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Oxidation of iron", hints = listOf("Corrosion of iron")),
            ExamQuestion(id = "s2025_sa5", question = "Which gas is evolved when metal reacts with acid?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Hydrogen", hints = listOf("H₂ gas")),
            ExamQuestion(id = "s2025_sa6", question = "What is the role of acid in stomach?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Digestion and kills bacteria", hints = listOf("HCl functions")),
            ExamQuestion(id = "s2025_sa7", question = "Name the green pigment in leaves.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Chlorophyll", hints = listOf("Green pigment")),
            ExamQuestion(id = "s2025_sa8", question = "What is angle of reflection if angle of incidence is 30°?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "30°", hints = listOf("Equal to incidence")),
            ExamQuestion(id = "s2025_sa9", question = "Which type of reaction is: 2KClO₃ → 2KCl + 3O₂?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Decomposition", hints = listOf("Breaks down")),
            ExamQuestion(id = "s2025_sa10", question = "What is the unit of electric current?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Ampere (A)", hints = listOf("SI unit")),
            
            // Section B (10 questions, 2-3 marks each)
            ExamQuestion(id = "s2025_sb1", question = "What happens when zinc is added to dilute H₂SO₄? Write equation.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Zn + H₂SO₄ → ZnSO₄ + H₂. Displacement reaction.", hints = listOf("Hydrogen gas evolved")),
            ExamQuestion(id = "s2025_sb2", question = "Explain process of photosynthesis with equation.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "6CO₂ + 6H₂O → C₆H₁₂O₆ + 6O₂ (sunlight, chlorophyll)", hints = listOf("Food making process")),
            ExamQuestion(id = "s2025_sb3", question = "Why do we apply paint on iron articles?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "To prevent rusting by forming protective layer", hints = listOf("Prevent corrosion")),
            ExamQuestion(id = "s2025_sb4", question = "State laws of reflection.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "1. Angle of incidence = angle of reflection. 2. All rays in same plane.", hints = listOf("Two laws")),
            ExamQuestion(id = "s2025_sb5", question = "Differentiate between aerobic and anaerobic respiration.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Aerobic: needs O₂, more energy. Anaerobic: no O₂, less energy.", hints = listOf("Compare oxygen requirement")),
            ExamQuestion(id = "s2025_sb6", question = "What is meant by rancidity? How to prevent?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "Oxidation of fats. Prevent: antioxidants, airtight containers.", hints = listOf("Food spoilage")),
            ExamQuestion(id = "s2025_sb7", question = "Explain digestion in human beings.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Mouth (amylase), Stomach (HCl, pepsin), Small intestine (complete digestion)", hints = listOf("Different parts")),
            ExamQuestion(id = "s2025_sb8", question = "Why are metals good conductors?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "Have free electrons that can move easily", hints = listOf("Free electrons")),
            ExamQuestion(id = "s2025_sb9", question = "What is power of accommodation?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "Ability of eye to focus on objects at different distances", hints = listOf("Eye focusing")),
            ExamQuestion(id = "s2025_sb10", question = "Explain transportation in plants.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Xylem: water/minerals up. Phloem: food in all directions.", hints = listOf("Two types")),
            
            // Section C (6 questions, 4-5 marks each)
            ExamQuestion(id = "s2025_sc1", question = "Explain photosynthesis in detail with conditions and equation.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Process, equation, conditions: sunlight, chlorophyll, CO₂, H₂O, temperature", hints = listOf("Complete explanation")),
            ExamQuestion(id = "s2025_sc2", question = "Explain process of rusting and methods to prevent it.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Rusting: oxidation. Prevention: galvanization, painting, oiling, alloying", hints = listOf("Mechanism and prevention")),
            ExamQuestion(id = "s2025_sc3", question = "Explain human digestive system with all parts and their functions.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Mouth, esophagus, stomach, small intestine, large intestine, anus with functions", hints = listOf("All organs")),
            ExamQuestion(id = "s2025_sc4", question = "Explain structure and working of human eye.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Parts: cornea, iris, pupil, lens, retina. Working: image formation", hints = listOf("Structure and function")),
            ExamQuestion(id = "s2025_sc5", question = "Explain electric current, potential difference, and Ohm's law.", marks = 4, type = QuestionType.LONG_ANSWER, answer = "Current: flow of charge. PD: work done. Ohm's law: V = IR", hints = listOf("Definitions and law")),
            ExamQuestion(id = "s2025_sc6", question = "Explain magnetic effects of electric current with applications.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Current produces magnetic field. Applications: motor, generator, electromagnet", hints = listOf("Effect and uses")),
            
            // Section D (4 questions, 4-5 marks each)
            ExamQuestion(id = "s2025_sd1", question = "Case: A student observes that iron nail rusts in moist air but not in dry air. (i) Why? (ii) How to prevent? (iii) Write chemical equation.", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Needs O₂ and H₂O (ii) Prevent contact (iii) 4Fe + 3O₂ + 6H₂O → 4Fe(OH)₃", hints = listOf("Rusting conditions")),
            ExamQuestion(id = "s2025_sd2", question = "Case: In an experiment, plant kept in dark doesn't produce starch. (i) Why? (ii) What is needed? (iii) Where does it occur?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) No sunlight (ii) Sunlight, CO₂, H₂O, chlorophyll (iii) Leaves", hints = listOf("Photosynthesis requirements")),
            ExamQuestion(id = "s2025_sd3", question = "Case: When zinc is added to CuSO₄, solution turns colorless. (i) Why? (ii) Write equation. (iii) What type of reaction?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Zn displaces Cu (ii) Zn + CuSO₄ → ZnSO₄ + Cu (iii) Displacement", hints = listOf("Reactivity series")),
            ExamQuestion(id = "s2025_sd4", question = "Case: A circuit has 12V battery and 4Ω resistor. (i) Find current. (ii) If another 4Ω added in series, find new current. (iii) If in parallel?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) 3A (ii) 1.5A (iii) 6A", hints = listOf("Ohm's law, series/parallel"))
        )
    }
    
    // ========== SCIENCE 2024 ==========
    private fun getScience2024Questions(): List<ExamQuestion> {
        return listOf(
            ExamQuestion(id = "s2024_sa1", question = "Balance: CaCO₃ + HCl → CaCl₂ + H₂O + CO₂", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "CaCO₃ + 2HCl → CaCl₂ + H₂O + CO₂", hints = listOf("Balance atoms")),
            ExamQuestion(id = "s2024_sa2", question = "What is pH of acidic solution?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Less than 7", hints = listOf("Acidic < 7")),
            ExamQuestion(id = "s2024_sa3", question = "Name the process by which plants release water vapor.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Transpiration", hints = listOf("Water loss")),
            ExamQuestion(id = "s2024_sa4", question = "What is galvanization?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Coating with zinc", hints = listOf("Zinc coating")),
            ExamQuestion(id = "s2024_sa5", question = "Which gas is produced during photosynthesis?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Oxygen", hints = listOf("O₂ gas")),
            ExamQuestion(id = "s2024_sa6", question = "What is the function of pancreas?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Produces insulin and enzymes", hints = listOf("Hormone production")),
            ExamQuestion(id = "s2024_sa7", question = "Name the process of breakdown of glucose.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Respiration", hints = listOf("Energy release")),
            ExamQuestion(id = "s2024_sa8", question = "What is angle of incidence if angle of reflection is 45°?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "45°", hints = listOf("Equal angles")),
            ExamQuestion(id = "s2024_sa9", question = "Which type of reaction is: 2H₂ + O₂ → 2H₂O?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Combination", hints = listOf("Two combine")),
            ExamQuestion(id = "s2024_sa10", question = "What is the unit of resistance?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Ohm (Ω)", hints = listOf("SI unit")),
            ExamQuestion(id = "s2024_sb1", question = "What happens when copper is added to silver nitrate solution? Write equation.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Cu + 2AgNO₃ → Cu(NO₃)₂ + 2Ag. Displacement reaction.", hints = listOf("Reactivity series")),
            ExamQuestion(id = "s2024_sb2", question = "Explain process of respiration with equation.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "C₆H₁₂O₆ + 6O₂ → 6CO₂ + 6H₂O + Energy", hints = listOf("Energy release")),
            ExamQuestion(id = "s2024_sb3", question = "Why is food stored in refrigerator?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "Low temperature slows bacterial growth", hints = listOf("Prevent spoilage")),
            ExamQuestion(id = "s2024_sb4", question = "State laws of refraction.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "1. sin i/sin r = constant. 2. All rays in same plane.", hints = listOf("Snell's law")),
            ExamQuestion(id = "s2024_sb5", question = "Differentiate between arteries and veins.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Arteries: thick walls, carry blood away. Veins: thin walls, carry blood to heart.", hints = listOf("Structure and function")),
            ExamQuestion(id = "s2024_sb6", question = "What is meant by corrosion? Give example.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "Oxidation of metals. Example: rusting of iron", hints = listOf("Metal degradation")),
            ExamQuestion(id = "s2024_sb7", question = "Explain structure of human heart.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Four chambers: 2 atria, 2 ventricles. Valves prevent backflow.", hints = listOf("Chambers and valves")),
            ExamQuestion(id = "s2024_sb8", question = "Why is copper used in electrical wires?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "Good conductor, ductile, abundant", hints = listOf("Properties")),
            ExamQuestion(id = "s2024_sb9", question = "What is myopia? How is it corrected?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "Nearsightedness. Corrected with concave lens.", hints = listOf("Eye defect")),
            ExamQuestion(id = "s2024_sb10", question = "Explain excretion in plants.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Through stomata, leaf fall, root exudates", hints = listOf("Methods")),
            ExamQuestion(id = "s2024_sc1", question = "Explain process of digestion in detail with all organs and enzymes.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Mouth (amylase), Stomach (HCl, pepsin), Small intestine (bile, enzymes), Large intestine (water absorption)", hints = listOf("All organs")),
            ExamQuestion(id = "s2024_sc2", question = "Explain methods to prevent corrosion with examples.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Galvanization, painting, alloying, oiling, electroplating", hints = listOf("Various methods")),
            ExamQuestion(id = "s2024_sc3", question = "Explain human circulatory system with blood flow.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Heart pumps blood through arteries, capillaries, veins. Double circulation.", hints = listOf("Blood flow")),
            ExamQuestion(id = "s2024_sc4", question = "Explain structure and working of human ear.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Outer, middle, inner ear. Sound waves converted to nerve impulses.", hints = listOf("Parts and function")),
            ExamQuestion(id = "s2024_sc5", question = "Explain series and parallel connections with differences.", marks = 4, type = QuestionType.LONG_ANSWER, answer = "Series: same current, voltage divides. Parallel: same voltage, current divides.", hints = listOf("Compare connections")),
            ExamQuestion(id = "s2024_sc6", question = "Explain electromagnetic induction with applications.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Changing magnetic field induces current. Applications: generator, transformer", hints = listOf("Principle and uses")),
            ExamQuestion(id = "s2024_sd1", question = "Case: A student observes that copper vessel turns green. (i) Why? (ii) What is formed? (iii) How to prevent?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Corrosion (ii) Basic copper carbonate (iii) Clean and dry", hints = listOf("Corrosion process")),
            ExamQuestion(id = "s2024_sd2", question = "Case: A plant kept in dark for 48 hours doesn't show starch. (i) Why? (ii) What happens? (iii) What is needed?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) No photosynthesis (ii) Uses stored starch (iii) Sunlight", hints = listOf("Photosynthesis requirements")),
            ExamQuestion(id = "s2024_sd3", question = "Case: When iron nail is placed in CuSO₄, solution turns green. (i) Why? (ii) Write equation. (iii) What type?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Fe displaces Cu (ii) Fe + CuSO₄ → FeSO₄ + Cu (iii) Displacement", hints = listOf("Reactivity")),
            ExamQuestion(id = "s2024_sd4", question = "Case: A circuit has 6V battery. Two 3Ω resistors in series. (i) Find current. (ii) If in parallel, find current. (iii) Compare power.", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) 1A (ii) 4A (iii) Parallel has more power", hints = listOf("Ohm's law, power"))
        )
    }
    
    // ========== SCIENCE 2023 ==========
    private fun getScience2023Questions(): List<ExamQuestion> {
        return listOf(
            ExamQuestion(id = "s2023_sa1", question = "Balance: Zn + H₂SO₄ → ZnSO₄ + H₂", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Zn + H₂SO₄ → ZnSO₄ + H₂", hints = listOf("Already balanced")),
            ExamQuestion(id = "s2023_sa2", question = "What is pH of basic solution?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Greater than 7", hints = listOf("Basic > 7")),
            ExamQuestion(id = "s2023_sa3", question = "Name the process by which plants take in CO₂.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Photosynthesis", hints = listOf("CO₂ intake")),
            ExamQuestion(id = "s2023_sa4", question = "What is alloying?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Mixing metals", hints = listOf("Metal mixture")),
            ExamQuestion(id = "s2023_sa5", question = "Which gas is used in respiration?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Oxygen", hints = listOf("O₂")),
            ExamQuestion(id = "s2023_sa6", question = "What is the function of liver?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Produces bile, detoxifies", hints = listOf("Digestion and detox")),
            ExamQuestion(id = "s2023_sa7", question = "Name the energy currency of cell.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "ATP", hints = listOf("Energy molecule")),
            ExamQuestion(id = "s2023_sa8", question = "What is focal length of plane mirror?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Infinite", hints = listOf("No focus")),
            ExamQuestion(id = "s2023_sa9", question = "Which type of reaction is: CaO + H₂O → Ca(OH)₂?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Combination", hints = listOf("Two combine")),
            ExamQuestion(id = "s2023_sa10", question = "What is the unit of power?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Watt (W)", hints = listOf("SI unit")),
            ExamQuestion(id = "s2023_sb1", question = "What happens when sodium reacts with water? Write equation.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "2Na + 2H₂O → 2NaOH + H₂. Violent reaction.", hints = listOf("Alkali metal")),
            ExamQuestion(id = "s2023_sb2", question = "Explain process of transpiration with importance.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Water loss from leaves. Helps in water transport and cooling.", hints = listOf("Function")),
            ExamQuestion(id = "s2023_sb3", question = "Why do we store food in air-tight containers?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "Prevents oxidation and bacterial growth", hints = listOf("Prevent spoilage")),
            ExamQuestion(id = "s2023_sb4", question = "State mirror formula.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "1/f = 1/v + 1/u", hints = listOf("Mirror equation")),
            ExamQuestion(id = "s2023_sb5", question = "Differentiate between xylem and phloem.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Xylem: water up, dead cells. Phloem: food both ways, living cells.", hints = listOf("Structure and function")),
            ExamQuestion(id = "s2023_sb6", question = "What is meant by tarnishing? Give example.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "Surface discoloration. Example: silver turning black", hints = listOf("Corrosion type")),
            ExamQuestion(id = "s2023_sb7", question = "Explain process of breathing.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Inhalation: diaphragm contracts. Exhalation: diaphragm relaxes.", hints = listOf("Mechanism")),
            ExamQuestion(id = "s2023_sb8", question = "Why is aluminum used in aircraft?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "Light, strong, corrosion-resistant", hints = listOf("Properties")),
            ExamQuestion(id = "s2023_sb9", question = "What is hypermetropia? How is it corrected?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "Farsightedness. Corrected with convex lens.", hints = listOf("Eye defect")),
            ExamQuestion(id = "s2023_sb10", question = "Explain nutrition in plants.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Autotrophic: photosynthesis. Heterotrophic: parasitic, saprophytic.", hints = listOf("Types")),
            ExamQuestion(id = "s2023_sc1", question = "Explain process of respiration in detail with types.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Aerobic: with O₂, more energy. Anaerobic: without O₂, less energy. Process and equation.", hints = listOf("Types and process")),
            ExamQuestion(id = "s2023_sc2", question = "Explain reactivity series and its applications.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Order of reactivity. Applications: displacement reactions, extraction of metals", hints = listOf("Series and uses")),
            ExamQuestion(id = "s2023_sc3", question = "Explain human respiratory system with mechanism.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Nose, trachea, bronchi, lungs. Gas exchange in alveoli.", hints = listOf("Structure and function")),
            ExamQuestion(id = "s2023_sc4", question = "Explain structure and working of human brain.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Cerebrum, cerebellum, medulla. Functions of each part.", hints = listOf("Parts and functions")),
            ExamQuestion(id = "s2023_sc5", question = "Explain electric power and energy with formulas.", marks = 4, type = QuestionType.LONG_ANSWER, answer = "Power = VI = I²R = V²/R. Energy = Power × Time.", hints = listOf("Formulas")),
            ExamQuestion(id = "s2023_sc6", question = "Explain AC and DC with differences and applications.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "AC: alternating direction. DC: constant direction. Applications of each.", hints = listOf("Types and uses")),
            ExamQuestion(id = "s2023_sd1", question = "Case: A student finds that magnesium ribbon burns with bright flame. (i) What happens? (ii) Write equation. (iii) What type?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Combines with O₂ (ii) 2Mg + O₂ → 2MgO (iii) Combination", hints = listOf("Combustion")),
            ExamQuestion(id = "s2023_sd2", question = "Case: A plant kept in CO₂-free air doesn't produce starch. (i) Why? (ii) What is needed? (iii) What happens?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) No CO₂ (ii) CO₂ needed (iii) No photosynthesis", hints = listOf("Photosynthesis requirements")),
            ExamQuestion(id = "s2023_sd3", question = "Case: When aluminum is placed in FeSO₄, no reaction. (i) Why? (ii) What does it show? (iii) Which is more reactive?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Al less reactive (ii) Reactivity series (iii) Fe more reactive", hints = listOf("Reactivity")),
            ExamQuestion(id = "s2023_sd4", question = "Case: A bulb rated 60W, 220V. (i) Find current. (ii) Find resistance. (iii) If used at 110V, find power.", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) 0.27A (ii) 807Ω (iii) 15W", hints = listOf("Power formulas"))
        )
    }
    
    // ========== SCIENCE 2022 ==========
    private fun getScience2022Questions(): List<ExamQuestion> {
        return listOf(
            ExamQuestion(id = "s2022_sa1", question = "Balance: CH₄ + O₂ → CO₂ + H₂O", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "CH₄ + 2O₂ → CO₂ + 2H₂O", hints = listOf("Balance atoms")),
            ExamQuestion(id = "s2022_sa2", question = "What is pH of pure water?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "7", hints = listOf("Neutral")),
            ExamQuestion(id = "s2022_sa3", question = "Name the process by which plants absorb water.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Osmosis", hints = listOf("Water absorption")),
            ExamQuestion(id = "s2022_sa4", question = "What is electroplating?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Coating with metal using electricity", hints = listOf("Metal coating")),
            ExamQuestion(id = "s2022_sa5", question = "Which gas is released during fermentation?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "CO₂", hints = listOf("Carbon dioxide")),
            ExamQuestion(id = "s2022_sa6", question = "What is the function of kidneys?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Filter blood, produce urine", hints = listOf("Excretion")),
            ExamQuestion(id = "s2022_sa7", question = "Name the site of photosynthesis.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Chloroplasts", hints = listOf("Cell organelle")),
            ExamQuestion(id = "s2022_sa8", question = "What is magnification of plane mirror?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "1", hints = listOf("Same size")),
            ExamQuestion(id = "s2022_sa9", question = "Which type of reaction is: 2HgO → 2Hg + O₂?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Decomposition", hints = listOf("Breaks down")),
            ExamQuestion(id = "s2022_sa10", question = "What is the unit of voltage?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Volt (V)", hints = listOf("SI unit")),
            ExamQuestion(id = "s2022_sb1", question = "What happens when calcium reacts with water? Write equation.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Ca + 2H₂O → Ca(OH)₂ + H₂. Vigorous reaction.", hints = listOf("Alkaline earth metal")),
            ExamQuestion(id = "s2022_sb2", question = "Explain process of nutrition in human beings.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Ingestion, digestion, absorption, assimilation, egestion", hints = listOf("Stages")),
            ExamQuestion(id = "s2022_sb3", question = "Why do we add salt to pickles?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "Prevents bacterial growth", hints = listOf("Preservation")),
            ExamQuestion(id = "s2022_sb4", question = "State lens formula.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "1/f = 1/v - 1/u", hints = listOf("Lens equation")),
            ExamQuestion(id = "s2022_sb5", question = "Differentiate between autotrophic and heterotrophic nutrition.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Autotrophic: makes own food. Heterotrophic: depends on others.", hints = listOf("Food making")),
            ExamQuestion(id = "s2022_sb6", question = "What is meant by oxidation? Give example.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "Gain of oxygen. Example: 2Mg + O₂ → 2MgO", hints = listOf("Oxygen gain")),
            ExamQuestion(id = "s2022_sb7", question = "Explain structure of nephron.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Glomerulus, Bowman's capsule, tubules. Filtration and reabsorption.", hints = listOf("Parts and function")),
            ExamQuestion(id = "s2022_sb8", question = "Why is gold used in jewelry?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "Non-reactive, lustrous, malleable", hints = listOf("Properties")),
            ExamQuestion(id = "s2022_sb9", question = "What is astigmatism? How is it corrected?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "Irregular curvature. Corrected with cylindrical lens.", hints = listOf("Eye defect")),
            ExamQuestion(id = "s2022_sb10", question = "Explain transport of water in plants.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Root pressure, transpiration pull, cohesion-tension", hints = listOf("Mechanisms")),
            ExamQuestion(id = "s2022_sc1", question = "Explain process of photosynthesis in detail with factors affecting it.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Process, equation, factors: light, CO₂, temperature, chlorophyll, water", hints = listOf("Complete process")),
            ExamQuestion(id = "s2022_sc2", question = "Explain types of chemical reactions with examples.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Combination, decomposition, displacement, double displacement, redox", hints = listOf("All types")),
            ExamQuestion(id = "s2022_sc3", question = "Explain human excretory system with process.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Kidneys, ureters, bladder, urethra. Filtration, reabsorption, secretion.", hints = listOf("Organs and process")),
            ExamQuestion(id = "s2022_sc4", question = "Explain structure and working of human nervous system.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Brain, spinal cord, nerves. Reflex action, voluntary action.", hints = listOf("Structure and function")),
            ExamQuestion(id = "s2022_sc5", question = "Explain heating effect of electric current with applications.", marks = 4, type = QuestionType.LONG_ANSWER, answer = "H = I²Rt. Applications: heater, iron, bulb", hints = listOf("Joule's law")),
            ExamQuestion(id = "s2022_sc6", question = "Explain working of electric motor with principle.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Magnetic effect of current. Force on conductor in magnetic field.", hints = listOf("Principle and working")),
            ExamQuestion(id = "s2022_sd1", question = "Case: A student observes that iron filings are attracted to magnet. (i) Why? (ii) What property? (iii) Give application.", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Magnetic property (ii) Ferromagnetism (iii) Electromagnet", hints = listOf("Magnetism")),
            ExamQuestion(id = "s2022_sd2", question = "Case: A plant kept without water wilts. (i) Why? (i) What happens? (iii) What is needed?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) No water (ii) Turgor loss (iii) Water", hints = listOf("Water importance")),
            ExamQuestion(id = "s2022_sd3", question = "Case: When lead is placed in CuSO₄, no reaction. (i) Why? (ii) What does it show? (iii) Which is more reactive?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Pb less reactive (ii) Reactivity series (iii) Cu more reactive", hints = listOf("Reactivity")),
            ExamQuestion(id = "s2022_sd4", question = "Case: A heater rated 1000W, 220V. (i) Find current. (ii) Find resistance. (iii) Find energy in 2 hours.", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) 4.55A (ii) 48.4Ω (iii) 2 kWh", hints = listOf("Power and energy"))
        )
    }
    
    // ========== SCIENCE 2021 ==========
    private fun getScience2021Questions(): List<ExamQuestion> {
        return listOf(
            ExamQuestion(id = "s2021_sa1", question = "Balance: Na + H₂O → NaOH + H₂", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "2Na + 2H₂O → 2NaOH + H₂", hints = listOf("Balance atoms")),
            ExamQuestion(id = "s2021_sa2", question = "What is pH of lemon juice?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Less than 7 (acidic)", hints = listOf("Acidic")),
            ExamQuestion(id = "s2021_sa3", question = "Name the process by which plants exchange gases.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Respiration and photosynthesis", hints = listOf("Gas exchange")),
            ExamQuestion(id = "s2021_sa4", question = "What is anodizing?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Coating aluminum with oxide layer", hints = listOf("Protection")),
            ExamQuestion(id = "s2021_sa5", question = "Which gas is used in fire extinguishers?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "CO₂", hints = listOf("Carbon dioxide")),
            ExamQuestion(id = "s2021_sa6", question = "What is the function of small intestine?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Complete digestion and absorption", hints = listOf("Digestion")),
            ExamQuestion(id = "s2021_sa7", question = "Name the process of energy release in cells.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Cellular respiration", hints = listOf("Energy")),
            ExamQuestion(id = "s2021_sa8", question = "What is radius of curvature of plane mirror?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Infinite", hints = listOf("Flat surface")),
            ExamQuestion(id = "s2021_sa9", question = "Which type of reaction is: AgNO₃ + NaCl → AgCl + NaNO₃?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Double displacement", hints = listOf("Exchange")),
            ExamQuestion(id = "s2021_sa10", question = "What is the unit of charge?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Coulomb (C)", hints = listOf("SI unit")),
            ExamQuestion(id = "s2021_sb1", question = "What happens when magnesium burns in air? Write equation.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "2Mg + O₂ → 2MgO. Bright white flame.", hints = listOf("Combustion")),
            ExamQuestion(id = "s2021_sb2", question = "Explain process of excretion in human beings.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Kidneys filter blood, form urine, excrete through urethra", hints = listOf("Process")),
            ExamQuestion(id = "s2021_sb3", question = "Why do we add sugar to jams?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "Prevents microbial growth", hints = listOf("Preservation")),
            ExamQuestion(id = "s2021_sb4", question = "State magnification formula.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "m = -v/u = h'/h", hints = listOf("Magnification")),
            ExamQuestion(id = "s2021_sb5", question = "Differentiate between breathing and respiration.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Breathing: physical process. Respiration: chemical process, energy release.", hints = listOf("Physical vs chemical")),
            ExamQuestion(id = "s2021_sb6", question = "What is meant by reduction? Give example.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "Loss of oxygen. Example: CuO + H₂ → Cu + H₂O", hints = listOf("Oxygen loss")),
            ExamQuestion(id = "s2021_sb7", question = "Explain structure of human kidney.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Cortex, medulla, pelvis. Nephrons for filtration.", hints = listOf("Structure")),
            ExamQuestion(id = "s2021_sb8", question = "Why is silver used in photography?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "Light-sensitive compounds", hints = listOf("Property")),
            ExamQuestion(id = "s2021_sb9", question = "What is presbyopia? How is it corrected?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "Old age vision. Corrected with bifocal lens.", hints = listOf("Eye defect")),
            ExamQuestion(id = "s2021_sb10", question = "Explain transport of food in plants.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Through phloem. Source to sink. Pressure flow mechanism.", hints = listOf("Mechanism")),
            ExamQuestion(id = "s2021_sc1", question = "Explain process of respiration in detail with equation and types.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Aerobic and anaerobic. Equations. Energy production.", hints = listOf("Types and equations")),
            ExamQuestion(id = "s2021_sc2", question = "Explain redox reactions with examples.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Oxidation and reduction together. Examples: combustion, displacement", hints = listOf("Redox concept")),
            ExamQuestion(id = "s2021_sc3", question = "Explain human digestive system with enzymes.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "All organs with enzymes: amylase, pepsin, trypsin, lipase", hints = listOf("Enzymes")),
            ExamQuestion(id = "s2021_sc4", question = "Explain structure and working of human eye with defects.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Parts, image formation, defects: myopia, hypermetropia, correction", hints = listOf("Structure and defects")),
            ExamQuestion(id = "s2021_sc5", question = "Explain electric fuse and its working.", marks = 4, type = QuestionType.LONG_ANSWER, answer = "Safety device. Melts when current exceeds. Protects circuit.", hints = listOf("Safety device")),
            ExamQuestion(id = "s2021_sc6", question = "Explain working of electric generator with principle.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Electromagnetic induction. AC generator. Mechanical to electrical energy.", hints = listOf("Principle and working")),
            ExamQuestion(id = "s2021_sd1", question = "Case: A student observes that copper turns black. (i) Why? (ii) What is formed? (iii) How to clean?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Oxidation (ii) CuO (iii) Acid cleaning", hints = listOf("Corrosion")),
            ExamQuestion(id = "s2021_sd2", question = "Case: A plant kept in CO₂-rich air produces more starch. (i) Why? (ii) What happens? (iii) What is the factor?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) More CO₂ (ii) More photosynthesis (iii) CO₂ concentration", hints = listOf("Photosynthesis factors")),
            ExamQuestion(id = "s2021_sd3", question = "Case: When silver is placed in CuSO₄, no reaction. (i) Why? (ii) What does it show? (iii) Which is more reactive?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Ag less reactive (ii) Reactivity series (iii) Cu more reactive", hints = listOf("Reactivity")),
            ExamQuestion(id = "s2021_sd4", question = "Case: A device rated 500W, 110V. (i) Find current. (ii) Find resistance. (iii) Find cost at ₹5 per kWh for 10 hours.", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) 4.55A (ii) 24.2Ω (iii) ₹25", hints = listOf("Power and cost"))
        )
    }
    
    // ========== SCIENCE 2020 ==========
    private fun getScience2020Questions(): List<ExamQuestion> {
        return listOf(
            ExamQuestion(id = "s2020_sa1", question = "Balance: C₃H₈ + O₂ → CO₂ + H₂O", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "C₃H₈ + 5O₂ → 3CO₂ + 4H₂O", hints = listOf("Balance atoms")),
            ExamQuestion(id = "s2020_sa2", question = "What is pH of soap solution?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Greater than 7 (basic)", hints = listOf("Basic")),
            ExamQuestion(id = "s2020_sa3", question = "Name the process by which plants lose water.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Transpiration", hints = listOf("Water loss")),
            ExamQuestion(id = "s2020_sa4", question = "What is tinning?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Coating with tin", hints = listOf("Tin coating")),
            ExamQuestion(id = "s2020_sa5", question = "Which gas is produced during anaerobic respiration?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "CO₂ and alcohol", hints = listOf("Fermentation")),
            ExamQuestion(id = "s2020_sa6", question = "What is the function of large intestine?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Water absorption, waste storage", hints = listOf("Absorption")),
            ExamQuestion(id = "s2020_sa7", question = "Name the pigment that captures light energy.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Chlorophyll", hints = listOf("Light capture")),
            ExamQuestion(id = "s2020_sa8", question = "What is power of lens?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "1/f (in meters)", hints = listOf("Reciprocal of focal length")),
            ExamQuestion(id = "s2020_sa9", question = "Which type of reaction is: Fe + CuSO₄ → FeSO₄ + Cu?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Displacement", hints = listOf("Single displacement")),
            ExamQuestion(id = "s2020_sa10", question = "What is the unit of energy?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Joule (J)", hints = listOf("SI unit")),
            ExamQuestion(id = "s2020_sb1", question = "What happens when potassium reacts with water? Write equation.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "2K + 2H₂O → 2KOH + H₂. Explosive reaction.", hints = listOf("Alkali metal")),
            ExamQuestion(id = "s2020_sb2", question = "Explain process of circulation in human beings.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Heart pumps blood. Arteries carry away, veins carry back. Double circulation.", hints = listOf("Blood flow")),
            ExamQuestion(id = "s2020_sb3", question = "Why do we add vinegar to pickles?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "Acidic environment prevents bacteria", hints = listOf("Preservation")),
            ExamQuestion(id = "s2020_sb4", question = "State power of lens formula.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "P = 1/f (f in meters)", hints = listOf("Power formula")),
            ExamQuestion(id = "s2020_sb5", question = "Differentiate between light and dark reactions.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Light: needs light, O₂ released. Dark: no light, CO₂ fixed.", hints = listOf("Photosynthesis stages")),
            ExamQuestion(id = "s2020_sb6", question = "What is meant by displacement reaction? Give example.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "More reactive replaces less reactive. Example: Zn + CuSO₄ → ZnSO₄ + Cu", hints = listOf("Reactivity")),
            ExamQuestion(id = "s2020_sb7", question = "Explain structure of human heart.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Four chambers, valves, blood flow. Double circulation.", hints = listOf("Structure")),
            ExamQuestion(id = "s2020_sb8", question = "Why is platinum used in jewelry?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "Non-reactive, lustrous, rare", hints = listOf("Properties")),
            ExamQuestion(id = "s2020_sb9", question = "What is cataract? How is it treated?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "Clouding of lens. Treated with surgery.", hints = listOf("Eye condition")),
            ExamQuestion(id = "s2020_sb10", question = "Explain role of stomata in plants.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Gas exchange, transpiration, photosynthesis", hints = listOf("Functions")),
            ExamQuestion(id = "s2020_sc1", question = "Explain process of photosynthesis with light and dark reactions.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Light reaction: photolysis, ATP, NADPH. Dark reaction: Calvin cycle, glucose formation.", hints = listOf("Both reactions")),
            ExamQuestion(id = "s2020_sc2", question = "Explain displacement and double displacement reactions with examples.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Single: one element replaces. Double: two compounds exchange. Examples.", hints = listOf("Both types")),
            ExamQuestion(id = "s2020_sc3", question = "Explain human circulatory system with blood flow.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Heart, blood vessels, blood. Pulmonary and systemic circulation.", hints = listOf("Complete system")),
            ExamQuestion(id = "s2020_sc4", question = "Explain structure and working of human nervous system with reflex action.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "CNS, PNS. Reflex arc. Voluntary and involuntary actions.", hints = listOf("System and reflex")),
            ExamQuestion(id = "s2020_sc5", question = "Explain magnetic field and field lines.", marks = 4, type = QuestionType.LONG_ANSWER, answer = "Field around magnet. Field lines show direction. Properties.", hints = listOf("Magnetic field")),
            ExamQuestion(id = "s2020_sc6", question = "Explain working of transformer with principle.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Electromagnetic induction. Step-up and step-down. Power transmission.", hints = listOf("Principle and working")),
            ExamQuestion(id = "s2020_sd1", question = "Case: A student observes that iron rusts faster in coastal area. (i) Why? (ii) What factors? (iii) How to prevent?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) More moisture and salt (ii) Water, O₂, salt (iii) Coating, alloying", hints = listOf("Rusting factors")),
            ExamQuestion(id = "s2020_sd2", question = "Case: A plant kept in red light produces less starch. (i) Why? (ii) What is needed? (iii) Which light is best?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Less efficient (ii) White/blue light (iii) Blue light", hints = listOf("Light quality")),
            ExamQuestion(id = "s2020_sd3", question = "Case: When copper is placed in AgNO₃, silver deposits. (i) Why? (ii) Write equation. (iii) What type?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Cu more reactive (ii) Cu + 2AgNO₃ → Cu(NO₃)₂ + 2Ag (iii) Displacement", hints = listOf("Reactivity")),
            ExamQuestion(id = "s2020_sd4", question = "Case: A motor rated 750W, 150V. (i) Find current. (ii) Find resistance. (iii) Find work done in 5 minutes.", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) 5A (ii) 30Ω (iii) 225 kJ", hints = listOf("Power and work"))
        )
    }
    
    // ========== SCIENCE 2019 ==========
    private fun getScience2019Questions(): List<ExamQuestion> {
        return listOf(
            ExamQuestion(id = "s2019_sa1", question = "Balance: Al + Fe₂O₃ → Al₂O₃ + Fe", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "2Al + Fe₂O₃ → Al₂O₃ + 2Fe", hints = listOf("Balance atoms")),
            ExamQuestion(id = "s2019_sa2", question = "What is pH of milk?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Around 6.5-6.7 (slightly acidic)", hints = listOf("Slightly acidic")),
            ExamQuestion(id = "s2019_sa3", question = "Name the process by which plants make glucose.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Photosynthesis", hints = listOf("Glucose making")),
            ExamQuestion(id = "s2019_sa4", question = "What is chroming?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Coating with chromium", hints = listOf("Chromium coating")),
            ExamQuestion(id = "s2019_sa5", question = "Which gas is released during breathing?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "CO₂", hints = listOf("Carbon dioxide")),
            ExamQuestion(id = "s2019_sa6", question = "What is the function of stomach?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Digestion with acid and enzymes", hints = listOf("Digestion")),
            ExamQuestion(id = "s2019_sa7", question = "Name the organelle where respiration occurs.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Mitochondria", hints = listOf("Cell organelle")),
            ExamQuestion(id = "s2019_sa8", question = "What is focal length of concave mirror?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Negative (virtual focus)", hints = listOf("Sign convention")),
            ExamQuestion(id = "s2019_sa9", question = "Which type of reaction is: CaCO₃ → CaO + CO₂?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Decomposition", hints = listOf("Breaks down")),
            ExamQuestion(id = "s2019_sa10", question = "What is the unit of frequency?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Hertz (Hz)", hints = listOf("SI unit")),
            ExamQuestion(id = "s2019_sb1", question = "What happens when zinc reacts with dilute HCl? Write equation.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Zn + 2HCl → ZnCl₂ + H₂. Bubbles of H₂.", hints = listOf("Acid reaction")),
            ExamQuestion(id = "s2019_sb2", question = "Explain process of reproduction in plants.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Sexual: flowers, pollination, fertilization. Asexual: vegetative propagation.", hints = listOf("Types")),
            ExamQuestion(id = "s2019_sb3", question = "Why do we add preservatives to food?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "Prevents spoilage and microbial growth", hints = listOf("Preservation")),
            ExamQuestion(id = "s2019_sb4", question = "State laws of refraction.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "1. sin i/sin r = constant. 2. Incident, refracted, normal in same plane.", hints = listOf("Snell's law")),
            ExamQuestion(id = "s2019_sb5", question = "Differentiate between plant and animal cells.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Plant: cell wall, chloroplasts, large vacuole. Animal: no cell wall, centrioles.", hints = listOf("Structure differences")),
            ExamQuestion(id = "s2019_sb6", question = "What is meant by neutralization? Give example.", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "Acid + Base → Salt + Water. Example: HCl + NaOH → NaCl + H₂O", hints = listOf("Acid-base reaction")),
            ExamQuestion(id = "s2019_sb7", question = "Explain structure of human brain.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Cerebrum, cerebellum, medulla. Functions of each.", hints = listOf("Parts and functions")),
            ExamQuestion(id = "s2019_sb8", question = "Why is copper used in electrical circuits?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "Good conductor, ductile, abundant", hints = listOf("Properties")),
            ExamQuestion(id = "s2019_sb9", question = "What is color blindness? How is it caused?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "Cannot distinguish colors. Genetic defect in cone cells.", hints = listOf("Eye defect")),
            ExamQuestion(id = "s2019_sb10", question = "Explain role of roots in plants.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Anchorage, water/mineral absorption, storage", hints = listOf("Functions")),
            ExamQuestion(id = "s2019_sc1", question = "Explain process of nutrition in detail with types.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Autotrophic and heterotrophic. Photosynthesis. Digestion in animals.", hints = listOf("Types and process")),
            ExamQuestion(id = "s2019_sc2", question = "Explain acid-base reactions with examples and uses.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Neutralization. Examples. Uses: antacids, soil treatment, industries", hints = listOf("Reactions and uses")),
            ExamQuestion(id = "s2019_sc3", question = "Explain human reproductive system with process.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Male and female systems. Gamete formation, fertilization, development.", hints = listOf("System and process")),
            ExamQuestion(id = "s2019_sc4", question = "Explain structure and working of human endocrine system.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Glands, hormones, functions. Feedback mechanism.", hints = listOf("System and hormones")),
            ExamQuestion(id = "s2019_sc5", question = "Explain electric circuit and its components.", marks = 4, type = QuestionType.LONG_ANSWER, answer = "Battery, switch, resistor, wires. Series and parallel.", hints = listOf("Components")),
            ExamQuestion(id = "s2019_sc6", question = "Explain working of electric bell with principle.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Electromagnet. Make and break circuit. Continuous ringing.", hints = listOf("Principle and working")),
            ExamQuestion(id = "s2019_sd1", question = "Case: A student observes that iron rusts in presence of water and air. (i) Why? (ii) Write equation. (iii) How to prevent?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Needs O₂ and H₂O (ii) 4Fe + 3O₂ + 6H₂O → 4Fe(OH)₃ (iii) Coating, alloying", hints = listOf("Rusting")),
            ExamQuestion(id = "s2019_sd2", question = "Case: A plant kept in nitrogen-rich soil grows better. (i) Why? (ii) What is needed? (iii) What is the role?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) N is nutrient (ii) Nitrogen (iii) Protein synthesis", hints = listOf("Nutrients")),
            ExamQuestion(id = "s2019_sd3", question = "Case: When magnesium is placed in ZnSO₄, reaction occurs. (i) Why? (ii) Write equation. (iii) What type?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Mg more reactive (ii) Mg + ZnSO₄ → MgSO₄ + Zn (iii) Displacement", hints = listOf("Reactivity")),
            ExamQuestion(id = "s2019_sd4", question = "Case: A device rated 200W, 100V. (i) Find current. (ii) Find resistance. (iii) Find energy in 30 minutes.", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) 2A (ii) 50Ω (iii) 0.1 kWh", hints = listOf("Power and energy"))
        )
    }
    
    // ========== SOCIAL SCIENCE 2025-2019 ==========
    private fun getSocialScience2025Questions(): List<ExamQuestion> {
        return listOf(
            // Section A (10 questions, 1 mark each)
            ExamQuestion(id = "ss2025_sa1", question = "In which year did French Revolution occur?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "1789", hints = listOf("French Revolution")),
            ExamQuestion(id = "ss2025_sa2", question = "Who led the unification of Germany?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Bismarck", hints = listOf("German leader")),
            ExamQuestion(id = "ss2025_sa3", question = "What is nationalism?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Feeling of unity towards nation", hints = listOf("National identity")),
            ExamQuestion(id = "ss2025_sa4", question = "In which year did Salt March take place?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "1930", hints = listOf("Gandhi's march")),
            ExamQuestion(id = "ss2025_sa5", question = "What is federalism?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Division of power between levels", hints = listOf("Power sharing")),
            ExamQuestion(id = "ss2025_sa6", question = "What is democracy?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Government by the people", hints = listOf("People's rule")),
            ExamQuestion(id = "ss2025_sa7", question = "Name one renewable resource.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Solar energy", hints = listOf("Natural resource")),
            ExamQuestion(id = "ss2025_sa8", question = "What is the main crop of Punjab?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Wheat", hints = listOf("Food crop")),
            ExamQuestion(id = "ss2025_sa9", question = "Which is the largest state in India by area?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Rajasthan", hints = listOf("Indian state")),
            ExamQuestion(id = "ss2025_sa10", question = "What is HDI?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Human Development Index", hints = listOf("Development indicator")),
            
            // Section B (10 questions, 2-3 marks each)
            ExamQuestion(id = "ss2025_sb1", question = "Explain role of French Revolution in rise of nationalism.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Introduced liberty, equality, fraternity. Spread through Napoleon.", hints = listOf("Ideas and spread")),
            ExamQuestion(id = "ss2025_sb2", question = "What was impact of First World War on India?", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Economic hardships, increased taxes, forced recruitment, led to movements", hints = listOf("Economic and political impact")),
            ExamQuestion(id = "ss2025_sb3", question = "Explain significance of Salt March.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Symbolic act, broke salt law, inspired mass civil disobedience", hints = listOf("Symbolism and impact")),
            ExamQuestion(id = "ss2025_sb4", question = "What is federalism? Explain its features.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Power division. Features: multiple levels, independent jurisdiction", hints = listOf("Definition and features")),
            ExamQuestion(id = "ss2025_sb5", question = "Explain types of resources with examples.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Natural, human-made, human. Renewable and non-renewable", hints = listOf("Classification")),
            ExamQuestion(id = "ss2025_sb6", question = "What is power sharing? Explain forms.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Essential for democracy. Forms: horizontal, vertical, community, political", hints = listOf("Forms of sharing")),
            ExamQuestion(id = "ss2025_sb7", question = "Explain outcomes of democracy.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Accountable government, accommodation of diversity, dignity, freedom", hints = listOf("Benefits")),
            ExamQuestion(id = "ss2025_sb8", question = "What are major crops of India?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "Food: rice, wheat. Cash: sugarcane, cotton. Plantation: tea, coffee", hints = listOf("Crop types")),
            ExamQuestion(id = "ss2025_sb9", question = "Explain importance of manufacturing industries.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Employment, value addition, trade, economic growth", hints = listOf("Economic importance")),
            ExamQuestion(id = "ss2025_sb10", question = "What is sustainable development?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "Development meeting present needs without compromising future", hints = listOf("Future-oriented")),
            
            // Section C (6 questions, 4-5 marks each)
            ExamQuestion(id = "ss2025_sc1", question = "Describe process of unification of Germany.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Bismarck's leadership, three wars (Danish, Austro-Prussian, Franco-Prussian), 1871 unification", hints = listOf("Process and wars")),
            ExamQuestion(id = "ss2025_sc2", question = "Explain Non-Cooperation Movement and its impact.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Gandhi's leadership, boycott, Khilafat, mass participation, withdrawal", hints = listOf("Features and impact")),
            ExamQuestion(id = "ss2025_sc3", question = "Explain federal structure of India with three lists.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Union List, State List, Concurrent List with examples and residuary powers", hints = listOf("Three lists")),
            ExamQuestion(id = "ss2025_sc4", question = "Explain water resources and their management in India.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Sources, scarcity, multi-purpose projects, rainwater harvesting, conservation", hints = listOf("Resources and management")),
            ExamQuestion(id = "ss2025_sc5", question = "Explain political parties and their role in democracy.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Functions, necessity, types, challenges, reforms needed", hints = listOf("Role and importance")),
            ExamQuestion(id = "ss2025_sc6", question = "Explain development and its goals.", marks = 4, type = QuestionType.LONG_ANSWER, answer = "Process of improvement. Goals: income, education, health, freedom, security", hints = listOf("Definition and goals")),
            
            // Section D (4 questions, 4-5 marks each)
            ExamQuestion(id = "ss2025_sd1", question = "Case: Belgium has 59% Dutch, 40% French, 1% German. (i) What problem? (ii) How solved? (iii) Lessons?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Ethnic conflict (ii) Power sharing, equal representation (iii) Accommodation works", hints = listOf("Power sharing example")),
            ExamQuestion(id = "ss2025_sd2", question = "Case: A state has 60% literacy, 5% unemployment, high HDI. (i) Is it developed? (ii) What indicators? (iii) How to improve?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Partially (ii) Literacy, employment, HDI (iii) Education, jobs, health", hints = listOf("Development indicators")),
            ExamQuestion(id = "ss2025_sd3", question = "Case: A region has good transport, industries, resources. (i) Why suitable for industry? (ii) What factors? (iii) Benefits?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Good location (ii) Transport, resources, market (iii) Employment, growth", hints = listOf("Industrial location")),
            ExamQuestion(id = "ss2025_sd4", question = "Case: In election, Party A gets 45%, Party B gets 35%, others 20%. (i) Who forms government? (ii) What type? (iii) Challenges?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Coalition (ii) Multi-party (iii) Consensus, stability", hints = listOf("Coalition politics"))
        )
    }
    
    // ========== SOCIAL SCIENCE 2024 ==========
    private fun getSocialScience2024Questions(): List<ExamQuestion> {
        return listOf(
            ExamQuestion(id = "ss2024_sa1", question = "In which year did Italian unification complete?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "1871", hints = listOf("Italian unification")),
            ExamQuestion(id = "ss2024_sa2", question = "Who was known as 'Iron Man of India'?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Sardar Patel", hints = listOf("Indian leader")),
            ExamQuestion(id = "ss2024_sa3", question = "What is power sharing?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Distribution of power among different groups", hints = listOf("Power distribution")),
            ExamQuestion(id = "ss2024_sa4", question = "In which year did Jallianwala Bagh massacre occur?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "1919", hints = listOf("Massacre")),
            ExamQuestion(id = "ss2024_sa5", question = "What is unitary system?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Central government has all powers", hints = listOf("Centralized power")),
            ExamQuestion(id = "ss2024_sa6", question = "What is representative democracy?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "People elect representatives", hints = listOf("Elected representatives")),
            ExamQuestion(id = "ss2024_sa7", question = "Name one non-renewable resource.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Coal", hints = listOf("Fossil fuel")),
            ExamQuestion(id = "ss2024_sa8", question = "What is the main crop of West Bengal?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Rice", hints = listOf("Food crop")),
            ExamQuestion(id = "ss2024_sa9", question = "Which is the smallest state in India by area?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Goa", hints = listOf("Indian state")),
            ExamQuestion(id = "ss2024_sa10", question = "What is PCI?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Per Capita Income", hints = listOf("Income indicator")),
            ExamQuestion(id = "ss2024_sb1", question = "Explain role of Giuseppe Garibaldi in Italian unification.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Led Red Shirts, captured Sicily and Naples, contributed to unification", hints = listOf("Italian leader")),
            ExamQuestion(id = "ss2024_sb2", question = "What was impact of Rowlatt Act on India?", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Allowed detention without trial, led to protests, Jallianwala Bagh", hints = listOf("British act")),
            ExamQuestion(id = "ss2024_sb3", question = "Explain significance of Dandi March.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Broke salt law, symbol of defiance, mass participation", hints = listOf("Gandhi's march")),
            ExamQuestion(id = "ss2024_sb4", question = "What is federalism? Explain its importance.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Power division. Importance: accommodates diversity, prevents concentration", hints = listOf("Definition and importance")),
            ExamQuestion(id = "ss2024_sb5", question = "Explain classification of resources with examples.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Natural, human-made, human. Renewable and non-renewable. Examples.", hints = listOf("Classification")),
            ExamQuestion(id = "ss2024_sb6", question = "What is horizontal power sharing? Explain with example.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Between organs of government. Example: legislature, executive, judiciary", hints = listOf("Horizontal sharing")),
            ExamQuestion(id = "ss2024_sb7", question = "Explain challenges to democracy.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Corruption, poverty, inequality, communalism, casteism", hints = listOf("Challenges")),
            ExamQuestion(id = "ss2024_sb8", question = "What are major industries of India?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "Textile, iron and steel, IT, automobile, pharmaceuticals", hints = listOf("Industries")),
            ExamQuestion(id = "ss2024_sb9", question = "Explain importance of transport in economy.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Connects regions, facilitates trade, promotes development", hints = listOf("Economic importance")),
            ExamQuestion(id = "ss2024_sb10", question = "What is economic development?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "Increase in income and standard of living", hints = listOf("Development concept")),
            ExamQuestion(id = "ss2024_sc1", question = "Describe process of unification of Italy.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Role of Mazzini, Cavour, Garibaldi. Wars, 1871 unification.", hints = listOf("Process and leaders")),
            ExamQuestion(id = "ss2024_sc2", question = "Explain Civil Disobedience Movement and its impact.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Gandhi's leadership, Dandi March, mass participation, impact on British", hints = listOf("Movement and impact")),
            ExamQuestion(id = "ss2024_sc3", question = "Explain features of Indian federalism with examples.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Three lists, residuary powers, special status, examples", hints = listOf("Features")),
            ExamQuestion(id = "ss2024_sc4", question = "Explain mineral resources and their distribution in India.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Types, distribution, importance, conservation", hints = listOf("Resources and distribution")),
            ExamQuestion(id = "ss2024_sc5", question = "Explain role of political parties in democracy.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Contest elections, form government, make policies, opposition", hints = listOf("Role")),
            ExamQuestion(id = "ss2024_sc6", question = "Explain concept of development with indicators.", marks = 4, type = QuestionType.LONG_ANSWER, answer = "Income, education, health, HDI, other indicators", hints = listOf("Concept and indicators")),
            ExamQuestion(id = "ss2024_sd1", question = "Case: Sri Lanka has 74% Sinhalese, 18% Tamils. (i) What problem? (ii) How to solve? (iii) Lessons?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Ethnic conflict (ii) Power sharing (iii) Accommodation needed", hints = listOf("Ethnic conflict")),
            ExamQuestion(id = "ss2024_sd2", question = "Case: A state has 70% literacy, 8% unemployment, medium HDI. (i) Is it developed? (ii) What indicators? (iii) How to improve?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Partially (ii) Literacy, employment, HDI (iii) Education, jobs", hints = listOf("Development")),
            ExamQuestion(id = "ss2024_sd3", question = "Case: A region has ports, railways, industries. (i) Why suitable? (ii) What factors? (iii) Benefits?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Good connectivity (ii) Transport, location (iii) Trade, growth", hints = listOf("Location factors")),
            ExamQuestion(id = "ss2024_sd4", question = "Case: In election, Party A gets 38%, Party B gets 32%, others 30%. (i) Who forms government? (ii) What type? (iii) Challenges?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Coalition (ii) Multi-party (iii) Stability, consensus", hints = listOf("Coalition"))
        )
    }
    
    // ========== SOCIAL SCIENCE 2023 ==========
    private fun getSocialScience2023Questions(): List<ExamQuestion> {
        return listOf(
            ExamQuestion(id = "ss2023_sa1", question = "In which year did Congress of Vienna take place?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "1815", hints = listOf("Congress")),
            ExamQuestion(id = "ss2023_sa2", question = "Who led the Quit India Movement?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Mahatma Gandhi", hints = listOf("Indian leader")),
            ExamQuestion(id = "ss2023_sa3", question = "What is democracy?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Government of the people, by the people, for the people", hints = listOf("People's rule")),
            ExamQuestion(id = "ss2023_sa4", question = "In which year did Partition of Bengal occur?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "1905", hints = listOf("Partition")),
            ExamQuestion(id = "ss2023_sa5", question = "What is confederation?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Loose union of states", hints = listOf("Union type")),
            ExamQuestion(id = "ss2023_sa6", question = "What is direct democracy?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "People directly participate", hints = listOf("Direct participation")),
            ExamQuestion(id = "ss2023_sa7", question = "Name one metallic mineral.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Iron ore", hints = listOf("Metal")),
            ExamQuestion(id = "ss2023_sa8", question = "What is the main crop of Karnataka?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Coffee", hints = listOf("Plantation crop")),
            ExamQuestion(id = "ss2023_sa9", question = "Which is the most populous state in India?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Uttar Pradesh", hints = listOf("Indian state")),
            ExamQuestion(id = "ss2023_sa10", question = "What is GDP?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Gross Domestic Product", hints = listOf("Economic indicator")),
            ExamQuestion(id = "ss2023_sb1", question = "Explain role of Count Cavour in Italian unification.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Prime Minister of Sardinia, diplomatic skills, wars, unification", hints = listOf("Italian leader")),
            ExamQuestion(id = "ss2023_sb2", question = "What was impact of Non-Cooperation Movement?", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Mass participation, boycott, withdrawal after Chauri Chaura", hints = listOf("Movement impact")),
            ExamQuestion(id = "ss2023_sb3", question = "Explain significance of Khilafat Movement.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Muslim support, united Hindus and Muslims, part of Non-Cooperation", hints = listOf("Movement")),
            ExamQuestion(id = "ss2023_sb4", question = "What is vertical power sharing? Explain with example.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Between levels of government. Example: Union, State, Local", hints = listOf("Vertical sharing")),
            ExamQuestion(id = "ss2023_sb5", question = "Explain soil types of India with distribution.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Alluvial, black, red, laterite, arid. Distribution across regions.", hints = listOf("Soil types")),
            ExamQuestion(id = "ss2023_sb6", question = "What is community power sharing? Explain with example.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Between social groups. Example: Belgium, linguistic groups", hints = listOf("Community sharing")),
            ExamQuestion(id = "ss2023_sb7", question = "Explain merits of democracy.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Accountability, equality, freedom, dignity, conflict resolution", hints = listOf("Benefits")),
            ExamQuestion(id = "ss2023_sb8", question = "What are major ports of India?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "Mumbai, Chennai, Kolkata, Kochi, Visakhapatnam", hints = listOf("Ports")),
            ExamQuestion(id = "ss2023_sb9", question = "Explain importance of communication in development.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Information exchange, connectivity, economic growth", hints = listOf("Importance")),
            ExamQuestion(id = "ss2023_sb10", question = "What is social development?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "Improvement in education, health, social conditions", hints = listOf("Development type")),
            ExamQuestion(id = "ss2023_sc1", question = "Describe rise of nationalism in Europe with factors.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "French Revolution, culture, language, art, music, literature", hints = listOf("Factors")),
            ExamQuestion(id = "ss2023_sc2", question = "Explain Quit India Movement and its significance.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Gandhi's call, mass participation, British response, significance", hints = listOf("Movement and significance")),
            ExamQuestion(id = "ss2023_sc3", question = "Explain working of Indian federalism with challenges.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Three lists, center-state relations, challenges, solutions", hints = listOf("Working and challenges")),
            ExamQuestion(id = "ss2023_sc4", question = "Explain energy resources and their conservation in India.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Conventional and non-conventional. Conservation methods.", hints = listOf("Resources and conservation")),
            ExamQuestion(id = "ss2023_sc5", question = "Explain challenges faced by political parties.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Lack of internal democracy, money, muscle power, dynastic succession", hints = listOf("Challenges")),
            ExamQuestion(id = "ss2023_sc6", question = "Explain different aspects of development.", marks = 4, type = QuestionType.LONG_ANSWER, answer = "Economic, social, political, environmental aspects", hints = listOf("Aspects")),
            ExamQuestion(id = "ss2023_sd1", question = "Case: A country has 60% majority, 40% minority. (i) What problem? (ii) How to solve? (iii) What is needed?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Majority domination (ii) Power sharing (iii) Accommodation", hints = listOf("Majority-minority")),
            ExamQuestion(id = "ss2023_sd2", question = "Case: A state has 50% literacy, 12% unemployment, low HDI. (i) Is it developed? (ii) What indicators? (iii) How to improve?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Underdeveloped (ii) Literacy, employment, HDI (iii) Education, jobs", hints = listOf("Development")),
            ExamQuestion(id = "ss2023_sd3", question = "Case: A region has coal, iron, water, labor. (i) Why suitable for industry? (ii) What factors? (iii) Benefits?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Resources available (ii) Raw materials, labor (iii) Employment, growth", hints = listOf("Industrial factors")),
            ExamQuestion(id = "ss2023_sd4", question = "Case: In election, Party A gets 42%, Party B gets 28%, others 30%. (i) Who forms government? (ii) What type? (iii) Challenges?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Coalition (ii) Multi-party (iii) Consensus, stability", hints = listOf("Coalition"))
        )
    }
    
    // ========== SOCIAL SCIENCE 2022 ==========
    private fun getSocialScience2022Questions(): List<ExamQuestion> {
        return listOf(
            ExamQuestion(id = "ss2022_sa1", question = "In which year did Battle of Waterloo occur?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "1815", hints = listOf("Battle")),
            ExamQuestion(id = "ss2022_sa2", question = "Who was the first Prime Minister of India?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Jawaharlal Nehru", hints = listOf("Indian leader")),
            ExamQuestion(id = "ss2022_sa3", question = "What is authoritarianism?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Rule by single person or group", hints = listOf("Authoritarian rule")),
            ExamQuestion(id = "ss2022_sa4", question = "In which year did Swadeshi Movement start?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "1905", hints = listOf("Movement")),
            ExamQuestion(id = "ss2022_sa5", question = "What is coming together federation?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Independent states unite", hints = listOf("Federation type")),
            ExamQuestion(id = "ss2022_sa6", question = "What is constitutional monarchy?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Monarch with limited powers", hints = listOf("Monarchy type")),
            ExamQuestion(id = "ss2022_sa7", question = "Name one non-metallic mineral.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Limestone", hints = listOf("Non-metal")),
            ExamQuestion(id = "ss2022_sa8", question = "What is the main crop of Assam?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Tea", hints = listOf("Plantation crop")),
            ExamQuestion(id = "ss2022_sa9", question = "Which is the least populous state in India?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Sikkim", hints = listOf("Indian state")),
            ExamQuestion(id = "ss2022_sa10", question = "What is GNP?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Gross National Product", hints = listOf("Economic indicator")),
            ExamQuestion(id = "ss2022_sb1", question = "Explain role of Napoleon in spread of nationalism.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Introduced reforms, spread ideas, created nationalist feelings", hints = listOf("Napoleon's role")),
            ExamQuestion(id = "ss2022_sb2", question = "What was impact of Partition of Bengal?", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Led to Swadeshi Movement, united people, anti-British feelings", hints = listOf("Partition impact")),
            ExamQuestion(id = "ss2022_sb3", question = "Explain significance of Champaran Satyagraha.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "First satyagraha, indigo farmers, success, Gandhi's method", hints = listOf("Satyagraha")),
            ExamQuestion(id = "ss2022_sb4", question = "What is holding together federation? Explain with example.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Large country divides power. Example: India, Spain", hints = listOf("Federation type")),
            ExamQuestion(id = "ss2022_sb5", question = "Explain water resources of India with problems.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Rivers, groundwater, lakes. Problems: scarcity, pollution, distribution", hints = listOf("Resources and problems")),
            ExamQuestion(id = "ss2022_sb6", question = "What is political power sharing? Explain with example.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Between political parties. Example: coalition governments", hints = listOf("Political sharing")),
            ExamQuestion(id = "ss2022_sb7", question = "Explain demerits of democracy.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Slow decision-making, corruption, instability, majority rule", hints = listOf("Disadvantages")),
            ExamQuestion(id = "ss2022_sb8", question = "What are major airports of India?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "Delhi, Mumbai, Bangalore, Chennai, Kolkata", hints = listOf("Airports")),
            ExamQuestion(id = "ss2022_sb9", question = "Explain importance of railways in India.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Transportation, connectivity, economic development", hints = listOf("Importance")),
            ExamQuestion(id = "ss2022_sb10", question = "What is sustainable development?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "Development without compromising future generations", hints = listOf("Development concept")),
            ExamQuestion(id = "ss2022_sc1", question = "Describe unification of Germany with process.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Bismarck's role, three wars, 1871 unification, process", hints = listOf("Process")),
            ExamQuestion(id = "ss2022_sc2", question = "Explain Swadeshi Movement and its impact.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Boycott of British goods, promotion of Indian goods, impact", hints = listOf("Movement and impact")),
            ExamQuestion(id = "ss2022_sc3", question = "Explain center-state relations in India.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Three lists, financial relations, administrative relations, disputes", hints = listOf("Relations")),
            ExamQuestion(id = "ss2022_sc4", question = "Explain forest resources and their conservation.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Types, distribution, importance, conservation methods", hints = listOf("Resources and conservation")),
            ExamQuestion(id = "ss2022_sc5", question = "Explain need for political parties in democracy.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Contest elections, form government, make policies, represent people", hints = listOf("Need")),
            ExamQuestion(id = "ss2022_sc6", question = "Explain public facilities and their importance.", marks = 4, type = QuestionType.LONG_ANSWER, answer = "Education, health, water, sanitation. Importance for development.", hints = listOf("Facilities")),
            ExamQuestion(id = "ss2022_sd1", question = "Case: A country has multiple ethnic groups. (i) What problem? (ii) How to solve? (iii) What is needed?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Ethnic conflict (ii) Power sharing (iii) Accommodation", hints = listOf("Ethnic diversity")),
            ExamQuestion(id = "ss2022_sd2", question = "Case: A state has 65% literacy, 6% unemployment, medium HDI. (i) Is it developed? (ii) What indicators? (iii) How to improve?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Moderately (ii) Literacy, employment, HDI (iii) Education, jobs", hints = listOf("Development")),
            ExamQuestion(id = "ss2022_sd3", question = "Case: A region has market, raw materials, skilled labor. (i) Why suitable? (ii) What factors? (iii) Benefits?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Good location (ii) Market, resources, labor (iii) Growth, employment", hints = listOf("Location factors")),
            ExamQuestion(id = "ss2022_sd4", question = "Case: In election, Party A gets 35%, Party B gets 30%, others 35%. (i) Who forms government? (ii) What type? (iii) Challenges?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Coalition (ii) Multi-party (iii) Consensus, stability", hints = listOf("Coalition"))
        )
    }
    
    // ========== SOCIAL SCIENCE 2021 ==========
    private fun getSocialScience2021Questions(): List<ExamQuestion> {
        return listOf(
            ExamQuestion(id = "ss2021_sa1", question = "In which year did Treaty of Versailles signed?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "1919", hints = listOf("Treaty")),
            ExamQuestion(id = "ss2021_sa2", question = "Who was known as 'Father of Nation'?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Mahatma Gandhi", hints = listOf("Indian leader")),
            ExamQuestion(id = "ss2021_sa3", question = "What is dictatorship?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Rule by single person", hints = listOf("Authoritarian")),
            ExamQuestion(id = "ss2021_sa4", question = "In which year did Simon Commission come to India?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "1928", hints = listOf("Commission")),
            ExamQuestion(id = "ss2021_sa5", question = "What is residuary power?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Power with central government", hints = listOf("Central power")),
            ExamQuestion(id = "ss2021_sa6", question = "What is parliamentary democracy?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Executive responsible to legislature", hints = listOf("Parliamentary system")),
            ExamQuestion(id = "ss2021_sa7", question = "Name one energy resource.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Coal", hints = listOf("Energy")),
            ExamQuestion(id = "ss2021_sa8", question = "What is the main crop of Maharashtra?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Cotton", hints = listOf("Cash crop")),
            ExamQuestion(id = "ss2021_sa9", question = "Which is the most densely populated state?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Bihar", hints = listOf("Indian state")),
            ExamQuestion(id = "ss2021_sa10", question = "What is NNP?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Net National Product", hints = listOf("Economic indicator")),
            ExamQuestion(id = "ss2021_sb1", question = "Explain role of culture in rise of nationalism.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Language, art, music, literature created national identity", hints = listOf("Cultural factors")),
            ExamQuestion(id = "ss2021_sb2", question = "What was impact of Simon Commission?", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Boycott, protests, demand for complete independence", hints = listOf("Commission impact")),
            ExamQuestion(id = "ss2021_sb3", question = "Explain significance of Kheda Satyagraha.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Peasants' movement, tax relief, success of satyagraha", hints = listOf("Satyagraha")),
            ExamQuestion(id = "ss2021_sb4", question = "What is concurrent list? Give examples.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Both center and state can make laws. Examples: education, forest", hints = listOf("Concurrent list")),
            ExamQuestion(id = "ss2021_sb5", question = "Explain agricultural resources of India.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Land, water, seeds, fertilizers, technology. Types of crops.", hints = listOf("Agricultural resources")),
            ExamQuestion(id = "ss2021_sb6", question = "What is social diversity? Explain with example.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Different social groups. Example: caste, religion, language", hints = listOf("Diversity")),
            ExamQuestion(id = "ss2021_sb7", question = "Explain conditions for democracy.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Free and fair elections, rule of law, rights, participation", hints = listOf("Conditions")),
            ExamQuestion(id = "ss2021_sb8", question = "What are major highways of India?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "NH-1, NH-2, NH-7, NH-8, Golden Quadrilateral", hints = listOf("Highways")),
            ExamQuestion(id = "ss2021_sb9", question = "Explain importance of waterways in India.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Cheap transport, connectivity, trade", hints = listOf("Importance")),
            ExamQuestion(id = "ss2021_sb10", question = "What is human development?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "Development focusing on human capabilities", hints = listOf("Development concept")),
            ExamQuestion(id = "ss2021_sc1", question = "Describe rise of nationalism in India with factors.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "British rule, economic exploitation, cultural revival, leaders", hints = listOf("Factors")),
            ExamQuestion(id = "ss2021_sc2", question = "Explain Round Table Conferences and their outcomes.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Three conferences, participants, discussions, outcomes", hints = listOf("Conferences")),
            ExamQuestion(id = "ss2021_sc3", question = "Explain linguistic states and their formation.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Language-based states, formation, examples, importance", hints = listOf("Linguistic states")),
            ExamQuestion(id = "ss2021_sc4", question = "Explain land resources and their utilization.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Types of land, utilization, problems, conservation", hints = listOf("Land resources")),
            ExamQuestion(id = "ss2021_sc5", question = "Explain functions of political parties.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Contest elections, form government, make policies, represent, opposition", hints = listOf("Functions")),
            ExamQuestion(id = "ss2021_sc6", question = "Explain quality of life and its indicators.", marks = 4, type = QuestionType.LONG_ANSWER, answer = "Standard of living, health, education, environment, indicators", hints = listOf("Quality of life")),
            ExamQuestion(id = "ss2021_sd1", question = "Case: A country has religious diversity. (i) What problem? (ii) How to solve? (iii) What is needed?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Religious conflict (ii) Secularism, power sharing (iii) Tolerance", hints = listOf("Religious diversity")),
            ExamQuestion(id = "ss2021_sd2", question = "Case: A state has 55% literacy, 10% unemployment, low HDI. (i) Is it developed? (ii) What indicators? (iii) How to improve?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Underdeveloped (ii) Literacy, employment, HDI (iii) Education, jobs", hints = listOf("Development")),
            ExamQuestion(id = "ss2021_sd3", question = "Case: A region has power, transport, market access. (i) Why suitable? (ii) What factors? (iii) Benefits?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Good infrastructure (ii) Power, transport, market (iii) Growth, investment", hints = listOf("Infrastructure")),
            ExamQuestion(id = "ss2021_sd4", question = "Case: In election, Party A gets 40%, Party B gets 35%, others 25%. (i) Who forms government? (ii) What type? (iii) Challenges?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Coalition (ii) Multi-party (iii) Consensus, stability", hints = listOf("Coalition"))
        )
    }
    
    // ========== SOCIAL SCIENCE 2020 ==========
    private fun getSocialScience2020Questions(): List<ExamQuestion> {
        return listOf(
            ExamQuestion(id = "ss2020_sa1", question = "In which year did Russian Revolution occur?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "1917", hints = listOf("Revolution")),
            ExamQuestion(id = "ss2020_sa2", question = "Who was known as 'Lion of Punjab'?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Lala Lajpat Rai", hints = listOf("Indian leader")),
            ExamQuestion(id = "ss2020_sa3", question = "What is monarchy?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Rule by king or queen", hints = listOf("Monarch")),
            ExamQuestion(id = "ss2020_sa4", question = "In which year did Poona Pact signed?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "1932", hints = listOf("Pact")),
            ExamQuestion(id = "ss2020_sa5", question = "What is union list?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "List of subjects with central government", hints = listOf("Central list")),
            ExamQuestion(id = "ss2020_sa6", question = "What is presidential democracy?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Executive independent of legislature", hints = listOf("Presidential system")),
            ExamQuestion(id = "ss2020_sa7", question = "Name one biotic resource.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Forest", hints = listOf("Living resource")),
            ExamQuestion(id = "ss2020_sa8", question = "What is the main crop of Tamil Nadu?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Rice", hints = listOf("Food crop")),
            ExamQuestion(id = "ss2020_sa9", question = "Which is the least densely populated state?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Arunachal Pradesh", hints = listOf("Indian state")),
            ExamQuestion(id = "ss2020_sa10", question = "What is GNI?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Gross National Income", hints = listOf("Economic indicator")),
            ExamQuestion(id = "ss2020_sb1", question = "Explain role of print culture in rise of nationalism.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Spread ideas, created awareness, united people", hints = listOf("Print culture")),
            ExamQuestion(id = "ss2020_sb2", question = "What was impact of Poona Pact?", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Reserved seats for depressed classes, unity, compromise", hints = listOf("Pact impact")),
            ExamQuestion(id = "ss2020_sb3", question = "Explain significance of Bardoli Satyagraha.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Peasants' movement, tax reduction, success", hints = listOf("Satyagraha")),
            ExamQuestion(id = "ss2020_sb4", question = "What is state list? Give examples.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "List of subjects with state government. Examples: police, agriculture", hints = listOf("State list")),
            ExamQuestion(id = "ss2020_sb5", question = "Explain mineral resources of India with distribution.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Types, distribution across states, importance", hints = listOf("Mineral resources")),
            ExamQuestion(id = "ss2020_sb6", question = "What is caste diversity? Explain with example.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Different castes. Example: upper, lower, scheduled castes", hints = listOf("Caste diversity")),
            ExamQuestion(id = "ss2020_sb7", question = "Explain features of democracy.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Elections, rule of law, rights, participation, accountability", hints = listOf("Features")),
            ExamQuestion(id = "ss2020_sb8", question = "What are major pipelines of India?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "HBJ pipeline, Salaya-Mathura, others", hints = listOf("Pipelines")),
            ExamQuestion(id = "ss2020_sb9", question = "Explain importance of airways in India.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Fast transport, connectivity, international trade", hints = listOf("Importance")),
            ExamQuestion(id = "ss2020_sb10", question = "What is inclusive development?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "Development including all sections", hints = listOf("Development concept")),
            ExamQuestion(id = "ss2020_sc1", question = "Describe impact of First World War on Europe.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Destruction, economic crisis, political changes, rise of new nations", hints = listOf("Impact")),
            ExamQuestion(id = "ss2020_sc2", question = "Explain Government of India Act 1935 and its provisions.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Provisions, federal structure, provincial autonomy, reactions", hints = listOf("Act and provisions")),
            ExamQuestion(id = "ss2020_sc3", question = "Explain special status states in India.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Jammu and Kashmir, special provisions, reasons, examples", hints = listOf("Special status")),
            ExamQuestion(id = "ss2020_sc4", question = "Explain multipurpose projects and their benefits.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Irrigation, power, flood control, examples, benefits", hints = listOf("Projects")),
            ExamQuestion(id = "ss2020_sc5", question = "Explain types of political parties.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "National, state, regional. Left, right, center. Examples.", hints = listOf("Types")),
            ExamQuestion(id = "ss2020_sc6", question = "Explain development goals and their importance.", marks = 4, type = QuestionType.LONG_ANSWER, answer = "Income, education, health, freedom, security, importance", hints = listOf("Goals")),
            ExamQuestion(id = "ss2020_sd1", question = "Case: A country has linguistic diversity. (i) What problem? (ii) How to solve? (iii) What is needed?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Linguistic conflict (ii) Recognition, power sharing (iii) Accommodation", hints = listOf("Linguistic diversity")),
            ExamQuestion(id = "ss2020_sd2", question = "Case: A state has 75% literacy, 4% unemployment, high HDI. (i) Is it developed? (ii) What indicators? (iii) How to maintain?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Developed (ii) Literacy, employment, HDI (iii) Continue policies", hints = listOf("Development")),
            ExamQuestion(id = "ss2020_sd3", question = "Case: A region has skilled labor, technology, capital. (i) Why suitable? (ii) What factors? (iii) Benefits?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Good resources (ii) Labor, technology, capital (iii) Innovation, growth", hints = listOf("Resources")),
            ExamQuestion(id = "ss2020_sd4", question = "Case: In election, Party A gets 48%, Party B gets 30%, others 22%. (i) Who forms government? (ii) What type? (iii) Challenges?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Single party (ii) Majority (iii) Accountability, opposition", hints = listOf("Majority government"))
        )
    }
    
    // ========== SOCIAL SCIENCE 2019 ==========
    private fun getSocialScience2019Questions(): List<ExamQuestion> {
        return listOf(
            ExamQuestion(id = "ss2019_sa1", question = "In which year did American Civil War end?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "1865", hints = listOf("War")),
            ExamQuestion(id = "ss2019_sa2", question = "Who was known as 'Netaji'?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Subhas Chandra Bose", hints = listOf("Indian leader")),
            ExamQuestion(id = "ss2019_sa3", question = "What is oligarchy?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Rule by few people", hints = listOf("Few rulers")),
            ExamQuestion(id = "ss2019_sa4", question = "In which year did Lahore Resolution passed?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "1940", hints = listOf("Resolution")),
            ExamQuestion(id = "ss2019_sa5", question = "What is special status?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Special powers to certain states", hints = listOf("Special powers")),
            ExamQuestion(id = "ss2019_sa6", question = "What is rule of law?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Everyone equal before law", hints = listOf("Legal equality")),
            ExamQuestion(id = "ss2019_sa7", question = "Name one abiotic resource.", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Minerals", hints = listOf("Non-living resource")),
            ExamQuestion(id = "ss2019_sa8", question = "What is the main crop of Gujarat?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Groundnut", hints = listOf("Oilseed")),
            ExamQuestion(id = "ss2019_sa9", question = "Which is the most urbanized state?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Goa", hints = listOf("Indian state")),
            ExamQuestion(id = "ss2019_sa10", question = "What is PPP?", marks = 1, type = QuestionType.VERY_SHORT_ANSWER, answer = "Purchasing Power Parity", hints = listOf("Economic indicator")),
            ExamQuestion(id = "ss2019_sb1", question = "Explain role of Mazzini in Italian unification.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Young Italy, revolutionary ideas, inspired people", hints = listOf("Italian leader")),
            ExamQuestion(id = "ss2019_sb2", question = "What was impact of Lahore Resolution?", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Demand for Pakistan, partition, Muslim League", hints = listOf("Resolution impact")),
            ExamQuestion(id = "ss2019_sb3", question = "Explain significance of Individual Satyagraha.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Personal protest, limited scope, preparation for bigger movement", hints = listOf("Satyagraha")),
            ExamQuestion(id = "ss2019_sb4", question = "What is coalition government? Explain with example.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Multiple parties form government. Example: UPA, NDA", hints = listOf("Coalition")),
            ExamQuestion(id = "ss2019_sb5", question = "Explain forest resources and their types.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Types: tropical, temperate, coniferous. Distribution, importance", hints = listOf("Forest types")),
            ExamQuestion(id = "ss2019_sb6", question = "What is gender diversity? Explain with example.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Different genders. Example: men, women, others", hints = listOf("Gender diversity")),
            ExamQuestion(id = "ss2019_sb7", question = "Explain importance of democracy.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Accountability, equality, freedom, dignity, development", hints = listOf("Importance")),
            ExamQuestion(id = "ss2019_sb8", question = "What are major seaports of India?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "Mumbai, Chennai, Kolkata, Visakhapatnam, Kochi", hints = listOf("Ports")),
            ExamQuestion(id = "ss2019_sb9", question = "Explain importance of pipelines in India.", marks = 3, type = QuestionType.SHORT_ANSWER, answer = "Transport oil, gas, cost-effective, efficient", hints = listOf("Importance")),
            ExamQuestion(id = "ss2019_sb10", question = "What is equitable development?", marks = 2, type = QuestionType.SHORT_ANSWER, answer = "Development with equality", hints = listOf("Development concept")),
            ExamQuestion(id = "ss2019_sc1", question = "Describe impact of French Revolution on Europe.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Ideas spread, inspired revolutions, end of monarchy, rise of nationalism", hints = listOf("Impact")),
            ExamQuestion(id = "ss2019_sc2", question = "Explain Cripps Mission and its failure.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Proposals, reactions, reasons for failure, consequences", hints = listOf("Mission")),
            ExamQuestion(id = "ss2019_sc3", question = "Explain local government in India.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Panchayati Raj, municipalities, functions, importance", hints = listOf("Local government")),
            ExamQuestion(id = "ss2019_sc4", question = "Explain conservation of resources and methods.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Need, methods: reduce, reuse, recycle, sustainable use", hints = listOf("Conservation")),
            ExamQuestion(id = "ss2019_sc5", question = "Explain reforms needed in political parties.", marks = 5, type = QuestionType.LONG_ANSWER, answer = "Internal democracy, transparency, funding, representation", hints = listOf("Reforms")),
            ExamQuestion(id = "ss2019_sc6", question = "Explain different views on development.", marks = 4, type = QuestionType.LONG_ANSWER, answer = "Income view, capability view, sustainable view, different perspectives", hints = listOf("Views")),
            ExamQuestion(id = "ss2019_sd1", question = "Case: A country has regional diversity. (i) What problem? (ii) How to solve? (iii) What is needed?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Regional conflict (ii) Federalism, power sharing (iii) Accommodation", hints = listOf("Regional diversity")),
            ExamQuestion(id = "ss2019_sd2", question = "Case: A state has 80% literacy, 3% unemployment, very high HDI. (i) Is it developed? (ii) What indicators? (iii) How to sustain?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Highly developed (ii) Literacy, employment, HDI (iii) Maintain policies", hints = listOf("Development")),
            ExamQuestion(id = "ss2019_sd3", question = "Case: A region has research institutions, universities, IT parks. (i) Why suitable? (ii) What factors? (iii) Benefits?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Knowledge hub (ii) Education, technology (iii) Innovation, growth", hints = listOf("Knowledge economy")),
            ExamQuestion(id = "ss2019_sd4", question = "Case: In election, Party A gets 52%, Party B gets 28%, others 20%. (i) Who forms government? (ii) What type? (iii) Challenges?", marks = 5, type = QuestionType.CASE_BASED, answer = "(i) Single party (ii) Majority (iii) Accountability, opposition", hints = listOf("Majority government"))
        )
    }
}
