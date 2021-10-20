package com.anna_shark.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)
            }
    }

    fun clickDatePicker(view: View) {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                view, selectedYear, selectedMonth, selectedDayOfMonth ->
           // Toast.makeText(this, "The chosen year is $selectedYear, month is $selectedMonth, day is $selectedDayOfMonth "
             //   , Toast.LENGTH_LONG).show()
            val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
            tvSelectedDate.setText(selectedDate)
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)
            val selectedDateInDays = theDate.time/(1440*60000)
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInDays = currentDate.time/(1440*60000)
            val diffInMinutes = currentDateInDays - selectedDateInDays

            tvSelectedDateInMinutes.setText(diffInMinutes.toString())
        }
            , year
            , month
            , day)
        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()
    }

}