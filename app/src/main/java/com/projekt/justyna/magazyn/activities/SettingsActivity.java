package com.projekt.justyna.magazyn.activities;



import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import com.projekt.justyna.magazyn.Application;
import com.projekt.justyna.magazyn.R;
import com.projekt.justyna.magazyn.data.beams.Article;
import com.projekt.justyna.magazyn.data.stores.IStore;
import com.projekt.justyna.magazyn.data.stores.StoreFactory;

/**
 * Created by Justyna on 25.08.2017.
 */

public class SettingsActivity extends AppCompatActivity {
    public static final String SETTINGS_PREFERENCES = "settings_preferences";
    public static final String BEST_ARTICLE = "best_article";
    public static final String BACKGROUND_COLOR = "background_color";
    public static final String DEFAULT_BACKGROUND_COLOR = "#FFFFFF";
    public static final String BUTTONS_COLOR = "buttons_color";
    public static final String DEFAULT_BUTTONS_COLOR = "#AAAAAA";

    ArrayAdapter<Article> articlesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button background = (Button) findViewById(R.id.background);
        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog = Application.createListDialog(R.string.set_background_color, R.array.colors_names,  new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String[] colors = SettingsActivity.this.getResources().getStringArray(R.array.colors);

                        SharedPreferences sp = getSharedPreferences(SETTINGS_PREFERENCES, 0);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString(BACKGROUND_COLOR, colors[which]);
                        editor.commit();
                    }
                }, SettingsActivity.this);
                dialog.show();
            }
        });
        Button buttons = (Button) findViewById(R.id.buttons);
        buttons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                builder.setTitle(R.string.set_buttons_color);
                builder.setItems(R.array.colors_names, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String[] colors = SettingsActivity.this.getResources().getStringArray(R.array.colors);

                        SharedPreferences sp = getSharedPreferences(SETTINGS_PREFERENCES, 0);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString(BUTTONS_COLOR, colors[which]);
                        editor.commit();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        Spinner articlesSpinner = (Spinner) findViewById(R.id.best_article);
        articlesAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item);
        articlesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        articlesSpinner.setAdapter(articlesAdapter);
        articlesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Article article = articlesAdapter.getItem(i);

                SharedPreferences sp = getSharedPreferences(SETTINGS_PREFERENCES, 0);
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt(BEST_ARTICLE, article.getId());
                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        articlesAdapter.clear();

        IStore<Article> store = StoreFactory.createArticlesStore(StoreFactory.createCategoriesStore(), StoreFactory.createProvidersStore());
        List<Article> all = new ArrayList<>();
        all.add(new Article(0, "Brak", 0.0f, null, null));
        all.addAll(store.get());

        articlesAdapter.addAll(all);

        SharedPreferences sp = getSharedPreferences(SETTINGS_PREFERENCES, 0);
        int id = sp.getInt(SettingsActivity.BEST_ARTICLE, 0);

        Spinner articlesSpinner = (Spinner) findViewById(R.id.best_article);
        int position = Article.getPositionById(id, all);
        articlesSpinner.setSelection(position);
    }
}

