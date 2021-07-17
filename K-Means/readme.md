# K-Means 1-D

## Input

```
Enter number of Clusters: 2

Enter number of points: 9

Enter points: 2 4 10 12 3 20 30 11 25


Enter Mean

Enter Mean 1: 2

Enter Mean 2: 4
```

## Output

```
Cluster 1: 2.0 3.0

Mean 1 = 2.5


Cluster 2: 4.0 10.0 12.0 20.0 30.0 11.0 25.0

Mean 2 = 16.0

---

Cluster 1: 2.0 4.0 3.0

Mean 1 = 3.0


Cluster 2: 10.0 12.0 20.0 30.0 11.0 25.0

Mean 2 = 18.0

---

Cluster 1: 2.0 4.0 10.0 3.0

Mean 1 = 4.75


Cluster 2: 12.0 20.0 30.0 11.0 25.0

Mean 2 = 19.6

---

Cluster 1: 2.0 4.0 10.0 12.0 3.0 11.0

Mean 1 = 7.0


Cluster 2: 20.0 30.0 25.0

Mean 2 = 25.0

---

Cluster 1: 2.0 4.0 10.0 12.0 3.0 11.0

Mean 1 = 7.0


Cluster 2: 20.0 30.0 25.0

Mean 2 = 25.0

Since the clusters are same as the last iteration, these are the final clusters.

---

# K-Means 2-D

## Input

Enter number of Clusters: 3

Enter number of points: 8

Enter Points

Enter point 1: 2 10

Enter point 2: 2 5

Enter point 3: 8 4

Enter point 4: 5 8

Enter point 5: 7 5

Enter point 6: 6 4

Enter point 7: 1 2

Enter point 8: 4 9


Enter Centroid

Enter Centroid 1: 2 10

Enter Centroid 2: 5 8

Enter Centroid 3: 1 2

## Output

Let centroids be

C1: (2.000, 10.000)

C2: (5.000, 8.000)

C3: (1.000, 2.000)

---

Calculate the distance between points and centroids using Euclidean distance

write here euclidean formula


1: C1 = 0.000, C2 = 3.606, C3 = 8.062,

2: C1 = 5.000, C2 = 4.243, C3 = 3.162,

3: C1 = 8.485, C2 = 5.000, C3 = 7.280,

4: C1 = 3.606, C2 = 0.000, C3 = 7.211,

5: C1 = 7.071, C2 = 3.606, C3 = 6.708,

6: C1 = 7.211, C2 = 4.123, C3 = 5.385,

7: C1 = 8.062, C2 = 7.211, C3 = 0.000,

8: C1 = 2.236, C2 = 1.414, C3 = 7.616,


Cluster 1:

2.0, 10.0

Centroid 1 = (2.00, 10.00)


Cluster 2:

8.0, 4.0

5.0, 8.0

7.0, 5.0

6.0, 4.0

4.0, 9.0

Centroid 2 = (6.00, 6.00)


Cluster 3:

2.0, 5.0

1.0, 2.0

Centroid 3 = (1.50, 3.50)

---

1: C1 = 0.000, C2 = 5.657, C3 = 6.519,

2: C1 = 5.000, C2 = 4.123, C3 = 1.581,

3: C1 = 8.485, C2 = 2.828, C3 = 6.519,

4: C1 = 3.606, C2 = 2.236, C3 = 5.701,

5: C1 = 7.071, C2 = 1.414, C3 = 5.701,

6: C1 = 7.211, C2 = 2.000, C3 = 4.528,

7: C1 = 8.062, C2 = 6.403, C3 = 1.581,

8: C1 = 2.236, C2 = 3.606, C3 = 6.042,


Cluster 1:

2.0, 10.0

4.0, 9.0

Centroid 1 = (3.00, 9.50)


Cluster 2:

8.0, 4.0

5.0, 8.0

7.0, 5.0

6.0, 4.0

Centroid 2 = (6.50, 5.25)


Cluster 3:

2.0, 5.0

1.0, 2.0

Centroid 3 = (1.50, 3.50)

---

1: C1 = 1.118, C2 = 6.543, C3 = 6.519,

2: C1 = 4.610, C2 = 4.507, C3 = 1.581,

3: C1 = 7.433, C2 = 1.953, C3 = 6.519,

4: C1 = 2.500, C2 = 3.132, C3 = 5.701,

5: C1 = 6.021, C2 = 0.559, C3 = 5.701,

6: C1 = 6.265, C2 = 1.346, C3 = 4.528,

7: C1 = 7.762, C2 = 6.388, C3 = 1.581,

8: C1 = 1.118, C2 = 4.507, C3 = 6.042,


Cluster 1:

2.0, 10.0

5.0, 8.0

4.0, 9.0

Centroid 1 = (3.67, 9.00)


Cluster 2:

8.0, 4.0

7.0, 5.0

6.0, 4.0

Centroid 2 = (7.00, 4.33)


Cluster 3:

2.0, 5.0

1.0, 2.0

Centroid 3 = (1.50, 3.50)

---

1: C1 = 1.944, C2 = 7.557, C3 = 6.519,

2: C1 = 4.333, C2 = 5.044, C3 = 1.581,

3: C1 = 6.616, C2 = 1.054, C3 = 6.519,

4: C1 = 1.667, C2 = 4.177, C3 = 5.701,

5: C1 = 5.207, C2 = 0.667, C3 = 5.701,

6: C1 = 5.518, C2 = 1.054, C3 = 4.528,

7: C1 = 7.491, C2 = 6.438, C3 = 1.581,

8: C1 = 0.333, C2 = 5.548, C3 = 6.042,


Cluster 1:

2.0, 10.0

5.0, 8.0

4.0, 9.0

Centroid 1 = (3.67, 9.00)


Cluster 2:

8.0, 4.0

7.0, 5.0

6.0, 4.0

Centroid 2 = (7.00, 4.33)


Cluster 3:

2.0, 5.0

1.0, 2.0

Centroid 3 = (1.50, 3.50)


Since the clusters are same as the last iteration, these are the final clusters.
```