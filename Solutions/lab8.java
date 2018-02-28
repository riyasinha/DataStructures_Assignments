package lab8;

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

class Node{
	int data;
	Node left;
	Node right;
	
	public Node(){
		this.data=0;
		left = null;
		right = null;
	}
	
	public Node(int data){
		this.data = data;
		left = null;
		right = null;
	}
}


public class lab8 {
	
	public static int index = 0;
	public static void main(String[] args) throws IOException{
		Reader.init(System.in);
		int n = Reader.nextInt();
		
		int[] preorder = new int[n];
		for(int i = 0; i<n ;i++){
			preorder[i] = Reader.nextInt();
		}
		
		int[] inorder = new int[n];
		for(int j = 0; j<n; j++){
			inorder[j] = Reader.nextInt();
		}
		
		lab8 tree  = new lab8();
		Node x = tree.BT(inorder, preorder, 0, inorder.length-1);
		
		tree.postorder(x);
		System.out.println();
		
		
		
		boolean is_BST = is_sorted(inorder);
		
		if(is_BST==true){
			System.out.println("YES");
		}
		else{
			System.out.println("NO");
		}

	}
	
	public static boolean is_sorted(int[] arr){
		for(int i = 0; i<arr.length-1 ;i++){
			if(arr[i]>arr[i+1]){
				return false;
			}
		}
		return true;
	}
	public Node BT(int[] in , int pre[], int in1, int in2){
		if(in1>in2){
			return null;
		}
		Node root =  new Node(pre[index]);
		index++;
		
		if(in1==in2){
			return root;
		}
		
		int index2 = search(in, in1, in2, root.data);
		root.left = BT(in, pre, in1, index2-1);
		root.right = BT(in, pre, index2+1, in2);
		return root;
		
	}
	
	public int search(int[] arr, int start, int end, int data){
		for(int i =start; i<=end; i++){
			if(arr[i] == data){
				return i;
			}
		}
		return -1;
	}
	
	public void postorder(Node root){
		if(root!=null){
			postorder(root.left);
			postorder(root.right);
			System.out.print( root.data + " ");
		}
	}
	
	
	
}
