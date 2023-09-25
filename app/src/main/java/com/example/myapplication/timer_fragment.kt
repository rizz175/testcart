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
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import kotlin.math.log

class TimerFragment : Fragment() {


    private lateinit var cancelBtn: Button
    private lateinit var saveBtn: Button

    private var dayPicker: NumberPicker?=null
    private var hourPicker: NumberPicker?=null
    private var minutePicker: NumberPicker?=null
    val hoursOptions = arrayOf(
        "0 hours",
        "1 hours",
        "2 hours" ,
        "3 hours",
        "4 hours",
        "5 hours" ,
        "6 hours",
        "7 hours",
        "8 hours" ,
        "9 hours",
        "10 hours",
        "11 hours",
        "12 hours" ,
        "13 hours",
        "14 hours",
        "15 hours" ,
        "16 hours",
        "17 hours",
        "18 hours",
        "19 hours" ,
        "20 hours",
        "21 hours",
        "22 hours",
        "23 hours",
        "24 hours"


    )
    val daysOptions = arrayOf("0 day", "1 day")
    val minutesOptions = arrayOf("0 min", "15 mins", "30 mins","45 mins")





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.timer_fragment, container, false)
         dayPicker = view.findViewById<NumberPicker>(R.id.dayPicker)
         hourPicker = view.findViewById<NumberPicker>(R.id.hourPicker)
         minutePicker = view.findViewById<NumberPicker>(R.id.minutePicker)

        // days = 1440 min
        // 1 day 1 hour 30 = 1530
        // 1 day 2 hour 30 = 1530
        // 1 day 3 hour 45 = 1665

        // 1 day 1 hour 1500

        presetTime(timeConvert(150).toString())


        cancelBtn = view.findViewById(R.id.cancelBtn)
        saveBtn = view.findViewById(R.id.saveBtn)

        cancelBtn.setOnClickListener {
//            showUnSavedDialog()
      ;

        }
        saveBtn.setOnClickListener {
            if (areAllPickersZero()) {
                // Show the "Cart Timer Too Short" alert if all pickers are zero.
                showCartTimerAlert()
            }
        }

        showCartTimerAlert()



        return view
    }
    override fun onResume() {
        super.onResume()
        requireActivity().onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showUnSavedDialog()

            }
        })
    }

    enum class TimeConversion {
        DAYS, HOURS, MIN
    }


    fun presetTime(convertedTime: String){

        setDayPicker(convertedTime, TimeConversion.DAYS)
        setHourPicker(convertedTime, TimeConversion.HOURS)
        setMinutePicker(convertedTime, TimeConversion.MIN)



    }


    fun setDayPicker(convertedTime: String, timeConversion: TimeConversion){
        dayPicker!!.minValue = 0
        dayPicker!!.maxValue = daysOptions.size - 1
        dayPicker!!.displayedValues = daysOptions
        dayPicker!!.value = returnTimeValue(convertedTime, timeConversion)
    }


    fun setHourPicker(convertedTime: String, timeConversion: TimeConversion){
        hourPicker!!.minValue = 0
        hourPicker!!.maxValue = hoursOptions.size - 1
        hourPicker!!.displayedValues = hoursOptions
        hourPicker!!.value = returnTimeValue(convertedTime, timeConversion)
    }

    fun setMinutePicker(convertedTime: String, timeConversion: TimeConversion){
        minutePicker!!.minValue = 0
        minutePicker!!.maxValue = minutesOptions.size - 1
        minutePicker!!.displayedValues = minutesOptions
        minutePicker!!.value = returnTimeValue(convertedTime, timeConversion)
    }


    fun timeConvert(time: Int): String? {
        return (time / 24 / 60).toString() + ":" + time / 60 % 24 + ':' + time % 60
    }
    fun getTimeValue(sTime: String): TimeConversion{

        var hold = sTime.split(":")

        if (hold[0] == "0" && hold[1] == "0")
            return TimeConversion.MIN
        else if (hold[0] == "0" && hold[1] != "0")
            return TimeConversion.HOURS
        else
            return TimeConversion.DAYS


    }

    fun returnTimeValue(sTime: String, timeConversion: TimeConversion): Int{

        var hold = sTime.split(":")

        when (timeConversion) {

             TimeConversion.DAYS -> {

                 return hold[0].toInt()

             }
             TimeConversion.HOURS -> {

                 return hold[1].toInt()
             }
            else -> {

                val iMin = hold[2].toInt()

                if (iMin == 0)
                    return 0
                else if (iMin == 15)
                    return 1
                else if (iMin == 30)
                    return 2
                else
                    return 3
            }
        }

        return 0
    }


    private fun calculateTotalMinutes(): Int {
        // Get the selected values from NumberPickers
        val selectedHours = hourPicker?.value ?: 0
        val selectedMinutes = minutePicker?.value ?: 0
        val selectedDays = dayPicker?.value ?: 0

        // Calculate total minutes
        val totalMinutes = (selectedDays * 24 * 60) +(selectedHours * 60) + (selectedMinutes*15)


        return totalMinutes
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
    private fun areAllPickersZero(): Boolean {
        val selectedDays = dayPicker?.value ?: 0
        val selectedHours = hourPicker?.value ?: 0
        val selectedMinutes = minutePicker?.value ?: 0

        return selectedDays == 0 && selectedHours == 0 && selectedMinutes == 0
    }
}
