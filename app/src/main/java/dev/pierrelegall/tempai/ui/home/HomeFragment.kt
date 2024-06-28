package dev.pierrelegall.tempai.ui.home

import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.pierrelegall.tempai.databinding.FragmentHomeBinding
import dev.pierrelegall.tempai.tool.BpmCounter

const val UNKNOWN_BPM_TEXT = "- - -"
const val AUTO_RESET_TIMEOUT: Long = 10000
const val BLINK_COLOR_SWITCH_DELAY = 30

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding
       get() = _binding!!
    private val bpmCounter = BpmCounter()
    private var handler: Handler = Handler(Looper.getMainLooper())
    private var autoResetTask = Runnable { this.reset() }
    private val blinkColors = arrayOf(
        ColorDrawable(-0xcfcfcf),
        ColorDrawable(-0xd8d8d8),
        ColorDrawable(-0xdfdfdf),
        ColorDrawable(-0xefefef),
        ColorDrawable(-0xe8e8e8),
    )

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        this.reset()

        this.binding.homeLayout.setOnTouchListener { _view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    this.tap()
                    true
                }
                else -> false
            }
        }

        this.binding.resetButton.setOnClickListener { this.reset() }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun tap() {
        this.bpmCounter.mark()
        this.blink()
        val bpm = this.bpmCounter.compute()
        this.binding.bpmText.text = this.humanizeBpmOutput(bpm)
        this.binding.bpmText.visibility = View.VISIBLE
        this.binding.howToUseText.visibility = View.INVISIBLE
        this.setAutoResetTask()
    }

    private fun reset() {
        this.bpmCounter.reset()
        this.binding.bpmText.text = UNKNOWN_BPM_TEXT
        this.binding.bpmText.visibility = View.INVISIBLE
        this.binding.howToUseText.visibility = View.VISIBLE
        this.cancelAutoResetTask()
    }

    fun blink(): Boolean {
        val layout = this.binding.homeLayout

        val a = AnimationDrawable()
        for (color in this.blinkColors) { a.addFrame(color, BLINK_COLOR_SWITCH_DELAY) }
        a.addFrame(ColorDrawable(layout.solidColor), BLINK_COLOR_SWITCH_DELAY)
        a.isOneShot = true

        layout.background = a
        a.start()

        return true
    }

    private fun setAutoResetTask() {
        this.cancelAutoResetTask()
        this.handler.postDelayed(this.autoResetTask, AUTO_RESET_TIMEOUT)
    }

    private fun cancelAutoResetTask() {
        this.handler.removeCallbacks(this.autoResetTask)
    }

    private fun humanizeBpmOutput(bpm: Long?): String {
        return bpm?.toString() ?: UNKNOWN_BPM_TEXT
    }
}
