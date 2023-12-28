import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static int cnt=0;
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int r=Integer.parseInt(st.nextToken());
        int c=Integer.parseInt(st.nextToken());

        dfs(N,r,c,0,0);
        
        System.out.println(cnt);
    }
    public static void dfs(int N,int r,int c,int firstX,int firstY)
    {
        int size=(int)Math.pow(2,N);
        int halfPoint=(int)Math.pow(2,N-1);
        int square=(int)Math.pow(halfPoint,2);
        int startX[]={firstX,firstX,firstX+halfPoint,firstX+halfPoint};
        int startY[]={firstY,firstY+halfPoint,firstY,firstY+halfPoint};
        int endX=0;
        int endY=0;

        if(size==2)
        {
            if(firstX==r&&firstY+1==c) cnt+=1;
            else if(firstX+1==r&&firstY==c) cnt+=2;
            else if(firstX+1==r&&firstY+1==c) cnt+=3;
            return;
        }
        for(int i=0;i<4;i++)
        {
            endX=startX[i]+halfPoint;
            endY=startY[i]+halfPoint;
            if(startX[i]<=r&&r<endX&&c>=startY[i]&&c<endY)
            {
                cnt=cnt+square*i;
                dfs(N-1,r,c,startX[i],startY[i]);
            }
        }
        
    }
}
