package by.it.group473602.prilepin.lesson11;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class B_Bellman_Ford {

    private static int INF = Integer.MAX_VALUE / 2;

    private int numberVertex;
    private int numberEdge;
    private Edge[] edges;
    private boolean used[];
    private int distance[];

    private int parent[];
    int start;

    private void dejkstra(int s) {
	distance[s] = 0;
	for (int i = 0; i < numberVertex - 1; i++) {
	    for (int j = 0; j < numberEdge; j++) {
		int from = edges[j].from;
		int to = edges[j].to;
		int weight = edges[j].weight;

		// Ясно, что если вершина from на
		// текущем шаге работы алгоритма
		// находится бесконечно далеко от
		// 0-ой, то она не изменит ответ
		if (distance[from] == INF) {
		    continue;
		}

		// В противном случае обновим
		// расстояние до вершины to,
		// это называют релаксацией
		distance[to] = Math.min(distance[to], distance[from] + weight);
		parent[to] = from;
	    }
	}
    }

    private void readData(InputStream stream) throws IOException {

	Scanner scanner = new Scanner(stream);

	numberVertex = scanner.nextInt();
	numberEdge = scanner.nextInt();
	start = (int) scanner.next().charAt(0) - 65;

	edges = new Edge[numberEdge];

	// read graph
	for (int i = 0; i < numberEdge; ++i) {
	    int u = (int) scanner.next().charAt(0) - 65;
	    int v = (int) scanner.next().charAt(0) - 65;
	    int weight = (int) scanner.next().charAt(0) - 65;

	    Edge edge = new Edge(u, v, weight);
	    edges[i] = edge;
	}

	scanner.close();

	used = new boolean[numberVertex];
	Arrays.fill(used, false);

	parent = new int[numberVertex];
	Arrays.fill(parent, -1);

	distance = new int[numberVertex];
	Arrays.fill(distance, INF);

    }

   
    private void printData() throws IOException {
  	for (int v = 0; v < numberVertex; ++v) {
  	    if (distance[v] != INF) {
  		System.out.print(distance[v] + " ");
  	    } else {
  		System.out.print("-1 ");
  	    }
  	}
    }

    private void run() throws IOException {

	String root = System.getProperty("user.dir") + "/src/";
	InputStream stream = new FileInputStream(root + "by/it/group473602/prilepin/lesson11/dataB.txt");

	readData(stream);
	dejkstra(start);
	printData();
    }

    public static void main(String[] args) throws IOException {
	B_Bellman_Ford solution = new B_Bellman_Ford();
	solution.run();
    }
}