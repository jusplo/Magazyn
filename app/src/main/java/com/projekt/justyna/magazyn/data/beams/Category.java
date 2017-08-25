package com.projekt.justyna.magazyn.data.beams;


import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;



public class Category extends Beam{
    private Integer parentId;

    public Category(int id, String name, Integer parentId) {
        super(id, name);
        this.parentId = parentId;
    }

    public Category(JSONObject category) throws JSONException {
        super(0, "");
        setId(category.getInt("id"));
        setName(category.getString("name"));

        if(category.has("parent_id")) {
            parentId = category.getInt("parent_id");
        }
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public static int getPositionById(int id, List<Category> list) {
        int position = -1;

        for(int i = 0;i < list.size();++i) {
            Category category = list.get(i);
            if(category.getId() == id) {
                position = i;
                break;
            }
        }

        return position;
    }
}

