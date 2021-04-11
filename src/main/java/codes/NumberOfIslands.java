package codes;

import java.util.*;

//geeks for geeks
//this is an unoptimized solution. An efficient solution is to use
public class NumberOfIslands {

    private static class Key {
        int i;
        int j;

        public Key(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return i == key.i && j == key.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }
    }

    Map<Key, Set<Key>> map;

    public int numIslandsUnoptimized(char[][] grid) {
        map = new HashMap<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    initiate(new Key(i, j));
                    checkAndMerge(grid, i, j, i + 1, j);
                    checkAndMerge(grid, i, j, i + 1, j + 1);
                    checkAndMerge(grid, i, j, i + 1, j - 1);
                    checkAndMerge(grid, i, j, i, j + 1);
                    checkAndMerge(grid, i, j, i, j - 1);
                    checkAndMerge(grid, i, j, i - 1, j);
                    checkAndMerge(grid, i, j, i - 1, j + 1);
                    checkAndMerge(grid, i, j, i - 1, j - 1);
                }
            }
        }

        int size = (int) map.values().stream().distinct().count();
        if (size == 1 && map.entrySet().iterator().next().getValue().size() == grid.length * grid[0].length) return 0;
        return size;
    }

    private void checkAndMerge(char[][] grid, int i, int j, int x, int y) {
        if (valid(grid, x, y)) {
            merge(grid, i, j, x, y);
        }
    }

    private void merge(char[][] grid, int i, int j, int x, int y) {
        Key pKey = new Key(i, j);
        Key cKey = new Key(x, y);
        initiate(pKey);
        initiate(cKey);

        Set<Key> pSet = map.get(pKey);
        Set<Key> cSet = map.get(cKey);
        if (pSet == cSet) return;

        pSet.addAll(cSet);
        for (Key key : cSet) {
            map.put(key, pSet);
        }
    }

    private void initiate(Key key) {
        map.computeIfAbsent(key, key1 -> {
            HashSet<Key> set = new HashSet<>();
            set.add(key1);
            return set;
        });
    }

    private boolean valid(char[][] grid, int i, int j) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[i].length && grid[i][j] == '1';
    }

    public static void main(String[] args) {
        NumberOfIslands islands = new NumberOfIslands();
        System.out.println(
                islands.numIslandsUnoptimized(new char[][]{{'1', '0', '1', '0', '0', '1', '1', '0', '1'}})
        );
    }

}
