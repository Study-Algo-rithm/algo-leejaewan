import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static int[][] arr;
    public static boolean[][] visited;
    public static int dr[]={-1,1,0,0}; //상하좌우
    public static int dc[]={0,0,-1,1};
    public static int min=Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine());
        arr=new int[N][N];
        int edge=2;
        
        StringTokenizer st;
        for(int i=0;i<N;i++)
        {
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++)
               arr[i][j]=Integer.parseInt(st.nextToken());     
        }
        
        //가장자리 탐색 bfs
        visited=new boolean[N][N];
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                if(arr[i][j]==1&&!visited[i][j])
                {
                    bfs(i,j,N,edge);
                    edge++;
                }
            }
        }
        
        //최소거리 탐색 bfs
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                if(arr[i][j]>1&&arr[i][j]<edge-1)
                {
                    visited=new boolean[N][N];
                    bfs2(i,j,N,arr[i][j]);
                }
            }
        }
        
        System.out.println(min);
        
    }
    //가장자리 탐색 bfs
    public static void bfs(int x,int y,int N,int edge)
    {
        Queue<Integer> que=new LinkedList<>();
        que.add(x);
        que.add(y);
        visited[x][y]=true;

        while(!que.isEmpty())
        {
            int x2=que.remove();
            int y2=que.remove();

            for(int i=0;i<4;i++)
            {
                int newX=x2+dr[i]; 
                int newY=y2+dc[i];

                if(newX>=0&&newX<N&&newY>=0&&newY<N&&!visited[newX][newY])
                {
                    if(arr[newX][newY]==0)
                    {
                        arr[x2][y2]=edge;
                    }
                    else
                    {
                        que.add(newX);
                        que.add(newY);
                        visited[newX][newY]=true;
                    }
                }
            }
        }
    }
    //최소거리 탐색 bfs
    public static void bfs2(int x,int y,int N,int edge)
    {
        int cnt=0;
        Queue<Integer> que=new LinkedList<>();
        que.add(x);
        que.add(y);
        visited[x][y]=true;

        while(!que.isEmpty())
        {
            if(cnt>=min||min==1)
                break;
            int size=que.size();
            while(size>0)
            {
                int x2=que.remove();   
                int y2=que.remove();
                size-=2;
    
                for(int i=0;i<4;i++)
                {
                    int newX=x2+dr[i]; 
                    int newY=y2+dc[i];
    
                    if(newX>=0&&newX<N&&newY>=0&&newY<N&&!visited[newX][newY])
                    {
                        if(arr[newX][newY]==0)
                        {
                            que.add(newX);
                            que.add(newY);
                            visited[newX][newY]=true;
                        }
                        else if(arr[newX][newY]!=1&&arr[newX][newY]!=edge)
                        {
                            min=Math.min(cnt,min);
                            return;
                        }
                    }
                }
            }
            cnt++;
        }
        
    }
}
