package hackerrank

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter

/**
 * @author Artem Zhdanov <azhdanov@griddynamics.com>
 * @since 12.05.2016
 */

fun main(args: Array<String>) {
    val IN = "res/hackerrank/cutboard/sample.in"
    val OU = "res/hackerrank/cutboard/sample.out"

    val br = BufferedReader(FileReader(IN))
    val fw = BufferedWriter(FileWriter(OU))

    fun readInts() = br.readLine().split(" ").map { it.toInt() }

    var line = br.readLine()
    val numCases = Integer.valueOf(line)

    for (i in 1..numCases) {
        br.readLine() //skip

        // solution #1
        var result : Long = 0
        val mc = arrayOf(1)
        val nc = arrayOf(1)
        val ma = readInts()
        val na = readInts()

        ma.map { Pair(it, Triple(nc, mc, 1))}.plus(
         na.map{ Pair(it, Triple(mc, nc, 0))}).sortedByDescending { it.first + it.second.third / 2}.
                forEach {
                    result += it.first * it.second.first[0]
                    it.second.second[0]++
                }

        fw.write(result.toString() + "\n")

        // solution #2
        val res2 = listOf(Triple(0,1,1)).plus(ma.map { Triple(it, 0, 1) }.plus(
                na.map { Triple(it, 1, 0)} ).sortedByDescending { it.first + it.third / 2 }).
                reduce { t1, t2 -> Triple(t1.first + t2.first * (t1.second*t2.third + t1.third*t2.second), t1.second + t2.second, t1.third + t2.third) }
        fw.write(res2.first.toString() + "\n")
    }
    fw.flush()
    fw.close()
}