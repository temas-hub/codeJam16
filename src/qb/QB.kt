package qb


/**
 * @author Artem Zhdanov <azhdanov@griddynamics.com>
 * @since 08.04.2016
 */

import sun.applet.Main

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
import java.util.*

/**
 * User: temas
 * Date: 04.05.13
 */

val INP = "res/q2/in.txt"
val OUT = "res/q2/qb_test.out"

object QB {
    @Throws(Exception::class)
    @JvmStatic fun main(args: Array<String>) {

        val br = BufferedReader(FileReader(INP))
        val fw = BufferedWriter(FileWriter(OUT))

        var line = br.readLine()
        val numCases = Integer.valueOf(line)

        for (i in 1..numCases) {
            line = br.readLine()
            var res = 0
            var ca = line.toCharArray().reversedArray()
            var j = 0
            while (j <= ca.lastIndex) {
                if (ca[j] == '-') {
                    res++
                    ca = ca.reversedArray()
                    for (k in ca.withIndex()) {
                        if (k.value == '-') {
                            ca[k.index] = '+'
                        } else {
                            ca[k.index] = '-'
                        }
                    }
                }
                j++
            }
            fw.write(String.format("Case #%d: %d\n", i, res))
        }
        fw.flush()
        fw.close()
        br.close()
    }


}

