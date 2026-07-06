class Solution {
    public int countPaths(int n, int[][] roads) {

        int MOD = 1_000_000_007;

        List<List<int[]>> graph = new ArrayList<>();

        for(int i=0;i<n;i++){
            graph.add(new ArrayList<>());
        }

        for(int[] road : roads){
            
            int u = road[0];
            int v = road[1];
            int w = road[2];

            graph.get(u).add(new int[]{v,w});
            graph.get(v).add(new int[]{u,w});
        }

        long[] dist = new long[n];
        Arrays.fill(dist,Long.MAX_VALUE);

        long[] ways = new long[n];

        dist[0] = 0;
        ways[0] = 1;

        PriorityQueue<long[]> pq = new PriorityQueue<>((a,b) -> Long.compare(a[0],b[0]));

        pq.offer(new long[]{0,0});

        while(!pq.isEmpty()){

            long[] current = pq.poll();

            long distance = current[0];
            int node = (int) current[1];

            if(distance > dist[node]){
                continue;
            }

            for(int[] neighbor : graph.get(node)){
                 
                int next = neighbor[0];
                int weight = neighbor[1];

                long newDistance = distance + weight;

                if(newDistance < dist[next]){

                    dist[next] = newDistance;

                    ways[next] = ways[node];

                    pq.offer(new long[]{newDistance,next});
                }

                else if(newDistance == dist[next]){

                    ways[next] = (ways[next] + ways[node]) % MOD;
                } 
            }
        }

        return (int)ways[n-1];
        
    }
}