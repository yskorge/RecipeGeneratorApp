package skorge.yngve.recipegeneratorapp.firebase;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import skorge.yngve.recipegeneratorapp.models.Recipe;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<Recipe> recipes = new ArrayList<>();

    public interface DataStatus {
        void DataIsLoaded(ArrayList<Recipe> recipes, List<String> keys);

        void DataIsInserted();

        void DataIsUpdated();

        void DataIsDeleted();
    }

    public FirebaseDatabaseHelper() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("server/recipes");
    }

    public void readRecipes(final DataStatus dataStatus) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                recipes.clear();

                List<String> keys = new ArrayList<>();
                for (DataSnapshot dss : dataSnapshot.getChildren()) {
                    keys.add(dss.getKey());
                    Recipe recipe = dss.getValue(Recipe.class);
                    recipes.add(recipe);
                }
                dataStatus.DataIsLoaded(recipes, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addRecipe(Recipe recipe, final DataStatus dataStatus) {
        String key = databaseReference.push().getKey();
        databaseReference.child(key).setValue(recipe)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsInserted();
                    }
                });
    }

    public void updateRecipe(String key, Recipe recipe, final DataStatus dataStatus) {
        databaseReference.child(key).setValue(recipe)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsUpdated();
                    }
                });
    }

    public void deleteRecipe(String key, final DataStatus dataStatus) {
        databaseReference.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsDeleted();
                    }
                });
    }
}
