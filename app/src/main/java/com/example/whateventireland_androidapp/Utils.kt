package com.example.whateventireland_androidapp

import com.google.android.material.textfield.TextInputEditText

class Utils {

    fun clearTextFields(vararg field:TextInputEditText){
         field.forEach {
             it.setText("")
         }

    }

}