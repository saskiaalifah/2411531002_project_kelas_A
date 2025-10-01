package Pertemuan4;

public class Textbook extends Book {
    private String subject;

    public Textbook(String title, String author, String subject) {
        super(title, author);
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }
}
