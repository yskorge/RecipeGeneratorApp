package skorge.yngve.recipegeneratorapp;

import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import skorge.yngve.recipegeneratorapp.models.Recipe;

public class HomeFragment extends Fragment {

    ListView mListView = null;
    private ArrayList<Recipe> recipeList = new ArrayList<>();
    TextView tvHomeRecipe;
    Button bRandomGenerator;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference refGlobal = database.getReference("server");
    DatabaseReference recipesRef = refGlobal.child("recipes");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        // TODO static data for FE testing
//        Recipe soup = new Recipe("Moms Souppp", "delicous", "tomatoes, onions");
//        Recipe spag = new Recipe("Dads Spag", "spicy", "pasta, garlic");
//        Recipe burger = new Recipe("Jerrys Burger", "meaty burger", "bread, meat");
//        recipeList.add(soup);
//        recipeList.add(spag);
//        recipeList.add(burger);

        // Attach a listener to read the data at our recipe reference
        recipesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    recipeList.clear();

                    for(DataSnapshot dss: dataSnapshot.getChildren()) {
                        Recipe recipe = dss.getValue(Recipe.class);
                        recipeList.add(recipe);
                        Log.d("yngveeee 666", recipe.toString());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //TODO random generator button must only show when list is at least >= 1 otherwise show "first add recipes under recipes"
        bRandomGenerator = (Button) view.findViewById(R.id.home_random_recipe_generator);
        tvHomeRecipe = (TextView) view.findViewById(R.id.home_recipe);

        bRandomGenerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recipeList.size() > 0) {
                    tvHomeRecipe.setText(recipeList.get(getRandom(recipeList.size())).getTitle());
                }
            }
        });

        return view;
    }

    public static int getRandom(int max){
        return (int) (Math.random() * max);
    }
}
