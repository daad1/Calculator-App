package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView


private lateinit var result: TextView
private lateinit var listOperator: ArrayList<Button>
private lateinit var operationArray: Array<String>

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        result = findViewById(R.id.tvResult)
        operationArray = arrayOf("", "", "")
        initiateButtons()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArray("operationArray", operationArray)
        outState.putString("resultTextView", result.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        operationArray = savedInstanceState.getStringArray("operationArray") as Array<String> 
        result.text = savedInstanceState.getString("resultTextView", "")
    }

    private fun initiateButtons() {
        // buttons initiation
        listOperator = arrayListOf(
            findViewById(R.id.number_0),
            findViewById(R.id.number_1),
            findViewById(R.id.number_2),
            findViewById(R.id.number_3),
            findViewById(R.id.number_4),
            findViewById(R.id.number_5),
            findViewById(R.id.number_6),
            findViewById(R.id.number_7),
            findViewById(R.id.number_8),
            findViewById(R.id.number_9),
            findViewById(R.id.del_bnt),
            findViewById(R.id.clear_btn),
            findViewById(R.id.plusandsub_btn),
            findViewById(R.id.dot_btn),
            findViewById(R.id.equal_btn),
            findViewById(R.id.plus_bnt),
            findViewById(R.id.sub_bnt),
            findViewById(R.id.multiply_btn),
            findViewById(R.id.divide_btn)
        )

        for (button in listOperator)
            button.setOnClickListener { entries(button.id) }
    }


    private fun entries (viewID: Int){
        val index = if (operationArray[1].isEmpty()) 0 else 2
        val currentOperation = result.text.toString()
        when (viewID) {
            R.id.number_0 -> {
                operationArray[index] += "0"
                result.text = "${currentOperation}0"
            }
            R.id.number_1 -> {
                operationArray[index] += "1"
                result.text = "${currentOperation}1"
            }
            R.id.number_2 -> {
                operationArray[index] += "2"
                result.text = "${currentOperation}2"
            }
            R.id.number_3 -> {
                operationArray[index] += "3"
                result.text = "${currentOperation}3"
            }
            R.id.number_4 -> {
                operationArray[index] += "4"
                result.text = "${currentOperation}4"
            }
            R.id.number_5 -> {
                operationArray[index] += "5"
                result.text = "${currentOperation}5"
            }
            R.id.number_6 -> {
                operationArray[index] += "6"
                result.text = "${currentOperation}6"
            }
            R.id.number_7 -> {
                operationArray[index] += "7"
                result.text = "${currentOperation}7"
            }
            R.id.number_8 -> {
                operationArray[index] += "8"
                result.text = "${currentOperation}8"
            }
            R.id.number_9 -> {
                operationArray[index] += "9"
                result.text = "${currentOperation}9"
            }
            R.id.del_bnt -> {
                operationArray[index] = operationArray[index].dropLast(1)
                result.text = currentOperation.dropLast(1)
            }
            R.id.clear_btn -> {
                operationArray = arrayOf("", "", "")
                result.text = ""
            }
            R.id.plusandsub_btn -> {
                operationArray[index] += "-"
                result.text = "$currentOperation -"
            }
            R.id.dot_btn -> {
                operationArray[index] += "."
                result.text = "${currentOperation}."
            }
            R.id.equal_btn -> {
                operationArray = arrayOf(compute(), "", "")
                result.text = operationArray[0]
            }
            R.id.plus_bnt -> {
                operationArray[1] = "+"
                result.text = "$currentOperation + "
            }
            R.id.sub_bnt -> {
                operationArray[1] = "-"
                result.text = "$currentOperation - "
            }
            R.id.multiply_btn -> {
                operationArray[1] = "*"
                result.text = "$currentOperation * "
            }
            R.id.divide_btn -> {
                operationArray[1] = "/"
                result.text = "$currentOperation / "
            }
        }
    }

    private fun compute(): String {
        when (operationArray[1]){
            "+" -> return (operationArray[0].toFloat() + operationArray[2].toFloat()).toString()
            "-" -> return (operationArray[0].toFloat() - operationArray[2].toFloat()).toString()
            "*" -> return (operationArray[0].toFloat() * operationArray[2].toFloat()).toString()
            "/" -> {
                if (operationArray[2].toFloat() == 0f)
                    return "Math Error"
                return (operationArray[0].toFloat() / operationArray[2].toFloat()).toString()
            }
            else -> return ""
        }
    }
}
