package r1c.tA

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

/**
 * @author Artem Zhdanov <azhdanov@griddynamics.com>
 * @since 02.06.2016
 */

fun main(args: Array<String>) {
    val IN = "res/r1c/tA/A-large-practice.in"
    val OU = "res/r1c/tA/A-large-practice.out"
//    val IN = "res/r1c/tA/A-small-practice.in"
//    val OU = "res/r1c/tA/A-small-practice.out"
//    val IN = "res/r1c/tA/sample.in"
//    val OU = "res/r1c/tA/sample.out"


    val br = BufferedReader(FileReader(IN))
    val fw = BufferedWriter(FileWriter(OU))

    var line = br.readLine()
    val numCases = Integer.valueOf(line)

    for (i in 1..numCases) {
        val N = br.readLine().toInt()
        val P = br.readLine().split(" ").map { it.toInt() }

        var f : Char? = null
        var s : Char? = null

        fw.write("Case #$i:")
        var work = P.withIndex().map { Pair(AtomicInteger(it.value), 'A' + it.index) }.sortedByDescending { it.first.get() }.toMutableList()
        while (work[0].first.get() > 0 || work[1].first.get() > 0) {
            //check majority
//            val total = work.sumBy { it.first.get() }
//            work.forEach {
//                        println("${it.second}=${it.first.get().toDouble()/total} ")
//            }
//            println("---")
            f = work[0].second
            if (work.size > 2 && work[0].first.get() == 2 && work[1].first.get() == 1 && work[2].first.get() == 1) {
                s = work[0].second
                work[0].first.decrementAndGet()
                work[0].first.decrementAndGet()
            } else if (work.size > 2 && work[0].first.get() == 1 && work[1].first.get() == 1 && work[2].first.get() == 1) {
                s = null
                work[0].first.decrementAndGet()
            }
            else if (work[1].first.get() == 0) {
                s = null
                work[0].first.decrementAndGet()
            } else if (work[0].first.get()> work[1].first.get()) {
                s = work[0].second
                work[0].first.decrementAndGet()
                work[0].first.decrementAndGet()
            } else {
                s = work[1].second
                work[0].first.decrementAndGet()
                work[1].first.decrementAndGet()
            }
            fw.write(" ${f}${s?.toString() ?: ""}")
            work.sortByDescending { it.first.get() }
        }
        fw.write("\n")
    }
    fw.flush()
    fw.close()
}