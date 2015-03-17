#Assembly Line Problem
##Shumail Mohy-ud-Din 
##BSCS-2B | 01947

GitHub Link: 
https://github.com/shumail92/CS213-Advanced-Programming/tree/master/Assignment-1-AssemblyLine

Assembly line problem is an optimization problem, solved using Dynamic Programming. Naive approach which is to solve this by checking each combination which can take O(2n) time but when solved us dynamic programming, it can be solved in O(n) time.

I have implemented Fastest Way Algorithm to solve the assembly line problem here and it abides by Dynamic Programming Principles i.e. solving the dependent sub-problems, saving the result and reusing it to solve greater sub-problems wherever possible whenever that sub-problem is encountered again.  In this implementation, it’s for two lines and n number of stations. 

The file AssemblyLine.java in package com.AP_Assign1.src is the primary file with implementation of fastest way algorithm in method fastestWayAssemblyLine(). The other method in class displaySolution() is for displaying the solution.

#Testing:

The other file AssemblyLineProblemTest.java in package com.AP_Assign1.test is the Junit test case for testing the implementation. It’s tested against different set of inputs to ensure that my implementation is correct and complete. 






