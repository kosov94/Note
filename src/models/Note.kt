package models

import NoteAction
import java.io.File


class Note : NoteAction {
    private val fileName: String = "notes.txt"
    private val notesMap: MutableMap<Title, Info> = loadNotes(fileName)

    private fun loadNotes(fileName: String): MutableMap<Title, Info> {
        var mutableMap: MutableMap<Title, Info> = mutableMapOf<Title, Info>()
        var buf: Array<String>
        try {
            val reader = File(fileName).bufferedReader()
            reader.forEachLine {
                buf = it.split("/0").toTypedArray()
                mutableMap[Title(buf[0].toString())] = Info(buf[1].toString())
            }
            reader.close()
            return mutableMap
        } catch (ex: Exception) {
            return mutableMap
        }
    }

    override fun createNote(title: Title, info: Info) {
        notesMap[title] = info
    }


    override fun showAllNote() {
        for ((key, value) in notesMap)
            println("${key}: ${value}")
    }

    override fun getNoteById(titel: Title) {
        if (notesMap.containsKey(titel))
            println("${titel.toString()}: ${notesMap.getValue(titel).toString()}")

    }

    override fun deleteNote(titel: Title) {
        notesMap.remove(titel)
        println("Заметка удалена.")
    }

    override fun saveNotes() {
        File(fileName).bufferedWriter().use { out ->
            notesMap.forEach {
                out.write("${it.key}/0 ${it.value}/0 \n")
            }
        }
    }

    override fun containsNote(titel: Title): Boolean {
        return notesMap.containsKey(titel)
    }
}
