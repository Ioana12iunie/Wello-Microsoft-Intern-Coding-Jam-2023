package com.example.wello.ui.planner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.wello.databinding.FragmentPlannerBinding
import com.google.android.material.slider.Slider

class PlannerFragment : Fragment() {

    private var _binding: FragmentPlannerBinding? = null

    private val binding get() = _binding!!

    private var sleepAmount: Int = 0
    private var exerciseAmount: Int = 0
    private var meditateAmount: Int = 0

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
            sleepAmount = value.toInt()
        }

        binding.sliderExercise.addOnChangeListener { slider, value, fromUser ->
            // we will need to update a variable we'll later use to prompt gpt4
            exerciseAmount = value.toInt()
        }

        binding.sliderMeditate.addOnChangeListener { slider, value, fromUser ->
            // we will need to update a variable we'll later use to prompt gpt4
            meditateAmount = value.toInt()
        }

        binding.button2.setOnClickListener {
            Toast.makeText(context, "Sleep: " + sleepAmount.toString() + " Exercise: " + exerciseAmount.toString() + " Meditate: " + meditateAmount.toString(), Toast.LENGTH_LONG).show()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}