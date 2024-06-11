package com.example.lemonrecipe

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.lemonrecipe.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var rvAdapter: PopularAdapter
    private lateinit var dataList: ArrayList<Recipe>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()
        binding.search.setOnClickListener {
            startActivity(Intent(this,SearchActivity::class.java))
        }
        binding.appetizer.setOnClickListener {
            var myIntent=Intent(this@HomeActivity,CategoryActivity::class.java)
            myIntent.putExtra("TITLE","Appetizer")
            myIntent.putExtra("CATEGORY","Appetizer")
            startActivity(myIntent)
        }
        binding.bev.setOnClickListener {
            var myIntent=Intent(this@HomeActivity,CategoryActivity::class.java)
            myIntent.putExtra("TITLE","Beverage")
            myIntent.putExtra("CATEGORY","Beverage")
            startActivity(myIntent)
        }
        binding.breakfast.setOnClickListener {
            var myIntent=Intent(this@HomeActivity,CategoryActivity::class.java)
            myIntent.putExtra("TITLE","Breakfast")
            myIntent.putExtra("CATEGORY","Breakfast")
            startActivity(myIntent)
        }
        binding.dessert.setOnClickListener {
            var myIntent=Intent(this@HomeActivity,CategoryActivity::class.java)
            myIntent.putExtra("TITLE","Dessert")
            myIntent.putExtra("CATEGORY","Dessert")
            startActivity(myIntent)
        }
        binding.main.setOnClickListener {
            var myIntent=Intent(this@HomeActivity,CategoryActivity::class.java)
            myIntent.putExtra("TITLE","Main dish")
            myIntent.putExtra("CATEGORY","Main dish")
            startActivity(myIntent)
        }
        binding.salad.setOnClickListener {
            var myIntent=Intent(this@HomeActivity,CategoryActivity::class.java)
            myIntent.putExtra("TITLE","Salad")
            myIntent.putExtra("CATEGORY","Salad")
            startActivity(myIntent)
        }
        binding.side.setOnClickListener {
            var myIntent=Intent(this@HomeActivity,CategoryActivity::class.java)
            myIntent.putExtra("TITLE","Side dish")
            myIntent.putExtra("CATEGORY","Side dish")
            startActivity(myIntent)
        }
        binding.snack.setOnClickListener {
            var myIntent=Intent(this@HomeActivity,CategoryActivity::class.java)
            myIntent.putExtra("TITLE","Snack")
            myIntent.putExtra("CATEGORY","Snack")
            startActivity(myIntent)
        }
        binding.soup.setOnClickListener {
            var myIntent=Intent(this@HomeActivity,CategoryActivity::class.java)
            myIntent.putExtra("TITLE","Soup")
            myIntent.putExtra("CATEGORY","Soup")
            startActivity(myIntent)
        }
    }

    private fun setUpRecyclerView() {
        dataList = ArrayList()
        binding.rvPopular.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        var db = Room.databaseBuilder(this@HomeActivity, AppDatabase::class.java, "db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()
        var daoObject = db.getDao()
        var recipes = daoObject.getAll()
        for (i in recipes!!.indices) {
            if (recipes[i]!!.category.contains("Popular")) {
                dataList.add(recipes[i]!!)
            }
            rvAdapter = PopularAdapter(dataList, this)
            binding.rvPopular.adapter = rvAdapter
        }
    }
}