class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        //create adjacency list
        List<List<int[]>> graph = new ArrayList<>();

        for(int i=0;i<=n;i++){
            graph.add(new ArrayList<>());
        }

        for(int[] edge : times){

            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            graph.get(u).add(new int[]{v,w});
        }

        //distance aaray
        int[] dist = new int[n+1];

        Arrays.fill(dist,Integer.MAX_VALUE);

        dist[k] = 0;

        //create min heap
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)-> a[0] - b[0]);

        pq.offer(new int[]{0,k});

        //dijkstra algo apply
        while(!pq.isEmpty()){

            int[] current = pq.poll();

            int currentDistance = current[0];
            int node = current[1];

            if(currentDistance > dist[node]){
                continue;
            }

            for(int[] neighbor : graph.get(node)){

                int nextNode = neighbor[0];
                int weight = neighbor[1];

                if(currentDistance + weight < dist[nextNode]) {

                    dist[nextNode] = currentDistance + weight;

                    pq.offer(new int[]{dist[nextNode],nextNode});
                }
            }
        }

        //find answer
        int answer = 0;

        for(int i=1;i<=n;i++){
            if(dist[i] == Integer.MAX_VALUE){
                return -1;
            }
            answer = Math.max(answer,dist[i]);
        }

        return answer;
    }
}