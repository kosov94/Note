import Const.IN_WORK
import Const.NEED_TO_WORK
import Const.READY
import models.*


fun main(args: Array<String>) {
    var tasks = Tasks()
    var exit = "д"
    while (exit == "д") {
        println("Введите название задачи(чтобы посмотреть все введите - 'все')")
        var answer: String = readLine()!!
        if (answer == "все") {
            println(
                "Выберите статус задач:\n" +
                        "1. Все задачи\n" +
                        "2. ${NEED_TO_WORK}\n" +
                        "3. ${IN_WORK}\n" +
                        "4. ${READY}\n"
            )
            when (readLine()?.toInt()) {
                1 -> tasks.shawAllTask()
                2 -> tasks.shawTasksByStatus(Status(1))
                3 -> tasks.shawTasksByStatus(Status(2))
                4 -> tasks.shawTasksByStatus(Status(3))
            }
        } else {
            if (tasks.constainsTask(Title(answer))) {
                tasks.shawTaskByTitle(Title(answer))
                println(
                    "1. Изменить статус задачи\n" +
                            "2. Изменить исполнителей задачи\n" +
                            "3. Удалить задачу\n" +
                            "4. Продолжить"
                )
                when (readLine()?.toInt()) {
                    1 -> {
                        var change: String
                        do {
                            println("Введите статус задачи")
                            println(
                                "1.${NEED_TO_WORK}\n" +
                                        "2.${IN_WORK}\n" +
                                        "3.${READY}"
                            )
                            change = readLine() ?: "4"
                        } while (change != "1" && change != "2" && change != "3")
                        tasks.changeStatus(Title(answer), Status(change.toInt()))
                    }
                    2 -> {
                        println("Введите новых исполнителей(через пробел)")
                        tasks.changeExecudors(Title(answer), stringToList(readLine()?.split(" ")?.toMutableList()))
                    }
                    3 -> {
                        tasks.deleteTask(Title(answer))
                        println("Задача удалена")
                    }
                }
            } else {
                println("Задача не найдена, хотите создать? (д/н)")
                val title = Title(answer)
                when (readLine()) {
                    "д" -> {
                        println("Введите исполнителей(через пробел)")
                        var executors = readLine()?.split(" ")?.toMutableList()
                        do {
                            println("Введите статус задачи")
                            println(
                                "1.${NEED_TO_WORK}\n" +
                                        "2.${IN_WORK}\n" +
                                        "3.${READY}"
                            )
                            answer = readLine() ?: "4"
                        } while (answer != "1" && answer != "2" && answer != "3")
                        tasks.createTask(title, Status(answer.toInt()), stringToList(executors))

                    }
                }
            }
        }
        println("Хотите продолжить? (д/н)")
        if (readLine() != "д")
            exit = "н"


    }
}

private fun stringToList(string: MutableList<String>?): MutableList<Executor> {
    var list: MutableList<Executor> = mutableListOf()
    for (executor in string.orEmpty())
        list.let { list.add(Executor(executor)) }
    return list
}

