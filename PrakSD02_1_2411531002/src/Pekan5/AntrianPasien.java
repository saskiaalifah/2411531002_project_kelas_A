package Pekan5;
import java.util.Scanner;

public class AntrianPasien {

    // Node untuk menyimpan data pasien
    private static class Node {
        int noAntrian;
        String nama;
        String keluhan;
        Node next;

        Node(int noAntrian, String nama, String keluhan) {
            this.noAntrian = noAntrian;
            this.nama = nama;
            this.keluhan = keluhan;
            this.next = null;
        }
    }

    private Node head;

    // Tambah pasien ke akhir antrian
    public void tambahPasien(int noAntrian, String nama, String keluhan) {
        Node newNode = new Node(noAntrian, nama, keluhan);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("\nData pasien berhasil ditambahkan!");
    }

    // Tampilkan semua antrian
    public void tampilkanAntrian() {
        if (isEmpty()) {
            System.out.println("\nAntrian kosong.");
            return;
        }
        System.out.println("\n--- Daftar Antrian Pasien ---");
        Node current = head;
        int index = 1;
        while (current != null) {
            System.out.println(index + ". [" + current.noAntrian + "] " + current.nama + " - " + current.keluhan);
            current = current.next;
            index++;
        }
    }

    // Hapus pasien di depan antrian
    public void hapusPasienPertama() {
        if (isEmpty()) {
            System.out.println("\nAntrian kosong. Tidak ada pasien yang bisa dilayani.");
        } else {
            System.out.println("\nPasien dengan nama " + head.nama + " telah dilayani (dihapus dari antrian).");
            head = head.next;
        }
    }

    // Cari pasien berdasarkan nama
    public void cariPasien(String nama) {
        Node current = head;
        boolean ditemukan = false;
        while (current != null) {
            if (current.nama.equalsIgnoreCase(nama)) {
                System.out.println("\nPasien ditemukan: [" + current.noAntrian + "] " + current.nama + " - " + current.keluhan);
                ditemukan = true;
                break; // stop setelah ketemu
            }
            current = current.next;
        }
        if (!ditemukan) {
            System.out.println("\nPasien dengan nama " + nama + " tidak ditemukan dalam antrian.");
        }
    }

    // Cek apakah antrian kosong
    public boolean isEmpty() {
        return head == null;
    }

    // Hitung jumlah pasien dalam antrian
    public int hitungPasien() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    // Program interaktif
    public static void main(String[] args) {
        AntrianPasien antrian = new AntrianPasien();
        Scanner scanner = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n=== SISTEM ANTRIAN PASIEN KLINIK ===");
            System.out.println("1. Tambah Pasien");
            System.out.println("2. Tampilkan Antrian");
            System.out.println("3. Layani Pasien (Hapus Antrian Pertama)");
            System.out.println("4. Cari Pasien");
            System.out.println("5. Jumlah Pasien");
            System.out.println("6. Keluar");
            System.out.print("Pilih menu: ");
            
            try {
                pilihan = Integer.parseInt(scanner.nextLine());

                switch (pilihan) {
                    case 1:
                        System.out.print("Masukkan Nomor Antrian: ");
                        int no = Integer.parseInt(scanner.nextLine());
                        System.out.print("Masukkan Nama Pasien: ");
                        String nama = scanner.nextLine();
                        System.out.print("Masukkan Keluhan: ");
                        String keluhan = scanner.nextLine();
                        antrian.tambahPasien(no, nama, keluhan);
                        break;
                    case 2:
                        antrian.tampilkanAntrian();
                        break;
                    case 3:
                        antrian.hapusPasienPertama();
                        break;
                    case 4:
                        System.out.print("Masukkan Nama Pasien yang Dicari: ");
                        String cari = scanner.nextLine();
                        antrian.cariPasien(cari);
                        break;
                    case 5:
                        System.out.println("\nJumlah pasien saat ini: " + antrian.hitungPasien());
                        break;
                    case 6:
                        System.out.println("\nTerima kasih telah menggunakan sistem antrian.");
                        break;
                    default:
                        System.out.println("\nPilihan tidak valid. Silakan pilih antara 1 hingga 6.");
                }

            } catch (NumberFormatException e) {
                System.out.println("\nInput tidak valid. Harus berupa angka.");
                pilihan = 0; // ulang menu
            }

        } while (pilihan != 6);

        scanner.close();
    }
}
