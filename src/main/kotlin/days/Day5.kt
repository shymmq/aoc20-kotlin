package days

class Day5 : Day(5) {

    private val ids = input.map { pass ->
        pass.replace("B", "1").replace("R", "1")
            .replace("F", "0").replace("L", "0")
            .toInt(2)
    }

    private val idRange = ids.minOrNull()!!..ids.maxOrNull()!!

    override fun partOne() = ids.maxOrNull()!!

    override fun partTwo() = idRange.find { it !in ids }!!
}