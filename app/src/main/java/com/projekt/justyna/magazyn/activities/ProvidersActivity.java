package com.projekt.justyna.magazyn.activities;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ListView;
        import android.widget.Toast;

        import java.util.List;

        import com.projekt.justyna.magazyn.R;
        import com.projekt.justyna.magazyn.data.beams.Category;
        import com.projekt.justyna.magazyn.data.beams.Provider;
        import com.projekt.justyna.magazyn.data.stores.IStore;
        import com.projekt.justyna.magazyn.data.stores.StoreFactory;
        import com.projekt.justyna.magazyn.views.adapters.ProvidersAdapter;

/**
 * Created by Justyna on 25.08.2017.
 */

public class ProvidersActivity extends UpdatableActivity {
    private ListView listView;
    private ProvidersAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = (ListView) findViewById(R.id.list);
        listAdapter = new ProvidersAdapter(getApplicationContext(), this);
        listView.setAdapter(listAdapter);

        Button add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ProviderActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        update();
    }

    @Override
    public void update() {
        IStore<Provider> store = StoreFactory.createProvidersStore();
        List<Provider> providers = store.get();

        listAdapter.update(providers);
    }
}
