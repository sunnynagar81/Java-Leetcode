class Solution {

    class DisjointSet {

        int[] parent;
        int[] rank;

        DisjointSet(int n) {

            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int node) {

            if (parent[node] == node)
                return node;

            return parent[node] = find(parent[node]);
        }

        boolean union(int u, int v) {

            int pu = find(u);
            int pv = find(v);

            if (pu == pv)
                return false;

            if (rank[pu] < rank[pv]) {

                parent[pu] = pv;

            } else if (rank[pu] > rank[pv]) {

                parent[pv] = pu;

            } else {

                parent[pv] = pu;
                rank[pu]++;
            }

            return true;
        }
    }

    public int findCircleNum(int[][] isConnected) {

        int n = isConnected.length;

        DisjointSet ds = new DisjointSet(n);

        int provinces = n;

        for (int i = 0; i < n; i++) {

            for (int j = i + 1; j < n; j++) {

                if (isConnected[i][j] == 1) {

                    if (ds.union(i, j)) {

                        provinces--;
                    }
                }
            }
        }

        return provinces;
    }
}