package models

class Tasks {
    private val taskStatus = TaskStatus()
    private val executors = Executors()

    fun shawTaskByTitle(title: Title) {
        if (taskStatus.containsStatus(title))
            println(
                "Исполнители: ${executors.getExecutors(title)}\n" +
                        "Статус: ${taskStatus.getStatusByTitle(title)}\n"
            )
    }

    fun shawTasksByStatus(status: Status) {
        taskStatus.getTitlebyStatus(status)
    }

    fun createTask(title: Title, status: Status, executor: MutableList<Executor>) {
        taskStatus.createStatus(title, status)
        executors.addExecutor(title, executor)
    }

    fun shawAllTask() {
        for (title in taskStatus.getAllStaus().keys)
            println(
                "Название: ${title}\n" +
                        "Исполнители: ${executors.getExecutors(title)}\n" +
                        "Статус: ${taskStatus.getStatusByTitle(title)}\n"
            )
    }

    fun changeExecudors(title: Title, executor: MutableList<Executor>) {
        executors.addExecutor(title, executor)
    }

    fun deleteTask(title: Title) {
        taskStatus.deleteStatus(title)
        executors.deleteExecutors(title)

    }

    fun changeStatus(title: Title, status: Status) = taskStatus.createStatus(title, status)

    fun constainsTask(title: Title): Boolean = taskStatus.containsStatus(title)

}