import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        StringBuilder sb=new StringBuilder();
        int n=Integer.parseInt(st.nextToken());
        int k=Integer.parseInt(st.nextToken());
        boolean[] arr=new boolean[n];

        sb.append("<");
        int cnt=1;
        int result=0;
        while(result<n) {
            for (int i = 0; i < n; i++, cnt++) {
                if (arr[i]) {
                    cnt--;
                    continue;
                }
                if (cnt == k) {
                    if(result!=n-1)
                        sb.append((i + 1) + ", ");
                    else
                        sb.append(i + 1);
                    arr[i] = true;
                    cnt=0;
                    result++;
                }
            }
        }
        sb.append(">");
        System.out.println(sb);
    }
}
