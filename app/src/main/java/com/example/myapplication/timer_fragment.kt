package com.example.myapplication

import android.app.AlertDialog
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.TimePicker
import androidx.fragment.app.Fragment

class TimerFragment : Fragment() {


    private lateinit var cancelBtn: Button
    private lateinit var saveBtn: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.timer_fragment, container, false)
        val dayPicker = view.findViewById<NumberPicker>(R.id.dayPicker)
        val hourPicker = view.findViewById<NumberPicker>(R.id.hourPicker)
        val minutePicker = view.findViewById<NumberPicker>(R.id.minutePicker)

        // Options for the first NumberPicker
        val daysOptions = arrayOf("0 day", "1 day")
        dayPicker.minValue = 0
        dayPicker.maxValue = daysOptions.size - 1
        dayPicker.displayedValues = daysOptions

        // Options for the second NumberPicker
        val hoursOptions = arrayOf("3 hours", "4 hours" , "5 hours")
        hourPicker.minValue = 0
        hourPicker.maxValue = hoursOptions.size - 1
        hourPicker.displayedValues = hoursOptions

        // Options for the third NumberPicker
        val minutesOptions = arrayOf("15 mins", "30 mins","45 mins")
        minutePicker.minValue = 0
        minutePicker.maxValue = minutesOptions.size - 1
        minutePicker.displayedValues = minutesOptions

        cancelBtn = view.findViewById(R.id.cancelBtn)
        saveBtn = view.findViewById(R.id.saveBtn)

        cancelBtn.setOnClickListener {
            showUnSavedDialog()
        }
        saveBtn.setOnClickListener {
            showCartTimerAlert()
        }

        showCartTimerAlert()



        return view
    }

    fun showUnSavedDialog()
    {
        // Set up the AlertDialog
        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle("You have unsaved changes")
            .setMessage("Continuing without saving your changes means you will lose those changes")
            .setPositiveButton("Delete Changes") { _, _ ->
                // Handle "Delete Changes" button click
                // Add your logic here
            }
            .setNegativeButton("Cancel Navigation") { _, _ ->
                // Handle "Cancel Navigation" button click
                // Add your logic here
            }
            .create()
        alertDialog.show()

    }

    fun showCartTimerAlert()
    {
        // Set up the AlertDialog
        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle("Cart Timer Too Short")
            .setMessage("Cart timer must be at least 15 minutes.")
            .setPositiveButton("Ok") { _, _ ->

            }
            .create()
        alertDialog.show()

    }
}
