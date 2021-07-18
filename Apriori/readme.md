# Apriori

## Input

```
Enter number of transactions: 9

Enter transactions
100 I1,I2,I5
200 I2,I4
300 I2,I3
400 I1,I2,I4
500 I1,I3
600 I2,I3
700 I1,I3
800 I1,I2,I3,I5
900 I1,I2,I3

Enter minimum support (%): 22
Enter confidence (%): 70
```

## Output

```
Support = (Tuples containing A U B) / (Total tuples)
Confidence = (Tuples containing A U B) / (Total containing only A)

Minimum support count: 2

C1:
[I1] :     6
[I4] :     2
[I5] :     2

L1:
[I1] :     6
[I2] :     7
[I3] :     6
[I4] :     2
[I5] :     2

---

C2:
[I1, I2] : 4
[I1, I3] : 4
[I1, I4] : 1
[I1, I5] : 2
[I2, I3] : 4
[I2, I4] : 2
[I2, I5] : 2
[I3, I4] : 0
[I3, I5] : 1
[I4, I5] : 0

L2:
[I1, I2] : 4
[I1, I3] : 4
[I1, I5] : 2
[I2, I3] : 4
[I2, I4] : 2
[I2, I5] : 2

---

C3:
[I1, I2, I3] : 2
[I1, I2, I5] : 2

L3:
[I1, I2, I3] : 2
[I1, I2, I5] : 2

---

C4:
NULL

---

The frequent item set is
[I1, I2, I3]
[I1, I2, I5]

---

The association rule generated for [I1, I2, I3] is as follows:
I1 ^ I2 => I3 = 50
I1 ^ I3 => I2 = 50
I2 ^ I3 => I1 = 50
I1 => I2 ^ I3 = 33
I2 => I1 ^ I3 = 29
I3 => I1 ^ I2 = 33

Since confidence threshold is 70%
The strong association rules are
None

---

The association rule generated for [I1, I2, I5] is as follows:
I1 ^ I2 => I5 = 50
I1 ^ I5 => I2 = 100
I2 ^ I5 => I1 = 100
I1 => I2 ^ I5 = 33
I2 => I1 ^ I5 = 29
I5 => I1 ^ I2 = 100

Since confidence threshold is 70%
The strong association rules are
I1 ^ I5 => I2 = 100%
I2 ^ I5 => I1 = 100%
I5 => I1 ^ I2 = 100%
```