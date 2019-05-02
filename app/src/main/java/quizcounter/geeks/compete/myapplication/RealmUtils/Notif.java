package quizcounter.geeks.compete.myapplication.RealmUtils;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Notif extends RealmObject implements Serializable {
    @PrimaryKey
    public String notifId;
    public String notifData; //    public int order; is inside the data
    public boolean isRead=false;
    public String notifIdTitle;
    public String notifDesc;
//    public String notifId;
//    public String notifData; //    public int order; is inside the data
}
