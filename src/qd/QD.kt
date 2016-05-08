package qd

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
import java.util.*

/**
 * @author Artem Zhdanov <azhdanov@griddynamics.com>
 * @since 08.04.2016
 */
object QD {
    val INP = "res/q4/D-small-attempt0.in"
    val OUT = "res/q4/qd_small.out"

    @Throws(Exception::class)
    @JvmStatic fun main(args: Array<String>) {

        val br = BufferedReader(FileReader(INP))
        val fw = BufferedWriter(FileWriter(OUT))

        var line = br.readLine()
        val numCases = Integer.valueOf(line)

        for (i in 1..numCases) {
            line = br.readLine()
            var res = ArrayList<Int>()
            val comp = line.split(" ")
            for (i in 1..comp[0].toInt()) {
                res.add(i)
            }

            fw.write(String.format("Case #%d:", i))
            res.forEach { fw.write(" " + it) }
            fw.write("\n")
        }
        fw.flush()
        fw.close()
        br.close()
    }
}