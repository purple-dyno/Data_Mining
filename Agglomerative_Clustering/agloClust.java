/* 
Authors
https://github.com/Manu1ND
https://github.com/Ichigo27
*/

// Agglomerative Clustering

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class agloClust {
	int numOfPoints, maxPoints = 10, choice;
	String method;
	String names[] = new String[maxPoints];
	double distances[][] = new double[maxPoints][maxPoints];
	double tempDistances[][] = new double[maxPoints][maxPoints];
	List<ArrayList<Integer>> clusters = new ArrayList<ArrayList<Integer>>();
	List<ArrayList<ArrayList>> history = new ArrayList<ArrayList<ArrayList>>();
	Scanner sc = new Scanner(System.in);

	void input() {
		for (int i = 0; i < numOfPoints; i++) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			list.add(i);
			clusters.add(list);
		}

		System.out.print("Enter number of points: ");
		numOfPoints = sc.nextInt();
		System.out.print("Enter points: ");
		for (int i = 0; i < numOfPoints; i++) {
			names[i] = sc.next();
			ArrayList<Integer> list = new ArrayList<Integer>();
			list.add(i);
			clusters.add(list);
		}
		System.out.println("\nEnter distance matrix");
		for (int i = 0; i < numOfPoints; i++) {
			for (int j = 0; j < numOfPoints; j++) {
				distances[i][j] = sc.nextDouble();
			}
		}
		System.out.println("\n1. Single Linkage\t2. Complete Linkage\t3. Average Linkage");
		System.out.print("Choose Method: ");
		this.choice = sc.nextInt();
		switch (this.choice) {
			case 1:
				this.method = "Dendrogram - Single Linkage";
				break;
			case 2:
				this.method = "Dendrogram - Complete Linkage";
				break;
			case 3:
				this.method = "Dendrogram - Average Linkage";
				break;
			default:
				break;
		}
	}

	// Single Linkage
	void calcDistMin() {
		double min, tempMin;
		for (int clust1 = 0; clust1 < clusters.size(); clust1++) {
			for (int clust2 = clust1 + 1; clust2 < clusters.size(); clust2++) {
				min = distances[clusters.get(clust1).get(0)][clusters.get(clust2).get(0)];
				for (Integer point1 : clusters.get(clust1)) {
					for (Integer point2 : clusters.get(clust2)) {
						tempMin = distances[point1][point2];
						if (tempMin < min) {
							min = tempMin;
						}
					}
				}
				tempDistances[clust1][clust2] = min;
				tempDistances[clust2][clust1] = min;
			}
		}
		printMatrix();
	}

	// Complete Linkage
	void calcDistMax() {
		double max, tempMax;
		for (int clust1 = 0; clust1 < clusters.size(); clust1++) {
			for (int clust2 = clust1 + 1; clust2 < clusters.size(); clust2++) {
				max = 0;
				for (Integer point1 : clusters.get(clust1)) {
					for (Integer point2 : clusters.get(clust2)) {
						tempMax = distances[point1][point2];
						if (max < tempMax) {
							max = tempMax;
						}
					}
				}
				tempDistances[clust1][clust2] = max;
				tempDistances[clust2][clust1] = max;
			}
		}
		printMatrix();
	}

	// Average Linkage
	void calcDistAvg() {
		double avg, count;
		for (int clust1 = 0; clust1 < clusters.size(); clust1++) {
			for (int clust2 = clust1 + 1; clust2 < clusters.size(); clust2++) {
				avg = count = 0;
				for (Integer point1 : clusters.get(clust1)) {
					for (Integer point2 : clusters.get(clust2)) {
						avg += distances[point1][point2];
						count++;
					}
				}
				avg /= count;
				tempDistances[clust1][clust2] = avg;
				tempDistances[clust2][clust1] = avg;
			}
		}
		printMatrix();
	}

	void printMatrix() {
		String toPrint = "";
		System.out.printf("\n%14s ", "");
		for (ArrayList<Integer> cluster : clusters) {
			toPrint = names[cluster.get(0)];
			for (int i = 1; i < cluster.size(); i++) {
				toPrint += "-" + names[cluster.get(i)];
			}
			System.out.printf("%-14s ", toPrint);
		}
		for (int i = 0; i < clusters.size(); i++) {
			System.out.println();
			toPrint = names[clusters.get(i).get(0)];
			for (int j = 1; j < clusters.get(i).size(); j++) {
				toPrint += "-" + names[clusters.get(i).get(j)];
			}
			System.out.printf("%-14s ", toPrint);
			for (int j = 0; j < clusters.size(); j++) {
				System.out.printf("%-14.3f ", tempDistances[i][j]);
			}
		}
	}

	// calculate y points for dendrogram
	ArrayList<String> dendroStruct(int clustIndex, ArrayList<String> yCord) {
		ArrayList<String> clust = new ArrayList<String>();
		ArrayList<String> clustTemp = new ArrayList<String>();
		for (Integer point : clusters.get(clustIndex)) {
			clust.add(names[point]);
			System.out.print(names[point]);
		}
		if (clust.size() > 1) {
			for (ArrayList<ArrayList> block : history) {
				clustTemp.clear();
				for (int i = 0; i < 2; i++) {
					for (String s : (ArrayList<String>) block.get(i)) {
						clustTemp.add(s);
					}
				}
				if (clust.equals(clustTemp)) {
					yCord.add((String) block.get(2).get(2));
				}
			}
		} else {
			yCord.add("0.0");
		}
		return clust;
	}

	// calculate x points for dendrogram
	double sortPoints(ArrayList<ArrayList> c, int start) {
		ArrayList<String> clust1 = new ArrayList<String>();
		ArrayList<String> clust2 = new ArrayList<String>();
		ArrayList<String> clustTemp = new ArrayList<String>();
		double clust1Avg = 0, clust2Avg = 0;
		clust1.clear();
		clust2.clear();
		for (String point : (ArrayList<String>) c.get(0)) {
			clust1.add(point);
		}
		if (clust1.size() == 1) {
			for (ArrayList<ArrayList> block : history) {
				if (block.equals(c)) {
					clust1Avg = start;
					block.get(3).add(Integer.toString(start));
				}
			}
		} else {
			for (ArrayList<ArrayList> block : history) {
				clustTemp.clear();
				for (int i = 0; i < 2; i++) {
					for (String s : (ArrayList<String>) block.get(i)) {
						clustTemp.add(s);
					}
				}
				if (clust1.equals(clustTemp)) {
					clust1Avg = sortPoints(block, start);
					break;
				}
			}
			for (ArrayList<ArrayList> block : history) {
				if (block.equals(c)) {
					block.get(3).add(Double.toString(clust1Avg));
				}
			}
		}
		for (String point : (ArrayList<String>) c.get(1)) {
			clust2.add(point);
		}
		if (clust2.size() == 1) {
			for (ArrayList<ArrayList> block : history) {
				if (block.equals(c)) {
					clust2Avg = start + clust1.size();
					block.get(3).add(Integer.toString(start + clust1.size()));
				}
			}
		} else {
			for (ArrayList<ArrayList> block : history) {
				clustTemp.clear();
				for (int i = 0; i < 2; i++) {
					for (String s : (ArrayList<String>) block.get(i)) {
						clustTemp.add(s);
					}
				}
				if (clust2.equals(clustTemp)) {
					clust2Avg = sortPoints(block, start + clust1.size());
					break;
				}
			}
			for (ArrayList<ArrayList> block : history) {
				if (block.equals(c)) {
					block.get(3).add(Double.toString(clust2Avg));
				}
			}
		}
		return (clust1Avg + clust2Avg) / 2.0;
	}

	// get mininum distance between 2 clusters
	void calcMatrixMin() {
		ArrayList<ArrayList> list = new ArrayList<ArrayList>();
		ArrayList<String> clust1 = new ArrayList<String>();
		ArrayList<String> clust2 = new ArrayList<String>();
		ArrayList<String> yCord = new ArrayList<String>();
		ArrayList<String> xCord = new ArrayList<String>();
		double tempMin, min = tempDistances[0][1];
		int clust1Index = 0, clust2Index = 1;
		for (int i = 0; i < clusters.size(); i++) {
			for (int j = i + 1; j < clusters.size(); j++) {
				tempMin = tempDistances[i][j];
				if (tempMin < min) {
					min = tempMin;
					clust1Index = i;
					clust2Index = j;
				}
			}
		}

		System.out.printf("\nMin = %.3f\n", min);
		clust1 = dendroStruct(clust1Index, yCord);
		System.out.print(" - ");
		clust2 = dendroStruct(clust2Index, yCord);
		yCord.add(Double.toString(min));
		list.add(clust1);
		list.add(clust2);
		list.add(yCord);
		list.add(xCord);
		history.add(list);
		clusters.get(clust1Index).addAll(clusters.get(clust2Index));
		clusters.remove(clust2Index);
	}

	void solve() {
		while (clusters.size() > 1) {
			System.out.println();
			switch (this.choice) {
				case 1:
					calcDistMin();
					break;
				case 2:
					calcDistMax();
					break;
				case 3:
					calcDistAvg();
					break;
				default:
					break;
			}
			calcMatrixMin();
		}
	}

	public static void main(String[] args) {
		ArrayList<ArrayList> list = new ArrayList<ArrayList>();
		agloClust ac = new agloClust();
		ac.input();
		ac.solve();
		list = ac.history.get(ac.history.size() - 1);
		ac.sortPoints(list, 1);
		dendro s = new dendro(ac.history, ac.numOfPoints, ac.method);
		s.setVisible(true);
	}
}