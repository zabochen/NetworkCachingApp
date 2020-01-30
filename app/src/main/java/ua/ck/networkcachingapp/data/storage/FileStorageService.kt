package ua.ck.networkcachingapp.data.storage

import android.content.Context
import java.io.File

class FileStorageService(
    private val context: Context
) {

    fun saveImage() {

        // app directory
        val folderPath = context.filesDir.toString() + "/images"
        val fileFolder = File(folderPath)
        try {
            if (!fileFolder.exists()) {
                fileFolder.mkdir()
            }
        } catch (e: Exception) {
        }
    }

}