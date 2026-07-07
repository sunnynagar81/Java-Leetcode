class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        
        int INF = (int)1e9;
        //create matrix
        int[][] dist = new int[n][n];

        for(int i=0;i<n;i++){
            Arrays.fill(dist[i],INF);
            dist[i][i] = 0;
        }

        //fill the edges
        for(int[] edge : edges){

            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            dist[u][v] = w;
            dist[v][u] = w;
        }

        //floyd warshall
        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){

                    if(dist[i][k] == INF || dist[k][j] == INF){
                        continue;
                    }

                    dist[i][j] = Math.min(dist[i][j],dist[i][k] + dist[k][j]);
                }
            }
        }

        //count reachable cities

        int answer = -1;
        int minReachable = Integer.MAX_VALUE;

        for(int city=0;city<n;city++){
            int count=0;

            for(int neighbor=0;neighbor<n;neighbor++){
                if(city!=neighbor && dist[city][neighbor] <= distanceThreshold){

                    count++;
                }
            }

            //If tie,larger city index wins
            if(count <= minReachable){

                minReachable = count;
                answer = city;
            }
        }
        return answer;
    }

}