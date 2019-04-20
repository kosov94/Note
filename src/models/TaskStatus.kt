package models

import Const.IN_WORK
import Const.NEED_TO_WORK
import Const.READY
import java.io.File


class TaskStatus {
    private val fileName: String = "notes.txt"
    private val notesMap: MutableMap<Title, Status> = loadStatus(fileName)


    private fun loadStatus(fileName: String): MutableMap<Title, Status> {
        var mutableMap: MutableMap<Title, Status> = mutableMapOf<Title, Status>()
        var buf: Array<String>
        try {
            val reader = File(fileName).bufferedReader()
            reader.forEachLine {
                buf = it.split("/0").toTypedArray()
                mutableMap[Title(buf[0].toString())] = Status(
                    when (buf[1]) {
                        NEED_TO_WORK -> 1
                        IN_WORK -> 2
                        READY -> 3
                        else -> 4
                    }
                )
            }
            reader.close()
            return mutableMap
        } catch (ex: Exception) {
            println("Списаок задач пуст.")
            return mutableMap
        }
    }

    fun createStatus(title: Title, status: Status) {
        notesMap[title] = status
        saveStatus()
    }


    fun getAllStaus(): MutableMap<Title, Status> {
        return notesMap
    }

    fun getStatusByTitle(titel: Title): Status {
        if (notesMap.containsKey(titel))
            return notesMap.getValue(titel)
        return Status(1)
    }

    fun getTitlebyStatus(status: Status) {
        for (item in notesMap)
            if (item.value == status)
                println("Задача: ${item.key}\n")
    }

    fun deleteStatus(titel: Title) {
        notesMap.remove(titel)
        saveStatus()
    }

    fun saveStatus() {
        File(fileName).bufferedWriter().use { out ->
            notesMap.forEach {
                out.write("${it.key}/0${it.value}/0\n")
            }
        }
    }

    fun containsStatus(titel: Title): Boolean {
        return notesMap.containsKey(titel)
    }
}
