package days

import java.lang.Exception

class Day3 : Day(3) {

    override fun partOne(): Int {
        val width = input.first().length
        return input.foldIndexed(0) { index, count, line ->
            val hit = line[(index * 3) % width] == '#'
            if (hit) count + 1 else count
        }
    }


    override fun partTwo(): Long {

        val slopes = listOf(
            1 to 1,
            3 to 1,
            5 to 1,
            7 to 1,
            1 to 2
        )

        val width = input.first().length

        return slopes
            .map { (dx, dy) ->
                input
                    .filterIndexed { i, _ -> i % dy == 0 }
                    .foldIndexed(0) { index, count, line ->
                        val hit = line[(index * dx) % width] == '#'
                        if (hit) count + 1 else count
                    }
            }
            .map(Int::toLong)
            .reduce(Long::times)

    }
}
