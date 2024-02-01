package com.example.belajarfisika

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat

class CalculatorFragment2 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calculator2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editText1 = view.findViewById<EditText>(R.id.editText1)
        val textResult = view.findViewById<TextView>(R.id.result)
        val defaultUnderlineColor = ViewCompat.getBackgroundTintList(editText1)?.defaultColor

        editText1.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                ViewCompat.setBackgroundTintList(editText1, ContextCompat.getColorStateList(requireContext(), R.color.underline_color))
            } else {
                ViewCompat.setBackgroundTintList(editText1, ContextCompat.getColorStateList(requireContext(), defaultUnderlineColor ?: R.color.default_underline_color))
            }
        }

        val buttonCalculate = view.findViewById<Button>(R.id.buttonCalculator)
        buttonCalculate.setOnClickListener {
            outputMain(editText1.text.toString(), textResult)
        }
    }

    private fun countFaksi(input: Double): Double {
        return -input
    }

    private fun outputMain(inputText: String, textResult: TextView) {
        val editText1 = view?.findViewById<EditText>(R.id.editText1)
        if (inputText.isNotEmpty()) {
            val result = countFaksi(inputText.toDouble())
            textResult.text = "Hasil = $result Freaksi"
            textResult.visibility = View.VISIBLE
            textResult.setBackgroundResource(R.drawable.result)
            textResult.setTextAppearance(R.style.defaultResult)
            editText1?.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.default_underline_color)
            Toast.makeText(requireContext(), "Faksi = $inputText > Freaksi = $result", Toast.LENGTH_SHORT).show()
        } else {
            textResult.text = "Input tidak boleh kosong!!"
            textResult.visibility = View.VISIBLE
            textResult.setBackgroundResource(R.drawable.resulterror)
            textResult.setTextAppearance(R.style.errorResult)
            editText1?.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.colorError)
            Toast.makeText(requireContext(), "Error = Invalid value!!" ,Toast.LENGTH_SHORT).show()
        }
    }
}