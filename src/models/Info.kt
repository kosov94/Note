package models

data class Info(val text: String){
    override fun toString(): String {
        return "$text"
    }
}