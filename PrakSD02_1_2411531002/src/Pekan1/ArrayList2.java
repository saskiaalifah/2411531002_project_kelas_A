package Pekan1;

import java.util.ArrayList;

public class ArrayList2 {

	public static void main(String[] args) {
	
			// Size of the arraylist
			int n=5;
			//Declaring the arraylist with initial size n
			ArrayList<Integer> arrli = new ArrayList<Integer>(n);
			//Appending new elements at the end of the list
			for (int i = 0; i <= n; i++)
			arrli.add(i);
			//Printing elements
			System.out.println(arrli);
			//remove element at index 3
			arrli.remove(3);
			//displaying the arraylist
			//after deletion
			System.out.println(arrli);
			//printing elements one by one
			for (int i = 0; i < arrli.size(); i++)
			System.out.print(arrli.get(i) + " ");


	}

}
