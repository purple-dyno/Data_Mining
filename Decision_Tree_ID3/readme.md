# Decision Tree(ID3)

## Input

Enter the number of instances: 11

Enter the number of attributes: 4

Enter the value of attributes: department age salary status


Enter the value of instances:

sales 31...35 46K...50K yes 30

sales 26...30 26K...30K no 40

sales 31...35 31K...35K no 40

systems 21...25 46K...50K no 20

systems 31...35 66K...70K yes 5

systems 26...30 46K...50K no 3

systems 41...45 66K...70K yes 3

marketing 36...40 46K...50K yes 10

marketing 31...35 41K...45K no 4

secretary 46...50 36K...40K yes 4

secretary 26...30 26K...30K no 6

## Output

Yes = 52, No = 113

Entropy = -52/165 * log(52/165) -113/165 * log(113/165) = 0.899

### For Attribute department:

sales: Yes = 30, No = 80

Entropy = -30/110 * log(30/110) -80/110 * log(80/110) = 0.845


systems: Yes = 8, No = 23

Entropy = -8/31 * log(8/31) -23/31 * log(23/31) = 0.824


marketing: Yes = 10, No = 4

Entropy = -10/14 * log(10/14) -4/14 * log(4/14) = 0.863


secretary: Yes = 4, No = 6

Entropy = -4/10 * log(4/10) -6/10 * log(6/10) = 0.971


`Expected Entropy = (110/165) * 0.845 + (31/165) * 0.824 + (14/165) * 0.863 + (10/165) * 0.971 = 0.850`

`Information Gain = 0.899 - 0.850 = 0.049`

### For Attribute age:

31...35: Yes = 35, No = 44

Entropy = -35/79 * log(35/79) -44/79 * log(44/79) = 0.991


26...30: Yes = 0, No = 49

Entropy = -0/49 * log(0/49) -49/49 * log(49/49) = 0.000


21...25: Yes = 0, No = 20

Entropy = -0/20 * log(0/20) -20/20 * log(20/20) = 0.000


41...45: Yes = 3, No = 0

Entropy = -3/3 * log(3/3) -0/3 * log(0/3) = 0.000


36...40: Yes = 10, No = 0

Entropy = -10/10 * log(10/10) -0/10 * log(0/10) = 0.000


46...50: Yes = 4, No = 0

Entropy = -4/4 * log(4/4) -0/4 * log(0/4) = 0.000


`Expected Entropy = (79/165) * 0.991 + (49/165) * 0.000 + (20/165) * 0.000 + (3/165) * 0.000 + (10/165) * 0.000 + (4/165) * 0.000 = 0.474`

`Information Gain = 0.899 - 0.474 = 0.425`

### For Attribute salary:

46K...50K: Yes = 40, No = 23

Entropy = -40/63 * log(40/63) -23/63 * log(23/63) = 0.947


26K...30K: Yes = 0, No = 46

Entropy = -0/46 * log(0/46) -46/46 * log(46/46) = 0.000


31K...35K: Yes = 0, No = 40

Entropy = -0/40 * log(0/40) -40/40 * log(40/40) = 0.000


66K...70K: Yes = 8, No = 0

Entropy = -8/8 * log(8/8) -0/8 * log(0/8) = 0.000


41K...45K: Yes = 0, No = 4

Entropy = -0/4 * log(0/4) -4/4 * log(4/4) = 0.000


36K...40K: Yes = 4, No = 0

Entropy = -4/4 * log(4/4) -0/4 * log(0/4) = 0.000


`Expected Entropy = (63/165) * 0.947 + (46/165) * 0.000 + (40/165) * 0.000 + (8/165) * 0.000 + (4/165) * 0.000 + (4/165) * 0.000 = 0.362`

`Information Gain = 0.899 - 0.362 = 0.538`

### Attribute 'salary' has the highest gain(0.537518) so it is choosen as the root node

---

salary:46K...50K : Yes = 40, No = 23

Entropy = -40/63 * log(40/63) -23/63 * log(23/63) = 0.947

### For Attribute department:

sales: Yes = 30, No = 0

Entropy = -30/30 * log(30/30) -0/30 * log(0/30) = 0.000


systems: Yes = 0, No = 23

Entropy = -0/23 * log(0/23) -23/23 * log(23/23) = 0.000


marketing: Yes = 10, No = 0

Entropy = -10/10 * log(10/10) -0/10 * log(0/10) = 0.000


secretary: Yes = 0, No = 0

`Expected Entropy = (30/63) * 0.000 + (23/63) * 0.000 + (10/63) * 0.000 + (0/63) * 0.000 = 0.000`

`Information Gain = 0.947 - 0.000 = 0.947`

### For Attribute age:

31...35: Yes = 30, No = 0

Entropy = -30/30 * log(30/30) -0/30 * log(0/30) = 0.000


26...30: Yes = 0, No = 3

Entropy = -0/3 * log(0/3) -3/3 * log(3/3) = 0.000


21...25: Yes = 0, No = 20

Entropy = -0/20 * log(0/20) -20/20 * log(20/20) = 0.000


41...45: Yes = 0, No = 0

36...40: Yes = 10, No = 0

Entropy = -10/10 * log(10/10) -0/10 * log(0/10) = 0.000


46...50: Yes = 0, No = 0

`Expected Entropy = (30/63) * 0.000 + (3/63) * 0.000 + (20/63) * 0.000 + (0/63) * 0.000 + (10/63) * 0.000 + (0/63) * 0.000 = 0.000`

`Information Gain = 0.947 - 0.000 = 0.947`

### Attribute 'department' has the highest gain(0.946819) so it is choosen as the root node

---

salary:46K...50K department:sales : Yes = 30, No = 0

Entropy = -30/30 * log(30/30) -0/30 * log(0/30) = 0.000

status = Yes

---

salary:46K...50K department:systems : Yes = 0, No = 23

Entropy = -0/23 * log(0/23) -23/23 * log(23/23) = 0.000

status = No

---

salary:46K...50K department:marketing : Yes = 10, No = 0

Entropy = -10/10 * log(10/10) -0/10 * log(0/10) = 0.000

status = Yes

---

salary:46K...50K department:secretary : Yes = 0, No = 0

---

salary:26K...30K : Yes = 0, No = 46

Entropy = -0/46 * log(0/46) -46/46 * log(46/46) = 0.000

status = No

---

salary:31K...35K : Yes = 0, No = 40

Entropy = -0/40 * log(0/40) -40/40 * log(40/40) = 0.000

status = No

---

salary:66K...70K : Yes = 8, No = 0

Entropy = -8/8 * log(8/8) -0/8 * log(0/8) = 0.000

status = Yes

---

salary:41K...45K : Yes = 0, No = 4

Entropy = -0/4 * log(0/4) -4/4 * log(4/4) = 0.000

status = No

---

salary:36K...40K : Yes = 4, No = 0

Entropy = -4/4 * log(4/4) -0/4 * log(0/4) = 0.000

status = Yes

---

### Decision Tree

salary

|__46K...50K: department

|&emsp;|__sales: = Yes

|&emsp;|__systems: = No

|&emsp;|__marketing: = Yes

|__26K...30K: = No

|__31K...35K: = No

|__66K...70K: = Yes

|__41K...45K: = No

|__36K...40K: = Yes


### Rules

salary:46K...50K, department:sales, status: = Yes

salary:46K...50K, department:systems, status: = No

salary:46K...50K, department:marketing, status: = Yes

salary:26K...30K, status: = No

salary:31K...35K, status: = No

salary:66K...70K, status: = Yes

salary:41K...45K, status: = No

salary:36K...40K, status: = Yes


## Note

The last column refers to the frequency of each tuple.

sales 31...35 46K...50K yes 30

In the above tuple, 30 is the frequency of the tuple and the number of attributes are 4.
