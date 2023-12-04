fun main() {
    fun part1(input: List<String>) =
        input.map { obfuscatedCalibrationValue ->
            val firstDigit = obfuscatedCalibrationValue.first { it.isDigit() }
            val secondDigit = obfuscatedCalibrationValue.last { it.isDigit() }
            "$firstDigit$secondDigit"
        }.sumOf { it.toInt() }

    val digitMap = mapOf(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9,
    )

    val digitRegex = "(one|two|three|four|five|six|seven|eight|nine|[0-9])".toRegex()
    val reverseDigitRegex = "(eno|owt|eerht|ruof|evif|xis|neves|thgie|enin|[0-9])".toRegex()

    fun part2(input: List<String>) =
        input.map { obfuscatedCalibrationValue ->
            val firstDigit = digitRegex.find(obfuscatedCalibrationValue)?.groups?.first()?.value
                ?.let { digit ->
                    digit.toIntOrNull() ?: digitMap[digit]
                }
            val secondDigit = reverseDigitRegex.find(obfuscatedCalibrationValue.reversed())?.groups?.first()?.value
                ?.let { digit ->
                    digit.toIntOrNull() ?: digitMap[digit.reversed()]
                }
            "$firstDigit$secondDigit"
        }
            .sumOf { it.toInt() }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    // check(part1(testInput) == 142)
    // check(part2(testInput) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
