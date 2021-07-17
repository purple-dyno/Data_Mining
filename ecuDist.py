def euc_dist(x1, x2, y1, y2):
	return ((x1-x2)**2 + (y1-y2)**2)**0.5

x = list(map(float, input("Enter x coords\n").split()))
y = list(map(float, input("Enter y coords or press enter if 1D array\n").split()))
dist_mat = []

if (len(x) != len(y)) and len(y) != 0:
	print("Enter again")
else:
	if len(y) == 0:
		y = [0] * len(x)
	for x1, y1 in zip(x, y):
		temp_dist_mat = []
		for x2, y2 in zip(x, y):
			temp_dist_mat.append(euc_dist(x1, x2, y1, y2))
		dist_mat.append(temp_dist_mat)
	
	for i in dist_mat:
		print(" ".join(map(lambda m: str(round(m, 3)), i)))
