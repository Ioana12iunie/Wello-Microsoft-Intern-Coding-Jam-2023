package com.example.wello.ui.planner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.wello.databinding.FragmentPlannerBinding
import com.google.android.material.slider.Slider

class PlannerFragment : Fragment() {

    private var _binding: FragmentPlannerBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(PlannerViewModel::class.java)

        _binding = FragmentPlannerBinding.inflate(inflater, container, false)
        val root: View = binding.root


        binding.sliderSleep.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: Slider) {
            }

            override fun onStopTrackingTouch(slider: Slider) {
            }
        })

        binding.sliderSleep.addOnChangeListener { slider, value, fromUser ->
            // we will need to update a variable we'll later use to prompt gpt4
        }

        binding.sliderExercise.addOnChangeListener { slider, value, fromUser ->
            // we will need to update a variable we'll later use to prompt gpt4
        }

        binding.sliderMeditate.addOnChangeListener { slider, value, fromUser ->
            // we will need to update a variable we'll later use to prompt gpt4
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}