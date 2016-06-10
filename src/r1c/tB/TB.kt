package r1c.tB

import java.io.*

/**
 * @author Artem Zhdanov <azhdanov@griddynamics.com>
 * @since 07.06.2016
 */


fun main(args: Array<String>) {
//    val IN = "res/r1c/tB/B-large-practice.in"
//    val OU = "res/r1c/tB/B-large-practice.out"
    val IN = "res/r1c/tB/B-small-practice.in"
    val OU = "res/r1c/tB/B-small-practice.out"
//    val IN = "res/r1c/tB/sample.in"
//    val OU = "res/r1c/tB/sample.out"


    val br = BufferedReader(FileReader(IN))
    val fw = BufferedWriter(FileWriter(OU))

    var line = br.readLine()
    val numCases = Integer.valueOf(line)

    for (i in 1..numCases) {
        println("Case #$i")
        val (B, M) = br.readLine().split(" ").map { it.toInt() }


        var result : Array<Array<Int>>? = null
        val bitCount = Math.pow(B.toDouble(), 2.0).toInt() - B
        val mask = 1L.shl(bitCount).toLong()
        var diag: Long = 1;
        for (x in 1 .. B - 1) {
            diag = diag.shl(B+1) + 1
        }
//        printDiag(diag, B, 0)
//        println()
        for (m in 1 .. mask - 1) {
            if (diag and m == 0L) {
                val mat = generateMatrix(B, bitCount, m)
                //printMatrix(mat, null)
                val numberOfPaths = bfs(mat, B, 0, emptySet(), M)
                if (numberOfPaths != null && numberOfPaths == M) {
                    result = mat
                    break
                }
            }
        }
        if (result != null) {
            fw.write("Case #$i: POSSIBLE\n")
            printMatrix(result, fw)
        } else {
            fw.write("Case #$i: IMPOSSIBLE\n")
        }



//        val mat = readMatrix(B, "res/r1c/tB/input.mat")
//        println(bfs(mat, B, 0, emptySet()))
    }
    fw.flush()
    fw.close()
}

private fun printDiag(diag: Long, B: Int, divCount: Int) {
    if (diag < 2) {
        print(diag)
        return
    }
    printDiag(diag / 2, B, divCount + 1)
    print(diag % 2)
    if (divCount % B == 0) {
        println()
    }
}

private fun bfs(mat: Array<Array<Int>>, B: Int, y : Int, visited: Set<Int>, M: Int) : Int?{
    if (y in visited) {
        return null;
    }
    if (y == B - 1) {
        return 1
    }
    var sum = 0
    for (x in 0 .. B - 1) {
        if (mat[x][y] == 1) {
            val next = bfs(mat, B, x, visited.plus(y), M)
            if (next == null) {
                return null
            }
            sum += next
            if (sum > M) {
                return null
            }
        }
    }
    return sum
}

private fun readMatrix(B: Int, file: String): Array<Array<Int>> {
    val br = BufferedReader(FileReader(file))
    val mat = Array(B, { Array(B, { 0 }) })
    for (i in 0 .. B -1) {
        val line = br.readLine().split(" ").map { it.toInt() }
        line.forEachIndexed { idx, n ->
            mat[idx][i] = n
        }
    }
    return mat
}

private fun generateMatrix(B: Int, bitCount: Int, mask: Long): Array<Array<Int>> {
    val mat = Array(B, { Array(B, { 0 }) })
    for (k in 0..bitCount - 1) {
        if (mask and 1.shl(k).toLong() > 0) {
            val x = k % B
            val y = k / B
            if (x == y) continue
            mat[x][y] = 1
        }
    }
    return mat
}
private fun printMatrix(mat: Array<Array<Int>>, fw: Writer?) {
    for (i in 0 .. mat.size -1) {
        for (j in 0 .. mat.size -1) {
            fw?.write(mat[j][i].toString())
            print(mat[j][i].toString())
        }
        fw?.write("\n")
        println()
    }
    println("------------")
}