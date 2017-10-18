package azhar.sudha.marmupos.interfaces;

import java.util.List;

import azhar.sudha.marmupos.main.model.CategoryModel;

/**
 * Created by azharuddin on 18/9/17.
 */

public interface CategoryListListener {
    public void getCategoryList(List<CategoryModel> categoryList);
}
