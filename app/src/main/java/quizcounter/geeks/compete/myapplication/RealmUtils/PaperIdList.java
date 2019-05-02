package quizcounter.geeks.compete.myapplication.RealmUtils;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PaperIdList extends RealmObject implements Serializable {
    @PrimaryKey
    public String id;
    public Long timestamp;
}
