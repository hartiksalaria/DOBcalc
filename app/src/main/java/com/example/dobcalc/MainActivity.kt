package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate: TextView? = null

    private var tvAgeInMinutes: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)

        tvSelectedDate = findViewById(R.id.tvSelectedDate)

        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)

        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val date = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener() { _, selectedYear, selectedMonth, selectedDay ->

                val selectedDate = "$selectedDay.${selectedMonth + 1}.$selectedYear"

                tvSelectedDate?.text = selectedDate

                val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)

                val selectedDateInMinutes = theDate?.time?.div(60000)

                val currentTime = sdf.parse(sdf.format(System.currentTimeMillis()))

                val currentTimeInMinutes = (currentTime?.time)?.div(60000)

                val differenceInMinutes = currentTimeInMinutes?.minus(selectedDateInMinutes!!)

                tvAgeInMinutes?.text = differenceInMinutes.toString()

            },
            year, month, date
        )

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000

        dpd.show()

    }

}