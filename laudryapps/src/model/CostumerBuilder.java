package model;

public class CostumerBuilder {

    private int id;
    private String nama;
    private String email = "";   // default value
    private String alamat;
    private String hp;

    public CostumerBuilder() {}

    public CostumerBuilder setId(int i) {
        this.id = i;
        return this;
    }

    public CostumerBuilder setNama(String nama) {
        this.nama = nama;
        return this;
    }

    public CostumerBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public CostumerBuilder setAlamat(String alamat) {
        this.alamat = alamat;
        return this;
    }

    public CostumerBuilder setHp(String hp) {
        this.hp = hp;
        return this;
    }

    // Build sesuai teori dosen
    public Costumer build() {
        return new Costumer(id, nama, email, alamat, hp);
    }
}
