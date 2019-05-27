import java.util.LinkedList;
import java.util.Queue;
public class MatrixGraph {
	  private boolean adjMatrix[][];
	  public int wheights[][];
      private boolean visited[];
      private int numVertices, visitedNum=0,edgeCounter=0;
      private int rank[];
      private boolean flag=true;
      private MostRankable mostRankable= new MostRankable();
      private LeastRankable leastRankable= new LeastRankable();
      
      private class LeastRankable{
    	  int vertice, rank;
    	  LeastRankable(){
    		  rank=0;
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
      
      public MatrixGraph(int numVertices) {
          this.numVertices = numVertices;
          adjMatrix = new boolean[numVertices][numVertices];
          wheights=new int[numVertices][numVertices];
          visited= new boolean[numVertices];
          rank= new int[numVertices];
          leastRankable.rank=numVertices;
          for (int i=0;i<numVertices;i++)
        	  rank[i]=0;
      }
      
      
      public String setWeight(int w) {
    	  if (w==0)
    		  return("you can't give 0");
    	  else {
    		  StringBuilder s = new StringBuilder();
    		  for (int i = 0; i < numVertices; i++) {
    			  for (int j=i+1; j<numVertices;j++) {
    	                if (adjMatrix[i][j]==true) {
    	                	do
    	                		wheights[i][j]=(int)(Math.random()*w);
    	                	while (wheights[i][j]==0);
    	                	if (Math.random()<0.5)
    	                		wheights[i][j]=-wheights[i][j];
    	                	wheights[j][i]=wheights[i][j];
    	                 	                    	                	
    	                }
    	                else
    	                	wheights[i][j]=0;
    	            }
    		  }
    		  
    		  for (int i = 0; i < numVertices; i++) {
    	            s.append(i + ": ");
    	            for (int j=0; j<numVertices;j++) {
    	                s.append(wheights[i][j] + " ");
    	            }
    	            s.append("\n");
    		  }
    		  return s.toString();
    	  }
    		  
    	  
      }
      
       
    
  public void addEdge(int i, int j) {
                adjMatrix[i][j] = true;
                adjMatrix[j][i] = true;
                edgeCounter++;
                if (i!=j) {
                	rank[i]++;
                	rank[j]++;
                }
                if (rank[i]>mostRankable.rank) {
                	mostRankable.rank=rank[i];
                	mostRankable.vertice=i;
                }
                if (rank[j]>mostRankable.rank) {
                	mostRankable.rank=rank[j];
                	mostRankable.vertice=j;
                }
                                	
    }
 
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < numVertices; i++) {
            s.append(i + ": ");
            for (boolean j : adjMatrix[i]) {
                s.append((j?1:0) + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }
    
    public void bfs(int source){
        boolean[] visited = new boolean[adjMatrix.length];
        visited[source] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        System.out.println("\nThe breadth first order is");
        while(!queue.isEmpty()){
            System.out.println(queue.peek());
            int x = queue.poll();
            for(int i=0; i<adjMatrix.length;i++){
                if(adjMatrix[x][i] == true && visited[i] == false){
                    queue.add(i);
                    visited[i] = true;
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

            for (int j = 0; j < adjMatrix[source].length; j++) {
                if (adjMatrix[source][j] && !visited[j]) {   
                    dfs(j); // Visit node
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
        	for(int j=0;j<numVertices;j++){
            	if (i!=j&&adjMatrix[i][j]){
            	  	s.append(" -> "+j);
                	j++;
            	}
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
    	for (int i=0;i<numVertices;i++) {
    		if (rank[i]<leastRankable.rank) {
    			leastRankable.rank=rank[i];
    			leastRankable.vertice=i;
    		}
    	}
    	System.out.println("The least rankable verice is:"+leastRankable.vertice);
    	System.out.println("His rank is:"+ leastRankable.rank);
    	
    }

}