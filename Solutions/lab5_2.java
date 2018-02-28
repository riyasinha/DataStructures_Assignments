package lab5;
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
	public int merge(LinkedList L1, LinkedList L2){
		int count = 0;
		Node N1 = L1.start;
		Node N2 = L2.start;
		while(N1!=null && N2 != null){
			if(N1.getData()<=N2.getData()){
				this.insert(N1.getData());
				N1 = N1.getLink();
				count++;
			}
			else if(N2.getData()<N1.getData()){
				this.insert(N2.getData());
				N2 = N2.getLink();
				count++;
			}
		}
		if(N1==null && N2!=null){
			while(N2!=null){
				this.insert(N2.getData());
				N2 = N2.getLink();
			}
		}
		else if(N2==null && N1!=null){
			while(N1!=null){
				this.insert(N1.getData());
				N1 = N1.getLink();
			}
		}
		return count;
	}
}		
public class lab5_2 {

	public static void main(String[] args) throws IOException {
		Reader.init(System.in);
		int X = Reader.nextInt();
		int Y = Reader.nextInt();

		LinkedList L1 = new LinkedList();
		LinkedList L2 = new LinkedList();
		LinkedList L3 = new LinkedList();
		for(int i = 0 ; i <X; i++){
			int t = Reader.nextInt();
			L1.insert(t);	
		}
		for (int j = 0; j <Y; j++){
			int s = Reader.nextInt();
			L2.insert(s);	
		}
		int count ;
		count = L3.merge(L1,L2);
		Node N3 = L3.start;
		while(N3 != null){
			System.out.print(N3.getData()+" ");
			N3 = N3.getLink();
		}
		System.out.println();
		System.out.println(count);

	}

}
