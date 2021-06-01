package com.rsschool.android2021

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment


class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var router: Router? = null
    private var etMin: EditText? = null
    private var etMax: EditText? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)
        etMin = view.findViewById(R.id.min_value)
        etMax = view.findViewById(R.id.max_value)


        if (context is Router) {
            router = context as Router
        }


        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"


        generateButton?.setOnClickListener {
            val minString = etMin?.text.toString()
            val maxString = etMax?.text.toString()
            if (isNumbersAllowed(minString, maxString)) {
                router?.toSecondFragment(minString.toInt(), maxString.toInt())
            }
        }
    }

    /**
     * Checks if input numbers is in integer range , or if min is greater than max
     */
    private fun isNumbersAllowed(min: String, max: String): Boolean {
        return when {
            min.toIntOrNull() == null -> false.also {
                etMin?.error = "Number must be in integer range "
            }
            max.toIntOrNull() == null -> false.also {
                etMax?.error = "Number must be in integer range "
            }
            min.toInt() > max.toInt() -> false.also {
                etMax?.error = "Max number must be greater than min"
            }
            else -> true
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}