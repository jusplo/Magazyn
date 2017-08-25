package com.projekt.justyna.magazyn.data.stores.files;

        import android.content.Context;
        import android.os.Build;
        import android.util.Log;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.BufferedReader;
        import java.io.FileInputStream;
        import java.io.FileOutputStream;
        import java.io.InputStreamReader;
        import java.util.ArrayList;
        import java.util.Collections;
        import java.util.Comparator;
        import java.util.List;

        import com.projekt.justyna.magazyn.data.beams.Category;
        import com.projekt.justyna.magazyn.data.stores.IStore;


public class CategoriesStore implements IStore<Category> {
    private String fileName;
    private Context context;

    public CategoriesStore(String fileName, Context context) {
        this.fileName = fileName;
        this.context = context;
    }

    @Override
    public List<Category> get() {
        return get("", "");
    }

    @Override
    public List<Category> getWithoutId(int withoutId) {
        List<Category> list = get();

        for(Category c : list) {
            if( c.getId() == withoutId) {
                list.remove(c);
                break;
            }
        }

        return list;
    }

    @Override
    public List<Category> get(String column, String order) {
        String content = readFile();

        try {
            JSONObject obj = new JSONObject(content);

            JSONArray categories = obj.getJSONArray("data");

            List<Category> list = new ArrayList<>();

            for(int i = 0;i < categories.length();++i) {
                JSONObject category = (JSONObject) categories.get(i);

                list.add(new Category(category));
            }

            /*Collections.sort(list, new Comparator<Category>() {
                @Override
                public int compare(Category a, Category b) {
                    return 0;
                }
            });*/

            return list;
        } catch (JSONException e) {
            Log.d("CategoriesStore", e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public Category getById(int id) {
        List<Category> list = get();

        for(Category c : list) {
            if( c.getId() == id) {
                return c;
            }
        }

        return null;
    }

    @Override
    public void add(Category c) {
        int autoIncrement = getAutoIncrement();
        List<Category> list = get();
        c.setId(autoIncrement);
        list.add(c);

        ++autoIncrement;

        saveToFile(autoIncrement, list);
    }

    @Override
    public void update(Category c) {
        List<Category> list = get();

        for(Category c1 : list) {
            if(c1.getId() == c.getId()) {
                c1.setName(c.getName());
                c1.setParentId(c.getParentId());

                saveToFile(getAutoIncrement(), list);
                break;
            }
        }
    }

    @Override
    public void remove(int id) {
        List<Category> list = get();

        for(Category c : list) {
            if(c.getId() == id) {
                list.remove(c);

                saveToFile(getAutoIncrement(), list);
                break;
            }
        }
    }

    private String readFile() {
        try {
            FileInputStream fis = context.openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            String temp =  FilesHelper.readAllContent(br);

            br.close();

            return temp;
        } catch (Exception e) {
            Log.d("CategoriesStore", e.getMessage());
            return  "";
        }
    }

    private int getAutoIncrement() {
        String content = readFile();

        int autoIncrement;
        try {
            JSONObject obj = new JSONObject(content);

            autoIncrement = obj.getInt("auto_increment");
        } catch (JSONException e) {
            Log.d("CategoriesStore", e.getMessage());
            autoIncrement = 1;
        }

        return autoIncrement;
    }

    private void saveToFile(int autoIncrement, List<Category> list) {
        try {
            JSONObject root = new JSONObject();
            root.put("auto_increment", autoIncrement);

            JSONArray categories = new JSONArray();

            for(Category c : list) {
                JSONObject o = new JSONObject();
                o.put("id", c.getId());
                o.put("name", c.getName());

                if(c.getParentId() != null) {
                    o.put("parent_id", c.getParentId());
                }

                categories.put(o);
            }

            root.put("data", categories);

            String content = root.toString();

            try {
                FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
                fos.write(content.getBytes());
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (JSONException e) {
            Log.d("CategoriesStore", e.getMessage());
        }
    }
}

