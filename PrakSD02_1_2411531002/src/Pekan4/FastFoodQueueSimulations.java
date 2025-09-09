package Pekan4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Pelanggan {
    String id;
    int jumlahPesanan;
    int waktuPerPesanan;

    public Pelanggan(String id, int jumlahPesanan, int waktuPerPesanan) {
        this.id = id;
        this.jumlahPesanan = jumlahPesanan;
        this.waktuPerPesanan = waktuPerPesanan;
    }

    public int getTotalWaktu() {
        return jumlahPesanan * waktuPerPesanan;
    }
}

public class FastFoodQueueSimulations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Queue<Pelanggan> antrian = new LinkedList<>();

        System.out.print("Masukkan jumlah pelanggan: ");
        int n = Integer.parseInt(scanner.nextLine().trim());

        System.out.println("\nMasukkan data pelanggan:");
        for (int i = 0; i < n; i++) {
            System.out.print("Pelanggan ke-" + (i + 1) + " (format: ID jumlahPesanan): ");
            String[] data = scanner.nextLine().trim().split(" ");
            if (data.length < 2) {
                System.out.println("Input salah! Harus dalam format: ID jumlahPesanan");
                i--;
                continue;
            }

            String id = data[0];
            int jumlahPesanan;
            try {
                jumlahPesanan = Integer.parseInt(data[1]);
            } catch (NumberFormatException e) {
                System.out.println("Jumlah pesanan harus berupa angka!");
                i--;
                continue;
            }

            int waktuPerPesanan = 1; 
            antrian.add(new Pelanggan(id, jumlahPesanan, waktuPerPesanan));
        }

        System.out.println("\nHasil pemrosesan antrian:");
        int waktuKumulatif = 0;
        while (!antrian.isEmpty()) {
            Pelanggan p = antrian.poll();
            int waktuPelanggan = p.getTotalWaktu();
            waktuKumulatif += waktuPelanggan;
            System.out.println(p.id + " selesai dalam " + waktuKumulatif + " menit");
        }

        scanner.close();
    }
}
