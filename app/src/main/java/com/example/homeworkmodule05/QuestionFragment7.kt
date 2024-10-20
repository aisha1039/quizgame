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

class QuestionFragment7(private val onAnswerSelected: (Boolean) -> Unit) : Fragment() {

    private lateinit var confirmButton: Button
    private lateinit var radioGroup: RadioGroup
    private lateinit var timerTextView: TextView
    private lateinit var timer: CountDownTimer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_question7, container, false)

        confirmButton = view.findViewById(R.id.btn_confirm)
        radioGroup = view.findViewById(R.id.radioGroup)
        timerTextView = view.findViewById(R.id.timerTextView)

        startTimer()

        confirmButton.setOnClickListener {
            timer.cancel()
            val selectedOption = radioGroup.checkedRadioButtonId
            if (selectedOption != -1) {
                val radioButton: RadioButton = view.findViewById(selectedOption)
                val isCorrect = selectedOption == R.id.radio_option3  // Assume option 3 is correct

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

    private fun startTimer() {
        timer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timerTextView.text = "Time left: ${millisUntilFinished / 1000}s"
            }

            override fun onFinish() {
                (activity as MainActivity).showToast("Time's up!")
                onAnswerSelected(false)
            }
        }.start()
    }
}
