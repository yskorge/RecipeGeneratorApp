package skorge.yngve.recipegeneratorapp.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import skorge.yngve.recipegeneratorapp.MainActivity;
import skorge.yngve.recipegeneratorapp.R;
import skorge.yngve.recipegeneratorapp.firebase.FirebaseDatabaseHelper;
import skorge.yngve.recipegeneratorapp.models.Recipe;

public class AddRecipeDialog extends AppCompatDialogFragment {

    private EditText mEditTitle;
    private EditText mEditIngredients;
    private EditText mEditInstructions;
    private ArrayList<String> currentTags = new ArrayList<>();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_recipe_dialog, null);


        mEditTitle = view.findViewById(R.id.add_recipe_title);
        mEditIngredients = view.findViewById(R.id.add_recipe_ingredients);
        mEditInstructions = view.findViewById(R.id.add_recipe_instructions);

        //TODO works but shit
        final CheckBox breakfast = view.findViewById(R.id.checkBox_breakfast);
        breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (breakfast.isChecked()){
                    currentTags.add("Breakfast");
                }else{
                    currentTags.remove("Breakfast");
                }
            }
        });

        final CheckBox lunch = view.findViewById(R.id.checkBox_lunch);
        lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lunch.isChecked()){
                    currentTags.add("Lunch");
                }else{
                    currentTags.remove("Lunch");
                }
            }
        });

        final CheckBox dinner = view.findViewById(R.id.checkBox_dinner);
        dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dinner.isChecked()){
                    currentTags.add("Dinner");
                }else{
                    currentTags.remove("Dinner");
                }
            }
        });

        builder.setView(view)
                .setTitle("Add Recipe")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getDialog().dismiss();
                    }
                })
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String title = mEditTitle.getText().toString();
                        String ingredients = mEditIngredients.getText().toString();
                        String instructions = mEditInstructions.getText().toString();

                        if (title.length() > 2 || ingredients.length() > 2 || instructions.length() > 2) {
//                            addRecipeListener.applyInputs(title, currentTag, ingredients, instructions);
                            Recipe recipe = new Recipe(title, currentTags, ingredients, instructions);
                            new FirebaseDatabaseHelper().addRecipe(recipe, new FirebaseDatabaseHelper.DataStatus() {
                                @Override
                                public void DataIsLoaded(ArrayList<Recipe> recipes, List<String> keys) {

                                }

                                @Override
                                public void DataIsInserted() {
                                    Toast.makeText(getContext(), "Successfully added a recipe!", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void DataIsUpdated() {

                                }

                                @Override
                                public void DataIsDeleted() {

                                }
                            });
                        } else {
                            Toast.makeText(getContext(), "Please enter more the 3 characters", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        return builder.create();
    }
}

