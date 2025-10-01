package Pertemuan4;
public class User {
    private String name;

    // Konstruktor default
    public User() {
        this.name = "Pengguna Umum";
    }

    // Konstruktor dengan parameter nama
    public User(String name) {
        this.name = name;
    }

    // Menampilkan detail buku menggunakan polymorphism
    public void viewBookDetails(Book book) {
        System.out.println("Judul: " + book.getTitle());
        System.out.println("Penulis: " + book.getAuthor());
        System.out.println("Tersedia: " + (book.isAvailable() ? "Ya" : "Tidak"));

        if (book instanceof Novel) {
            System.out.println("Genre: " + ((Novel) book).getGenre());
        } else if (book instanceof Magazine) {
            System.out.println("Kategori: " + ((Magazine) book).getCategory());
        } else if (book instanceof Textbook) {
            System.out.println("Bidang Studi: " + ((Textbook) book).getSubject());
        }
    }

 // Meminjam buku
    public void borrowBook(Book book) {
        if (book.isAvailable()) {
            book.borrowBook();
            System.out.println("Buku \"" + book.getTitle() + "\" berhasil dipinjam oleh " + this.name);
        } else {
            System.out.println("Maaf " + this.name + ", buku \"" + book.getTitle() + "\" sedang tidak tersedia.");
        }
    }
 // Mengembalikan buku
    public void returnBook(Book book) {
        if (!book.isAvailable()) {
            book.returnBook();
            System.out.println("Buku \"" + book.getTitle() + "\" berhasil dikembalikan.");
        } else {
            System.out.println("Buku \"" + book.getTitle() + "\" sudah tersedia.");
        }
    }
}