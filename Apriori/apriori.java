/* 
Authors
https://github.com/Manu1ND
https://github.com/Ichigo27
*/

package Apriori;
// Apriori

import java.util.*;

public class apriori {
	Scanner sc = new Scanner(System.in);

	int n, support, confidence, support_count;
	String temp[] = new String[2];
	List<String> items = new ArrayList<String>();
	HashMap<String, List<String>> dataset = new HashMap<String, List<String>>();
	HashSet<String> itemSet = new HashSet<String>();
	LinkedHashMap<String, Integer> c = new LinkedHashMap<String, Integer>();
	LinkedHashMap<String, Integer> l = new LinkedHashMap<String, Integer>();

	void input() {
		String str;
		System.out.print("Enter number of transactions: ");
		n = Integer.parseInt(sc.nextLine());
		System.out.println("Enter transactions");
		for (int i = 0; i < n; i++) {
			str = sc.nextLine();
			temp = str.split("\\s+");
			items = Arrays.asList(temp[1].split(","));
			dataset.put(temp[0], items);
			itemSet.addAll(items);
		}
		System.out.print("\nEnter minimum support (%): ");
		support = Integer.parseInt(sc.nextLine());
		System.out.print("Enter confidence (%): ");
		confidence = Integer.parseInt(sc.nextLine());
		System.out.print("\nSupport = (Tuples containing A U B) / (Total tuples)");
		support_count = (int) Math.round(support * dataset.size() / 100.0);
		System.out.print("\nConfidence = (Tuples containing A U B) / (Total containing only A)");
		System.out.print("\nMinimum support count: " + support_count);
	}

	void combination(ArrayList<String> input, ArrayList<String> output, int noOfComb) {
		ArrayList<String> tempinput;
		int limit;
		if (output.size() == noOfComb) {
			c.put(output.toString(), 0);
			return;
		}
		tempinput = (ArrayList<String>) input.clone();
		limit = input.size() + 1 - (noOfComb - output.size());
		for (int i = 0; i < limit; i++) {
			output.add(input.get(0));
			input.remove(0);
			combination(input, output, noOfComb);
			output.remove(output.size() - 1);
		}

		input.clear();
		for (String i : tempinput) {
			input.add(i);
		}
	}

	void prune(int pruneCount) {
		LinkedHashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
		LinkedHashMap<String, Integer> prunedC = new LinkedHashMap<String, Integer>();
		ArrayList<String> output = new ArrayList<String>();
		List<String> tempItems = new ArrayList<String>();
		temp.putAll(c);
		if (pruneCount < 2) {
			return;
		} else {
			for (String i : temp.keySet()) {
				c.clear();
				tempItems = Arrays.asList(i.replace("[", "").replace("]", "").split(", "));
				combination(new ArrayList<String>(tempItems), output, pruneCount);
				if (l.keySet().containsAll(c.keySet())) {
					prunedC.put(i, 0);
				}
			}
			c.clear();
			c.putAll(prunedC);
		}
	}

	void calcC(ArrayList<String> itemsInput, int groupCount) {
		ArrayList<String> output = new ArrayList<String>();
		List<String> tempItems = new ArrayList<String>();
		//int count;
		c.clear();
		combination(itemsInput, output, groupCount);
		prune(groupCount - 1);
		for (String i : c.keySet()) {
			tempItems = Arrays.asList(i.replace("[", "").replace("]", "").split(", "));
			for (List<String> j : dataset.values()) {
				if (j.containsAll(tempItems)) {
					/* if (tempItems.size() == 1) {
						count = c.get(i) + Collections.frequency(j, tempItems.get(0));
					} else {
						count = c.get(i) + 1;
					}
					c.put(i, count); */
					c.put(i, c.get(i)+1);
				}
			}
		}
		for (String i : c.keySet()) {
			System.out.printf("%-10s%-10d\n", i + ": ", c.get(i));
		}
	}

	void calcL() {
		Iterator itemIndex = c.entrySet().iterator();
		itemSet.clear();
		l.clear();
		while (itemIndex.hasNext()) {
			Map.Entry itemTuple = (Map.Entry) itemIndex.next();
			if ((int) itemTuple.getValue() >= support_count) {
				System.out.printf("%-10s%-10d\n", itemTuple.getKey() + ": ", itemTuple.getValue());
				itemSet.addAll(
						Arrays.asList(((String) itemTuple.getKey()).replace("[", "").replace("]", "").split(", ")));
				l.put((String) itemTuple.getKey(), (Integer) itemTuple.getValue());
			}
		}
	}

	void calculate() {
		ArrayList<String> output = new ArrayList<String>();
		List<String> tempItems = new ArrayList<String>();
		LinkedHashMap<String, Integer> l = new LinkedHashMap<String, Integer>();
		String assocLeftStr, assocRightStr;
		int iter = 1, maxCount = 0, flag = 0, count;
		do {
			System.out.println("\nC" + iter + ":");

			calcC(new ArrayList<String>(itemSet), iter);
			if (c.size() > 0) {
				System.out.println("\nL" + iter + ":");
				calcL();
				if (c.size() > 0) {
					l = (LinkedHashMap<String, Integer>) c.clone();
				}
			}
			iter++;
		} while (c.size() > 0);

		for (String i : l.keySet()) {
			if (l.get(i) > maxCount) {
				maxCount = l.get(i);
			}
		}
		System.out.println("NULL");
		System.out.println("\nThe frequent item set is");
		for (String i : l.keySet()) {
			if (l.get(i) == maxCount) {
				System.out.println(i);
			}
		}

		for (String i : l.keySet()) {
			if (l.get(i) == maxCount) {
				System.out.println("\n***********************************");
				System.out.printf("The association rule generated for %s is as follows:\n", i);
				ArrayList<String> itemsInput = new ArrayList<String>(
						Arrays.asList(i.replace("[", "").replace("]", "").split(", ")));
				c.clear();
				for (int j = itemsInput.size() - 1; j > 0; j--) {
					combination(itemsInput, output, j);
				}
				for (String j : c.keySet()) {
					tempItems = Arrays.asList(j.replace("[", "").replace("]", "").split(", "));
					for (List<String> k : dataset.values()) {
						if (k.containsAll(tempItems)) {
							if (tempItems.size() == 1) {
								count = c.get(j) + Collections.frequency(k, tempItems.get(0));
							} else {
								count = c.get(j) + 1;
							}
							c.put(j, count);
						}
					}
				}
				for (String j : c.keySet()) {
					c.put(j, (int) Math.round(maxCount * 100.0 / c.get(j)));
					ArrayList<String> assocRight = new ArrayList<String>(itemsInput);
					tempItems = Arrays.asList(j.replace("[", "").replace("]", "").split(", "));
					assocRight.removeAll(tempItems);
					assocLeftStr = j.replace("[", "").replace("]", "").replace(", ", " ^ ");
					assocRightStr = assocRight.toString().replace("[", "").replace("]", "").replace(", ", " ^ ");
					System.out.printf("%s => %s = %d\n", assocLeftStr, assocRightStr, c.get(j));
				}

				System.out.printf("\nSince confidence threshold is %s \n", confidence + "%");
				System.out.println("The strong association rules are");
				for (String j : c.keySet()) {
					if (c.get(j) > confidence) {
						ArrayList<String> assocRight = new ArrayList<String>(itemsInput);
						tempItems = Arrays.asList(j.replace("[", "").replace("]", "").split(", "));
						assocRight.removeAll(tempItems);
						assocLeftStr = j.replace("[", "").replace("]", "").replace(", ", " ^ ");
						assocRightStr = assocRight.toString().replace("[", "").replace("]", "").replace(", ", " ^ ");
						System.out.printf("%s => %s = %s\n", assocLeftStr, assocRightStr, c.get(j) + "%");
						flag = 1;
					}
				}
				if (flag == 0) {
					System.out.println("None");
				}
			}
		}
	}

	public static void main(String[] args) {
		apriori a = new apriori();
		a.input();
		a.calculate();
	}
}