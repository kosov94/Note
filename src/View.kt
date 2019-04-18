import Const.IN_WORK
import Const.NEED_TO_WORK
import Const.READY
import models.Info
import models.Note
import models.Title


fun main(args: Array<String>) {
    var notes = Note()
    var exit = "д"
    while (exit.contains("д")) {
        println("Введите название заметки(чтобы посмотреть все введите - 'все')")
        var answer: String = readLine()!!
        if (answer.contains("все"))
            notes.showAllNote()
        else {
            if (notes.containsNote(Title(answer))) {
                notes.getNoteById(Title(answer))
                println("Хотите удалить? (д/н)")
                if (readLine() == "д") {
                    notes.deleteNote(Title(answer))
                    notes.saveNotes()
                }
            } else {
                println("Заметка не найдена, хотите создать? (д/н)")
                val title = Title(answer)
                answer = readLine()!!
                if (answer == "д") {
                    do {
                        println("Установите статус задачи")
                        println(
                            "1.${NEED_TO_WORK}\n" +
                                    "2.${IN_WORK}\n" +
                                    "3.${READY}"
                        )
                        answer = readLine()!!
                    } while (answer != "1" && answer != "2" && answer != "3")
                    val info = Info(answer.toInt())
                    notes.createNote(title, info)
                    notes.saveNotes()
                }
            }
        }
        println("Хотите продолжить? (д/н)")
        if (readLine() != "д")
            exit = "н"

    }

}