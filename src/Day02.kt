import java.awt.Color.red
import kotlin.math.max

fun main() {

    fun part1(input: List<String>): Int = input
        .map { Game.fromInput(it) }
        .filter { game ->
            game.handfuls.none {
                it.getOrDefault("red", 0) > 12 ||
                        it.getOrDefault("green", 0) > 13 ||
                        it.getOrDefault("blue", 0) > 14
            }
        }
        .sumOf { it.id }

    fun part2(input: List<String>): Int = input
        .map { Game.fromInput(it) }
        .map { game ->
            game.handfuls.fold(mapOf("red" to 0, "green" to 0, "blue" to 0)) { acc, handful ->
                mapOf(
                    "red" to max(acc.getOrDefault("red", 0), handful.getOrDefault("red", 0)),
                    "green" to max(acc.getOrDefault("green", 0), handful.getOrDefault("green", 0)),
                    "blue" to max(acc.getOrDefault("blue", 0), handful.getOrDefault("blue", 0)),
                )
            }
        }
        .sumOf {
            it.getOrDefault("red", 0) * it.getOrDefault("green", 0) * it.getOrDefault("blue", 0)
        }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 8)
    check(part2(testInput) == 2286)

    val input = readInput("Day02")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

private typealias Handful = Map<String, Int>

private data class Game(
    val id: Int,
    val handfuls: List<Handful>,
) {

    companion object {
        fun fromInput(input: String): Game {
            val (gameLabel, handfulsString) = input.splitLast(": ")
            val gameId = "Game (\\d+)".toRegex().find(gameLabel)?.groupValues?.last()?.toIntOrNull()
                ?: throw IllegalArgumentException("")

            val handfuls = handfulsString.split("; ")
                .map { handfulString ->
                    handfulString.split(", ").associate {
                        val (count, color) = it.splitLast(" ")
                        color to count.toInt()
                    }
                }

            return Game(gameId, handfuls)
        }
    }
}
