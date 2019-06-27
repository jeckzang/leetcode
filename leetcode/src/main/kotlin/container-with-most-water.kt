
fun main() {
    var str = intArrayOf(
            1,2,3,4,5,6,7,8,9,10)

    println("max area:${maxArea(str)}")
}

fun maxArea(h: IntArray): Int {
    var tc = 0
    var xmin = 0
    var mini = 0
    h.forEachIndexed {i,x1->
        h.forEachIndexed loop@{j,x2->
            mini =minOf(x1,x2)
            var ijabs = kotlin.math.abs(i-j)
            if (mini<xmin && ijabs<xmin){
                return@loop
            }
            var c = count(i,j,mini)
            if (c>tc){
                xmin = minOf(mini,ijabs)
                tc = c
            }
        }
    }
    return tc
}

fun count(i11:Int, i12:Int, mini:Int):Int{
    var i1 = i11
    var i2 = i12
    if (i1>i2){
        var i3 = i1
        i1 = i2
        i2 = i3
    }
    return (i2-i1)*mini
}