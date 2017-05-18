package by.it.group473602.prilepin.lesson10;

//Depth-first search

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class A_DFS {

    private static int counter = 1;

    public static void DFS(int node, int graph[][], int n, int preVisit[], int postVisit[], int color[],
	    int[][] edgeType) {

	if (color[node] == -1) {
	    return;
	}
	color[node] = 1;
	preVisit[node] = counter++;
	System.out.print((char) (node + 65) + ", ");
	for (int i = 0; i < n; i++) {
	    if (color[i] == 1 && i != node) {
		edgeType[node][i] = -1;
	    }
	    if (graph[node][i] == 1 && color[i] == 0) {
		edgeType[node][i] = 1;
		DFS(i, graph, n, preVisit, postVisit, color, edgeType);
	    }
	    color[node] = -1;
	}
	postVisit[node] = counter++;
    }

    public static int[][] readData(InputStream stream) {
	Scanner scanner = new Scanner(stream);
	int numberV = scanner.nextInt();
	int numberW = scanner.nextInt();

	int[][] graph = new int[numberV][numberV];

	for (int i = 0; i < numberW; i++) {
	    int v = (int) scanner.next().charAt(0) - 65;
	    int w = (int) scanner.next().charAt(0) - 65;

	    graph[v][w] = 1;

	}
	scanner.close();
	return graph;
    }

    public static void print(int[]preVisit, int[] postVisit, int [][]edgeType){
	System.out.println();
	for (int i = 0; i < preVisit.length; i++) {
	    System.out.print(preVisit[i] + ", ");
	}
	System.out.println();
	for (int i = 0; i < postVisit.length; i++) {
	    System.out.print(postVisit[i] + ", ");
	}
	System.out.println();
	for (int i = 0; i < edgeType.length; i++) {
	    for (int j = 0; j < edgeType[i].length; j++) {
		if (edgeType[i][j] == 1) {
		    System.out.println("[" + i + ", " + j + "] - древесное");
		}
		if (edgeType[i][j] == -1) {
		    System.out.println("[" + i + ", " + j + "] - обратное");
		}
	    }

	}
    }

    public static void main(String[] args) throws FileNotFoundException {

	String root = System.getProperty("user.dir") + "/src/";
	InputStream stream = new FileInputStream(root + "by/it/group473602/prilepin/lesson10/dataA.txt");

	int[][] graph = readData(stream);

	// 1 - tree edge
	// -1 - reverse edge
	int[][] edgeType = new int[graph.length][graph.length];

	int preVisit[] = new int[graph.length];
	int postVisit[] = new int[graph.length];
	// -1 black
	// 0 white
	// 1 gray
	int color[] = new int[graph.length];

	for (int i = 0; i < graph.length; i++) {
	    if (color[i] == 0) {
		DFS(i, graph, graph[i].length, preVisit, postVisit, color, edgeType);
	    }
	}
	print(preVisit, postVisit, edgeType);



    }
}