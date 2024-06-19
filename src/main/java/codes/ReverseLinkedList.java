package codes;

//leetcode 206
//https://leetcode.com/problems/reverse-linked-list/

import helper.Helper;

public class ReverseLinkedList {

    public static void main(String[] args) {
        ListNode head =
                new ListNode(
                        1, new ListNode(
                        2, new ListNode(
                        3, new ListNode(
                        4, new ListNode(
                        5)))));

        Helper.printLinkedList(head);
        Helper.printLinkedList(reverseList(head));

    }


    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode temp = current;
            current = current.next;
            temp.next = prev;
            prev = temp;
        }
        return prev;
    }

    public static class ListNode {
        private int val;
        private ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }
    }

}
