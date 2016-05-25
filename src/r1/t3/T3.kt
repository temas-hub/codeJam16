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

    val IN = "res/r1/t3/sample.in"
    val OU = "res/r1/t3/sample.out"

    val br = BufferedReader(FileReader(IN))
    val fw = BufferedWriter(FileWriter(OU))

    var line = br.readLine()
    val numCases = Integer.valueOf(line)

    for (i in 1..numCases) {
        val inp = br.readLine()

//            fw.write(String.format("Case #%d: %s %s\n", i, can1, can2))
        println(i)
    }
    fw.flush()
    fw.close()
}