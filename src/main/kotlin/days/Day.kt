package days

import java.io.File
import java.io.FileNotFoundException
import java.lang.NullPointerException

abstract class Day(private val dayNumber: Int) {

    protected val input by lazy { getInput(sample = false) }
    protected val sampleInput by lazy { getInput(sample = true) }

    protected val inputString by lazy { getInputString(sample = false) }
    protected val sampleInputString by lazy { getInputString(sample = true) }

    private fun getInput(sample: Boolean = false): List<String> {
        val dir = if (sample) "samples" else "inputs"
        val path = "$dir/day_$dayNumber.txt"
        try {
            val file = File(javaClass.classLoader.getResource(path)!!.toURI())
            return file.readLines()
        } catch (e: NullPointerException) {
            throw FileNotFoundException("File $path doesn't exist")
        }
    }

    private fun getInputString(sample: Boolean = false): String {
        val dir = if (sample) "samples" else "inputs"
        val path = "$dir/day_$dayNumber.txt"
        try {
            val file = File(javaClass.classLoader.getResource(path)!!.toURI())
            return file.readText()
        } catch (e: NullPointerException) {
            throw FileNotFoundException("File $path doesn't exist")
        }
    }

    abstract fun partOne(): Any

    abstract fun partTwo(): Any
}
