package com.projekt.justyna.magazyn.data.stores;

        import android.content.Context;

        import pl.rozkocha.szymon.magazyn.Application;
        import pl.rozkocha.szymon.magazyn.data.beams.Article;
        import pl.rozkocha.szymon.magazyn.data.beams.Category;
        import pl.rozkocha.szymon.magazyn.data.beams.Provider;
        import pl.rozkocha.szymon.magazyn.data.stores.database.ArticlesStore;
        import pl.rozkocha.szymon.magazyn.data.stores.database.CategoriesStore;
        import pl.rozkocha.szymon.magazyn.data.stores.database.DatabaseHelper;
        import pl.rozkocha.szymon.magazyn.data.stores.database.ProvidersStore;

/**
 * Created by Justyna on 25.08.2017.
 */

public class StoreFactory {
    public static IStore<Category> createCategoriesStore() {
        return new CategoriesStore(new DatabaseHelper(Application.get().getApplicationContext()));
    }

    public static IStore<Provider> createProvidersStore() {
        return new ProvidersStore(new DatabaseHelper(Application.get().getApplicationContext()));
    }

    public static IStore<Article> createArticlesStore(IStore<Category> categoriesStore, IStore<Provider> providersStore) {
        return new ArticlesStore(new DatabaseHelper(Application.get().getApplicationContext()), categoriesStore, providersStore);
    }

    public static IStore<Category> createCategoriesStore(Context context) {
        return new CategoriesStore(new DatabaseHelper(context));
    }

    public static IStore<Provider> createProvidersStore(Context context) {
        return new ProvidersStore(new DatabaseHelper(context));
    }

    public static IStore<Article> createArticlesStore(Context context, IStore<Category> categoriesStore, IStore<Provider> providersStore) {
        return new ArticlesStore(new DatabaseHelper(context), categoriesStore, providersStore);
    }
}

