import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static boolean visited[];
    public static int result[];
    public static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());

        for(int i=0;i<T;i++)
        {
            StringTokenizer st=new StringTokenizer(br.readLine());
            int n=Integer.parseInt(st.nextToken());
            int m=Integer.parseInt(st.nextToken());
            visited=new boolean[m];
            result=new int[n];
            answer=0;
            System.out.println(factorial(29,14));
            //System.out.println(comb(m,n));
        }
    }
    public static long comb(int n,int r)
    {
        if(n!=r)
        {
            System.out.println(factorial(n,1));
            System.out.println(factorial(n,n-(n-r)));
            System.out.println(factorial(r,1));
            System.out.println(factorial(n-r,1));
            return factorial(n,n-(n-r))/factorial(n-r,1);
        }
        else
            return 1;
    }
    public static long factorial(int num,int depth)
    {
        if(num==depth)
        {
            System.out.println(num);
            return num;
        }
        System.out.println(num);
        return num*factorial(num-1,depth);
    }

}
