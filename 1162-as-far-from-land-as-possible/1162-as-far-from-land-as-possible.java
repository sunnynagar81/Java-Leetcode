class Solution {
    public int maxDistance(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        Queue<int[]> q = new LinkedList<>();

        for(int r=0;r<rows;r++){
            for(int c=0;c<cols;c++){
                if(grid[r][c] == 1){
                    q.offer(new int[]{r,c});
                }
            }
        }

        if(q.isEmpty() || q.size() == rows*cols){
            return -1;
        }

        int[] dr = {-1,1,0,0};
        int[] dc = {0,0,-1,1};

        int distance = -1;

        while(!q.isEmpty()){
            int size = q.size();

            distance++;

            for(int i=0;i<size;i++){
                int[] curr = q.poll();

                int r=curr[0];
                int c=curr[1];

                for(int k=0;k<4;k++){

                    int nr = r+dr[k];
                    int nc = c+dc[k];

                    if(nr>=0 && nr<rows && nc>=0 && nc<cols && grid[nr][nc] ==0){
                        grid[nr][nc]=1;

                        q.offer(new int[]{nr,nc});
                    }
                }
            }
        }
        return distance;
    }
}