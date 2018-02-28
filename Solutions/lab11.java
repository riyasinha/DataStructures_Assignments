package lab11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//Reader class for taking inputs.

//import lab10_1.Node;

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

class edge{
	int c1;
	int c2;
	int cost;
	
//	edge(int a, int b, int m){
//		c1 = a;
//		c2 = b;
//		cost = m;
//	}
	public boolean compare_edge(edge e){
		if(this.cost>e.cost){
			return true; 
		}
		return false;
	}
	
}

class subset{
	int parent;
	int rank;
}

class graph{
	int V,E;
	edge edge[];
	graph(int v,int e){
		V= v;
		E = e;
		edge = new edge[E];
		for(int i=0;i<e;i++){
			edge[i] = new edge();
		}
	}
	
	int find_subset(subset s[],int x){
		if(s[x].parent != x){
			s[x].parent = find_subset(s,s[x].parent);
		}
		return s[x].parent;
	}
	
	void union(subset s[],int x,int y){
		int a = find_subset(s,x);
		int b = find_subset(s,y);
		
		if(s[a].rank<s[b].rank){
			s[a].parent = b;
		}
		else if(s[a].rank>s[b].rank){
			s[b].parent = a;
		}
		else{
			s[b].parent =a;
			s[a].rank++;
		}
	}
	
	int mst(){
		edge ans[] = new edge[V+1];
		int x = 0;
		int sum =0;
		int y =0;
		for(y = 0; y<V+1;y++){
			ans[y] = new edge();
		}

		quickSort(this.edge,0,edge.length-1);
	
		subset s[] = new subset[V+1];
		for(y=0;y<V+1;y++){
			s[y] = new subset();
		}
		
		for(y=0; y<V+1;y++){
			s[y].parent = y;
			s[y].rank =0;
		}
		
		y=0;
		while(x<V-1){
			//y=y+1;
			edge n = new edge();
			n = edge[y++];
			//System.out.println(n.c1 + " " + n.c2);
			int a = find_subset(s,n.c1);
			int b = find_subset(s,n.c2);
			
			if(a!=b){
				ans[x++] = n;
				union(s,a,b);
			}
		}
		for(int i = 0;i<x;i++){
			sum =sum+ans[i].cost;
		}
		return sum;
	}

	  public static void quickSort(edge arr[], int low, int high) 
    {
        int i = low, j = high;
        edge temp;
        int pivot = arr[(low + high) / 2].cost;
 
        /** partition **/
        while (i <= j) 
        {
            while (arr[i].cost < pivot)
                i++;
            while (arr[j].cost > pivot)
                j--;
            if (i <= j) 
            {
                /** swap **/
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
 
                i++;
                j--;
            }
        }
 
        /** recursively sort lower half **/
        if (low < j)
            quickSort(arr, low, j);
        /** recursively sort upper half **/
        if (i < high)
            quickSort(arr, i, high);
    }
}

public class lab11 {
	public static void main(String args[]) throws IOException{
		Reader.init(System.in);
		int c = Reader.nextInt();
		int r = Reader.nextInt();
		
		graph g = new graph(c,r);
		
		for(int i =0; i<r;i++){
			g.edge[i].c1 = Reader.nextInt();
			g.edge[i].c2 = Reader.nextInt();
			g.edge[i].cost = Reader.nextInt();
		}
		int a = Reader.nextInt();
		int b = Reader.nextInt();
		int w = 0;
		for(int i =0; i<r;i++){
			if(g.edge[i].c1 == a && g.edge[i].c2 == b ){
				w = g.edge[i].cost;
				g.edge[i].cost =0;
				
			}
		}

		int x= g.mst();
		x = x+w;
		System.out.println(x);
	}
	
}
