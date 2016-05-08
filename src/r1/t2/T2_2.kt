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

val IN = "res/r1/t2/B-large-practice.in"
val OU = "res/r1/t2/B-small-practice.out"

//val IN = "res/r1/t2/sample.in"
//val OU = "res/r1/t2/sample.out"

object T2_2 {
    val can = arrayOf(0,9)

    private val QQ = '?'

    @Throws(Exception::class)
    @JvmStatic fun main(args: Array<String>) {

        val br = BufferedReader(FileReader(IN))
        val fw = BufferedWriter(FileWriter(OU))

        var line = br.readLine()
        val numCases = Integer.valueOf(line)

        fun conv(c: Char, r: Char) : Char? {
            if ((c == '2' || c == '3' || c == '4') && r == QQ) {
                return null
            }
            return when(c) {
                '0' -> '0'
                '1' -> '1'
                '2' -> Math.max(0, Integer.parseInt(r.toString()) - 1).toString()[0]
                '3' -> r
                '4' -> Math.min(9, Integer.parseInt(r.toString()) + 1).toString()[0]
                else -> '9'
            }
        }

        for (i in 1..numCases) {
            val inp = br.readLine().split(" ")

            val C = inp[0].toCharArray()
            val J = inp[1].toCharArray()

            val len = C.size

            val acc = { count : Int, ch : Char -> if (ch == QQ) count + 1 else count }

            val qCnt = C.fold(0, acc) + J.fold(0, acc)

            var minDiff = Long.MAX_VALUE
            var can1 : String? = null
            var can2 : String? = null
            start@
            for (i in 0 .. Math.pow(6.0, qCnt.toDouble()).toInt() - 1) {
                val nums = Integer.toString(i, 6).padStart(qCnt,'0').toCharArray().toMutableList()
                val str1 = CharArray(len, {'x'})
                val str2 = CharArray(len, {'x'})

                for (i in 0..len-1) {
                    if (C[i] == QQ) {
                        val rmd = nums.removeAt(0)
                        val conv = conv(rmd, J[i])
                        if (conv == null) continue@start
                        str1[i] = conv
                    } else {
                        str1[i] = C[i]
                    }
                }

                for (i in 0..len-1) {
                    if (J[i] == QQ) {
                        val rmd = nums.removeAt(0)
                        val conv = conv(rmd, str1[i])
                        if (conv == null) continue@start
                        str2[i] = conv
                    } else {
                        str2[i] = J[i]
                    }
                }

                val s1 = String(str1)
                val s2 = String(str2)
                val sNum1 = s1.toLong()
                val sNum2 = s2.toLong()
                val diff = Math.abs(sNum1 - sNum2)
                if (diff < minDiff ) {
                    minDiff = diff
                    can1 = s1
                    can2 = s2
                } /*else if (diff.equals(minDiff) && can1 != null && sNum1 < Integer.valueOf(can1)) {
                    minDiff = diff
                    can1 = s1
                    can2 = s2
                } else if (can2 != null && diff.equals(minDiff) && sNum1.equals(Integer.valueOf(can1)) && sNum2 < Integer.valueOf(can2)) {
                    minDiff = diff
                    can1 = s1
                    can2 = s2
                } */
            }

            fw.write(String.format("Case #%d: %s %s\n", i, can1, can2))
            println(i)
        }
        fw.flush()
        fw.close()
    }
}