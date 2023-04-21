/*
 * SPDX-License-Identifier: MPL-2.0
 * Copyright © 2023 Skyline Team and Contributors (https://github.com/skyline-emu/)
 */

package emu.skyline.data

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import androidx.preference.PreferenceManager
import emu.skyline.SkylineApplication
import org.json.JSONException
import org.json.JSONObject
import java.io.File


object GameFolders {

    private val sharedPref : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(SkylineApplication.instance)

    private val directories : HashMap<String, String> = hashMapOf()

    init {
        try {
            val jsonString = sharedPref.getString("game_folders", JSONObject().toString())
            if (jsonString != null) {
                val jsonObject = JSONObject(jsonString)
                val keysItr = jsonObject.keys()
                while (keysItr.hasNext()) {
                    val key = keysItr.next()
                    val value = jsonObject.getString(key)
                    directories[key] = value
                }
            }
        } catch (e : JSONException) {
            e.printStackTrace()
        }
    }

    private fun saveMap(inputMap : Map<String?, String?>) {
        val jsonObject = JSONObject(inputMap)
        val jsonString = jsonObject.toString()
        with (sharedPref.edit()) {
            remove("game_folders")
            putString("game_folders", jsonString)
            apply()
        }
    }

    fun removeDirectory(key: String) {
        directories.remove(key)
        saveMap(directories.toMap())
    }
    fun putDirectory(key: String, directory: String) {
        directories[key] = directory
        saveMap(directories.toMap())
    }

    private fun getDirectory(key: String) : String? {
        return directories[key]
    }

    fun getDirectoryPath(key: String) : String {
        val uri = getDirectory(key)
        return  uri?.let { Uri.parse(it).path.toString() } ?: ""
    }

    fun getDirectoryPath(uri: Uri?) : String {
        return uri?.path ?: ""
    }

    fun hasDirectory(uri: Uri) : Boolean {
        return directories.values.any { dlcPath ->
            uri.path?.substringAfterLast(":")?.startsWith("${Uri.parse(dlcPath).path?.substringAfterLast(":")}${File.separator}") ?: false
        }
    }
}
