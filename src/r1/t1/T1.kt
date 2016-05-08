package r1.t1

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

val INP = "res/r1/t1/A-large_1.in"
val OUT = "res/r1/t1/A-large_1.out"

//val INP = "res/r1/t1/sample.txt"
//val OUT = "res/r1/t1/sample.out"

object T1 {
    private var outFile: String? = null

    val arr = arrayOf("ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE")
    val markers = arrayOf("Z", "O", "W", "R", "U", "F", "X", "S", "G", "N")

    val seq = arrayOf(0,2,4,6,1,3,5,7,8,9)

    @Throws(Exception::class)
    @JvmStatic fun main(args: Array<String>) {

        val br = BufferedReader(FileReader(INP))
        val fw = BufferedWriter(FileWriter(OUT))

        var line = br.readLine()
        val numCases = Integer.valueOf(line)

        for (i in 1..numCases) {
            val S = br.readLine()
            val asList = S.toCharArray().toMutableList()
            val res = Array<Int>(10, { i -> 0 })

            while (asList.isNotEmpty()) {
                for (se in seq) {
                //for (num in markers.withIndex()) {
                    var found = true
                    out@
                    while (asList.isNotEmpty() && found) {
                        for (numChar in markers[se].toCharArray()) {
                            if (!asList.contains(numChar)) {
                                found = false
                                break@out
                            }
                        }
                        arr[se].toCharArray().forEach { asList.remove(it) }
                        res[se]++
                        if (asList.isEmpty()) {
                            break
                        }
                    }
                }
            }

            val map = res.withIndex().map { it -> String(Array(it.value, { i -> it.index.toString()[0] }).toCharArray()) }
            val result = map.reduce { s1, s2 -> s1.plus(s2) }
            fw.write(String.format("Case #%d: %s\n", i, result))
        }
        fw.flush()
        fw.close()
    }
}