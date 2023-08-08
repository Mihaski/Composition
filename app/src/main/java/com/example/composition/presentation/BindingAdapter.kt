package com.example.composition.presentation

import android.widget.ImageView
import android.widget.TextView
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
fun bindSetImage(imageView: ImageView, win:Boolean) {
    val getEmoResId = if (win) R.drawable.happy_win_smile
    else R.drawable.sad_lose_smile
    imageView.setImageResource(getEmoResId)
}