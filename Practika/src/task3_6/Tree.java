package task3_6;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Tree {
    private List<Branch> branches;

    public Tree() {
        branches = new ArrayList<>();
    }

    public void addBranch(Branch branch) throws DataProcessingException {
        if (branch == null) {
            throw new DataProcessingException("Branch cannot be null.");
        }
        branches.add(branch);
    }

    public void removeBranch(Branch branch) throws DataProcessingException {
        if (branch == null) {
            throw new DataProcessingException("Branch cannot be null.");
        }
        if (!branches.contains(branch)) {
            throw new DataProcessingException("Branch not found in tree.");
        }
        branches.remove(branch);
    }

    public void bloomAllLeaves() {
        for (Branch branch : branches) {
            branch.bloomLeaves();
        }
    }

    public void witherAllLeaves() {
        for (Branch branch : branches) {
            branch.witherLeaves();
        }
    }

    public void frostAllLeaves() {
        for (Branch branch : branches) {
            branch.frostLeaves();
        }
    }

    public void yellowAllLeaves() {
        for (Branch branch : branches) {
            branch.yellowLeaves();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tree tree = (Tree) o;
        return Objects.equals(branches, tree.branches);
    }

    @Override
    public int hashCode() {
        return Objects.hash(branches);
    }

    @Override
    public String toString() {
        return "Tree{" +
                "branches=" + branches +
                '}';
    }

    public static void main(String[] args) {
        try {

            Leaf leaf1 = new Leaf("green");
            Leaf leaf2 = new Leaf("green");


            Branch branch1 = new Branch();
            branch1.addLeaf(leaf1);
            branch1.addLeaf(leaf2);


            Tree tree = new Tree();
            tree.addBranch(branch1);


            System.out.println("Before: " + tree);
            tree.bloomAllLeaves();
            tree.yellowAllLeaves();
            tree.frostAllLeaves();
            System.out.println("After: " + tree);


            Leaf leaf3 = new Leaf("yellow");
            System.out.println("leaf2 equals leaf3: " + leaf2.equals(leaf3));
            System.out.println("hashCode leaf2: " + leaf2.hashCode());
            System.out.println("toString leaf2: " + leaf2.toString());
        } catch (DataProcessingException e) {
            System.err.println("Data processing error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
