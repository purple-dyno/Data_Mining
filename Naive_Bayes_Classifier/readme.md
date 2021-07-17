# Naive Bayes Classifier

## Input

````
Number of instances: 14

Number of attributes: 5

Values of attributes: age income student credit_rating buys_computer

Value of instances:

youth high no fair no

youth high no excellent no

middle_aged high no fair yes

senior  medium no fair yes

senior low yes fair yes

senior low yes excellent no

middle_aged low yes excellent yes

youth medium no fair no

youth low yes fair yes

senior medium yes fair yes

youth medium yes excellent yes

middle_aged medium no excellent yes

middle_aged high yes fair yes

senior medium no excellent no

---

If count?: Yes:1 or No:0: 0

Name of the attribute to predict: buys_computer

Value of instances to predict:

age: youth

income: medium

student: yes

credit_rating: fair
```

## Output

```
Prior probability P(i) for each class

P(buys_computer = 'no') = 5/14 = 0.357

P(buys_computer = 'yes') = 9/14 = 0.643


To compute P(X|Ci) for i=..., we compute the following conditional probabilities.


P(income = 'medium' | buys_computer = 'no') = 2/5 = 0.400

P(income = 'medium' | buys_computer = 'yes') = 4/9 = 0.444


P(credit_rating = 'fair' | buys_computer = 'no') = 2/5 = 0.400

P(credit_rating = 'fair' | buys_computer = 'yes') = 6/9 = 0.667


P(student = 'yes' | buys_computer = 'no') = 1/5 = 0.200

P(student = 'yes' | buys_computer = 'yes') = 6/9 = 0.667


P(age = 'youth' | buys_computer = 'no') = 3/5 = 0.600

P(age = 'youth' | buys_computer = 'yes') = 2/9 = 0.222

---

Using the probabilities we obtain

P(X|buys_computer = 'no') = 0.019

P(X|buys_computer = 'yes') = 0.044

---

P(X|buys_computer = 'no') * P(buys_computer = 'no') = 0.019 * 0.357 = 0.007

P(X|buys_computer = 'yes') * P(buys_computer = 'yes') = 0.044 * 0.643 = 0.028


Since, 0.028 is greatest. Therefore the naive bayesian classfier predicts (buys_computer = 'yes') for Sample X.

## Note

If frequency of even one tuple is more than 1 then select "Yes" (1) for the following input prompt

If count?: Yes:1 or No:0:
```