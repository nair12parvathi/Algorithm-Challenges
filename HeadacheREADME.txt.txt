Headache:

You are working at an amusement park on a day when a large company reserves the entire park for its employees. You are working at a ride fed by two lines. You already feel a headache coming on, and your goal is to get everybody in the two lines onto the ride with minimum aggravation of your headache. Here are the characteristics of your problem: 1. There are three types of employees: (E)xecutives, (V)eterans, and (N)ew Hires. 2. The two lines are filled with a mix of the three different types of employees. Line 1 has m people in it. Line 2 has n people in it. 3. The ride fits two people at a time. Standard procedure is to take the person at the front of each line and put them together on the ride. However... 4. E’s and N’s don’t want to sit together. Matching an E with an N causes grumbling that incurs 5 units of headache for you. (Any other pairing causes no problem.) 5. You can choose to fill a ride by taking two people from the front of the same line. However, this causes grumbling from the other line that incurs 3 units of headache to you, unless the other line is empty. If the two people you take from the front of the same line happen to be an E and an N, you would also incur an additional 5 units of headache (see above). 6. You can also choose to fill a car with only one person. Everybody grumbles that the line is moving too slowly, incurring 4 units of headache, unless there is nobody left at that point (this is the last person to ride). For example, suppose Line 1 = {E, E, N}, and Line 2 = {N, N, V, E}, where the lines are listed from the back to the front. You could start by pairing V, E from line 2 at a cost of 3. Then pair N from the front of each line at no cost. Then match E, E from line 1 at a cost of 3. Finally, N from line 2 rides alone at no cost (because there is nobody left). Thus the total cost is 6. However, this is not optimal. You could do better by letting N from line 1 ride alone to start, at a cost of 4. Then pair the front of each line twice (E, E and E, V ) at no cost. Finally, only N, N remains in line 2, and can ride at no cost (since the other line is empty). Thus the total cost is 4. No other solution yields a total less than 4. Give an O(mn) dynamic programming solution to this problem, that computes the minimum units of headache you will incur getting all of the company employees on the ride.

Input specification: the first line contains m, and the second line contains n. These numbers represent the number of employees in the first and second lines for the ride, respectively. The third input line contains m characters, with a space between consecutive characters. Each character is either E, V or N. These characters represent the people in the first line for the ride. The first of these characters is the person who is last in line for the ride. The last of these characters is the person who is first in line for the ride. The fourth input line contains n characters, with a space between consecutive characters. Each character is either E, V or N. These characters represent the people in the second line for the ride. The first of these characters is the person who is last in line for the ride. The last of these characters is the person who is first in line for the ride. You may assume that m and n are each at most 1000. 

Output specification: the output contains a single line containing the integer-valued minimum possible units of headache incurred. (Include the new-line character in your output line.) 


Sample Input and Output:

1.

4
3
V V N E
N E V

>>3

2.

3
2
E N E
N N

>>5

3.

2
3
E N
E N E

>>4

4.

13
11
N V V E E E N N E E E N V
E V N V N E V N E E N

>>7

5.

40
35
N N N V V V E E E E V V V N N N N N N N E E E E E N N N N N N N V V V V V N N N
V N E V N E V N E V N E V N E V N E V N E V N E V N E V N E V N E V N

>>27
