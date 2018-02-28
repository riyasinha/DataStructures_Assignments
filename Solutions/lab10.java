package lab10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



//Reader class for taking inputs.

/** Class for buffered reading int and double values */
class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    /** call this method to initialise reader for InputStream */
    static void init(InputStream input) {
        reader = new BufferedReader(
                     new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }

    /** get next word */
    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                   reader.readLine() );
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }
	
    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }
}

//class Stack{
//	public int[] arr ;
//	public int top;
//	
//	public Stack(int len){
//		arr = new int[len];
//		top = -1;
//	}
//	
//	public int topElement(){
//		int ch;
//		if(top!=-1){
//			ch = arr[top];
//		}
//		else{
//			ch =  -1;
//		}
//		return ch;
//	}
//	
//	public void push(int pushedEle){
//		if(top<arr.length){
//			top++;
//			arr[top] = pushedEle;	
//		}
//	}
//	
//	public int pop(){
//		if(top>=0){
//			int ch1 = arr[top];
//			top--;
//			return ch1;
//		}
//		return -1;
//	}
//		
//}
class Node
{
    public int data;
    public Node link;
    
    Node()
    {
    data = 0;
    link = null;
    }
    
    Node(int n)
    {
        data = n;
        link = null;
    }
    
    Node (int n, Node p)
    {
        data = n;
        link = p;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getLink() {
        return link;
    }

    public void setLink(Node link) {
        this.link = link;
    }
}

class LinkedList{
	public Node rear;
	public Node start;
	public LinkedList(){
		rear = null;
		start = null;
	}
	public void insert(int val){
		Node nptr = new Node(val,null);
		if(start == null){
			start = nptr;
			rear = start;
		}
		else{
			rear.setLink(nptr);
			rear =nptr;
		}
	}
	
}
	

class Graph{
	int V;
	LinkedList[] adj_list;
	//Stack stack;
	int flag;
	
	public Graph(int v){
		V = v;
		adj_list = new LinkedList[v];
		//stack = new Stack(v+1);
		for(int e=0;e<v;e++){
			adj_list[e]=new LinkedList();
		}
		flag =0;
	}
	
	public void DFSUtil(int v, boolean[] visited){
		visited[v] = true;
		//System.out.print(v+" ");
		Node temp = adj_list[v].start;
		
		while(temp!=null){
			int n = temp.data ;
			if(!visited[n]){
				DFSUtil(n,visited);
			}
			
		else{
				flag =-1;
				break;
			}
			temp=temp.getLink();
		}
				
	}
	
	public void DFS(int v){
		boolean visited[] = new boolean[V];
		
		DFSUtil(v,visited);
	}
	
	
}
public class lab10 {
	public static void main(String args[]) throws IOException{
		Reader.init(System.in);
		
		int N = Reader.nextInt();
		int D = Reader.nextInt();
		
		Graph G = new Graph(N+1);
		
		for( int i = 1; i<=D; i++){
			int x = Reader.nextInt();
			int y = Reader.nextInt();
			G.adj_list[x].insert(y);
		}
		
		int[] k = new int[N+1];
		for(int j = 1; j<=N;j++){
			G.DFS(j);
			System.out.println();
			if(G.flag==-1){
				System.out.println("-1");
				
			}
			else{
				System.out.println("no deadlock");
			}
		}
		
	}
	
	
}
