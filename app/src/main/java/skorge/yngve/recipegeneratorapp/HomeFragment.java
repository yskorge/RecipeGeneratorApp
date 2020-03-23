package skorge.yngve.recipegeneratorapp;

import android.content.Intent;
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

    private ArrayList<Recipe> recipeList = new ArrayList<>();
    TextView tvHomeRecipe;
    Button bRandomGenerator;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference refGlobal = database.getReference("server");
    DatabaseReference recipesRef = refGlobal.child("recipes");

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Attach a listener to read the data at our recipe reference
        recipesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    recipeList.clear();

                    for(DataSnapshot dss: dataSnapshot.getChildren()) {
                        Recipe recipe = dss.getValue(Recipe.class);
                        recipeList.add(recipe);
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
                    final Recipe currentRecipe = recipeList.get(getRandom(recipeList.size()));
                    tvHomeRecipe.setText(currentRecipe.getTitle());
                    tvHomeRecipe.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), SingleRecipeActivity.class);
                            intent.putExtra("currentRecipe", currentRecipe);
                            startActivity(intent);
                        }
                    });
                }
            }
        });

        return view;
    }

    public static int getRandom(int max){
        return (int) (Math.random() * max);
    }
}
