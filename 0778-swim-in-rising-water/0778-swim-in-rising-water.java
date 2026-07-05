class Solution {
    public int swimInWater(int[][] grid) {

        int n = grid.length;

        int dist[][] = new int[n][n];

        for(int i=0;i<n;i++){
            Arrays.fill(dist[i],Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] - b[0]);

        dist[0][0] = grid[0][0];

        pq.offer(new int[]{grid[0][0],0,0}); //{time,row,col}

        int[] dr = {-1,1,0,0};
        int[] dc = {0,0,-1,1};

        while(!pq.isEmpty()){
            int[] current = pq.poll();

            int time = current[0];
            int row = current[1];
            int col = current[2];

            if(row == n-1 && col == n-1){
                return time;
            }

            if(time > dist[row][col]){
                continue;
            }

            for(int k=0;k<4;k++){

                int nr = row + dr[k];
                int nc = col + dc[k];

                if(nr<0 || nr>=n || nc<0 || nc>=n){
                    continue;
                }

                int newTime = Math.max(time,grid[nr][nc]);// main formula

                if(newTime < dist[nr][nc]){
                    dist[nr][nc] = newTime;

                    pq.offer(new int[]{newTime,nr,nc});
                }
            }
        }
        return -1;
        
    }
}