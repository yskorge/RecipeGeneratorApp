package skorge.yngve.recipegeneratorapp.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Recipe implements Serializable {
    private String title;
    private ArrayList<String> tags = new ArrayList<>();
    private String instructions;
    private int id;
    private String ingredients;

    public Recipe(String title, ArrayList<String> tags, String ingredients, String instructions) {
        this.title = title;
        this.tags = tags;
        this.instructions = instructions;
        this.ingredients = ingredients;
    }

    public Recipe() {
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "title='" + title + '\'' +
                ", tags=" + tags +
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

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
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