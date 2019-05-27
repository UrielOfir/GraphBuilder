import java.util.*;

public class ListGraph {
    private int numVertices;
    private LinkedList<Integer> adjLists[];
    private int edgeCounter=0,visitedNum=0;
    private int rank[];
    private MostRankable mostRankable= new MostRankable();
    private LeastRankable leastRankable= new LeastRankable();
    private boolean flag=true;
    private boolean visited[];
    
    private class LeastRankable{
  	  int vertice, rank;
  	  LeastRankable(){
  		  rank=numVertices;
  		  vertice=0;
  	  }
    }
    
    private class MostRankable{
  	  int vertice, rank;
  	  MostRankable(){
  		  rank=0;
  		  vertice=0;
  	  }
    }
    
	ListGraph(int vertices)
    {
        numVertices = vertices;
        adjLists = new LinkedList[vertices];
        visited= new boolean[numVertices];
        
        for (int i = 0; i < vertices; i++) {
            adjLists[i] = new LinkedList<Integer>();
        	visited[i]=false;
        }
        rank= new int[numVertices];
        for (int i=0;i<numVertices;i++)
      	  rank[i]=0;
        
        leastRankable.rank=numVertices;
    }
 
    void addEdge(int src, int dest)
    {
        adjLists[src].add(dest);
        if (src!=dest)
        	adjLists[dest].add(src);
        edgeCounter++;
        if (src!=dest) {
	        rank[src]++;
	        rank[dest]++;
        }
        if (rank[src]>mostRankable.rank) {
        	mostRankable.rank=rank[src];
        	mostRankable.vertice=src;
        }
        if (rank[dest]>mostRankable.rank) {
        	mostRankable.rank=rank[dest];
        	mostRankable.vertice=dest;
        }
    }
    
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("The list output is:\n");
        for (int i = 0; i < numVertices; i++) {
        	s.append(i);
            int j=0;
          while (j<adjLists[i].size()) {
            	s.append(" -> "+adjLists[i].get(j));
            	j++;
          }
          s.append("\n");
        }
        return s.toString();
    }
    
    public void bfs(int source) {
    	boolean[] visited = new boolean[adjLists.length];
    	visited[source] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        System.out.println("\nThe breadth first order is");
        while(!queue.isEmpty()){
            System.out.println(queue.peek());
            int x = queue.poll();
            for(int j=0;j<adjLists[x].size();j++) {
	                if(!visited[adjLists[x].get(j)]){
	                    queue.add(adjLists[x].get(j));
	                    visited[adjLists[x].get(j)] = true;
	                	}
	            }
            
        }
    	
    	
    }
    
    public void dfs(int source) {
    	if (flag) {
    		System.out.println("\nThe dfs output is:");
    		flag=false;
    	}
    	if(!visited[source]){
        	visited[source] = true; // Mark node as "visited"
            System.out.println(source);
            visitedNum++;

            for (int j = 0; j < adjLists[source].size(); j++) {
                if (!visited[adjLists[source].get(j)]) {   
                    dfs(adjLists[source].get(j)); // Visit node
                }
            }
        }
        if (visitedNum==numVertices) {
        	flag=true;
        	visitedNum=0;
        }
    }
    
    public String neighbors() {
    	StringBuilder s = new StringBuilder();
    	s.append("\nThe neighbors of each vertice are:\n");
        for (int i = 0; i < numVertices; i++) {
        	s.append(i);
            int j=0;
          while (j<adjLists[i].size()) {
            	if (i!=adjLists[i].get(j)){
            	  	s.append(" -> "+adjLists[i].get(j));
            	}
            	j++;
          }
          s.append("\n");           
      }
        
        return s.toString();
    }
    
    public int getEdges() {
    	return edgeCounter;
    }
    
    public void printMostRankable() {
    	System.out.println("The most rankable verice is:"+mostRankable.vertice);
    	System.out.println("His rank is:"+ mostRankable.rank);
    	
    }
    
    public void printLeastRankable() {
    	for (int i=0;i<adjLists.length;i++) {
    		if (rank[i]<leastRankable.rank) {
    			leastRankable.vertice=i;
    			leastRankable.rank=rank[i];
    		}
    			
    	}
    	System.out.println("The least rankable verice is:"+leastRankable.vertice);
    	System.out.println("His rank is:"+ leastRankable.rank);
    	
    }
 
}