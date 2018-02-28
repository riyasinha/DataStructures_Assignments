package lab6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;


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

class Stack{
	public String[] arr ;
	public int top;
	
	public Stack(int len){
		arr = new String[len];
		top = -1;
	}
	
	public String topElement(){
		String ch;
		if(top!=-1){
			ch = arr[top];
		}
		else{
			ch =  "nothing";
		}
		return ch;
	}
	
	public void push(String pushedEle){
		if(top<arr.length){
			top++;
			arr[top] = pushedEle;	
		}
	}
	
	public String pop(){
		if(top>=0){
			String ch1 = arr[top];
			top--;
			return ch1;
		}
		return "nothing";
	}
		
}

public class lab6_2 {
	public static void main(String[] args) throws IOException{
		Reader.init(System.in);
		int N = Reader.nextInt();
		String[] infixarr = new String[N];
		String[] postfixarr = new String[N];
		Stack S1 = new Stack(N);
		String t;
		int k = 0;
		int postfix_len  = 0;
		
		for(int i = 0 ; i<N ; i++){
			String a = Reader.next();
			infixarr[i] = a;
		}
		for (int j = 0 ; j < N ; j++ ) {
			if(infixarr[j].equals("(") == false && infixarr[j].equals(")") == false && infixarr[j].equals("+") == false && infixarr[j].equals("-") == false && infixarr[j].equals("*") == false && infixarr[j].equals("/") == false ){
				postfixarr[k++] = infixarr[j];
				postfix_len++;
				//System.out.println(postfixarr[k-1]+"dv");
			}
			
			else if(infixarr[j].equals("(") == true){
				S1.push(infixarr[j]);
			}
			
			else if(infixarr[j].equals(")")){
				while ((t = S1.pop()).equals("(")== false ){
					postfixarr[k++] = t;
					postfix_len++;
					//System.out.println(postfixarr[k-1]+"dhello");
					
				}
			}
			
			else if (prec(infixarr[j]) < prec(S1.topElement())){
				while (prec(infixarr[j])<prec(S1.topElement())){
					t = S1.pop();
					postfixarr[k++] = t;
					postfix_len++;
					//System.out.println(postfixarr[k-1]+"lol");
					
				}
				S1.push(infixarr[j]);
			}
			else if (prec(infixarr[j]) > prec(S1.topElement())){
				S1.push(infixarr[j]);	
			}
			
			else if (prec(infixarr[j]) == prec(S1.topElement())){
				S1.push(infixarr[j]);
			}
			
		}
		while((t= S1.pop()).equals("nothing") == false){
			postfixarr[k++] = t;
			postfix_len++;
			//System.out.println(postfixarr[k-1]+"yolo");
		}
		for (int l = 0; l<postfix_len; l++){
			System.out.print(postfixarr[l]+" ");
		}
	}
	
	public static int prec(String oper){
		switch(oper){
			case  "-" : return 1;
			case  "+" : return 1;
			case  "*" : return 2;
			case  "/" : return 2;
		}
		return 0;
	}
	
}
