import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int ans=Integer.MIN_VALUE;

        int N=Integer.parseInt(st.nextToken());
        int K=Integer.parseInt(st.nextToken());
        int[] arr=new int[N];

        st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++)
            arr[i]=Integer.parseInt(st.nextToken());

        int start=0;
        int end=K;
        int sum=0;

        for(int i=start;i<end;i++)
        {   
            sum+=arr[i];
            ans=sum;
        }
        
        while(end<N)
        {
            sum=sum-arr[start]+arr[end];
            ans=Math.max(ans,sum);
            start++;
            end++;
        }

        System.out.println(ans);
    }
    
}
