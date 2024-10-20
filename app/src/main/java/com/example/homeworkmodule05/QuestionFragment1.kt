package com.example.homeworkmodule05

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class QuestionFragment1(private val onAnswerSelected: (Boolean) -> Unit) : Fragment() {

    private lateinit var confirmButton: Button
    private lateinit var radioGroup: RadioGroup
    private lateinit var timerTextView: TextView  // Timer display
    private lateinit var timer: CountDownTimer  // Countdown timer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_question1, container, false)

        confirmButton = view.findViewById(R.id.btn_confirm)
        radioGroup = view.findViewById(R.id.radioGroup)
        timerTextView = view.findViewById(R.id.timerTextView)

        startTimer()  // Start the timer

        confirmButton.setOnClickListener {
            timer.cancel()  // Stop the timer when the user submits an answer
            val selectedOption = radioGroup.checkedRadioButtonId
            if (selectedOption != -1) {
                val radioButton: RadioButton = view.findViewById(selectedOption)
                val isCorrect = selectedOption == R.id.radio_option1  // Assume option 1 is correct

                // Change the button color based on correctness
                if (isCorrect) {
                    radioButton.setBackgroundColor(Color.GREEN)
                } else {
                    radioButton.setBackgroundColor(Color.RED)
                }

                onAnswerSelected(isCorrect)
            } else {
                (activity as MainActivity).showToast("Please select an answer!")
            }
        }

        return view
    }

    // Start the countdown timer (e.g., 30 seconds)
    private fun startTimer() {
        timer = object : CountDownTimer(30000, 1000) {  // 30-second timer
            override fun onTick(millisUntilFinished: Long) {
                timerTextView.text = "Time left: ${millisUntilFinished / 1000}s"
            }

            override fun onFinish() {
                // If time runs out, mark the question as incorrect and go to the next question
                (activity as MainActivity).showToast("Time's up!")
                onAnswerSelected(false)  // Automatically move to next with incorrect answer
            }
        }.start()
    }
}
