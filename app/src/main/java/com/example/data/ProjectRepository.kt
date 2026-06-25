package com.example.data

import android.util.Log
import com.example.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class ProjectRepository(private val projectDao: ProjectDao) {

    val allProjects: Flow<List<AcademicProject>> = projectDao.getAllProjectsFlow()

    fun getSectionsFlow(projectId: Int): Flow<List<ProjectSection>> {
        return projectDao.getSectionsForProjectFlow(projectId)
    }

    suspend fun getProjectById(projectId: Int): AcademicProject? = withContext(Dispatchers.IO) {
        projectDao.getProjectById(projectId)
    }

    suspend fun createProject(
        title: String,
        subject: String,
        studentName: String,
        rollNumber: String,
        schoolName: String,
        academicLevel: String,
        extraInstructions: String
    ): Long = withContext(Dispatchers.IO) {
        val project = AcademicProject(
            title = title,
            subject = subject,
            studentName = studentName,
            rollNumber = rollNumber,
            schoolName = schoolName,
            academicLevel = academicLevel,
            extraInstructions = extraInstructions,
            currentSectionIndex = 0
        )
        projectDao.insertProject(project)
    }

    suspend fun deleteProject(projectId: Int) = withContext(Dispatchers.IO) {
        projectDao.deleteProjectById(projectId)
    }

    suspend fun updateSectionContent(
        sectionId: Int,
        projectId: Int,
        sectionIndex: Int,
        title: String,
        content: String
    ) = withContext(Dispatchers.IO) {
        val project = projectDao.getProjectById(projectId)
        if (project?.isLocked == true) return@withContext

        val existing = projectDao.getSectionById(sectionId)
        val section = ProjectSection(
            id = sectionId,
            projectId = projectId,
            sectionIndex = sectionIndex,
            sectionTitle = title,
            content = content,
            generatedAt = System.currentTimeMillis(),
            illustrationUrl = existing?.illustrationUrl
        )
        projectDao.insertSection(section)
    }

    suspend fun updateSectionIllustration(sectionId: Int, projectId: Int, url: String) = withContext(Dispatchers.IO) {
        val project = projectDao.getProjectById(projectId)
        if (project?.isLocked == true) return@withContext

        projectDao.updateSectionIllustration(sectionId, url)
    }

    suspend fun toggleProjectLock(id: Int, locked: Boolean) = withContext(Dispatchers.IO) {
        projectDao.updateProjectLock(id, locked)
    }

    suspend fun updateProjectMetadata(
        id: Int,
        title: String,
        subject: String,
        studentName: String,
        rollNumber: String,
        schoolName: String,
        academicLevel: String,
        extraInstructions: String
    ) = withContext(Dispatchers.IO) {
        val existing = projectDao.getProjectById(id)
        if (existing != null && !existing.isLocked) {
            val updated = existing.copy(
                title = title,
                subject = subject,
                studentName = studentName,
                rollNumber = rollNumber,
                schoolName = schoolName,
                academicLevel = academicLevel,
                extraInstructions = extraInstructions,
                lastUpdatedTimestamp = System.currentTimeMillis()
            )
            projectDao.insertProject(updated)
        }
    }

    suspend fun initializeProjectSections(projectId: Int, sectionsList: List<String>) = withContext(Dispatchers.IO) {
        sectionsList.forEachIndexed { index, title ->
            val section = ProjectSection(
                projectId = projectId,
                sectionIndex = index + 1,
                sectionTitle = title,
                content = ""
            )
            projectDao.insertSection(section)
        }
    }

    suspend fun generateNextSection(projectId: Int): String = withContext(Dispatchers.IO) {
        val project = projectDao.getProjectById(projectId) ?: return@withContext "Error: Project not found"
        if (project.isLocked) return@withContext "Error: Project is locked and cannot be modified."
        val existingSections = projectDao.getSectionsForProject(projectId)

        val nextSectionToGenerate = if (existingSections.isEmpty()) {
            val nextSectionIndex = project.currentSectionIndex + 1
            if (nextSectionIndex > 10) {
                return@withContext "Error: All 10 sections are already generated for this project."
            }
            val sectionTitle = getSectionTitle(nextSectionIndex)
            ProjectSection(projectId = projectId, sectionIndex = nextSectionIndex, sectionTitle = sectionTitle, content = "")
        } else {
            existingSections.firstOrNull { it.content.isBlank() }
                ?: return@withContext "Error: All sections of the planned outline are already generated."
        }

        val nextSectionIndex = nextSectionToGenerate.sectionIndex
        val sectionTitle = nextSectionToGenerate.sectionTitle

        // Compile context of already-generated sections for Gemini's memory
        val generatedSections = existingSections.filter { it.content.isNotBlank() }
        val existingContentContext = if (generatedSections.isNotEmpty()) {
            buildString {
                append("--- PREVIOUS SECTIONS MEMORY (Do not repeat or redefine these concepts, maintain consistent tone and structure) ---\n")
                generatedSections.forEach { sec ->
                    append("Section ${sec.sectionIndex}: ${sec.sectionTitle}\n")
                    append(sec.content.take(1500)) // Send a summary snippet to fit in context efficiently
                    append("\n...\n")
                }
            }
        } else ""

        val prompt = buildPrompt(project, nextSectionIndex, sectionTitle, existingContentContext)

        val apiKey = BuildConfig.GEMINI_API_KEY
        if (apiKey.isEmpty() || apiKey == "MY_GEMINI_API_KEY") {
            return@withContext "Error: GEMINI_API_KEY is not configured in the Secrets panel."
        }

        try {
            val request = GeminiRequest(
                contents = listOf(Content(parts = listOf(Part(text = prompt)))),
                generationConfig = GenerationConfig(temperature = 0.5f)
            )
            val response = RetrofitClient.service.generateContent(apiKey, request)
            val responseText = response.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text
                ?: return@withContext "Error: Received empty response from Gemini API."

            // Save generated section to local database (overwriting the blank placeholder or inserting new)
            val newSection = nextSectionToGenerate.copy(
                content = responseText,
                generatedAt = System.currentTimeMillis()
            )
            projectDao.insertSection(newSection)

            // Update project status
            projectDao.updateProjectProgress(projectId, nextSectionIndex, System.currentTimeMillis())

            return@withContext "Success"
        } catch (e: Exception) {
            Log.e("ProjectRepository", "API Error: ${e.message}", e)
            return@withContext "Error: ${e.localizedMessage ?: "Network error calling Gemini API"}"
        }
    }

    private fun getSectionTitle(index: Int): String {
        return when (index) {
            1 -> "Front Matter & Section 1: Introduction"
            2 -> "Section 2: Background and Theory"
            3 -> "Section 3: Literature Review"
            4 -> "Section 4: Experimental Design / Methodology"
            5 -> "Section 5: Observations and Data"
            6 -> "Section 6: Analysis and Discussion"
            7 -> "Section 7: Applications and Real-World Relevance"
            8 -> "Section 8: Conclusion"
            9 -> "Section 9: Bibliography"
            10 -> "Section 10: Viva Questions"
            else -> "Section"
        }
    }

    private fun buildPrompt(
        project: AcademicProject,
        sectionIndex: Int,
        sectionTitle: String,
        existingContext: String
    ): String {
        val extraInstrText = if (project.extraInstructions.isNotBlank()) {
            "CRITICAL: Adhere to these custom guidelines/specifications/experiment notes provided by the user:\n${project.extraInstructions}\n"
        } else ""

        val baseSystem = """
            You are a professional academic writer specializing in academic projects for the education level: ${project.academicLevel}.
            Your objective is to create complete, high-quality academic projects that are ready to be copied into a Word document and submitted with minimal editing.
            
            STRICT RULES:
            1. Generate REAL academic content. Focus on accuracy, clarity, structure, readability, and educational value.
            2. Never prioritize artificial length over quality.
            3. Do NOT: fake page counts, fake word counts, fake progress percentages, create fictional validation systems, or mention internal AI processes.
            4. Do NOT repeat or redefine concepts repeatedly (follow the MEMORY RULE).
            5. The writing style must match:
               - Academic level: ${project.academicLevel}
               - Easy, fluent academic English appropriate for the level ${project.academicLevel} (higher levels like College Undergrad/Postgrad should feature advanced scientific rigor, equations, references, or sophisticated analysis).
               - Natural, human-written style.
            6. TABLE RULE: Every major section MUST contain at least one Comparison Chart, Data Table, Observation Format, or Diagram description (represented beautifully in markdown tables/formatting) where relevant.
            
            $extraInstrText
        """.trimIndent()

        return when (sectionIndex) {
            1 -> """
                $baseSystem
                
                PROJECT TOPIC: ${project.title}
                SUBJECT: ${project.subject}
                
                STUDENT METADATA:
                - Student Name: ${project.studentName.ifEmpty { " [Student Name] " }}
                - Roll/ID Number: ${project.rollNumber.ifEmpty { " [Roll Number] " }}
                - School/College/Institution Name: ${project.schoolName.ifEmpty { " [Institution Name] " }}
                
                TASK:
                Generate Step 1 of the project, which consists of the complete Front Matter and Section 1: Introduction.
                
                STRUCTURE FOR STEP 1:
                1. COVER PAGE: Include beautiful text layout showing Project Title, Subject, Academic Level: ${project.academicLevel}, Student's Name, Roll/ID Number, School/College/Institution, and year 2026.
                2. CERTIFICATE: Standard school/college project certificate of excellence signed by internal examiner, external examiner, and teacher/professor/supervisor.
                3. ACKNOWLEDGEMENT: Sincere appreciation to the institution principal/head, subject teacher/professor, classmates, and parents for providing this opportunity.
                4. ABSTRACT: A highly comprehensive summary (150-200 words) outlining the project topic, core objectives, methods, and expected results.
                5. TABLE OF CONTENTS: Generate a complete table of contents listing all 10 sections with clear hierarchical headings (do not assign fictional page numbers, just list the sections and sub-headings).
                6. SECTION 1: INTRODUCTION: Follow the DEPTH RULE:
                   - Clear Definition of ${project.title}
                   - Scientific Background & Theory
                   - Historical Context (who discovered or pioneered it, key milestones)
                   - Detailed Explanation (break down core concepts)
                   - Real Examples
                   - Practical Applications
                   - Advantages
                   - Limitations
                   - Visual/Table representation: Include a beautiful comparison or classification table detailing the key classifications or categories of the topic.
                   - Summary of the introduction section, ending naturally.
                
                At the very end of your response, you MUST write exactly:
                👉 Type CONTINUE for next section
            """.trimIndent()

            else -> """
                $baseSystem
                
                PROJECT TOPIC: ${project.title}
                SUBJECT: ${project.subject}
                
                $existingContext
                
                TASK:
                Generate ONLY "$sectionTitle" for this ${project.academicLevel} Project.
                Do NOT generate multiple sections. Focus solely on making "$sectionTitle" exhaustive, accurate, and ready for submission.
                
                FOLLOW THE DEPTH RULE for "$sectionTitle":
                - Definition: Clear focus on this section's sub-topic.
                - Scientific Background: Underlying formula, laws, principles, or logic.
                - Detailed Explanation: Step-by-step breakdown of concepts.
                - Examples: Real world or academic scenarios.
                - Applications: Practical uses of the ideas in this specific section.
                - Advantages & Limitations of the methods/theories.
                - Table/Chart: You MUST include at least one detailed markdown table (e.g., experimental observation format, comparison chart, bibliography log, or list of viva questions and answers depending on the section).
                - Summary: Conclude the section naturally.
                
                At the very end of your response, you MUST write exactly:
                👉 Type CONTINUE for next section
            """.trimIndent()
        }
    }
}
