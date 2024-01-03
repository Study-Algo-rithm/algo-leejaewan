import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));        
        StringTokenizer st=new StringTokenizer(br.readLine(),"-");
        int ans=Integer.MAX_VALUE;
        
        while(st.hasMoreTokens())
        {
            int sum=0;
            StringTokenizer plus=new StringTokenizer(st.nextToken(),"+");
            while(plus.hasMoreTokens())
            {
                sum+=Integer.parseInt(plus.nextToken());
            }
            ans=(ans==Integer.MAX_VALUE)?sum:ans-sum;
        }
        System.out.println(ans);
    }
}
