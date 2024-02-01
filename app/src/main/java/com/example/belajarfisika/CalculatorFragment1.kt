package com.example.belajarfisika

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat

class CalculatorFragment1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calculator1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editText1 = view.findViewById<EditText>(R.id.editText1)
        val editText2 = view.findViewById<EditText>(R.id.editText2)
        val textResult = view.findViewById<TextView>(R.id.result)
        val spinnerOperation = view.findViewById<Spinner>(R.id.select)
        val buttonCalculate = view.findViewById<Button>(R.id.buttonCalculator)
        val defaultUnderlineColor = ViewCompat.getBackgroundTintList(editText1)?.defaultColor

        editText1.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                ViewCompat.setBackgroundTintList(editText1, ContextCompat.getColorStateList(requireContext(), R.color.underline_color))
            } else {
                ViewCompat.setBackgroundTintList(editText1, ContextCompat.getColorStateList(requireContext(), defaultUnderlineColor ?: R.color.default_underline_color))
            }
        }

        editText2.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                ViewCompat.setBackgroundTintList(editText2, ContextCompat.getColorStateList(requireContext(), R.color.underline_color))
            } else {
                ViewCompat.setBackgroundTintList(editText2, ContextCompat.getColorStateList(requireContext(), defaultUnderlineColor ?: R.color.default_underline_color))
            }
        }

        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.select1,
            android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerOperation.adapter = adapter

        spinnerOperation.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                when (position) {
                    0 -> {
                        editText1.hint = "masukkan nilai F (gaya) dalam satuan Newton"
                        editText2.hint = "masukkan nilai s (jarak) dalam satuan Meter"
                    }
                    1 -> {
                        editText1.hint = "masukkan nilai W (usaha) dalam satuan Joule"
                        editText2.hint = "masukkan nilai s (jarak) dalam satuan Meter"
                    }
                    2 -> {
                        editText1.hint = "masukkan nilai W (usaha) dalam satuan Joule"
                        editText2.hint = "masukkan nilai F (gaya) dalam satuan Newton"
                    }
                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // Do nothing here
            }
        }

        buttonCalculate.setOnClickListener {
            val x = editText1.text.toString().toDoubleOrNull()
            val y = editText2.text.toString().toDoubleOrNull()
            val selectedOperation = spinnerOperation.selectedItem.toString()

            if (x == null || y == null) {
                editText1.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.colorError)
                editText2.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.colorError)
                textResult.visibility = View.VISIBLE
                textResult.text = "Masukkan value dengan benar!!"
                textResult.setBackgroundResource(R.drawable.resulterror)
                textResult.setTextAppearance(R.style.errorResult)
                Toast.makeText(requireContext(), "Error = Invalid value!!" ,Toast.LENGTH_SHORT).show()
            } else {
                val result = when (selectedOperation) {
                    "Rumus Usaha" -> x * y
                    "Rumus Gaya" -> if (y != 0.0) x / y else 0.0
                    "Rumus Jarak" -> if (y != 0.0) x / y else 0.0
                    else -> 0.0
                }

                val unit = when (selectedOperation) {
                    "Rumus Usaha" -> "Joule"
                    "Rumus Gaya" -> "Newton"
                    "Rumus Jarak" -> "Meter"
                    else -> ""
                }

                val operationSymbol = when (selectedOperation) {
                    "Rumus Usaha" -> "Usaha (W)"
                    "Rumus Gaya" -> "Gaya (F)"
                    "Rumus Jarak" -> "Jarak (S)"
                    else -> ""
                }

                textResult.setBackgroundResource(R.drawable.result)
                textResult.setTextAppearance(R.style.defaultResult)
                editText1.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.default_underline_color)
                editText2.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.default_underline_color)
                textResult.text = "Hasil = $result $unit"
                textResult.visibility = View.VISIBLE
                Toast.makeText(requireContext(), "$operationSymbol = $result", Toast.LENGTH_SHORT).show()
            }
        }
    }
}