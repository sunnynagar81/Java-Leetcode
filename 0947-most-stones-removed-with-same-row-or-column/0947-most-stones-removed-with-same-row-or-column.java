class Solution {

    class DSU {

        int[] parent;
        int[] rank;

        DSU(int n){
            parent = new int[n];
            rank = new int[n];

            for(int i=0;i<n;i++){
                parent[i] = i;
                rank[i] = 0;
            }
        }

        int find(int x) {
            if(parent[x] == x)
              return x;

            return parent[x] = find(parent[x]);
        }

        void union(int u,int v){
            
            int pu = find(u);
            int pv = find(v);

            if(pu == pv)
               return;

            if(rank[pu] < rank[pv]){
                parent[pu] = pv;
            }
            else if(rank[pu] > rank[pv]){
                parent[pv] = pu;
            }
            else{
                parent[pv] = pu;
                rank[pu]++;
            }   
        }
    }
    public int removeStones(int[][] stones) {

        int maxRow = 0;
        int maxCol = 0;

        for(int[] stone : stones) {
            maxRow = Math.max(maxRow,stone[0]);
            maxCol = Math.max(maxCol,stone[1]);
        }

        DSU dsu = new DSU(maxRow + maxCol + 2);

        Set<Integer> usedNodes = new HashSet<>();

        for(int[] stone : stones) {
            int rowNode = stone[0];
            int colNode = stone[1] + maxRow + 1;

            dsu.union(rowNode,colNode);

            usedNodes.add(rowNode);
            usedNodes.add(colNode);
        }

        int components = 0;

        for(int node : usedNodes) {
            if(dsu.find(node) == node)
               components++;
        }
        return stones.length - components;  
    }
}