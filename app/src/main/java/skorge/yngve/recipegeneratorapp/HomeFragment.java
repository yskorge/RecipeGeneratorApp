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

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ListView mListView = null;
    private ArrayList<Recipe> recipeList = new ArrayList<>();
    TextView tvHomeRecipe;
    Button bRandomGenerator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // TODO static data for FE testing
        Recipe soup = new Recipe("Moms Souppp", "delicous", "tomatoes, onions");
        Recipe spag = new Recipe("Dads Spag", "spicy", "pasta, garlic");
        Recipe burger = new Recipe("Jerrys Burger", "meaty burger", "bread, meat");
        recipeList.add(soup);
        recipeList.add(spag);
        recipeList.add(burger);

        // Inflate the layout for this fragment
        Log.d("myTag", "This is HomeFragment");

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        bRandomGenerator = (Button) view.findViewById(R.id.home_random_recipe_generator);
        tvHomeRecipe = (TextView) view.findViewById(R.id.home_recipe);

        bRandomGenerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvHomeRecipe.setText(recipeList.get(getRandom(3)).getTitle());
            }
        });

        return view;
    }

    public static int getRandom(int max){
        return (int) (Math.random() * max);
    }
}
