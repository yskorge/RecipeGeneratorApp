package skorge.yngve.recipegeneratorapp;

public class Recipe {
    private String title;
    private String description;
    private int id;
    private String ingredients;

    public Recipe(String title, String description, String ingredients) {
        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
}