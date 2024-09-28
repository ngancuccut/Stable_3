package Trip_Matching_Theory;

public class Pr_List {
    private  double[] scores;
    private  int[] positions;
    private int current;
    private  int padding;

    public Pr_List(int size, int padding) {
        this.scores = new double[size];
        this.positions = new int[size];
        this.current = 0;
        this.padding = padding;
    }

    public int size() {
        return current; // Trả về số lượng cá thể đã được thêm
    }

    public int getLeastPreferredNode(int newNode, Integer[] currentNodes) {
        int leastNode = newNode - this.padding;
        for (int currentNode : currentNodes) {
            if (this.scores[leastNode] > this.scores[currentNode - this.padding]) {
                leastNode = currentNode - this.padding;
            }
        }
        return leastNode + this.padding;
    }

    public boolean isScoreGreater(int node, int nodeToCompare) {
        return this.scores[node - this.padding] > this.scores[nodeToCompare - this.padding];
    }

    public int getIndexByPosition(int position) {
        if (position < 0 || position >= current) {
            System.err.println("Position " + position + " not found.");
            return -1;
        }
        return positions[position] + this.padding;
    }

    public void add(double score) {
        this.scores[current] = score;
        this.positions[current] = current;
        current++;
    }

    public void sort() {
        sortDescendingByScores();
    }

    private void sortDescendingByScores() {
        double[] cloneScores = scores.clone();
        int size = cloneScores.length;

        // Build min heap
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(cloneScores, size, i);
        }

        // Extract elements from heap one by one
        for (int i = size - 1; i > 0; i--) {
            double tempScore = cloneScores[0];
            int tempPos = positions[0];

            cloneScores[0] = cloneScores[i];
            positions[0] = positions[i];

            cloneScores[i] = tempScore;
            positions[i] = tempPos;

            heapify(cloneScores, i, 0);
        }
    }

    private void heapify(double[] array, int heapSize, int rootIndex) {
        int smallestIndex = rootIndex;
        int leftChildIndex = 2 * rootIndex + 1;
        int rightChildIndex = 2 * rootIndex + 2;

        if (leftChildIndex < heapSize && array[leftChildIndex] < array[smallestIndex]) {
            smallestIndex = leftChildIndex;
        }

        if (rightChildIndex < heapSize && array[rightChildIndex] < array[smallestIndex]) {
            smallestIndex = rightChildIndex;
        }

        if (smallestIndex != rootIndex) {
            double swap = array[rootIndex];
            int posSwap = positions[rootIndex];

            array[rootIndex] = array[smallestIndex];
            positions[rootIndex] = positions[smallestIndex];

            array[smallestIndex] = swap;
            positions[smallestIndex] = posSwap;

            heapify(array, heapSize, smallestIndex);
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        for (int i = 0; i < current; i++) {
            int pos = positions[i];
            result.append("[")
                    .append(pos + padding)
                    .append(" -> ")
                    .append(scores[pos])
                    .append("]");
            if (i < current - 1) result.append(", ");
        }
        result.append("}");
        return result.toString();
    }

    public double getScoreByIndex(int index) {
        return scores[index - this.padding];
    }

}
