package skorge.yngve.recipegeneratorapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddRecipeDialog extends AppCompatDialogFragment {

    private EditText mEditTitle;
    private EditText mEditIngredients;
    private EditText mEditInstructions;
    private AddRecipeListener addRecipeListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_recipe_dialog, null);

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
                        addRecipeListener.applyInputs(title, ingredients, instructions);
                    }
                });

        mEditTitle = view.findViewById(R.id.add_recipe_title);
        mEditIngredients = view.findViewById(R.id.add_recipe_ingredients);
        mEditInstructions = view.findViewById(R.id.add_recipe_instructions);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            addRecipeListener = (AddRecipeListener) getTargetFragment();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface AddRecipeListener{
        void applyInputs(String title, String ingredients, String instructions);
    }
}
