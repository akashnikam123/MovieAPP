package com.example.movielistapp


import com.example.movielistapp.Database.SqliteData
import androidx.recyclerview.widget.RecyclerView
import com.example.movielistapp.model.UserModel
import android.os.Bundle
import android.content.Intent
import android.app.Activity
import android.widget.Toast
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.movielistapp.R
import com.google.android.material.textfield.TextInputEditText
import androidx.core.content.FileProvider
import android.provider.MediaStore
import android.graphics.drawable.BitmapDrawable
import android.graphics.Bitmap
import android.net.Uri
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movielistapp.UserAdapter
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.lang.RuntimeException
import java.util.ArrayList

class Fragment_Third : Fragment() {
    var sqliteData: SqliteData? = null
    private var photoURI: Uri? = null
    private val cameraResult = 1001
    lateinit var image_displayIV: ImageView
    var recyclerView: RecyclerView? = null
    var arrayList = ArrayList<UserModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == cameraResult) {
            image_displayIV!!.setImageURI(photoURI)
        } else {
            Toast.makeText(context, "error to capture image", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment__third, container, false)
        val UserNameET: TextInputEditText = view.findViewById(R.id.User_Name_ET)
        val UserMobileET: TextInputEditText = view.findViewById(R.id.User_mobile_ET)
        val UserCity: TextInputEditText = view.findViewById(R.id.User_City_ET)
        val UserEmailET: TextInputEditText = view.findViewById(R.id.User_email_ET)
        image_displayIV = view.findViewById(R.id.image_displayIV)
        val addImageIV = view.findViewById<ImageView>(R.id.addImageIV)
        recyclerView = view.findViewById(R.id.recyclerview)
        sqliteData = SqliteData(context)
        UpdateRV()
        val submitBTN = view.findViewById<Button>(R.id.sumitBTN)
        addImageIV.setOnClickListener {
            try {
                val imgFile = File.createTempFile("cameraphoto", ".png", requireContext().filesDir)
                photoURI = FileProvider.getUriForFile(
                    requireContext(),
                    "com.example.movielistapp.fileprovider", imgFile
                )
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                startActivityForResult(intent, cameraResult)
            } catch (e: IOException) {
                throw RuntimeException(e)
            }
        }
        submitBTN.setOnClickListener {
            val name = UserNameET.text.toString()
            val mobile = UserMobileET.text.toString()
            val email = UserEmailET.text.toString()
            val city = UserCity.text.toString()

            val drawable = image_displayIV.getDrawable() as BitmapDrawable
            val bitmap1 = drawable.bitmap
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap1.compress(Bitmap.CompressFormat.JPEG, 10, byteArrayOutputStream)
            val img1 = byteArrayOutputStream.toByteArray()
            if (!name.isEmpty() && !mobile.isEmpty() && !email.isEmpty() && !city.isEmpty() && img1.size > 0) {
                if (mobile.length != 10) {
                    Toast.makeText(context, "Invalid Phone", Toast.LENGTH_SHORT).show()
                } else {
                    val model = UserModel(img1, name, mobile, email, city)
                    val isVisitorAdded = sqliteData!!.adduser(model)
                    if (isVisitorAdded) {
                        Toast.makeText(context, "Visitor Added Successful", Toast.LENGTH_SHORT)
                            .show()
                        UpdateRV()
                        UserNameET.setText("")
                        UserMobileET.setText("")
                        UserEmailET.setText("")
                        UserCity.setText("")
                        image_displayIV.setImageResource(R.drawable.visitor_logo)
                    } else {
                        Toast.makeText(context, "Error to add user", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(context, "all fields are mandatory", Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }

    private fun UpdateRV() {
        arrayList.clear()
        arrayList = sqliteData!!.allusers
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 2)
        recyclerView!!.layoutManager = layoutManager
        val adapter = context?.let { UserAdapter(arrayList, it) }
        recyclerView!!.adapter = adapter
    }
}