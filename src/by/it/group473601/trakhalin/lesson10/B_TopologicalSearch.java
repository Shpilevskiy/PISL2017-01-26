package by.it.group473601.trakhalin.lesson10;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class B_TopologicalSearch {

    static void dfs(List<Integer>[] graph, boolean[] used, List<Integer> res, int u) {
	used[u] = true;
	for (int v : graph[u])
	    if (!used[v])
		dfs(graph, used, res, v);
	res.add(u);
    }

    public static List<Integer> topologicalSort(List<Integer>[] graph) {
	int n = graph.length;
	boolean[] used = new boolean[n];
	List<Integer> res = new ArrayList<>();
	for (int i = 0; i < n; i++) {
	    if (!used[i]) {
		dfs(graph, used, res, i);
	    }
	}
	Collections.reverse(res);
	return res;
    }

    public static List<Integer>[] readData(InputStream stream) {
  	Scanner scanner = new Scanner(stream);
  	int numberV = scanner.nextInt();
  	int numberW = scanner.nextInt();

  	List<Integer>[] graph =  new List[numberV];
  	for (int i = 0; i < numberV; i++) {
	    graph[i] = new ArrayList<>();
	}

  	for (int i = 0; i < numberW; i++) {
  	    int v = (int) scanner.next().charAt(0) - 65;
  	    int w = (int) scanner.next().charAt(0) - 65;

  	  graph[v].add(w);
          graph[w].add(v);

  	}
  	scanner.close();
  	return graph;
      }
    
    // Usage example
    public static void main(String[] args) throws FileNotFoundException {
	
	String root = System.getProperty("user.dir") + "/src/";
	InputStream stream = new FileInputStream(root + "by/it/group473601/trakhalin/lesson10/dataB.txt");

	List<Integer>[] graph = readData(stream);

	List<Integer> res = topologicalSort(graph);
	for(Integer i: res){
	    System.out.print((char)(int)(i+65)+", ");
	}
	//System.out.println(res);
    }

}
