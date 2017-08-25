package com.projekt.justyna.magazyn.views.adapters;

        import android.content.Context;
        import android.support.annotation.NonNull;
        import android.support.annotation.Nullable;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;

        import java.util.List;

        import com.projekt.justyna.magazyn.views.CategoryItem;
        import com.projekt.justyna.magazyn.activities.UpdatableActivity;
        import com.projekt.justyna.magazyn.data.beams.Category;

public class CategoriesAdapter extends ArrayAdapter<Category> {
    private UpdatableActivity activity;

    public CategoriesAdapter(Context context, UpdatableActivity activity) {
        super(context, 0);
        this.activity = activity;
    }

    public void update(List<Category> categories) {
        clear();

        addAll(categories);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Category category = getItem(position);

        return new CategoryItem(getContext(), category, this.activity);
    }
}

