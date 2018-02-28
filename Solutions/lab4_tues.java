package lab8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import lab5.Node;

//Reader class for taking inputs.

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
    public String data;
    public Node link;
    
    Node()
    {
    data = "";
    link = null;
    }
    
    Node(String n)
    {
        data = n;
        link = null;
    }
    
    Node (String n, Node p)
    {
        data = n;
        link = p;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
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
		rear =null;
		start = null;
		size = 0;
	}
	public void insert(String val){
		Node nptr = new Node(val,null);
		if(start == null){
			start = nptr;
			rear = start;
		}
		else{
			rear.setLink(nptr);
			rear =nptr;
		}
		size++;
	}
	
	public void insert_at_p(String v, int p){
		Node nptr = new Node(v,null);
		Node ptr = start;
		p = p-1;
		for(int i =1 ; i<size; i++ ){
			if(i==p){
				Node tmp = ptr.getLink();
				ptr.setLink(nptr);
				nptr.setLink(tmp);
				break;
			}
			ptr=ptr.getLink();
		}
	}
	
	public void delete_at_p(int p){
		Node prev = start;
		Node cur = start.getLink();
		
		if(p ==1){
			start = start.getLink();
		}
		else{
			while(cur.getLink()!=null){
				for(int i =2; i<=p; i++){
					if(i==p){
						prev = cur;
						cur= cur.getLink();
						break;
					}
					prev = prev.getLink();
					cur = cur.getLink();
				}
			}
		}
	}
	
	public void display(){
		Node nptr = start;
		while(nptr!=null){
			System.out.print(nptr.getData() +" ");
			nptr = nptr.getLink();
		}
	}
	
	
	
}

public class lab4_tues{
	public static void main(String[] args) throws IOException{
		Reader.init(System.in);
		int M = Reader.nextInt();
		int Q = Reader.nextInt();
		LinkedList L = new LinkedList();
		
		for(int i = 0; i<M;i++){
			String s = Reader.next();
			L.insert(s);
		}
		
		for(int j = 0; j<Q;j++){
			if(Q==1){
				String v = Reader.next();
				int p  =Reader.nextInt();
				L.insert_at_p(v, p);
			}
			if(Q==2){
				int p = Reader.nextInt();
				L.delete_at_p(p);
			}
			
			if(Q==3){
				L.display();
				
			}
		}
		
		
		
	}
}