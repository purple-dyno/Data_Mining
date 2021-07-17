/* 
Authors
https://github.com/Manu1ND
https://github.com/Ichigo27
*/

package Bayesian;
// Naive Bayes Classifier

import java.util.*;

public class bayesian {
	int nOfInst, nOfInstCount=0, nOfAttr, countPerTupleFlag;
	String predictAttribute;
	ArrayList<String> labels = new ArrayList<String>();
	ArrayList<Integer> countPerTuple = new ArrayList<Integer>();
	HashMap<String, String> predictSet = new HashMap<String, String>();
	HashMap<String, ArrayList<String>> dataset = new HashMap<String, ArrayList<String>>();
	HashMap<String, HashSet<String>> attributes = new HashMap<String, HashSet<String>>();
	Scanner sc = new Scanner(System.in);

	void input() {
		System.out.print("Number of instances: ");
		nOfInst = sc.nextInt();
		System.out.print("Number of attributes: ");
		nOfAttr = sc.nextInt();
		System.out.print("Values of attributes: ");
		// Store labels
		for (int i = 0; i < nOfAttr; i++) {
			labels.add(sc.next());
			ArrayList<String> init = new ArrayList<String>();
			dataset.put(labels.get(i), init);
		}
		// Store dataset
		System.out.println("\nValue of instances:");
		for (int i = 0; i < nOfInst; i++) {
			for (int j = 0; j < nOfAttr; j++) {
				dataset.get(labels.get(j)).add(sc.next());
			}
		}
		System.out.print("If count?: Yes:1 or No:0: ");
		countPerTupleFlag = sc.nextInt();
		if (countPerTupleFlag == 1){
			for (int i = 0; i < nOfInst; i++) {
				countPerTuple.add(sc.nextInt());
				nOfInstCount += countPerTuple.get(i);
			}
		} else {
			nOfInstCount = nOfInst;
			for (int i = 0; i < nOfInst; i++) {
				countPerTuple.add(1);
			}
		}
		// Store unique values of labels
		for (String label : dataset.keySet()) {
			HashSet<String> valuesOfAttr = new HashSet<String>();
			dataset.get(label).forEach(value -> {
				valuesOfAttr.add(value);
			});
			attributes.put(label, valuesOfAttr);
		}
		// Get tuple for prediction
		System.out.print("\nName of the attribute to predict: ");
		predictAttribute = sc.next();
		System.out.println("\nValue of instances to predict:");
		for (String label : labels) {
			if (!label.equals(predictAttribute)) {
				System.out.print(label + ": ");
				predictSet.put(label, sc.next());
			}
		}
	}

	void predict() {
		HashMap<String, Integer> priorProb = new HashMap<String, Integer>();
		HashMap<String, Double> prob = new HashMap<String, Double>();
		String finalPred = "";
		int count;
		Double result = 0.0, resultMAX = 0.0;

		System.out.println("\nPrior probability P(i) for each class");
		for (String predVal : attributes.get(predictAttribute)) {
			//count = Collections.frequency(dataset.get(predictAttribute), predVal);
			count = 0;
			for (int i = 0; i < nOfInst; i++) {
				if(dataset.get(predictAttribute).get(i).equals(predVal)) {
					count += countPerTuple.get(i);
				}
			}
			priorProb.put(predVal, count);
			System.out.printf("P(%s = '%s') = %d/%d = %.3f\n", predictAttribute, predVal, count, nOfInstCount,
					count / Double.valueOf(nOfInstCount));
			prob.put(predVal, 1.0);
		}

		System.out.println("\nTo compute P(X|Ci) for i=..., we compute the following conditional probabilities.");

		for (String attr : predictSet.keySet()) {
			System.out.println();
			for (String predVal : attributes.get(predictAttribute)) {
				count = 0;
				for (int i = 0; i < nOfInst; i++) {
					if (dataset.get(attr).get(i).equals(predictSet.get(attr))
							&& dataset.get(predictAttribute).get(i).equals(predVal)) {
						count += countPerTuple.get(i);
					}
				}
				System.out.printf("P(%s = '%s' | %s = '%s') = %d/%d = %.3f\n", attr, predictSet.get(attr),
						predictAttribute, predVal, count, priorProb.get(predVal),
						count / Double.valueOf(priorProb.get(predVal)));
				prob.put(predVal, prob.get(predVal) * count / Double.valueOf(priorProb.get(predVal)));
			}
		}

		System.out.println("\nUsing the probabilities we obtain");
		for (String predVal : attributes.get(predictAttribute)) {
			System.out.printf("P(X|%s = '%s') = %.3f\n", predictAttribute, predVal, prob.get(predVal));
		}
		System.out.println();
		
		for (String predVal : attributes.get(predictAttribute)) {
			Double tempProb = priorProb.get(predVal) / Double.valueOf(nOfInstCount);
			result = prob.get(predVal) * tempProb;
			System.out.printf("P(X|%s = '%s') * P(%s = '%s') = %.3f * %.3f = %.3f\n", predictAttribute, predVal, predictAttribute,
					predVal, prob.get(predVal), tempProb, result);
			if (resultMAX < result) {
				finalPred = predVal;
				resultMAX = result;
			}
		}
		System.out.printf("\nSince, %.3f is greatest. Therefore the naive bayesian classfier predicts (%s = '%s') for Sample X where X = {..}", resultMAX, predictAttribute, finalPred);
	}

	public static void main(String[] args) {
		bayesian n = new bayesian();
		n.input();
		n.predict();
	}
}