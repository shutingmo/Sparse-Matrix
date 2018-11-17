public class Test {

   

    public static void main(String[] args){
        SparseInterface myTest = new SparseMatrix();

	myTest.setSize(3, 3);

        System.out.println("Num Rows is 3: " + (myTest.getNumRows() == 3));
 	System.out.println("Num Cols is 3: " + (myTest.getNumCols() == 3));

        myTest.addElement(0, 0, 16);

        myTest.addElement(0, 1, 4);

        myTest.removeElement(0,1);

        String correctString = "0 0 16\n";

        System.out.println("toString is correct: " + correctString.equals(myTest.toString()));

	myTest.removeElement(0,0);
        

        myTest.addElement(2,2,4);

        myTest.addElement(1,0,-3);

        correctString = "1 0 -3\n2 2 4\n";

        System.out.println("toString is correct: " + correctString.equals(myTest.toString()));
         

	myTest.removeElement(2, 2);

	myTest.removeElement(1, 0);

        myTest.addElement(0, 0, 0);

        correctString = "";

        //Because we are not storing 0 values in the matrix the toString should reflect an "empty" (all 0) matrix.
        System.out.println("toString is correct: " + correctString.equals(myTest.toString()));

        myTest.addElement(0, 1, 3);

        myTest.addElement(0, 1, 0);

        correctString = "";

        //Note that adding 0 to the matrix overwrites the data at that position to 0 as defined in the interface description
        //Because we are not storing 0 values, we can remove the element at that position.
        System.out.println("toString is correct: " + correctString.equals(myTest.toString()));

        myTest.addElement(0, 0, 16);
        myTest.addElement(0, 1, 4);
        myTest.addElement(1, 1, 9);
        myTest.addElement(2, 2, 7);
        
        SparseInterface addTest1 = new SparseMatrix();
	addTest1.setSize(3, 3);
        addTest1.addElement(0, 0, 1);
        addTest1.addElement(1, 1, 2);
        addTest1.addElement(2, 2, 3);
        
        SparseInterface addTest2 = new SparseMatrix();
	addTest2.setSize(3, 3);
        addTest2.addElement(0, 0, 3);
        addTest2.addElement(0, 0, 2);
        addTest2.addElement(0, 0, 1);
        
        SparseInterface addTest3 = addTest1.addMatrices(addTest2);
//        for (int r = 0; r < addTest3.getNumRows(); r++)
//		{
//			for (int c = 0; c < addTest3.getNumCols(); c++)
//			{
//				System.out.println(addTest3.getElement(r, c));
//			}
//		}
        
        System.out.println("added matrix: " + addTest3.toString());
        
        SparseInterface multiplyTest1 = new SparseMatrix();
        multiplyTest1.setSize(3, 3);
        multiplyTest1.addElement(0, 0, 1);
        multiplyTest1.addElement(0, 1, 1);
        multiplyTest1.addElement(0, 2, 1);

        SparseInterface multiplyTest2 = new SparseMatrix();
        multiplyTest2.setSize(3, 3);
        multiplyTest2.addElement(0, 0, 1);
        multiplyTest2.addElement(1, 0, 1);
        multiplyTest2.addElement(2, 0, 1);
        
        multiplyTest2.addElement(0, 1, 2);
        multiplyTest2.addElement(1, 1, 1);
        multiplyTest2.addElement(2, 1, 1);
        
        multiplyTest2.addElement(0, 2, 3);
        multiplyTest2.addElement(1, 2, 1);
        multiplyTest2.addElement(2, 2, 1);

        SparseInterface multiplyTest3 = multiplyTest1.multiplyMatrices(multiplyTest2);
        
        System.out.println("multiplied matrix: "+multiplyTest3.toString());

	SparseInterface test1 = new SparseMatrix();
        test1.setSize(3, 4);
	SparseInterface test2 = new SparseMatrix();
        test2.setSize(4, 5);

	SparseInterface test3 = test1.addMatrices(test2);  //should return null
	SparseInterface test4 = test1.multiplyMatrices(test2); //should work
	SparseInterface test5 = test2.multiplyMatrices(test1); //should return null

	SparseInterface multTest1 = new SparseMatrix();
    multTest1.setSize(2, 2);
    multTest1.addElement(0, 0, 2);
    multTest1.addElement(0, 1, 8);
    multTest1.addElement(1, 0, 3);
    multTest1.addElement(1, 1, 4);
    
    SparseInterface multTest2 = new SparseMatrix();
    multTest2.setSize(2, 3);
    multTest2.addElement(0, 0, 4);
    multTest2.addElement(0, 1, 2);
    multTest2.addElement(0, 2, 1);
    multTest2.addElement(1, 0, 3);
    multTest2.addElement(1, 1, 5);
    multTest2.addElement(1, 2, 7);

    SparseInterface multTest3 = multTest1.multiplyMatrices(multTest2);
    
    System.out.println("multiplied matrix: "+multTest3.toString());
    
    
    SparseInterface multTest4 = new SparseMatrix();
    multTest4.setSize(3,5);
    multTest4.addElement(0, 0, 4);
    multTest4.addElement(0, 1, 3);
    multTest4.addElement(0, 2, 31);
    multTest4.addElement(0, 3, 13);
    multTest4.addElement(0, 4, 7);
    multTest4.addElement(2, 1, 8);
    multTest4.addElement(1, 0, 3);
    multTest4.addElement(1, 1, 4);
    
    SparseInterface multTest5 = new SparseMatrix();
    multTest5.setSize(5, 4);
    multTest5.addElement(0, 0, 4);
    multTest5.addElement(0, 1, 2);
    multTest5.addElement(0, 2, 1);
    multTest5.addElement(1, 0, 3);
    multTest5.addElement(1, 1, 5);
    multTest5.addElement(1, 2, 7);
    multTest5.addElement(2, 3, 8);
    multTest5.addElement(3, 1, 9);
    multTest5.addElement(3, 2, 23);
    multTest5.addElement(4, 0, 3);
    multTest5.addElement(4, 3, 12);

    SparseInterface multTest6 = multTest4.multiplyMatrices(multTest5);
    
    System.out.println("multiplied matrix: "+multTest6.toString());
        
    }
}
