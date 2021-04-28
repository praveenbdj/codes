package codes;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedListsPriorityQueue {

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
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(listNode -> listNode.val));
//        queue.addAll(Arrays.stream(lists).filter(Objects::nonNull).collect(Collectors.toList()));
        for (ListNode list : lists) {
            if (list != null) {
                queue.add(list);
            }
        }
        ListNode dummy = new ListNode(0);
        ListNode ptr = dummy;

        while (!queue.isEmpty()) {
            ListNode poll = queue.poll();
            ptr.next = poll;
            if (poll.next != null) {
                queue.add(poll.next);
            }
            ptr = ptr.next;
        }
        return dummy.next;
    }
}
