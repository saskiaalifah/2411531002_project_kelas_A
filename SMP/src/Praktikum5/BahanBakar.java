package Praktikum5;

public interface BahanBakar {
    String jenisBahanBakar();

    default void infoKonsumsi() {
        System.out.println("Info Konsumsi: Konsumsi bahan bakar tergantung kapasitas mesin");
    }
}