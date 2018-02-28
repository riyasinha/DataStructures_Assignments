import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
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
public class DirectionOfShips {
	public static void main(String[] args) throws IOException{
		Reader.init(System.in);
		int t = Reader.nextInt();
		
		for (int i =0; i <t; i++ ){
			String str1 = Reader.next();
			String str2 = Reader.next();
			int a = str1.length();
			int b = str2.length();
			int[] arr1 = new int[26];
			int[] arr2 = new int[26];
			
			for (int j = 0 ; j<a; j++ ){
				char char1 = str1.charAt(j);
				arr1[char1 - 97]++;}
			for (int k = 0;k<b; k++){
				char char2 = str2.charAt(k);
				arr2[char2 - 97]++;}
			int f  = 0;
			
			for (int l = 0 ; l<26 ; l++){
				if (arr1[l] != arr2[l]){
					f = f+1;		
			}
			}
			if(f!= 0){
				System.out.println("Yes");
			}
			else{
				System.out.println("No");
			}
			}
				
			}
			
			
		
	}


