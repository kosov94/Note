import models.Info
import models.Title

interface NoteAction {
    fun createNote(titel: Title, info: Info)

    fun loadAllNote()

    fun getNoteById(titel: Title)

    fun deleteAllNote()

    fun deleteNote(titel: Title)

}
