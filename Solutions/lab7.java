package lab7;


/* 
 * This is the Java Code for a program that allows grid search. 
 * The grid consists of 1s and 0s where 1 represents the path we can traverse and 0 represents the obstacles. 
 * Here, we find out the shortest possible route from one coordinate to another with obstacles in the the grid.
 * Firstly, it takes in the size of the NxN grid.
 * In the second line, it takes the coordinates in the form  xs ys xd yd ,where x represents column number and y represents row number and s represents source and d represents destination.
 * Next N lines consist of N inputs in each line in the form of 0s and 1s.
 * After that follows the code to find the shortest path.
 * 
 * The output is gives the coordinated traversed in the form of x y ,where x in the column number and y is the row number.
 * 
 * The data type I have used are Queues(implemented on the concept of linked lists where Nodes are used to initialise them), 2-D arrays and 1-d arrays.
 * 
 */


/*
 * EXAMPLE INPUTS AND OUTPUTS:
 * 
 * INPUT:
 * 
 * 4
 * 1 1 3 3
 * 1 0 1 1
 * 1 1 1 1
 * 1 0 0 1
 * 1 1 0 1
 * 
 * OUTPUT:
 * 
 * 1 1
 * 2 1
 * 3 1
 * 3 2
 * 3 3
 * 
 */
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

// Class Node is used to initialise the Queue in the form of linked lists where the values are stored as 1D array with two inputs which contain the coordinates of the position. 

class Node{
	int[] data ;
	Node link;
	Node(int[] d, Node l){
		data = d;
		link = l;
	}
	//constructor
	public Node getLink() {
        return link;
	}
	
    public void setLink(Node l) {
        link = l;
    }
    
    public int[] getData(){
    	return data;
    }
	
}

// Class queue to store the coordinates
class queue{
	
	public Node front;
	public Node rear;
	public int size;
	
	public queue(){
		rear = null;
		front = null;
		size = 0;
	}
	
	// to add elements to the end of the queue
	public  void enqueue(int[] val){
		Node nptr = new Node(val,null);
		if(rear==null){
			front = nptr;
		}
		else{
			rear.setLink(nptr);
		}
		rear = nptr;
		size++;
	}
	// to remove elements from the start of the queue
	public int[] dequeue(){
		int[] d= front.getData();
		front = front.getLink();
		if(front == null){
			rear = null;
		}
		size--;
		return d;                                                                           
	}
	
	//to check whether the queue is emepty or not
	public boolean isEmpty(){
		return rear==null;
	}
	
	// to get the queue size
	public int getSize(){
		return size;
	}
	
	// to display the queue elements with a single space seperating their corresponding values
	public  void display(){
		Node temp = front;
		if(front!=null){
			while(temp.link !=null){
				System.out.println(temp.data[0] + " " + temp.data[1]);
				temp = temp.link;
			}
		}
	}
}

// the main class
public class lab7 {
	public static void main(String[] args) throws IOException{
		Reader.init(System.in);
		// input to determine the size of the NxN grid
		int N = Reader.nextInt();
		
		// taking in the coordinates
		int xs = Reader.nextInt();
		int ys = Reader.nextInt();
		int xd = Reader.nextInt();
		int yd = Reader.nextInt();
		
		// forming the grid by a 2D array
		int[][] arr = new int[N][N];
		
		// initialising a queue to store coordinates to traverse the path
		queue Q = new queue();
		
		// storing the values in the 2D array arr
		for (int i = 0; i<N ; i++){
			for (int j = 0; j<N; j++){
				arr[i][j] = Reader.nextInt();
			}
		}
		
		// initialising another 2D array that stores the path distance
		// here we initialise the array with storing some random large number to determine the path
		// here, in the array 'path' we will change the value of those particular coordinates according to the distance from the source.
		int [][] path = new int[N][N];
		
		// we put in the valur 60000 for each element in the array path
		for (int k = 0; k<N ; k++){
			for (int l = 0 ; l<N ; l++){
				path[k][l] = 60000;
			}
		}
		/*for(int y = 0 ; y<N;y++){
			for(int z = 0; z<N;z++){
				System.out.print(path[y][z]+" ");
			}
			System.out.println();
		}*/
		
		// create a source 1D array that stores in the coordinates of the source position in the form of {ys,xs} where ys is the row position and xs is the column position.
		int[] source = new int[2];
		source[0] = ys;
		source[1] = xs;
		
		// create a source 1D array that stores in the coordinates of the destination position in the form of {ys,xs} where ys is the row position and xs is the column position.
		int[] dest = new int[2];
		dest[0] = yd;
		dest[1] = xd;
		
		// firstly, enqueue the source coordinates in the queue
		Q.enqueue(source);
		
		// initialise a variable 'count' that will help us determine the unit distance of a point from the source point.
		int count = 0;
		
		// let the source coordinate have a distance of 0
		// hence, we put the value 0 for the source coordinates in the 2D array 'path'
		// for example, if the source coordinate is {ys, xs} , then the {ys,xs} position in 'path' array will have the value zero
		path[ys][xs] = count;
		//count++;
		
		// now we run a while loop which has the condition that our queue should never be empty
		// in this loop, we check for elements above and below and left right of the source position in the grid array 'arr'
		// if there is no obstacle( i.e. no 0 as the value), we enqueue that coordinate in the queue.
		// then we assign that coordinate in the 'path' array the corresponding distance value. 
		while(Q.isEmpty()==false){
				//Q.display();
			
				// firstly dequeue the first element in the queue
				int[] arr2 = Q.dequeue();
				// store the coordinates in two integers
				int a = arr2[0];
				int b = arr2[1];
				
				
				//System.out.println(arr2[0]+" dequeue "+arr2[1]);
				//Q.display();
				/*for(int c = 0;c<2; c++){
					arr2[c] = 
				}
				System.out.println(arr2[0]);
				System.out.println(arr2[1]);
				count++;*/
				
				// check is it is the destination coordinate
				if(a==yd && b == xd){
					break;
				}
				
				// if not the check these conditions
				
				// if no obstacle is found, enqueue that coordinate
				if(a+1<N){
					if(arr[a+1][b] != 0){
						//System.out.println("hgcch");
						if(path[a+1][b] == 60000){
							//System.out.println("hgcch6000");
							int[] q_arr = new int[2];
							q_arr[0] = a+1;
							q_arr[1] = b;
							Q.enqueue(q_arr);
							//Q.display();
							path[a+1][b] = path[a][b]+1;
							//System.out.println(q_arr[0]+"enqueue "+q_arr[1]);
						}
					}
				}
				if(b+1<N){
					if(arr[a][b+1] != 0){
						//System.out.println("hgcch");
						if(path[a][b+1] == 60000){
							//System.out.println("hgcch6000");
							int[] q_arr = new int[2];
							q_arr[0] = a;
							q_arr[1] = b+1;	
							Q.enqueue(q_arr);
							//Q.display();
							path[a][b+1] = path[a][b]+1;
							//System.out.println(q_arr[0]+"enquque "+q_arr[1]);
						}
					}
				}
				if(a-1>=0){
					if(arr[a-1][b] != 0){
						//System.out.println("hgcch");
						if(path[a-1][b] == 60000){
							//System.out.println("hgcc6000h");
							int[] q_arr = new int[2];
							q_arr[0] = a-1;
							q_arr[1] = b;	
							Q.enqueue(q_arr);
							//Q.display();
							path[a-1][b] =path[a][b]+1;
							//System.out.println(q_arr[0]+" enqueue"+q_arr[1]);
						}
					}
				}
				if(b-1>=0){
					if(arr[a][b-1] != 0){
						//System.out.println("hgcch");
						if(path[a][b-1] == 60000){
							//System.out.println("hgcch60000");
							int[] q_arr = new int[2];
							q_arr[0] = a;
							q_arr[1] = b-1;	
							Q.enqueue(q_arr);
							//Q.display();
							path[a][b-1] =path[a][b]+1;
							//System.out.println(q_arr[0]+" enquque"+q_arr[1]);
						}
					}
				}
						
		}
		
		/*(for(int y = 0 ; y<N;y++){
			for(int z = 0; z<N;z++){
				System.out.print(path[y][z]+" ");
			}
			System.out.println();
		}*/
		
		// now, we have to find out the shortest path
		// we start by initialising a new 2D array called 'dist'
		// here we will traverse the path array in reverse order
		// i.e. we shall start from the destination element and trace back the shortest route to the source element
		int[][] dist = new int[N*2][2];
		int cnt=0;
		
		int a = yd;
		int b = xd;
		
		// initialise a path element called 'la' which will help us determine the next coordinate.
		int la = path[a][b]-1;
		dist[cnt] = dest;
		cnt++;
		
		// this loop is will break when 'la', 'a' and 'b' become lesser than zero and 'a' and 'b' should always remain lesser than 'N' 
		while(la>=0 &&  a<N && b<N && a>=0 && b>=0){
			
			//System.out.println(la);
			//System.out.println(a+  " " + b);
			
			if(a-1>=0 && path[a-1][b] == la ){
				int[] d_arr = new int[2];
				d_arr[0]= a-1;
				d_arr[1] = b;
				dist[cnt] = d_arr;
				/*System.out.println("first");
				for(int i = 0; i<=cnt;i++){
					System.out.println(dist[i][0]+" " + dist[i][1]);
				}*/
				cnt = cnt+1;
				a = a-1;
				la=la-1;	
			}
			else if(b-1>=0 && path[a][b-1] == la ){
				int[] d_arr = new int[2];
				d_arr[0]= a;
				d_arr[1] = b-1;
				dist[cnt] = d_arr;
				/*System.out.println("second");
				for(int i = 0; i<=cnt;i++){
					System.out.println(dist[i][0]+" " + dist[i][1]);
				}*/
				cnt = cnt+1;
				b = b-1;
				la=la-1;
			}
			else if(a+1<N && path[a+1][b] == la ){
				int[] d_arr = new int[2];
				d_arr[0]= a+1;
				d_arr[1] = b;
				dist[cnt] = d_arr;
				/*System.out.println("third");
				for(int i = 0; i<=cnt;i++){
					System.out.println(dist[i][0]+" " + dist[i][1]);
				}*/
				cnt = cnt+1;
				a = a+1;
				la=la-1;
			}
			else if(b+1<N && path[a][b+1] == la){
				int[] d_arr = new int[2];
				d_arr[0]= a;
				d_arr[1] = b+1;
				dist[cnt] = d_arr;
				/*System.out.println("fourth");
				for(int i = 0; i<=cnt;i++){
					System.out.println(dist[i][0]+" " + dist[i][1]);
				}*/
				cnt = cnt+1;
				b = b+1;
				la=la-1;
			}
			if(a==ys && b ==xs){
				//dist[cnt++] = source;
				break;
			}
		}
		
		
		// after finally putting all the coordinates in the 'dist' array, we have to print it
		// in the 'dist' array all the elements are stored in reverse order
		// hence we print from last to first to get the proper order of traversal
		for(int i = cnt-1; i>=0;i--){
			//System.out.println(cnt);
			System.out.println(dist[i][1] + " " + dist[i][0]);	
		}
	}
}