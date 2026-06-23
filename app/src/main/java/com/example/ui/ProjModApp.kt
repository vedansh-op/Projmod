package com.example.ui

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.horizontalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.AssignmentTurnedIn
import androidx.compose.material.icons.filled.Spellcheck
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.Image
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.data.AcademicProject
import com.example.data.ProjectSection
import coil.compose.AsyncImage
import coil.request.ImageRequest
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.os.Bundle
import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.DisposableEffect
import androidx.compose.material3.Slider
import androidx.compose.animation.core.*
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.filled.VolumeMute
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Image

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjModApp(viewModel: ProjectViewModel) {
    val selectedProjectId by viewModel.selectedProjectId.collectAsState()
    val selectedProject by viewModel.selectedProject.collectAsState()

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (selectedProjectId == null || selectedProject == null) {
                DashboardScreen(viewModel = viewModel)
            } else {
                ProjectWorkspaceScreen(
                    project = selectedProject!!,
                    viewModel = viewModel,
                    onBack = { viewModel.selectProject(-1) } // clear selection
                )
            }
        }
    }
}

@Composable
fun DashboardScreen(viewModel: ProjectViewModel) {
    val projects by viewModel.allProjects.collectAsState()
    var showCreateDialog by varShowCreateDialog()
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            // Header
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = com.example.R.drawable.academic_logo),
                        contentDescription = "ProjMod Ultimate Logo",
                        modifier = Modifier
                            .size(44.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "ProjMod Ultimate",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            text = "Academic & Syllabus Project Architect",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                        )
                    }
                }

                val isDarkState by viewModel.isDarkTheme.collectAsState()
                val systemInDark = androidx.compose.foundation.isSystemInDarkTheme()
                val activeDark = isDarkState ?: systemInDark

                IconButton(
                    onClick = { viewModel.toggleTheme(systemInDark) },
                    modifier = Modifier.testTag("theme_toggle_button")
                ) {
                    Icon(
                        imageVector = if (activeDark) Icons.Default.WbSunny else Icons.Default.DarkMode,
                        contentDescription = "Toggle Dark/Light Theme",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Quick Info Card
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Info",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Project Writing Standard",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Generates complete academic projects tailored to any class or educational level. Includes custom student detail mapping, customized syllabus focus, data tables, scientific methodologies, and bibliography.",
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Your Projects",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(12.dp))

            if (projects.isEmpty()) {
                EmptyState(onCreateClick = { showCreateDialog = true })
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.weight(1.0f)
                ) {
                    items(projects) { project ->
                        ProjectItemCard(
                            project = project,
                            onClick = { viewModel.selectProject(project.id) },
                            onDelete = { viewModel.deleteProject(project.id) }
                        )
                    }
                }
            }
        }

        // FAB to start new project
        FloatingActionButton(
            onClick = { showCreateDialog = true },
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Icon")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "New Project", fontWeight = FontWeight.Bold)
            }
        }
    }

    if (showCreateDialog) {
        CreateProjectDialog(
            onDismiss = { showCreateDialog = false },
            onSubmit = { title, subject, name, roll, school, level, extra, outline ->
                viewModel.createProject(
                    title = title,
                    subject = subject,
                    studentName = name,
                    rollNumber = roll,
                    schoolName = school,
                    academicLevel = level,
                    extraInstructions = extra,
                    outlineSections = outline
                ) {
                    showCreateDialog = false
                    Toast.makeText(context, "Project Created Successfully!", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
}

@Composable
fun EmptyState(onCreateClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.History,
            contentDescription = "Empty History",
            tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "No Projects Yet",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Create your first high-scoring academic project for school or college using advanced research intelligence.",
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 32.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedButton(
            onClick = onCreateClick,
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(text = "Explore Project Ideas")
        }
    }
}

@Composable
fun ProjectItemCard(
    project: AcademicProject,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    val dateFormat = remember { SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault()) }
    val formattedDate = remember(project.lastUpdatedTimestamp) { dateFormat.format(Date(project.lastUpdatedTimestamp)) }
    val progressPercent = (project.currentSectionIndex * 10)

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "${project.subject.uppercase()} • ${project.academicLevel.uppercase()}",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = project.title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                IconButton(onClick = onDelete) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = MaterialTheme.colorScheme.error.copy(alpha = 0.8f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Student Metadata Badge if available
            if (project.studentName.isNotEmpty()) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.School,
                        contentDescription = "School",
                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "${project.studentName} • ${project.schoolName}",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
            }

            // Progress bar and details
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                LinearProgressIndicator(
                    progress = { project.currentSectionIndex / 10f },
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier
                        .weight(1f)
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp))
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "$progressPercent% (${project.currentSectionIndex}/10 Sections)",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Last updated: $formattedDate",
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
            )
        }
    }
}

data class AcademicTemplate(
    val title: String,
    val subject: String,
    val extraInstructions: String,
    val defaultOutline: List<String>
)

val predefinedTemplates = listOf(
    AcademicTemplate(
        title = "Electromagnetic Induction and Transformers",
        subject = "Physics",
        extraInstructions = "Include Maxwell's equations, Faraday's Law, Lenz's Law, comparison of step-up/down transformers, and a mathematical table representing induction efficiency.",
        defaultOutline = listOf(
            "Cover Page, Abstract & Table of Contents",
            "Introduction to Electromagnetism & Magnetic Flux",
            "Faraday's Laws of Induction & Lenz's Law Mechanics",
            "Self-Induction and Mutual Induction Principles",
            "Working Principle and Construction of Transformers",
            "Energy Losses in Transformers & Mitigation",
            "Applications of Induction in Modern Electric Grids",
            "Safety Measures, Limitations & Future Scope",
            "Project Bibliography & Scholarly Citations",
            "Physics Viva-Voce Key Questions & Answers"
        )
    ),
    AcademicTemplate(
        title = "To Study Factors Affecting Self Inductance of a Coil",
        subject = "Physics",
        extraInstructions = "Include the formula L = (μ₀ * N² * A) / l, experimental observations with different core materials (iron, air), and a table of self-inductance values.",
        defaultOutline = listOf(
            "Cover Page, Abstract & Table of Contents",
            "Introduction to Self Inductance & Magnetic Permeability",
            "Theoretical Derivation of Coil Inductance",
            "Experimental Setup and Circuit Diagram Analysis",
            "Observations: Effect of Number of Turns (N)",
            "Observations: Effect of Core Permeability",
            "Observations: Effect of Cross-sectional Area & Length",
            "Discussion of Experimental Errors & Precautions",
            "Bibliography and Reference Texts",
            "Viva Q&A: Self-Inductance Concepts"
        )
    ),
    AcademicTemplate(
        title = "Mendelian Genetics and Monohybrid Cross",
        subject = "Biology",
        extraInstructions = "Focus on Gregor Mendel's Pisum sativum experiments, the Law of Segregation, Punnett squares for inheritance, and a Chi-Square goodness-of-fit table.",
        defaultOutline = listOf(
            "Cover Page, Abstract & Table of Contents",
            "Introduction to Genetics & Mendel's Historic Work",
            "The Law of Dominance and Segregation Explained",
            "Experimental Method: Hybridization of Garden Peas",
            "Monohybrid Cross Phenotypic & Genotypic Ratios",
            "The Punnett Square Representation of F1 and F2 Gener.",
            "Chi-Square Statistical Verification of 3:1 Ratio",
            "Incomplete Dominance & Co-dominance Exceptions",
            "Bibliography and Scholarly Journal References",
            "Biology Viva FAQ on Mendelian Principles"
        )
    ),
    AcademicTemplate(
        title = "To Study Drug Abuse and Addiction Among Adolescents",
        subject = "Biology",
        extraInstructions = "Include classification of drugs (stimulants, depressants, hallucinogens), socio-psychological factors leading to addiction, a survey response matrix table, and rehabilitation steps.",
        defaultOutline = listOf(
            "Cover Page, Abstract & Table of Contents",
            "Introduction to Psychoactive Substances & Addiction",
            "Classification of Frequently Abused Drugs",
            "Biological Action of Drugs on the Nervous System",
            "Social, Psychological, and Peer Factors in Youth",
            "Case Studies & Survey Analysis Matrix Table",
            "Physical and Psychological Withdrawal Symptoms",
            "Prevention, Counseling, and Rehabilitation Framework",
            "Bibliography and Medical Authority References",
            "Viva Voce: Drug Action & Adolescent Counseling"
        )
    ),
    AcademicTemplate(
        title = "Relational Database Management Systems & SQL",
        subject = "Computer Science",
        extraInstructions = "Include Entity-Relationship diagrams description, Normalization (1NF, 2NF, 3NF), basic SQL commands table (DDL, DML), and a case study of a school database with code snippets.",
        defaultOutline = listOf(
            "Cover Page, Abstract & Table of Contents",
            "Introduction to DBMS and Relational Models",
            "Entity-Relationship (ER) Diagram Schema Blueprint",
            "Normalization Rules: 1NF, 2NF, 3NF, and BCNF",
            "Structured Query Language: DDL & DML Commands",
            "Case Study: Database Implementation for School Portal",
            "Database Integrity, Constraints, and Keys",
            "SQL Transaction Control (ACID Properties)",
            "Bibliography & Official Documentation References",
            "CS Viva Q&A & SQL Code Snippet Walkthrough"
        )
    ),
    AcademicTemplate(
        title = "Encryption Algorithms and Cyber Security",
        subject = "Computer Science",
        extraInstructions = "Explain Symmetric vs Asymmetric Encryption, detailed steps of RSA algorithm with prime numbers, simple cipher comparison table, and modern SSL/TLS handshake description.",
        defaultOutline = listOf(
            "Cover Page, Abstract & Table of Contents",
            "Introduction to Cryptography and Information Security",
            "Classical Ciphers: Caesar Cipher and Vigenere",
            "Symmetric Cryptography: DES, AES and Key Exchange",
            "Asymmetric Cryptography and RSA Algorithm Steps",
            "Cipher Comparison Matrix (Security, Speed, Key Size)",
            "Symmetric vs Asymmetric Key Infrastructure",
            "SSL/TLS Protocol and Web Handshake Mechanics",
            "Bibliography and Cybersecurity Guidelines Reference",
            "CS Viva Voce: Encryption FAQ and Practical Cipher Code"
        )
    )
)

fun getDefaultOutlineForSubject(sub: String): List<String> {
    return when (sub.trim().lowercase()) {
        "physics" -> listOf(
            "Cover Page, Abstract & Table of Contents",
            "Section 1: Introduction to Physics Concept",
            "Section 2: Underlying Physical Principles & Formula",
            "Section 3: Literature Review & Preceding Studies",
            "Section 4: Experimental Design & Setup",
            "Section 5: Observation and Experimental Readings",
            "Section 6: Graphical Analysis & Calculation",
            "Section 7: Advantages, Disadvantages & Error Analysis",
            "Section 8: Bibliography & Scholarly References",
            "Section 9: Viva-Voce Questions and Answers"
        )
        "biology" -> listOf(
            "Cover Page, Abstract & Table of Contents",
            "Section 1: Introduction & Biological Relevance",
            "Section 2: Anatomical / Physiological Background",
            "Section 3: Case Studies & Clinical History",
            "Section 4: Materials and Experimental Procedure",
            "Section 5: Observations & Tabular Data Formats",
            "Section 6: Discussion & Analytical Review",
            "Section 7: Modern Medical / Agricultural Significance",
            "Section 8: Bibliography & Authority References",
            "Section 9: Biology Viva Q&A Guide"
        )
        "computer science" -> listOf(
            "Cover Page, Abstract & Table of Contents",
            "Section 1: Introduction & Computational Theory",
            "Section 2: System Architecture & ER Diagrams",
            "Section 3: Database / Logic Schema Specifications",
            "Section 4: Algorithm & Program Code Snippet",
            "Section 5: Output Screens & Console logs",
            "Section 6: System Requirements & Testing Matrix",
            "Section 7: Future Scope, Scalability & Security",
            "Section 8: Reference Books & Technical Docs",
            "Section 9: CS Viva Q&A & Code Discussion"
        )
        else -> listOf(
            "Cover Page, Abstract & Table of Contents",
            "Section 1: Introduction to Research Topic",
            "Section 2: History and Background Studies",
            "Section 3: Methodology and Core Principles",
            "Section 4: Key Discussion Points & Analysis",
            "Section 5: Data Presentation or Case Study Matrix",
            "Section 6: Real-World Applications & Advantages",
            "Section 7: Project Limitations and Scope",
            "Section 8: Academic Conclusion & Summary",
            "Section 9: Bibliography & Citation Log",
            "Section 10: Topic Viva Q&A Framework"
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateProjectDialog(
    onDismiss: () -> Unit,
    onSubmit: (String, String, String, String, String, String, String, List<String>) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("Physics") }
    var studentName by remember { mutableStateOf("") }
    var rollNumber by remember { mutableStateOf("") }
    var schoolName by remember { mutableStateOf("") }
    var academicLevel by remember { mutableStateOf("Class XII (CBSE/State)") }
    var extraInstructions by remember { mutableStateOf("") }
    var isLevelExpanded by remember { mutableStateOf(false) }
    var customLevel by remember { mutableStateOf("") }
    var useCustomLevel by remember { mutableStateOf(false) }

    var currentOutline by remember { mutableStateOf(getDefaultOutlineForSubject("Physics")) }
    var creationStep by remember { mutableIntStateOf(1) } // 1: Details & Templates, 2: Interactive Outline Planner
    var selectedTemplateIndex by remember { mutableIntStateOf(-1) }

    val context = LocalContext.current
    var activeVoiceField by remember { mutableStateOf<String?>(null) }

    val speechRecognizerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val spokenText = result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.firstOrNull()
            if (spokenText != null) {
                when (activeVoiceField) {
                    "title" -> title = spokenText
                    "subject" -> {
                        subject = spokenText
                        currentOutline = getDefaultOutlineForSubject(spokenText)
                    }
                    "instructions" -> extraInstructions = if (extraInstructions.isEmpty()) spokenText else "$extraInstructions $spokenText"
                }
            }
        }
        activeVoiceField = null
    }

    val startVoiceRecognition = { fieldName: String ->
        activeVoiceField = fieldName
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak clearly to dictate...")
        }
        try {
            speechRecognizerLauncher.launch(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Voice dictation not supported on this device. Keyboards are fully active!", Toast.LENGTH_SHORT).show()
        }
    }

    val levels = listOf(
        "Class X",
        "Class XI",
        "Class XII (CBSE/State)",
        "College Undergraduate (B.Sc / B.Tech / B.A)",
        "College Postgraduate (M.Sc / M.Tech / M.A)",
        "Custom Level"
    )

    val subjects = listOf(
        "Physics", "Chemistry", "Computer Science", "Biology", "Mathematics",
        "Business Studies", "Economics", "History", "Political Science", "Geography", "English"
    )

    val sampleTopics = mapOf(
        "Physics" to listOf(
            "Electromagnetic Induction and its Applications",
            "To study factors affecting internal resistance of a cell",
            "To study self inductance of a coil",
            "To construct and study a working model of a full-wave rectifier"
        ),
        "Chemistry" to listOf(
            "To study quantity of casein present in different milk samples",
            "To study rate of fermentation of various fruit juices",
            "To study presence of oxalate ions in guava fruit"
        ),
        "Computer Science" to listOf(
            "Student Management System in Python & MySQL",
            "Library Database and Book Tracking System",
            "Hospital Portal and Medical Inventory System"
        ),
        "Biology" to listOf(
            "To study drug abuse and addiction among adolescents",
            "Study of common human pathogens and diseases",
            "To study rate of transpiration in different plant species"
        ),
        "Mathematics" to listOf(
            "Applications of Integration in Real Life",
            "Probability Theory and Game Analysis",
            "Linear Programming and Resource Optimization"
        ),
        "Economics" to listOf(
            "Impact of Demonetization on Digital Payments",
            "Analysis of Consumer Buying Behavior",
            "Study of Inflation Dynamics in Developing Nations"
        ),
        "History" to listOf(
            "The French Revolution and Its Global Impact",
            "A Study of the League of Nations' Failure",
            "Evolution of Democratic Systems in Modern Era"
        )
    )

    val selectedSubjectLower = subject.lowercase()
    val suggestionList = when {
        selectedSubjectLower.contains("phys") -> sampleTopics["Physics"]
        selectedSubjectLower.contains("chem") -> sampleTopics["Chemistry"]
        selectedSubjectLower.contains("comp") || selectedSubjectLower.contains("cs") || selectedSubjectLower.contains("soft") || selectedSubjectLower.contains("cod") -> sampleTopics["Computer Science"]
        selectedSubjectLower.contains("biol") || selectedSubjectLower.contains("bot") || selectedSubjectLower.contains("zoo") -> sampleTopics["Biology"]
        selectedSubjectLower.contains("math") || selectedSubjectLower.contains("calc") || selectedSubjectLower.contains("stat") -> sampleTopics["Mathematics"]
        selectedSubjectLower.contains("econ") || selectedSubjectLower.contains("bus") || selectedSubjectLower.contains("comm") || selectedSubjectLower.contains("acc") -> sampleTopics["Economics"]
        selectedSubjectLower.contains("hist") || selectedSubjectLower.contains("pol") || selectedSubjectLower.contains("civ") || selectedSubjectLower.contains("geog") -> sampleTopics["History"]
        else -> listOf(
            "Detailed Analysis of Selected Topic",
            "A Comprehensive Research on Proposed Theme",
            "Case Study and Scientific Investigation"
        )
    }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxHeight(0.85f)
            ) {
                if (creationStep == 1) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = "Syllabus Architect",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(28.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Draft Academic Project",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Text(
                            text = "Step 1 of 2: Configure parameters or load a pre-structured template.",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Predefined Templates Library",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            itemsIndexed(predefinedTemplates) { index, template ->
                                val isSelected = selectedTemplateIndex == index
                                Card(
                                    colors = CardDefaults.cardColors(
                                        containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer
                                        else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                                    ),
                                    border = androidx.compose.foundation.BorderStroke(
                                        width = if (isSelected) 2.dp else 1.dp,
                                        color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
                                    ),
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier
                                        .width(190.dp)
                                        .clickable {
                                            selectedTemplateIndex = index
                                            title = template.title
                                            subject = template.subject
                                            extraInstructions = template.extraInstructions
                                            currentOutline = template.defaultOutline
                                        }
                                ) {
                                    Column(modifier = Modifier.padding(10.dp)) {
                                        Box(
                                            modifier = Modifier
                                                .background(
                                                    color = when (template.subject) {
                                                        "Physics" -> Color(0xFF1976D2)
                                                        "Biology" -> Color(0xFF388E3C)
                                                        "Computer Science" -> Color(0xFFF57C00)
                                                        else -> MaterialTheme.colorScheme.secondary
                                                    },
                                                    shape = RoundedCornerShape(4.dp)
                                                )
                                                .padding(horizontal = 6.dp, vertical = 2.dp)
                                        ) {
                                            Text(
                                                text = template.subject,
                                                fontSize = 9.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.White
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(6.dp))
                                        Text(
                                            text = template.title,
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = MaterialTheme.colorScheme.onSurface,
                                            maxLines = 2,
                                            overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = "${template.defaultOutline.size} Outline sections",
                                            fontSize = 9.sp,
                                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        ExposedDropdownMenuBox(
                            expanded = isLevelExpanded,
                            onExpandedChange = { isLevelExpanded = !isLevelExpanded },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            OutlinedTextField(
                                value = academicLevel,
                                onValueChange = {},
                                readOnly = true,
                                label = { Text("Academic Level / Class") },
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isLevelExpanded) },
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                                    unfocusedBorderColor = MaterialTheme.colorScheme.outline
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .menuAnchor()
                                    .testTag("academic_level_input")
                            )
                            ExposedDropdownMenu(
                                expanded = isLevelExpanded,
                                onDismissRequest = { isLevelExpanded = false }
                            ) {
                                levels.forEach { lvl ->
                                    DropdownMenuItem(
                                        text = { Text(lvl) },
                                        onClick = {
                                            academicLevel = lvl
                                            useCustomLevel = (lvl == "Custom Level")
                                            isLevelExpanded = false
                                        }
                                    )
                                }
                            }
                        }

                        if (useCustomLevel) {
                            Spacer(modifier = Modifier.height(12.dp))
                            OutlinedTextField(
                                value = customLevel,
                                onValueChange = { customLevel = it },
                                label = { Text("Enter Custom Academic Level") },
                                placeholder = { Text("e.g. B.Tech 3rd Year, PhD") },
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                                    unfocusedBorderColor = MaterialTheme.colorScheme.outline
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("custom_academic_level_input")
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = subject,
                            onValueChange = {
                                subject = it
                                selectedTemplateIndex = -1
                                currentOutline = getDefaultOutlineForSubject(it)
                            },
                            label = { Text("Project Subject") },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.outline
                            ),
                            trailingIcon = {
                                IconButton(onClick = { startVoiceRecognition("subject") }) {
                                    Icon(
                                        imageVector = Icons.Default.Mic,
                                        contentDescription = "Voice Type Subject",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("subject_input")
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Or tap a popular subject suggestion:",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items(subjects) { sub ->
                                val isSelected = (subject.lowercase() == sub.lowercase())
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(
                                            if (isSelected) MaterialTheme.colorScheme.primary
                                            else MaterialTheme.colorScheme.surfaceVariant
                                        )
                                        .clickable {
                                            subject = sub
                                            selectedTemplateIndex = -1
                                            currentOutline = getDefaultOutlineForSubject(sub)
                                        }
                                        .padding(horizontal = 10.dp, vertical = 6.dp)
                                ) {
                                    Text(
                                        text = sub,
                                        fontSize = 11.sp,
                                        color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = title,
                            onValueChange = {
                                title = it
                                selectedTemplateIndex = -1
                            },
                            label = { Text("Project Title / Topic") },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.outline
                            ),
                            trailingIcon = {
                                IconButton(onClick = { startVoiceRecognition("title") }) {
                                    Icon(
                                        imageVector = Icons.Default.Mic,
                                        contentDescription = "Voice Type Title",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("title_input")
                        )

                        suggestionList?.let { topicList ->
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Suggested topics for $subject:",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                topicList.forEach { suggestion ->
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.08f))
                                            .clickable {
                                                title = suggestion
                                                selectedTemplateIndex = -1
                                            }
                                            .padding(horizontal = 8.dp, vertical = 6.dp)
                                    ) {
                                        Text(
                                            text = suggestion,
                                            fontSize = 11.sp,
                                            color = MaterialTheme.colorScheme.primary,
                                            fontWeight = FontWeight.Medium
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Student Details (Optional)",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.secondary
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = studentName,
                            onValueChange = { studentName = it },
                            label = { Text("Your Name") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("student_name_input")
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        OutlinedTextField(
                            value = rollNumber,
                            onValueChange = { rollNumber = it },
                            label = { Text("Roll Number / Student ID") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("roll_number_input")
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        OutlinedTextField(
                            value = schoolName,
                            onValueChange = { schoolName = it },
                            label = { Text("School / College / University Name") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("school_name_input")
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Guidelines / Specifics (Optional)",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.secondary
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = extraInstructions,
                            onValueChange = {
                                extraInstructions = it
                                selectedTemplateIndex = -1
                            },
                            label = { Text("Custom Project Instructions") },
                            placeholder = { Text("e.g. Include circuit diagram description, write at a college level, focus on sustainable development, etc.") },
                            minLines = 3,
                            maxLines = 6,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.outline
                            ),
                            trailingIcon = {
                                IconButton(onClick = { startVoiceRecognition("instructions") }) {
                                    Icon(
                                        imageVector = Icons.Default.Mic,
                                        contentDescription = "Voice Type Guidelines",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("custom_instructions_input")
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedButton(
                            onClick = onDismiss,
                            modifier = Modifier.testTag("cancel_button")
                        ) {
                            Text(text = "Cancel")
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Button(
                            onClick = {
                                if (title.isNotBlank()) {
                                    creationStep = 2
                                }
                            },
                            enabled = title.isNotBlank(),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                            modifier = Modifier.testTag("next_step_button")
                        ) {
                            Text(text = "Next: Plan Outline")
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(imageVector = Icons.Default.ChevronRight, contentDescription = "Next")
                        }
                    }
                } else {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.AssignmentTurnedIn,
                                contentDescription = "Interactive Outline Planner",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(28.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Interactive Outline Planner",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Text(
                            text = "Step 2 of 2: Reorder, add, rename or delete chapters of your thesis outline before generating content.",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            itemsIndexed(currentOutline) { index, secName ->
                                Card(
                                    colors = CardDefaults.cardColors(
                                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                                    ),
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Row(
                                        modifier = Modifier.padding(8.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Box(
                                            contentAlignment = Alignment.Center,
                                            modifier = Modifier
                                                .size(28.dp)
                                                .background(MaterialTheme.colorScheme.primary, androidx.compose.foundation.shape.CircleShape)
                                        ) {
                                            Text(
                                                text = (index + 1).toString(),
                                                color = Color.White,
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }

                                        Spacer(modifier = Modifier.width(12.dp))

                                        var isEditingName by remember { mutableStateOf(false) }
                                        var nameText by remember { mutableStateOf(secName) }

                                        if (isEditingName) {
                                            OutlinedTextField(
                                                value = nameText,
                                                onValueChange = { nameText = it },
                                                singleLine = true,
                                                modifier = Modifier.weight(1f),
                                                colors = OutlinedTextFieldDefaults.colors(
                                                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                                                    unfocusedBorderColor = MaterialTheme.colorScheme.outline
                                                )
                                            )
                                            IconButton(onClick = {
                                                if (nameText.isNotBlank()) {
                                                    currentOutline = currentOutline.toMutableList().apply {
                                                        set(index, nameText)
                                                    }
                                                }
                                                isEditingName = false
                                            }) {
                                                Icon(
                                                    imageVector = Icons.Default.Check,
                                                    contentDescription = "Save Name",
                                                    tint = MaterialTheme.colorScheme.primary
                                                )
                                            }
                                        } else {
                                            Text(
                                                text = secName,
                                                fontSize = 13.sp,
                                                fontWeight = FontWeight.Medium,
                                                color = MaterialTheme.colorScheme.onSurface,
                                                modifier = Modifier
                                                    .weight(1f)
                                                    .clickable { isEditingName = true }
                                            )
                                            IconButton(onClick = { isEditingName = true }) {
                                                Icon(
                                                    imageVector = Icons.Default.Edit,
                                                    contentDescription = "Edit name",
                                                    tint = MaterialTheme.colorScheme.secondary,
                                                    modifier = Modifier.size(16.dp)
                                                )
                                            }
                                        }

                                        IconButton(
                                            onClick = {
                                                if (index > 0) {
                                                    currentOutline = currentOutline.toMutableList().apply {
                                                        val temp = get(index)
                                                        set(index, get(index - 1))
                                                        set(index - 1, temp)
                                                    }
                                                }
                                            },
                                            enabled = index > 0,
                                            modifier = Modifier.size(32.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.ArrowUpward,
                                                contentDescription = "Move Up",
                                                tint = if (index > 0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
                                            )
                                        }

                                        IconButton(
                                            onClick = {
                                                if (index < currentOutline.size - 1) {
                                                    currentOutline = currentOutline.toMutableList().apply {
                                                        val temp = get(index)
                                                        set(index, get(index + 1))
                                                        set(index + 1, temp)
                                                    }
                                                }
                                            },
                                            enabled = index < currentOutline.size - 1,
                                            modifier = Modifier.size(32.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.ArrowDownward,
                                                contentDescription = "Move Down",
                                                tint = if (index < currentOutline.size - 1) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
                                            )
                                        }

                                        IconButton(
                                            onClick = {
                                                currentOutline = currentOutline.toMutableList().apply {
                                                    removeAt(index)
                                                }
                                            },
                                            modifier = Modifier.size(32.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Delete,
                                                contentDescription = "Delete Section",
                                                tint = MaterialTheme.colorScheme.error
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        var customAddText by remember { mutableStateOf("") }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            OutlinedTextField(
                                value = customAddText,
                                onValueChange = { customAddText = it },
                                label = { Text("Add Custom Outlined Section") },
                                singleLine = true,
                                modifier = Modifier.weight(1f),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                                    unfocusedBorderColor = MaterialTheme.colorScheme.outline
                                )
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Button(
                                onClick = {
                                    if (customAddText.isNotBlank()) {
                                        currentOutline = currentOutline + customAddText.trim()
                                        customAddText = ""
                                    }
                                },
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.height(56.dp)
                            ) {
                                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Section")
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedButton(
                            onClick = { creationStep = 1 },
                            modifier = Modifier.testTag("back_button")
                        ) {
                            Text(text = "Back to Details")
                        }
                        Button(
                            onClick = {
                                val finalLevel = if (useCustomLevel) customLevel.ifBlank { "Custom Level" } else academicLevel
                                onSubmit(
                                    title,
                                    subject,
                                    studentName,
                                    rollNumber,
                                    schoolName,
                                    finalLevel,
                                    extraInstructions,
                                    currentOutline.ifEmpty { getDefaultOutlineForSubject(subject) }
                                )
                            },
                            enabled = currentOutline.isNotEmpty(),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                            modifier = Modifier.testTag("submit_button")
                        ) {
                            Text(text = "Generate Project with Outline")
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(imageVector = Icons.Default.AutoAwesome, contentDescription = "Spark")
                        }
                    }
                }
            }
        }
    }
}

data class ClichéSuggestion(
    val word: String,
    val suggestion: String
)

data class ToneAuditResult(
    val uniquenessScore: Int,
    val toneRating: String,
    val cliches: List<ClichéSuggestion>
)

fun performToneAudit(text: String): ToneAuditResult {
    val words = text.lowercase().split("\\s+".toRegex()).filter { it.isNotBlank() }
    if (words.isEmpty()) return ToneAuditResult(100, "N/A", emptyList())

    val academicKeywords = setOf("hypothesis", "methodology", "empirical", "consequently", "furthermore", "systematic", "analytical", "quantitative", "qualitative", "literature", "correlation", "significance")
    val aiCliches = setOf("delve", "testament", "tapestry", "moreover", "pioneering", "revolutionize", "seamlessly", "beacon", "treasure trove", "rich tapestry")

    val academicCount = words.count { it in academicKeywords }
    val flaggedCliches = aiCliches.filter { text.lowercase().contains(it) }.map {
        when (it) {
            "delve" -> ClichéSuggestion("delve", "investigate / examine")
            "testament" -> ClichéSuggestion("testament", "evidence / demonstration")
            "tapestry" -> ClichéSuggestion("tapestry", "complex structure / matrix")
            "moreover" -> ClichéSuggestion("moreover", "furthermore / in addition")
            "pioneering" -> ClichéSuggestion("pioneering", "groundbreaking / foundational")
            "revolutionize" -> ClichéSuggestion("revolutionize", "significantly alter / advance")
            "seamlessly" -> ClichéSuggestion("seamlessly", "efficiently / smoothly")
            "beacon" -> ClichéSuggestion("beacon", "exemplar / illustration")
            "treasure trove" -> ClichéSuggestion("treasure trove", "repository / database")
            "rich tapestry" -> ClichéSuggestion("rich tapestry", "diverse composition")
            else -> ClichéSuggestion(it, "scholarly alternative")
        }
    }

    val uniqueWords = words.toSet().size
    val baseUniqueness = if (words.isNotEmpty()) (uniqueWords.toFloat() / words.size.toFloat() * 100f) else 100f
    val finalUniquenessScore = maxOf(30, (baseUniqueness - (flaggedCliches.size * 5)).toInt())

    val toneRating = when {
        academicCount > 4 && flaggedCliches.isEmpty() -> "High Rigor (Scholarly)"
        academicCount > 2 -> "Academic (Balanced)"
        flaggedCliches.size > 2 -> "Informal / High AI Cliché"
        else -> "Standard Narrative"
    }

    return ToneAuditResult(
        uniquenessScore = finalUniquenessScore,
        toneRating = toneRating,
        cliches = flaggedCliches
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectWorkspaceScreen(
    project: AcademicProject,
    viewModel: ProjectViewModel,
    onBack: () -> Unit
) {
    val sections by viewModel.sections.collectAsState()
    val isGenerating by viewModel.isGenerating.collectAsState()
    val error by viewModel.generationError.collectAsState()
    val success by viewModel.generationSuccess.collectAsState()

    var activeSectionIndex by remember { mutableIntStateOf(1) }
    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current

    var showMetadataDialog by remember { mutableStateOf(false) }
    var editingDraftContent by remember { mutableStateOf("") }
    var onSpeechAppended: ((String) -> Unit)? by remember { mutableStateOf(null) }

    val speechRecognizerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val spokenText = result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.firstOrNull()
            if (spokenText != null) {
                onSpeechAppended?.invoke(spokenText)
            }
        }
    }

    // Set active section when new section is successfully generated
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    LaunchedEffect(sections.size) {
        if (sections.isNotEmpty()) {
            activeSectionIndex = sections.size
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = MaterialTheme.colorScheme.surface,
                modifier = Modifier.width(320.dp).fillMaxHeight()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.School,
                            contentDescription = "Project Sidebar",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(32.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Thesis Portfolio",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "Drafting & Quality Audits",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    androidx.compose.material3.HorizontalDivider(
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    val totalWordCount = sections.sumOf { sec ->
                        sec.content.split("\\s+".toRegex()).count { it.isNotBlank() }
                    }
                    Text(
                        text = "Live Draft Word Count Tracker",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f)
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "Current Draft Size",
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                                )
                                Text(
                                    text = "$totalWordCount Words",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            
                            val targetWords = 3000f
                            val progressFraction = (totalWordCount.toFloat() / targetWords).coerceIn(0f, 1f)
                            LinearProgressIndicator(
                                progress = { progressFraction },
                                color = MaterialTheme.colorScheme.primary,
                                trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                                modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp))
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Goal progress: ${(progressFraction * 100).toInt()}% of 3,000 word target",
                                fontSize = 10.sp,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Breakdown by Section",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        sections.forEach { sec ->
                            val wordCount = sec.content.split("\\s+".toRegex()).count { it.isNotBlank() }
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
                                    .padding(horizontal = 8.dp, vertical = 6.dp)
                            ) {
                                Text(
                                    text = sec.sectionTitle,
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    maxLines = 1,
                                    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
                                    modifier = Modifier.weight(1f)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "$wordCount words",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    androidx.compose.material3.HorizontalDivider(
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Originality & Tone Audit Tool",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Keyword uniqueness verification & scholarly style scanning.",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    val activeSectionObj = sections.find { it.sectionIndex == activeSectionIndex }
                    val activeText = activeSectionObj?.content ?: ""
                    val auditResult = performToneAudit(activeText)

                    if (activeText.isBlank()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                                .padding(12.dp)
                        ) {
                            Text(
                                text = "Generate this section to audit its academic tone and uniqueness.",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    } else {
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = Icons.Default.Spellcheck,
                                            contentDescription = "Uniqueness Gauge",
                                            tint = if (auditResult.uniquenessScore >= 80) Color(0xFF388E3C) else Color(0xFFF57C00),
                                            modifier = Modifier.size(18.dp)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = "Originality Index",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                    Text(
                                        text = "${auditResult.uniquenessScore}%",
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Black,
                                        color = if (auditResult.uniquenessScore >= 80) Color(0xFF388E3C) else Color(0xFFF57C00)
                                    )
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                LinearProgressIndicator(
                                    progress = { auditResult.uniquenessScore.toFloat() / 100f },
                                    color = if (auditResult.uniquenessScore >= 80) Color(0xFF388E3C) else Color(0xFFF57C00),
                                    trackColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.15f),
                                    modifier = Modifier.fillMaxWidth().height(6.dp).clip(RoundedCornerShape(3.dp))
                                )
                                
                                Spacer(modifier = Modifier.height(12.dp))
                                
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = "Academic Tone Style:",
                                        fontSize = 11.sp,
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                                    )
                                    Text(
                                        text = auditResult.toneRating,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }

                                if (auditResult.cliches.isNotEmpty()) {
                                    Spacer(modifier = Modifier.height(12.dp))
                                    androidx.compose.material3.HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = "AI-Cliché Warning & Suggestions",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFD32F2F)
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))
                                    auditResult.cliches.forEach { cliché ->
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(vertical = 4.dp)
                                                .background(Color(0xFFD32F2F).copy(alpha = 0.05f), RoundedCornerShape(4.dp))
                                                .padding(6.dp)
                                        ) {
                                            Text(
                                                text = "Flagged: \"${cliché.word}\"",
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.SemiBold,
                                                color = Color(0xFFD32F2F)
                                            )
                                            Text(
                                                text = "Alternative: Use ${cliché.suggestion}",
                                                fontSize = 10.sp,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                    }
                                } else {
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.background(Color(0xFF388E3C).copy(alpha = 0.08f), RoundedCornerShape(4.dp)).padding(6.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Done,
                                            contentDescription = "Rigor check passed",
                                            tint = Color(0xFF388E3C),
                                            modifier = Modifier.size(12.dp)
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = "Scholarly prose verification passed! No AI clichés detected.",
                                            fontSize = 9.sp,
                                            color = Color(0xFF388E3C)
                                        )
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(40.dp))
                }
            }
        },
        content = {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Column {
                                Text(
                                    text = project.title,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1
                                )
                                Text(
                                    text = "${project.subject} • ${project.academicLevel}",
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f)
                                )
                            }
                        },
                        navigationIcon = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                IconButton(onClick = onBack) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Back"
                                    )
                                }
                                IconButton(onClick = {
                                    scope.launch { drawerState.open() }
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Menu,
                                        contentDescription = "Open Sidebar Menu",
                                        tint = MaterialTheme.colorScheme.onPrimary
                                    )
                                }
                            }
                        },
                actions = {
                    IconButton(onClick = {
                        if (sections.isEmpty()) {
                            Toast.makeText(context, "No content generated yet.", Toast.LENGTH_SHORT).show()
                            return@IconButton
                        }
                        val fullDoc = buildString {
                            sections.forEach { sec ->
                                append("# ${sec.sectionTitle}\n\n")
                                append(sec.content)
                                append("\n\n---\n\n")
                            }
                        }
                        clipboardManager.setText(AnnotatedString(fullDoc))
                        Toast.makeText(context, "Full Project copied to clipboard!", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(imageVector = Icons.Default.ContentCopy, contentDescription = "Copy Full Doc")
                    }

                    IconButton(onClick = {
                        if (sections.isEmpty()) {
                            Toast.makeText(context, "No content generated yet.", Toast.LENGTH_SHORT).show()
                            return@IconButton
                        }
                        val fullDoc = buildString {
                            sections.forEach { sec ->
                                append("# ${sec.sectionTitle}\n\n")
                                append(sec.content)
                                append("\n\n---\n\n")
                            }
                        }
                        val sendIntent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, fullDoc)
                            type = "text/plain"
                        }
                        val shareIntent = Intent.createChooser(sendIntent, "Share Academic Project")
                        context.startActivity(shareIntent)
                    }) {
                        Icon(imageVector = Icons.Default.Share, contentDescription = "Share Full Doc")
                    }

                    IconButton(
                        onClick = {
                            if (sections.isEmpty()) {
                                Toast.makeText(context, "No content generated yet.", Toast.LENGTH_SHORT).show()
                                return@IconButton
                            }
                            PdfExporter.exportProjectToPdf(context, project, sections)
                        },
                        modifier = Modifier.testTag("pdf_export_button")
                    ) {
                        Icon(imageVector = Icons.Default.PictureAsPdf, contentDescription = "Export to PDF")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            val totalSectionsCount = maxOf(sections.size, 1)
            val navigationProgress = activeSectionIndex.toFloat() / totalSectionsCount.toFloat()
            Column(modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface)) {
                LinearProgressIndicator(
                    progress = { navigationProgress.coerceIn(0f, 1f) },
                    modifier = Modifier.fillMaxWidth().height(6.dp),
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                )
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Viewing: ${getSectionLabel(activeSectionIndex)}",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Navigation Progress: ${(navigationProgress * 100).toInt()}%",
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
            }

            // Project Section Timeline Navigator
            SectionTimelineBar(
                sectionsGenerated = sections.size,
                activeSectionIndex = activeSectionIndex,
                onSectionClick = { activeSectionIndex = it }
            )

            // Main Content Area
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                if (sections.isEmpty()) {
                    // Empty state (no sections generated yet)
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(32.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Description,
                            contentDescription = "Document Empty",
                            tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f),
                            modifier = Modifier.size(80.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Project Initialized",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "We will now generate the Cover Page, Certificate, Acknowledgement, Abstract, Table of Contents, and Section 1: Introduction.",
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Button(
                            onClick = { viewModel.generateNextSection() },
                            enabled = !isGenerating,
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                        ) {
                            Text(text = "Generate Step 1")
                        }
                    }
                } else {
                    // Display selected section content
                    val currentSection = sections.find { it.sectionIndex == activeSectionIndex }
                    if (currentSection != null) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState())
                                .padding(16.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = currentSection.sectionTitle,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.weight(1f)
                                )
                                Row {
                                    IconButton(onClick = {
                                        clipboardManager.setText(AnnotatedString(currentSection.content))
                                        Toast.makeText(context, "Copied section to clipboard", Toast.LENGTH_SHORT).show()
                                    }) {
                                        Icon(
                                            imageVector = Icons.Default.ContentCopy,
                                            contentDescription = "Copy Section",
                                            tint = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            FormattedProjectText(text = currentSection.content)

                            Spacer(modifier = Modifier.height(24.dp))

                            // 1. Voice Aloud TTS Reader
                            TextToSpeechPlayer(content = currentSection.content)

                            // 2. Interactive Labs and Simulation Charts
                            InteractiveLabCharts(
                                projectTopic = project.title,
                                subject = project.subject
                            )

                            // 3. Power Code lab generator
                            PowerCodeLab(
                                viewModel = viewModel,
                                projectTitle = project.title,
                                subject = project.subject
                            )

                            // 4. AI Prompt Designer & Slide illustration blueprint
                            AIDiagramDesigner(
                                viewModel = viewModel,
                                projectTitle = project.title,
                                subject = project.subject
                            )

                            Spacer(modifier = Modifier.height(24.dp))

                            // 5. Academic Citation Helper & Bibliography Tracker
                            AcademicCitationHelper(
                                projectTopic = project.title,
                                subject = project.subject
                            )

                            Spacer(modifier = Modifier.height(40.dp))
                        }
                    } else {
                        // Section is not yet generated
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(32.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = Icons.Default.Error,
                                contentDescription = "Unfinished Section",
                                tint = MaterialTheme.colorScheme.outline,
                                modifier = Modifier.size(64.dp)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Section Not Yet Generated",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            val prevSectTitle = getSectionLabel(activeSectionIndex - 1)
                            Text(
                                text = "You must generate the previous sections first. Please select $prevSectTitle to proceed.",
                                fontSize = 13.sp,
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                // Loading Overlay
                if (isGenerating) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.4f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Card(
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            modifier = Modifier.padding(32.dp)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(24.dp)
                            ) {
                                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = "Drafting with Academic Rigor...",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Formulating structures, theories, experimental design schemas, and compiling observation templates...",
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }

            // Bottom Action Drawer (Continuation Interface)
            BottomActionBar(
                sectionsCount = sections.size,
                isGenerating = isGenerating,
                error = error,
                onGenerateNext = { viewModel.generateNextSection() },
                onClearError = { viewModel.clearError() }
            )
        }
    }
}
)
}

@Composable
fun SectionTimelineBar(
    sectionsGenerated: Int,
    activeSectionIndex: Int,
    onSectionClick: (Int) -> Unit
) {
    Surface(
        shadowElevation = 4.dp,
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Project Outline",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "$sectionsGenerated / 10 Sections Generated",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(bottom = 8.dp),
                verticalArrangement = Arrangement.Center
            ) {
                // We use horizontal row in LazyColumn as workaround or simpler
            }
            // To make it fully scrollable horizontally:
            val scrollState = rememberScrollState()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp, start = 12.dp, end = 12.dp)
                    .verticalScroll(rememberScrollState()) // actually horizontal is better
                    // let's do simple Row with horizontalScroll
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                ) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Row(modifier = Modifier.padding(horizontal = 4.dp)) {
                            (1..10).forEach { index ->
                                val isGenerated = index <= sectionsGenerated
                                val isActive = index == activeSectionIndex

                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .padding(horizontal = 4.dp)
                                        .clip(RoundedCornerShape(16.dp))
                                        .background(
                                            when {
                                                isActive -> MaterialTheme.colorScheme.primary
                                                isGenerated -> MaterialTheme.colorScheme.primaryContainer
                                                else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.12f)
                                            }
                                        )
                                        .clickable { onSectionClick(index) }
                                        .padding(horizontal = 12.dp, vertical = 6.dp)
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        if (isGenerated) {
                                            Icon(
                                                imageVector = Icons.Default.CheckCircle,
                                                contentDescription = "Generated",
                                                tint = if (isActive) Color.White else MaterialTheme.colorScheme.primary,
                                                modifier = Modifier.size(12.dp)
                                            )
                                            Spacer(modifier = Modifier.width(4.dp))
                                        }
                                        Text(
                                            text = "Sec $index",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = when {
                                                isActive -> Color.White
                                                isGenerated -> MaterialTheme.colorScheme.onPrimaryContainer
                                                else -> MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomActionBar(
    sectionsCount: Int,
    isGenerating: Boolean,
    error: String?,
    onGenerateNext: () -> Unit,
    onClearError: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Error banner if any
            AnimatedVisibility(visible = error != null) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.errorContainer)
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Error,
                        contentDescription = "Error",
                        tint = MaterialTheme.colorScheme.error
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = error ?: "",
                        color = MaterialTheme.colorScheme.onErrorContainer,
                        fontSize = 12.sp,
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(onClick = onClearError) {
                        Icon(
                            imageVector = Icons.Default.Add, // Close symbol
                            contentDescription = "Dismiss",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
            }

            if (sectionsCount < 10) {
                val nextIndex = sectionsCount + 1
                val nextLabel = getSectionLabel(nextIndex)

                Text(
                    text = "👉 Type CONTINUE or Tap below for next section",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    letterSpacing = 0.5.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Next: $nextLabel",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Step $nextIndex of 10 in standard academic curriculum.",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                        )
                    }
                    Button(
                        onClick = onGenerateNext,
                        enabled = !isGenerating,
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "CONTINUE", fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(imageVector = Icons.Default.NavigateNext, contentDescription = "Continue")
                        }
                    }
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Complete",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Academic Project Draft Complete!",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}

private fun getSectionLabel(index: Int): String {
    return when (index) {
        1 -> "Front Matter & Sec 1: Introduction"
        2 -> "Sec 2: Background and Theory"
        3 -> "Sec 3: Literature Review"
        4 -> "Sec 4: Experimental Design"
        5 -> "Sec 5: Observations and Data"
        6 -> "Sec 6: Analysis and Discussion"
        7 -> "Sec 7: Applications & Relevance"
        8 -> "Sec 8: Conclusion"
        9 -> "Sec 9: Bibliography"
        10 -> "Sec 10: Viva Questions"
        else -> "Section $index"
    }
}

@Composable
fun FormattedProjectText(text: String) {
    val lines = text.split("\n")
    var inTable = false
    var tableHeaders = listOf<String>()
    var tableRows = mutableListOf<List<String>>()

    var inCodeBlock = false
    val codeBlockLines = remember { mutableListOf<String>() }
    var codeBlockLanguage = ""

    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxWidth()) {
        lines.forEach { line ->
            val trimmed = line.trim()

            if (trimmed.startsWith("```")) {
                if (inCodeBlock) {
                    val codeContent = codeBlockLines.joinToString("\n")
                    CodeBlockContainer(
                        code = codeContent,
                        language = codeBlockLanguage,
                        onCopy = {
                            clipboardManager.setText(AnnotatedString(codeContent))
                            Toast.makeText(context, "Code copied to clipboard", Toast.LENGTH_SHORT).show()
                        }
                    )
                    codeBlockLines.clear()
                    inCodeBlock = false
                } else {
                    inCodeBlock = true
                    codeBlockLanguage = trimmed.removePrefix("```").trim()
                }
            } else if (inCodeBlock) {
                codeBlockLines.add(line)
            } else if (trimmed.startsWith("|")) {
                inTable = true
                val parts = trimmed.split("|").map { it.trim() }.filter { it.isNotEmpty() }
                if (trimmed.contains("---") || trimmed.contains("-|-")) {
                    // separator, ignore
                } else if (tableHeaders.isEmpty()) {
                    tableHeaders = parts
                } else {
                    tableRows.add(parts)
                }
            } else {
                if (inTable) {
                    RenderMarkdownTable(headers = tableHeaders, rows = tableRows)
                    inTable = false
                    tableHeaders = listOf()
                    tableRows = mutableListOf()
                }

                if (trimmed.startsWith("###")) {
                    Text(
                        text = trimmed.removePrefix("###").trim(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                } else if (trimmed.startsWith("##")) {
                    Text(
                        text = trimmed.removePrefix("##").trim(),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )
                } else if (trimmed.startsWith("#")) {
                    Text(
                        text = trimmed.removePrefix("#").trim(),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(vertical = 12.dp)
                    )
                } else if (trimmed.startsWith("-") || trimmed.startsWith("*")) {
                    Row(modifier = Modifier.padding(start = 8.dp).padding(vertical = 2.dp)) {
                        Text(text = "• ", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                        Text(
                            text = parseBoldText(trimmed.substring(1).trim()),
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                } else if (trimmed.isNotEmpty()) {
                    Text(
                        text = parseBoldText(trimmed),
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                } else {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
        if (inTable) {
            RenderMarkdownTable(headers = tableHeaders, rows = tableRows)
        }
        if (inCodeBlock && codeBlockLines.isNotEmpty()) {
            val codeContent = codeBlockLines.joinToString("\n")
            CodeBlockContainer(
                code = codeContent,
                language = codeBlockLanguage,
                onCopy = {
                    clipboardManager.setText(AnnotatedString(codeContent))
                    Toast.makeText(context, "Code copied to clipboard", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}

fun getSyntaxHighlightedCode(code: String, language: String): AnnotatedString {
    val builder = AnnotatedString.Builder(code)
    
    val keywordColor = Color(0xFFE5C07B) // Peach/Yellow
    val functionColor = Color(0xFF61AFEF) // Blue
    val stringColor = Color(0xFF98C379) // Green
    val commentColor = Color(0xFF7F848E) // Gray (Italic)
    val numberColor = Color(0xFFD19A66) // Orange
    val annotationColor = Color(0xFFE06C75) // Red/Pink

    val keywords = setOf(
        "fun", "val", "var", "import", "package", "class", "object", "interface", "return", "if", "else", "for", "while",
        "def", "from", "as", "in", "is", "not", "and", "or", "try", "except", "finally", "with", "lambda",
        "public", "private", "protected", "static", "final", "void", "int", "float", "double", "char", "boolean", "string",
        "using", "namespace", "struct", "template", "typename", "const", "let", "function", "select", "insert",
        "update", "delete", "from", "where", "join", "on", "group", "by", "having", "order", "into", "values", "null", "true", "false"
    )

    val commentRegex = Regex("(//.*)|(#.*)|(/\\*.*?\\*/)")
    val stringRegex = Regex("(\"[^\"]*\")|('[^']*')")
    val numberRegex = Regex("\\b\\d+(\\.\\d+)?\\b")
    val keywordRegex = Regex("\\b(" + keywords.joinToString("|") + ")\\b")
    val annotationRegex = Regex("@[a-zA-Z0-9_]+")
    val functionCallRegex = Regex("\\b([a-zA-Z0-9_]+)(?=\\s*\\()")

    val lines = code.split("\n")
    var currentOffset = 0
    
    lines.forEach { line ->
        val lineStart = currentOffset
        val commentMatch = commentRegex.find(line)
        if (commentMatch != null) {
            val start = lineStart + commentMatch.range.first
            val end = lineStart + commentMatch.range.last + 1
            builder.addStyle(
                SpanStyle(color = commentColor, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic),
                start, end
            )
            val nonCommentLine = line.substring(0, commentMatch.range.first)
            highlightLineContent(builder, nonCommentLine, lineStart, stringRegex, stringColor, keywordRegex, keywordColor, numberRegex, numberColor, annotationRegex, annotationColor, functionCallRegex, functionColor)
        } else {
            highlightLineContent(builder, line, lineStart, stringRegex, stringColor, keywordRegex, keywordColor, numberRegex, numberColor, annotationRegex, annotationColor, functionCallRegex, functionColor)
        }
        currentOffset += line.length + 1
    }
    
    return builder.toAnnotatedString()
}

private fun highlightLineContent(
    builder: AnnotatedString.Builder,
    line: String,
    lineStart: Int,
    stringRegex: Regex,
    stringColor: Color,
    keywordRegex: Regex,
    keywordColor: Color,
    numberRegex: Regex,
    numberColor: Color,
    annotationRegex: Regex,
    annotationColor: Color,
    functionCallRegex: Regex,
    functionColor: Color
) {
    stringRegex.findAll(line).forEach { match ->
        val start = lineStart + match.range.first
        val end = lineStart + match.range.last + 1
        builder.addStyle(SpanStyle(color = stringColor), start, end)
    }

    keywordRegex.findAll(line).forEach { match ->
        val start = lineStart + match.range.first
        val end = lineStart + match.range.last + 1
        builder.addStyle(SpanStyle(color = keywordColor, fontWeight = FontWeight.Bold), start, end)
    }

    numberRegex.findAll(line).forEach { match ->
        val start = lineStart + match.range.first
        val end = lineStart + match.range.last + 1
        builder.addStyle(SpanStyle(color = numberColor), start, end)
    }

    annotationRegex.findAll(line).forEach { match ->
        val start = lineStart + match.range.first
        val end = lineStart + match.range.last + 1
        builder.addStyle(SpanStyle(color = annotationColor), start, end)
    }

    functionCallRegex.findAll(line).forEach { match ->
        val start = lineStart + match.range.first
        val end = lineStart + match.range.last + 1
        builder.addStyle(SpanStyle(color = functionColor), start, end)
    }
}

@Composable
fun CodeBlockContainer(code: String, language: String, onCopy: () -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1E1E1E)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF2D2D2D))
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = language.ifBlank { "Code Block" }.uppercase(),
                    color = Color(0xFFCCCCCC),
                    fontSize = 11.sp,
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                    fontWeight = FontWeight.Bold
                )
                IconButton(
                    onClick = onCopy,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ContentCopy,
                        contentDescription = "Copy Code",
                        tint = Color(0xFFCCCCCC),
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
            Text(
                text = getSyntaxHighlightedCode(code, language),
                fontSize = 12.sp,
                fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                lineHeight = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .horizontalScroll(rememberScrollState())
            )
        }
    }
}

@Composable
fun RenderMarkdownTable(headers: List<String>, rows: List<List<String>>) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            // Headers
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                    .padding(8.dp)
            ) {
                headers.forEach { header ->
                    Text(
                        text = header,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                }
            }

            // Rows
            rows.forEachIndexed { index, row ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            if (index % 2 == 0) Color.Transparent
                            else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.03f)
                        )
                        .padding(8.dp)
                ) {
                    row.forEach { cell ->
                        Text(
                            text = cell,
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

fun parseBoldText(text: String): AnnotatedString {
    val builder = AnnotatedString.Builder()
    var index = 0
    while (index < text.length) {
        val nextBold = text.indexOf("**", index)
        if (nextBold == -1) {
            builder.append(text.substring(index))
            break
        }
        builder.append(text.substring(index, nextBold))
        val endBold = text.indexOf("**", nextBold + 2)
        if (endBold == -1) {
            builder.append(text.substring(nextBold))
            break
        }
        builder.pushStyle(androidx.compose.ui.text.SpanStyle(fontWeight = FontWeight.Bold))
        builder.append(text.substring(nextBold + 2, endBold))
        builder.pop()
        index = endBold + 2
    }
    return builder.toAnnotatedString()
}

@Composable
private fun varShowCreateDialog() = remember { mutableStateOf(false) }

@Composable
fun TextToSpeechPlayer(content: String) {
    val context = LocalContext.current
    var tts by remember { mutableStateOf<TextToSpeech?>(null) }
    var isSpeaking by remember { mutableStateOf(false) }
    var speed by remember { mutableStateOf(1.0f) }
    var isReady by remember { mutableStateOf(false) }

    DisposableEffect(context) {
        val ttsInstance = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                isReady = true
            }
        }
        tts = ttsInstance
        onDispose {
            ttsInstance.stop()
            ttsInstance.shutdown()
        }
    }

    LaunchedEffect(isSpeaking) {
        if (isSpeaking && tts != null) {
            tts?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                override fun onStart(utteranceId: String?) { isSpeaking = true }
                override fun onDone(utteranceId: String?) { isSpeaking = false }
                @Deprecated("Deprecated in Java")
                override fun onError(utteranceId: String?) { isSpeaking = false }
            })
        }
    }

    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.25f)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .testTag("tts_player_card")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = if (isSpeaking) Icons.Default.VolumeUp else Icons.Default.VolumeMute,
                    contentDescription = "Voice",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = if (isSpeaking) "Speaking Project Content..." else "Syllabus Voice Reader",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                    Text(
                        text = "Listen to content for Viva Voce and verbal study preparation.",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = {
                        if (tts != null && isReady) {
                            if (isSpeaking) {
                                tts?.stop()
                                isSpeaking = false
                            } else {
                                tts?.setSpeechRate(speed)
                                val cleanText = content
                                    .replace("#", "")
                                    .replace("*", "")
                                    .replace("|", " ")
                                    .replace("-", " ")
                                val chunks = cleanText.split(". ")
                                var utteranceId = 0
                                chunks.forEach { chunk ->
                                    if (chunk.isNotBlank()) {
                                        tts?.speak(
                                            chunk,
                                            TextToSpeech.QUEUE_ADD,
                                            Bundle().apply {
                                                putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "chunk_${utteranceId++}")
                                            },
                                            "chunk_${utteranceId}"
                                        )
                                    }
                                }
                                isSpeaking = true
                            }
                        } else {
                            Toast.makeText(context, "Voice reader engine starting, please wait...", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                        .size(40.dp)
                        .testTag("tts_play_button")
                ) {
                    Icon(
                        imageVector = if (isSpeaking) Icons.Default.Stop else Icons.Default.PlayArrow,
                        contentDescription = "Play/Stop",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(22.dp)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                if (isSpeaking) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(3.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        val infiniteTransition = rememberInfiniteTransition(label = "soundwave")
                        repeat(6) { index ->
                            val delay = index * 120
                            val heightScale by infiniteTransition.animateFloat(
                                initialValue = 0.15f,
                                targetValue = 1.0f,
                                animationSpec = infiniteRepeatable(
                                    animation = tween(durationMillis = 450, delayMillis = delay, easing = FastOutSlowInEasing),
                                    repeatMode = RepeatMode.Reverse
                                ),
                                label = "bar_$index"
                            )
                            Box(
                                modifier = Modifier
                                    .width(3.dp)
                                    .height(24.dp * heightScale)
                                    .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(2.dp))
                            )
                        }
                    }
                } else {
                    Box(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Audio engine ready.",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.5f)
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    listOf(0.75f, 1.0f, 1.25f, 1.5f).forEach { rate ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(
                                    if (speed == rate) MaterialTheme.colorScheme.primary
                                    else MaterialTheme.colorScheme.surfaceVariant
                                )
                                .clickable {
                                    speed = rate
                                    if (isSpeaking && tts != null) {
                                        tts?.setSpeechRate(rate)
                                    }
                                }
                                .padding(horizontal = 6.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "${rate}x",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (speed == rate) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InteractiveLabCharts(projectTopic: String, subject: String) {
    var selectedTab by remember { mutableStateOf("Bar Chart") }
    var val1 by remember { mutableStateOf(65f) }
    var val2 by remember { mutableStateOf(85f) }
    var val3 by remember { mutableStateOf(45f) }
    var val4 by remember { mutableStateOf(70f) }

    val labels = when {
        subject.lowercase().contains("phys") -> listOf("Trial A", "Trial B", "Trial C", "Control")
        subject.lowercase().contains("chem") -> listOf("Reactant", "Product", "Catalyst", "Inhibitor")
        subject.lowercase().contains("comp") || subject.lowercase().contains("soft") -> listOf("CPU Use", "RAM Use", "Latency", "I/O Speed")
        subject.lowercase().contains("math") -> listOf("Sample X", "Sample Y", "Sample Z", "Expected")
        else -> listOf("Group 1", "Group 2", "Group 3", "Average")
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .testTag("interactive_charts_card")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "📈 Interactive Lab Data Diagram & Graphs",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Simulate scientific observations and render custom styled data charts.",
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                listOf("Bar Chart", "Line Chart", "Pie Chart", "Flowchart").forEach { tab ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                if (selectedTab == tab) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.surfaceVariant
                            )
                            .clickable { selectedTab = tab }
                            .padding(vertical = 6.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = tab,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (selectedTab == tab) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(12.dp)
            ) {
                val colorScheme = MaterialTheme.colorScheme
                val gridColor = colorScheme.outline.copy(alpha = 0.15f)

                Canvas(modifier = Modifier.fillMaxSize()) {
                    val width = size.width
                    val height = size.height

                    when (selectedTab) {
                        "Bar Chart" -> {
                            val gridCount = 5
                            for (i in 0..gridCount) {
                                val y = (height / gridCount) * i
                                drawLine(
                                    color = gridColor,
                                    start = Offset(40f, y),
                                    end = Offset(width, y),
                                    strokeWidth = 2f
                                )
                            }

                            val barCount = 4
                            val spacing = (width - 60f) / barCount
                            val barVals = listOf(val1, val2, val3, val4)
                            val barColors = listOf(
                                colorScheme.primary,
                                colorScheme.secondary,
                                colorScheme.tertiary,
                                colorScheme.error
                            )

                            barVals.forEachIndexed { idx, valPercent ->
                                val x = 50f + idx * spacing
                                val barHeight = (height - 40f) * (valPercent / 100f)
                                val barWidth = spacing * 0.6f

                                drawRoundRect(
                                    color = barColors[idx],
                                    topLeft = Offset(x, height - 20f - barHeight),
                                    size = Size(barWidth, barHeight),
                                    cornerRadius = CornerRadius(8f, 8f)
                                )
                            }
                        }
                        "Line Chart" -> {
                            drawLine(color = gridColor, start = Offset(40f, 0f), end = Offset(40f, height - 20f), strokeWidth = 3f)
                            drawLine(color = gridColor, start = Offset(40f, height - 20f), end = Offset(width, height - 20f), strokeWidth = 3f)

                            val points = listOf(
                                Offset(60f, height - 20f - (height - 40f) * (val1 / 100f)),
                                Offset(150f, height - 20f - (height - 40f) * (val2 / 100f)),
                                Offset(240f, height - 20f - (height - 40f) * (val3 / 100f)),
                                Offset(330f, height - 20f - (height - 40f) * (val4 / 100f))
                            )

                            val path = Path().apply {
                                moveTo(points[0].x, points[0].y)
                                for (i in 1 until points.size) {
                                    lineTo(points[i].x, points[i].y)
                                }
                            }
                            drawPath(path = path, color = colorScheme.primary, style = Stroke(width = 6f, cap = StrokeCap.Round))

                            points.forEachIndexed { i, pt ->
                                drawCircle(color = colorScheme.secondary, radius = 10f, center = pt)
                                drawCircle(color = colorScheme.background, radius = 5f, center = pt)
                            }
                        }
                        "Pie Chart" -> {
                            val total = val1 + val2 + val3 + val4
                            var startAngle = 0f
                            val vals = listOf(val1, val2, val3, val4)
                            val colors = listOf(
                                colorScheme.primary,
                                colorScheme.secondary,
                                colorScheme.tertiary,
                                colorScheme.primaryContainer
                            )

                            vals.forEachIndexed { idx, value ->
                                val sweepAngle = (value / total) * 360f
                                drawArc(
                                    color = colors[idx],
                                    startAngle = startAngle,
                                    sweepAngle = sweepAngle,
                                    useCenter = true,
                                    topLeft = Offset((width - 150f) / 2, (height - 150f) / 2),
                                    size = Size(150f, 150f)
                                )
                                startAngle += sweepAngle
                            }

                            drawCircle(
                                color = colorScheme.surface,
                                radius = 45f,
                                center = Offset(width / 2, height / 2)
                            )
                        }
                        "Flowchart" -> {
                            val nodeWidth = 100f
                            val nodeHeight = 50f
                            val spacing = (width - (nodeWidth * 3)) / 4

                            drawRoundRect(
                                color = colorScheme.primary,
                                topLeft = Offset(spacing, (height - nodeHeight)/2),
                                size = Size(nodeWidth, nodeHeight),
                                cornerRadius = CornerRadius(10f, 10f)
                            )

                            drawLine(
                                color = colorScheme.outline,
                                start = Offset(spacing + nodeWidth, height/2),
                                end = Offset(spacing * 2 + nodeWidth, height/2),
                                strokeWidth = 4f
                            )

                            drawRoundRect(
                                color = colorScheme.secondary,
                                topLeft = Offset(spacing * 2 + nodeWidth, (height - nodeHeight)/2),
                                size = Size(nodeWidth, nodeHeight),
                                cornerRadius = CornerRadius(10f, 10f)
                            )

                            drawLine(
                                color = colorScheme.outline,
                                start = Offset(spacing * 2 + nodeWidth * 2, height/2),
                                end = Offset(spacing * 3 + nodeWidth * 2, height/2),
                                strokeWidth = 4f
                            )

                            drawRoundRect(
                                color = colorScheme.tertiary,
                                topLeft = Offset(spacing * 3 + nodeWidth * 2, (height - nodeHeight)/2),
                                size = Size(nodeWidth, nodeHeight),
                                cornerRadius = CornerRadius(10f, 10f)
                            )
                        }
                    }
                }

                if (selectedTab == "Bar Chart" || selectedTab == "Line Chart") {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .padding(horizontal = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        labels.forEach { label ->
                            Text(
                                text = label,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                            )
                        }
                    }
                } else if (selectedTab == "Flowchart") {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center)
                            .padding(horizontal = 24.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("INPUT", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimary)
                        Text("PROCESS", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSecondary)
                        Text("OUTPUT", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onTertiary)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (selectedTab != "Flowchart") {
                Text(
                    text = "Tune Metrics / Custom Trial Numbers:",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(labels[0], fontSize = 11.sp, modifier = Modifier.width(80.dp))
                        Slider(
                            value = val1,
                            onValueChange = { val1 = it },
                            valueRange = 10f..100f,
                            modifier = Modifier.weight(1f)
                        )
                        Text("${val1.toInt()}%", fontSize = 11.sp, fontWeight = FontWeight.Bold, modifier = Modifier.width(35.dp))
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(labels[1], fontSize = 11.sp, modifier = Modifier.width(80.dp))
                        Slider(
                            value = val2,
                            onValueChange = { val2 = it },
                            valueRange = 10f..100f,
                            modifier = Modifier.weight(1f)
                        )
                        Text("${val2.toInt()}%", fontSize = 11.sp, fontWeight = FontWeight.Bold, modifier = Modifier.width(35.dp))
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(labels[2], fontSize = 11.sp, modifier = Modifier.width(80.dp))
                        Slider(
                            value = val3,
                            onValueChange = { val3 = it },
                            valueRange = 10f..100f,
                            modifier = Modifier.weight(1f)
                        )
                        Text("${val3.toInt()}%", fontSize = 11.sp, fontWeight = FontWeight.Bold, modifier = Modifier.width(35.dp))
                    }
                }
            } else {
                Text(
                    text = "System flowchart diagrams illustrate structural dependencies beautifully in reports.",
                    fontSize = 11.sp,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                )
            }
        }
    }
}

@Composable
fun PowerCodeLab(
    viewModel: ProjectViewModel,
    projectTitle: String,
    subject: String
) {
    val codeOutput by viewModel.codeOutput.collectAsState()
    val isGenerating by viewModel.isGeneratingCode.collectAsState()
    val error by viewModel.codeError.collectAsState()
    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current

    var selectedLanguage by remember { mutableStateOf("Python") }
    val languages = listOf("Python", "C++", "SQL Schema", "Java", "HTML/JS")

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.15f)),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .testTag("power_code_lab_card")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Code,
                    contentDescription = "Code icon",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "💻 Power Code Lab & Script Builder",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            Text(
                text = "Generate and compile syllabus-compliant lab coding assignments with comments.",
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                languages.forEach { lang ->
                    val isSel = selectedLanguage == lang
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                if (isSel) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.surfaceVariant
                            )
                            .clickable { selectedLanguage = lang }
                            .padding(horizontal = 8.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = lang,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isSel) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (codeOutput == null) {
                Button(
                    onClick = { viewModel.generateLabCode(projectTitle, selectedLanguage) },
                    enabled = !isGenerating,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (isGenerating) {
                        CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Compiling Script...")
                    } else {
                        Text("Generate $selectedLanguage Lab Script")
                    }
                }
            } else {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                MaterialTheme.colorScheme.primaryContainer,
                                RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                            )
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "$selectedLanguage Source Code",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            IconButton(
                                onClick = {
                                    clipboardManager.setText(AnnotatedString(codeOutput!!))
                                    Toast.makeText(context, "Code copied!", Toast.LENGTH_SHORT).show()
                                },
                                modifier = Modifier.size(30.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ContentCopy,
                                    contentDescription = "Copy Code",
                                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                            IconButton(
                                onClick = { viewModel.clearLabCode() },
                                modifier = Modifier.size(30.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Refresh,
                                    contentDescription = "Clear Code",
                                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Color(0xFF1E1E1E),
                                RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
                            )
                            .padding(12.dp)
                    ) {
                        Row {
                            val codeLines = codeOutput!!.split("\n")
                            Column(modifier = Modifier.width(30.dp)) {
                                codeLines.forEachIndexed { idx, _ ->
                                    Text(
                                        text = "${idx + 1}",
                                        fontFamily = FontFamily.Monospace,
                                        fontSize = 11.sp,
                                        color = Color.Gray,
                                        textAlign = TextAlign.End,
                                        modifier = Modifier.fillMaxWidth().padding(end = 6.dp)
                                    )
                                }
                            }

                            Column(modifier = Modifier.weight(1f)) {
                                codeLines.forEach { line ->
                                    Text(
                                        text = line,
                                        fontFamily = FontFamily.Monospace,
                                        fontSize = 11.sp,
                                        color = if (line.trim().startsWith("#") || line.trim().startsWith("//")) Color(0xFF6A9955) else Color(0xFF9CDCFE),
                                        lineHeight = 16.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }

            if (error != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(error!!, color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun AIDiagramDesigner(
    viewModel: ProjectViewModel,
    projectTitle: String,
    subject: String
) {
    val schemaOutput by viewModel.diagramSchemaOutput.collectAsState()
    val isGenerating by viewModel.isGeneratingDiagramSchema.collectAsState()
    val error by viewModel.diagramSchemaError.collectAsState()
    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.15f)),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary.copy(alpha = 0.2f)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .testTag("ai_diagram_designer_card")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Palette,
                    contentDescription = "Diagram icon",
                    tint = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "🎨 AI Slide Blueprint & Prompt Designer",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
            }
            Text(
                text = "Generate high-quality manual drawings guides and ready-to-copy AI slide prompts.",
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onTertiaryContainer.copy(alpha = 0.7f)
            )

            Spacer(modifier = Modifier.height(12.dp))

            if (schemaOutput == null) {
                Button(
                    onClick = { viewModel.generateDiagramSchema(projectTitle, subject) },
                    enabled = !isGenerating,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (isGenerating) {
                        CircularProgressIndicator(color = MaterialTheme.colorScheme.onTertiary, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Synthesizing Blueprint...")
                    } else {
                        Text("Generate Photo Prompt & Guide")
                    }
                }
            } else {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Drafting Guide Ready",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            IconButton(
                                onClick = {
                                    clipboardManager.setText(AnnotatedString(schemaOutput!!))
                                    Toast.makeText(context, "Full guide copied!", Toast.LENGTH_SHORT).show()
                                },
                                modifier = Modifier.size(30.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ContentCopy,
                                    contentDescription = "Copy Prompt",
                                    tint = MaterialTheme.colorScheme.tertiary,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                            IconButton(
                                onClick = { viewModel.clearDiagramSchema() },
                                modifier = Modifier.size(30.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Refresh,
                                    contentDescription = "Clear Guide",
                                    tint = MaterialTheme.colorScheme.tertiary,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                MaterialTheme.colorScheme.surface,
                                RoundedCornerShape(8.dp)
                            )
                            .padding(12.dp)
                    ) {
                        Text(
                            text = schemaOutput!!,
                            fontSize = 12.sp,
                            lineHeight = 18.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            if (error != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(error!!, color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
            }
        }
    }
}

// ==========================================
// ACADEMIC CITATION HELPER & BIBLIOGRAPHY
// ==========================================

data class AcademicSource(
    val id: String,
    val type: String, // "Book", "Journal Article", "Website"
    val authors: String,
    val title: String,
    val container: String, // Publisher for Book, Journal Name for Article, Site Name for Website
    val year: String,
    val extra: String = "" // Volume/Issue for Journal, URL for Website
)

@Composable
fun AcademicCitationHelper(projectTopic: String, subject: String) {
    var selectedStyle by remember { mutableStateOf("APA") }
    var sources by remember {
        mutableStateOf(
            listOf(
                AcademicSource(
                    id = "source1",
                    type = "Book",
                    authors = "Carter, Alistair",
                    title = "Modern Research in $projectTopic",
                    container = "Academic Press",
                    year = "2024"
                ),
                AcademicSource(
                    id = "source2",
                    type = "Journal Article",
                    authors = "Vance, John, and Sarah Lee",
                    title = "A Comprehensive Scientific Analysis of $projectTopic",
                    container = "International Journal of $subject",
                    year = "2025",
                    extra = "vol. 14, no. 2, pp. 112-128"
                ),
                AcademicSource(
                    id = "source3",
                    type = "Website",
                    authors = "National Science Foundation",
                    title = "Understanding $projectTopic and Its Real-world Applications",
                    container = "National Science Foundation Portal",
                    year = "2026",
                    extra = "https://nsf.gov/topics/${projectTopic.lowercase().replace(" ", "-").replace("(", "").replace(")", "")}"
                )
            )
        )
    }

    var showAddForm by remember { mutableStateOf(false) }
    var newType by remember { mutableStateOf("Book") }
    var newAuthors by remember { mutableStateOf("") }
    var newTitle by remember { mutableStateOf("") }
    var newContainer by remember { mutableStateOf("Publisher") }
    var newYear by remember { mutableStateOf("") }
    var newExtra by remember { mutableStateOf("") }

    // Citation builder state
    var selectedSourceId by remember { mutableStateOf(sources.firstOrNull()?.id ?: "") }
    var citationPage by remember { mutableStateOf("") }
    var passageText by remember { mutableStateOf("") }

    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current

    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("academic_citation_card")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header Title
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Bookmark,
                    contentDescription = "Citation Helper",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Academic Reference & Citation Assistant",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Style Selector Tab
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf("APA", "MLA").forEach { style ->
                    Button(
                        onClick = { selectedStyle = style },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedStyle == style) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant
                        ),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.weight(1f).height(36.dp)
                    ) {
                        Text(
                            text = "$style Style",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (selectedStyle == style) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Bibliography Block
            Text(
                text = "Tracked Sources & Bibliography",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(8.dp))
                    .padding(12.dp)
            ) {
                sources.forEachIndexed { index, source ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "[${source.type}] ${formatSource(source, selectedStyle)}",
                                fontSize = 12.sp,
                                lineHeight = 16.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        IconButton(
                            onClick = {
                                sources = sources.filter { it.id != source.id }
                                if (selectedSourceId == source.id) {
                                    selectedSourceId = sources.firstOrNull()?.id ?: ""
                                }
                            },
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete reference",
                                tint = MaterialTheme.colorScheme.error.copy(alpha = 0.7f),
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                    if (index < sources.size - 1) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f))
                                .padding(vertical = 8.dp)
                        )
                    }
                }

                if (sources.isEmpty()) {
                    Text(
                        text = "No sources tracked yet. Add below to begin.",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp)
                    )
                } else {
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = {
                            val bibText = buildString {
                                append("BIBLIOGRAPHY (${selectedStyle} Style):\n\n")
                                sources.forEach {
                                    append(formatSource(it, selectedStyle))
                                    append("\n\n")
                                }
                            }
                            clipboardManager.setText(AnnotatedString(bibText))
                            Toast.makeText(context, "Bibliography copied!", Toast.LENGTH_SHORT).show()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                        modifier = Modifier.align(Alignment.End).height(32.dp),
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 12.dp, vertical = 0.dp)
                    ) {
                        Icon(imageVector = Icons.Default.ContentCopy, contentDescription = "Copy", modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Copy Bibliography", fontSize = 11.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Add Custom Source Form Toggle
            OutlinedButton(
                onClick = { showAddForm = !showAddForm },
                modifier = Modifier.fillMaxWidth().height(36.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Icon(
                    imageVector = if (showAddForm) Icons.Default.Delete else Icons.Default.Add,
                    contentDescription = "Add Source Icon",
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(if (showAddForm) "Close Form" else "Add Custom Reference Source", fontSize = 12.sp)
            }

            if (showAddForm) {
                Spacer(modifier = Modifier.height(12.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                        .padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("New Reference Source", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                    
                    // Source Type Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        listOf("Book", "Journal Article", "Website").forEach { type ->
                            val isSel = newType == type
                            Button(
                                onClick = {
                                    newType = type
                                    newContainer = if (type == "Book") "Publisher" else if (type == "Journal Article") "Journal Name" else "Site Name"
                                    newExtra = if (type == "Journal Article") "vol. 1, no. 1, pp. 1-10" else if (type == "Website") "https://..." else ""
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (isSel) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant
                                ),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.weight(1f).height(28.dp),
                                contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp)
                            ) {
                                Text(type, fontSize = 9.sp, color = if (isSel) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                    }

                    OutlinedTextField(
                        value = newAuthors,
                        onValueChange = { newAuthors = it },
                        label = { Text("Author(s) (e.g. Smith, John or Company)", fontSize = 10.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = newTitle,
                        onValueChange = { newTitle = it },
                        label = { Text("Title of Work", fontSize = 10.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedTextField(
                            value = newContainer,
                            onValueChange = { newContainer = it },
                            label = { Text(if (newType == "Book") "Publisher" else if (newType == "Journal Article") "Journal Name" else "Site Name", fontSize = 10.sp) },
                            modifier = Modifier.weight(2f),
                            singleLine = true
                        )
                        OutlinedTextField(
                            value = newYear,
                            onValueChange = { newYear = it },
                            label = { Text("Year (e.g. 2026)", fontSize = 10.sp) },
                            modifier = Modifier.weight(1f),
                            singleLine = true
                        )
                    }

                    if (newType != "Book") {
                        OutlinedTextField(
                            value = newExtra,
                            onValueChange = { newExtra = it },
                            label = { Text(if (newType == "Journal Article") "Volume, Issue, Pages" else "URL", fontSize = 10.sp) },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                    }

                    Button(
                        onClick = {
                            if (newAuthors.isBlank() || newTitle.isBlank() || newContainer.isBlank() || newYear.isBlank()) {
                                Toast.makeText(context, "Please fill out all main reference fields.", Toast.LENGTH_SHORT).show()
                                return@Button
                            }
                            val newSource = AcademicSource(
                                id = "custom_${System.currentTimeMillis()}",
                                type = newType,
                                authors = newAuthors,
                                title = newTitle,
                                container = newContainer,
                                year = newYear,
                                extra = newExtra
                            )
                            sources = sources + newSource
                            selectedSourceId = newSource.id
                            // clear form
                            newAuthors = ""
                            newTitle = ""
                            newYear = ""
                            newExtra = ""
                            showAddForm = false
                            Toast.makeText(context, "Source successfully tracked!", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier.fillMaxWidth().height(36.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Text("Add to Reference Tracker", fontSize = 12.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Citation Helper Block
            Text(
                text = "In-Text Citation Helper",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))

            if (sources.isEmpty()) {
                Text(
                    text = "Add at least one reference to use the citation generator.",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth().padding(12.dp)
                )
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(8.dp))
                        .padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Select a tracked reference to cite:", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
                    
                    // Simple source selector chips
                    Row(
                        modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        sources.forEach { src ->
                            val isSel = selectedSourceId == src.id
                            Button(
                                onClick = { selectedSourceId = src.id },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (isSel) MaterialTheme.colorScheme.primary.copy(alpha = 0.85f) else MaterialTheme.colorScheme.surfaceVariant
                                ),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.height(28.dp),
                                contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 8.dp)
                            ) {
                                val label = getAuthorLastName(src.authors) + " (${src.year})"
                                Text(label, fontSize = 9.sp, color = if (isSel) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = citationPage,
                            onValueChange = { citationPage = it },
                            label = { Text("Page Number (e.g. 15)", fontSize = 10.sp) },
                            modifier = Modifier.weight(1f),
                            singleLine = true
                        )
                        OutlinedTextField(
                            value = passageText,
                            onValueChange = { passageText = it },
                            label = { Text("Quote/Sentence to Cite (optional)", fontSize = 10.sp) },
                            modifier = Modifier.weight(2.0f),
                            singleLine = true
                        )
                    }

                    val activeSource = sources.find { it.id == selectedSourceId }
                    if (activeSource != null) {
                        val parentheticalResult = when (selectedStyle) {
                            "APA" -> {
                                val lastNames = getAuthorLastName(activeSource.authors)
                                val pSuffix = if (citationPage.isNotBlank()) ", p. $citationPage" else ""
                                "($lastNames, ${activeSource.year}$pSuffix)"
                            }
                            else -> { // MLA
                                val lastNames = getAuthorLastName(activeSource.authors)
                                val pSuffix = if (citationPage.isNotBlank()) " $citationPage" else ""
                                "($lastNames$pSuffix)"
                            }
                        }

                        val narrativeResult = when (selectedStyle) {
                            "APA" -> {
                                val lastNames = getAuthorLastName(activeSource.authors)
                                val pSuffix = if (citationPage.isNotBlank()) ", p. $citationPage" else ""
                                "$lastNames (${activeSource.year}$pSuffix)"
                            }
                            else -> { // MLA
                                val lastNames = getAuthorLastName(activeSource.authors)
                                val pSuffix = if (citationPage.isNotBlank()) " ($citationPage)" else ""
                                "$lastNames$pSuffix"
                            }
                        }

                        val quoteWithCitation = if (passageText.isNotBlank()) {
                            "\"$passageText\" $parentheticalResult"
                        } else ""

                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Generated Citations:", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)

                        // 1. Parenthetical
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text("Parenthetical:", fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
                                Text(parentheticalResult, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                            }
                            IconButton(onClick = {
                                clipboardManager.setText(AnnotatedString(parentheticalResult))
                                Toast.makeText(context, "Parenthetical citation copied!", Toast.LENGTH_SHORT).show()
                            }, modifier = Modifier.size(28.dp)) {
                                Icon(imageVector = Icons.Default.ContentCopy, contentDescription = "Copy parenthetical", modifier = Modifier.size(14.dp))
                            }
                        }

                        // 2. Narrative
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text("Narrative / In-text:", fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
                                Text(narrativeResult, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                            }
                            IconButton(onClick = {
                                clipboardManager.setText(AnnotatedString(narrativeResult))
                                Toast.makeText(context, "Narrative citation copied!", Toast.LENGTH_SHORT).show()
                            }, modifier = Modifier.size(28.dp)) {
                                Icon(imageVector = Icons.Default.ContentCopy, contentDescription = "Copy narrative", modifier = Modifier.size(14.dp))
                            }
                        }

                        // 3. Fully Cited Passage (If text exists)
                        if (quoteWithCitation.isNotBlank()) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text("Cited Passage:", fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
                                    Text(quoteWithCitation, fontSize = 11.sp, fontStyle = FontStyle.Italic)
                                }
                                IconButton(onClick = {
                                    clipboardManager.setText(AnnotatedString(quoteWithCitation))
                                    Toast.makeText(context, "Cited passage copied!", Toast.LENGTH_SHORT).show()
                                }, modifier = Modifier.size(28.dp)) {
                                    Icon(imageVector = Icons.Default.ContentCopy, contentDescription = "Copy cited passage", modifier = Modifier.size(14.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun formatSource(source: AcademicSource, style: String): String {
    return when (style) {
        "APA" -> {
            val formattedAuthors = formatAuthorsForAPA(source.authors)
            when (source.type) {
                "Book" -> "$formattedAuthors (${source.year}). *${source.title}*. ${source.container}."
                "Journal Article" -> {
                    val volIssue = formatVolIssueForAPA(source.extra)
                    "$formattedAuthors (${source.year}). ${source.title}. *${source.container}*, $volIssue."
                }
                "Website" -> "$formattedAuthors (${source.year}). ${source.title}. *${source.container}*. ${source.extra}"
                else -> "$formattedAuthors (${source.year}). ${source.title}."
            }
        }
        else -> { // MLA
            when (source.type) {
                "Book" -> "${source.authors}. *${source.title}*. ${source.container}, ${source.year}."
                "Journal Article" -> {
                    val volIssue = formatVolIssueForMLA(source.extra)
                    "${source.authors}. \"${source.title}.\" *${source.container}*, $volIssue, ${source.year}."
                }
                "Website" -> "${source.authors}. \"${source.title}.\" *${source.container}*, ${source.year}, ${source.extra}."
                else -> "${source.authors}. ${source.title}."
            }
        }
    }
}

private fun formatAuthorsForAPA(authors: String): String {
    if (!authors.contains(",")) return authors
    
    val parts = authors.split(Regex("(?i)\\s+and\\s+|\\s*,\\s*and\\s*|\\s*;\\s*|\\s*,\\s*(?![a-zA-Z])"))
        .map { it.trim() }
        .filter { it.isNotEmpty() }
    
    val result = mutableListOf<String>()
    var i = 0
    while (i < parts.size) {
        val last = parts[i]
        val first = if (i + 1 < parts.size) parts[i + 1] else ""
        if (first.isNotBlank() && first.length < 15 && !first.contains(",")) {
            val init = first.firstOrNull()?.toString()?.uppercase() ?: ""
            val initText = if (init.isNotEmpty()) " $init." else ""
            result.add("$last,$initText")
            i += 2
        } else {
            result.add(last)
            i += 1
        }
    }
    
    return when {
        result.isEmpty() -> authors
        result.size == 1 -> result[0]
        result.size == 2 -> "${result[0]} & ${result[1]}"
        else -> result.dropLast(1).joinToString(", ") + ", & " + result.last()
    }
}

private fun formatVolIssueForAPA(extra: String): String {
    val volMatch = Regex("vol\\.\\s*(\\d+)").find(extra)
    val noMatch = Regex("no\\.\\s*(\\d+)").find(extra)
    val ppMatch = Regex("pp\\.\\s*([\\d-]+)").find(extra)
    
    val vol = volMatch?.groupValues?.get(1) ?: "1"
    val no = noMatch?.groupValues?.get(1)
    val pp = ppMatch?.groupValues?.get(1) ?: ""
    
    return buildString {
        append(vol)
        if (no != null) append("($no)")
        if (pp.isNotEmpty()) append(", $pp")
    }
}

private fun formatVolIssueForMLA(extra: String): String {
    return extra
}

private fun getAuthorLastName(authors: String): String {
    if (!authors.contains(",")) return authors
    val parts = authors.split(Regex("(?i)\\s+and\\s+|\\s*,\\s*and\\s*|\\s*,\\s*"))
        .map { it.trim() }
        .filter { it.isNotEmpty() }
    
    val lastNames = mutableListOf<String>()
    var i = 0
    while (i < parts.size) {
        val last = parts[i]
        lastNames.add(last)
        i += 2
    }
    
    return when {
        lastNames.isEmpty() -> authors
        lastNames.size == 1 -> lastNames[0]
        lastNames.size == 2 -> "${lastNames[0]} & ${lastNames[1]}"
        else -> "${lastNames[0]} et al."
    }
}

@Composable
fun AcademicIllustrationCard(
    sectionTitle: String,
    sectionContent: String,
    subject: String
) {
    var generatedUrl by remember(sectionTitle) { mutableStateOf<String?>(null) }
    var isGenerating by remember(sectionTitle) { mutableStateOf(false) }
    var illustrationStyle by remember { mutableStateOf("Textbook Vector Diagram") }
    var generationStatus by remember { mutableStateOf("") }
    val context = LocalContext.current

    val styles = listOf("Textbook Vector Diagram", "Scientific 3D Render", "Pencil Sketch Chart")

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.15f)),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .testTag("academic_illustration_card")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Palette,
                        contentDescription = "Illustration Icon",
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "🔬 Academic Section Illustration",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }
            Text(
                text = "Generate and render high-quality scientific diagrams or illustrations directly for this section's topic.",
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f),
                modifier = Modifier.padding(vertical = 4.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Style Selector Chips
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                styles.forEach { style ->
                    val isSelected = style == illustrationStyle
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (isSelected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.surfaceVariant)
                            .clickable { illustrationStyle = style }
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = style,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isSelected) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (generatedUrl == null) {
                // Empty state
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(12.dp))
                        .border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f), RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        if (isGenerating) {
                            CircularProgressIndicator(color = MaterialTheme.colorScheme.secondary)
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = generationStatus,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.Image,
                                contentDescription = "No image",
                                tint = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                                modifier = Modifier.size(48.dp)
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Button(
                                onClick = {
                                    isGenerating = true
                                    val styleSuffix = when (illustrationStyle) {
                                        "Scientific 3D Render" -> "3D scientific render model, isometric, high resolution, dark background"
                                        "Pencil Sketch Chart" -> "pencil sketch design, technical blueprint drawing, white background"
                                        else -> "professional textbook scientific vector illustration, labeled, white background"
                                    }
                                    val prompt = "A clean $styleSuffix showing academic topic: $sectionTitle in subject $subject. Labeled parts, clean educational illustration."
                                    
                                    // Start a coroutine to simulate progress
                                    // Coil handles loading/generating.
                                    kotlinx.coroutines.MainScope().launch {
                                        generationStatus = "Analyzing section keywords..."
                                        kotlinx.coroutines.delay(800)
                                        generationStatus = "Drafting scientific blueprint..."
                                        kotlinx.coroutines.delay(1000)
                                        generationStatus = "Rendering illustration..."
                                        kotlinx.coroutines.delay(1000)
                                        val encodedPrompt = java.net.URLEncoder.encode(prompt, "UTF-8")
                                        generatedUrl = "https://image.pollinations.ai/prompt/$encodedPrompt?width=800&height=600&nologo=true"
                                        isGenerating = false
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text("Generate Illustration", fontSize = 12.sp)
                            }
                        }
                    }
                }
            } else {
                // Display Image
                Column(modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(240.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White)
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(context)
                                .data(generatedUrl)
                                .crossfade(true)
                                .build(),
                            contentDescription = "Academic Section Illustration",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Fit
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Style: $illustrationStyle",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Button(
                                onClick = {
                                    // Reset/Regenerate
                                    generatedUrl = null
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.height(36.dp)
                            ) {
                                Text("Regenerate", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditMetadataDialog(
    project: AcademicProject,
    onDismiss: () -> Unit,
    onSubmit: (title: String, subject: String, studentName: String, rollNumber: String, schoolName: String, academicLevel: String, extraInstructions: String) -> Unit
) {
    var title by remember { mutableStateOf(project.title) }
    var subject by remember { mutableStateOf(project.subject) }
    var studentName by remember { mutableStateOf(project.studentName) }
    var rollNumber by remember { mutableStateOf(project.rollNumber) }
    var schoolName by remember { mutableStateOf(project.schoolName) }
    var academicLevel by remember { mutableStateOf(project.academicLevel) }
    var extraInstructions by remember { mutableStateOf(project.extraInstructions) }

    val context = LocalContext.current

    val speechLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val spokenText = result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.firstOrNull()
            if (spokenText != null) {
                extraInstructions = if (extraInstructions.isEmpty()) spokenText else "$extraInstructions\n$spokenText"
                Toast.makeText(context, "Dictation appended successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "🔧 Edit Draft Settings",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Project Title") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = subject,
                    onValueChange = { subject = it },
                    label = { Text("Subject / Class") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = studentName,
                    onValueChange = { studentName = it },
                    label = { Text("Student Name") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = rollNumber,
                    onValueChange = { rollNumber = it },
                    label = { Text("Roll Number / ID") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = schoolName,
                    onValueChange = { schoolName = it },
                    label = { Text("School / College Name") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = academicLevel,
                    onValueChange = { academicLevel = it },
                    label = { Text("Academic Level") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = extraInstructions,
                    onValueChange = { extraInstructions = it },
                    label = { Text("Guidelines & Extra Specifications") },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 4,
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                                    putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                                    putExtra(RecognizerIntent.EXTRA_PROMPT, "Dictate guidelines or details...")
                                }
                                try {
                                    speechLauncher.launch(intent)
                                } catch (e: Exception) {
                                    Toast.makeText(context, "Voice search is not supported on this device.", Toast.LENGTH_SHORT).show()
                                }
                            }
                        ) {
                            Icon(imageVector = Icons.Default.Mic, contentDescription = "Dictate guidelines")
                        }
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            onSubmit(title, subject, studentName, rollNumber, schoolName, academicLevel, extraInstructions)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Text("Save Changes")
                    }
                }
            }
        }
    }
}
