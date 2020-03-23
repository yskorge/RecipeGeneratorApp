package skorge.yngve.recipegeneratorapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import skorge.yngve.recipegeneratorapp.dialogs.AddRecipeDialog;
import skorge.yngve.recipegeneratorapp.firebase.FirebaseDatabaseHelper;
import skorge.yngve.recipegeneratorapp.models.Recipe;

public class RecipeFragment extends Fragment {

    ListView mListView;
    Button mButton;
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

        mListView = (ListView) view.findViewById(R.id.recipe_listview);

        // firebase read
        new FirebaseDatabaseHelper().readRecipes(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(final ArrayList<Recipe> recipes, List<String> keys) {

                recipeAdapter = new RecipeAdapter(getContext(), recipes, keys);
                mListView.setAdapter(recipeAdapter);
                recipeAdapter.notifyDataSetChanged();

                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(getActivity(), SingleRecipeActivity.class);
                        intent.putExtra("currentRecipe", recipes.get(i));
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });

//        // Attach a listener to read the data at our recipe reference
//        recipesRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if(dataSnapshot.exists()) {
//                    recipeList.clear();
//                    Log.d("yngveeee", dataSnapshot.toString());
//                    Log.d("yngveeee2", dataSnapshot.getValue().toString());
//
//                    for(DataSnapshot dss: dataSnapshot.getChildren()) {
//                        Recipe recipe = dss.getValue(Recipe.class);
////                        Log.d("yngveeee 55", recipe.toString());
//                        recipeList.add(recipe);
//                    }
//                  recipeAdapter.notifyDataSetChanged();
//                }
//            }

        //            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                System.out.println("The read failed: " + databaseError.getCode());
//            }
//        });
        return view;
    }

    public void openAddRecipeDialog() {
        AddRecipeDialog addRecipeDialog = new AddRecipeDialog();
        addRecipeDialog.setTargetFragment(RecipeFragment.this, 1);
        addRecipeDialog.show(getFragmentManager(), "add recipe dialog");
    }
}