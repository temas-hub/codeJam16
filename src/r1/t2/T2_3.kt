package r1.t2

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter

/**
 * Created by azhdanov on 5/8/16.
 */

val <T> List<T>.tail: List<T>
    get() = drop(1)

val <T> List<T>.head: T
    get() = first()

operator fun Pair<String,String>.compareTo(p : Pair<String, String>): Int {   // < > ==
    val diff = Math.abs(first.toLong() - second.toLong()) - Math.abs(p.first.toLong() - p.second.toLong())
    when {
        diff < 0 -> return -1
        diff > 0 -> return 1
        else -> return 0
    }
}


object T2_3 {

//    val IN = "res/r1/t2/sample.in"
//    val OU = "res/r1/t2/sample.out2"
//    val IN = "res/r1/t2/B-small-practice.in"
//    val OU = "res/r1/t2/B-small-practice.out2"

    val IN = "res/r1/t2/B-large-practice.in"
    val OU = "res/r1/t2/B-large-practice.out"

    private val QQ = '?'
    private val X = -1

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

            val result = TestCase(C, J).process()
            fw.write(String.format("Case #%d: %s %s\n", i, result.first, result.second))
        }
        fw.flush()
        fw.close()
    }

    data class TestCase (val C: CharArray, val J: CharArray) {
        fun process(): Pair<String, String> {
            return process(C.copyOf(), J.copyOf(), 0, 0)
        }
        fun process(a1: CharArray, a2: CharArray, i: Int, cmp: Int): Pair<String, String> {
            fun pickClosest(a: List<Pair<Int, Int>>): Pair<String, String> {
                val head = a.head
                if (C[i] == QQ) {
                    a1[i] = head.first.toString()[0]
                }
                if (J[i] == QQ) {
                    a2[i] = head.second.toString()[0]
                }
                val res = process(a1, a2, i + 1, if (cmp != 0) cmp else a1[i].toString().toInt() - a2[i].toString().toInt())
                if (a.tail.isEmpty()) {
                    return res
                }
                val tailRes = pickClosest(a.tail)
                return if (tailRes < res) tailRes else res
            }
            if (i >= a1.size) {
                return Pair(String(a1), String(a2))
            }
            val c1 = C[i]
            val c2 = J[i]
            if (cmp != 0) {
                if (c1 == QQ) {
                    a1[i] = if (cmp < 0) '9' else '0'
                }
                if (c2 == QQ) {
                    a2[i] = if (cmp < 0) '0' else '9'
                }
                return process(a1, a2, i + 1, cmp)
            } else {
                if (c1 == QQ && c2 == QQ) {
                    val op = listOf(Pair(0, 0), Pair(0, 1), Pair(1, 0))
                    return pickClosest(op)
                } else if (c1 != QQ && c2 != QQ) {
                    return process(a1, a2, i + 1, c1.toString().toInt() - c2.toString().toInt())
                } else if (c1 != QQ) {
                    val c1v = c1.toString().toInt()
                    val op = listOf(Pair(X, c1v-1), Pair(X, c1v), Pair(X, c1v+1)).filter { it.second >= 0 && it.second <= 9 }
                    return pickClosest(op)
                } else {
                    val c2v = c2.toString().toInt()
                    val op = listOf(Pair(c2v-1, X), Pair(c2v, X), Pair(c2v+1, X)).filter { it.first >= 0 && it.first <= 9 }
                    return pickClosest(op)
                }
            }
        }
    }

}