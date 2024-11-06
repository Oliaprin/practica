package task3_6;

import java.util.Objects;


public class Leaf {
    private String color;
    private boolean isBlooming;
    private boolean isFrosted;

    public Leaf(String color) throws DataProcessingException {
        if (color == null || color.trim().isEmpty()) {
            throw new DataProcessingException("Color cannot be null or empty.");
        }
        this.color = color;
        this.isBlooming = false;
        this.isFrosted = false;
    }

    public void bloom() {
        isBlooming = true;
        System.out.println("The leaf is blooming.");
    }

    public void wither() {
        isBlooming = false;
        System.out.println("The leaf is withering.");
    }

    public void frost() {
        isFrosted = true;
        System.out.println("The leaf is frosted.");
    }

    public void yellow() {
        color = "yellow";
        System.out.println("The leaf has turned yellow.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Leaf leaf = (Leaf) o;
        return isBlooming == leaf.isBlooming &&
                isFrosted == leaf.isFrosted &&
                Objects.equals(color, leaf.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, isBlooming, isFrosted);
    }

    @Override
    public String toString() {
        return "Leaf{" +
                "color='" + color + '\'' +
                ", isBlooming=" + isBlooming +
                ", isFrosted=" + isFrosted +
                '}';
    }
}
