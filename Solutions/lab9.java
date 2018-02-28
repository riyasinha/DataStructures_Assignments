package lab9;

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

// from this class we create an object which will have two parameters - the gray code and the frequency of that gray code 
class Node{
	int gray_val; // gray code value of the object
	Node left; // node for the left child
	Node right; // node for the right child
	int char_freq; // frequency of occurrence of that particular gray value
	
	//constructor for a leaf node
	Node(int freq, int val){
		gray_val = val;
		char_freq = freq;
		left = null;
		right = null;
	}
	
	// constructor for a node with a left child and right child
	Node(int freq, int val, Node l, Node r){
		left = l;
		right = r;
		char_freq = freq;
		gray_val = val;
	}
	
	// method to get the gray code value of the node
	public int getValue(){
		return gray_val;
	}
	
	// method to set the gray code value
	public void setValue(int gray_val){
		this.gray_val = gray_val;
	}
	
	// method to set the right child of node
	public void setRight(Node right){
		this.right = right;
	}
	
	// method to get the right child of a node
	public Node getRight(){
		return right;
	}
	
	// method to set the left child of anode
	public void setLeft(Node left){
		this.left = left;
	}
	
	// method to get the left child of a node
	public Node getLeft(){
		return left;
	}
	
	// method to get the frequency parameter of  a node
	public int getChar_Freq(){
		return char_freq;
	}
	
	// method to set the frequency in a node
	public void setChar_Freq(int char_freq){
		this.char_freq = char_freq;
	}
	
}

// this class will create an object of the type of a min heap
class minHeap{
	Node[] Heap;// node array that will store all nodes
	int size; // current size of the heap array
	int maxsize; // maximum size of the heap array
	//int front  = 0;
	int ans = 0; // to calculate the height of a node from the root
	int sum = 0; // to calculate the total frequency
	
	// contructor for minheap
	public minHeap(int maxsize){
		this.maxsize = maxsize;
		this.size =0;
		Heap = new Node[this.maxsize+1];
	}
	
	// method to declare the parent node or the root node
	public int parent(int pos){
		return (pos-1)/2;
	}
	
	// method to declare the left child
	public int leftChild(int pos){
		return (2*pos)+1;
	}
	
	// method to declare the right child
	public int rightChild(int pos){
		return (2*pos)+2;
	}
	
	// method to know whether the node is a leaf or not
	public boolean isLeaf(int pos){
		if(pos>=(size/2) && pos<= size){
			return true;
		}
		return false;
	}
	
	// method to swap the elements if they do not satisfy the min heap property
	public void swap(int pos1, int pos2){
		Node tmp = Heap[pos1];
		Heap[pos1] = Heap[pos2];
		Heap[pos2] = tmp;		
	}
	
//	public void minHeapify(int pos){
//		if(!isLeaf(pos)){
//			while((2*pos+2)<size && (Heap[pos].char_freq > Heap[leftChild(pos)].char_freq || Heap[pos].char_freq > Heap[rightChild(pos)].char_freq)){
//				if(Heap[leftChild(pos)].char_freq <= Heap[rightChild(pos)].char_freq){
//					swap(pos, leftChild(pos));
//					minHeapify(leftChild(pos));
//				}
//				else{
//					swap(pos,rightChild(pos));
//					minHeapify(rightChild(pos));
//				}
//			}
//		}
//	}
	
	// method to insert the node in the Heap array
	public void insert(Node n){
		// first add the node to be inserted in the end of the array
		Heap[size] = n;
		// variable to know the position of the last node
		int current =size;
		// check whether the current node frequency is less than the root node frequency
		while(current>=0 && (Heap[current].char_freq<Heap[parent(current)].char_freq)){
			// if true, swap the nodes
			swap(current,parent(current));
			// make the current node the parent node
			current = parent(current);
		}
		size++;
	}
	
	/*public Node extract(){
		Node extracted = Heap[0];
		Heap[0] = Heap[size-1];
		minHeapify(front);
		return extracted;
	}*/
	
	// method to extract a node from the HEap array
	public Node extract(){
		Node extracted = Heap[0];
		Heap[0] = Heap[size-1];
		int a = 0;
		while(2*a+2<size && (Heap[a].char_freq>Heap[leftChild(a)].char_freq || Heap[a].char_freq > Heap[rightChild(a)].char_freq)){
			if(Heap[leftChild(a)].char_freq <= Heap[rightChild(a)].char_freq){
				swap(a,leftChild(a));
				a = a*2+1;
			}
			else{
				swap(a,rightChild(a));
				a = a*2+2;
			}
			
		}
		size--;
		return extracted;
	}
	
	// method to print the heap array
	public void print(){
		for(int i =0;i<size;i++){
			System.out.println(Heap[i].char_freq + " " + Heap[i].gray_val);
		}
	}
	
	// method to calculate the huffman compression frequency
	public void depth(Node root){
		ans = ans + 1;
		
		if(root.left == null && root.right == null){
			sum = sum + root.char_freq *(ans-1);
		}
		else{
			depth(root.right);
			ans = ans-1;
			depth(root.left);
			ans = ans-1;
		}
	}
}

public class lab9 {
	public static void main(String[] args) throws IOException{
		Reader.init(System.in);
		int x = Reader.nextInt(); // width of the pixels matrix
		int y = Reader.nextInt(); // length of the pixel matrix
		
		int total = x*y;
		int[] arr = new int[256];
		
		for(int i = 0; i<total; i++){
			int pixel = Reader.nextInt();
			arr[pixel] = arr[pixel] + 1;
		}
		
		minHeap a = new minHeap(total);
		a.print();
		
		Node node_a  = new Node(0,0);
		//int cnt_a = 0;
		
		for(int j = 0; j<256; j++){
			//System.out.println("la");
			if(arr[j]>0){
				//System.out.println("aa");
				node_a = new Node(arr[j],j);
				a.insert(node_a);
				//cnt_a++;
			}
		}
		//System.out.println(a.size);
		
		//a.print();
		
		Node a1 = new Node(0,-1);
		while(a.size> 1){
			Node a2 = a.extract();
			Node a3 = a.extract();
			a1 = new Node(a2.char_freq+a3.char_freq,-100,a2,a3);
			a.insert(a1);
		}
		
		//System.out.println(a.sum);
		
		if(a1.left!=null || a1.right!=null){
			a.depth(a1);
			//System.out.println(a.sum);
			System.out.println((double)Math.round((float)(x*y*8)/a.sum*10)/10 );
		}
		
		if(a.sum ==0){
			//System.out.println(node_a.char_freq);
			System.out.println((double)Math.round((float)(x*y*8)/node_a.char_freq*10)/10);
		}
		
		minHeap b = new minHeap(total);
		int cnt_b = 0;
		
		Node node_b = new Node(0,0);
		int m = 5;
		for(int k = 0; k<240; k=k+10){
			int l = 0;
			for(int n = k; n<k+10;n++){
				l = l + arr[n];
			}
			if(l!=0){
				node_b = new Node(l,m);
				b.insert(node_b);
			}
			m = m+10;
		}
		
		int z = 0;
		for(int u = 240;u<=255;u++){
			z = z+arr[u];
		}
		if(z!=0){
			node_b = new Node(z,245);
			b.insert(node_b);
		}
		//b.print();
		Node b1 = new Node(0,-1);
		while(b.size>1){
			Node b2 = b.extract();
			Node b3 = b.extract();
			b1 = new Node(b2.char_freq+b3.char_freq,-100,b2,b3);
			b.insert(b1);
		}
		
		//System.out.println(b.sum);
		if(b1.left!=null || b1.right!=null){
			b.depth(b1);
			//System.out.println(b.sum);
			System.out.println((double)Math.round((float)(x*y*8)/b.sum*10)/10);
		}
		if(b.sum==0){
			//System.out.println(node_b.char_freq);
			System.out.println((double)Math.round((float)(x*y*8)/node_b.char_freq*10)/10);
		}
	}
}

