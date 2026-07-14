class Solution {
    int timer = 0;
    List<List<Integer>> bridges = new ArrayList<>();

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {

        //Build graph
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for(int i=0;i<n;i++)
            adj.add(new ArrayList<>());
            
        for(List<Integer> edge : connections){

            int u = edge.get(0);
            int v = edge.get(1);

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        boolean[] vis = new boolean[n];

        int[] tin = new int[n];
        int[] low = new int[n];

        //Graph may have multiple components
        for(int i=0;i<n;i++){
            if(!vis[i])
                dfs(i,-1,adj,vis,tin,low);
            }
            return bridges;
        }

        void dfs (int node,
                 int parent,
                 ArrayList<ArrayList<Integer>> adj,
                 boolean[] vis,
                 int[] tin,
                 int[] low){

            vis[node] = true;

            tin[node] = low[node] = timer++;

            for(int nei : adj.get(node)){
                if(nei == parent)
                  continue;

                if(!vis[nei]){
                    dfs(nei,node,adj,vis,tin,low);

                    low[node] = Math.min(low[node],low[nei]);

                    if(low[nei] > tin[node]){
                        bridges.add(Arrays.asList(node,nei));
                    }
                }
                else{
                    low[node] = Math.min(low[node],tin[nei]);
                }  
            }
        }
        
    }
