fun main() {
    var str = 1534236469

    println("convert to ${reverse(str)}")
}

fun reverse(x: Int): Int {
    var sx = x.toString()
    sx = sx.reversed()
    var flag = 1
    if (sx.endsWith("-")) {
        flag = -1
        sx = sx.dropLast(1)
    }
    var lsx = sx.toLong()
    if (lsx > Int.MAX_VALUE) {
        return 0
    }
    return sx.toInt() * flag
}