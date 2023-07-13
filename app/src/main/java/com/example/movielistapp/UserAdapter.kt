package com.example.movielistapp


import com.example.movielistapp.model.UserModel
import androidx.recyclerview.widget.RecyclerView
import com.example.movielistapp.Database.SqliteData
import android.view.ViewGroup
import android.view.LayoutInflater
import com.example.movielistapp.R
import android.annotation.SuppressLint
import android.content.Context
import android.text.Html
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import java.util.ArrayList

class UserAdapter(var arrayList: ArrayList<UserModel>, var context: Context) :
    RecyclerView.Adapter<UserAdapter.holder>() {
    var sqliteDB: SqliteData? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.gridlayout_item, parent, false)
        return holder(view)
    }

    override fun onBindViewHolder(holder: holder, @SuppressLint("RecyclerView") position: Int) {
        sqliteDB = SqliteData(context)
        val name = "Name : <b>" + arrayList[position].user_name + "</b>"
        holder.Username.text = Html.fromHtml(name)
        val City = "City : <b>" + arrayList[position].user_city + "</b>"
        holder.UserID.text = Html.fromHtml(City)
        val email = "Email : <b>" + arrayList[position].user_email + "</b>"
        holder.UserEmail.text = Html.fromHtml(email)
        val phone = "Phone : <b>" + arrayList[position].user_mobile + "</b>"
        holder.Usermobile.text = Html.fromHtml(phone)
        val bitmapdata = arrayList[position].user_img
        val bitmap = BitmapFactory.decodeByteArray(bitmapdata, 0, bitmapdata.size)
        if (bitmap != null) {
            holder.User_img_item.setImageBitmap(bitmap)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var Username: TextView
        var Usermobile: TextView
        var UserID: TextView
        var UserEmail: TextView
        var deleteUser: ImageView? = null
        var User_img_item: ImageView

        init {
            Username = itemView.findViewById(R.id.user_name_item)
            Usermobile = itemView.findViewById(R.id.user_mobile_item)
            UserEmail = itemView.findViewById(R.id.user_email_item)
            UserID = itemView.findViewById(R.id.user_id_item)
            User_img_item = itemView.findViewById(R.id.user_img_item)
        }
    }
}