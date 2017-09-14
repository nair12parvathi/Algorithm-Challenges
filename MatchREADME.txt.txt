
Match:

You are given two disjoint sets, along with preference lists for each element of each set, ranking all of the elements of the other set. Design an O(n 2 ) algorithm that determines the number of elements from the first set that have only one valid partner. (An element from the first set has only one valid partner if it is paired with the same element from the second set in every stable matching.)

Input specification: the first line contains the value n, indicating the number of elements in each group. (In the remainder of the input, the elements of a group are referred to by an integer index in the range [0,n-1].) 

Following this is 2*n lines of data. The first n lines contain the preference lists for the elements of the first group. The first of these lines contains the preference list for element 0 of the first group. The next line contains the preference list for element 1 of the first group, and so forth. 

The next n lines contain the preference lists for the elements of the second group. The first of these lines contains the preference list for element 0 of the second group. The next line contains the preference list for element 1 of the second group, and so forth. 

Each line of data representing a preference list contains n numbers, separated by spaces. Specifically, it contains some permutation of the integers in the range [0,n-1]. The values are ordered from highest preference to lowest preference. 

You may assume all values fit in a 32-bit integer. 

Output specification: the output contains a single line, containing a single number that indicates the number of elements of the first group that have only one valid partner. The output line should end with the end-of-line character. 

Sample Input and Output

1.

3
0 1 2
1 0 2
0 1 2
1 0 2
0 1 2
0 1 2

>>1

2.

5
0 1 2 3 4
1 2 3 0 4
2 3 0 1 4
3 0 1 2 4
0 1 2 3 4
1 2 3 4 0
2 3 4 0 1
3 4 0 1 2
4 0 1 2 3
0 1 2 3 4

>>5