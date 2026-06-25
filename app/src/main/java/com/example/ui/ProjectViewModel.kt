package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ProjModApplication
import com.example.data.AcademicProject
import com.example.data.ProjectRepository
import com.example.data.ProjectSection
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import android.util.Log
import com.example.BuildConfig
import com.example.data.RetrofitClient
import com.example.data.GeminiRequest
import com.example.data.Content
import com.example.data.Part
import com.example.data.GenerationConfig

class ProjectViewModel(
    application: Application,
    private val repository: ProjectRepository
) : AndroidViewModel(application) {

    private val _isDarkTheme = MutableStateFlow<Boolean?>(null)
    val isDarkTheme = _isDarkTheme.asStateFlow()

    fun toggleTheme(systemInDark: Boolean) {
        val current = _isDarkTheme.value ?: systemInDark
        _isDarkTheme.value = !current
    }

    private val _codeOutput = MutableStateFlow<String?>(null)
    val codeOutput = _codeOutput.asStateFlow()

    private val _isGeneratingCode = MutableStateFlow(false)
    val isGeneratingCode = _isGeneratingCode.asStateFlow()

    private val _codeError = MutableStateFlow<String?>(null)
    val codeError = _codeError.asStateFlow()

    private val _diagramSchemaOutput = MutableStateFlow<String?>(null)
    val diagramSchemaOutput = _diagramSchemaOutput.asStateFlow()

    private val _isGeneratingDiagramSchema = MutableStateFlow(false)
    val isGeneratingDiagramSchema = _isGeneratingDiagramSchema.asStateFlow()

    private val _diagramSchemaError = MutableStateFlow<String?>(null)
    val diagramSchemaError = _diagramSchemaError.asStateFlow()

    fun generateLabCode(topic: String, language: String, extraInstructions: String = "") {
        if (_isGeneratingCode.value || _selectedProject.value?.isLocked == true) return

        _isGeneratingCode.value = true
        _codeError.value = null
        _codeOutput.value = null

        viewModelScope.launch {
            val apiKey = BuildConfig.GEMINI_API_KEY
            if (apiKey.isEmpty() || apiKey == "MY_GEMINI_API_KEY") {
                _codeError.value = "Error: GEMINI_API_KEY is not configured in the Secrets panel."
                _isGeneratingCode.value = false
                return@launch
            }

            try {
                val prompt = """
                    You are a professional academic coding tutor. Write a complete, syllabus-ready, highly commented programming script for the topic: "$topic" in $language.
                    Include:
                    1. Direct execution instructions for the lab workbook.
                    2. Clean, readable code structure with clear logic.
                    3. Brief educational explanation of the algorithm or model.
                    4. Sample input and expected console output.
                    
                    Custom Guidelines: $extraInstructions
                """.trimIndent()

                val request = GeminiRequest(
                    contents = listOf(Content(parts = listOf(Part(text = prompt)))),
                    generationConfig = GenerationConfig(temperature = 0.6f)
                )
                val response = RetrofitClient.service.generateContent(apiKey, request)
                val text = response.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text
                if (text != null) {
                    _codeOutput.value = text
                } else {
                    _codeError.value = "Received empty response from Gemini."
                }
            } catch (e: Exception) {
                Log.e("ProjectViewModel", "Error generating code", e)
                _codeError.value = "Error: ${e.message}"
            } finally {
                _isGeneratingCode.value = false
            }
        }
    }

    fun generateDiagramSchema(topic: String, subject: String) {
        if (_isGeneratingDiagramSchema.value || _selectedProject.value?.isLocked == true) return

        _isGeneratingDiagramSchema.value = true
        _diagramSchemaError.value = null
        _diagramSchemaOutput.value = null

        viewModelScope.launch {
            val apiKey = BuildConfig.GEMINI_API_KEY
            if (apiKey.isEmpty() || apiKey == "MY_GEMINI_API_KEY") {
                _diagramSchemaError.value = "Error: GEMINI_API_KEY is not configured."
                _isGeneratingDiagramSchema.value = false
                return@launch
            }

            try {
                val prompt = """
                    You are an expert academic illustrator. Design a comprehensive visual blueprint and a professional AI text-to-image prompt for the academic topic: "$topic" in the subject: "$subject".
                    
                    Provide:
                    1. MANUAL LAYOUT GUIDE: Detailed instructions on how to draw this on paper (lines, labels, color highlights).
                    2. THEORETICAL BREAKDOWN: Brief explanation of what each component of the diagram represents.
                    3. TEXTBOOK ILLUSTRATOR PROMPT: A highly descriptive, single-paragraph AI prompt starting with "A professional textbook scientific illustration of..." that a student can copy to generate this image in any AI generator. Ensure it uses a clean white background, detailed text-labels, and modern vector design.
                """.trimIndent()

                val request = GeminiRequest(
                    contents = listOf(Content(parts = listOf(Part(text = prompt)))),
                    generationConfig = GenerationConfig(temperature = 0.6f)
                )
                val response = RetrofitClient.service.generateContent(apiKey, request)
                val text = response.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text
                if (text != null) {
                    _diagramSchemaOutput.value = text
                } else {
                    _diagramSchemaError.value = "Received empty response from Gemini."
                }
            } catch (e: Exception) {
                Log.e("ProjectViewModel", "Error generating diagram blueprint", e)
                _diagramSchemaError.value = "Error: ${e.message}"
            } finally {
                _isGeneratingDiagramSchema.value = false
            }
        }
    }

    fun clearLabCode() {
        _codeOutput.value = null
        _codeError.value = null
    }

    fun clearDiagramSchema() {
        _diagramSchemaOutput.value = null
        _diagramSchemaError.value = null
    }

    val allProjects: StateFlow<List<AcademicProject>> = repository.allProjects
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _selectedProjectId = MutableStateFlow<Int?>(null)
    val selectedProjectId = _selectedProjectId.asStateFlow()

    private val _selectedProject = MutableStateFlow<AcademicProject?>(null)
    val selectedProject = _selectedProject.asStateFlow()

    private val _sections = MutableStateFlow<List<ProjectSection>>(emptyList())
    val sections = _sections.asStateFlow()

    private val _isGenerating = MutableStateFlow(false)
    val isGenerating = _isGenerating.asStateFlow()

    private val _generationError = MutableStateFlow<String?>(null)
    val generationError = _generationError.asStateFlow()

    private val _generationSuccess = MutableStateFlow(false)
    val generationSuccess = _generationSuccess.asStateFlow()

    fun createProject(
        title: String,
        subject: String,
        studentName: String,
        rollNumber: String,
        schoolName: String,
        academicLevel: String,
        extraInstructions: String,
        outlineSections: List<String>,
        onSuccess: (Int) -> Unit
    ) {
        viewModelScope.launch {
            val id = repository.createProject(
                title = title,
                subject = subject,
                studentName = studentName,
                rollNumber = rollNumber,
                schoolName = schoolName,
                academicLevel = academicLevel,
                extraInstructions = extraInstructions
            )
            repository.initializeProjectSections(id.toInt(), outlineSections)
            selectProject(id.toInt())
            onSuccess(id.toInt())
        }
    }

    fun selectProject(projectId: Int) {
        _selectedProjectId.value = projectId
        _generationError.value = null
        _generationSuccess.value = false
        loadProjectDetails(projectId)
    }

    private fun loadProjectDetails(projectId: Int) {
        viewModelScope.launch {
            val project = repository.getProjectById(projectId)
            _selectedProject.value = project
            if (project != null) {
                repository.getSectionsFlow(projectId).collect { sectionList ->
                    _sections.value = sectionList
                }
            }
        }
    }

    fun generateNextSection() {
        val projectId = _selectedProjectId.value ?: return
        if (_isGenerating.value || _selectedProject.value?.isLocked == true) return

        _isGenerating.value = true
        _generationError.value = null
        _generationSuccess.value = false

        viewModelScope.launch {
            val result = repository.generateNextSection(projectId)
            _isGenerating.value = false
            if (result == "Success") {
                _generationSuccess.value = true
                loadProjectDetails(projectId)
            } else {
                _generationError.value = result
            }
        }
    }

    fun deleteProject(projectId: Int) {
        viewModelScope.launch {
            repository.deleteProject(projectId)
            if (_selectedProjectId.value == projectId) {
                _selectedProjectId.value = null
                _selectedProject.value = null
                _sections.value = emptyList()
            }
        }
    }

    fun updateSectionContent(sectionId: Int, projectId: Int, sectionIndex: Int, title: String, content: String) {
        viewModelScope.launch {
            repository.updateSectionContent(sectionId, projectId, sectionIndex, title, content)
            loadProjectDetails(projectId)
        }
    }

    fun updateSectionIllustration(sectionId: Int, projectId: Int, url: String) {
        viewModelScope.launch {
            repository.updateSectionIllustration(sectionId, url)
            loadProjectDetails(projectId)
        }
    }

    fun toggleProjectLock(projectId: Int, locked: Boolean) {
        viewModelScope.launch {
            repository.toggleProjectLock(projectId, locked)
            loadProjectDetails(projectId)
        }
    }

    fun updateProjectMetadata(
        id: Int,
        title: String,
        subject: String,
        studentName: String,
        rollNumber: String,
        schoolName: String,
        academicLevel: String,
        extraInstructions: String
    ) {
        viewModelScope.launch {
            repository.updateProjectMetadata(
                id = id,
                title = title,
                subject = subject,
                studentName = studentName,
                rollNumber = rollNumber,
                schoolName = schoolName,
                academicLevel = academicLevel,
                extraInstructions = extraInstructions
            )
            loadProjectDetails(id)
        }
    }

    fun clearError() {
        _generationError.value = null
    }

    fun clearSuccess() {
        _generationSuccess.value = false
    }

    companion object {
        fun provideFactory(application: Application): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val repo = (application as ProjModApplication).container.repository
                return ProjectViewModel(application, repo) as T
            }
        }
    }
}
