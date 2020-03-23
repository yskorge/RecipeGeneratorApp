package skorge.yngve.recipegeneratorapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import skorge.yngve.recipegeneratorapp.dialogs.EditRecipeDialog;
import skorge.yngve.recipegeneratorapp.models.Recipe;

public class RecipeAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Recipe> recipes;
    private List<String> keys;

    public RecipeAdapter(Context context, ArrayList<Recipe> arrayList, List<String> keys) {
        this.context = context;
        this.recipes = arrayList;
        this.keys = keys;
    }
    @Override
    public int getCount() {
        return recipes.size();
    }
    @Override
    public Object getItem(int position) {
        return recipes.get(position);
    }
    @Override
    public long getItemId(int position) {
        return recipes.get(position).getId();
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.listview_row, parent, false);

        TextView title = convertView.findViewById(R.id.listitem_title);
        TextView ingredients = convertView.findViewById(R.id.listitem_ingredients);
        TextView edit = convertView.findViewById(R.id.listitem_edit);

        title.setText(recipes.get(position).getTitle());
        ingredients.setText(recipes.get(position).getIngredients());

        for (Recipe r: recipes) {
            Log.d("ahhhh", r.toString());
        }

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO very ugly
                EditRecipeDialog editRecipeDialog = new EditRecipeDialog();
                Bundle arguments = new Bundle();
                arguments.putSerializable("recipe", recipes.get(position));
                arguments.putString("key", keys.get(position));
                editRecipeDialog.setArguments(arguments);
                editRecipeDialog.show(((AppCompatActivity)context).getSupportFragmentManager(),"Title");            }
        });

        return convertView;
    }
}
