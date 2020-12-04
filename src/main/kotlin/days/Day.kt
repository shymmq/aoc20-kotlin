package days

import java.io.File
import java.io.FileNotFoundException
import java.lang.NullPointerException

abstract class Day(private val dayNumber: Int) {

    fun getInput(sample: Boolean = false): List<String> {
        val dir = if (sample) "samples" else "inputs"
        val path = "$dir/day_$dayNumber.txt"
        try {
            val file = File(javaClass.classLoader.getResource(path)!!.toURI())
            return file.readLines()
        } catch (e: NullPointerException) {
            throw FileNotFoundException("File $path doesn't exist")
        }
    }

    abstract fun partOne(): Any

    abstract fun partTwo(): Any
}
