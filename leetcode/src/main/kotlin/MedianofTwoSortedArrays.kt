fun main() {
    var a1 = intArrayOf(1, 2)
    var a2 = intArrayOf(3, 4)

    println("median:" + findMedianSortedArrays(a1, a2))
}

fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
    var newA = IntArray(nums1.size + nums2.size)
    var i = 0
    var j = 0
    var ni = 0
    var half = newA.size / 2
    var remain = newA.size % 2
    var pair: Pair<Int, Int?>? = null
    if (nums1.isEmpty()) {
        if (nums2.size / 2 > 0 && nums2.size % 2 == 0) {
            pair = Pair(nums2[nums2.size / 2 - 1], nums2[nums2.size / 2])
        } else if (nums2.size / 2 > 0 && nums2.size % 2 == 1) {
            pair = Pair(nums2[nums2.size / 2], null)
        } else {
            pair = Pair(nums2[0], null)
        }
    } else if (nums2.isEmpty()) {
        if (nums1.size / 2 > 0 && nums1.size % 2 == 0) {
            pair = Pair(nums1[nums1.size / 2 - 1], nums1[nums1.size / 2])
        } else if (nums1.size / 2 > 0 && nums1.size % 2 == 1) {
            pair = Pair(nums1[nums1.size / 2], null)
        } else {
            pair = Pair(nums1[0], null)
        }
    } else {
        loop@
        while (i < nums1.size) {
            while (j < nums2.size && nums1[i] >= nums2[j]) {
                newA[ni] = nums2[j]
                j++
                if (ni == half) {
                    if (remain == 1) {
                        pair = Pair(newA[ni], null)
                        break@loop
                    } else {
                        pair = Pair(newA[ni - 1], newA[ni])
                        break@loop
                    }
                }
                ni++
            }
            newA[ni] = nums1[i]
            i++
            if (ni == half) {
                if (remain == 1) {
                    pair = Pair(newA[ni], null)
                    break@loop
                } else {
                    pair = Pair(newA[ni - 1], newA[ni])
                    break@loop
                }
            }
            ni++
        }
        if (pair == null) {
            loop@
            while (ni < newA.size && j < nums2.size) {
                newA[ni] = nums2[j]
                j++
                if (ni == half) {
                    if (remain == 1) {
                        pair = Pair(newA[ni], null)
                        break@loop
                    } else {
                        pair = Pair(newA[ni - 1], newA[ni])
                        break@loop
                    }
                }
                ni++
            }
        }
    }
    if (pair?.second == null) {
        return (pair?.first ?: 0) * 1.0
    }
    return (pair.first + (pair.second ?: 0)) / 2.0
}
