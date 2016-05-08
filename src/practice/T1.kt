package rond1

import sun.applet.Main

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
import java.util.ArrayList
import java.util.Collections

/**
 * User: temas
 * Date: 04.05.13
 */

val INP = "res/p1/A-small-practice.in"
val OUT = "res/p1/t1.out"

object T1 {
    private var outFile: String? = null
    @Throws(Exception::class)
    @JvmStatic fun main(args: Array<String>) {

        val br = BufferedReader(FileReader(INP))
        val fw = BufferedWriter(FileWriter(OUT))
        outFile = args[1]

        var line = br.readLine()
        val numCases = Integer.valueOf(line)!!
        for (i in 1..numCases) {
            line = br.readLine()
            var nums = line.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val A = Integer.valueOf(nums[0])!!
            val N = Integer.valueOf(nums[1])!!
            val motes = ArrayList<Int>(N)

            line = br.readLine()
            nums = line.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            for (num in nums) {
                motes.add(Integer.valueOf(num))
            }

            Collections.sort(motes)

            var currentMote = A
            var minOperations = Integer.MAX_VALUE
            var deletion = 0
            var operations = 0
            var prevDelitions = 0

            do {
                operations = 0
                deletion = 0
                for (m in motes) {
                    if (currentMote > m) {
                        currentMote += m
                    } else if (currentMote * 2 - 1 > m) {
                        //add
                        currentMote = currentMote * 2 - 1
                        currentMote += m
                        operations++
                    } else {
                        while (currentMote * 2 - 1 <= m && prevDelitions > 0) {
                            prevDelitions--
                            currentMote = currentMote * 2 - 1
                            operations++
                        }
                        if (currentMote * 2 - 1 <= m) {
                            deletion++
                            operations++
                        } else {
                            currentMote = currentMote * 2 - 1
                            currentMote += m
                            operations++
                        }

                    }
                }

                if (operations < minOperations) {
                    minOperations = operations
                } else
                    break

                prevDelitions = deletion
                currentMote = A

            } while (deletion > 0)


            fw.write(String.format("Case #%d: %d\n", i, minOperations))
        }
        fw.flush()
        fw.close()
        br.close()
    }


}
