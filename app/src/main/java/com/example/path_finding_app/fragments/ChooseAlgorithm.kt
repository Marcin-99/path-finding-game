package com.example.path_finding_app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.path_finding_app.MainActivity
import com.example.path_finding_app.R

class ChooseAlgorithm : Fragment() {

    protected lateinit var DijkstraButton: View
    protected lateinit var BestFirstButton: View
    protected lateinit var HierarchicalPathButton: View

    protected lateinit var SandboxModeButton: View
    protected lateinit var CompetetiveModeButton: View

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
        if (selectedMode !== "sandbox")
            changeBtnColor(SandboxModeButton, false)
        if (selectedMode !== "competetive")
            changeBtnColor(CompetetiveModeButton, false)
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

        SandboxModeButton.setOnClickListener {
            selectedMode = "sandbox"
            clearRestModes()
            changeBtnColor(SandboxModeButton, true)
        }
        CompetetiveModeButton.setOnClickListener {
            selectedMode = "competetive"
            clearRestModes()
            changeBtnColor(CompetetiveModeButton, true)
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
        SandboxModeButton = root.findViewById<View>(R.id.sandboxModeButton)
        CompetetiveModeButton = root.findViewById<View>(R.id.competetiveModeButton)
        StartButton = root.findViewById<View>(R.id.startButton)

        setOnClickListeners(root)
        return root
    }
}