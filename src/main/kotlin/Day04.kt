fun main() {
    val inputRaw = loadInput("day04_input")

    var part1Result = 0
    var part2NumberOfOverlaps = 0
    for (i in inputRaw.lines().indices) {
        val line = inputRaw.lines()[i]
        if (line.isBlank()) {
            continue
        }
        val pairsRaw = line.split(",")
        require(pairsRaw.size == 2)
        val left = pairsRaw[0].parsePair()
        val right = pairsRaw[1].parsePair()
        var isPart1ConditionMet: Boolean
        var isPart2ConditionMet: Boolean
        if (left[0] == right[0]) {
            isPart1ConditionMet = true
            isPart2ConditionMet = true
        } else if (left[0] < right[0]) {
            isPart1ConditionMet = left[1] >= right[1]
            isPart2ConditionMet = right[0] <= left[1]
        } else {
            isPart1ConditionMet = right[1] >= left[1]
            isPart2ConditionMet = left[0] <= right[1]
        }
        if (isPart1ConditionMet) {
            part1Result++
        }
        if (isPart2ConditionMet) {
            part2NumberOfOverlaps++
        }
    }

    println("""
        part1=$part1Result
        part2=${part2NumberOfOverlaps}
    """.trimIndent())
}

fun String.parsePair() = split("-").map { it.toInt() }
