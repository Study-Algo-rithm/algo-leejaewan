import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int[] paperNum={0,5,5,5,5,5};
    public static int ans=Integer.MAX_VALUE;
    public static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        arr=new int[10][10];

        for(int i=0;i<10;i++)
        {
            StringTokenizer st=new StringTokenizer(br.readLine());
            for(int j=0;j<10;j++)
            {
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        dfs(0,0,0);
        System.out.println((ans==Integer.MAX_VALUE)?-1:ans);
    }
    public static void dfs(int x,int y,int cnt)
    {
        //10*10 종이 탐색완료
        if(x==9&&y>9)
        {
            //색종이를 붙이는 최소 횟수 반환
            ans=Math.min(cnt,ans);
            return;
        }

        // 현재 붙이는 색종이가 최소 횟수 이상임
        if(ans<=cnt)
        {
            return;
        }

        //오른쪽으로 탐색 끝
        if(y>9)
        {
            //아랫줄 제일 왼쪽 값으로 이동
            dfs(x+1,0,cnt);
            return;
        }

        //색종이를 붙일 수 있는 위치
        if(arr[x][y]==1){
            //큰 색종이부터 붙임
            for(int i=5;i>0;i--)
            {
                //현재 색종이가 남아있음 && 붙일 수 있음
                if(paperNum[i]>0&&IsAttach(x,y,i))
                {
                    Attach(x,y,i,i);
                    paperNum[i]--;
                    //색종이를 붙였으므로 cnt+1
                    dfs(x,y+1,cnt+1);
                    Attach(x,y,i,1);
                    paperNum[i]++;
                }
                //색종이를 붙일 수 있는 위치(arr[x][y]==1)지만,
                //남은 색종이로 붙일 수 없다면(paperNum[i]==0||!IsAttach(x,y,i))
                //다음 dfs로 이동하지 못하므로,
                //10*10 종이를 탐색하지 못하고 끝난다
                //즉 if(x==9&&y>9) 조건까지 도달못하고 현재 케이스는 종료

            }
        }
        //색종이를 붙일 수 없는 위치
        else{
            //색종이를 못 붙이므로 그대로 cnt
            dfs(x,y+1,cnt);
        }

    }
    public static boolean IsAttach(int x, int y, int size)
    {
        if(x+size> arr.length||y+size>arr[0].length)
            return false;

        for(int i=x;i<x+size;i++)
        {
            for(int j=y;j<y+size;j++)
            {
                if(arr[i][j]!=1)
                    return false;
            }
        }
        return true;
    }
    public static void Attach(int x,int y,int size, int scatch)
    {
        for(int i=x;i<x+size;i++)
        {
            for(int j=y;j<y+size;j++)
            {
                arr[i][j]=scatch;
            }
        }
    }
}