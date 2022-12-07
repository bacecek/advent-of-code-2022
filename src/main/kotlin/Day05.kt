data class Input(
    val crates: MutableList<MutableList<String>>,
    val commands: List<Command>
)

data class Command(
    val howMuch: Int,
    val from: Int,
    val to: Int,
)

fun main() {
    val inputRaw = loadInput("day05_input")

    println("""
        part1 = ${part1(parseInput(inputRaw))}
        part2 = ${part2(parseInput(inputRaw))}
    """.trimIndent())
}

fun part1(input: Input): String {
    val crates = input.crates
    input.commands.forEach { command ->
        val from = crates[command.from - 1]
        val to = crates[command.to - 1]
        for (i in 0 until command.howMuch) {
            to.add(from.removeLast())
        }
    }

    return crates.map { it.last() }.joinToString(separator = "")
}

fun part2(input: Input): String {
    val crates = input.crates
    input.commands.forEach { command ->
        val from = crates[command.from - 1]
        val to = crates[command.to - 1]
        val toAdd = mutableListOf<String>()
        for (i in 0 until command.howMuch) {
            toAdd.add(from.removeLast())
        }
        to.addAll(toAdd.reversed())
    }
    return crates.map { it.last() }.joinToString(separator = "")
}

fun parseInput(inputRaw: String): Input {
    val inputLines = inputRaw.lines()
    val commandsStartIndex = inputLines.indexOfFirst { it.startsWith("move") }
    require(commandsStartIndex >= 4)

    return Input(parseCrates(inputLines.take(commandsStartIndex - 2)),
        parseCommands(inputLines.subList(commandsStartIndex, inputLines.size)))
}

fun parseCrates(lines: List<String>): MutableList<MutableList<String>> {
    val reversed = lines.reversed()

    val output = mutableListOf<MutableList<String>>()
    val startOffset = 1
    val betweenOffset = 4

    val numberOfStacks = ((reversed[0].length - startOffset) / betweenOffset) + 1
    for (i in 0 until numberOfStacks) {
        output.add(mutableListOf())
    }

    for (i in reversed.indices) {
        val line = reversed[i]

        var indexInString = startOffset
        var indexOfStack = 0
        while (indexInString < line.length) {
            val candidate = line[indexInString]
            if (candidate.isLetter()) {
                output[indexOfStack].add(candidate.toString())
            }
            indexInString += betweenOffset
            indexOfStack++
        }
    }

    return output
}

fun parseCommands(lines: List<String>): List<Command> {
    return lines.map { line ->
        val numbers = line.split(' ').filter { it.toIntOrNull() != null }
        Command(
            numbers[0].toInt(),
            numbers[1].toInt(),
            numbers[2].toInt(),
        )
    }
}
