package skorge.yngve.recipegeneratorapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

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
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.listview_row, parent, false);

        TextView title = convertView.findViewById(R.id.listitem_title);
        TextView description = convertView.findViewById(R.id.listitem_description);

        title.setText(arrayList.get(position).getTitle());
        description.setText(arrayList.get(position).getDescription());

        return convertView;
    }
}
