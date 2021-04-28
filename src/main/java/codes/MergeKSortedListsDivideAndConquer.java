package codes;

public class MergeKSortedListsDivideAndConquer {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int i, int j) {
        if (i == j) {
            return lists[i];
        }
        int m = (i + j) / 2;
        ListNode left = merge(lists, i, m);
        ListNode right = merge(lists, m + 1, j);
        ListNode sol = new ListNode(0);
        ListNode ptr = sol;
        while (left != null && right != null) {
            if (left.val <= right.val) {
                ptr.next = left;
                left = left.next;
            } else {
                ptr.next = right;
                right = right.next;
            }
            ptr = ptr.next;
        }
        while (left != null) {
            ptr.next = left;
            left = left.next;
            ptr = ptr.next;
        }
        while (right != null) {
            ptr.next = right;
            right = right.next;
            ptr = ptr.next;
        }
        return sol.next;
    }

    public static void main(String[] args) {
        ListNode[] arr = new ListNode[3];
        ListNode node = new ListNode(0);
        ListNode ptr = add(node, 1);
        ptr = add(ptr, 4);
        ptr = add(ptr, 5);
        arr[0] = node.next;

        node = new ListNode(0);
        ptr = add(node, 1);
        ptr = add(ptr, 3);
        ptr = add(ptr, 4);
        arr[1] = node.next;

        node = new ListNode(0);
        ptr = add(node, 2);
        ptr = add(ptr, 6);
        arr[2] = node.next;

        new MergeKSortedListsDivideAndConquer().mergeKLists(arr);

    }

    private static ListNode add(ListNode ptr, int i) {
        ptr.next = new ListNode(i);
        return ptr.next;
    }
}
