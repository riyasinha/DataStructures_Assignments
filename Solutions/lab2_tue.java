import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


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
public class lab2_tue {

	public static void main(String[] args) throws IOException{
		Reader.init(System.in);
		int max_len = Reader.nextInt();
		int cur_len = Reader.nextInt();
		int query = Reader.nextInt();
		int[] arr = new int[max_len];
		for(int i  = 0; i<cur_len; i++){
			int d = Reader.nextInt();
			arr[i] = d;
		}
		
		while(query-->0){
			int q  = Reader.nextInt();
			query--;
			if(q==1){
				int insert = Reader.nextInt();
				int cnt1=0;
				if(cur_len<max_len){
					int index = 0;
					for(int a = cur_len; a>0; a--){
						if(arr[a]>insert){
							continue;
						}
						else{
							index = a;
							break;
						}
					}
					for(int a1 = cur_len; a1>index; a1--){
						arr[a1] = arr[a1-1];
						cnt1++;
					}
					arr[index] = insert;
					cur_len++;
					System.out.println(cur_len + " "+ cnt1);	
					}
				else{
					System.out.println(cur_len + " 0");
				}	
			}
			if(q==2){
				int delete = Reader.nextInt();
				int cnt2 = 0;
				if(cur_len!=0){
					int index = 0;
					for(int b = cur_len; b>0; b--){
						if(arr[b]>delete){
							continue;
						}
						else{
							index = b;
							break;
						}
					}
					for(int b1 =index; b1<cur_len-1 ; b1++){
						arr[b1] = arr[b1+1];
						cnt2 = cnt2 +1;
					}
					arr[cur_len-1] = 0; 
					cur_len =cur_len-1;
					System.out.println(cur_len + " " + cnt2);
				}
				else{
					System.out.println("0 0");
				}
			}
			
			if(q==3){
				for ( int c= 0 ; c<cur_len ; c++){
					System.out.print(arr[c]+" ");
				}
				System.out.println();
			}
		}
	}
}
