package com.example.revisionadda10.data.repository

import com.example.revisionadda10.data.model.ExamQuestion
import com.example.revisionadda10.data.model.QuestionType

object AIGeneratedQuestions {
    
    fun getQuestionsForChapter(subjectId: String, chapterId: String): List<ExamQuestion> {
        return when (subjectId) {
            "maths" -> getMathsQuestions(chapterId)
            "science" -> getScienceQuestions(chapterId)
            "social_science" -> getSocialScienceQuestions(chapterId)
            else -> emptyList()
        }
    }
    
    private fun getMathsQuestions(chapterId: String): List<ExamQuestion> {
        return when (chapterId) {
            "maths_1" -> listOf(
                ExamQuestion(
                    id = "m1_q1",
                    question = "Prove that √5 is an irrational number.",
                    marks = 3,
                    type = QuestionType.SHORT_ANSWER,
                    answer = "Assume √5 is rational. Then √5 = p/q where p, q are coprime integers. Squaring both sides: 5 = p²/q², so 5q² = p². This means p² is divisible by 5, so p is divisible by 5. Let p = 5k. Then 5q² = 25k², so q² = 5k². This means q² is divisible by 5, so q is divisible by 5. But this contradicts that p and q are coprime. Hence, √5 is irrational.",
                    hints = listOf("Use proof by contradiction", "Assume √5 is rational", "Show both p and q are divisible by 5")
                ),
                ExamQuestion(
                    id = "m1_q2",
                    question = "Find the HCF of 96 and 404 using Euclid's division algorithm.",
                    marks = 3,
                    type = QuestionType.SHORT_ANSWER,
                    answer = "404 = 96 × 4 + 20\n96 = 20 × 4 + 16\n20 = 16 × 1 + 4\n16 = 4 × 4 + 0\nHCF = 4",
                    hints = listOf("Apply Euclid's algorithm", "Keep dividing until remainder is zero", "Last non-zero remainder is HCF")
                ),
                ExamQuestion(
                    id = "m1_q3",
                    question = "Show that any positive odd integer is of the form 6q + 1, or 6q + 3, or 6q + 5, where q is some integer.",
                    marks = 4,
                    type = QuestionType.LONG_ANSWER,
                    answer = "By Euclid's division lemma, any integer a can be written as a = 6q + r, where 0 ≤ r < 6.\nFor r = 0, 2, 4: a = 6q, 6q+2, 6q+4 are even.\nFor r = 1, 3, 5: a = 6q+1, 6q+3, 6q+5 are odd.\nHence, any positive odd integer is of the form 6q+1, 6q+3, or 6q+5.",
                    hints = listOf("Use Euclid's division lemma", "Consider all possible remainders", "Identify which forms give odd numbers")
                )
            )
            "maths_2" -> listOf(
                ExamQuestion(
                    id = "m2_q1",
                    question = "If α and β are the zeroes of the polynomial x² - 5x + 6, find the value of α² + β².",
                    marks = 2,
                    type = QuestionType.SHORT_ANSWER,
                    answer = "For polynomial x² - 5x + 6:\nα + β = 5, αβ = 6\nα² + β² = (α + β)² - 2αβ = 25 - 12 = 13",
                    hints = listOf("Use sum and product of zeroes", "Apply identity: α² + β² = (α + β)² - 2αβ")
                ),
                ExamQuestion(
                    id = "m2_q2",
                    question = "Find a quadratic polynomial whose zeroes are 3 + √2 and 3 - √2.",
                    marks = 3,
                    type = QuestionType.SHORT_ANSWER,
                    answer = "Sum of zeroes = (3 + √2) + (3 - √2) = 6\nProduct = (3 + √2)(3 - √2) = 9 - 2 = 7\nPolynomial: x² - (sum)x + product = x² - 6x + 7",
                    hints = listOf("Find sum and product of zeroes", "Use formula: x² - (sum)x + product")
                )
            )
            "maths_3" -> listOf(
                ExamQuestion(
                    id = "m3_q1",
                    question = "Solve the pair of linear equations: 2x + 3y = 7 and 3x - 2y = 1.",
                    marks = 3,
                    type = QuestionType.SHORT_ANSWER,
                    answer = "Multiplying first equation by 2: 4x + 6y = 14\nMultiplying second equation by 3: 9x - 6y = 3\nAdding: 13x = 17, so x = 17/13\nSubstituting: 2(17/13) + 3y = 7, so y = 11/13",
                    hints = listOf("Use elimination method", "Make coefficients of y equal", "Add or subtract equations")
                ),
                ExamQuestion(
                    id = "m3_q2",
                    question = "The cost of 2 kg of apples and 1 kg of grapes is ₹160. The cost of 4 kg of apples and 2 kg of grapes is ₹300. Represent this situation algebraically and find the cost of 1 kg of apples and 1 kg of grapes.",
                    marks = 4,
                    type = QuestionType.LONG_ANSWER,
                    answer = "Let cost of 1 kg apples = ₹x, cost of 1 kg grapes = ₹y\nEquations: 2x + y = 160 ...(1)\n4x + 2y = 300 ...(2)\nDividing (2) by 2: 2x + y = 150\nBut from (1): 2x + y = 160\nThis is inconsistent. Check the problem data.",
                    hints = listOf("Form equations from given conditions", "Use substitution or elimination", "Verify your answer")
                )
            )
            "maths_4" -> listOf(
                ExamQuestion(
                    id = "m4_q1",
                    question = "Find the nature of roots of the quadratic equation 2x² - 3x + 5 = 0.",
                    marks = 2,
                    type = QuestionType.SHORT_ANSWER,
                    answer = "Discriminant D = b² - 4ac = (-3)² - 4(2)(5) = 9 - 40 = -31\nSince D < 0, the equation has no real roots (complex roots).",
                    hints = listOf("Calculate discriminant", "D = b² - 4ac", "Check sign of D")
                ),
                ExamQuestion(
                    id = "m4_q2",
                    question = "If the quadratic equation x² - (k+1)x + k = 0 has equal roots, find the value of k.",
                    marks = 3,
                    type = QuestionType.SHORT_ANSWER,
                    answer = "For equal roots, D = 0\nD = (k+1)² - 4(1)(k) = k² + 2k + 1 - 4k = k² - 2k + 1 = (k-1)²\nSo (k-1)² = 0, hence k = 1",
                    hints = listOf("For equal roots, discriminant = 0", "Expand and simplify", "Solve for k")
                )
            )
            "maths_5" -> listOf(
                ExamQuestion(
                    id = "m5_q1",
                    question = "Find the 10th term of the AP: 5, 8, 11, 14, ...",
                    marks = 2,
                    type = QuestionType.SHORT_ANSWER,
                    answer = "First term a = 5, common difference d = 3\n10th term = a + 9d = 5 + 9(3) = 5 + 27 = 32",
                    hints = listOf("Identify first term and common difference", "Use formula: aₙ = a + (n-1)d")
                )
            )
            else -> getDefaultMathsQuestions()
        }
    }
    
    private fun getScienceQuestions(chapterId: String): List<ExamQuestion> {
        return when (chapterId) {
            "science_1" -> listOf(
                ExamQuestion(
                    id = "s1_q1",
                    question = "Balance the following chemical equation: Fe + H₂O → Fe₃O₄ + H₂",
                    marks = 2,
                    type = QuestionType.SHORT_ANSWER,
                    answer = "3Fe + 4H₂O → Fe₃O₄ + 4H₂",
                    hints = listOf("Balance Fe first", "Then balance H and O", "Check all atoms are balanced")
                ),
                ExamQuestion(
                    id = "s1_q2",
                    question = "What happens when zinc granules are added to dilute sulphuric acid? Write the chemical equation and identify the type of reaction.",
                    marks = 3,
                    type = QuestionType.SHORT_ANSWER,
                    answer = "Zinc reacts with dilute sulphuric acid to produce zinc sulphate and hydrogen gas.\nZn + H₂SO₄ → ZnSO₄ + H₂\nThis is a displacement reaction (single displacement).",
                    hints = listOf("Zinc displaces hydrogen", "Hydrogen gas is evolved", "It's a displacement reaction")
                ),
                ExamQuestion(
                    id = "s1_q3",
                    question = "Explain the process of rusting of iron. Write the chemical equation and suggest two methods to prevent rusting.",
                    marks = 5,
                    type = QuestionType.LONG_ANSWER,
                    answer = "Rusting is the slow oxidation of iron in presence of air and moisture.\nChemical equation: 4Fe + 3O₂ + 6H₂O → 4Fe(OH)₃\nPrevention methods:\n1. Galvanization: Coating iron with zinc\n2. Painting: Applying paint layer\n3. Oiling/Greasing: Applying oil layer\n4. Alloying: Making stainless steel",
                    hints = listOf("Requires oxygen and water", "It's an oxidation process", "Prevent contact with air/moisture")
                )
            )
            "science_2" -> listOf(
                ExamQuestion(
                    id = "s2_q1",
                    question = "Write the balanced chemical equation for photosynthesis. Explain the role of chlorophyll in this process.",
                    marks = 3,
                    type = QuestionType.SHORT_ANSWER,
                    answer = "6CO₂ + 6H₂O → C₆H₁₂O₆ + 6O₂ (in presence of sunlight and chlorophyll)\nChlorophyll absorbs sunlight and converts light energy into chemical energy, which is used to convert CO₂ and H₂O into glucose.",
                    hints = listOf("Reactants: CO₂ and H₂O", "Products: Glucose and O₂", "Chlorophyll is the green pigment")
                ),
                ExamQuestion(
                    id = "s2_q2",
                    question = "Explain the process of digestion in human beings with reference to different parts of the alimentary canal.",
                    marks = 5,
                    type = QuestionType.LONG_ANSWER,
                    answer = "1. Mouth: Saliva (amylase) breaks down starch\n2. Stomach: HCl and pepsin digest proteins\n3. Small Intestine: Complete digestion by pancreatic enzymes, bile emulsifies fats\n4. Absorption: Nutrients absorbed in small intestine\n5. Large Intestine: Water absorption\n6. Anus: Egestion of undigested food",
                    hints = listOf("Different enzymes at different stages", "Mechanical and chemical digestion", "Absorption in small intestine")
                ),
                ExamQuestion(
                    id = "s2_q3",
                    question = "Differentiate between aerobic and anaerobic respiration. Write one equation for each.",
                    marks = 3,
                    type = QuestionType.SHORT_ANSWER,
                    answer = "Aerobic: Requires oxygen, produces more energy (38 ATP), end products are CO₂ and H₂O.\nEquation: C₆H₁₂O₆ + 6O₂ → 6CO₂ + 6H₂O + Energy\nAnaerobic: No oxygen, produces less energy (2 ATP), end products vary (lactic acid or alcohol).\nEquation: C₆H₁₂O₆ → 2C₃H₆O₃ + Energy (in muscles)",
                    hints = listOf("Compare oxygen requirement", "Compare energy production", "Compare end products")
                )
            )
            "science_3" -> listOf(
                ExamQuestion(
                    id = "s3_q1",
                    question = "State the laws of reflection. A ray of light is incident on a plane mirror at an angle of 30°. What is the angle of reflection?",
                    marks = 2,
                    type = QuestionType.SHORT_ANSWER,
                    answer = "Laws of Reflection:\n1. Angle of incidence = Angle of reflection\n2. Incident ray, reflected ray, and normal lie in same plane\nAngle of reflection = 30° (equal to angle of incidence)",
                    hints = listOf("Angle of incidence = angle of reflection", "Both angles measured from normal")
                )
            )
            "science_4" -> listOf(
                ExamQuestion(
                    id = "s4_q1",
                    question = "What is the pH of a solution? A solution turns red litmus blue. What is its nature?",
                    marks = 2,
                    type = QuestionType.SHORT_ANSWER,
                    answer = "pH is a measure of acidity or basicity, ranging from 0-14.\npH < 7 = acidic, pH = 7 = neutral, pH > 7 = basic\nSolution turning red litmus blue is basic (pH > 7).",
                    hints = listOf("pH scale ranges from 0-14", "Red litmus turns blue in basic solution")
                )
            )
            else -> getDefaultScienceQuestions()
        }
    }
    
    private fun getDefaultScienceQuestions(): List<ExamQuestion> {
        return listOf(
            ExamQuestion(
                id = "default_s1",
                question = "What is the pH of a neutral solution?",
                marks = 1,
                type = QuestionType.VERY_SHORT_ANSWER,
                answer = "pH = 7",
                hints = listOf("Neutral means neither acidic nor basic")
            ),
            ExamQuestion(
                id = "default_s2",
                question = "Explain why metals are good conductors of electricity.",
                marks = 2,
                type = QuestionType.SHORT_ANSWER,
                answer = "Metals have free electrons in their outermost shell. These free electrons can move easily when an electric field is applied, allowing electric current to flow through the metal.",
                hints = listOf("Free electrons", "Mobile charge carriers")
            )
        )
    }
    
    private fun getDefaultMathsQuestions(): List<ExamQuestion> {
        return listOf(
            ExamQuestion(
                id = "default_m1",
                question = "Find the value of x if 2x + 3 = 11.",
                marks = 1,
                type = QuestionType.VERY_SHORT_ANSWER,
                answer = "x = 4",
                hints = listOf("Subtract 3 from both sides", "Divide by 2")
            ),
            ExamQuestion(
                id = "default_m2",
                question = "If the zeroes of polynomial x² + px + q are in the ratio 2:3, find the relationship between p and q.",
                marks = 3,
                type = QuestionType.SHORT_ANSWER,
                answer = "Let zeroes be 2k and 3k.\nSum = 5k = -p, so k = -p/5\nProduct = 6k² = q\nSubstituting: 6(-p/5)² = q, so 6p²/25 = q",
                hints = listOf("Let zeroes be 2k and 3k", "Use sum and product formulas")
            )
        )
    }
    
    private fun getSocialScienceQuestions(chapterId: String): List<ExamQuestion> {
        return when (chapterId) {
            "social_1" -> listOf(
                ExamQuestion(
                    id = "ss1_q1",
                    question = "Explain the role of French Revolution in the rise of nationalism in Europe.",
                    marks = 5,
                    type = QuestionType.LONG_ANSWER,
                    answer = "The French Revolution (1789) marked the beginning of modern nationalism. It introduced ideas of liberty, equality, and fraternity. The revolutionaries declared that people, not rulers, constitute the nation. These ideas spread across Europe through Napoleon's conquests, inspiring people to fight for their rights and national identity.",
                    hints = listOf("Mention 1789", "Ideas of liberty, equality, fraternity", "Role of Napoleon", "Spread across Europe")
                ),
                ExamQuestion(
                    id = "ss1_q2",
                    question = "Describe the process of unification of Germany.",
                    marks = 5,
                    type = QuestionType.LONG_ANSWER,
                    answer = "Germany was unified under the leadership of Otto von Bismarck through 'blood and iron' policy. The process involved three wars: Danish War (1864), Austro-Prussian War (1866), and Franco-Prussian War (1870-71). After defeating France, German states united and the German Empire was proclaimed in 1871 at Versailles.",
                    hints = listOf("Bismarck's role", "Three wars", "1871 unification", "Versailles")
                )
            )
            "social_2" -> listOf(
                ExamQuestion(
                    id = "ss2_q1",
                    question = "What was the impact of First World War on India?",
                    marks = 5,
                    type = QuestionType.LONG_ANSWER,
                    answer = "First World War created economic hardships in India. It led to increased taxes, forced recruitment of soldiers, food shortages, and inflation. The war expenses were borne by India, causing resentment. This created conditions for mass movements like Non-Cooperation Movement.",
                    hints = listOf("Economic hardships", "Increased taxes", "Forced recruitment", "Food shortages")
                ),
                ExamQuestion(
                    id = "ss2_q2",
                    question = "Explain the significance of Salt March in the Civil Disobedience Movement.",
                    marks = 5,
                    type = QuestionType.LONG_ANSWER,
                    answer = "Salt March (1930) was a symbolic act of breaking the salt law. Gandhi marched from Sabarmati to Dandi covering 240 miles. Thousands joined the march. On reaching Dandi, Gandhi made salt, breaking the British monopoly. This inspired mass civil disobedience across India, with people making salt, boycotting foreign goods, and refusing to pay taxes.",
                    hints = listOf("Symbolic act", "Sabarmati to Dandi", "Breaking salt law", "Mass participation")
                )
            )
            else -> getDefaultSocialScienceQuestions()
        }
    }
    
    private fun getDefaultSocialScienceQuestions(): List<ExamQuestion> {
        return listOf(
            ExamQuestion(
                id = "default_ss1",
                question = "What is nationalism?",
                marks = 2,
                type = QuestionType.SHORT_ANSWER,
                answer = "Nationalism is a feeling of unity and loyalty towards one's nation.",
                hints = listOf("Unity and loyalty", "Towards nation")
            ),
            ExamQuestion(
                id = "default_ss2",
                question = "What is democracy?",
                marks = 2,
                type = QuestionType.SHORT_ANSWER,
                answer = "Democracy is a form of government where people elect their representatives.",
                hints = listOf("Government by people", "Elected representatives")
            )
        )
    }
}

