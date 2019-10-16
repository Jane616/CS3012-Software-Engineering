# CS3012-Software-Engineering
Files for TCD CS3012 courseworks
By Ruijie Xiong

# Development Task 1

LCA.java is the source code for solving the LCA problem in a binary tree.
LCATest.java is the JUnit test code for the methods in LCA.java.

LCA.java and LCATest.java should be opened and tested in a Eclipse java project, under the default package in src folder, and project initiated with javaSE 1.8 standard library. The JUnit 4 library should also be included as well.

In this task, lowest common ancester is defined as a node with the largest depth that lies on any 2 paths from root node to either target nodes, for example, the LCA of node 3 with path 1-2-3 and 1-4-3 and node 5 with path 1-4-5 is node 4. In addition, a node can be considered as its own ancester, for example, the LCA of node 3 with path 1-2-3 with node 2 with path 1-2 is node 2, a target node itself.

Some tests in LCATest.java fail because the solution in LCA.java cannot cope with those situations as it only records one possible route from root node to target node.

# Development Task 2

LCA_DAG.java is the source code for solving the LCA problem in a Directed Acyclic Graph.
LCA_DAGTest.java is the JUnit test code for the methods in LCA_DAG.java

LCA.java and LCATest.java should be opened and tested in a Eclipse java project, under the default package in src folder, and project initiated with javaSE 1.8 standard library. The JUnit 4 library should also be included as well.

In this task, the lowest common ancester is calculated as a lowest single common ancester, which is proposed by Fischer J, Huson D H. in New common ancestor problems in trees and directed acyclic graphs[J]. Available here: https://www.sciencedirect.com/science/article/pii/S0020019010000487. Lowest single common ancester(LSCA) is a node that lies on all possible paths from root node to target nodes, and no ancesters of the LSCA node satisfy this rule. Under this rule, if the target nodes are different nodes and exist in the DAG, there must be one and only one LSCA.

