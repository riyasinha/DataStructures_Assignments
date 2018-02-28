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
public class lab2_set1 {
	public static void main(String[] args)throws IOException {
		Reader.init(System.in);
		int max_len = Reader.nextInt();
		int cur_len = Reader.nextInt();
		int query = Reader.nextInt();
		int[] arr = new int[max_len];
		int m;
		int n;
		for (int i = 0; i<cur_len; i++){
			n = Reader.nextInt();
			arr[i] = n;
		}
		
		for (int j = 0; j< query ; j++){
			m = Reader.nextInt();
			if(m==1){
				int in_no = Reader.nextInt();
				int cnt1 = 0;
				int a1 = 0;
				if(cur_len<max_len){
					for(int a  = cur_len-1 ; a>=0 ; a-- ){	
						if(in_no>arr[a]){
							a1 = a+1;
							break;
						}
					}
					for(int a2 = cur_len ; a2>a1 ; a2--){
						arr[a2] = arr[a2-1];
						cnt1 = cnt1+1;
					}
					arr[a1] = in_no;
					cur_len = cur_len + 1;
					System.out.println(cur_len + " " + cnt1);
				}
				else{
					System.out.println(cur_len + " 0");
				}
			}
			
			if(m==2){
				int del_no = Reader.nextInt();
				int cnt2 = 0;
				int b1 = 0;
				if(cur_len!=0){
					for(int b = 0; b<cur_len; b++){
						if(del_no == arr[b]){
							b1 = b ;
							break;
						}
					}
					for(int b2 = b1 ; b2<cur_len -1 ; b1++ ){
						arr[b2] = arr[b2+1];
						cnt2 = cnt2+1;
					}
					arr[cur_len-1] = 0;
					cur_len = cur_len-1;
					System.out.println(cur_len + " "+ cnt2);
				}
				else{
					System.out.println("0 0");
				}
			}
			
			if(m==3){
				for(int c = 0 ; c<cur_len; c++){
					System.out.print(arr[c] + " ");
				}
				System.out.println();
			}
		}
		
	}
}
