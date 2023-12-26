import java.util.*;
import java.lang.*;
import java.io.*;
/*참고: https://velog.io/@jihun333/%EB%B0%B1%EC%A4%802477-%EC%B0%B8%EC%99%B8%EB%B0%AD-%EC%9E%90%EB%B0%94*/
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int widthArr[]=new int[6];
        int heightArr[]=new int[6];
        int K=Integer.parseInt(br.readLine());
        int widthMax=0;
        int heightMax=0;
        for(int i=0;i<6;i++)
        {
            StringTokenizer st=new StringTokenizer(br.readLine());
            int dir=Integer.parseInt(st.nextToken());
            int val=Integer.parseInt(st.nextToken());
            
            if(dir==1||dir==2)
            {
                widthArr[i]=val;
                widthMax=(widthArr[widthMax]<val)?i:widthMax;
            }
            else
            {
                heightArr[i]=val;
                heightMax=(heightArr[heightMax]<val)?i:heightMax;
            }
        }
        int leftX_IDX=rtnIDX(widthMax,-1);
        int rightX_IDX=rtnIDX(widthMax,1);
        int delX=Math.abs(heightArr[leftX_IDX]-heightArr[rightX_IDX]);

        int leftY_IDX=rtnIDX(heightMax,-1);
        int rightY_IDX=rtnIDX(heightMax,1);
        int delY=Math.abs(widthArr[leftY_IDX]-widthArr[rightY_IDX]);

        int ans=(widthArr[widthMax]*heightArr[heightMax]-(delX*delY))*K;
        System.out.println(ans);
    }
    public static int rtnIDX(int idx, int num)
    {
        if(idx+num>=0&&idx+num<6)
            return idx+num;
        else if(idx+num>=6)
            return 0;
        else if(idx+num<0)
            return 5;
        else
            return -999;
    }
}
