Triangulation:

Given is a convex polygon with n vertices: (x1, y1),(x2, y2), ...,(xn, yn). The vertices are listed in clockwise order. A triangulation of a convex polygon is a set of n-3 nonintersecting edges (they can, however, meet at a vertex), where each edge connects two non-consecutive vertices. The overall picture consists of n - 2 triangles that together form the original polygon. We will define the length of a triangulation as the sum of the lengths of these n-3 edges. Give an O(n 3 ) algorithm that finds the minimum possible length of a triangulation of the given polygon. Hint: Use a 2D dynamic programming array where S[j][k] corresponds to the minimum length triangulation of the polygon defined by vertices (xj , yj ), ...,(xk, yk). Hint: Any triangulation of the polygon defined by vertices (xj , yj ), ...,(xk, yk) must contain a triangle that includes the edge from (xk, yk) to (xj , yj ). Use this fact to split the problem into two subproblems that retain consecutive ordering of their vertices.


Input specification: the first line contains n. Following that are n lines. Each line contains xi and yi, separated by a space. You may assume that n is not bigger than 1000 and that all the coordinates fit in double. 

Output specification: the output contains one line with the integer part of the minimum possible length of a triangulation. (Include the new-line character in your output line.) 

Explanation for sample input 1: Let's name the vertices 1,2,...,5. The possible triangulations are (1,3)+(1,4), or (2,4)+(2,5), or (3,1)+(3,5), or (4,1)+(4,2), or (5,2)+(5,3). The corresponding lengths are 42.9762, and 34.7577, and 36.5028, and 41.2311, and 28.2843. Out of these the lowest is 28.2843 and if we truncate to the integer part we get 28. 


Sample Input and Output:

1.

5
0 0
0 10.0
20.0 10.0
20.0 5.0
10.0 0

>>28

2.

6
0 10.0
5.0 20.0
10.0 25.0
15.0 20.0
20.0 10.0
10.0 0

>>48

3.

10
2.9 0.0
1.5 0.1
0.0 2.5
0.4 5.0
1.1 6.5
5.3 9.5
9.3 8.5
10.5 6.5
10.5 3.5
8.4 1.5

>>43