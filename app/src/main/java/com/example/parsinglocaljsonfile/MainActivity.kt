package com.example.parsinglocaljsonfile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.json.JSONArray
import java.io.BufferedReader
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var myRv: RecyclerView
    private  var images = ArrayList<String>()
    lateinit var imageView1: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myRv = findViewById(R.id.recyclerView)
        imageView1 = findViewById(R.id.imageView1)

        imageView1.setOnClickListener { closeImage() }

        try{
            val iStream = assets.open("data.json")
            val json=iStream.bufferedReader().use { it.readText() }
            val jsonArray= JSONArray(json)

            for (image in 0..jsonArray.length()-1){
                var imageObj=jsonArray.getJSONObject(image)
                val url=imageObj.getString("url")
                images.add(url)
            }

            myRv.adapter = RecyclerViewAdapter(this,images)
            myRv.layoutManager = LinearLayoutManager(this)
        }catch (e: IOException) {
            println("ISSUE: $e")
        }
    }
    //open image glide
    fun openImage(link: String){
        Glide.with(this)
            .load(link)
            .into(imageView1)
        imageView1.isVisible = true
        myRv.isVisible = false
    }
    // close image
    private fun closeImage(){
        imageView1.isVisible = false
        myRv.isVisible = true
    }
}