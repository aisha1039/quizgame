package com.example.homeworkmodule05.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.homeworkmodule05.*

class QuestionPagerAdapter(
    activity: FragmentActivity,
    private val onAnswerSelected: (Boolean) -> Unit  // Callback to handle correct/incorrect answers
) : FragmentStateAdapter(activity) {

    private val questionFragments = listOf(
        QuestionFragment1(onAnswerSelected),
        QuestionFragment2(onAnswerSelected),
        QuestionFragment3(onAnswerSelected),
        QuestionFragment4(onAnswerSelected),
        QuestionFragment5(onAnswerSelected),
        QuestionFragment6(onAnswerSelected),
        QuestionFragment7(onAnswerSelected)
    )

    override fun getItemCount(): Int {
        return questionFragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return questionFragments[position]
    }
}
