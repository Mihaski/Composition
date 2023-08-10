package com.example.composition.presentation

import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.composition.R
import com.example.composition.domain.entity.GameResult

@BindingAdapter("minEnAns")
fun bindMinEnAns(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.tv_required_answer_text),
        count
    )
}

@BindingAdapter("Score")
fun bindScore(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.tv_score_answer_text),
        count
    )
}

@BindingAdapter("minEnPerc")
fun bindMinEnPerc(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.tv_required_perc_text),
        count
    )
}

@BindingAdapter("ScorePerc")
fun bindScorePerc(textView: TextView, gameResult: GameResult) {
    val value = if (gameResult.countOfQuestions == 0) {
        0
    } else {
        ((gameResult.countOfRightAnswers / gameResult.countOfQuestions.toDouble()) * 100).toInt()
    }
    textView.text = String.format(
        textView.context.getString(R.string.tv_score_perc_text),
        value
    )
}

@BindingAdapter("setImage")
fun bindSetImage(imageView: ImageView, win: Boolean) {
    val getEmoResId = if (win) R.drawable.happy_win_smile
    else R.drawable.sad_lose_smile
    imageView.setImageResource(getEmoResId)
}

@BindingAdapter("setColorText")
fun bindColorText(textView: TextView, count: Boolean) {
    val part = ContextCompat.getColor(textView.context, getColorByBooleanState(count))
    textView.setTextColor(part)
}

private fun getColorByBooleanState(count: Boolean): Int {
    return if (count) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
}

@BindingAdapter("setColorBar")
fun bindColorBar(progressBar: ProgressBar, count: Int) {
    val color = if (count >= progressBar.secondaryProgress) android.R.color.holo_green_light
    else android.R.color.holo_red_light

    val part = ContextCompat.getColor(progressBar.context, color)
    progressBar.progressTintList = ColorStateList.valueOf(part)
}

@BindingAdapter("numberAsText")
fun bindNumberAsText(textView: TextView, number: Int) {
    textView.text = number.toString()
}

interface OnClickListeners {
    fun onClickListener(option: Int)
}

@BindingAdapter("onOptionClick")
fun bindOnClickListener(textView: TextView, clickListener: OnClickListeners) {
    textView.setOnClickListener {
        clickListener.onClickListener(textView.text.toString().toInt())
    }
}