import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static int[][] arr;
    public static int[] dr={-1,0,1,0}; //상좌하우
    public static int[] dc={0,-1,0,1};
    public static int cnt=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());

        st=new StringTokenizer(br.readLine());
        int R=Integer.parseInt(st.nextToken());
        int C=Integer.parseInt(st.nextToken());
        int D=Integer.parseInt(st.nextToken());
        
        arr=new int[N][M];
        for(int i=0;i<N;i++)
        {
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++)
            {
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        
        //D 입력이 상우하좌(북동남서)로 들어오므로, 상좌하우로 다시 매핑 필요
        switch(D)
        {
            case 0: D=0; break;
            case 1: D=3; break;
            case 2: D=2; break;
            case 3: D=1; break;
        }
        
        dfs(R,C,D,N,M);
        System.out.println(cnt);   
    }
    public static void dfs(int R, int C, int D, int N, int M)
    {
        //청소되지 않은 빈칸임
        if(arr[R][C]==0)
        {
            //청소
            arr[R][C]=2;
            cnt++;
        }

        //사방 중 빈칸이 있음
        if(BlankCHK(R,C,N,M))
        {
            int frontX=0;
            int frontY=0;
            
            do{
                D=D+1>3?0:D+1; //반시계 90도 회전
                frontX=R+dr[D];
                frontY=C+dc[D];
            }while(arr[frontX][frontY]!=0);
            
            dfs(frontX,frontY,D,N,M);
        }
        //사방 중 빈칸이 없음
        else
        {
            int back=D+2>3?(D+2)%4:D+2; //반대방향
            int backX=R+dr[back];
            int backY=C+dc[back];
            //System.out.println("D: "+D+" D%4: "+(D%4)+" back: "+back);

            //뒷칸이 1이 아니면 후진
            if(arr[backX][backY]!=1)
            {
                dfs(backX,backY,D,N,M);
            }
            //뒷칸이 1이면 종료
            else
            {
                return;
            }
        }
    }
    public static boolean BlankCHK(int R, int C, int N, int M)
    {
        if(arr[R+1][C]==0||arr[R-1][C]==0||arr[R][C+1]==0||arr[R][C-1]==0) 
        {
            return true;
        }
        return false;
    }
}
