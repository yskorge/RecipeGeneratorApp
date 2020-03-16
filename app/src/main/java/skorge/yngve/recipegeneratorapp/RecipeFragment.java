package skorge.yngve.recipegeneratorapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class RecipeFragment extends Fragment implements AddRecipeDialog.AddRecipeListener {

    ListView mListView;
    Button mButton;
    ArrayList<Recipe> recipeList = new ArrayList<>();
    RecipeAdapter recipeAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_recipe, container, false);

        mButton = (Button) view.findViewById(R.id.recipe_add_recipe);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddRecipeDialog();
            }
        });

        Recipe soup = new Recipe("Moms Soup", "delicous", "tomatoes, onions");
        Recipe spag = new Recipe("Dads Spag", "spicy", "pasta, garlic");

        recipeList.add(soup);
        recipeList.add(spag);

        mListView = (ListView) view.findViewById(R.id.recipe_listview);
        Log.d("myTag", "test");

        recipeAdapter = new RecipeAdapter(getContext(), recipeList);
        mListView.setAdapter(recipeAdapter);

        return view;
    }

    public void openAddRecipeDialog() {
        AddRecipeDialog addRecipeDialog = new AddRecipeDialog();
        addRecipeDialog.setTargetFragment(RecipeFragment.this, 1);
        addRecipeDialog.show(getFragmentManager(), "add recipe dialog");
    }

    @Override
    public void applyInputs(String title, String ingredients, String instructions) {
        Recipe newRecipe = new Recipe(title, instructions, ingredients);

        recipeList.add(newRecipe);
        recipeAdapter.notifyDataSetChanged();

        for(int i = 0; i < recipeList.size(); i++)
            Log.d("arrayyy", recipeList.get(i).getDescription());
    }
}