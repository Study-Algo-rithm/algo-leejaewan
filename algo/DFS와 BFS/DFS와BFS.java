import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static ArrayList<Integer>[] arr;
    public static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());
        int V=Integer.parseInt(st.nextToken())-1;

        arr=new ArrayList[N];
        for(int i=0;i<N;i++)
            arr[i]=new ArrayList<Integer>();
        
        for(int i=0;i<M;i++)
        {
            st=new StringTokenizer(br.readLine());
            int point1=Integer.parseInt(st.nextToken())-1;
            int point2=Integer.parseInt(st.nextToken())-1;
            arr[point1].add(point2);
            arr[point2].add(point1);
        }

        for(int i=0;i<N;i++)
            Collections.sort(arr[i]);
        
        visited=new boolean[N];
        dfs(V);
        System.out.println();
        
        visited=new boolean[N];
        bfs(V);
        
    }
    public static void dfs(int node)
    {
        System.out.print((node+1)+" ");
        visited[node]=true;
        
        for(int i=0;i<arr[node].size();i++)
        {
            int nextNode=arr[node].get(i);
            
            if(!visited[nextNode])
                dfs(nextNode);
        }
    }
    public static void bfs(int start)
    {
        Queue<Integer> que=new LinkedList<>();
        que.add(start);
        visited[start]=true;
        
        while(!que.isEmpty())
        {
            int node=que.remove();
            System.out.print((node+1)+" ");
            
            for(int i=0;i<arr[node].size();i++)
            {
                int nextNode=arr[node].get(i);
                if(!visited[nextNode])
                {
                    visited[nextNode]=true;
                    que.add(nextNode);
                }
            }
        }
    }
}
