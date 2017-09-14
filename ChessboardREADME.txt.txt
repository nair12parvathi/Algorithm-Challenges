Chessboard:

Given is a d1×d2 array Chessboard containing only 0’s (empty squares) and 1’s (occupied positions). Moreover, there is an unlimited number of dominos, i.e. 1 × 2 rectangle pieces. Your task is to decide if it is possible to cover all of the empty squares on the chess board by non-overlapping dominos (the dominos cannot cover any of the occupied positions and they cannot “stick out” of the chess board). Your algorithm should run in time O((d1d2) 3 ). HINT: note that a chess board (or a checkers board) utilizes two different colors. What must be true about any 1 × 2 domino that is placed on the board?


Input specification: the first line contains d1 and d2, separated by a space. Then d1 lines follow. The i-th of these lines represents the i-th row of the chess board and it contains d2 numbers, each number is either 0 or 1 (and the numbers are separated by spaces). You may assume that d1 and d2 are bigger than 1 and not bigger than 10000. 

Output specification: the output contains one line with the string "YES" if it is possible to cover the chess board with dominos, and "NO" if it is not possible to do so, followed by the end-of-line symbol "\n". 

Sample Input and Output:

1.

4 5
0 0 0 0 0
0 0 1 0 0
0 0 1 0 0
0 0 0 0 0

>>YES

2.

4 5
0 0 0 0 0
0 0 1 0 0
0 0 1 1 0
0 0 0 0 1

>>NO

3.

4 5
1 1 0 0 0
0 0 1 0 0
0 0 1 1 1
0 0 0 0 0

>>NO

4.

7 5
0 0 1 0 0
0 1 1 1 0
0 1 0 1 0
0 0 0 0 0
0 1 0 1 0
0 1 1 1 0
0 0 1 0 0

>>NO

5.

10 5
0 0 0 0 0
0 1 0 1 0
0 0 0 1 0
0 0 0 0 0
0 1 0 1 0
0 0 0 0 0
0 1 0 0 0
0 0 0 1 0
0 0 0 0 0
0 0 0 1 0

>>YES

6.

7 5
0 1 1 1 0
0 1 0 1 0
1 0 0 0 1
0 1 0 1 0
0 1 0 1 0
1 0 0 0 1
1 1 0 1 1

>>NO

7.

20 20
0 1 0 0 0 0 1 1 0 0 0 1 0 0 0 0 1 0 0 1
0 0 0 0 1 0 0 0 1 0 0 0 1 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 1 0 0 0 1 1 0 0 1 0 0 0 0 1 0 0 0
0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 1
0 0 1 0 0 1 0 0 0 0 1 0 0 0 0 0 0 0 0 0
0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 1 0
0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 1 1 0
0 0 0 0 0 0 1 0 0 0 1 0 0 1 0 0 0 0 0 0
0 1 0 0 0 1 0 0 0 1 0 0 0 0 0 0 1 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0
0 0 1 0 0 0 1 1 0 0 0 0 0 1 0 0 0 0 0 1
0 0 0 0 0 0 1 1 0 0 0 0 1 0 0 0 0 1 0 0
0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0
0 0 0 0 1 0 0 0 1 0 0 0 0 0 0 0 0 0 1 0
0 1 0 0 0 0 0 0 0 0 1 0 1 0 0 0 1 0 0 0
0 0 0 1 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 1
0 0 0 0 0 0 0 0 1 1 0 0 0 0 0 1 0 0 0 0
0 1 0 0 1 0 0 0 0 0 0 1 0 0 1 0 0 0 1 0
1 0 0 0 0 1 0 0 0 1 0 0 0 0 0 0 0 0 0 1

>>YES

8.

20 20
0 1 0 0 0 0 1 1 0 0 0 1 0 0 0 0 1 0 0 1
0 0 0 0 1 0 0 0 1 0 0 0 1 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 1 0 0 0 1 1 0 0 1 0 0 0 0 1 0 0 0
0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 1
0 0 1 0 0 1 0 0 0 0 1 0 0 0 0 0 0 0 0 0
0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 1 0
0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 1 1 0
0 0 0 0 0 0 1 0 0 0 1 0 0 1 0 0 0 0 0 0
0 1 0 0 0 1 0 0 0 1 0 0 0 0 0 0 1 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0
0 0 1 0 0 0 1 1 0 0 0 0 0 1 0 0 0 0 0 1
0 0 0 0 0 0 1 1 0 0 0 0 1 0 0 0 0 1 0 0
1 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0
0 0 0 0 1 0 0 0 1 0 0 0 0 0 0 0 0 0 1 0
0 1 0 0 0 0 0 0 0 0 1 0 1 0 0 0 1 0 0 0
0 0 0 1 0 0 1 0 0 0 0 0 0 1 0 0 0 0 0 1
0 0 0 0 0 0 0 0 1 1 0 0 0 0 0 1 0 0 0 0
0 1 0 0 1 0 0 0 0 0 0 1 0 0 1 0 0 0 1 0
1 0 0 0 0 1 0 0 0 1 0 0 0 0 0 0 0 0 0 1

NO




