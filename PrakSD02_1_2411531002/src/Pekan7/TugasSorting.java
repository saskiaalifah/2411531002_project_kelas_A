package Pekan7;

public class TugasSorting {
	//Nama : Saskia Alifah
	//Nim  : 2411531002
	
    // Selection Sort untuk mengurutkan sebagian elemen array secara ascending
    public static void selectionSort(char[] arr, int range) {
        for (int i = 0; i < range - 1; i++) {
            int min = i;
            for (int j = i + 1; j < range; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            char temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
    }

    public static void main(String[] args) {
        // Array dari z ke a
        char[] data = {
            'z', 'y', 'x', 'w', 'v', 'u', 't', 's', 'r', 'q',
            'p', 'o', 'n', 'm', 'l', 'k', 'j', 'i', 'h', 'g',
            'f', 'e', 'd', 'c', 'b', 'a'
        };

        // Sorting 2 elemen pertama karena SNBP & NIM akhir 02
        selectionSort(data, 2);

        // Tampilkan array dengan format " - "
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i]);
            if (i < data.length - 1) System.out.print(" - ");
        }

        // Identitas 
        System.out.println("\n\nNama : Saskia Alifah");
        System.out.println("Nim  : 2411531002");
    }
}
