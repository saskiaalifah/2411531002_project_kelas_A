package Pekan6;

import java.util.Scanner;

//Main program interaktif dengan menu
public class Main {
 public static void main(String[] args) {
     DaftarBelanja daftar = new DaftarBelanja();
     Scanner scanner = new Scanner(System.in);
     int pilihan;

     do {
         System.out.println("=== MENU DAFTAR BELANJA ===");
         System.out.println("1. Tambah Item");
         System.out.println("2. Hapus Item");
         System.out.println("3. Tampilkan Semua Item");
         System.out.println("4. Tampilkan Item Berdasarkan Kategori");
         System.out.println("5. Cari Item");
         System.out.println("6. Keluar");
         System.out.print("Pilih menu: ");

         while (!scanner.hasNextInt()) {
             System.out.println("⚠️ Masukkan angka 1-6!");
             scanner.next(); // skip input yang tidak valid
             System.out.print("Pilih menu: ");
         }

         pilihan = scanner.nextInt();
         scanner.nextLine(); // mengonsumsi newline

         switch (pilihan) {
             case 1:
                 System.out.print("Masukkan nama item: ");
                 String nama = scanner.nextLine().trim();
                 System.out.print("Masukkan kuantitas: ");
                 while (!scanner.hasNextInt()) {
                     System.out.println("⚠️ Kuantitas harus angka!");
                     scanner.next(); // skip
                     System.out.print("Masukkan kuantitas: ");
                 }
                 int qty = scanner.nextInt();
                 scanner.nextLine();
                 System.out.print("Masukkan kategori: ");
                 String kategori = scanner.nextLine().trim();
                 daftar.tambahItem(nama, qty, kategori);
                 break;

             case 2:
                 System.out.print("Masukkan nama item yang ingin dihapus: ");
                 String hapusNama = scanner.nextLine().trim();
                 daftar.hapusItem(hapusNama);
                 break;

             case 3:
                 daftar.tampilkanSemuaItem();
                 break;

             case 4:
                 System.out.print("Masukkan kategori: ");
                 String filterKategori = scanner.nextLine().trim();
                 daftar.tampilkanPerKategori(filterKategori);
                 break;

             case 5:
                 System.out.print("Masukkan nama item: ");
                 String cariNama = scanner.nextLine().trim();
                 daftar.cariItem(cariNama);
                 break;

             case 6:
                 System.out.println("👋 Terima kasih telah menggunakan program.");
                 break;

             default:
                 System.out.println("⚠️ Pilihan tidak valid. Silakan pilih 1-6.");
         }

     } while (pilihan != 6);

     scanner.close();
 }
}
