package codes;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//asked in whatfix's first round
public class FriendsRelations {

    Map<Integer, Integer> parent;

    Map<Integer, Integer> size;

    public void printParent() {
        parent.forEach((r1, r2) -> {
            System.out.println("key -> " + r1 + ", val = " + r2);
        });
    }

    public FriendsRelations(List<List<Integer>> relations) {
        parent = new HashMap<>();
        size = new HashMap<>();
        for (List<Integer> relation : relations) {
            Integer r1 = relation.get(0);
            Integer r2 = relation.get(1);
            if (!find(r1, r2)) {
                union(r1, r2);
            }
        }

    }

    // amortized O(1).
    public boolean find(Integer r1, Integer r2) {
        return findParent(r1).equals(findParent(r2));
    }

    private Integer findParent(Integer r) {
        if (!parent.containsKey(r)) {
            parent.put(r, r);
        }
        Integer p = this.parent.get(r);
        if (!p.equals(r)) {
            parent.put(r, findParent(p));
        }
        return parent.get(r);
    }

    private void union(Integer r1, Integer r2) {
        Integer p1 = findParent(r1);
        Integer p2 = findParent(r2);
        size.putIfAbsent(p1, 1);
        size.putIfAbsent(p2, 1);
        Integer s1 = size.get(p1);
        Integer s2 = size.get(p2);
        if (s1 >= s2) {
            parent.put(p2, p1);
            size.put(p1, s1 + s2);
        } else {
            parent.put(p1, p2);
            size.put(p2, s1 + s2);
        }

    }

    public static void main(String[] args) {
        List<List<Integer>> rs = new LinkedList<>();
        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);
        rs.add(l);
        l = new LinkedList<>();
        l.add(4);
        l.add(2);
        rs.add(l);
        l = new LinkedList<>();
        l.add(5);
        l.add(17);
        rs.add(l);

        l = new LinkedList<>();
        l.add(8);
        l.add(2);
        rs.add(l);

        l = new LinkedList<>();
        l.add(11);
        l.add(5);
        rs.add(l);

        l = new LinkedList<>();
        l.add(2);
        l.add(5);
        rs.add(l);

        FriendsRelations relations = new FriendsRelations(rs);
        relations.printParent();
        System.out.println(
                relations.find(2, 17)
        );
        relations.printParent();
    }
}
