package days

class Day6 : Day(6) {

    private val groups = inputString.split("\n\n")
        .map { groupString -> groupString.split("\n") }

    override fun partOne() = groups.sumBy { group ->
        group.joinToString("").toSet().count()
    }

    override fun partTwo() = groups.sumBy { group ->
        ('a'..'z').count { question -> group.all { question in it } }
    }

}