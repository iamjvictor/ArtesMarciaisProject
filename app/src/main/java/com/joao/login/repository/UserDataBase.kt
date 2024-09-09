package com.joao.login.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.joao.login.infra.AppConstants

// Função - Conexão com o Banco de dados

class UserDataBase(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {

        private const val DATABASE_NAME = "user.db"
        private const val DATABASE_VERSION = 3

        const val TABLE_USERS  = "user"
        const val TABLE_POSTS = "posts"
        const val TABLE_PROF = "professor"
    }
    object COLUMNS {
        // Colunas da tabela users
        const val ID = "id"
        const val NAME = "name"
        const val EMAIL = "email"
        const val PASSWORD = "password"
        const val AREA = "area"
        const val MODALIDADE = "modalidade"

        // Colunas da nova tabela posts
        const val POST_ID = "post_id"
        const val USER_ID = "user_id"
        const val POST_TITLE = "post_title"
        const val POST_CONTENT = "post_content"
        const val POST_DATE = "post_date"
        const val POST_URI_IMAGE = "post_uri_image"
        const val POST_IMAGE = "post_image"

        // Coluna Professor
        const val REGISTER = "register"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableSQL = "CREATE TABLE $TABLE_USERS (" +
                "${COLUMNS.ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${COLUMNS.NAME} TEXT, " +
                "${COLUMNS.EMAIL} TEXT, " +
                "${COLUMNS.PASSWORD} TEXT, " +
                "${COLUMNS.AREA} INTEGER," +
                "${COLUMNS.MODALIDADE} TEXT " +
                ")"
        db.execSQL(createTableSQL)

        val createProfTableSQL = "CREATE TABLE $TABLE_PROF (" +
                "${COLUMNS.ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${COLUMNS.NAME} TEXT, " +
                "${COLUMNS.EMAIL} TEXT, " +
                "${COLUMNS.PASSWORD} TEXT, " +
                "${COLUMNS.AREA} INTEGER," +
                "${COLUMNS.MODALIDADE} TEXT, " +
                "${COLUMNS.REGISTER} TEXT" +
                ")"
        db.execSQL(createProfTableSQL)
        // tabela de posts
        val createPostsTableSQL = "CREATE TABLE $TABLE_POSTS (" +
                "${COLUMNS.POST_ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${COLUMNS.USER_ID} INTEGER, " +
                "${COLUMNS.POST_TITLE} TEXT, " +
                "${COLUMNS.POST_CONTENT} TEXT, " +
                "${COLUMNS.POST_DATE} TEXT, " +
                "${COLUMNS.POST_URI_IMAGE} TEXT, " +
                "${COLUMNS.POST_IMAGE} BLOB, " +
                "FOREIGN KEY(${COLUMNS.USER_ID}) REFERENCES $TABLE_USERS(${COLUMNS.ID})" + // Adicionar uma chave estrangeira
                ")"
        db.execSQL(createPostsTableSQL)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // atualizar o banco de dados, implemente aquiS
    }
}