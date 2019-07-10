fun main() {
    //Given linked list: 1->2->3->4->5, and n = 2.
    var li = ListNode(1)
    var head = li
    li.next = ListNode(2)
    removeNthFromEnd(head,2)
    var f:ListNode? = head
    while(f!=null){
        println(f.`val`)
        f = f.next
    }
}



/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */

fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
    var list = emptyList<Map<ListNode,ListNode?>>()
    var headNext = head?.next
    var last:ListNode?=null
    var f:ListNode? = head
    while(f!=null){
        if (last!=null){
            var pair = HashMap<ListNode,ListNode?>()
            pair.put(last!!,f.next)
            list+=pair
        }
        last = f
        f = f.next
    }
    if (list.size!=0){
        if (list.size>n){
            var m =list.get(list.size-n)
            m.keys.first().next = m.values.first()
            return head
        }else if (list.size == n){
            var m =list.get(list.size-n)
            head?.next = m.values.first()
            return head
        }else if (n==list.size+1){
            return headNext
        }
    }
    return null
}
