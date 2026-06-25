package com.example.data

import android.content.Context
import androidx.room.Room

class AppContainer(private val context: Context) {
    val database: ProjectDatabase by lazy {
        Room.databaseBuilder(
            context.applicationContext,
            ProjectDatabase::class.java,
            "project_database.db"
        )
        .addMigrations(ProjectDatabase.MIGRATION_2_3)
        .fallbackToDestructiveMigration()
        .build()
    }

    val repository: ProjectRepository by lazy {
        ProjectRepository(database.projectDao())
    }
}
