package com.example.homeworkmodule05

import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.homeworkmodule05.adapters.QuestionPagerAdapter

class MainActivity : FragmentActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var pagerAdapter: QuestionPagerAdapter
    private lateinit var progressBar: ProgressBar

    private var correctAnswers = 0  // Track correct answers
    private val totalQuestions = 7  // Set the total number of questions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize progress bar
        progressBar = findViewById(R.id.progressBar)
        progressBar.max = totalQuestions  // Max progress set to the total number of questions

        // Initialize ViewPager2 and Adapter
        viewPager = findViewById(R.id.viewPager)
        pagerAdapter = QuestionPagerAdapter(this, ::onAnswerSelected)

        // Set the adapter to the ViewPager2
        viewPager.adapter = pagerAdapter
    }

    // Function to show toast messages
    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    // Function to navigate to the next question or stats screen
    fun goToNextQuestion() {
        if (viewPager.currentItem < pagerAdapter.itemCount - 1) {
            viewPager.currentItem += 1  // Navigate to the next question
            updateProgressBar()  // Update progress bar for each question
        } else {
            // After the last question, go to the stats screen
            val intent = Intent(this, StatsActivity::class.java)
            intent.putExtra("correctAnswers", correctAnswers)  // Pass correct answers count
            intent.putExtra("totalQuestions", pagerAdapter.itemCount)  // Pass total questions
            startActivity(intent)
            finish()
        }
    }

    // Callback to handle when a correct answer is selected
    private fun onAnswerSelected(isCorrect: Boolean) {
        if (isCorrect) {
            correctAnswers += 1
            showToast("Correct!")
        } else {
            showToast("Incorrect!")
        }

        goToNextQuestion()  // Move to the next question
    }

    // Update the progress bar as the user progresses through the questions
    private fun updateProgressBar() {
        progressBar.progress = viewPager.currentItem + 1  // Update progress based on current question index
    }
}
