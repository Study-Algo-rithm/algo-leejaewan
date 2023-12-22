import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static class CHICKEN
    {
        int x,y;
        CHICKEN(int x, int y)
        {
            this.x=x;
            this.y=y;
        }
    }
    public static class HOUSE
    {
        int x,y;
        HOUSE(int x,int y)
        {
            this.x=x;
            this.y=y;
        }
    }
    public static int[][] arr;
    public static int[] result;
    public static CHICKEN[] chickens;
    public static HOUSE[] houses;
    public static int[][] len;
    public static int[] dr={-1,1,0,0};
    public static int[] dc={0,0,-1,1};
    public static boolean[][] visited;
    public static int[][] order;
    public static int min=Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());
        arr=new int[N][N];
        order=new int[N][N];
        
        result=new int[M];
        houses=new HOUSE[2*N];
        chickens=new CHICKEN[13];

        int Hsize=0;
        int Csize=0;
        for(int i=0;i<N;i++)
        {
            st= new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++)
            {
                arr[i][j]=Integer.parseInt(st.nextToken());
                if(arr[i][j]==1)
                {
                    houses[Hsize]=new HOUSE(i,j);
                    order[i][j]=Hsize;
                    Hsize++;
                }
                else if(arr[i][j]==2)
                {
                    chickens[Csize]=new CHICKEN(i,j);
                    order[i][j]=Csize;
                    Csize++;
                }
            }
        }
        houses=Arrays.copyOf(houses,Hsize);
        chickens=Arrays.copyOf(chickens,Csize);
        len=new int[chickens.length][houses.length];
        for(int i=0;i<houses.length;i++)
        {
            visited=new boolean[N][N];
            bfs(i);
        }
        
        comb(Csize,M,0,0);
        System.out.println(min);
    }
    public static void bfs(int idx)
    {
        Queue<Integer> que=new LinkedList<>();
        int x=houses[idx].x;
        int y=houses[idx].y;
        que.add(x);
        que.add(y);
        visited[x][y]=true;

        int finish=0;
        int depth=0;
        while(!que.isEmpty()&&finish<chickens.length)
        {
            int size=que.size();
            while(size>0)
            {
                int x2=que.remove();
                int y2=que.remove();
                if(arr[x2][y2]==2)
                {
                    len[order[x2][y2]][idx]=depth;
                    finish++;
                }
                
                for(int i=0;i<4;i++)
                {
                    int newX=x2+dr[i];
                    int newY=y2+dc[i];
                   if(newX>=0&&newX<arr.length&&newY>=0&&newY<arr[0].length&&!visited[newX][newY])
                   {
                       visited[newX][newY]=true;
                       que.add(newX);
                       que.add(newY);
                   }
                }
                size-=2;
            }
            depth++;
        }
    }
    public static void comb(int n, int r, int start, int cnt)
    {
        if(cnt==r)
        {
            int sum=0;
            for(int j=0;j<len[0].length;j++)
            {
                int tmp=Integer.MAX_VALUE;
                for(int i=0;i<result.length;i++)
                    tmp=Math.min(len[result[i]][j],tmp); 
                sum+=tmp;
            }
            min=Math.min(sum,min);
            return;
        }
        for(int i=start;i<n;i++)
        {
            result[cnt]=i;
            comb(n,r,i+1,cnt+1);
        }
    }
}
