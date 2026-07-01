class Solution {

    class DSU {

        int[] parent;
        int[] size;

        DSU(int n) {

            parent = new int[n];
            size = new int[n];

            for(int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int x) {

            if(parent[x] == x)
                return x;

            return parent[x] = find(parent[x]);
        }

        void union(int u, int v) {

            int pu = find(u);
            int pv = find(v);

            if(pu == pv)
                return;

            if(size[pu] < size[pv]) {

                parent[pu] = pv;
                size[pv] += size[pu];
            }
            else {

                parent[pv] = pu;
                size[pu] += size[pv];
            }
        }
    }

    public int largestIsland(int[][] grid) {

        int n = grid.length;

        DSU dsu = new DSU(n * n);

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        // Step 1: connect all lands
        for(int r = 0; r < n; r++) {

            for(int c = 0; c < n; c++) {

                if(grid[r][c] == 0)
                    continue;

                int node = r * n + c;

                for(int k = 0; k < 4; k++) {

                    int nr = r + dr[k];
                    int nc = c + dc[k];

                    if(nr >= 0 &&
                       nr < n &&
                       nc >= 0 &&
                       nc < n &&
                       grid[nr][nc] == 1) {

                        int adjNode = nr * n + nc;

                        dsu.union(node, adjNode);
                    }
                }
            }
        }

        int ans = 0;

        // Step 2: try every 0 -> 1
        for(int r = 0; r < n; r++) {

            for(int c = 0; c < n; c++) {

                if(grid[r][c] == 1)
                    continue;

                HashSet<Integer> set =
                    new HashSet<>();

                for(int k = 0; k < 4; k++) {

                    int nr = r + dr[k];
                    int nc = c + dc[k];

                    if(nr >= 0 &&
                       nr < n &&
                       nc >= 0 &&
                       nc < n &&
                       grid[nr][nc] == 1) {

                        int root =
                            dsu.find(nr * n + nc);

                        set.add(root);
                    }
                }

                int sizeTotal = 1;

                for(int root : set) {

                    sizeTotal +=
                        dsu.size[root];
                }

                ans = Math.max(ans,
                               sizeTotal);
            }
        }

        // Step 3: all land case
        for(int i = 0; i < n * n; i++) {

            ans = Math.max(
                    ans,
                    dsu.size[dsu.find(i)]
                  );
        }

        return ans;
    }
}