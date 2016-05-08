package qc

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
import java.math.BigInteger
import java.text.DecimalFormat
import java.util.*

/**
 * @author Artem Zhdanov <azhdanov@griddynamics.com>
 * @since 08.04.2016
 */
object QC1 {
    val INP = "res/q3/qc_small_pre.txt"
    val OUT = "res/q3/qc_small_pre.out"

    @Throws(Exception::class)
    @JvmStatic fun main(args: Array<String>) {

        val br = BufferedReader(FileReader(INP))
        val fw = BufferedWriter(FileWriter(OUT))

        var line = br.readLine()
        val numCases = Integer.valueOf(line)
        line = br.readLine()
        val split = line.split(" ")

        val N = split[0]
        val J = split[1]

        fw.write(String.format("Case #%d:\n", 1))

        val maxN = Math.pow(2.toDouble(), N.toDouble()).toInt() - 1
        val minN = Math.pow(2.toDouble(), N.toDouble() - 1).toInt() + 1

        var count = J.toInt()
        for (i in minN..maxN step 2) {
            val binary = toBinary(i)
            var isJamCoin = true
            var list = ArrayList<Int>()
            for (r in 2..10) {
                val radPres = java.lang.Long.parseLong(binary, r)
                val divizor = isPrime(radPres)
                if (divizor == -1) {
                    isJamCoin = false
                    break
                } else {
                    list.add(divizor)
                }
            }
            if (isJamCoin) {
                fw.write(binary + " ")
                list.forEach { fw.write(" " + it) }
                fw.write("\n")

                print(binary + " ")
                list.forEach { print(" " + it) }
                println()
                count--
            }
            if (count == 0) {
                break;
            }
        }
        fw.flush()
        fw.close()
        br.close()
    }

    private fun isPrime(num: Long): Int {
        if (num < 2) return 1;
        if (num == 2L) return -1;
        if (num % 2L == 0L) return 2;
        var i = 3
        while (i * i < num) {
            if (num % i == 0L) return i;
            i += 2
        }
        return -1;
    }

    private fun toBinary(i: Int): String {
        if (i < 2) return i.toString()
        return toBinary(i / 2).plus(i % 2)
    }
}