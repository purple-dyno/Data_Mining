/* 
Authors
https://github.com/Manu1ND
https://github.com/Ichigo27
*/

// Decision Tree (ID3)

import java.util.*;
import java.lang.Math.*;

class id3 {
	Scanner sc = new Scanner(System.in);
	static int maxLab = 10, maxValPerLab = 20, maxInst = 50;
	String[] labels = new String[maxLab];
	String[][] labelsDum = new String[maxLab][maxValPerLab];
	String[][] dataset = new String[maxInst][maxLab];
	String[][] rulesAttr = new String[maxValPerLab][maxLab];
	String[][] rulesValues = new String[maxValPerLab][maxLab];
	String[] rulesResult = new String[maxValPerLab];
	int rulesLength[] = new int[maxValPerLab];
	int nOfRules = 0;
	int labelPtr[] = new int[maxLab];
	int nOfInst, nOfAttr;
	double gain;

	void insert() {
		System.out.print("Enter the number of instances: ");
		nOfInst = sc.nextInt();
		System.out.print("Enter the number of attributes: ");
		nOfAttr = sc.nextInt();
		System.out.print("Enter the value of attributes: ");
		sc.nextLine();
		String columns = sc.nextLine();
		labels = columns.split("\\s+");

		// input dataset
		System.out.println("\nEnter the value of instances:");
		for (int i = 0; i < nOfInst; i++) {
			String row = sc.nextLine();
			dataset[i] = row.split("\\s+");
		}

		// store unique values of attributes
		for (int i = 0; i < nOfInst; i++) {
			for (int j = 0; j < nOfAttr; j++) {
				if (!Arrays.asList(labelsDum[j]).contains(dataset[i][j])) {
					labelsDum[j][labelPtr[j]] = dataset[i][j];
					labelPtr[j]++;
				}
			}
		}
	}

	// Info(a,b) = -1/(a+b) * (a*log(a/(a+b)) + b*log(b/(a+b)))
	double calcInfo(int a, int b) {
		double tempGain;
		int total = a + b;
		if (total == 0) {
			return 0;
		}
		double aRatio, bRatio, aInfo = 0, bInfo = 0;
		aRatio = (double) a / total;
		bRatio = (double) b / total;
		if (aRatio != 0)
			aInfo = aRatio * Math.log(aRatio);
		if (bRatio != 0)
			bInfo = bRatio * Math.log(bRatio);
		tempGain = -(aInfo + bInfo) / Math.log(2);
		if (tempGain == 0) // remove negative sign from -0
			tempGain = Math.round(tempGain);
		System.out.printf("\nEntropy = -%d/%d * log(%d/%d) -%d/%d * log(%d/%d) = %.3f\n", a, total, a, total, b, total,
				b, total, tempGain);
		return tempGain;

	}

	double calcEntropy(double infos[], int instancesPerAttr[], int noOfValues) {
		double entropy = 0;
		int instancesPerValue = 0;
		System.out.printf("\nExpected Entropy = ");
		for (int i = 0; i < noOfValues; i++) {
			entropy += instancesPerAttr[i] * infos[i];
			instancesPerValue += instancesPerAttr[i];
		}
		entropy /= instancesPerValue;
		for (int i = 0; i < noOfValues; i++) {
			System.out.printf("(%d/%d) * %.3f ", instancesPerAttr[i], instancesPerValue, infos[i]);
			if (i != noOfValues - 1) {
				System.out.printf("+ ");
			}
		}
		System.out.printf("= %.3f\n", entropy);
		return entropy;
	}

	void solve(int attributeIndex[], int valueIndex[], int origin, int nodeLevel) {
		int yes = 0, no = 0, maxGainIndex = 0, checkTuple, skip;
		double info, entropy, maxGain = 0;
		double infos[] = new double[maxValPerLab];
		double gains[] = new double[maxValPerLab];
		int instancesPerAttr[] = new int[20];
		String values = "";

		for (int i = 0; i < nOfInst; i++) {
			// filter tuples based on parameters
			checkTuple = 1;
			for (int j = 0; j < nodeLevel; j++) {
				if (!dataset[i][attributeIndex[j]].equals(labelsDum[attributeIndex[j]][valueIndex[j]])) {
					checkTuple = 0;
				}
			}
			if (origin == 1 || checkTuple == 1) {
				if (dataset[i][nOfAttr - 1].equals("yes")) {
					yes += Integer.parseInt(dataset[i][nOfAttr]);
				} else {
					no += Integer.parseInt(dataset[i][nOfAttr]);
				}
			}
		}
		if (origin == 0) {
			for (int i = 0; i < nodeLevel; i++) {
				values += labels[attributeIndex[i]] + ":" + labelsDum[attributeIndex[i]][valueIndex[i]] + " ";
			}
		}
		if (values == "") {
			System.out.print("\nYes = " + yes + ", No = " + no);
		} else {
			System.out.println("\n" + values + ": " + "Yes = " + yes + ", No = " + no);
		}
		gain = calcInfo(yes, no);
		if ((yes + no) == 0) {
			return;
		} else if (gain == 0) {
			for (int i = 0; i < nodeLevel; i++) {
				rulesAttr[nOfRules][i] = labels[attributeIndex[i]];
				rulesValues[nOfRules][i] = labelsDum[attributeIndex[i]][valueIndex[i]];
				rulesLength[nOfRules] += 1;
			}
			if (yes == 0) {
				rulesResult[nOfRules] = "= No";
				System.out.println("\n" + labels[nOfAttr - 1] + " = No");
			} else {
				rulesResult[nOfRules] = "= Yes";
				System.out.println("\n" + labels[nOfAttr - 1] + " = Yes");
			}
			nOfRules += 1;
			return;
		}

		for (int i = 0; i < nOfAttr - 1; i++) {
			// attribute shouldn't be part of the rule chain
			skip = 0;
			for (int j = 0; j < nodeLevel; j++) {
				if (i == attributeIndex[j]) {
					skip = 1;
				}
			}
			if (skip == 1) {
				continue;
			}
			System.out.printf("\nFor Attribute %s:\n", labels[i]);
			for (int j = 0; j < labelPtr[i]; j++) {
				yes = no = 0;
				for (int k = 0; k < nOfInst; k++) {
					// filter tuples based on parameters
					checkTuple = 1;
					for (int m = 0; m < nodeLevel; m++) {
						if (!dataset[k][attributeIndex[m]].equals(labelsDum[attributeIndex[m]][valueIndex[m]])) {
							checkTuple = 0;
						}
					}
					if (dataset[k][i].equals(labelsDum[i][j]) && checkTuple == 1) {
						if (dataset[k][nOfAttr - 1].equals("yes")) {
							yes += Integer.parseInt(dataset[k][nOfAttr]);
						} else {
							no += Integer.parseInt(dataset[k][nOfAttr]);
						}
					}
				}
				System.out.print("\n" + labelsDum[i][j] + ": " + "Yes = " + yes + ", No = " + no);
				info = calcInfo(yes, no);
				infos[j] = info;
				instancesPerAttr[j] = yes + no;
			}
			entropy = calcEntropy(infos, instancesPerAttr, labelPtr[i]);
			gains[i] = gain - entropy;
			System.out.printf("Information Gain = %.3f - %.3f = %.3f\n", gain, entropy, gains[i]);
			if (maxGain < gains[i]) {
				maxGain = gains[i];
				maxGainIndex = i;
			}
		}
		System.out.printf("\nAttribute '%s' has the highest gain(%f) so it is choosen as the root node\n",
				labels[maxGainIndex], maxGain);

		attributeIndex[nodeLevel] = maxGainIndex;
		for (int i = 0; i < labelPtr[maxGainIndex]; i++) {
			valueIndex[nodeLevel] = i;
			System.out.println("***********************************************************************");
			solve(attributeIndex, valueIndex, 0, nodeLevel + 1);
		}
	}

	void printTree(int ruleNo, String level) {
		if (ruleNo == 0) {
			System.out.println("\nDecision Tree");
		}
		if (ruleNo == nOfRules) {
			return;
		}
		for (int i = 0; i < rulesLength[ruleNo]; i++) {
			if (ruleNo > 0 && rulesAttr[ruleNo - 1][i] == rulesAttr[ruleNo][i]) {
				System.out.print("");
			} else {
				System.out.print(rulesAttr[ruleNo][i]);
			}
			if (ruleNo > 0 && rulesValues[ruleNo - 1][i] == rulesValues[ruleNo][i]) {
				System.out.print(level);
			} else {
				System.out.print("\n" + level + "  |___" + rulesValues[ruleNo][i] + ": ");
			}
			level += "  |   ";
		}
		System.out.print(rulesResult[ruleNo]);
		printTree(ruleNo + 1, "");
	}

	void printRules() {
		System.out.println("\n\nRules");
		for (int i = 0; i < nOfRules; i++) {
			for (int j = 0; j < rulesLength[i]; j++) {
				System.out.print(rulesAttr[i][j] + ":" + rulesValues[i][j] + ", ");
			}
			System.out.println(labels[nOfAttr - 1] + ": " + rulesResult[i]);
		}
	}

	public static void main(String args[]) {
		int attributeIndex[] = new int[maxLab];
		int valueIndex[] = new int[maxLab];
		id3 km = new id3();
		km.insert();
		km.solve(attributeIndex, valueIndex, 1, 0);
		km.printTree(0, "");
		km.printRules();

	}
}