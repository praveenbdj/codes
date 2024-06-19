package codes;


import helper.Helper;

import java.util.*;

public class PriorityQueueImpl<E> {

    private List<E> list;
    private Comparator<E> comparator;
    private Map<E, Integer> indexes;

    public PriorityQueueImpl(Comparator<E> comparator, int size) {
        this.comparator = comparator;
        list = new ArrayList<>(size);
        indexes = new HashMap<>(size);
    }

    public void add(E var) {
        list.add(var);
        add(list.size() - 1, var);
        heapifyUp(list.size() - 1);
    }

    public E peek() {
        return this.list.size() == 0 ? null : this.list.get(0);
    }

    public E pop() {
        if (this.list.size() == 0) return null;
        E first = this.list.get(0);
        E last = list.get(list.size() - 1);

        list.remove(list.size() - 1);//TODO complexity?
        indexes.remove(last);

        indexes.remove(first);

        add(0, last);
        heapifyDown(0);
        return first;
    }

    public void adjustIncrement(E var) {
        Integer i = indexes.get(var);
        heapifyDown(i);
    }

    private void heapifyDown(int i) {
        if (i < 0 || i >= list.size() / 2) return;
        int left = getLeft(i);
        int right = getRight(i);
        if (left < list.size() && comparator.compare(list.get(left), list.get(i)) < 0 ||
                right < list.size() && comparator.compare(list.get(right), list.get(i)) < 0) {
            if (right >= list.size() || comparator.compare(list.get(left), list.get(right)) < 0) {
                E ex = list.get(i);
                add(i, list.get(left));
                add(left, ex);
                heapifyDown(left);
            } else {
                E ex = list.get(i);
                add(i, list.get(right));
                add(right, ex);
                heapifyDown(right);
            }
        }

    }

    private void heapifyUp(int i) {
        E var = list.get(i);
        int parent = getParent(i);
        while (parent != -1) {
            E pEl = list.get(parent);
            if (this.comparator.compare(var, pEl) < 0) {
                add(parent, var);
                add(i, pEl);
                i = getParent(i);
                parent = getParent(i);
                continue;
            }
            break;
        }
    }

    public void printOut() {
        if (list.size() > 0) {
            System.out.println(Helper.serialize(list.toArray()));
        }
    }

    private void add(int i, E var) {
        list.set(i, var);
        indexes.put(var, i);
    }

    private int getParent(int i) {
        return i < 1 ? -1 : (i - 1) / 2;
    }

    private int getLeft(int i) {
        return 2 * i + 1;
    }

    private int getRight(int i) {
        return 2 * i + 2;
    }

    public static void main(String[] args) {
        PriorityQueueImpl<Integer> queue = new PriorityQueueImpl<>(Comparator.comparingInt(integer -> integer), 5);
        queue.add(3);
        queue.add(6);
        queue.add(4);
        queue.add(7);
        queue.add(0);
        queue.add(1);
        queue.add(2);
        queue.add(5);
        queue.printOut();
        queue.pop();
        queue.printOut();
        queue.pop();
        queue.printOut();
        queue.adjustIncrement(5);
        queue.adjustIncrement(2);
        queue.printOut();
    }

}
