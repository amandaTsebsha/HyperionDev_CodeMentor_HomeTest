package SECTION_C;

import static SECTION_C.RoadNavigation.*;
import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.Test;

public class RoadNavigationTest {

    @Test
    public void testNavigate() {
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

        Map<String, Object> result = navigate(roads, 0, 4);
        assertEquals(17, result.get("distance"));
        assertEquals(Arrays.asList(0, 1, 2, 3, 4), result.get("path"));
    }
}
