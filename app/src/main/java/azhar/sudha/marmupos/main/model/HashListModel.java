package azhar.sudha.marmupos.main.model;

import java.io.Serializable;

/**
 * Created by azharuddin on 3/8/17.
 */

public class HashListModel implements Serializable {
    private String key;
    private Object ListMap;

    public HashListModel(String key, Object ListMap) {
        this.key = key;
        this.ListMap = ListMap;
    }

    public String getKey() {
        return key;
    }

    public Object getListMap() {
        return ListMap;
    }
}
