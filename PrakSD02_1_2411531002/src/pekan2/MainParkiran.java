package pekan2;

import java.util.ArrayList;
import java.util.Scanner;

class Mobil {
    private String platNomor;

    public Mobil(String platNomor) {
        this.platNomor = platNomor;
    }

    public String getPlatNomor() {
        return platNomor;
    }
}

class Parkiran {
    private ArrayList<Mobil> daftarMobil = new ArrayList<>();

    // Menambahkan mobil ke dalam parkiran
    public void tambahMobil(String platNomor) {
        daftarMobil.add(new Mobil(platNomor));
        System.out.println("✅ Mobil dengan plat " + platNomor + " berhasil ditambahkan ke parkiran.");
    }

    // Mengeluarkan mobil berdasarkan plat nomor
    public void keluarkanMobil(String platNomor) {
        boolean ditemukan = false;
        for (int i = 0; i < daftarMobil.size(); i++) {
            if (daftarMobil.get(i).getPlatNomor().equalsIgnoreCase(platNomor)) {
                daftarMobil.remove(i);
                System.out.println("✅ Mobil dengan plat " + platNomor + " berhasil dikeluarkan.");
                ditemukan = true;
                break;
            }
        }
        if (!ditemukan) {
            System.out.println("⚠️ Mobil dengan plat " + platNomor + " tidak ditemukan di parkiran.");
        }
    }

    // Menampilkan isi parkiran
    public void tampilkanParkiran() {
        if (daftarMobil.isEmpty()) {
            System.out.println("📭 Parkiran kosong.");
        } else {
            System.out.println("🚗 Daftar mobil saat ini di parkiran:");
            for (Mobil mobil : daftarMobil) {
                System.out.println("- " + mobil.getPlatNomor());
            }
        }
    }

    // Mencari mobil di parkiran
    public void cariMobil(String platNomor) {
        for (Mobil mobil : daftarMobil) {
            if (mobil.getPlatNomor().equalsIgnoreCase(platNomor)) {
                System.out.println("🔍 Mobil dengan plat " + platNomor + " masih ada di parkiran.");
                return;
            }
        }
        System.out.println("❌ Mobil dengan plat " + platNomor + " tidak ditemukan di parkiran.");
    }
}

public class MainParkiran {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Parkiran parkiran = new Parkiran();

        // Menambahkan 7 mobil awal
        String[] platAwal = {
            "BA1111AA", "BA2222BB", "BA3333CC",
            "BA4444DD", "BA5555EE", "BA6666FF", "BA7777GG"
        };

        for (String plat : platAwal) {
            parkiran.tambahMobil(plat);
        }

        // Menu interaktif
        while (true) {
            System.out.println("\n=== Menu Parkiran Mobil ===");
            System.out.println("1. Tambah mobil ke Parkiran");
            System.out.println("2. Keluarkan mobil dari Parkiran");
            System.out.println("3. Tampilkan isi Parkiran");
            System.out.println("4. Cari mobil di Parkiran");
            System.out.println("5. Keluar");
            System.out.print("Pilih Opsi: ");
            
            int opsi;
            try {
                opsi = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Masukkan angka yang valid (1-5).");
                continue;
            }

            switch (opsi) {
                case 1:
                    System.out.print("Masukkan plat nomor mobil: ");
                    String platTambah = scanner.nextLine();
                    parkiran.tambahMobil(platTambah);
                    break;
                case 2:
                    System.out.print("Masukkan plat nomor mobil yang keluar: ");
                    String platKeluar = scanner.nextLine();
                    parkiran.keluarkanMobil(platKeluar);
                    break;
                case 3:
                    parkiran.tampilkanParkiran();
                    break;
                case 4:
                    System.out.print("Masukkan plat nomor mobil yang dicari: ");
                    String platCari = scanner.nextLine();
                    parkiran.cariMobil(platCari);
                    break;
                case 5:
                    System.out.println("👋 Terima kasih! Program selesai.");
                    scanner.close();
                    return;
                default:
                    System.out.println("⚠️ Opsi tidak valid. Silakan pilih antara 1 hingga 5.");
            }
        }
    }
}


