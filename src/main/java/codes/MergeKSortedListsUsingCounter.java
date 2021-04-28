package codes;

//this is not an optimal solution as it relies on the counter variables of each value.
//It will fail for values with very dynamic range.
class MergeKSortedListsUsingCounter {
    private static int OFFSET = 10000;

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
        int[] store = new int[2 * OFFSET + 2];
        ListNode node = new ListNode();
        ListNode start = node;
        for (ListNode list : lists) {
            while (list != null) {
                store[list.val + OFFSET]++;
                list = list.next;
            }
        }
        for (int i = 0; i < store.length; i++) {
            if (store[i] != 0) {
                for (int j = 0; j < store[i]; j++) {
                    node.next = new ListNode(i - OFFSET);
                    node = node.next;
                }
            }
        }
        return start.next;
    }
}
