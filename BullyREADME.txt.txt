Bully:

There is a pecking order among the kids as they wait for the school bus. They wait in a line ordered by age, with the oldest child first in line. Some of the kids are carrying their school lunch. The others have an account and buy lunch at school, and are not carrying anything with them. Among the kids, there is a universally accepted value assigned to the different lunches being brought to school. While they wait for the bus to come, there is some bullying that occurs. If an older child has a lunch of lower value than does the younger child standing next in line, the older child will force the younger child to swap lunches. Bullying can only occur from an older child to the younger child next in line, and can only occur if both children are carrying a lunch. This bullying continues, in no specific order, until no more bullying is possible. Design an O(n log n) algorithm that computes the number of incidents of bullying that occur.

Input specification: the first line contains n, the total number of children waiting for the bus. The next line contains n integers, with a space separating each number from the next. Each of these numbers represents the universally agreed upon value of a student's lunch. The values are specified beginning with the first student in line (the oldest), and continuing sequentially to the last student in line (the youngest). The value -1 indicates that the student is not carrying a lunch. All other values will be greater than or equal to 0, and represent the value of the lunch that that student is carrying. 

For the purposes of deciding what data types you need to represent the input, you may assume that all input values will fit in a 32-bit integer. However, you should not make any assumptions on the type (integer versus floating point) or range of the values when designing your algorithm to solve the problem. 

Output specification: the output contains a single line containing a single number: the number of bullying incidents that occur. 


Sample Input and Output:

1.

8
5 -1 3 10 10 -1 8 6

>>2

2.

10
3 6 8 -1 5 9 2 4 -1 3

>>5

3.

100
3 5 4 3 1 4 5 6 9 9 3 6 7 0 2 1 8 2 1 7 9 7 6 0 5 8 5 7 8 3 1 0 1 7 1 6 9 0 7 6 4 7 1 1 8 0 6 0 7 8 9 2 6 5 1 7 3 7 8 -1 0 0 3 0 5 0 8 7 0 8 2 3 3 0 7 3 3 7 7 3 8 4 5 0 0 6 8 1 2 6 8 3 8 3 0 2 9 3 3 8 

>>1212