import models.Info
import models.Title


interface NoteAction {
    fun createNote(titel: Title, info: Info)

    fun showAllNote()

    fun getNoteById(titel: Title)

    fun deleteNote(titel: Title)

    fun saveNotes()

    fun containsNote(titel: Title):Boolean

}
