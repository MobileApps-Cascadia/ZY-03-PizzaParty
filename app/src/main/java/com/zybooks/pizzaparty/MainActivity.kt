package com.zybooks.pizzaparty

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var numAttendEditText: EditText
    private lateinit var numPizzasTextView: TextView
    private lateinit var howHungryRadioGroup: RadioGroup
    private lateinit var calculateTotal: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Access to the UI View objects
        numAttendEditText = findViewById(R.id.num_attend_edit_text)
        numPizzasTextView = findViewById(R.id.num_pizzas_text_view)
        howHungryRadioGroup = findViewById(R.id.hungry_radio_group)
        calculateTotal = findViewById(R.id.calc_button)
        //Give the Total pizzas textview an initial value
        numPizzasTextView.setText("Total pizzas: ")

        //TODO: Set the OnButtonClick event handler for the calculateButton to the calculateClick function
    }

    fun calculateClick(view: View) {
        // Get the number of people from the UI (as an Int)
        val numAttendStr = numAttendEditText.text.toString()
        val numAttend = numAttendStr.toIntOrNull() ?: 0 //Fixes app crash by providing a default value if null

        // Get hunger level selection
        val hungerLevel = when (howHungryRadioGroup.getCheckedRadioButtonId()) {
            R.id.light_radio_button -> PizzaCalculator.HungerLevel.LIGHT
            R.id.medium_radio_button -> PizzaCalculator.HungerLevel.MEDIUM
            else -> PizzaCalculator.HungerLevel.RAVENOUS
        }
        // Get the number of pizzas needed using the Model: Pizza Calculator
        val calculator = PizzaCalculator(numAttend,hungerLevel)
        val totalPizzas = calculator.totalPizzas

        // Place totalPizzas into the string resource and display
        val totalText = getString(R.string.total_pizzas,totalPizzas)
        numPizzasTextView.setText(totalText)
    }
}