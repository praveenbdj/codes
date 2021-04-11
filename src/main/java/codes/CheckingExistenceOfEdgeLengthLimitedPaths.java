package codes;

import java.util.Arrays;
import java.util.Comparator;

public class CheckingExistenceOfEdgeLengthLimitedPaths {

    int[] parent, size;

    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        boolean[] sol = new boolean[queries.length];
        Arrays.sort(edgeList, Comparator.comparingInt(ints -> ints[2]));
        Integer[] indexArr = new Integer[queries.length];
        for (int i = 0; i < indexArr.length; i++) {
            indexArr[i] = i;
        }
        Arrays.sort(indexArr, Comparator.comparingInt(index -> queries[index][2]));
        int i = 0;
        for (Integer index : indexArr) {
            int[] query = queries[index];
            while (i < edgeList.length && edgeList[i][2] < query[2]) {
                unite(edgeList[i][0], edgeList[i][1]);
                i++;
            }
            sol[index] = root(query[1]) == root(query[0]);
        }
        return sol;
    }

    private int root(int i) {
        if (i != parent[i]) {
            parent[i] = root(parent[i]);
        }
        return parent[i];
    }

    private void unite(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (size[i] >= size[j]) {
            parent[j] = i;
            size[i] += size[j];
        } else {
            parent[i] = j;
            size[j] += size[i];
        }
    }
}
