package model;

import java.util.Date;

public class Order {
    private String id;
    private String idCostumer;
    private String idService;
    private String idUser;
    private double total;
    private Date tanggal;
    private Date tanggalSelesai;
    private String status;
    private String statusPembayaran;

    // Getter & Setter
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getIdCostumer() {
        return idCostumer;
    }
    public void setIdCostumer(String idCostumer) {
        this.idCostumer = idCostumer;
    }

    public String getIdService() {
        return idService;
    }
    public void setIdService(String idService) {
        this.idService = idService;
    }

    public String getIdUser() {
        return idUser;
    }
    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }

    public Date getTanggal() {
        return tanggal;
    }
    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public Date getTanggalSelesai() {
        return tanggalSelesai;
    }
    public void setTanggalSelesai(Date tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusPembayaran() {
        return statusPembayaran;
    }
    public void setStatusPembayaran(String statusPembayaran) {
        this.statusPembayaran = statusPembayaran;
    }
}