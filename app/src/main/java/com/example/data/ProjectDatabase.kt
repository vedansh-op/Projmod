package com.example.data

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "academic_projects")
data class AcademicProject(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val subject: String,
    val studentName: String = "",
    val rollNumber: String = "",
    val schoolName: String = "",
    val academicLevel: String = "Class XII",
    val extraInstructions: String = "",
    val createdTimestamp: Long = System.currentTimeMillis(),
    val lastUpdatedTimestamp: Long = System.currentTimeMillis(),
    val currentSectionIndex: Int = 0 // 0: Created/Empty, 1: Front Matter + Sec 1, 2: Sec 2, etc.
)

@Entity(
    tableName = "project_sections",
    foreignKeys = [
        ForeignKey(
            entity = AcademicProject::class,
            parentColumns = ["id"],
            childColumns = ["projectId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ProjectSection(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val projectId: Int,
    val sectionIndex: Int, // 1 to 10
    val sectionTitle: String,
    val content: String,
    val generatedAt: Long = System.currentTimeMillis()
)

@Dao
interface ProjectDao {
    @Query("SELECT * FROM academic_projects ORDER BY lastUpdatedTimestamp DESC")
    fun getAllProjectsFlow(): Flow<List<AcademicProject>>

    @Query("SELECT * FROM academic_projects WHERE id = :id")
    suspend fun getProjectById(id: Int): AcademicProject?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProject(project: AcademicProject): Long

    @Query("UPDATE academic_projects SET currentSectionIndex = :index, lastUpdatedTimestamp = :timestamp WHERE id = :id")
    suspend fun updateProjectProgress(id: Int, index: Int, timestamp: Long)

    @Query("DELETE FROM academic_projects WHERE id = :id")
    suspend fun deleteProjectById(id: Int)

    @Query("SELECT * FROM project_sections WHERE projectId = :projectId ORDER BY sectionIndex ASC")
    fun getSectionsForProjectFlow(projectId: Int): Flow<List<ProjectSection>>

    @Query("SELECT * FROM project_sections WHERE projectId = :projectId ORDER BY sectionIndex ASC")
    suspend fun getSectionsForProject(projectId: Int): List<ProjectSection>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSection(section: ProjectSection): Long

    @Query("DELETE FROM project_sections WHERE projectId = :projectId AND sectionIndex = :sectionIndex")
    suspend fun deleteSection(projectId: Int, sectionIndex: Int)
}

@Database(entities = [AcademicProject::class, ProjectSection::class], version = 2, exportSchema = false)
abstract class ProjectDatabase : RoomDatabase() {
    abstract fun projectDao(): ProjectDao
}
