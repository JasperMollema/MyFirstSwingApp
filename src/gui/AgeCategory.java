package gui;

public class AgeCategory {
    private int id;
    private String category;

    public AgeCategory(int id, String category) {
        this.id = id;
        this.category = category;
    }

    @Override
    public String toString() {
        return category;
    }

    public int getId() {
        return id;
    }
}
