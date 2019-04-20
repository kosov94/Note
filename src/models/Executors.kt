package models

import java.io.File
import java.lang.Exception

class Executors(val initialMap: Map<Title, List<Executor>> = emptyMap()) {
    private val fileName: String = "executors.txt"
    private val executorsMap: MutableMap<Title, List<Executor>> = loadExecutors(fileName)

    private fun loadExecutors(fileName: String): MutableMap<Title, List<Executor>> {
        var loadMap: MutableMap<Title, List<Executor>> = mutableMapOf()
        var buf: Array<String>
        try {
            val reader = File(fileName).bufferedReader()
            reader.forEachLine {
                buf = it.split(",").toTypedArray()
                var list: MutableList<Executor> = mutableListOf()
                var list2: MutableList<String> = buf[1].split(" ").toMutableList()
                for (item in list2)
                    if (item.isNotEmpty())
                    list.add(Executor(item))
                loadMap[Title(buf[0])] = list
            }
            reader.close()
            return loadMap
        } catch (ex: Exception) {
            return loadMap
        }
    }

    fun addExecutor(title: Title, executor: MutableList<Executor>) {
        executorsMap[title] = executor
        saveExecutors()
    }

    fun getExecutors(title: Title): List<Executor>? {
//        if (executorsMap.contains(title))
//            for (executor in executorsMap.getValue(title))
//                print(executor)
        return executorsMap[title]
    }

    fun saveExecutors() {
        File(fileName).bufferedWriter().use { out ->
            executorsMap.forEach {
                out.write("${it.key},${inString(it.value)},\n")
            }
            out.close()
        }
    }

    fun deleteExecutors(titel: Title) {
        executorsMap.remove(titel)
        saveExecutors()
    }

    private fun inString(list: List<Executor>): String {
        var string: String = ""
        list.forEach {
            string += it.toString()
        }
        return string
    }
}