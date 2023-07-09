package com.example.wello.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.wello.R
import com.example.wello.databinding.FragmentHomeBinding
import com.example.wello.ui.goals.GoalsFragment
import com.example.wello.ui.planner.PlannerFragment

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.buttonPlan.setOnClickListener {
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment_content_main, PlannerFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

//        binding.buttonToday.setOnClickListener {
//            val transaction = parentFragmentManager.beginTransaction()
//            transaction.replace(R.id.nav_host_fragment_content_main, EmotionsFragment())
//            transaction.addToBackStack(null)
//            transaction.commit()
//        }

        binding.buttonGoals.setOnClickListener {
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment_content_main, GoalsFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}