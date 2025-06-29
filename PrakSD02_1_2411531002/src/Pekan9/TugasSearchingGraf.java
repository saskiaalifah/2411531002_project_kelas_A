package Pekan9;

import java.util.*;

public class TugasSearchingGraf {

    private static final String NAMA = "Saskia Alifah";
    private static final String NIM  = "2411531002";

    private static final Map<String, List<String>> graph = new HashMap<>();
    static {
        graph.put("A", Arrays.asList("B", "C"));
        graph.put("B", Arrays.asList("A", "D", "E"));
        graph.put("C", Arrays.asList("A", "F"));
        graph.put("D", Arrays.asList("B"));
        graph.put("E", Arrays.asList("B", "F", "H"));
        graph.put("F", Arrays.asList("C", "E", "G"));
        graph.put("G", Arrays.asList("F"));
        graph.put("H", Arrays.asList("E"));
    }

    public static void search(String startNode, String goalNode) {
        Set<String> visited = new HashSet<>();
        Queue<List<String>> queue = new LinkedList<>();
        queue.offer(Arrays.asList(startNode));
        int langkah = 1;

        System.out.println("Nama: " + NAMA);
        System.out.println("NIM: " + NIM);
        System.out.println("\nNode awal: " + startNode);
        System.out.println("Node tujuan: " + goalNode);
        System.out.println("Algoritma: BFS");
        System.out.println();

        while (!queue.isEmpty()) {
            List<String> path = queue.poll();
            String current = path.get(path.size() - 1);

            if (!visited.contains(current)) {
                System.out.println("Langkah " + langkah++ + ": Kunjungi " + current);
                visited.add(current);

                if (current.equals(goalNode)) {
                    System.out.println();
                    System.out.println("Tujuan " + goalNode + " ditemukan");
                    System.out.println("Rute: " + String.join(" → ", path));
                    return;
                }

                for (String neighbor : graph.getOrDefault(current, new ArrayList<>())) {
                    if (!visited.contains(neighbor)) {
                        List<String> newPath = new ArrayList<>(path);
                        newPath.add(neighbor);
                        queue.offer(newPath);
                    }
                }
            }
        }

        System.out.println("Node tujuan tidak ditemukan.");
    }

    public static void main(String[] args) {
        search("A", "G");
    }
}