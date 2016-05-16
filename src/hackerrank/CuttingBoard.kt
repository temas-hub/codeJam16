package hackerrank

import r1.t2.T2_3
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter

/**
 * @author Artem Zhdanov <azhdanov@griddynamics.com>
 * @since 12.05.2016
 */

fun main(args: Array<String>) {
    CuttingBoard.start(args)
}

object CuttingBoard {

    val IN = "res/hackerrank/cutboard/sample.in"
    val OU = "res/hackerrank/cutboard/sample.out"

    fun start(args: Array<String>) {
        val br = BufferedReader(FileReader(IN))
        val fw = BufferedWriter(FileWriter(OU))

        var line = br.readLine()
        val numCases = Integer.valueOf(line)

        for (i in 1..numCases) {
            var result : Long = 0
            val mn = br.readLine().split(" ")
            val m = mn[0].toInt()
            val n = mn[1].toInt()
            val ma = br.readLine().split(" ").map { it.toInt() }
            val na = br.readLine().split(" ").map { it.toInt() }

            val list = ma.mapIndexed { idx, v -> Triple(idx, v, 1) }.
                    plus(na.mapIndexed { idx, v -> Triple(idx, v, 0) }).
                    sortedByDescending { it.second + it.third}
            var mc = 1
            var nc = 1
            list.forEach {
                if (it.second == 0) {
                    result += it.first * nc
                    mc++
                } else {
                    result += it.first * mc
                    nc++
                }
            }
            fw.write(result.toString())
        }
        fw.flush()
        fw.close()
    }
}