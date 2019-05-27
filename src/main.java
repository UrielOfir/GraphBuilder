// uriel ofir 032552317
// angelina nuseib 311014096

import java.util.Scanner;
public class main {

	public static void main(String[] args) {
		Scanner scanner= new Scanner(System.in);
		boolean isMatrix = false;
		int vericiesNum;
		
		System.out.println("How many verticies do you want?");
		vericiesNum=scanner.nextInt();
		
		int x=0;
		while (1>x||x>2) {
				System.out.println("If you want list graph press 1, if you want matrix graph press 2:");
				x=scanner.nextInt();
				if(x==1)
					isMatrix=false;
				if(x==2)
					isMatrix=true;
		}
		
		if (isMatrix) {
			MatrixGraph matrixGraph=new MatrixGraph(vericiesNum);
			for (int i=0;i<vericiesNum;i++) {
				for(int j=i;j<vericiesNum;j++) {
					if (i!=j) {
						if (Math.random()<0.5) {
							matrixGraph.addEdge(i, j);
						}
					}
				}
			}
			System.out.println(matrixGraph.toString());
			System.out.println("Enter the weight range:");
			int w=scanner.nextInt();
			System.out.println(matrixGraph.setWeight(w));
			Graph MST=new Graph(matrixGraph.wheights);
			MST.primMST();
			matrixGraph.bfs(0);
			matrixGraph.dfs(0);
			System.out.println(matrixGraph.neighbors());
			System.out.println("there are: "+matrixGraph.getEdges()+" edges in the graph");
			matrixGraph.printMostRankable();
			matrixGraph.printLeastRankable();
		}
		else {	
			ListGraph listGraph= new ListGraph(vericiesNum);
			for (int i=0;i<vericiesNum;i++) {
				for(int j=i;j<vericiesNum;j++) {
					if (Math.random()<0.5) {
						listGraph.addEdge(i, j);
					}
				}
			}
			System.out.println(listGraph.toString());
			listGraph.bfs(0);
			listGraph.dfs(0);
			System.out.println(listGraph.neighbors());
			System.out.println("there are: "+listGraph.getEdges()+" edges in the graph");		
			listGraph.printMostRankable();
			listGraph.printLeastRankable();
			
		}
			
	}

}
