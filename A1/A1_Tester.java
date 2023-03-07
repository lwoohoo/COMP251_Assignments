public class A1_Tester {
    public static void main (String[] args) {
        chainingTest1();
        chainingTest2();
        chainingTest3();
        linearProbingTest1();
        linearProbingTest2();
        linearProbingTest3();
        linearProbingTest4();
        linearProbingInsert1();
        linearProbingInsertRemove1();
        linearProbingInsertRemove2();
    }
    private static void chainingTest1() {
        Chaining chain = new Chaining(10,0,-1); int hashValue = chain.chain(1);
        if (hashValue == 30) { System.out.println("Chaining 1 = "+hashValue+". Test Passed.");
        } else { System.out.println("Chaining 1 = "+hashValue+" Expected = 30. Test Failed.");
        }
    }

    private static void chainingTest2() {
        Chaining chain = new Chaining(10,0,-1); int hashValue = chain.chain(4);
        if (hashValue == 25) { System.out.println("Chaining 4 = "+hashValue+". Test Passed.");
        } else { System.out.println("Chaining 4 = "+hashValue+" Expected = 25. Test Failed.");
        }
    }

    private static void chainingTest3() {
        Chaining chain = new Chaining(10,0,-1); int hashValue = chain.chain(8);
        if (hashValue == 19) { System.out.println("Chaining 8 = "+hashValue+". Test Passed.");
        } else { System.out.println("Chaining 8 = "+hashValue+" Expected = 19. Test Failed.");
        }
    }

    private static void linearProbingTest1() {
        Open_Addressing probe = new Open_Addressing(10,0,-1); int hashValue = probe.probe(1,0);
        if (hashValue == 30) { System.out.println("Probing 1,0 = "+hashValue+". Test Passed.");
        } else { System.out.println("Probing 1,0 = " + hashValue + " Expected = 30. Test Failed.");
        }
    }

    private static void linearProbingTest2() {
        Open_Addressing probe = new Open_Addressing(10,0,-1); int hashValue = probe.probe(1,1);
        if (hashValue == 31) { System.out.println("Probing 1,1 = "+hashValue+". Test Passed.");
        } else { System.out.println("Probing 1,1 = " + hashValue + " Expected = 31. Test Failed.");
        }
    }

    private static void linearProbingTest3() {
        Open_Addressing probe = new Open_Addressing(10,0,-1); int hashValue = probe.probe(1,3);
        if (hashValue == 1) { System.out.println("Probing 1,3 = "+hashValue+". Test Passed.");
        } else { System.out.println("Probing 1,3 = " + hashValue + " Expected = 1. Test Failed.");
        }
    }

    private static void linearProbingTest4() {
        Open_Addressing probe = new Open_Addressing(10,0,-1); int hashValue = probe.probe(2,0);
        if (hashValue == 28) { System.out.println("Probing 2,0 = "+hashValue+". Test Passed.");
        } else { System.out.println("Probing 2,0 = " + hashValue + " Expected = 28. Test Failed.");
        }
    }

    private static void linearProbingInsert1() {
        Open_Addressing probe = new Open_Addressing(10, 0, -1);
        probe.insertKey(32);
        int location = probe.Table[13];
        if (location == 32) {
            System.out.println("Open addressing insert 32 = " + location + " Test passed.");
        } else {
            System.out.println("Open addressing insert 32 = " + location + " Expected = 13. Test Failed.");
        }
    }

    private static void linearProbingInsertRemove1() {
        Open_Addressing probe = new Open_Addressing(10, 0, -1);
        probe.insertKey(32);
        probe.insertKey(52);
        probe.insertKey(72);
        probe.removeKey(52);
        probe.insertKey(92);
        int location1 = probe.Table[13];
        int location2 = probe.Table[14];
        int location3 = probe.Table[15];
        if (location1 == 32 && location2 == 92 && location3 == 72) {
            System.out.println("Open addressing insert/remove 32,52,72,52,92 = " + location1 +","+ location2 +","+ location3 +","+ " Test passed.");
        } else {
            System.out.println("Open addressing insert/remove 32,52,72,52,92 = " + location1 +","+ location2 +","+ location3 +","+ " Expected = 32, 92, 72. Test Failed.");
        }
    }

    private static void linearProbingInsertRemove2() {
        Open_Addressing probe = new Open_Addressing(10, 0, -1);
        probe.insertKey(69);
        probe.insertKey(89);
        probe.insertKey(109);
        probe.insertKey(129);
        int returnstatement = probe.removeKey(109);
        if (returnstatement == 2) {
            System.out.println("Open addressing insert/remove 69,89,109,129,109 = "+returnstatement+" Test passed.");
        } else {
            System.out.println("Open addressing insert/remove 69,89,109,129,109 = "+returnstatement+" Expected = 2. Test failed.");
        }
    }
}
