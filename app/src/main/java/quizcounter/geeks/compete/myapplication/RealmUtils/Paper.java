package quizcounter.geeks.compete.myapplication.RealmUtils;

import java.io.Serializable;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by espl on 28/11/17.
 */

public class Paper extends RealmObject implements Serializable {
    @PrimaryKey
    public String paperId;
    public String paperData; //    public int order; is inside the data
    public boolean isAttempted;
    public String paperTitle;
    public String paperDesc;
    public String time;
    public String totalMarks;
    public long timeElapsed;
}

