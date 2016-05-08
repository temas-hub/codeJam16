package qb

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter

/**
 * @author Artem Zhdanov <azhdanov@griddynamics.com>
 * @since 08.04.2016
 */
object QB2 {
    val INP = "res/q2/B-large.in"
    val OUT = "res/q2/qb_large.out"

    @Throws(Exception::class)
    @JvmStatic fun main(args: Array<String>) {

        val br = BufferedReader(FileReader(INP))
        val fw = BufferedWriter(FileWriter(OUT))

        var line = br.readLine()
        val numCases = Integer.valueOf(line)

        for (i in 1..numCases) {
            line = br.readLine()
            var res = 0
            line = line.trimEnd('+')
            if (line.length > 0) {
                res++
                val arr = line.toCharArray()
                var sign = arr[0]
                for (c in arr) {
                    if (c != sign) {
                        res++
                        sign = c
                    }
                }
            }
            fw.write(String.format("Case #%d: %d\n", i, res))
        }
        fw.flush()
        fw.close()
        br.close()
    }

}