import models.Info
import models.Note
import models.Title


fun main(args: Array<String>) {
    var notes: Note = Note()
    var exit: String = "д"
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
                    println("Введите текст заметки:")
                    val info = Info(readLine()!!)
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