package skorge.yngve.recipegeneratorapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.Serializable;

import skorge.yngve.recipegeneratorapp.models.Recipe;

public class SingleRecipeActivity extends AppCompatActivity implements Serializable {

    TextView title;
    TextView tag;
    TextView ingredients;
    TextView instructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_recipe);

        Recipe recipe = (Recipe) getIntent().getSerializableExtra("currentRecipe");

        title = findViewById(R.id.single_recipe_title);
        ingredients = findViewById(R.id.single_recipe_ingredients);
        instructions = findViewById(R.id.single_recipe_instructions);

        title.setText(recipe.getTitle());
        ingredients.setText(recipe.getIngredients());
        instructions.setText(recipe.getInstructions());

        Log.d("wwww", recipe.toString());
    }
}
