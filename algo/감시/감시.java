import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static class CCTV
    {
        int x,y,num;
        CCTV(int x, int y, int num)
        {
            this.x=x;
            this.y=y;
            this.num=num;
        }
    }
    public static int[] dr={-1,0,1,0}; //상우하좌
    public static int[] dc={0,1,0,-1};
    public static int[][] arr;
    public static int ans=Integer.MAX_VALUE;
    public static CCTV[] cctvs=new CCTV[8];
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());

        arr=new int[N][M];
        
        int cctv_num=0;
        for(int i=0;i<N;i++)
        {
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++)
            {
                arr[i][j]=Integer.parseInt(st.nextToken());
                if(arr[i][j]>0&&arr[i][j]<6)
                {
                    cctvs[cctv_num]=new CCTV(i,j,arr[i][j]);
                    cctv_num++;
                }
            }
        }
        cctvs=Arrays.copyOf(cctvs,cctv_num);
        dfs(0);
        System.out.println(ans);
    }

    public static void dfs(int cctv_num)
    {
        if(cctv_num>=cctvs.length)
        {
            ans=Math.min(ans,getEmptyArea());
            return;
        }    
        
        CCTV cctv=cctvs[cctv_num];
        int x=cctv.x;
        int y=cctv.y;
        int num=cctv.num;

        switch(num)
        {
            case 1:
                for(int i=0;i<4;i++)
                {
                    draw(x,y,i);
                    dfs(cctv_num+1);
                    erase(x,y,i);
                }
                break;
            case 2:
                for(int i=0;i<2;i++)
                {
                    int oppositeDIR=i+2>=4?(i+2)%4:i+2;
                    
                    draw(x,y,i);
                    draw(x,y,oppositeDIR);
                    dfs(cctv_num+1);
                    erase(x,y,i);
                    erase(x,y,oppositeDIR);
                }
                break;
            case 3:
                for(int i=0;i<4;i++)
                {
                    int nextDIR=i+1>=4?(i+1)%4:i+1;
                    
                    draw(x,y,i);
                    draw(x,y,nextDIR);
                    dfs(cctv_num+1);
                    erase(x,y,i);
                    erase(x,y,nextDIR);
                }
                break;
            case 4:
                for(int i=0;i<4;i++)
                {
                    int leftDIR=i-1<0?3:i-1;
                    int rightDIR=i+1>=4?(i+1)%4:i+1;
                    
                    draw(x,y,i);
                    draw(x,y,leftDIR);
                    draw(x,y,rightDIR);
                    dfs(cctv_num+1);
                    erase(x,y,i);
                    erase(x,y,leftDIR);
                    erase(x,y,rightDIR);
                }
                break;
            case 5:
                for(int i=0;i<2;i++)
                {
                    int oppositeDIR=i+2>=4?(i+2)%4:i+2;
                    
                    draw(x,y,i);
                    draw(x,y,oppositeDIR);
                }
                dfs(cctv_num+1);
                for(int i=0;i<2;i++)
                {
                    int oppositeDIR=i+2>=4?(i+2)%4:i+2;
                    
                    erase(x,y,i);
                    erase(x,y,oppositeDIR);
                }
                break;
        }
    }
    public static boolean range(int x, int y)
    {
        if(x>=0&&x<arr.length&&y>=0&&y<arr[0].length&&arr[x][y]!=6)
            return true;
        return false;
    }
    public static void draw(int x, int y,int dir)
    {
        int nextX=x+dr[dir];
        int nextY=y+dc[dir];
        while(range(nextX,nextY))
        {
            arr[nextX][nextY]++;
            nextX=nextX+dr[dir];
            nextY=nextY+dc[dir];
        }
    }
    public static void erase(int x, int y,int dir)
    {
        int nextX=x+dr[dir];
        int nextY=y+dc[dir];
        while(range(nextX,nextY))
        {
            arr[nextX][nextY]--;
            nextX=nextX+dr[dir];
            nextY=nextY+dc[dir];
        }
    }
    public static int getEmptyArea()
    {
        int result=0;
        for(int i=0;i<arr.length;i++)
        {
            for(int j=0;j<arr[0].length;j++)
            {
                if(arr[i][j]==0)
                    result++;
            }
        }
        return result;
    }
}
