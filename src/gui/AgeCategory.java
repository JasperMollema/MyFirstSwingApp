package gui;

public class AgeCategory {
    public static final String CHILD = "child";
    public static final String ADULT = "adult";
    public static final String SENIOR = "senior";

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
