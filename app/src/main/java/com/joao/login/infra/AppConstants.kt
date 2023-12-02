package com.joao.login.infra

class AppConstants private constructor() {

    object FILTER {
        const val MODALIDADES = 1
        const val TSUNAMI = 2
        const val FACE = 3
    }
     object USER {
        const val TABLE_NAME = "Users"

         object COLUMNS {
             const val ID = "id"
             const val NAME = "name"
             const val AREA = "area"
             const val EMAIL = "email"
         }
    }
}