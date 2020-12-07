package days

class Day7 : Day(7) {

    override fun partOne(): Any {

        val parents = input
            // parse into a list of edges
            .flatMap { ruleStr ->
                val parent = "^\\w+ \\w+".toRegex().find(ruleStr)!!.value
                val children = "(?<=\\d )\\w+ \\w+".toRegex().findAll(ruleStr).toList().map(MatchResult::value)
                children.map { parent to it }
            }
            // associate each node with list of incoming edges
            .groupBy { it.second }
            // associate each node with list of parents
            .mapValues { entry -> entry.value.map { it.first }.toSet() }

        fun ancestors(node: String): Set<String> =
            parents[node].orEmpty() + parents[node]?.flatMap(::ancestors)?.toSet().orEmpty()

        return ancestors("shiny gold").size
    }

    override fun partTwo(): Int {

        val children = input
            // associate each node with a list of (child, number) pairs
            .associate { ruleStr ->
                val parent = "^\\w+ \\w+".toRegex().find(ruleStr)!!.value
                val children = "(\\d) (\\w+ \\w+)".toRegex()
                    .findAll(ruleStr).toList()
                    .map(MatchResult::groupValues)
                    .map { it[2] to it[1].toInt() }
                parent to children
            }

        fun totalChildren(node: String): Int =
            1 + children[node].orEmpty().sumBy { (child, number) -> totalChildren(child) * number }

        return totalChildren("shiny gold")
    }
}