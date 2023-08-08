package com.example.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.composition.R
import com.example.composition.databinding.FragmentGameFinishedBinding

class GameFinishedFragment : Fragment() {

    private val args by navArgs<GameFinishedFragmentArgs>()

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setValueGResult()

        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
    }

    private fun setValueGResult() {
        with(binding) {
            emojiResult.setImageResource(getEmoResId())
            tvRequiredAnswers.text = String.format(
                getString(R.string.tv_required_answer_text),
                args.gameResult.gameSettings.minCountOfRightAnswers
            )
            tvScoreAnswers.text = String.format(
                getString(R.string.tv_score_answer_text),
                args.gameResult.countOfRightAnswers
            )
            tvRequiredPercentage.text = String.format(
                getString(R.string.tv_required_perc_text),
                args.gameResult.gameSettings.minPercentOfRightAnswers
            )
            tvScorePercentage.text = String.format(
                getString(R.string.tv_score_perc_text),
                calcPercentOfRightAnswer()
            )
        }
    }

    private fun getEmoResId(): Int {
        return if (args.gameResult.winner) R.drawable.happy_win_smile
        else R.drawable.sad_lose_smile
    }

    private fun calcPercentOfRightAnswer() = with(args.gameResult) {
        if (countOfQuestions == 0) {
            0
        } else {
            ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun retryGame() {
        findNavController().popBackStack()
    }
}