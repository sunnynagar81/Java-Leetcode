class Solution {
    public int minimumEffortPath(int[][] heights) {

        int rows = heights.length;
        int cols = heights[0].length;

        int[][] dist = new int[rows][cols];

        for(int i=0;i<rows;i++){
            Arrays.fill(dist[i],Integer.MAX_VALUE);
        }

        dist[0][0] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0]-b[0]);

        pq.offer(new int[]{0,0,0});

        int[] dr = {-1,1,0,0};
        int[] dc = {0,0,-1,1};

        while(!pq.isEmpty()){
            int[] current = pq.poll();

            int effort = current[0];
            int r = current[1];
            int c = current[2];

            if(effort > dist[r][c])
               continue;

            if(r == rows-1 && c == cols-1)
               return effort;

            for(int k=0;k<4;k++){

                int nr = r + dr[k];
                int nc = c + dc[k];

                if(nr < 0 || nr >= rows || nc < 0 || nc >= cols)
                   continue;

                int edge = Math.abs(heights[r][c] - heights[nr][nc]);

                int newEffort = Math.max(effort,edge);

                if(newEffort < dist[nr][nc]){
                   dist[nr][nc] = newEffort;

                   pq.offer(new int[]{newEffort,nr,nc});
                }      
            }      
        }
        return 0;
        
    }

}