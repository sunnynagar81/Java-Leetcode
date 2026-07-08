class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;

        if(grid[0][0] == 1 || grid[n-1][n-1] == 1){
            return -1;
        }

        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{0,0});

        grid[0][0] = 1;//visited

        int pathLength = 1;

        int[] dr = {-1,-1,-1,0,0,1,1,1};
        int[] dc = {-1,0,1,-1,1,-1,0,1};

        while(!q.isEmpty()){
            int size = q.size();

            for(int i=0;i<size;i++){
                int[] curr = q.poll();

                int r = curr[0];
                int c = curr[1];

                if(r == n-1 && c == n-1){
                    return pathLength;
                }

                for(int k=0;k<8;k++){
                    int nr = r+dr[k];
                    int nc = c+dc[k];

                    if(nr>=0 && nr<n && nc>=0 && nc<n && grid[nr][nc] == 0){
                        q.offer(new int[]{nr,nc});

                        grid[nr][nc] = 1;//mark visisted as 1

                    }
                }
            }
            pathLength++;
        }
        return -1;

    }
}