import kotlin.math.abs

fun main() {
    var str = intArrayOf(
            1, 1, -1, -1, 3
    )

    println("result:${threeSumClosest(str, -1)}")
}

fun threeSumClosest(nums: IntArray, target: Int): Int {
    var r = emptyArray<Int>()
    nums.forEachIndexed { i, x ->
        nums.forEachIndexed { j, y ->
            if (i != j) {
                nums.forEachIndexed { k, z ->
                    if (k != i && k != j) {
                        var res = x + y + z
                        if (r.isEmpty() || abs(target - res) < abs(target - r[0])) {
                            if (r.isEmpty()) {
                                r += res
                            } else {
                                r[0] = res
                            }
                        }
                    }
                }
            }

        }
    }
    return r[0]
}