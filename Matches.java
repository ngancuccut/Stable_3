package Trip_Matching_Theory;
import java.util.*;
public class Matches {
    private Map<Integer, Set<Integer>> matches;
    private Set<Integer> leftOvers;
    private int totalSize;

    public Matches(int totalSize) {
        this.totalSize = totalSize;
        this.matches = new HashMap<>();
        this.leftOvers = new HashSet<>();
    }

    public void addMatch(int leftNode, int rightNode) {
        matches.putIfAbsent(leftNode, new HashSet<>());
        matches.get(leftNode).add(rightNode);
    }

    public void disMatch(int leftNode, int rightNode) {
        if (matches.containsKey(leftNode)) {
            matches.get(leftNode).remove(rightNode);
            if (matches.get(leftNode).isEmpty()) {
                matches.remove(leftNode);
            }
        }
    }

    public boolean isAlreadyMatch(int leftNode, int rightNode) {
        return matches.containsKey(leftNode) && matches.get(leftNode).contains(rightNode);
    }

    public boolean isFull(int node, int capacity) {
        return matches.containsKey(node) && matches.get(node).size() >= capacity;
    }

    public List<Integer> getIndividualMatches(int node) {
        return new ArrayList<>(matches.getOrDefault(node, new HashSet<>()));
    }

    public void addLeftOver(int node) {
        leftOvers.add(node);
    }

    public Set<Integer> getLeftOvers() {
        return leftOvers;
    }

    public int getTotalSize() {
        return totalSize;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Set<Integer>> entry : matches.entrySet()) {
            sb.append("Node ").append(entry.getKey()).append(": Matches -> ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}
