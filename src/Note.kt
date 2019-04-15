import models.Info
import models.Title
import java.io.File

class Note(initialMap: Map<Title, Info> = emptyMap()) : NoteAction {
    private val notesMap: MutableMap<Title, Info> = mutableMapOf(* initialMap.arrayOfPairs)

    private val Map<Title, Info>.arrayOfPairs: Array<Pair<Title, Info>>
        get() = map { it.key to it.value }.toTypedArray()

    override fun createNote(title: Title, info: Info) {
        notesMap[title] = info
    }


    override fun loadAllNote() {
        var buf: String
        File("notes.txt").forEachLine {
            buf = it.split("/0").toString()
            notesMap.put(Title(buf[0].toString()), Info(buf[1].toString()))
        }
    }

    override fun getNoteById(titel: Title) {
        if (notesMap.containsKey(titel))
            println(titel.toString() + notesMap.getValue(titel).toString())
        else
            println("Не найдено")
    }

    override fun deleteAllNote() {
        for(note in notesMap)
            notesMap.remove(note.key)
    }

    override fun deleteNote(titel: Title) {
        if(notesMap.containsKey(titel))
            notesMap.remove(titel)
        else
            println("Не найдено")
    }

}