import kotlin.math.tan

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class Solution {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        var result: ListNode? = null
        var current: ListNode? = null
        if (l1 == null && l2 == null) {
            return result
        }
        var t1 = l1
        var t2 = l2
        var round = 0

        while (t1 != null) {
            var f = t1?.`val` ?: 0
            var l = t2?.`val` ?: 0
            var sum = f + l + round
            var rem = sum % 10
            round = sum / 10
            if (current == null) {
                result = ListNode(rem)
                current = result
            } else {
                current.next = ListNode(rem)
                current = current.next
            }
            t1 = t1?.next
            t2 = t2?.next
        }


        while (t2 != null) {
            var sum = t2.`val` + round
            var rem = sum % 10
            round = sum / 10
            if (current == null) {
                result = ListNode(rem)
                current = result
            } else {
                current.next = ListNode(rem)
                current = current.next
            }
            t2 = t2.next
        }

        if (round != 0) {
            if (current == null) {
                result = ListNode(round)
            } else {
                current.next = ListNode(round)
            }
        }

        return result
    }
}

fun main() {
    //51
    var t1 = ListNode(1)
    //51
    var t2 = ListNode(9)
    t2.next = ListNode(9)

//    var tArray = arrayOf(t1,t2)
//    switch(tArray)
//    t1 = tArray[0]
//    t2 = tArray[1]

    var v = Solution().addTwoNumbers(t1, t2)
    print(v?.`val`)
    while (v?.next != null) {
        v = v.next
        print(v?.`val`)
    }
}

fun switch(tArray:Array<ListNode>){
    tArray.reverse()
}