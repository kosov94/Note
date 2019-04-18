package models

import Const.IN_WORK
import Const.NEED_TO_WORK
import Const.READY

data class Info(val status: Int){
    override fun toString(): String {
        when(status){
            1->return NEED_TO_WORK
            2-> return IN_WORK
            3-> return READY
        }
        return "Статус не установлен"
    }
}