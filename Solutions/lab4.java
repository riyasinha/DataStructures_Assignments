package lab4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



/** Class for buffered reading int and double values */
class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    /** call this method to initialize reader for InputStream */
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
	public int size;
	
	public LinkedList(){
		rear = null;
		start = null;
		size=0;
	}
	
	public void push(int val){
		Node nptr = new Node(val,null);
		if(start == null){
			start = nptr;
		}
		else{
			nptr.setLink(start);
			start = nptr;
			
		}
		size++;
	}
	
	public void pop(){
		if(start==null){
			return;
		}
		else if (start==rear){
			start = null;
			rear=null;
		}
		else{
			Node ptr = start.getLink();
			start = ptr;
		}
	}
	
	public void display(){
		int[] display_arr = new int[50];
		int cnt = 0;
		Node tmp=start;
		while(tmp!=null){
			int d = tmp.getData();
			display_arr[cnt] = d;
			cnt++;
			tmp = tmp.getLink();
		}
		for(int i =0; i<cnt; i++){
			System.out.print(display_arr[i] + " ");
		}
	}
	
	public boolean sublist(LinkedList L2){
		Node L1_start = start;
		Node L2_start = L2.start;
		
		
		if(L2_start == null && L1_start==null){
			return true;
		}
		if(L2_start != null && L1_start == null){
			return false;
		}
		if(L2_start==null && L1_start!=null){
			return true;
		}
		
		while(L2_start!=null){
			while(L1_start!=null){
				if(L2_start==null){
					return false;
				}
				else if(L1_start.getData() == L2_start.getData()){
					L1_start = L1_start.getLink();
					L2_start = L2_start.getLink();
				}
				else{
					break;
				}
			}
			if (L1_start==null){
				return true;
			}
			L1_start = start;
			L2_start = (L2_start).getLink();
		}
		return false;
		
	}
	
	/*public void check_sublist(LinkedList L1){
		Node L1_start  = L1.start;
		int L1_size = L1.size;
					
		if(L1_size>size){	
			int cnt = 0;
			int j = 0;
			while(j<L1_size-size){
				if(start.getData()!=L1_start.getData()){
					j++;
					L1_start = L1_start.getLink();
				}
				else{
					start = start.getLink();
					cnt++;
					j=0;
					L1_size = L1_size-1;
				}
			}
			if(cnt == size){
				int result = 1;
				System.out.println(result);
			}
			else{
				int result = 0;
				System.out.println(result);
			}
		}
		else if(L1_size == size){
			if(start.getData()!= L1_start.getData()){
				int result = 0;
				System.out.println(result);
			}
			else{
				int cnt = 0;
				int j = 0;
				while(j<L1_size-1){
					if(start.getData()!=L1_start.getData()){
						j++;
						L1_start = L1_start.getLink();
					}
					else{
						start = start.getLink();
						cnt++;
						j=0;
						L1_size = L1_size-1;
					}
				}
				if(cnt == size){
					int result = 1;
					System.out.println(result);
				}
				else{
					int result = 0;
					System.out.println(result);
				}
			}
		}
		else if(size>L1_size){
			int result = 0;
			System.out.println(result);
		}
		
	}*/
	
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
public class lab4 {
	public static void main(String[] args) throws IOException{
		Reader.init(System.in);
		int M = Reader.nextInt();
		int Q = Reader.nextInt();
		
		LinkedList L1 = new LinkedList();
		/*int i = Reader.nextInt();
		Node in_L1 = new Node(i,null);
		L1.start = in_L1;*/
		//Node L1_start = L1.start;
		for (int j =0;j<M;j++){
			int data = Reader.nextInt();
			L1.insert(data);
			j++;
			
		}
		for(int a = 0; a<Q; a++){	
			int q = Reader.nextInt();
			if(q==1){
				int v = Reader.nextInt();
				L1.push(v);
			}
			else if(q==2){
				L1.pop();
			}
			else if(q==3){
				L1.display();
			}
			else if(q==4){
				int k = Reader.nextInt();
				LinkedList L2 = new LinkedList();
				//Node L2_start = L2.start;
				/*while(k-->0){
					int data2 = Reader.nextInt();
					Node nptr2= new Node(data2,null);
					(L2_start).setLink(nptr2);
					L2_start = nptr2;
				}*/
				for(int x = 0; x<k ; x++){
					int t = Reader.nextInt();
					L2.insert(t);
				}
				boolean ans = L1.sublist(L2);
				if(ans==true){
					System.out.println("1");
				}
				else{
					System.out.println("0");
				}
				
			}
		}
	}
}
