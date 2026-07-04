class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        //create adjacency list
        List<List<int[]>> graph = new ArrayList<>();

        for(int i=0;i<n;i++) {
            graph.add(new ArrayList<>());
        }

        for(int[] flight : flights) {

            int u = flight[0];
            int v = flight[1];
            int price = flight[2];

            graph.get(u).add(new int[]{v,price});
        }

        //distance array
        int[] dist = new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);

        dist[src] = 0;

        //{stop,node,cost}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] -b[0]);

        pq.offer(new int[]{0,src,0});

        while(!pq.isEmpty()) {

            int[] current = pq.poll();

            int stops = current[0];
            int node = current[1];
            int cost = current[2];

            if(stops > k) {
                continue;
            }

            for(int[] neighbor : graph.get(node)){

                int nextNode = neighbor[0];
                int price = neighbor[1];

                int newCost = cost + price;

                if(newCost < dist[nextNode]){
                    dist[nextNode] = newCost;

                    pq.offer(new int[]{stops + 1,nextNode,newCost});
                }
            }
        }
        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];    
    }
}