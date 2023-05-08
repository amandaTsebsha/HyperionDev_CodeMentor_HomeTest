package SECTION_C;

import java.util.*;


public class RoadNavigation {

    public static Map<Integer, Integer> dijkstra(Map<Integer, List<Edge>> graph, int start) {
        Map<Integer, Integer> dist = new HashMap<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Set<Integer> visited = new HashSet<>();

        for (int node : graph.keySet()) {
            dist.put(node, Integer.MAX_VALUE);
        }
        dist.put(start, 0);
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge curr = pq.remove();
            int currNode = curr.getTo();
            int currDist = curr.getWeight();

            if (visited.contains(currNode)) {
                continue;
            }
            visited.add(currNode);

            for (Edge neighbor : graph.get(currNode)) {
                int neighborNode = neighbor.getTo();
                int neighborDist = neighbor.getWeight();
                int newDist = currDist + neighborDist;

                if (newDist < dist.get(neighborNode)) {
                    dist.put(neighborNode, newDist);
                    pq.add(new Edge(neighborNode, newDist));
                }
            }
        }

        return dist;
    }

    public static Map<String, Object> navigate(Map<String, Object> roads, int start, int end) {
        Map<Integer, List<Edge>> graph = new HashMap<>();
        List<Integer> path = new ArrayList<>();
        int distance = 0;

        for (Map<String, Object> node : (List<Map<String, Object>>) roads.get("graph.nodes")) {
            graph.put((int) node.get("id"), new ArrayList<>());
        }
        for (Map<String, Object> edge : (List<Map<String, Object>>) roads.get("graph.edges")) {
            int from = (int) edge.get("source");
            int to = (int) edge.get("target");
            int weight = (int) ((Map<String, Object>) edge.get("metadata")).get("distance");

            graph.get(from).add(new Edge(to, weight));
            graph.get(to).add(new Edge(from, weight));
        }

        Map<Integer, Integer> dist = dijkstra(graph, start);
        int currNode = end;

        while (currNode != start) {
            path.add(currNode);
            for (Edge neighbor : graph.get(currNode)) {
                int neighborNode = neighbor.getTo();
                int neighborDist = neighbor.getWeight();
                int newDist = dist.get(currNode) - neighborDist;

                if (newDist == dist.get(neighborNode)) {
                    distance += neighborDist;
                    currNode = neighborNode;
                    break;
                }
            }
        }
        path.add(start);
        Collections.reverse(path);

        Map<String, Object> result = new HashMap<>();
        result.put("distance", distance);
        result.put("path", path);

        return result;
    }

    public static void main(String[] args) {
        Map<String, Object> roads = new HashMap<>();
        Map<String, Object> graph = new HashMap<>();
        List<Map<String, Object>> nodes = new ArrayList<>();
        List<Map<String, Object>> edges = new ArrayList<>();

        nodes.add(Map.of("id", 0));
        nodes.add(Map.of("id", 1));
        nodes.add(Map.of("id", 2));
        nodes.add(Map.of("id", 3));
        nodes.add(Map.of("id", 4));

        edges.add(Map.of("source", 0, "target", 1, "metadata", Map.of("distance", 5)));
        edges.add(Map.of("source", 1, "target", 2, "metadata", Map.of("distance", 3)));
        edges.add(Map.of("source", 2, "target", 3, "metadata", Map.of("distance", 7)));
        edges.add(Map.of("source", 3, "target", 4, "metadata", Map.of("distance", 2)));

        graph.put("nodes", nodes);
        graph.put("edges", edges);
        roads.put("graph", graph);

        System.out.println(roads);
    }}
