package com.joao.login.repository

import android.content.ContentValues
import android.content.Context
import com.joao.login.model.InfoModel
import com.joao.login.model.PostModel
import com.joao.login.model.ProfModel
import com.joao.login.model.UserModel

// Manipula os dados

class UserRepository private constructor(context: Context) {

    private val userDataBase = UserDataBase(context)

    companion object {
        private lateinit var repository: UserRepository

        fun getInstance(context: Context): UserRepository {
            if (!Companion::repository.isInitialized) {
                repository = UserRepository(context)
            }
            return repository
        }
    }

    fun insertUser (user: UserModel): Boolean {
        return try {
            val db = userDataBase.writableDatabase
            val values = ContentValues()

            values.put(UserDataBase.COLUMNS.NAME, user.name)
            values.put(UserDataBase.COLUMNS.EMAIL, user.email)
            values.put(UserDataBase.COLUMNS.PASSWORD, user.password)
            values.put(UserDataBase.COLUMNS.AREA, user.area)
            values.put(UserDataBase.COLUMNS.MODALIDADE, user.modalidade)

            db.insert(UserDataBase.TABLE_USERS, null, values)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun insertProf (user: ProfModel): Boolean {
        return try {
            val db = userDataBase.writableDatabase
            val values = ContentValues()

            values.put(UserDataBase.COLUMNS.NAME, user.name)
            values.put(UserDataBase.COLUMNS.EMAIL, user.email)
            values.put(UserDataBase.COLUMNS.PASSWORD, user.password)
            values.put(UserDataBase.COLUMNS.AREA, user.area)
            values.put(UserDataBase.COLUMNS.MODALIDADE, user.modalidade)
            values.put(UserDataBase.COLUMNS.REGISTER, user.register)

            db.insert(UserDataBase.TABLE_PROF, null, values)
            true
        } catch (e: Exception) {
            false
        }
    }



    fun insertPost (info: PostModel): Boolean {
        return try {
            val db = userDataBase.writableDatabase

            val values = ContentValues()

            values.put(UserDataBase.COLUMNS.USER_ID, info.USER_ID)
            values.put(UserDataBase.COLUMNS.POST_TITLE, info.title)
            values.put(UserDataBase.COLUMNS.POST_CONTENT, info.content)
            values.put(UserDataBase.COLUMNS.POST_DATE, info.date)

            if (info.imageBlob != null) {
                values.put(UserDataBase.COLUMNS.POST_IMAGE, info.imageUri)
                values.put(UserDataBase.COLUMNS.POST_IMAGE, info.imageBlob)
            }





            db.insert(UserDataBase.TABLE_POSTS, null, values)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun update(user: UserModel): Boolean {
        return try {
            val db = userDataBase.writableDatabase

            val values = ContentValues()

            values.put(UserDataBase.COLUMNS.NAME, user.name)
            values.put(UserDataBase.COLUMNS.EMAIL, user.email)
            values.put(UserDataBase.COLUMNS.AREA, user.area)
            values.put(UserDataBase.COLUMNS.MODALIDADE, user.modalidade)

            val selection = UserDataBase.COLUMNS.ID + "= ? "
            val args = arrayOf(user.id.toString())


            db.update(UserDataBase.TABLE_USERS, values, selection, args)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun delete(user: UserModel): Boolean {
        return try {
            val db = userDataBase.writableDatabase


            val selection = UserDataBase.COLUMNS.ID + "= ? "
            val args = arrayOf(user.id.toString())


            db.delete(UserDataBase.TABLE_USERS, selection, args)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getAll() : List<UserModel> {

        val list = mutableListOf<UserModel>()
        try {
            val db = userDataBase.readableDatabase
            val selection = arrayOf(
                UserDataBase.COLUMNS.ID,
                UserDataBase.COLUMNS.NAME,
                UserDataBase.COLUMNS.EMAIL,
                UserDataBase.COLUMNS.PASSWORD,
                UserDataBase.COLUMNS.AREA,
                UserDataBase.COLUMNS.MODALIDADE
            )
            val cursor = db.query(
                UserDataBase.TABLE_USERS,
                selection,
                null,
                null,
                null,
                null,
                UserDataBase.COLUMNS.NAME
            )

            if (cursor != null && cursor.count > 0){
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex(UserDataBase.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(UserDataBase.COLUMNS.NAME))
                    val email = cursor.getString(cursor.getColumnIndex(UserDataBase.COLUMNS.EMAIL))
                    val password = cursor.getString(cursor.getColumnIndex(UserDataBase.COLUMNS.PASSWORD))
                    val modalidade = cursor.getString(cursor.getColumnIndex(UserDataBase.COLUMNS.MODALIDADE))
                    var area = cursor.getString(cursor.getColumnIndex(UserDataBase.COLUMNS.AREA))


                    list.add(UserModel(id, name, email, password, modalidade, area))

                }
            }
            cursor.close()
        } catch (e: Exception){
            return list
        }
        return list
    }

    fun getArea(filter: Int) : List<UserModel> {

        val list = mutableListOf<UserModel>()
        try {
            val db = userDataBase.readableDatabase

           val cursor = db.rawQuery("SELECT id, name, modalidade From User WHERE area = $filter", null)

            if (cursor != null && cursor.count > 0){
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex(UserDataBase.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(UserDataBase.COLUMNS.NAME))
                    val email = cursor.getString(cursor.getColumnIndex(UserDataBase.COLUMNS.EMAIL))
                    val password = cursor.getString(cursor.getColumnIndex(UserDataBase.COLUMNS.PASSWORD))
                    val modalidade = cursor.getString(cursor.getColumnIndex(UserDataBase.COLUMNS.MODALIDADE))
                    var area = cursor.getString(cursor.getColumnIndex(UserDataBase.COLUMNS.AREA))


                    list.add(UserModel(id, name, email, password, modalidade, area))

                }
            }

            cursor.close()
        } catch (e: Exception){
            return list
        }

        return list
    }

    fun loginUser(email: String, password: String): Boolean {
        val db = userDataBase.readableDatabase

        val columns = arrayOf(
            UserDataBase.COLUMNS.ID,
            UserDataBase.COLUMNS.EMAIL,
            UserDataBase.COLUMNS.PASSWORD
        )

        val selection = "${UserDataBase.COLUMNS.EMAIL} = ? AND ${UserDataBase.COLUMNS.PASSWORD} = ?"
        val selectionArgs = arrayOf(email, password)

        val cursor = db.query(
            UserDataBase.TABLE_USERS,
            columns,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        val loginSuccess = cursor != null && cursor.count > 0

        cursor?.close()

        return loginSuccess
    }

    fun getAllPosts(): List<PostModel> {
        val list = mutableListOf<PostModel>()
        try {
            val db = userDataBase.readableDatabase
            val selection = arrayOf(
                UserDataBase.COLUMNS.POST_ID,
                UserDataBase.COLUMNS.USER_ID,
                UserDataBase.COLUMNS.POST_TITLE,
                UserDataBase.COLUMNS.POST_CONTENT,
                UserDataBase.COLUMNS.POST_DATE,
                UserDataBase.COLUMNS.POST_URI_IMAGE,
                UserDataBase.COLUMNS.POST_IMAGE

            )
            val cursor = db.query(
                UserDataBase.TABLE_POSTS,
                selection,
                null,
                null,
                null,
                null,
                UserDataBase.COLUMNS.POST_ID
            )

            if (cursor != null && cursor.count > 0){
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex(UserDataBase.COLUMNS.POST_ID))
                    val userID = cursor.getInt(cursor.getColumnIndex(UserDataBase.COLUMNS.USER_ID))
                    val title = cursor.getString(cursor.getColumnIndex(UserDataBase.COLUMNS.POST_TITLE))
                    val content = cursor.getString(cursor.getColumnIndex(UserDataBase.COLUMNS.POST_CONTENT))
                    val date = cursor.getString(cursor.getColumnIndex(UserDataBase.COLUMNS.POST_DATE))
                    val imageUri = cursor.getString(cursor.getColumnIndex(UserDataBase.COLUMNS.POST_URI_IMAGE))
                    val imageBlob = cursor.getBlob(cursor.getColumnIndex(UserDataBase.COLUMNS.POST_IMAGE))



                    list.add(PostModel(id, userID, title, content, date,imageUri,imageBlob))

                }
            }
            cursor.close()
        } catch (e: Exception){
            return list
        }
        return list
    }

}







