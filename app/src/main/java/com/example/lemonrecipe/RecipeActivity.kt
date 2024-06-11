package com.example.lemonrecipe

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.lemonrecipe.databinding.ActivityRecipeBinding

class RecipeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecipeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this).load(intent.getStringExtra("img")).into(binding.itemImage)
        binding.title.text = intent.getStringExtra("title")
        binding.ingData.text = intent.getStringExtra("ing")
        binding.stepsData.text = intent.getStringExtra("des")
        var ing =
            intent.getStringExtra("ing")?.split("\n".toRegex())?.dropLastWhile { it.isEmpty() }
                ?.toTypedArray()
        binding.time.text = ing?.get(0)
        for (i in 1 until ing!!.size) {
            binding.ingData.text = """
                ${binding.ingData.text} - ${ing[i]}
            """.trimIndent()
        }
        binding.steps.background = null
        binding.steps.setTextColor(getColor(R.color.black))
        binding.steps.setOnClickListener {
            binding.steps.setBackgroundResource(R.drawable.btn_ing)
            binding.steps.setTextColor(getColor(R.color.green))
            binding.ing.setTextColor(getColor(R.color.black))
            binding.ing.background = null
            binding.stepsScroll.visibility = View.VISIBLE
            binding.ingScroll.visibility = View.GONE
        }

        binding.ing.setOnClickListener {
            binding.ing.setBackgroundResource(R.drawable.btn_ing)
            binding.ing.setTextColor(getColor(R.color.green))
            binding.steps.setTextColor(getColor(R.color.black))
            binding.steps.background = null
            binding.ingScroll.visibility = View.VISIBLE
            binding.stepsScroll.visibility = View.GONE
            }
        binding.backBtn.setOnClickListener {
            finish()
        }
    }
}