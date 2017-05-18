package by.it.group473601.trakhalin.lesson11;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class A_Dijkstra {
    
    private static int INF = Integer.MAX_VALUE / 2;
    
    private int numberVertex; 
    private int numberEdge;
    private ArrayList<Integer> graph[]; 
    private ArrayList<Integer> weight[]; 
    private boolean used[]; 
    private int distance[];

    private int parent[]; 
    int start; 
    


    private void dejkstra(int s) { 
        distance[s] = 0; 
        for (int iter = 0; iter < numberVertex; ++iter) {
            int v = -1;
            int distV = INF;
         
            for (int i = 0; i < numberVertex; ++i) {
                if (used[i]) {
                    continue;
                }
                if (distV < distance[i]) {
                    continue;
                }
                v = i;
                distV = distance[i];
            }
          
            for (int i = 0; i < graph[v].size(); ++i) {
                int u = graph[v].get(i);
                int weightU = weight[v].get(i);
                //relax vertex
                if (distance[v] + weightU < distance[u]) {
                    distance[u] = distance[v] + weightU;
                    parent[u] = v;
                }
            }
            used[v] = true;
        }
    }
    
    private void readData(InputStream stream) throws IOException {
	
	Scanner scanner = new Scanner(stream);
	        
        numberVertex = scanner.nextInt();
        numberEdge = scanner.nextInt(); 
        start = (int)scanner.next().charAt(0)-65;
        
        graph = new ArrayList[numberVertex]; 
        for (int i = 0; i < numberVertex; ++i) {
            graph[i] = new ArrayList<Integer>();
        }

        weight = new ArrayList[numberVertex];
        for (int i = 0; i < numberVertex; ++i) {
            weight[i] = new ArrayList<Integer>();
        }
        
        //read graph
        for (int i = 0; i < numberEdge; ++i) {
            int u = (int)scanner.next().charAt(0)-65;
            int v = (int)scanner.next().charAt(0)-65;
            int w = (int)scanner.next().charAt(0)-65;
          
            graph[u].add(v);
            weight[u].add(w);
        }
        
        scanner.close();
        
        used = new boolean[numberVertex];
        Arrays.fill(used, false);
        
        parent = new int[numberVertex];
        Arrays.fill(parent, -1);
        
        distance = new int[numberVertex];
        Arrays.fill(distance, INF);
        
    }
    void printWay(int v) {
   	if (v == -1) {
   	    return;
   	}
   	printWay(parent[v]);
   	System.out.print((v + 1) + " ");
       }

    
    private void printData() throws IOException {
  	for (int v = 0; v < numberVertex; ++v) {
  	    if (distance[v] != INF) {
  		System.out.print(distance[v] + " ");
  	    } else {
  		System.out.print("-1 ");
  	    }
  	}
  	for(int i=0;i<parent.length;i++){
  	    System.out.println(parent[i]);
  	}
  	System.out.println();
  	for (int v = 0; v < numberVertex; ++v) {
  	    System.out.print((v + 1) + ": ");
  	    if (distance[v] != INF) {
  		printWay(v);
  	    }
  	    System.out.println();
  	}

      }
    
    private void run() throws IOException {
	
	String root = System.getProperty("user.dir") + "/src/";
	InputStream stream = new FileInputStream(root + "by/it/group473601/trakhalin/lesson11/dataA.txt");
	
        readData(stream);
        dejkstra(start);
        printData();

    }
    
    public static void main(String[] args) throws IOException {
        A_Dijkstra solution = new A_Dijkstra();
        solution.run();
    }
}