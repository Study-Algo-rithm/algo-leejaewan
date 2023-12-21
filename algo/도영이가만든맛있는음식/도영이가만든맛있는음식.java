import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static class MATERIAL
    {
        int sour;
        int bitter;
        MATERIAL(int sour, int bitter)
        {
            this.sour=sour;
            this.bitter=bitter;
        }
    }
    
    public static boolean visited[];
    public static int MIN=Integer.MAX_VALUE;
    public static MATERIAL[] material;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine());
        visited=new boolean[N];
        material=new MATERIAL[N];
        for(int i=0;i<N;i++)
        {
            StringTokenizer st=new StringTokenizer(br.readLine());
            int sour=Integer.parseInt(st.nextToken());
            int bitter=Integer.parseInt(st.nextToken());
            material[i]=new MATERIAL(sour, bitter);
        }

        for(int i=1;i<=N;i++)
            comb(i,N,1,0,0,0);
        
        System.out.println(MIN);
    }
    public static void comb(int r,int n, int sourMulti, int bitterSum,int cnt,int start)
    {
        if(cnt==r)
        {
            MIN=Math.abs(sourMulti-bitterSum)<MIN?Math.abs(sourMulti-bitterSum):MIN;
            return;
        }
        for(int i=start;i<n;i++)
                comb(r,n,sourMulti*material[i].sour,bitterSum+material[i].bitter,cnt+1,i+1);
    }
}
