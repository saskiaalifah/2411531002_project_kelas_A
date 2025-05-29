package Pekan6;

//Double Linked List untuk menyimpan dan mengelola daftar belanja
public class DaftarBelanja {
 private Node head;
 private Node tail;

 // Menambahkan item baru ke akhir daftar
 public void tambahItem(String nama, int kuantitas, String kategori) {
     ItemBelanja item = new ItemBelanja(nama, kuantitas, kategori);
     Node newNode = new Node(item);

     if (head == null) {
         head = tail = newNode;
     } else {
         tail.next = newNode;
         newNode.prev = tail;
         tail = newNode;
     }

     System.out.println("\n✅ Item '" + nama + "' berhasil ditambahkan!\n");
 }

 // Menghapus item berdasarkan nama
 public void hapusItem(String nama) {
     Node current = head;
     while (current != null) {
         if (current.data.nama.equalsIgnoreCase(nama)) {
             if (current == head && current == tail) {
                 head = tail = null;
             } else if (current == head) {
                 head = head.next;
                 head.prev = null;
             } else if (current == tail) {
                 tail = tail.prev;
                 tail.next = null;
             } else {
                 current.prev.next = current.next;
                 current.next.prev = current.prev;
             }
             System.out.println("\n🗑️ Item '" + nama + "' berhasil dihapus.\n");
             return;
         }
         current = current.next;
     }
     System.out.println("\n❌ Item tidak ditemukan.\n");
 }

 // Menampilkan seluruh item belanja
 public void tampilkanSemuaItem() {
     if (head == null) {
         System.out.println("\n📭 Daftar belanja kosong.\n");
         return;
     }
     System.out.println("\n🛒 --- Daftar Belanja ---");
     Node current = head;
     while (current != null) {
         ItemBelanja item = current.data;
         System.out.println("- " + item.nama + " (" + item.kuantitas + ") [" + item.kategori + "]");
         current = current.next;
     }
     System.out.println();
 }

 // Menampilkan item berdasarkan kategori
 public void tampilkanPerKategori(String kategori) {
     Node current = head;
     boolean ditemukan = false;

     System.out.println("\n📂 --- Item dalam kategori '" + kategori + "' ---");
     while (current != null) {
         if (current.data.kategori.equalsIgnoreCase(kategori)) {
             System.out.println("- " + current.data.nama + " (" + current.data.kuantitas + ")");
             ditemukan = true;
         }
         current = current.next;
     }

     if (!ditemukan) {
         System.out.println("Tidak ada item dalam kategori ini.");
     }
     System.out.println();
 }

 // Mencari item berdasarkan nama
 public void cariItem(String nama) {
     Node current = head;
     while (current != null) {
         if (current.data.nama.equalsIgnoreCase(nama)) {
             ItemBelanja item = current.data;
             System.out.println("\n🔍 Item ditemukan: " + item.nama + " (" + item.kuantitas + ") [" + item.kategori + "]\n");
             return;
         }
         current = current.next;
     }
     System.out.println("\n❌ Item tidak ditemukan.\n");
 }
}

