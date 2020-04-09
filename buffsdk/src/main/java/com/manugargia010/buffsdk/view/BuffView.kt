package com.manugargia010.buffsdk.view

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.manugargia010.buffsdk.model.*
import com.manugargia010.buffsdk2.R
import douglasspgyn.com.github.circularcountdown.CircularCountdown
import douglasspgyn.com.github.circularcountdown.listener.CircularListener

class BuffView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    companion object {
        private const val CLOSE_VIEW_AFTER_ANSWER_DURATION = 2000L
        private const val ANSWERS_LIST_MAX_SIZE = 5
    }

    private var answerIndex = 'A'

    private val answerRowLayoutParams: LayoutParams by lazy { initAnswerRowLayoutParams() }

    private val buffView: ConstraintLayout =
        View.inflate(context, R.layout.buff_view, this) as ConstraintLayout

    init {
       //todo: add attr and initialize them here
    }

    fun showBuffControls(
        buffAnswerListener: OnBuffAnswerClickedListener,
        buffData: Buff
    ) {
        setUpCloseButton()

        setAuthorView(buffData.author)

        setQuestionView(buffData.question)

        setCountDownTimerView(buffData.timeToShow)

        setAnswersView(buffData.answers, buffAnswerListener)
    }

    private fun setUpCloseButton() {
        val closeIcon = buffView.findViewById<ImageView>(R.id.closeIcon)
        closeIcon.setOnClickListener { closeQuestionnaire() }
    }

    private fun setAuthorView(author: Author) {
        val authorFirstName = author.firstName
        val authorLastName = author.lastName
        val authorFullName = "$authorFirstName $authorLastName"

        val authorTextView = buffView.findViewById<TextView>(R.id.authorName)
        authorTextView.text = authorFullName

        Glide.with(buffView)
            .load(author.imageUrl)
            .placeholder(R.drawable.ic_person_black_24dp)
            .into(buffView.findViewById(R.id.authorImage) as ImageView)

    }

    private fun setQuestionView(question: Question) {
        val questionTextView = buffView.findViewById<TextView>(R.id.questionText)
        questionTextView.text = question.title
    }

    private lateinit var countdownView: CircularCountdown

    private fun setCountDownTimerView(seconds: Int) {
        countdownView = buffView.findViewById(R.id.countdown)

        countdownView.disableLoop()

        countdownView
            .create(1, seconds, CircularCountdown.TYPE_SECOND)
            .listener(object : CircularListener {
                override fun onTick(progress: Int) {
                    if (progress == seconds)
                        closeQuestionnaire()
                }

                override fun onFinish(newCycle: Boolean, cycleCount: Int) {
                }
            })
            .start()
    }

    private fun closeQuestionnaire() {
        (parent as ViewGroup).removeView(this)
    }

    private fun setAnswersView(
        answers: List<Answer>,
        buffAnswerClickedListener: OnBuffAnswerClickedListener
    ) {

        val answersList = answers.take(ANSWERS_LIST_MAX_SIZE)
        // Add answer rows to container
        val answersContainer =
            buffView.findViewById<LinearLayout>(R.id.answersContainer)
        for (answer in answersList) {
            val rowAnswer = inflateAnswerRowView(answersContainer)

            // Set Alphabetical index
            val indexTextView = rowAnswer.findViewById<TextView>(R.id.indexText)
            indexTextView.text = answerIndex.toString()

            val answerTextView =
                rowAnswer.findViewById<TextView>(R.id.answerText)
            answerTextView.text = answer.title

            rowAnswer.setOnClickListener {
                stopCountDown()

                Handler().postDelayed({
                    closeQuestionnaire()
                }, CLOSE_VIEW_AFTER_ANSWER_DURATION)

                buffAnswerClickedListener.onBuffAnswerClicked(answer)
            }

            answersContainer.addView(rowAnswer)

            answerIndex = answerIndex.inc()
        }
    }

    private fun stopCountDown() {
        countdownView.stop()
    }

    private fun inflateAnswerRowView(answersLayout: LinearLayout): View {
        val view = LayoutInflater.from(answersLayout.context)
            .inflate(R.layout.row_answer, answersLayout, false)
        view.layoutParams = answerRowLayoutParams
        return view
    }

    private fun initAnswerRowLayoutParams(): LayoutParams {
        val answerRowLayoutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
        answerRowLayoutParams.bottomMargin =
            resources.getDimension(R.dimen.buffAnswerMarginBottom).toInt()
        return answerRowLayoutParams
    }
}

interface OnBuffAnswerClickedListener {
    fun onBuffAnswerClicked(answer: Answer)
}
