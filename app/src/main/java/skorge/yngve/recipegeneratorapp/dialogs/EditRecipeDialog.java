package skorge.yngve.recipegeneratorapp.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import skorge.yngve.recipegeneratorapp.R;
import skorge.yngve.recipegeneratorapp.firebase.FirebaseDatabaseHelper;
import skorge.yngve.recipegeneratorapp.models.Recipe;

public class EditRecipeDialog extends AppCompatDialogFragment {

    private EditText mEditTitle;
    private EditText mEditIngredients;
    private EditText mEditInstructions;
    private Recipe recipe;
    private String key;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.edit_recipe_dialog, null);

        //TODO very ugly written
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            recipe = (Recipe) bundle.getSerializable("recipe");
            key = bundle.getString("key");
        }

        mEditTitle = view.findViewById(R.id.edit_recipe_title);
        mEditIngredients = view.findViewById(R.id.edit_recipe_ingredients);
        mEditInstructions = view.findViewById(R.id.edit_recipe_instructions);

        mEditTitle.setText(recipe.getTitle());
        mEditIngredients.setText(recipe.getIngredients());
        mEditInstructions.setText(recipe.getInstructions());

        //TODO works but shit
        final CheckBox breakfast = view.findViewById(R.id.checkBox_breakfast);
        if (recipe.getTags().contains("Breakfast")) {
            breakfast.setChecked(true);
        }
        breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (breakfast.isChecked() && !recipe.getTags().contains("Breakfast")) {
                    recipe.getTags().add("Breakfast");
                } else {
                    recipe.getTags().remove("Breakfast");
                }
            }
        });

        final CheckBox lunch = view.findViewById(R.id.checkBox_lunch);
        if (recipe.getTags().contains("Lunch")) {
            lunch.setChecked(true);
        }
        lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lunch.isChecked() && !recipe.getTags().contains("Lunch")) {
                    recipe.getTags().add("Lunch");
                } else {
                    recipe.getTags().remove("Lunch");
                }
            }
        });

        final CheckBox dinner = view.findViewById(R.id.checkBox_dinner);
        if (recipe.getTags().contains("Dinner")) {
            dinner.setChecked(true);
        }
        dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dinner.isChecked() && !recipe.getTags().contains("Dinner")) {
                    recipe.getTags().add("Dinner");
                } else {
                    recipe.getTags().remove("Dinner");
                }
            }
        });

        /*
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, tags);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        // Apply the adapter to the spinner
        mSpinner.setAdapter(adapter);
        // Sets the default tag to existing object tag
        mSpinner.setSelection(adapter.getPosition(recipe.getTag()));

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currentTag = tags.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //do nothing atm
            }
        });

         */

        builder.setView(view)
                .setTitle("Edit Recipe")
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
//                            recipeRef.child(title).setValue(new Recipe(title, "test", ingredients, instructions));
                            ArrayList<String> currentTags = recipe.getTags();
                            Recipe recipe = new Recipe(title, currentTags, ingredients, instructions);
                            new FirebaseDatabaseHelper().updateRecipe(key, recipe, new FirebaseDatabaseHelper.DataStatus() {
                                @Override
                                public void DataIsLoaded(ArrayList<Recipe> recipes, List<String> keys) {

                                }

                                @Override
                                public void DataIsInserted() {

//                                    Toast.makeText(getContext(), "Successfully added a recipe!", Toast.LENGTH_SHORT).show();
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
