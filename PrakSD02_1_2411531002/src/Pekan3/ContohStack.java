package Pekan3;

import java.util.*;
public class ContohStack {

	public static void main(String[] args) {
		ArrayStack test = new ArrayStack();
		Integer[] a= {4, 8, 15, 16, 23, 42};  //autoboxing allows this
		for (int i = 0; i< a.length; i++) {
			System.out.println("nilai A "+i+" = "+ a[i]);
			test.push(a[i]);
			
		}
		System.out.println("size stacknya : "+test.size());
		System.out.println("paling atas : "+test.top());
		System.out.println("nilainya "+test.pop());

	}

}
