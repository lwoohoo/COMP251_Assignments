import java.util.TreeMap;
//java.util has standard RB tree implementation
public class RBTree {

    public static void main (String[] args) {
        TreeMap<Integer, Integer> RBTree = new TreeMap<Integer, Integer>(); //create new RB tree
        //Access node.rank as augmentation of RBTree

        //INIT TREE

        RBTree.put(5, 5);
        RBTree.put(2, 2);;
        RBTree.put(4, 4);
        RBTree.put(8, 8);
        RBTree.put(6, 6);
        RBTree.put(1, 1);
        RBTree.put(7, 7);
        RBTree.put(3, 3);

        //TEST 1
        System.out.println(" Show for RB Tree that a nodes order (position relative to other nodes)");
        System.out.println("is maintained for each node");
        System.out.println("The return values for each test show how many nodes in the tree are less than the input");
        System.out.println("Test 1 (EDGE as smallest node) (INPUT = 1)");
        System.out.println(RBTree.headMap(1, false).size());

        //Test 2
        System.out.println("Test 2 (standard input) (INPUT = 5)");
        System.out.println(RBTree.headMap(5, false).size());

        //Test 3
        System.out.println("Test 3 (Largest node, all should be lesser) (INPUT = 8)");
        System.out.println(RBTree.headMap(8, false).size());

    }

}