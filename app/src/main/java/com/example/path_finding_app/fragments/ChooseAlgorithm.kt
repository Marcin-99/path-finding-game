package com.example.path_finding_app.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.example.path_finding_app.MainActivity
import com.example.path_finding_app.R
import com.example.path_finding_app.fragments.adapters.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*


class ChooseAlgorithm : Fragment() {

    protected lateinit var DijkstraButton: View
    protected lateinit var BestFirstButton: View
    protected lateinit var HierarchicalPathButton: View

    protected lateinit var EasyModeButton: View
    protected lateinit var NormalModeButton: View

    protected lateinit var StartButton: View

    var selectedAlgorithm = ""
    var selectedMode = ""

    private fun changeBtnColor (btn: View, pressed: Boolean) {
        var color = R.color.buttonNotSubmited
        if (pressed)
            color = R.color.buttonSubmit
        btn.setBackgroundColor(resources.getColor(color))
    }

    private fun clearRestAlgorithms () {
        if (selectedAlgorithm !== "dijkstra")
            changeBtnColor(DijkstraButton, false)
        if (selectedAlgorithm !== "best-first")
            changeBtnColor(BestFirstButton, false)
        if (selectedAlgorithm !== "hierarchical-path")
            changeBtnColor(HierarchicalPathButton, false)
    }

    private fun clearRestModes () {
        if (selectedMode !== "easy-mode")
            changeBtnColor(EasyModeButton, false)
        if (selectedMode !== "normal-mode")
            changeBtnColor(NormalModeButton, false)
    }

    private fun setOnClickListeners (root: View) {
        DijkstraButton.setOnClickListener {
            selectedAlgorithm = "dijkstra"
            clearRestAlgorithms()
            changeBtnColor(DijkstraButton, true)
        }
        BestFirstButton.setOnClickListener {
            selectedAlgorithm = "best-first"
            clearRestAlgorithms()
            changeBtnColor(BestFirstButton, true)
        }
        HierarchicalPathButton.setOnClickListener {
            selectedAlgorithm = "hierarchical-path"
            clearRestAlgorithms()
            changeBtnColor(HierarchicalPathButton, true)
        }

        EasyModeButton.setOnClickListener {
            selectedMode = "easy-mode"
            clearRestModes()
            changeBtnColor(EasyModeButton, true)
        }
        NormalModeButton.setOnClickListener {
            selectedMode = "normal-mode"
            clearRestModes()
            changeBtnColor(NormalModeButton, true)
        }

        StartButton.setOnClickListener {
            if (selectedAlgorithm === "" ||  selectedMode === "") {
                (activity as MainActivity).alert("Can't start the game", "Select algorithm and mode to continue")
            }
            else {
                (activity as MainActivity).setNewLevel(1)
                (activity as MainActivity).setChoices(selectedAlgorithm, selectedMode)
                (activity as MainActivity).changeTab(1)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_choose_algorithm, container, false)
        DijkstraButton = root.findViewById<View>(R.id.dijkstraButton)
        BestFirstButton = root.findViewById<View>(R.id.bestFirstButton)
        HierarchicalPathButton = root.findViewById<View>(R.id.hierarchicalPathButton)
        EasyModeButton = root.findViewById<View>(R.id.easyModeButton)
        NormalModeButton = root.findViewById<View>(R.id.normalModeButton)
        StartButton = root.findViewById<View>(R.id.startButton)

        setOnClickListeners(root)
        return root
    }
}