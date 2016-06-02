package r1.t3

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
import java.util.*

/**
 * @author Artem Zhdanov <azhdanov@griddynamics.com>
 * @since 01.06.2016
 */

fun main(args: Array<String>) {

    //val IN = "res/r1/t3/C-large-practice.in"
    //val OU = "res/r1/t3/C-large-practice.out"
    val IN = "res/r1/t3/C-small-practice.in"
    val OU = "res/r1/t3/C-small-practice.out"
//    val IN = "res/r1/t3/sample.in"
//    val OU = "res/r1/t3/sample.out"


    val br = BufferedReader(FileReader(IN))
    val fw = BufferedWriter(FileWriter(OU))

    var line = br.readLine()
    val numCases = Integer.valueOf(line)

    for (i in 1..numCases) {
        val N = br.readLine().toInt()
        val list = ArrayList<Pair<String, String>>(N)

        val f = HashSet<String>()
        val s = HashSet<String>()
        for (t in 1..N) {
            val v = br.readLine().split(" ")
            val p = Pair(v[0], v[1])
            list.add(p)
            f.add(p.first)
            s.add(p.second)
        }

        val mask = 1.shl(N)
        var min = Int.MAX_VALUE
        for (m in 1..mask - 1) {
            var size = 0
            val ff = HashSet<String>()
            val ss = HashSet<String>()
            for (e in 0..N-1) {
                if (m and 1.shl(e) > 0 ) {
                    size++
                    ff.add(list[e].first)
                    ss.add(list[e].second)
                }
            }
            if (ff.containsAll(f) && ss.containsAll(s)) {
                if (size < min) {
                    min = size
                }
            }
        }


        fw.write(String.format("Case #%d: %d \n", i, N - min))
        println(i)
    }
    fw.flush()
    fw.close()
}

private fun toIndex(f: HashMap<String, MutableList<Int>>, s: HashMap<String, MutableList<Int>>, index: Int, pair: Pair<String, String>) {
    f.getOrPut(pair.first, { ArrayList() }).add(index)
    s.getOrPut(pair.second, { ArrayList() }).add(index)
}
