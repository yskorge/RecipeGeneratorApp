package skorge.yngve.recipegeneratorapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import skorge.yngve.recipegeneratorapp.models.Recipe;

public class RecipeFragment extends Fragment implements AddRecipeDialog.AddRecipeListener {

    ListView mListView;
    Button mButton;
    ArrayList<Recipe> recipeList = new ArrayList<>();
    RecipeAdapter recipeAdapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference refGlobal = database.getReference("server");
    DatabaseReference recipesRef = refGlobal.child("recipes");

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

//        Recipe soup = new Recipe("Moms Soup", "tomatoes, onions", "cook for long");
//        Recipe spag = new Recipe("Dads Spag", "spag, meat balls", "cook on 100 degrees");
//        recipeList.add(soup);
//        recipeList.add(spag);

        mListView = (ListView) view.findViewById(R.id.recipe_listview);

        recipeAdapter = new RecipeAdapter(getContext(), recipeList);
        mListView.setAdapter(recipeAdapter);

        // Attach a listener to read the data at our recipe reference
        recipesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    recipeList.clear();
                    Log.d("yngveeee", dataSnapshot.toString());
                    Log.d("yngveeee2", dataSnapshot.getValue().toString());

                    for(DataSnapshot dss: dataSnapshot.getChildren()) {
                        Recipe recipe = dss.getValue(Recipe.class);
                        Log.d("yngveeee 55", recipe.toString());
                        recipeList.add(recipe);
                    }
                  recipeAdapter.notifyDataSetChanged();
                }
            }



            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        return view;
    }

    public void openAddRecipeDialog() {
        AddRecipeDialog addRecipeDialog = new AddRecipeDialog();
        addRecipeDialog.setTargetFragment(RecipeFragment.this, 1);
        addRecipeDialog.show(getFragmentManager(), "add recipe dialog");
    }

    @Override
    public void applyInputs(String title, String tag, String ingredients, String instructions) {
        Recipe newRecipe = new Recipe(title, tag, instructions, ingredients);

//        recipeList.add(newRecipe);

        //TODO change title to a unique id
        recipesRef.child(title).setValue(newRecipe);
        recipeAdapter.notifyDataSetChanged();

//        for(int i = 0; i < recipeList.size(); i++)
//            Log.d("arrayyy", recipeList.get(i).getInstructions());
    }
}