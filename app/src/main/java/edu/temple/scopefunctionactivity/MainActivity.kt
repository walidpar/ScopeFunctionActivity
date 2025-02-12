package edu.temple.scopefunctionactivity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Test the helper functions
        Log.d("function output", getTestDataArray().toString())
        Log.d("function output", averageLessThanMedian(listOf(1.0, 2.0, 3.0, 4.0, 5.0)).toString())

        // Test getView with logging
        val collection = listOf(10, 20, 30, 40, 50)
        val context = this

        // Test getView with a new view
        val newView = getView(0, null, collection, context)
        Log.d("function output", "New View: ${(newView as TextView).text}")

        // Test getView with a recycled view
        val recycledView = getView(1, newView, collection, context)
        Log.d("function output", "Recycled View: ${(recycledView as TextView).text}")
    }

    // Return a list of random, sorted integers
    private fun getTestDataArray() = MutableList(10) { Random.nextInt() }.apply { sort() }

    // Return true if average value in list is greater than median value, false otherwise
    private fun averageLessThanMedian(listOfNumbers: List<Double>) = listOfNumbers.let { numbers ->
        val avg = numbers.average()
        val sortedList = numbers.sorted()
        val median = if (sortedList.size % 2 == 0)
            (sortedList[sortedList.size / 2] + sortedList[(sortedList.size - 1) / 2]) / 2
        else
            sortedList[sortedList.size / 2]
        avg < median
    }

    // Create a view from an item in a collection, but recycle if possible
    private fun getView(
        position: Int,
        recycledView: View?,
        collection: List<Int>,
        context: Context
    ) =
        (recycledView as? TextView ?: TextView(context).apply {
            Log.d("function output", "Creating new TextView for position $position")
            setPadding(5, 10, 10, 0)
            textSize = 22f
        }).apply {
            Log.d("function output", "Setting text for position $position: ${collection[position]}")
            text = collection[position].toString()
        }
}