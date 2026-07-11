class Solution {

    class DSU {
        int[] parent;
        int[] rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        int find(int x) {
            if (parent[x] == x) {
                return x;
            }

            return parent[x] = find(parent[x]); // Path Compression
        }

        void union(int u, int v) {
            int pu = find(u);
            int pv = find(v);

            if (pu == pv)
                return;

            if (rank[pu] < rank[pv]) {
                parent[pu] = pv;
            } else if (rank[pv] < rank[pu]) {
                parent[pv] = pu;
            } else {
                parent[pv] = pu;
                rank[pu]++;
            }
        }
    }
    public boolean equationsPossible(String[] equations) {

        DSU dsu = new DSU(26);

        //pass 1 : merge all equal variables
        for(String eq : equations){
            if(eq.charAt(1) == '='){
                 
                int u = eq.charAt(0) - 'a';
                int v = eq.charAt(3) - 'a';

                dsu.union(u,v); 
            }
        }

        //pass 2 : check inequalities
        for(String eq : equations){
            if(eq.charAt(1) == '!'){

                int u = eq.charAt(0) - 'a';
                int v = eq.charAt(3) - 'a';

                if(dsu.find(u) == dsu.find(v))
                   return false;
            }
        }
        return true;
        
    }
}