fun main() {
    val inputRaw = loadInput("day06_input")

    println("""
        part1=${getMarker(inputRaw, 4)}
        part2=${getMarker(inputRaw, 14)}
    """.trimIndent())
}

private fun getMarker(inputRaw: String, windowSize: Int): Int {
    val cache = mutableMapOf<Char, Int>()

    fun put(char: Char) {
        if (cache.containsKey(char)) {
            cache[char] = cache[char]!! + 1
        } else {
            cache[char] = 1
        }
    }

    fun remove(char: Char) {
        val amount = cache[char] ?: return
        if (amount == 1) {
            cache.remove(char)
        } else {
            cache[char] = cache[char]!! - 1
        }
    }

    inputRaw.take(windowSize).forEach { put(it) }

    var index = windowSize
    while (cache.keys.size != windowSize) {
        remove(inputRaw[index - windowSize])
        put(inputRaw[index])
        index++
    }
    return index
}
