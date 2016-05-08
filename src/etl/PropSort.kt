package etl

import java.io.BufferedReader
import java.io.FileReader
import java.util.*

/**
 * @author Artem Zhdanov <azhdanov@griddynamics.com>
 * @since 04.05.2016
 */


fun main(args: Array<String>) {
    PropSort.run("D:\\work\\Prop\\inst_promoted.properties")
}

object PropSort {

    fun run(file: String) {
        val inp = BufferedReader(FileReader(file))
        var line = inp.readLine()
        val map = TreeMap<String, String>()
        while (line != null) {
            val eqInd = line.indexOf("=")
            val prop = line.substring(0, eqInd - 1)
            map.put(prop, line)
            line = inp.readLine()
        }
        map.forEach { s1, s2 -> println(s2)}

    }

}