package com.example.movielistapp.Database

import android.database.sqlite.SQLiteOpenHelper
import com.example.movielistapp.Database.SqliteData
import android.database.sqlite.SQLiteDatabase
import android.content.ContentValues
import android.content.Context
import com.example.movielistapp.model.Movie
import com.example.movielistapp.model.UserModel
import java.util.ArrayList

class SqliteData(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, DB_Version) {
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        val qry = "Create table favourite_table(" +
                "IMDB_ID text PRIMARY KEY ," +
                "Title text, " +
                "Year text," +
                "Summary text," +
                "Short_Summary text," +
                "Genres text," +
                "Runtime text," +
                "Youtube_trailer text," +
                "Rating text," +
                "Movie_poster text," +
                "Director text," +
                "Writers text," +
                "Casts text)"
        sqLiteDatabase.execSQL(qry)
        val qry2 = "create table user_profile " +
                "(user_name text," +
                "user_phone text," +
                "user_email text," +
                "user_city text," +
                "user_img BLOB" + ")"
        sqLiteDatabase.execSQL(qry2)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {}
    fun checkFav(imdbId: String): Boolean {
        val db = this.readableDatabase
        val qry =
            db.rawQuery("select IMDB_ID from favourite_table where IMDB_ID=?", arrayOf(imdbId))
        if (qry.count > 0) {
            db.close()
            return true
        }
        db.close()
        return false
    }

    fun insertIntoFav(model: Movie): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("IMDB_ID", model.imdbId)
        cv.put("Title", model.title)
        cv.put("Year", model.year)
        cv.put("Summary", model.summary)
        cv.put("Short_Summary", model.shortSummary)
        cv.put("Genres", model.genres)
        cv.put("Runtime", model.runtime)
        cv.put("Youtube_trailer", model.trailerUrl)
        cv.put("Rating", model.rating)
        cv.put("Movie_poster", model.posterUrl)
        cv.put("Director", model.director)
        cv.put("Writers", model.writers)
        cv.put("Casts", model.cast)
        val result = db.insert("favourite_table", null, cv)
        db.close()
        return if (result == -1L) {
            false
        } else {
            true
        }
    }

    fun removeFromFav(imdbId: String) {
        val db = this.writableDatabase
        db.delete("favourite_table", "IMDB_ID=?", arrayOf(imdbId))
        db.close()
    }

    val favList: ArrayList<Movie>
        get() {
            val arrayList = ArrayList<Movie>()
            val db = this.readableDatabase
            val qry = "select * from favourite_table"
            val cursor = db.rawQuery(qry, null)
            if (cursor.moveToFirst()) {
                do {
                    val IMDB_ID = cursor.getString(0)
                    val Title = cursor.getString(1)
                    val Year = cursor.getString(2)
                    val Summary = cursor.getString(3)
                    val Short_Summary = cursor.getString(4)
                    val Genres = cursor.getString(5)
                    val Runtime = cursor.getString(6)
                    val Youtube_trailer = cursor.getString(7)
                    val Rating = cursor.getString(8)
                    val Movie_poster = cursor.getString(9)
                    val Director = cursor.getString(10)
                    val Writers = cursor.getString(11)
                    val Casts = cursor.getString(12)
                    arrayList.add(
                        Movie(
                            IMDB_ID, Title, Year, Summary, Short_Summary, Genres,
                            Runtime, Youtube_trailer, Rating, Movie_poster, Director, Writers, Casts
                        )
                    )
                } while (cursor.moveToNext())
            } else {
            }
            db.close()
            return arrayList
        }

    fun adduser(userModel: UserModel): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("user_city", userModel.user_city)
        cv.put("user_name", userModel.user_name)
        cv.put("user_phone", userModel.user_mobile)
        cv.put("user_email", userModel.user_email)
        cv.put("user_img", userModel.user_img)
        val c = db.insert("user_profile", null, cv)
        if (c != -1L) {
            db.close()
            return true
        }
        db.close()
        return false
    }

    val allusers: ArrayList<UserModel>
        get() {
            val arrayList = ArrayList<UserModel>()
            val db = this.readableDatabase
            val qry = "select * from user_profile"
            val cursor = db.rawQuery(qry, null)
            if (cursor.moveToFirst()) {
                do {
                    val name = cursor.getString(0)
                    val phone = cursor.getString(1)
                    val email = cursor.getString(2)
                    val city = cursor.getString(3)
                    val image = cursor.getBlob(4)
                    arrayList.add(UserModel(image, name, phone, email, city))
                } while (cursor.moveToNext())
            } else {
            }
            db.close()
            return arrayList
        }

    companion object {
        private const val DB_NAME = "Favourite_DB"
        private const val DB_Version = 1
    }
}