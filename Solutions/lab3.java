import java.util.Scanner;
public class lab3 {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		long n = sc.nextLong();
		
		//long startTime_1 = System.currentTimeMillis();
		//System.out.println(Rfib(n));
		//long endTime_1 = System.currentTimeMillis();	
		//double TimeElapsed_1= (endTime_1-startTime_1)/1000.0; 
		//System.out.println(TimeElapsed_1);
		
		long startTime_2 = System.currentTimeMillis();
		System.out.println(Ifib(n));
		long endTime_2 = System.currentTimeMillis();
		double TimeElapsed_2= (endTime_2-startTime_2)/1000.0; 
		System.out.println(TimeElapsed_2);
		
		//long startTime_3 = System.currentTimeMillis();
		//System.out.println(CFib(n));
		//long endTime_3 = System.currentTimeMillis();
		//double TimeElapsed_3= (endTime_3-startTime_3)/1000.0; 
		//System.out.println(TimeElapsed_3);
		
	}
	public static long Rfib(long n){
		if(n == 0){
			return 0;
		}
		else if(n == 1){
			return 1;
			}
		else{
			return ((Rfib(n-1) + Rfib(n-2))%2014);			
		}
	}
	public static long Ifib(long n){
		long n1 = 0;
		long n2 = 1;
		long n3 = 1;
		for(long i = 0; i<n; i++){
			n1 =n2;
			n2 =n3;
			n3 = (n1+n2)%2014;
		}
		return n1;
	}
	public static long CFib(long n){
		long arr[][] = new long[][]{{1,1},{1,0}};
	    if (n == 0)
	        return 0;
	    expo(arr, n-1);
	      
	    return ((arr[0][0])%2014);
	}
	static void multiply(long arr[][], long arr1[][])
    {
    long a1 =  arr[0][0]*arr1[0][0] + arr[0][1]*arr1[1][0];
    long a2 =  arr[0][0]*arr1[0][1] + arr[0][1]*arr1[1][1];
    long a3 =  arr[1][0]*arr1[0][0] + arr[1][1]*arr1[1][0];
    long a4 =  arr[1][0]*arr1[0][1] + arr[1][1]*arr1[1][1];
     
    arr[0][0] = a1;
    arr[0][1] = a2;
    arr[1][0] = a3;
    arr[1][1] = a4;
    }
	static void expo(long arr[][], long n)
    {
    if( n == 0 || n == 1)
      return;
    long arr1[][] = new long[][]{{1,1},{1,0}};
      
    expo(arr, n/2);
    multiply(arr,arr);
      
    if (n%2 != 0)
       multiply(arr, arr1);
    }
	
}
