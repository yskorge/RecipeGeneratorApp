package skorge.yngve.recipegeneratorapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import skorge.yngve.recipegeneratorapp.models.Recipe;

public class EditRecipeDialog extends AppCompatDialogFragment {

    private EditText mEditTitle;
    private EditText mEditIngredients;
    private EditText mEditInstructions;
    private Spinner mSpinner;
    private Recipe recipe;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.edit_recipe_dialog, null);

        //TODO very ugly written
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            recipe = (Recipe) bundle.getSerializable("recipe");
        }

        mEditTitle = view.findViewById(R.id.edit_recipe_title);
        mEditIngredients = view.findViewById(R.id.edit_recipe_ingredients);
        mEditInstructions = view.findViewById(R.id.edit_recipe_instructions);
        mSpinner = view.findViewById(R.id.edit_recipe_tag_spinner);

        mEditTitle.setText(recipe.getTitle());
        mEditIngredients.setText(recipe.getIngredients());
        mEditInstructions.setText(recipe.getInstructions());

        //TODO set up a spinner tag

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
                        if(title.length() > 2 || ingredients.length() > 2 || instructions.length() > 2) {
                            Toast.makeText(getContext(), "Please enter more the 3 characters", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Please enter more the 3 characters", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        return builder.create();
    }
}
