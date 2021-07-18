/* 
Authors
https://github.com/Manu1ND
https://github.com/Ichigo27
*/

import java.util.*;
import java.lang.Math.*;

class kMeans2D {
	Scanner sc = new Scanner(System.in);
	int nOfElements, nOfClusters, flag;
	int ptr[] = new int[100];
	double centroid[][] = new double[100][2];
	double oldCentroid[][] = new double[100][2];
	static double elements[][] = new double[100][2];
	double k[][][] = new double[100][100][2];

	double dist(double x1, double y1, double x2, double y2) {
		return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}

	void insert() throws InvalidClusterSize {
		System.out.print("Enter number of Clusters: ");
		nOfClusters = sc.nextInt();
		System.out.print("Enter number of points: ");
		nOfElements = sc.nextInt();
		if (nOfElements < nOfClusters) {
			throw new InvalidClusterSize("Number of Elements cannot be less than Clusters!\n");
		}

		// input points
		System.out.println("\nEnter Points");
		for (int i = 0; i < nOfElements; i++) {
			System.out.print("Enter point " + (i + 1) + ": ");
			elements[i][0] = sc.nextDouble();
			elements[i][1] = sc.nextDouble();
		}

		// input centroids
		System.out.println("\nEnter Centroid");
		for (int i = 0; i < nOfClusters; i++) {
			System.out.print("Enter Centroid " + (i + 1) + ": ");
			centroid[i][0] = sc.nextDouble();
			centroid[i][1] = sc.nextDouble();
		}

		System.out.println("Let centroids be");
		for (int i = 0; i < nOfClusters; i++) {
			System.out.printf("C%d: (%.3f, %.3f)\n", (i + 1), centroid[i][0], centroid[i][1]);
		}
		System.out.println("\nCalculate the distance between points and centroids using Euclidean distance");
		System.out.println("write here euclidean formula");
	}

	void solve() {
		int clusterIndex = flag = 0;
		double leastDiff, tempDist;

		// initialise pointer for every cluster
		for (int i = 0; i < nOfClusters; i++) {
			ptr[i] = 0;
		}
		// store elements in cluster
		for (int i = 0; i < nOfElements; i++) {
			System.out.print("\n" + (i + 1) + ": ");
			leastDiff = dist(elements[i][0], elements[i][1], centroid[0][0], centroid[0][1]);
			clusterIndex = 0;
			for (int j = 0; j < nOfClusters; j++) {
				tempDist = dist(elements[i][0], elements[i][1], centroid[j][0], centroid[j][1]);
				System.out.printf("C%d = %.3f, ", j + 1, tempDist);
				if (tempDist < leastDiff) {
					leastDiff = tempDist;
					clusterIndex = j;
				}
			}
			k[clusterIndex][ptr[clusterIndex]][0] = elements[i][0];
			k[clusterIndex][ptr[clusterIndex]][1] = elements[i][1];
			ptr[clusterIndex]++;
		}
		System.out.println("\nDraw 2 tables");
		// calculate centroid
		for (int i = 0; i < nOfClusters; i++) {
			centroid[i][0] = centroid[i][1] = 0;
			for (int j = 0; j < ptr[i]; j++) {
				centroid[i][0] += k[i][j][0];
				centroid[i][1] += k[i][j][1];
			}
			centroid[i][0] /= ptr[i];
			centroid[i][1] /= ptr[i];
		}

		// break the recursive call if clusters are same
		for (int i = 0; i < nOfClusters; i++) {
			if ((oldCentroid[i][0] != centroid[i][0]) || oldCentroid[i][1] != centroid[i][1]) {
				flag = 1;
				break;
			}
		}
		if (flag == 1) {
			for (int i = 0; i < nOfClusters; i++) {
				oldCentroid[i][0] = centroid[i][0];
				oldCentroid[i][1] = centroid[i][1];
			}
			disp();
			System.out.println("----------------------------------------------");
			solve();

		}
	}

	void disp() {
		for (int i = 0; i < nOfClusters; i++) {
			System.out.println("\nCluster " + (i + 1) + ":");
			for (int j = 0; j < ptr[i]; j++) {
				System.out.println(k[i][j][0] + ", " + k[i][j][1]);
			}
			System.out.printf("Centroid %d = (%.2f, %.2f)\n", (i + 1), centroid[i][0], centroid[i][1]);
		}
	}

	public static void main(String args[]) {
		try {
			kMeans2D km = new kMeans2D();
			km.insert();
			km.solve();
			km.disp();
			System.out.println("Since the clusters are same as the last iteration, these are the final clusters.");
		} catch (InvalidClusterSize e) {
			System.out.println("Exception occured: " + e);
		}
	}
}

class InvalidClusterSize extends Exception {
	public InvalidClusterSize(String s) {
		super(s);
	}
}