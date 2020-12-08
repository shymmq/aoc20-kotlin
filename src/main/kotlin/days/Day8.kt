package days

data class Instruction(val op: String, val arg: Int)
data class Result(val acc: Int, val finished: Boolean)

typealias Program = List<Instruction>

class Day8 : Day(8) {

    private fun <E> Iterable<E>.updated(index: Int, elem: E) = mapIndexed { i, existing -> if (i == index) elem else existing }

    private val loopProgram = input.map {
        Instruction(it.substringBefore(" "), it.substringAfter(" ").toInt())
    }

    /**
     * Runs the given list of instructions until it either loops or finishes
     * @return pair of (acc, finished)
     */
    private fun Program.run(): Result {
        var counter = 0
        var acc = 0
        val visited = mutableSetOf<Int>()

        while (counter !in visited && counter < this.size) this[counter].let { (op, arg) ->
            visited += counter
            if (op == "acc") acc += arg
            counter += if (op == "acc" || op == "nop") 1 else arg
        }

        return Result(acc, counter == this.size)
    }

    override fun partOne() = loopProgram.run().acc

    override fun partTwo(): Any {

        for ((ptr, instr) in loopProgram.withIndex()) {
            if (instr.op == "acc") continue
            val fixedInstr = Instruction(if (instr.op == "jmp") "nop" else "jmp", instr.arg)
            val fixedProgram = loopProgram.updated(ptr, fixedInstr)
            val (result, finished) = fixedProgram.run()
            if (finished) return result
        }

        throw Exception("Couldn't fix program")
    }
}