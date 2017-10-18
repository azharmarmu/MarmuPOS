package azhar.sudha.marmupos.main.model;

import java.util.HashMap;

/**
 * Created by azharuddin on 27/9/17.
 */

public class CategoryModel {
    private String name;
    private HashMap<String,Object> categoryMap;

    public CategoryModel(String name, HashMap<String, Object> categoryMap) {
        this.name = name;
        this.categoryMap = categoryMap;
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Object> getCategoryMap() {
        return categoryMap;
    }
}
