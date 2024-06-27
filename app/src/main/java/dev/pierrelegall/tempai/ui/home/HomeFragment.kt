package dev.pierrelegall.tempai.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dev.pierrelegall.tempai.databinding.FragmentHomeBinding
import dev.pierrelegall.tempai.tool.Counter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private var counter = Counter()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding
        get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) { binding.textHome.text = it }

        this.binding.homeLayout.setOnClickListener {
            Log.d("tempai", "TAP clicked!")
            this.counter.tapNow()
            val bpm = this.counter.computeBpm()
            binding.textHome.text = this.humanizeBpmOutput(bpm)
        }

        this.binding.resetButton.setOnClickListener {
            Log.d("tempai", "RESET clicked!")
            this.counter.reset()
            val bpm = this.counter.computeBpm()
            binding.textHome.text = this.humanizeBpmOutput(bpm)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun humanizeBpmOutput(bpm: Long?): String {
        return bpm?.toString() ?: "..."
    }
}
