import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static int[][] arr;
    public static int[] dr={0,0,0,0};
    public static int[] dc={0,0,0,0};
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());
        int R=Integer.parseInt(st.nextToken());

        dr[1]=N/2;dr[3]=-(N/2);
        dc[0]=M/2;dc[2]=-(M/2);
        
        
        arr=new int[N][M];
        for(int i=0;i<N;i++)
        {
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++)
            {
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        st=new StringTokenizer(br.readLine());
        for(int i=0;i<R;i++)
        {
            int Rnum=Integer.parseInt(st.nextToken());
            rotateArr(arr.length,arr[0].length,Rnum);
        }

        Print();
        
    }
    
    public static void rotateArr(int N, int M, int Rnum)
    {
        int[][] tmp=new int[N][M];
        
        switch(Rnum)
        {
            case 1:
                tmp=UpDownChange(tmp,N,M);
                break;
            case 2:
                tmp=LeftRightChange(tmp,N,M);
                break;
            case 3:
                tmp=RotateRight90(tmp,N,M);
                break;
            case 4:
                tmp=RotateLeft90(tmp,N,M);
                break;
            case 5:
                tmp=PartitialOneToTwo(tmp,N,M);
                break;
            case 6:
                tmp=PartitialTwoToOne(tmp,N,M);
                break;
        }

        int row=tmp.length;
        int col=tmp[0].length;
        
        arr=new int[row][col];
        dr[1]=row/2;dr[3]=-(row/2);
        dc[0]=col/2;dc[2]=-(col/2);
        for(int j=0;j<row;j++)
            arr[j]=tmp[j].clone();
    }

    public static int[][] UpDownChange(int[][] tmp,int N,int M)
    {
        for(int i=0;i<N;i++)
        {
             for(int j=0;j<M;j++)
            {
                tmp[i][j]=arr[N-1-i][j];
            }
        }
        return tmp;
    }

    public static int[][] LeftRightChange(int[][] tmp,int N,int M)
    {
        for(int i=0;i<N;i++)
        {
             for(int j=0;j<M;j++)
            {
                tmp[i][j]=arr[i][M-1-j];
            }
        }
        return tmp;
    }

    public static int[][] RotateRight90(int[][] tmp,int N,int M)
    {
        tmp=new int[M][N];
        for(int j=0,k=0;j<M;j++,k++)
        {
            for(int i=N-1,t=0;i>=0;i--,t++)
            {
                tmp[k][t]=arr[i][j];
            }
        }
        return tmp;
    }

    public static int[][] RotateLeft90(int[][] tmp,int N,int M)
    {
        tmp=new int[M][N];
        for(int j=M-1,k=0;j>=0;j--,k++)
        {
            for(int i=0,t=0;i<N;i++,t++)
            {
                tmp[k][t]=arr[i][j];
            }
        }
        return tmp;
    }

    public static int[][] PartitialOneToTwo(int[][] tmp,int N,int M)
    {
        int N_half=N/2;
        int M_half=M/2;
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<M;j++)
            {
                //좌상 to 우상
                if(i>=0&&i<N_half&&j>=0&&j<M_half)
                    tmp[i+dr[0]][j+dc[0]]=arr[i][j];
                //우상 to 우하
                else if(i>=0&&i<N_half&&j>=M_half&&j<M)
                    tmp[i+dr[1]][j+dc[1]]=arr[i][j];
                //우하 to 좌하
                else if(i>=N_half&&i<N&&j>=M_half&&j<M)
                    tmp[i+dr[2]][j+dc[2]]=arr[i][j];
                //좌하 to 좌상
                else if(i>=N_half&&i<N&&j>=0&&j<M_half)
                    tmp[i+dr[3]][j+dc[3]]=arr[i][j];
            }
        }
        
        return tmp;
    }

    public static int[][] PartitialTwoToOne(int[][] tmp,int N,int M)
    {
        int N_half=N/2;
        int M_half=M/2;
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<M;j++)
            {
                //좌상 to 우하
                if(i>=0&&i<N_half&&j>=0&&j<M_half)
                    tmp[i+dr[1]][j+dc[1]]=arr[i][j];
                //우상 to 좌상
                else if(i>=0&&i<N_half&&j>=M_half&&j<M)
                    tmp[i+dr[2]][j+dc[2]]=arr[i][j];
                //우하 to 우상
                else if(i>=N_half&&i<N&&j>=M_half&&j<M)
                    tmp[i+dr[3]][j+dc[3]]=arr[i][j];
                //좌하 to 우하
                else if(i>=N_half&&i<N&&j>=0&&j<M_half)
                    tmp[i+dr[0]][j+dc[0]]=arr[i][j];
            }
        }
        return tmp;
    }

    public static void Print()
    {
        for(int i=0;i<arr.length;i++)
        {
            for(int j=0;j<arr[0].length;j++)
            {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }
}
