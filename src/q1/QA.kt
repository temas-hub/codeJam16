package q1


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

val INP = "res/q1/A-large.in"
val OUT = "res/q1/t2.out"

object T1 {
    private var outFile: String? = null
    @Throws(Exception::class)
    @JvmStatic fun main(args: Array<String>) {

        val br = BufferedReader(FileReader(qb.INP))
        val fw = BufferedWriter(FileWriter(qb.OUT))

        var line = br.readLine()
        val numCases = Integer.valueOf(line)

        for (i in 1..numCases) {
            line = br.readLine()
            var res = "INSOMNIA"
            val aNum = Integer.valueOf(line)
            val map = HashSet<Int>()
            for (j in 1..1000) {
                val lastNum = (aNum * j)
                val numArr = lastNum.toString().toCharArray()
                for (c in numArr) {
                    map.add(c.toInt())
                }
                if (map.size == 10) {
                    res = lastNum.toString()
                    break
                }
            }
            fw.write(String.format("Case #%d: %s\n", i, res))
        }
        fw.flush()
        fw.close()
        br.close()
    }


}

