package task3_6;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Branch {
    private List<Leaf> leaves;

    public Branch() {
        leaves = new ArrayList<>();
    }

    public void addLeaf(Leaf leaf) throws DataProcessingException {
        if (leaf == null) {
            throw new DataProcessingException("Leaf cannot be null.");
        }
        leaves.add(leaf);
    }

    public void removeLeaf(Leaf leaf) throws DataProcessingException {
        if (leaf == null) {
            throw new DataProcessingException("Leaf cannot be null.");
        }
        if (!leaves.contains(leaf)) {
            throw new DataProcessingException("Leaf not found in branch.");
        }
        leaves.remove(leaf);
    }

    public void bloomLeaves() {
        for (Leaf leaf : leaves) {
            leaf.bloom();
        }
    }

    public void witherLeaves() {
        for (Leaf leaf : leaves) {
            leaf.wither();
        }
    }

    public void frostLeaves() {
        for (Leaf leaf : leaves) {
            leaf.frost();
        }
    }

    public void yellowLeaves() {
        for (Leaf leaf : leaves) {
            leaf.yellow();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Branch branch = (Branch) o;
        return Objects.equals(leaves, branch.leaves);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leaves);
    }

    @Override
    public String toString() {
        return "Branch{" +
                "leaves=" + leaves +
                '}';
    }
}
