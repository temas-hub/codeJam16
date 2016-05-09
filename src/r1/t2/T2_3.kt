package r1.t2

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter

/**
 * Created by denis on 5/8/16.
 */

object T2_3 {

    val IN = "res/r1/t2/B-large-practice.in"
    val OU = "res/r1/t2/B-small-practice2.out"
    private val QQ = '?'

    @Throws(Exception::class)
    @JvmStatic fun main(args: Array<String>) {

        val br = BufferedReader(FileReader(IN))
        val fw = BufferedWriter(FileWriter(OU))

        var line = br.readLine()
        val numCases = Integer.valueOf(line)

        for (i in 1..numCases) {
            val inp = br.readLine().split(" ")

            val C = inp[0].toCharArray()
            val J = inp[1].toCharArray()

            val len = C.size
        }
    }

    fun ff(a1: List<Char>, a2: List<Char>, i: Int, cmp: Int): Long {
        if (i >= a1.size) {
            return Math.abs(a1.toString().toLong() - a2.toString().toLong())
        }
        val c1 = a1[i]
        val c2 = a2[i]
        if (cmp != 0) {
            if (c1 == QQ) {
                a1[i] = if (cmp < 0) '9' else '0'
                return ff(a1., a2, i+1, cmp)
            }
            if (c2 == QQ) {
                a2[i] = if (cmp < 0) '0' else '9'
            }
            return
        } else {
            if (c1 == QQ && c2 == QQ) {
                for (j in -1..1) {
                    val a1c = a1.copyOf()
                    val a2c = a2.copyOf()
                    when (j) {
                        0 -> {
                            a1c[i] = '0'
                            a2c[i] = '0'
                        }
                        1 -> {
                            a1c[i] = '1'
                            a2c[i] = '0'
                        }
                        -1 -> {
                            a1c[i] = '0'
                            a2c[i] = '1'
                        }
                    }
                    ff(a1c, a2c, i+1, j)
                }
            } else if (c1 != QQ && c2 != QQ) {
                return ff(a1, a2, i+1, cmp)
            } else if (c1 != QQ) {
                val c1v = c1.toString().toInt()
                for (j in  c1v - 1 .. c1v + 1) {
                    val a2c = a2.copyOf()
                    a2c[i] = j.toChar()
                    ff(a1, a2c, i+1, c1v - j)
                }
            } else {
                val c2v = c2.toString().toInt()
                var best : CharArray? = null
                var min : Long = Long.MAX_VALUE
                for (j in  c2v - 1 .. c2v + 1) {
                    val a1c = a1.copyOf()
                    a1c[i] = j.toChar()
                    val res = ff(a1c, a2, i+1, j - c2v)
                    if (res < min) {
                        min = res
                        best = a1c
                    }

                }
            }
        }


    }

}