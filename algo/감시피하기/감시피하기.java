import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static int[][] arr;
    public static XY[] list;
    public static XY[] Tlist;
    public static boolean result;
    public static int[] dr={-1,1,0,0};
    public static int[] dc={0,0,-1,1};

    static class XY
    {
        int x;
        int y;

        XY(int x,int y)
        {
            this.x=x;
            this.y=y;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine());
        arr=new int[N][N];
        list=new XY[N*N];
        Tlist=new XY[9];

        int idx=0;
        int Tidx=0;
        StringTokenizer st;
        for(int i=0;i<N;i++)
        {
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++)
            {
                char ch=st.nextToken().charAt(0);
                arr[i][j]=ch;
                if(ch=='X')
                {
                    list[idx]=new XY(i,j);
                    idx++;
                }
                else if(ch=='T')
                {
                    Tlist[Tidx]=new XY(i,j);
                    Tidx++;
                }
            }
        }
        list= Arrays.copyOf(list,idx);
        Tlist= Arrays.copyOf(Tlist,Tidx);

        comb(idx,3,0,0);

        String answer=result?"YES":"NO";
        System.out.println(answer);
    }
    public static void comb(int n,int r,int depth,int start)
    {
        if(depth==r)
        {
            result=check();
            return;
        }
        for(int i=start;i<n;i++)
        {
            int x=list[i].x;
            int y=list[i].y;

            arr[x][y]='O';
            comb(n,r,depth+1,i+1);
            arr[x][y]='X';

            if(result)
                return;
        }
    }
    public static boolean check()
    {
        for(int i=0;i<Tlist.length;i++)
        {
            int Tx=Tlist[i].x;
            int Ty=Tlist[i].y;

            for(int j=0;j<4;j++)
            {
                int nextTx=Tx+dr[j];
                int nextTy=Ty+dc[j];

                while(nextTx>=0&&nextTx<arr.length&&nextTy>=0&&nextTy<arr[0].length)
                {
                    if(arr[nextTx][nextTy]=='O')
                    {
                        break;
                    }
                    else if(arr[nextTx][nextTy]=='S')
                    {
                        return false;
                    }
                    nextTx=nextTx+dr[j];
                    nextTy=nextTy+dc[j];
                }
            }
        }
        return true;
    }
}
