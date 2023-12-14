import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int[][] arr;
    public static int[][] tmp;
    public static boolean[][] visited;
    public static int dr[]={1,0,-1,0};//하우상좌
    public static int dc[]={0,1,0,-1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());
        int R=Integer.parseInt(st.nextToken());

        arr=new int[N][M];
        tmp=new int[N][M];

        //값 입력
        for(int i=0;i<N;i++)
        {
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++)
                arr[i][j]=Integer.parseInt(st.nextToken());
        }
        //R번 회전
        for(int i=0;i<R;i++)
        {
            visited=new boolean[N][M];
            dfs(0,0,0,N,M);
            for(int j=0;j<N;j++)
                arr[j]=tmp[j].clone();
        }
        //결과 출력
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<M;j++)
                System.out.print(arr[i][j]+" ");
            System.out.println();
        }
    }

    public static void dfs(int x, int y, int dir, int N, int M)
    {
        if(visited[x][y])
            return;

        int nextX=x+dr[dir];
        int nextY=y+dc[dir];

        //다음 위치가 범위 내
        if(range(nextX,nextY,N,M))
        {
            //아직 방문하지 않은 곳
            if(!visited[nextX][nextY])
            {
                visited[x][y]=true;       
                tmp[nextX][nextY]=arr[x][y];
                //다음 위치로 이동
                dfs(nextX,nextY,dir,N,M);
                return;
            }
            //이미 방문했음 && 한 바퀴 돌았음
            else if(visited[nextX][nextY]&&nextX==nextY&&dir==3)
            {
                visited[x][y]=true;
                tmp[nextX][nextY]=arr[x][y];
                //대각 위치로 이동
                dfs(nextX+1,nextY+1,0,N,M);
                return;
            }
        }

        //방향 전환
        dfs(x,y,dir+1,N,M);
        
    }
    public static boolean range(int nextX,int nextY,int N,int M)
    {
        if(nextX>=0&&nextX<N&&nextY>=0&&nextY<M)
            return true;
        else
            return false;
    }
    
}