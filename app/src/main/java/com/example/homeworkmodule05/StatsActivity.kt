package com.example.homeworkmodule05

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class StatsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)

        // Get the score and total number of questions from the intent
        val correctAnswers = intent.getIntExtra("correctAnswers", 0)
        val totalQuestions = intent.getIntExtra("totalQuestions", 7)

        // Display the score
        val scoreTextView: TextView = findViewById(R.id.tv_score)
        scoreTextView.text = "You got $correctAnswers / $totalQuestions correct!"
    }
}
