package com.projekt.justyna.magazyn.views;

        import android.app.AlertDialog;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.view.View;
        import android.widget.Button;
        import android.widget.LinearLayout;
        import android.widget.TextView;

        import com.projekt.justyna.magazyn.R;
        import com.projekt.justyna.magazyn.activities.CategoryActivity;
        import com.projekt.justyna.magazyn.activities.ProviderActivity;
        import com.projekt.justyna.magazyn.activities.UpdatableActivity;
        import com.projekt.justyna.magazyn.data.beams.Category;
        import com.projekt.justyna.magazyn.data.beams.Provider;
        import com.projekt.justyna.magazyn.data.stores.IStore;
        import com.projekt.justyna.magazyn.data.stores.StoreFactory;


public class ProviderItem extends LinearLayout {
    public ProviderItem(final Context context, final Provider provider, final UpdatableActivity activity) {
        super(context);
        inflate(context, R.layout.provider_row, this);

        TextView nameTextView = (TextView) findViewById(R.id.name);
        nameTextView.setText(provider.getName());

        TextView telephoneTextView = (TextView) findViewById(R.id.telephone);
        telephoneTextView.setText(provider.getTelephone());

        TextView addressTextView = (TextView) findViewById(R.id.address);
        addressTextView.setText(provider.getAddress());

        Button edit = (Button) findViewById(R.id.edit);
        edit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProviderActivity.class);
                intent.putExtra(ProviderActivity.EXTRA_ID, provider.getId());

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
                        IStore<Provider> store = StoreFactory.createProvidersStore();
                        store.remove(provider.getId());
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

