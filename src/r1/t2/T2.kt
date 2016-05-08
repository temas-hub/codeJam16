package r1.t2

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

//val INP = "res/r1/t1/A-large_1.in"
//val OUT = "res/r1/t1/A-large_1.out"

val INP = "res/r1/t2/sample.in"
val OUT = "res/r1/t2/sample.out"

object T1 {
    private var outFile: String? = null

    val seq = arrayOf(0,2,4,6,1,3,5,7,8,9)

    private val QQ = '?'

    @Throws(Exception::class)
    @JvmStatic fun main(args: Array<String>) {

        val br = BufferedReader(FileReader(INP))
        val fw = BufferedWriter(FileWriter(OUT))

        var line = br.readLine()
        val numCases = Integer.valueOf(line)

        for (i in 1..numCases) {
            val inp = br.readLine().split(" ")

            val C = inp[0].toCharArray()
            val J = inp[1].toCharArray()

            val len = C.size

            var lastReplace = -1
            for (i in len-1 downTo 0) {
                if (C[i] != QQ && J[i] != QQ && C[i] != J[i]) {
                    val cc = Integer.valueOf(C[i].toString())
                    val jj = Integer.valueOf(J[i].toString())
                    var s1: Char? = null
                    var s2: Char? = null
                    if (cc < jj) {
                        s1 = '9'
                        s2 = '0'
                    } else {
                        s1 = '0'
                        s2 = '9'
                    }
                    for (k in i..len-1) {
                        if (C[k] == QQ) {
                            C[k] = s1
                        }
                        if (J[k] == QQ) {
                            J[k] = s2
                        }
                    }
                    lastReplace = i
                }
            }
                for (j in 0..len-1) {
                    if (C[j] == QQ && J[j] != QQ) {
                        C[j] = J[j]
                    } else if (C[j] != QQ && J[j] == QQ) {
                        J[j] = C[j]
                    }

                    if (C[j] == QQ) {
                        C[j] = '0'
                    }
                    if (J[j] == QQ) {
                        J[j] = '0'
                    }
                }

            fw.write(String.format("Case #%d: %s %s\n", i, String(C), String(J)))
        }
        fw.flush()
        fw.close()
    }
}