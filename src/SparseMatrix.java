
import java.util.ArrayList;

public class SparseMatrix implements SparseInterface 
{
	/*	Create an ArrayList to hold the values of the matrix
		We created an Index object that goes into each "slot" of the ArrayList. The Index holds the relevant information for the matrix
		such as row value, column value, and data value.
		We will access the ArrayList's object to get any information we need for the matrix operations
	*/
	public ArrayList<Index> matrix;
	public int numRows;
	public int numCols;
	
	//constructor makes default size of the matrix 5x5
	//the size can be changed later with the setSize() function
	public SparseMatrix()
	{
		matrix = new ArrayList<Index>();
		numRows = 5;
		numCols = 5;
	}
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		matrix.clear();
	}


	/*	
	 * To add an element into the ArrayList we check for any values that are out of bounds of the designated size of the matrix.
	 * To ensure that the matrix is sparse, we check if the data being entered is 0, if it is then we remove whatever element was at
	 * that row and column and return so the 0 is never added to the sparse matrix.
	 * We use a for loop to iterate through the ArrayList's indices. At each index, we compare the object's row and column values with the 
	 * row and column values that the user passes in. If there is a match that means there is already a number at that row and column of the matrix.
	 * We remove the existing object there and replace it with the value we're trying to add. We create a new index object to insert into the ArrayList.
	 * Added elements get attached to the end of the ArrayList, so the ArrayList is not in order. We can have (2,3,8) before (1,0,9)
	 */
	@Override
	public void addElement(int row, int col, int data) {
		// TODO Auto-generated method stub
		if (row < 0 || col < 0)
		{
			System.out.println("out of bounds");
		}
		
		if (row > (numRows-1) || col > (numCols-1))
		{
			System.out.println("out of bounds");
		}
		
		//if the number being added is 0 then don't add it to the sparse matrix
		if (data == 0)
		{
			removeElement(row, col);
			return;
		}
		
		//if the row and column values passed in already match an existing object in the array
		else
		{
			for (int i = 0; i < matrix.size(); i++)
			{
				if((matrix.get(i).getRow() == row) && (matrix.get(i).getColumn() == col))
				{
					matrix.remove(i);
				}
			}
		}
		
		//made a new Index object with the information of the newly added element, and add the Index object to the sparse matrix
		Index addedIndex = new Index(row, col, data);
		matrix.add(addedIndex);
		
	}

	@Override
	public void removeElement(int row, int col) {
		// TODO Auto-generated method stub
		if (row < 0 || col < 0)
		{
			System.out.println("out of bounds");
		}
		
		if (row > (numRows-1) || col > (numCols-1))
		{
			System.out.println("out of bounds");
		}
		
		//if the row and col values passed in already match an existing object in the array
		else
		{
			for (int i = 0; i < matrix.size(); i++)
			{
				if((matrix.get(i).getRow() == row) && (matrix.get(i).getColumn() == col))
				{
					matrix.remove(i);
				}
			}
		}
		
	}

	@Override
	public int getElement(int row, int col) {
		// TODO Auto-generated method stub
		if (row < 0 || col < 0)
		{
			System.out.println("out of bounds");
		}
		
		if (row > (numRows-1) || col > (numCols-1))
		{
			System.out.println("out of bounds");
		}
		
		//if the row and col values passed in already match an existing object in the array
		else
		{
			for (int i = 0; i < matrix.size(); i++)
			{
				if((matrix.get(i).getRow() == row) && (matrix.get(i).getColumn() == col))
				{
					return matrix.get(i).getData();
				}
			}
		}
		return 0;
	}

	/*
	 * Since our elements don't get sorted when we add them, we have to address the unsorted nature of the ArrayList here.
	 * We create a triple nested for loop. The outer two for loops go through the matrix row by row. We start at (0,0) go to (0,1) then (0,2) and so on.
	 * We need to print the string in order, meaning values at (0,0) come first and then (0,1). 
	 * The outer for loops for row and column go through the matrix in order. The innermost for loop loops through the indices to find an slot in the
	 * ArrayList matrix that matches the row and column values in the outer for loops. If a nonzero number occupies that space we print it out, move 
	 * onto the next slot in the matrix and iterate through the indices again.
	 */
	
	public String toString()
	{
		String matrixString = "";
		
		for (int r = 0; r < numRows; r++)
		{
			for (int c = 0; c < numCols; c++)
			{	
				for (Index n : matrix)
				{
					if (r == n.getRow() && c == n.getColumn())
					{
						matrixString = matrixString + n.getRow() + " " + n.getColumn() + " " + n.getData() + "\n";
					}
				}
			}
		}
			
		return matrixString;
	}

	//when we set the size of a new matrix we clear the old one
	@Override
	public void setSize(int numRows, int numCols) 
	{
		// TODO Auto-generated method stub
		matrix.clear();
		this.numRows = numRows;
		this.numCols = numCols;
	}

	@Override
	public int getNumRows() {
		// TODO Auto-generated method stub
		return numRows;
	}

	@Override
	public int getNumCols() {
		// TODO Auto-generated method stub
		return numCols;
	}

	@Override
	public SparseInterface addMatrices(SparseInterface matrixToAdd) 
	{
		//create a new SparseMatrix object called matrix sum to hold the result after you add both matrices
		SparseMatrix matrixSum = new SparseMatrix();
		
		/*
		 * Since matrix is an ArrayList and not a SparseMatrix, we couldn't use methods such as getNumRows() to use in the add and
		 * multiply methods. We got around this by making a SparseMatric object called matrixCopy that references the ArrayList object called matrix.
		 * This allows us to use the methods defined in the SparseMatrix class such as toString() on the matrixCopy which has the same data
		 * as the matrix ArrayList.
		 */
		SparseMatrix matrixCopy = this;
		
		//In order to add two matrices, they must have the exact same dimensions, same number of rows and columns
		// TODO Auto-generated method stub
		if (matrixCopy.getNumRows() != matrixToAdd.getNumRows())
		{
			return null;
		}
		
		if (matrixCopy.getNumCols() != matrixToAdd.getNumCols())
		{
			return null;
		}
		
		//to add, we go through the matrices in row major order
		//at each (row, column) pair we add the values from each matrix and add that sum into the matrixSum
		else
		{
			for (int r = 0; r < matrixCopy.getNumRows(); r++)
			{
				for (int c = 0; c < matrixCopy.getNumCols(); c++)
				{
					matrixSum.addElement(r, c, (matrixCopy.getElement(r, c) + matrixToAdd.getElement(r, c)));
				}
			}
		}

		return matrixSum;
	}

	
	@Override
	public SparseInterface multiplyMatrices(SparseInterface matrixToMultiply) 
	{
		// TODO Auto-generated method stub
		SparseMatrix matrixProduct = new SparseMatrix();
		
		SparseMatrix matrixCopy = this;		
		
		int numRowA = matrixCopy.getNumRows();
		int numColA = matrixCopy.getNumCols();
		int numRowB = matrixToMultiply.getNumRows();
		int numColB = matrixToMultiply.getNumCols();
		
		int i;
		int j;
		int k;
		int sum = 0; 
		
		//in order to multiply two matrices, the number of columns of the first matrix must be the same as the number of 
		//rows in the second matrix
		if (numColA != numRowB)
		{
			System.out.println("illegal matrix dimensions");
			return null;
		}
	
		
		/*
		 * To multiply, we need a triple nested for loop to iterate through the rows and columns (i and j respectively). We need k to
		 * iterate through the corresponding values once we set the row and columns
		 * For example, if we had a (2x3) and (3x1) matrix and we wanted to get the (0,0) index of the matrixProduct.
		 * we would multiply the 0th row of the first matrix by the 0th column of the second matrix
		 * 
		 * The (row,column) pairs we would multiply and add are:
		 * 
		 * (0,0)*(0,0) + (0,1)*(1,0) + (0,2)(2,0)
		 * 
		 * As you can see the i (0) and j (0) remain constant but the k (0,1,2) changes with each iteration of the inner for loop.
		 * 
		 */
		else if (numColA == numRowB)
		{
			for (i = 0; i < numRowA; i++)
			{
				for (j = 0; j < numColB; j++)
				{
					sum = 0;

					for (k = 0; k < numColA; k++)
					{
						sum += (matrixCopy.getElement(i, k) * matrixToMultiply.getElement(k, j));

					}
					matrixProduct.addElement(i, j, sum);
				}

			}
			
		}
		
		return matrixProduct;
	}
	
	public static void main(String[] args)
	{
	
    }
}

class Index
{
	private int row;
	private int col;
	private int data;
	
	public Index(int row, int col, int data)
	{
		this.row=row;
		this.col=col;
		this.data=data;
	}
	
	public int getRow()
	{
		return this.row;
	}
	
	public int getColumn()
	{
		return this.col;
	}
	
	public int getData()
	{
		return this.data;
	}
}



