package skorge.yngve.recipegeneratorapp.models;

import java.io.Serializable;

public class Recipe implements Serializable {
    private String title;
    private String instructions;
    private int id;
    private String ingredients;

    public Recipe(String title, String instructions, String ingredients) {
        this.title = title;
        this.instructions = instructions;
        this.ingredients = ingredients;
    }

    public Recipe() {}

    @Override
    public String toString() {
        return "Recipe{" +
                "title='" + title + '\'' +
                ", instructions='" + instructions + '\'' +
                ", id=" + id +
                ", ingredients='" + ingredients + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
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