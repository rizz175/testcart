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

    private var dayPicker: NumberPicker?=null
    private var hourPicker: NumberPicker?=null
    private var minutePicker: NumberPicker?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.timer_fragment, container, false)
         dayPicker = view.findViewById<NumberPicker>(R.id.dayPicker)
         hourPicker = view.findViewById<NumberPicker>(R.id.hourPicker)
         minutePicker = view.findViewById<NumberPicker>(R.id.minutePicker)

        // Options for the first NumberPicker
//        val daysOptions = arrayOf("0 day", "1 day")
//        dayPicker.minValue = 0
//        dayPicker.maxValue = daysOptions.size - 1
//        dayPicker.displayedValues = daysOptions
//
//        // Options for the second NumberPicker
//        val hoursOptions = arrayOf("3 hours", "4 hours" , "5 hours")
//        hourPicker.minValue = 0
//        hourPicker.maxValue = hoursOptions.size - 1
//        hourPicker.displayedValues = hoursOptions
//        hourPicker.value = 1
//
//        // Options for the third NumberPicker
//        val minutesOptions = arrayOf("15 mins", "30 mins","45 mins")
//        minutePicker.minValue = 0
//        minutePicker.maxValue = minutesOptions.size - 1
//        minutePicker.displayedValues = minutesOptions

        presetTime(minutePicker!!,hourPicker!!, dayPicker!! )


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


    enum class TimeConversion {
        DAYS, HOURS, MIN
    }


    fun presetTime(nMin: NumberPicker, nHour: NumberPicker, nDays : NumberPicker){


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
        val minutesOptions = arrayOf("15 mins", "30 mins","45 mins")

        when (getTimeValue(timeConvert(90).toString())) {

            TimeConversion.DAYS ->{}
            TimeConversion.HOURS ->{

            dayPicker!!.minValue = 0
            dayPicker!!.maxValue = daysOptions.size - 1
            dayPicker!!.displayedValues = daysOptions

            hourPicker!!.minValue = 0
            hourPicker!!.maxValue = hoursOptions.size - 1
            hourPicker!!.displayedValues = hoursOptions
            hourPicker!!.value = returnTimeValue(timeConvert(90).toString(), TimeConversion.HOURS)


            // Options for the third NumberPicker

            minutePicker!!.minValue = 0
            minutePicker!!.maxValue = minutesOptions.size - 1
            minutePicker!!.displayedValues = minutesOptions
            minutePicker!!.value = returnTimeValue(timeConvert(90).toString(), TimeConversion.MIN)


            }
            TimeConversion.MIN ->{}



            else -> throw IllegalStateException()
        }


    }



    fun timeConvert(time: Int): String? {
        return (time / 24 / 60).toString() + ":" + time / 60 % 24 + ':' + time % 60
    }
    fun getTimeValue(sTime: String): TimeConversion{

        var hold = sTime.split(":")

        if (hold[1] == "0" && hold[1] == "0")
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

                if (iMin == 15)
                    return 0
                else if (iMin == 30)
                    return 1
                else
                    return 2

            }
        }

        return 0
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
