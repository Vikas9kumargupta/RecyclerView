package com.example.day24recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var myRecyclerView : RecyclerView
    lateinit var newsArrayList : ArrayList<News>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        myRecyclerView = findViewById(R.id.rView)

        val newsImageArray = arrayOf(
            R.drawable.img_0,
            R.drawable.img_1,
            R.drawable.img_2,
            R.drawable.img_3,
            R.drawable.img_4,
            R.drawable.img_5,
            R.drawable.img_6
        )

        val newsHeadingArray = arrayOf(
            "MI vs CSK Highlights, IPL 2023: CSK get 7-wicket victory vs MI",
            "PM Modi in Karnataka LIVE: PM takes jungle safari at Bandipur Tiger Reserve",
            "Covid Curbs Back In 3 States Amid Rapid Increase In Daily Cases",
            "Gaganyaan: ISRO making leaps to meet 2025 target for manned mission",
            "IMF deal well on track, assures Pakistan finance minister Dar",
            "China simulated strikes against 'key targets on Taiwan': Report",
            "Ukraine to export electricity again after months of Russian attacks"
        )

        val newsContent = arrayOf(
            getString(R.string.news1),getString(R.string.news1),getString(R.string.news1),
            getString(R.string.news1),getString(R.string.news1),getString(R.string.news1),
            getString(R.string.news1),
        )


        //to set working of inside recyclerView, vertically - horizontally scrolling, uniform grid
        myRecyclerView.layoutManager = LinearLayoutManager(this)
        newsArrayList = arrayListOf<News>()

        for(index in newsImageArray.indices){
            val news = News(newsHeadingArray[index], newsImageArray[index], newsContent[index])
            newsArrayList.add(news)
        }

        val myAdapter = MyAdapter(newsArrayList, this)
        myRecyclerView.adapter = myAdapter
        myAdapter.setItemClickListener(object : MyAdapter.onItemClickListener{
            override fun onItemClicking(position: Int) {
                //on clicking each item what item you want to perform

                val intent = Intent(applicationContext, NewsDetailActivity::class.java)
                intent.putExtra("heading",newsArrayList[position].newsHeading)
                intent.putExtra("imageId",newsArrayList[position].newsImage)
                intent.putExtra("newsContent",newsArrayList[position].newsContent)
                startActivity(intent)
            }

        })
    }
}