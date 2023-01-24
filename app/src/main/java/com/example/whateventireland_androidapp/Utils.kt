package com.example.whateventireland_androidapp

import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class Utils {

    fun clearTextFields(vararg field: TextInputEditText) {
        field.forEach {
            it.setText("")
        }
    }

    fun disableButtons(vararg button: Button){
        button.forEach {
            it.isEnabled=false
        }
    }

    fun enableButtons(vararg button: Button){
        button.forEach {
            it.isEnabled=true
        }
    }
}
