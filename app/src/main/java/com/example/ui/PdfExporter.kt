package com.example.ui

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.widget.Toast
import androidx.core.content.FileProvider
import com.example.data.AcademicProject
import com.example.data.ProjectSection
import java.io.File
import java.io.FileOutputStream

object PdfExporter {

    fun exportProjectToPdf(
        context: Context,
        project: AcademicProject,
        sections: List<ProjectSection>
    ) {
        if (sections.isEmpty()) {
            Toast.makeText(context, "Cannot export empty project", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            val pdfDocument = PdfDocument()
            val pageWidth = 595 // A4 width in points
            val pageHeight = 842 // A4 height in points
            var pageNumber = 1

            // Paints
            val titlePaint = Paint().apply {
                color = Color.rgb(26, 54, 93) // Deep blue
                textSize = 24f
                typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                isAntiAlias = true
            }

            val subtitlePaint = Paint().apply {
                color = Color.rgb(74, 85, 104) // Dark slate
                textSize = 14f
                typeface = Typeface.create(Typeface.DEFAULT, Typeface.ITALIC)
                isAntiAlias = true
            }

            val heading1Paint = Paint().apply {
                color = Color.rgb(43, 108, 176) // Medium blue
                textSize = 18f
                typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                isAntiAlias = true
            }

            val heading2Paint = Paint().apply {
                color = Color.rgb(45, 55, 72)
                textSize = 14f
                typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                isAntiAlias = true
            }

            val bodyPaint = Paint().apply {
                color = Color.rgb(45, 55, 72)
                textSize = 11f
                typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
                isAntiAlias = true
            }

            val metaLabelPaint = Paint().apply {
                color = Color.rgb(113, 128, 150)
                textSize = 10f
                typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                isAntiAlias = true
            }

            val metaValPaint = Paint().apply {
                color = Color.rgb(45, 55, 72)
                textSize = 11f
                typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                isAntiAlias = true
            }

            val footerPaint = Paint().apply {
                color = Color.rgb(160, 174, 192)
                textSize = 9f
                typeface = Typeface.create(Typeface.DEFAULT, Typeface.ITALIC)
                isAntiAlias = true
            }

            val linePaint = Paint().apply {
                color = Color.rgb(226, 232, 240)
                strokeWidth = 1f
                isAntiAlias = true
            }

            // --- PAGE 1: COVER PAGE ---
            var pageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, pageNumber++).create()
            var page = pdfDocument.startPage(pageInfo)
            var canvas = page.canvas

            // Draw accent top and bottom bars
            val accentPaint = Paint().apply {
                color = Color.rgb(26, 54, 93)
            }
            canvas.drawRect(0f, 0f, pageWidth.toFloat(), 20f, accentPaint)
            canvas.drawRect(0f, pageHeight.toFloat() - 20f, pageWidth.toFloat(), pageHeight.toFloat(), accentPaint)

            // Draw a double line border
            val borderPaint = Paint().apply {
                color = Color.rgb(26, 54, 93)
                style = Paint.Style.STROKE
                strokeWidth = 2f
            }
            canvas.drawRect(40f, 40f, pageWidth.toFloat() - 40f, pageHeight.toFloat() - 40f, borderPaint)
            borderPaint.strokeWidth = 0.5f
            canvas.drawRect(44f, 44f, pageWidth.toFloat() - 44f, pageHeight.toFloat() - 44f, borderPaint)

            // Center project title
            var currentY = 180f
            val maxTextWidth = pageWidth - 120 // Margins

            val titleLines = splitTextIntoLines(project.title, titlePaint, maxTextWidth)
            titleLines.forEach { line ->
                val x = (pageWidth - titlePaint.measureText(line)) / 2f
                canvas.drawText(line, x, currentY, titlePaint)
                currentY += 32f
            }

            currentY += 10f
            val subtitleText = "An Academic Project"
            canvas.drawText(subtitleText, (pageWidth - subtitlePaint.measureText(subtitleText)) / 2f, currentY, subtitlePaint)

            currentY = 380f
            // Metadata Box
            val boxLeft = 80f
            val boxRight = pageWidth - 80f
            val boxTop = currentY
            val boxBottom = currentY + 180f

            val boxBgPaint = Paint().apply {
                color = Color.rgb(247, 250, 252)
                style = Paint.Style.FILL
            }
            canvas.drawRect(boxLeft, boxTop, boxRight, boxBottom, boxBgPaint)
            canvas.drawRect(boxLeft, boxTop, boxRight, boxBottom, Paint().apply {
                color = Color.rgb(226, 232, 240)
                style = Paint.Style.STROKE
                strokeWidth = 1f
            })

            // Meta Details
            var metaY = boxTop + 30f
            val labelX = boxLeft + 24f
            val valX = boxLeft + 160f

            fun drawMetaLine(label: String, value: String) {
                canvas.drawText(label, labelX, metaY, metaLabelPaint)
                canvas.drawText(value, valX, metaY, metaValPaint)
                metaY += 24f
            }

            drawMetaLine("SUBJECT:", project.subject.uppercase())
            drawMetaLine("ACADEMIC LEVEL:", project.academicLevel.uppercase())
            drawMetaLine("STUDENT NAME:", project.studentName.ifEmpty { "[Your Name]" })
            drawMetaLine("ROLL / ID NUMBER:", project.rollNumber.ifEmpty { "[Your Roll Number]" })
            drawMetaLine("INSTITUTION:", project.schoolName.ifEmpty { "[Your School/College Name]" })

            // Footer
            val footerText = "Generated via ProjMod Ultimate - AI Project Architect"
            canvas.drawText(footerText, (pageWidth - footerPaint.measureText(footerText)) / 2f, pageHeight - 60f, footerPaint)

            pdfDocument.finishPage(page)

            // --- PAGES 2+: SECTIONS ---
            sections.forEach { section ->
                pageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, pageNumber++).create()
                page = pdfDocument.startPage(pageInfo)
                canvas = page.canvas

                // Header
                canvas.drawText(project.title.take(45) + if (project.title.length > 45) "..." else "", 50f, 45f, footerPaint)
                canvas.drawLine(50f, 52f, pageWidth - 50f, 52f, linePaint)

                // Section Title
                canvas.drawText(section.sectionTitle, 50f, 85f, heading1Paint)
                canvas.drawLine(50f, 92f, pageWidth - 50f, 92f, linePaint)

                currentY = 120f
                val sectionLines = section.content.split("\n")
                
                sectionLines.forEach { rawLine ->
                    val line = cleanMarkdown(rawLine).trim()
                    if (line.isEmpty()) {
                        currentY += 10f // paragraph break
                        return@forEach
                    }

                    // Handle headings
                    val isHeading = rawLine.trim().startsWith("###") || rawLine.trim().startsWith("##") || rawLine.trim().startsWith("**") && rawLine.trim().endsWith("**") && rawLine.length < 100
                    val currentPaint = if (isHeading) heading2Paint else bodyPaint
                    val leadingSpacing = if (isHeading) 12f else 6f
                    val lineSpacing = if (isHeading) 18f else 15f

                    // Split paragraph line into wrapped lines
                    val wrappedLines = splitTextIntoLines(line, currentPaint, pageWidth - 100)
                    wrappedLines.forEach { wrappedLine ->
                        // Check for page overflow
                        if (currentY > pageHeight - 80f) {
                            // Footer before finishing page
                            val pageNumStr = "Page ${pageNumber - 1}"
                            canvas.drawText(pageNumStr, pageWidth - 50f - footerPaint.measureText(pageNumStr), pageHeight - 45f, footerPaint)

                            pdfDocument.finishPage(page)

                            // Start next page
                            pageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, pageNumber++).create()
                            page = pdfDocument.startPage(pageInfo)
                            canvas = page.canvas

                            // Header for new page
                            canvas.drawText(project.title.take(45) + if (project.title.length > 45) "..." else "", 50f, 45f, footerPaint)
                            canvas.drawLine(50f, 52f, pageWidth - 50f, 52f, linePaint)

                            currentY = 80f
                        }

                        currentY += leadingSpacing
                        canvas.drawText(wrappedLine, 50f, currentY, currentPaint)
                        currentY += lineSpacing
                    }
                }

                // Illustration check
                if (section.illustrationUrl != null) {
                    if (currentY > pageHeight - 250f) {
                        // Footer before finishing page
                        val pageNumStr = "Page ${pageNumber - 1}"
                        canvas.drawText(pageNumStr, pageWidth - 50f - footerPaint.measureText(pageNumStr), pageHeight - 45f, footerPaint)
                        pdfDocument.finishPage(page)

                        // Start next page
                        pageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, pageNumber++).create()
                        page = pdfDocument.startPage(pageInfo)
                        canvas = page.canvas

                        // Header for new page
                        canvas.drawText(project.title.take(45) + if (project.title.length > 45) "..." else "", 50f, 45f, footerPaint)
                        canvas.drawLine(50f, 52f, pageWidth - 50f, 52f, linePaint)

                        currentY = 80f
                    }

                    currentY += 20f
                    val rect = RectF(100f, currentY, pageWidth - 100f, currentY + 180f)
                    val fillPaint = Paint().apply { color = Color.rgb(240, 244, 248); style = Paint.Style.FILL }
                    val strokePaint = Paint().apply { color = Color.rgb(203, 213, 224); style = Paint.Style.STROKE; strokeWidth = 1f }

                    canvas.drawRoundRect(rect, 8f, 8f, fillPaint)
                    canvas.drawRoundRect(rect, 8f, 8f, strokePaint)

                    val illusText = "[Section Illustration: AI Generated]"
                    val urlText = "Ref: " + (section.illustrationUrl.take(60) + if (section.illustrationUrl.length > 60) "..." else "")
                    canvas.drawText(illusText, (pageWidth - bodyPaint.measureText(illusText)) / 2f, currentY + 90f, bodyPaint)
                    canvas.drawText(urlText, (pageWidth - footerPaint.measureText(urlText)) / 2f, currentY + 110f, footerPaint)

                    currentY += 200f
                }

                // Page Footer for Section ending
                val pageNumStr = "Page ${pageNumber - 1}"
                canvas.drawText(pageNumStr, pageWidth - 50f - footerPaint.measureText(pageNumStr), pageHeight - 45f, footerPaint)

                pdfDocument.finishPage(page)
            }

            // Save PDF to cache
            val file = File(context.cacheDir, "${project.title.replace(" ", "_")}_Project.pdf")
            val outputStream = FileOutputStream(file)
            pdfDocument.writeTo(outputStream)
            pdfDocument.close()
            outputStream.close()

            // Open intent to share/view PDF
            val fileUri: Uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                file
            )

            val viewIntent = Intent(Intent.ACTION_SEND).apply {
                type = "application/pdf"
                putExtra(Intent.EXTRA_STREAM, fileUri)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }

            val chooser = Intent.createChooser(viewIntent, "Share or Save Project PDF")
            chooser.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(chooser)

            Toast.makeText(context, "PDF successfully compiled!", Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Error compiling PDF: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun splitTextIntoLines(text: String, paint: Paint, maxWidth: Int): List<String> {
        val words = text.split(" ")
        val lines = mutableListOf<String>()
        var currentLine = StringBuilder()

        words.forEach { word ->
            val testLine = if (currentLine.isEmpty()) word else "${currentLine} $word"
            val width = paint.measureText(testLine)
            if (width < maxWidth) {
                currentLine.append(if (currentLine.isEmpty()) word else " $word")
            } else {
                lines.add(currentLine.toString())
                currentLine = StringBuilder(word)
            }
        }
        if (currentLine.isNotEmpty()) {
            lines.add(currentLine.toString())
        }
        return lines
    }

    private fun cleanMarkdown(text: String): String {
        return text
            .replace(Regex("\\*\\*(.*?)\\*\\*"), "$1") // bold
            .replace(Regex("\\*(.*?)\\*"), "$1") // italic
            .replace(Regex("###\\s*(.*)"), "$1") // H3
            .replace(Regex("##\\s*(.*)"), "$1") // H2
            .replace(Regex("#\\s*(.*)"), "$1") // H1
            .replace(Regex("`([^`]+)`"), "$1") // code
            .replace(Regex("-\\|-"), "") // table separator
            .replace(Regex("\\|"), "  ") // table borders replaced with spaces
    }
}
