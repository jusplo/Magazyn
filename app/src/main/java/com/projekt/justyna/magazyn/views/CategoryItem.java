package com.projekt.justyna.magazyn.views;

        import android.app.Activity;
        import android.app.AlertDialog;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.view.View;
        import android.widget.Button;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.projekt.justyna.magazyn.R;
        import com.projekt.justyna.magazyn.activities.CategoryActivity;
        import com.projekt.justyna.magazyn.activities.UpdatableActivity;
        import com.projekt.justyna.magazyn.data.beams.Category;
        import com.projekt.justyna.magazyn.data.stores.IStore;
        import com.projekt.justyna.magazyn.data.stores.StoreFactory;


public class CategoryItem extends LinearLayout {
    public CategoryItem(final Context context, final Category category, final UpdatableActivity activity) {
        super(context);
        inflate(context, R.layout.category_row, this);

        TextView textView = (TextView) findViewById(R.id.text);
        textView.setText(category.getName());

        Button edit = (Button) findViewById(R.id.edit);
        edit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CategoryActivity.class);
                intent.putExtra(CategoryActivity.EXTRA_ID, category.getId());

                activity.startActivity(intent);
            }
        });

        Button delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setMessage(R.string.remove_message)
                        .setTitle(R.string.remove_title);

                builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        IStore<Category> store = StoreFactory.createCategoriesStore();
                        store.remove(category.getId());
                        activity.update();
                    }
                });
                builder.setNegativeButton(R.string.no, null);

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}

