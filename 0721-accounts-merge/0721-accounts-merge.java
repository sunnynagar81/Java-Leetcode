class Solution {

    class DSU {
        int[] parent;
        int[] rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
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

    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        int n = accounts.size();

        DSU dsu = new DSU(n);

        //email -> account index
        Map<String,Integer> emailToAccount = new HashMap<>();

        //step 1 : union accounts having common emails
        for(int i=0;i<n;i++){
            List<String> account = accounts.get(i);

            for(int j=1;j<account.size();j++){
                String email = account.get(j);

                if(!emailToAccount.containsKey(email)){
                    emailToAccount.put(email,i);
                }
                else{
                    dsu.union(i,emailToAccount.get(email));
                }
            }
        }

        //setp 2 : root -> emails

        Map<Integer,TreeSet<String>> merged = new HashMap<>();

        for(String email : emailToAccount.keySet()){
            int accountIndex = emailToAccount.get(email);
            int root = dsu.find(accountIndex);
            merged.putIfAbsent(root,new TreeSet<>());
            merged.get(root).add(email);
        }

        //step 3 : prepare answer
        List<List<String>> ans = new ArrayList<>();

        for(int root : merged.keySet()){

            List<String> list = new ArrayList<>();

            list.add(accounts.get(root).get(0));

            list.addAll(merged.get(root));

            ans.add(list);
        }

        return ans;
        
    }
}