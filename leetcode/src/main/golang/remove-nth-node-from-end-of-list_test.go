package leetcode

import "testing"

type ListNode struct {
	Val  int
	Next *ListNode
}

func TestRemoveNthFromEnd(t *testing.T) {
	var head *ListNode
	var p *ListNode
	for i := 1; i < 6; i++ {
		d := &ListNode{}
		d.Val = i
		if head == nil {
			head = d
		}
		if p != nil {
			p.Next = d
		}
		p = d
	}
	head = removeNthFromEnd(head, 2)
}

type RNode struct {
	Val  *ListNode
	Next *RNode
}

func removeNthFromEnd(head *ListNode, n int) *ListNode {
	var end *RNode
	cache := map[int]*RNode{}
	j := 1
	for i := head; i != nil; i = i.Next {
		d := &RNode{}
		d.Val = i
		d.Next = end
		end = d
		cache[j] = d
		j++
	}

	if cache[j-n].Next == nil && cache[j-n].Val == nil {
		return nil
	} else if cache[j-n].Next == nil && cache[j-n].Val != nil {
		return cache[j-n].Val.Next
	}
	cache[j-n].Next.Val.Next = cache[j-n].Val.Next
	return head
}
