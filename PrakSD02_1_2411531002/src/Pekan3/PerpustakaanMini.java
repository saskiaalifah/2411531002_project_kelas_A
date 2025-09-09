package Pekan3;

import java.util.Scanner;
import java.util.Stack;

// Kelas Buku
class Buku {
    private String judul;

    public Buku(String judul) {
        this.judul = judul;
    }

    public String getJudul() {
        return judul;
    }
}

// Kelas utama: PerpustakaanMini
public class PerpustakaanMini {
    private Stack<Buku> tumpukanBuku = new Stack<>();
    private Scanner scanner = new Scanner(System.in);

    // Tambahkan buku ke tumpukan
    public void tambahBuku(String judul) {
        Buku buku = new Buku(judul);
        tumpukanBuku.push(buku);
        System.out.println("✅ Buku '" + judul + "' berhasil ditambahkan ke tumpukan.");
    }

    // Ambil buku paling atas
    public void ambilBuku() {
        if (tumpukanBuku.isEmpty()) {
            System.out.println("⚠️ Tumpukan kosong. Tidak ada buku yang bisa diambil.");
        } else {
            Buku buku = tumpukanBuku.pop();
            System.out.println("📕 Buku diambil: " + buku.getJudul());
        }
    }

    // Lihat seluruh isi tumpukan
    public void lihatTumpukan() {
        if (tumpukanBuku.isEmpty()) {
            System.out.println("📭 Tumpukan kosong.");
        } else {
            System.out.println("📚 Tumpukan Buku Saat Ini :");
            for (int i = tumpukanBuku.size() - 1; i >= 0; i--) {
                System.out.println("- " + tumpukanBuku.get(i).getJudul());
            }
        }
    }

    // Cari apakah buku tertentu ada di tumpukan
    public void cariBuku(String judul) {
        boolean ditemukan = false;
        for (Buku buku : tumpukanBuku) {
            if (buku.getJudul().equalsIgnoreCase(judul)) {
                ditemukan = true;
                break;
            }
        }
        if (ditemukan) {
            System.out.println("🔍 Buku '" + judul + "' ditemukan di dalam tumpukan.");
        } else {
            System.out.println("❌ Buku '" + judul + "' tidak ditemukan.");
        }
    }

    // Tambahkan 7 buku awal
    private void inisialisasiTumpukan() {
        tambahBuku("Algoritma Dasar");
        tambahBuku("Struktur Data");
        tambahBuku("Basis Data");
        tambahBuku("Pemrograman Java");
        tambahBuku("Jaringan Komputer");
        tambahBuku("Sistem Operasi");
    }

    // Jalankan menu interaktif
    public void jalankan() {
        inisialisasiTumpukan();
        int pilihan;
        do {
            System.out.println("\n=== MENU PERPUSTAKAAN MINI ===");
            System.out.println("1. Tambah Buku ke Tumpukan");
            System.out.println("2. Ambil Buku Teratas");
            System.out.println("3. Lihat Tumpukan Buku");
            System.out.println("4. Cari Buku");
            System.out.println("5. Keluar");
            System.out.print("Pilihan: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Masukkan angka 1-5!");
                scanner.next(); // buang input invalid
            }
            pilihan = scanner.nextInt();
            scanner.nextLine(); // buang newline

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan judul buku: ");
                    String judul = scanner.nextLine();
                    tambahBuku(judul);
                    break;
                case 2:
                    ambilBuku();
                    break;
                case 3:
                    lihatTumpukan();
                    break;
                case 4:
                    System.out.print("Masukkan judul buku yang dicari: ");
                    String cari = scanner.nextLine();
                    cariBuku(cari);
                    break;
                case 5:
                    System.out.println("👋 Terima kasih telah menggunakan Perpustakaan Mini!");
                    break;
                default:
                    System.out.println("❗ Pilihan tidak valid. Coba lagi.");
            }
        } while (pilihan != 5);
    }

    // Main method
    public static void main(String[] args) {
        PerpustakaanMini app = new PerpustakaanMini();
        app.jalankan();
    }
}



