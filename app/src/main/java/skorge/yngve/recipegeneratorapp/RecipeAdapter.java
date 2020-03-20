package skorge.yngve.recipegeneratorapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import skorge.yngve.recipegeneratorapp.models.Recipe;

public class RecipeAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Recipe> arrayList;

    public RecipeAdapter(Context context, ArrayList<Recipe> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }
    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return arrayList.get(position).getId();
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.listview_row, parent, false);

        TextView title = convertView.findViewById(R.id.listitem_title);
        TextView ingredients = convertView.findViewById(R.id.listitem_ingredients);
        TextView edit = convertView.findViewById(R.id.listitem_edit);

        title.setText(arrayList.get(position).getTitle());
        ingredients.setText(arrayList.get(position).getIngredients());

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO very ugly
                EditRecipeDialog editRecipeDialog = new EditRecipeDialog();
                Bundle arguments = new Bundle();
                arguments.putSerializable("recipe",arrayList.get(position));
                editRecipeDialog.setArguments(arguments);
                editRecipeDialog.show(((AppCompatActivity)context).getSupportFragmentManager(),"Title");            }
        });

        return convertView;
    }
}
