package com.example.path_finding_app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.path_finding_app.MainActivity
import com.example.path_finding_app.R

class Summary : Fragment() {

    protected lateinit var root: View

    override fun onResume() {
        if ((activity as MainActivity).isGameFinished === true) {
            val congratulationsText: TextView =
                root.findViewById<View>(R.id.congratulationsText) as TextView
            congratulationsText.text = "Congratulations!"

            val algorithmText: TextView =
                root.findViewById<View>(R.id.algorithmCongratulationsText) as TextView
            algorithmText.text =
                "You finished all levels for ${(activity as MainActivity).selectedAlgorithm} algorithm."

            val yourScoreText: TextView = root.findViewById<View>(R.id.yourScoreText) as TextView
            yourScoreText.text = "Your score:"

            val summaryScoreText: TextView = root.findViewById<View>(R.id.summaryScore) as TextView
            summaryScoreText.text = "${(activity as MainActivity).score}/7500"
        }
        else {
            val congratulationsText: TextView =
                root.findViewById<View>(R.id.congratulationsText) as TextView
            congratulationsText.text = "To see summary you need to finish the game."
        }

        super.onResume()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_summary, container, false)
        return root
    }
}