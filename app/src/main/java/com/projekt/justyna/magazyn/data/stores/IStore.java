package com.projekt.justyna.magazyn.data.stores;

import java.util.List;

public interface IStore<T> {
    List<T> get();
    List<T> getWithoutId(int withoutId);
    List<T> get(String column, String order);
    T getById(int id);

    void add(T c);
    void update(T c);
    void remove(int id);
}

