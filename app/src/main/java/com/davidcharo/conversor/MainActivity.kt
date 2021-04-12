package com.davidcharo.conversor

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.davidcharo.conversor.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private val factorDolarEuro: Double = 0.85
    private val factorDolarCop: Double = 3661.70
    private val factorEuroCop: Double = 4314.84

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.processChangeButton.setOnClickListener {
            val spCurrentMoney = mainBinding.currentMoneySpinner.selectedItem.toString()
            val spExchangeMoney = mainBinding.exchangeMoneySpinner.selectedItem.toString()
            val valueToChange = mainBinding.valueToChangeEditText.text.toString()
            conversion(spCurrentMoney, spExchangeMoney, valueToChange.toDouble())
        }

        mainBinding.currentMoneySpinner.selectedItem.toString()
        mainBinding.exchangeMoneySpinner.selectedItem.toString()

    }

    private fun conversion(spCurrentMoney: String, spExchangeMoney: String, valueToChange: Double) {
        val res: Double
        val result: Double
        when (spCurrentMoney) {
            "Dolar" -> {
                when (spExchangeMoney) {
                    "Dolar" -> {
                        res = valueToChange
                        result = toTwoDigits(res)
                        printDolar(spCurrentMoney, spExchangeMoney, valueToChange, result)
                    }
                    "Euro" -> {
                        res = valueToChange * factorDolarEuro
                        result = toTwoDigits(res)
                        printDolar(spCurrentMoney, spExchangeMoney, valueToChange, result)
                    }
                    else -> {
                        res = valueToChange * factorDolarCop
                        result = toTwoDigits(res)
                        printDolar(spCurrentMoney, spExchangeMoney, valueToChange, result)
                    }
                }
            }
            "Euro" -> {
                when (spExchangeMoney) {
                    "Dolar" -> {
                        res = valueToChange / factorDolarEuro
                        result = toTwoDigits(res)
                        printEuro(spCurrentMoney, spExchangeMoney, valueToChange, result)
                    }
                    "Euro" -> {
                        res = valueToChange
                        result = toTwoDigits(res)
                        printEuro(spCurrentMoney, spExchangeMoney, valueToChange, result)
                    }
                    else -> {
                        res = valueToChange * factorEuroCop
                        result = toTwoDigits(res)
                        printEuro(spCurrentMoney, spExchangeMoney, valueToChange, result)
                    }
                }
            }
            "Peso Colombiano" -> {
                when (spExchangeMoney) {
                    "Dolar" -> {
                        res = valueToChange / factorDolarCop
                        result = toTwoDigits(res)
                        printCop(spCurrentMoney, spExchangeMoney, valueToChange, result)
                    }
                    "Euro" -> {
                        res = valueToChange / factorEuroCop
                        result = toTwoDigits(res)
                        printCop(spCurrentMoney, spExchangeMoney, valueToChange, result)
                    }
                    else -> {
                        res = valueToChange
                        result = toTwoDigits(res)
                        printCop(spCurrentMoney, spExchangeMoney, valueToChange, result)
                    }
                }
            }
        }
    }

    private fun toTwoDigits(res: Double): Double {
        return String.format("%.2f", res).toDouble()

    }


    @SuppressLint("SetTextI18n")
    private fun printDolar(
        spCurrentMoney: String,
        spExchangeMoney: String,
        valueToChange: Double,
        result: Double
    ) {
        if (valueToChange > 1) {
            if (spCurrentMoney == "Dolar" && spExchangeMoney == "Dolar") {
                mainBinding.infoTextView.text =
                    "$valueToChange ${getString(R.string.dolares)} = $result ${getString(R.string.dolares)}"
            } else if (spCurrentMoney == "Dolar" && spExchangeMoney == "Euro") {
                mainBinding.infoTextView.text =
                    "$valueToChange ${getString(R.string.dolares)} = $result ${getString(R.string.euros)}"
            } else if (spCurrentMoney == "Dolar" && spExchangeMoney == "Peso Colombiano") {
                mainBinding.infoTextView.text =
                    "$valueToChange ${getString(R.string.dolares)} = $result ${getString(R.string.pesos_colombianos)}"
            }
        } else {
            if (spCurrentMoney == "Dolar" && spExchangeMoney == "Dolar") {
                mainBinding.infoTextView.text =
                    "$valueToChange ${getString(R.string.dolar)} = $result ${getString(R.string.dolar)}"
            } else if (spCurrentMoney == "Dolar" && spExchangeMoney == "Euro") {
                mainBinding.infoTextView.text =
                    "$valueToChange ${getString(R.string.dolar)} = $result ${getString(R.string.euros)}"
            } else if (spCurrentMoney == "Dolar" && spExchangeMoney == "Peso Colombiano") {
                mainBinding.infoTextView.text =
                    "$valueToChange ${getString(R.string.dolar)} = $result ${getString(R.string.pesos_colombianos)}"
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun printEuro(
        spCurrentMoney: String,
        spExchangeMoney: String,
        valueToChange: Double,
        result: Double
    ) {
        if (valueToChange > 1) {
            if (spCurrentMoney == "Euro" && spExchangeMoney == "Dolar") {
                mainBinding.infoTextView.text =
                    "$valueToChange ${getString(R.string.euros)} = $result${getString(R.string.dolares)}"
            } else if (spCurrentMoney == "Euro" && spExchangeMoney == "Euro") {
                mainBinding.infoTextView.text =
                    "$valueToChange ${getString(R.string.euros)} = $result ${getString(R.string.euros)}"
            } else if (spCurrentMoney == "Euro" && spExchangeMoney == "Peso Colombiano") {
                mainBinding.infoTextView.text =
                    "$valueToChange ${getString(R.string.euros)} = $result ${getString(R.string.pesos_colombianos)}"
            }
        } else {
            if (spCurrentMoney == "Euro" && spExchangeMoney == "Dolar") {
                mainBinding.infoTextView.text =
                    "$valueToChange ${getString(R.string.euro)} = $result ${getString(R.string.dolares)}"
            } else if (spCurrentMoney == "Euro" && spExchangeMoney == "Euro") {
                mainBinding.infoTextView.text =
                    "$valueToChange ${getString(R.string.euro)} = $result ${getString(R.string.euro)}"
            } else {
                if (spCurrentMoney == "Euro" && spExchangeMoney == "Peso Colombiano") {
                    mainBinding.infoTextView.text =
                        "$valueToChange ${getString(R.string.euro)} = $result ${getString(R.string.pesos_colombianos)}"
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun printCop(
        spCurrentMoney: String,
        spExchangeMoney: String,
        valueToChange: Double,
        result: Double
    ) {
        if (valueToChange > 1) {
            if (spCurrentMoney == "Peso Colombiano" && spExchangeMoney == "Dolar") {
                mainBinding.infoTextView.text =
                    "$valueToChange ${getString(R.string.pesos_colombianos)} = $result ${getString(R.string.dolares)}"
            } else if (spCurrentMoney == "Peso Colombiano" && spExchangeMoney == "Euro") {
                mainBinding.infoTextView.text =
                    "$valueToChange ${getString(R.string.pesos_colombianos)} = $result ${getString(R.string.euros)}"
            } else if (spCurrentMoney == "Peso Colombiano" && spExchangeMoney == "Peso Colombiano") {
                mainBinding.infoTextView.text =
                    "$valueToChange ${getString(R.string.pesos_colombianos)} = $result ${getString(R.string.pesos_colombianos)}"
            }
        } else {
            if (spCurrentMoney == "Peso Colombiano" && spExchangeMoney == "Dolar") {
                mainBinding.infoTextView.text =
                    "$valueToChange ${getString(R.string.peso_colombiano)} = $result ${getString(R.string.dolares)}"
            } else if (spCurrentMoney == "Peso Colombiano" && spExchangeMoney == "Euro") {
                mainBinding.infoTextView.text =
                    "$valueToChange ${getString(R.string.peso_colombiano)} = $result ${getString(R.string.euros)}"
            } else if (spCurrentMoney == "Peso Colombiano" && spExchangeMoney == "Peso Colombiano") {
                mainBinding.infoTextView.text =
                    "$valueToChange ${getString(R.string.peso_colombiano)} = $result ${getString(R.string.peso_colombiano)}"
            }
        }
    }
}