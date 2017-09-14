KnapsackTest:

Implement the code for the Indivisible Knapsack problem (see slides). Also implement the following recursive pseudocode: KNAPSACK-REC(k, v) 1. if k = 0 then return 0 2. if v &amp;lt; wk then return KNAPSACK-REC(k - 1, v) 3. return max{ KNAPSACK-REC(k - 1, v), ck+ KNAPSACK-REC(k - 1, v - wk) } Consider the following input instance: ci = wi = 1 for every i ? {1, 2, . . . , n} with total knapsack capacity n/2. Run both implementations on these input instances where n ranges through {5, 10, 20, 50, 100, 1000, 10000} (a total of 7 inputs). Measure the running time of both implementations and summarize your observations in a short paragraph. Also submit a plot with the running times (use timing functions inside your code and rerun it several times for the same n and report the median of the observed times). If your code runs longer than 10 minutes, you may stop the computation and report the time as “>10 minutes”.


Input specification: There is no input specification for this problem. 

Output specification: There is no output specification for this problem. When run, your code should run both implementations of the knapsack algorithm for at least one value of n, and output the timing results for each. The output does not need to be in any particular format 
