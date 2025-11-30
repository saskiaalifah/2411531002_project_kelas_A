package Praktikum9;

public class DownloadLambdaApp {
    public static void main(String[] args) {

        System.out.println("Downloading...");

        // Thread 1 menggunakan lambda
        Thread file1 = new Thread(() -> {
            for (int i = 10; i <= 100; i += 10) {
                System.out.println("File-1 progress: " + i + "%");
                try {
                    Thread.sleep(500); // simulasi download
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("File-1 selesai diunduh!");
        });

        // Thread 2 menggunakan lambda
        Thread file2 = new Thread(() -> {
            for (int i = 10; i <= 100; i += 10) {
                System.out.println("File-2 progress: " + i + "%");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("File-2 selesai diunduh!");
        });

        // Thread 3 menggunakan lambda
        Thread file3 = new Thread(() -> {
            for (int i = 10; i <= 100; i += 10) {
                System.out.println("File-3 progress: " + i + "%");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("File-3 selesai diunduh!");
        });

        // Jalankan secara paralel
        file1.start();
        file2.start();
        file3.start();

        // Tunggu semua selesai
        try {
            file1.join();
            file2.join();
            file3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Semua selesai
        System.out.println("\nSemua file selesai diunduh!\n");

        // Status thread akhir
        System.out.println("Status akhir:");
        System.out.println("Thread-0: " + file1.getState()); // TERMINATED
        System.out.println("Thread-1: " + file2.getState()); // TERMINATED
        System.out.println("Thread-2: " + file3.getState()); // TERMINATED
    }
}
