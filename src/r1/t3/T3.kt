package r1.t3

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


fun main(args: Array<String>) {

    //val IN = "res/r1/t3/C-large-practice.in"
    //val OU = "res/r1/t3/C-large-practice.out"
//    val IN = "res/r1/t3/C-small-practice.in"
//    val OU = "res/r1/t3/C-small-practice.out"
    val IN = "res/r1/t3/sample.in"
    val OU = "res/r1/t3/sample.out"


    val br = BufferedReader(FileReader(IN))
    val fw = BufferedWriter(FileWriter(OU))

    var line = br.readLine()
    val numCases = Integer.valueOf(line)

    for (i in 1..numCases) {
        val N = br.readLine().toInt()
        val f = HashMap<String, MutableList<Int>>()
        val s = HashMap<String, MutableList<Int>>()
        val list = ArrayList<Pair<String, String>>(N)

        // run one. if not exist in both -> BB
        // run two+N if exist in both of BB-> BB+=?; r++
        // run 2+N if exist in one of BB-> BB+=? -> run two

        for (t in 0..N-1) {
            val v = br.readLine().split(" ")
            val p = Pair(v[0], v[1])
            list.add(p)
            toIndex(f, s, t, p)
        }
        var result = 0
        val f2 = HashMap<String, MutableList<Int>>()
        val s2 = HashMap<String, MutableList<Int>>()

        val f2_can = HashMap<String, MutableList<Int>>()
        val s2_can = HashMap<String, MutableList<Int>>()

        val g2 = ArrayList<Int>()
        val can = ArrayList<Int>()
        for (k in list.withIndex()) {
            val ff = f.get(k.value.first)
            val ss = s.get(k.value.second)
            if (ff != null && ff.size == 1 || ss != null && ss.size == 1) {
                toIndex(f2, s2, k.index, k.value)
                g2.add(k.index)
            } else {
                toIndex(f2_can, s2_can, k.index, k.value)
                can.add(k.index)
            }
        }

        val can2 = ArrayList<Int>()
        for (i3 in can) {
            val canP = list.get(i3)
            if (f2.containsKey(canP.first) && s2.containsKey(canP.second)) {
                result++
            } else {
                val ff2 = f2_can.get(canP.first)
                val ss2 = s2_can.get(canP.second)
                if (ff2 != null && ff2.size > 1 && ss2 != null && ss2.size > 1) {
                    ff2.remove(i3)
                    ss2.remove(i3)
                    toIndex(f2, s2, i3, canP)
                } else {
                    can2.add(i3)
                }
            }
        }
        for (i4 in can2) {
            val canP = list.get(i4)
            if (f2.containsKey(canP.first) && s2.containsKey(canP.second)) {
                result++
            }
        }

        for (ff2 in f2_can.values) {
            result += ff2.size - 1
        }

        for (ss2 in s2_can.values) {
            result += ss2.size - 1
        }


        fw.write(String.format("Case #%d: %d \n", i, result))
        println(i)
    }
    fw.flush()
    fw.close()
}

private fun toIndex(f: HashMap<String, MutableList<Int>>, s: HashMap<String, MutableList<Int>>, index: Int, pair: Pair<String, String>) {
    f.getOrPut(pair.first, { ArrayList() }).add(index)
    s.getOrPut(pair.second, { ArrayList() }).add(index)
}