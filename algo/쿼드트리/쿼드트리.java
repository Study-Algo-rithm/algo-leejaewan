import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static char[][] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine());
        arr=new char[N][N];
        
        for(int i=0;i<N;i++)
        {
            String tmp=br.readLine();
            for(int j=0;j<N;j++)
                arr[i][j]=tmp.charAt(j);
        }
        
        System.out.println(dfs(0,0,N,N,N));
    }
    public static String dfs(int startX,int startY,int endX,int endY,int size)
    {
        if(size==1)
            return Character.toString(arr[startX][startY]); 
        
        int halfX=(startX+endX)/2;
        int halfY=(startY+endY)/2;
        int[] quarterX={startX,startX,halfX,halfX};
        int[] quarterY={startY,halfY,startY,halfY};
        int[] quarterEndX={halfX,halfX,endX,endX};
        int[] quarterEndY={halfY,endY,halfY,endY};

        String[] temp=new String[4];
        for(int i=0;i<4;i++)
            temp[i]=dfs(quarterX[i],quarterY[i],quarterEndX[i],quarterEndY[i],size/2);
        
        String str="("+temp[0]+temp[1]+temp[2]+temp[3]+")";
        
        if(same(temp))
            return temp[0];
        else
            return str;
    }
    public static boolean same(String[] temp)
    {
        for(int i=0;i<4;i++)
        {
            if(!temp[i].equals(temp[0])||temp[i].length()!=1)
                return false;
        }
        return true;
    }
}
