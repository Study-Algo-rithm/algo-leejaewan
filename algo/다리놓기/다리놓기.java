import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static int arr[][]=new int[30][30];
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());
        StringBuilder sb=new StringBuilder();

        for(int i=0;i<30;i++)
        {
            for(int j=0;j<30;j++)
            {
                if(i==j||j==0)
                    arr[i][j]=1;
            }
        }
        
        for(int t=0;t<T;t++)
        {
            StringTokenizer st=new StringTokenizer(br.readLine());
            int N=Integer.parseInt(st.nextToken());
            int M=Integer.parseInt(st.nextToken());
            sb.append(comb(M,N)).append("\n");
        }
        System.out.println(sb);
    }
    public static int comb(int M,int N)
    {
        if(arr[M][N]!=0)
            return arr[M][N];
        
        arr[M][N]= comb(M-1,N-1)+comb(M-1,N);
        return arr[M][N];
    }
}
