package Pekan9;
//Saskia Alifah
//2411531002

	public class TreeMain {
	    public static void main(String[] args) {
	        // Membuat Pohon
	        BTree tree = new BTree();
	        System.out.print("Jumlah Simpul awal pohon: ");
	        System.out.println(tree.countNodes());

	        // Menambahkan simpul data 1
	        Node root = new Node(1);

	        // Menjadikan simpul 1 sebagai root
	        tree.setRoot(root);
	        System.out.println("Jumlah simpul jika hanya ada root:");
	        System.out.println(tree.countNodes());

	        // Membuat simpul lainnya
	        Node node2 = new Node(2);
	        Node node3 = new Node(3);
	        Node node4 = new Node(4);
	        Node node5 = new Node(5);
	        Node node6 = new Node(6);
	        Node node7 = new Node(7);

	        // Menyusun struktur pohon
	        root.setLeft(node2);
	        root.setRight(node3);
	        node2.setLeft(node4);
	        node2.setRight(node5);
	        node3.setLeft(node6);
	        node3.setRight(node7);

	        // Set current node ke root
	        tree.setCurrent(tree.getRoot());

	        System.out.println("Menampilkan simpul terakhir:");
	        System.out.println(tree.getCurrent().getData());

	        System.out.println("Jumlah simpul setelah simpul 7 ditambahkan:");
	        System.out.println(tree.countNodes());

	        System.out.println("InOrder: ");
	        tree.printInorder();

	        System.out.println("\nPreOrder: ");
	        tree.printPreOrder();

	        System.out.println("\nPostOrder: ");
	        tree.printPostOrder();

	        System.out.println("\nMenampilkan simpul dalam bentuk pohon:");
	        tree.print();
        
	        System.out.println("\nNama: Saskia Alifah");
	        System.out.println("NIM: 2411531002");
	}
}