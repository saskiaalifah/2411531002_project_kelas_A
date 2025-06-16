package Pekan8;

//Nama: Saskia Alifah
//NIM: 2411531002

public class TugasSortingLanjutan {

public static void bubbleSort(int[] arr) {
	System.out.println("\nNama: Saskia Alifah");
	System.out.println("NIM: 2411531002");
	int n = arr.length;
	int langkah = 1;
    System.out.println("\nAlgoritma: Bubble Sort");
    for (int i = 0; i < n; i++) {
    	  for (int j = 0; j < n - i - 1; j++ ) {
    	      if (arr[j] < arr[j + 1]) { 
    	          int temp = arr[j];
    	          arr[j] = arr[j + 1];
    	          arr[j + 1] = temp;

    	          System.out.print("Langkah " + langkah++ + ": ");
    	          printArray(arr);
    	       }
		}
	}
}
public static void printArray(int[] arr) {
    System.out.print("[");
    for (int i = 0; i < arr.length; i++) {
       System.out.print(arr[i]);
       if (i < arr.length - 1) System.out.print(", ");
    }
    System.out.println("]");
}

public static void main (String[] args) {
    int arr[] = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47};
    int n = arr.length;

    System.out.print("Deret awal: ");
    printArray(arr);

    bubbleSort(arr);

    System.out.print("\nHasil akhir: ");
    printArray(arr);
 	}
}
