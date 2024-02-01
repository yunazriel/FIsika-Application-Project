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

class CalculatorFragment3 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calculator3, container, false)

        val editText1 = view.findViewById<EditText>(R.id.editText1)
        val editText2 = view.findViewById<EditText>(R.id.editText2)
        val textResult = view.findViewById<TextView>(R.id.result)
        val spinnerOperation = view.findViewById<Spinner>(R.id.select)
        val buttonCalculator = view.findViewById<Button>(R.id.buttonCalculator)
        val defaultUnderlineColor = ViewCompat.getBackgroundTintList(editText1)?.defaultColor

        editText1.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                ViewCompat.setBackgroundTintList(
                    editText1,
                    ContextCompat.getColorStateList(requireContext(), R.color.underline_color)
                )
            } else {
                ViewCompat.setBackgroundTintList(
                    editText1,
                    ContextCompat.getColorStateList(requireContext(), defaultUnderlineColor ?: R.color.default_underline_color)
                )
            }
        }

        editText2.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                ViewCompat.setBackgroundTintList(
                    editText2,
                    ContextCompat.getColorStateList(requireContext(), R.color.underline_color)
                )
            } else {
                ViewCompat.setBackgroundTintList(
                    editText2,
                    ContextCompat.getColorStateList(requireContext(), defaultUnderlineColor ?: R.color.default_underline_color)
                )
            }
        }

        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.select3,
            android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerOperation.adapter = adapter

        spinnerOperation.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                when (position) {
                    0 -> {
                        editText1.hint = "masukkan nilai s (jarak) dalam satuan Meter"
                        editText2.hint = "masukkan nilai t (waktu) dalam satuan Second"
                    }
                    1 -> {
                        editText1.hint = "masukkan nilai v (kecepatan) dalam satuan m/s"
                        editText2.hint = "masukkan nilai t (waktu) dalam satuan Second"
                    }
                    2 -> {
                        editText1.hint = "masukkan nilai v (kecepatan) dalam satuan m/s"
                        editText2.hint = "masukkan nilai s (jarak) dalam satuan Meter"
                    }
                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // Do nothing here
            }
        }

        buttonCalculator.setOnClickListener {
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
                Toast.makeText(requireContext(), "Error = Invalid value!!", Toast.LENGTH_SHORT).show()
            } else {
                val result = when (selectedOperation) {
                    "Rumus Kecepatan" -> if (y != 0.0) x / y else 0.0
                    "Rumus Jarak" -> x * y
                    "Rumus Waktu" -> if (y != 0.0) x / y else 0.0
                    else -> 0.0
                }

                val unit = when (selectedOperation) {
                    "Rumus Kecepatan" -> "m/s"
                    "Rumus Jarak" -> "Meter"
                    "Rumus Waktu" -> "Second"
                    else -> ""
                }

                val operationSymbol = when (selectedOperation) {
                    "Rumus Kecepatan" -> "kecepatan (v)"
                    "Rumus Jarak" -> "jarak (s)"
                    "Rumus Waktu" -> "waktu (t)"
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

        return view
    }
}