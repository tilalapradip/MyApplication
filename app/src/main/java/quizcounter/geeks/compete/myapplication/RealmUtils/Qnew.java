package quizcounter.geeks.compete.myapplication.RealmUtils;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Qnew  extends RealmObject implements Serializable {
    @PrimaryKey
    public  String Qid;
    public  String Pid;
    public  int Qno ;
    public  String QData;
    public  String RightAns;
    public  String AnsData;
    public  String optionsString;
    public  String paraId;
//    options array string
//    Para,para id,list of questions.
}
