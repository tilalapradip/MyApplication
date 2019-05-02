package quizcounter.geeks.compete.myapplication;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import io.realm.Realm;
import io.realm.RealmResults;
import quizcounter.geeks.compete.myapplication.NetworkUtility.Utils;
import quizcounter.geeks.compete.myapplication.RealmUtils.Notif;
import quizcounter.geeks.compete.myapplication.RealmUtils.NotifIdList;
import quizcounter.geeks.compete.myapplication.RealmUtils.Paper;
import quizcounter.geeks.compete.myapplication.RealmUtils.PaperIdList;
import quizcounter.geeks.compete.myapplication.RealmUtils.Qnew;
import quizcounter.geeks.compete.myapplication.Utils.Constants;

public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2000;
    ProgressBar progressBar;
    TextView txtVersion;
    private String deviceToken;
    private String stringIsUpdated;
    private DatabaseReference mDatabase;
    private DatabaseReference mDatabaseNotif;
    private DatabaseReference mDatabasePapers;
    private DatabaseReference mDatabaseNotifIdList;
    private DatabaseReference mDatabasepaperIdList;
    private DatabaseReference mDatabasisUpdated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spalshscreen);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        progressBar=findViewById(R.id.progressBar);
        txtVersion=findViewById(R.id.application_version);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabaseNotifIdList = FirebaseDatabase.getInstance().getReference(Constants.NotifIdList);
        mDatabasepaperIdList = FirebaseDatabase.getInstance().getReference(Constants.paperIdList);
        mDatabaseNotif = FirebaseDatabase.getInstance().getReference(Constants.Notif);
        mDatabasePapers = FirebaseDatabase.getInstance().getReference(Constants.Papers);
        mDatabasisUpdated = FirebaseDatabase.getInstance().getReference(Constants.isUpdated);

            setApplicationVersion();
            getData();
    }


    public void getData() {
        try {
            Log.e("LOG_SPLASH", "" + "in getData();");
//            getUpdatedDateCategories();
//            mDatabase.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    collectDataPandN((Map<String,String>) dataSnapshot.child(Constants.Papers).getValue(),(Map<String,String>) dataSnapshot.child(Constants.Notif).getValue()
//                            ,(Map<String,Long>) dataSnapshot.child(Constants.NotifIdList).getValue(),(Map<String,Long>) dataSnapshot.child(Constants.paperIdList).getValue());
//                }
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//                    Log.e("DataRecived", "onCancelled", databaseError.toException());
//                }
//            });
//            getUpdatedDateCategories();
            getBestOffersCategory(MyApplication.getPrefranceData(Constants.isUpdated));
//            isUpdated
        } catch (Exception e) {
            e.printStackTrace();
        }
    }












//    private void gotoHome() {
//        try {
////            new CountDownTimer(SPLASH_TIME_OUT, SPLASH_TIME_OUT) {
//            new CountDownTimer(SPLASH_TIME_OUT, SPLASH_TIME_OUT) {
//                public void onFinish() {
//                    if (progressBar != null)
//                        progressBar.setVisibility(View.GONE);
////                    Application.setPreferences(CommanConstants.CURRUENT_FRAGMENT, "");
////                    Application.setPreferences(CommanConstants.force_message_updated_date, "");
//
//                    redirectToScreen();
//
//                }
//
//                public void onTick(long millisUntilFinished) {
//                }
//            }.start();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    //service call to get updated category
//    private void getUpdatedDateCategories() {
//        //list to store Updated Date
//        Log.e("LOG_SPLASH", "" + "in getUpdatedDateCategories();");
//        try {
//            Log.e("LOG_SPLASH", "" + "before getupdateDate getUpdatedDateCategories();");
//            offers_category update_date = dbGetData.getupdateDate(Application.getDB());
//            Stores last_updated_store = dbGetData.getLastUpdatedStore(Application.getDB());
//            Log.e("LOG_SPLASH", "" + "after getupdateDate getUpdatedDateCategories();");
////            Log.e("UpdatedAt",update_date.getUpdated_at().toString());
//            if (update_date != null && update_date.getUpdated_at() != null && update_date.getUpdated_at().length() > 0
//                    && last_updated_store != null && last_updated_store.getUpdated_at() != null && last_updated_store.getUpdated_at().length() > 0) {
//
//                Log.e("LOG_SPLASH", "" + "-if after getupdateDate getUpdatedDateCategories();");
//
////                if (imageExist()) {
//                    SPLASH_TIME_OUT = 700;
//                    Log.e("LOG_SPLASH", "" + "-before gotoHome getUpdatedDateCategories();");
//                    gotoHome();
////                } else {
////                    Log.e("LOG_SPLASH", "" + "-before  storeAssetsImagesToHome() getUpdatedDateCategories();");
////                    new storeAssetsImagesToHome().execute();
////                }
//            } else {
//                try {
//                    final Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//
////                            deviceToken = FirebaseInstanceId.getInstance().getToken();
//                            deviceToken = Application.getPrefranceData(BestofferConstant.app_device_token);
////                            FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(SplashScreenActivity.this, new OnSuccessListener<InstanceIdResult>() {
////                                @Override
////                                public void onSuccess(InstanceIdResult instanceIdResult) {
////                                    deviceToken = instanceIdResult.getToken();
////                                    // Do whatever you want with your token now
////                                    // i.e. store it on SharedPreferences or DB
////                                    // or directly send it to server
////                                }
////                            });
//                            if (deviceToken != null) {
//                                getBestOffersCategory(BestofferConstant.StartingDate);
//                            } else {
//                                handler.postDelayed(this, 500);
//                            }
//                        }
//                    }, 500);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    SPLASH_TIME_OUT = 700;
//                    gotoHome();
//                }
//            }
////            startService(new Intent(SplashScreenActivity.this, Prerequisite.class));
//        } catch (Exception e) {
//            e.printStackTrace();
//            SPLASH_TIME_OUT = 700;
//            gotoHome();
//        }
//    }
//
//
//

//


    @Override
    public void onStart() {
        super.onStart();

//        FirebaseUserActions.getInstance().start(getIndexApiAction());
    }

    @Override
    public void onStop() {
//        FirebaseUserActions.getInstance().end(getIndexApiAction());
        super.onStop();
    }

    //service call to get all category
    private void getBestOffersCategory(final String updatedate) {
        try {
            mDatabasisUpdated.addListenerForSingleValueEvent(new ValueEventListener() {   // addValueEventListener
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
//                    collectDataPandN((Map<String,String>) dataSnapshot.child(Constants.Papers).getValue(),(Map<String,String>) dataSnapshot.child(Constants.Notif).getValue()
//                            ,(Map<String,Long>) dataSnapshot.child(Constants.NotifIdList).getValue(),(Map<String,Long>) dataSnapshot.child(Constants.paperIdList).getValue());
//                    new setCategoryData().execute(dataSnapshot);
                    new setUpdatesData(updatedate,dataSnapshot).execute(dataSnapshot);
//                    new setCategoryData().execute((Map<String,String>) dataSnapshot.child(Constants.Papers).getValue(),(Map<String,String>) dataSnapshot.child(Constants.Notif).getValue()
//                            ,(Map<String,Long>) dataSnapshot.child(Constants.NotifIdList).getValue(),(Map<String,Long>) dataSnapshot.child(Constants.paperIdList).getValue());
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e("DataRecived", "onCancelled", databaseError.toException());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    class setUpdatesData extends AsyncTask<DataSnapshot, Void, String> {
        String updatedate;DataSnapshot dataSnapshot;

        public setUpdatesData(String updatedate, DataSnapshot dataSnapshot) {
            this.updatedate = updatedate;
            this.dataSnapshot = dataSnapshot;
        }

        @Override
        protected String doInBackground(DataSnapshot dataSnapshots[]) {
            DataSnapshot dataSnapshot = dataSnapshots[0];
            Log.e("DataRecivedisUpdated",""+dataSnapshot.getValue().toString());
            stringIsUpdated=dataSnapshot.getValue().toString();
            if(!updatedate.equalsIgnoreCase(stringIsUpdated)){
//                new setNotifPaperIDsData().execute(dataSnapshot);

                mDatabaseNotifIdList.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       new checkNotifIDsData().execute(dataSnapshot);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                mDatabasepaperIdList.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        Map<String, Long> paperIdList =(Map<String,Long>) dataSnapshot.getValue();
//                        Log.e("DataRecivedPapers","::"+paperIdList.toString());
                        new checkPaperIDsData().execute(dataSnapshot);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
            return null;
        }
    }


    class checkNotifIDsData extends AsyncTask<DataSnapshot, Void, String> {
//        Map<String, Long> notifIdList;
//
//        public checkNotifIDsData(Map<String, Long> notifIdList) {
//            this.notifIdList = notifIdList;
//        }
        @Override
        protected String doInBackground(DataSnapshot dataSnapshots[]) {

//            Map<String, Long> notifIdList= (Map<String,Long>) dataSnapshot.getValue();
//            Log.e("DataRecivedNotif","::"+notifIdList.toString());
            Map<String, Long> notifIdList= (Map<String,Long>) dataSnapshots[0].getValue();
            Log.e("DataRecivedNotif","::"+notifIdList.toString());

            final ArrayList<NotifIdList> NotifIds = new ArrayList<>();
            for (Map.Entry<String, Long> notifId : notifIdList.entrySet()){
                NotifIdList notif=new NotifIdList();
                notif.id=notifId.getKey();;
                notif.timestamp=notifId.getValue();
                NotifIds.add(notif);
            }

            RealmResults<NotifIdList> notifIdListRealmResults1=Realm.getDefaultInstance().where(NotifIdList.class).findAll();
            ArrayList<NotifIdList> notifIdListArrayList=new ArrayList<>();
            notifIdListArrayList.addAll(notifIdListRealmResults1);

            Set set=new HashSet();
            for(int i=0;i<notifIdListArrayList.size();i++){
                set.add(notifIdListArrayList.get(i).id);
            }

//            if(NotifIds.size()>0){
//                for(int i=0;i<NotifIds.size();i++){
//                    if(set.contains(NotifIds.get(i))){  //contained  //check time stamp //if time stamp is different than recorded then update paper with that id
//                        for(int v=0;v<notifIdListArrayList.size();v++){
//                            if(notifIdListArrayList.get(i).id.equalsIgnoreCase(NotifIds.get(i).id)){
//
//                            }
//                        }
//                    }else {  //not contained //new id --> need to add new Paper to DB
//                        getNotifFirebase(NotifIds.get(i).id);
//                    }
//                }
//            }

            Realm realm2 = null;
            try { // I could use try-with-resources here
                realm2 = Realm.getDefaultInstance();
                realm2.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        for(NotifIdList n:NotifIds) {
                            realm.insertOrUpdate(n);
                        }
                    }
                });
            } finally {
                if(realm2 != null) {
                    realm2.close();
                }
            }

            RealmResults<NotifIdList> notifIdListRealmResults=Realm.getDefaultInstance().where(NotifIdList.class).findAll();
            for (NotifIdList notif:notifIdListRealmResults)
            {
                Log.e("DataRecivedNotifIdList", "Sucss::"+notif.id);
                Log.e("DataRecivedNotifIdList", "Sucss::"+notif.timestamp);
            }


            ArrayList<NotifIdList> notifIdListArrayList1=new ArrayList<>();
            notifIdListArrayList1.addAll(notifIdListRealmResults);

            new setNotifDataToDB().execute(notifIdListArrayList1);

            return null;
        }
    }


    class checkPaperIDsData extends AsyncTask<DataSnapshot, Void, String> {
        //        Map<String, Long> notifIdList;
//
//        public checkNotifIDsData(Map<String, Long> notifIdList) {
//            this.notifIdList = notifIdList;
//        }
        @Override
        protected String doInBackground(DataSnapshot dataSnapshots[]) {

//            Map<String, Long> notifIdList= (Map<String,Long>) dataSnapshot.getValue();
//            Log.e("DataRecivedNotif","::"+notifIdList.toString());
            Map<String, Long> paperIdList= (Map<String,Long>) dataSnapshots[0].getValue();
            Log.e("DataRecivedpaper","::"+paperIdList.toString());

            final ArrayList<PaperIdList> PaperIds = new ArrayList<>();
            for (Map.Entry<String, Long> notifId : paperIdList.entrySet()){
                //Get user map
                String key = notifId.getKey();
                Long value=notifId.getValue();
                PaperIdList notif=new PaperIdList();
                notif.id=key;
                notif.timestamp=value;
                PaperIds.add(notif);
            }
            Realm realm3 = null;
            try { // I could use try-with-resources here
                realm3 = Realm.getDefaultInstance();
                realm3.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        for(PaperIdList n:PaperIds) {
                            realm.insertOrUpdate(n);
                        }
                    }
                });
            } finally {
                if(realm3 != null) {
                    realm3.close();
                }
            }


            Realm realm22=Realm.getDefaultInstance();
            RealmResults<PaperIdList> paperIdListRealmResults=Realm.getDefaultInstance().where(PaperIdList.class).findAll();
            for (PaperIdList notif:paperIdListRealmResults)
            {
                Log.e("DataRecivedPaperIdList", "Sucss::"+notif.id);
                Log.e("DataRecivedPaperIdList", "Sucss::"+notif.timestamp);
            }

            ArrayList<PaperIdList> paperIdLists=new ArrayList<>();
            paperIdLists.addAll(realm22.copyFromRealm(paperIdListRealmResults));
//            new setAllPaperDataToDB().execute(paperIdLists);
                setAllPaperDataToDBMethod(paperIdLists);
            return null;
        }
    }
  void setAllPaperDataToDBMethod(ArrayList<PaperIdList> paperIdLists){
      new setAllPaperDataToDB().execute(paperIdLists);
  };

    class setNotifDataToDB extends AsyncTask<ArrayList<NotifIdList>, Void, String> {
        @Override
        protected String doInBackground(ArrayList<NotifIdList> notifIdLists[]) {
            ArrayList<NotifIdList> notifIdListRealmResults = notifIdLists[0];
//            Log.e("DataRecivedisUpdated",""+dataSnapshot.getValue().toString());

//            Map<String, Long> notifIdList= (Map<String,Long>) dataSnapshot.getValue();
//            Log.e("DataRecivedNotif","::"+notifIdList.toString());

            for(NotifIdList notif: notifIdListRealmResults){
                mDatabaseNotif.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Map<String, String> mapNotif= (Map<String,String>) dataSnapshot.getValue();
                        final ArrayList<Notif> notifs = new ArrayList<>();
                        for (Map.Entry<String, String> entryN : mapNotif.entrySet()){
                            //Get user map
                            String key = entryN.getKey();
                            String value=entryN.getValue();
                            Notif notif=new Notif();
                            notif.notifId=key;
                            notif.notifData=value;
                            Document document = Jsoup.parse(value);
//                        Elements title = document.getElementsByClass("title");
                            Element title = document.getElementById("title");
//                        doc.select("div.masthead").first()
//                        Elements desc = document.getElementsByClass("desc");
//                            Element desc = document.select("div.desc").first();
                            Element desc = document.getElementById("desc");
                            notif.notifIdTitle=title.text();
                            notif.notifDesc=desc.text();


                            notifs.add(notif);
                        }
                        Realm realm1 = null;
                        try { // I could use try-with-resources here
                            realm1 = Realm.getDefaultInstance();
                            realm1.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    for(Notif n:notifs) {
                                        realm.insertOrUpdate(n);
                                    }
                                }
                            });
                        } finally {
                            if(realm1 != null) {
                                realm1.close();
                            }
                        }

                        RealmResults<Notif> notifRealmResults=Realm.getDefaultInstance().where(Notif.class).findAll();
                        for (Notif notif:notifRealmResults)
                        {
                            Log.e("DataRecivedRalmNotif", "Sucss::"+notif.notifId);
                            Log.e("DataRecivedRalmNotif", "Sucss::"+notif.notifData);
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });




                }
            return null;
        }
    }

    class setAllPaperDataToDB extends AsyncTask<ArrayList<PaperIdList>, Void, ArrayList<Paper>> {
        @Override
        protected ArrayList<Paper> doInBackground(ArrayList<PaperIdList> notifIdLists[]) {
            ArrayList<PaperIdList> notifIdListRealmResults = notifIdLists[0];
//            Log.e("setAllPaperDataToDB",""+notifIdLists[0].size());
            for (PaperIdList paperIdList:notifIdListRealmResults){
                Log.e("setAllPaperDataToDB",""+paperIdList.id);
            }
//            Map<String, Long> notifIdList= (Map<String,Long>) dataSnapshot.getValue();
//            Log.e("DataRecivedNotif","::"+notifIdList.toString());
            final ArrayList<Paper> papers = new ArrayList<>();
            for(final PaperIdList paperId: notifIdListRealmResults){
                final Paper paper=new Paper();
                mDatabasePapers.child(paperId.id).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    Log.e("mDatabasePapersonDataChange","::"+dataSnapshot.getValue().toString());
//                        Map<String, String> map= (Map<String,String>) dataSnapshot.getValue();
                        String value=dataSnapshot.getValue().toString();
//                        for (Map.Entry<String, String> entry : map.entrySet()){
                            //Get user map
//                            String key = entry.getKey();
//                            String value=entry.getValue();
                        paper.paperId=paperId.id;
                            paper.paperData=value;
                        Document document = Jsoup.parse(value);
//                        Elements title = document.getElementsByClass("title");
                        Element title = document.select("div.title").first();
//                        doc.select("div.masthead").first()
//                        Elements desc = document.getElementsByClass("desc");
                        Element desc = document.select("div.desc").first();
                            paper.paperTitle=title.text();
                            paper.paperDesc=desc.text();

//                            papers.add(paper);
                        Realm realm = null;
//                        final Paper paper=realm.createObject(Paper.class,paperId.id);
                        try { // I could use try-with-resources here
//                            final Paper paper=new Paper();
//                            paper.paperId=paperId.id;
                            realm = Realm.getDefaultInstance();
//                            paper.paperData=value;
                            realm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
//                                    if(papers.size()>0){
//                                        papers.get(papers.size()-1);
//                                        papers.get(papers.size()-1);
//                                        realm.insertOrUpdate(papers.get(papers.size()-1));
//                                        realm.copyToRealmOrUpdate(papers.get(papers.size()-1));
//                                    }
//      for(Paper p:papers) {
//                                    Paper paper1=new Paper();
//                                    paper1.paperId=paper.paperId;
//                                    paper1.paperData=paper.paperData;
                                        realm.insertOrUpdate(paper);
//                                    }
                                }
                            });
                        } finally {
                            if(realm != null) {
                                realm.close();
                            }
                        }

//                        Realm realm1=Realm.getDefaultInstance();
//                        RealmResults<Paper> all=realm1.where(Paper.class).findAll();
//                       new setQuestionsWithJsoup().execute(Realm.getDefaultInstance().copyFromRealm(papers.get(papers.size()-1)));

//                        Log.e("setQuestionsWithJsoup",":::"+all.get(0).paperTitle);
                        new setQuestionsWithJsoup().execute(paper);
//                        if(realm1 != null) {
//                            realm1.close();
//                        }
                        //                        if(realm != null) {
//                            realm.close();
//                        }

//                        }

//                        Realm realm = null;
//                        try { // I could use try-with-resources here
//                            realm = Realm.getDefaultInstance();
//                            realm.executeTransaction(new Realm.Transaction() {
//                                @Override
//                                public void execute(Realm realm) {
//                                    for(Paper p:papers) {
//                                        realm.insertOrUpdate(p);
//                                    }
//                                }
//                            });
//                        } finally {
//                            if(realm != null) {
//                                realm.close();
//                            }
//                        }
//
                        ArrayList<Paper> paperList=new ArrayList<>();
                        Realm realm11 = Realm.getDefaultInstance();
                        RealmResults<Paper> paperRealmResults=realm11.where(Paper.class).findAllAsync();
                        for (Paper paper:paperRealmResults)
                        {
                            Log.e("DataRecivedRalmpap", "Sucss::"+paper.paperId);
                            Log.e("DataRecivedRalmpap", "Sucss::"+paper.paperTitle);
                            Log.e("DataRecivedRalmpap", "Sucss::"+paper.paperDesc);
                            paperList.add(realm11.copyFromRealm(paper));
                        }

//                        ArrayList<Paper> paperList=new ArrayList<>();
//                        Realm realm11 = Realm.getDefaultInstance();
//                        paperList.addAll(paperRealmResults);
//                        new setQuestionsWithJsoup().execute(paperList);

//                        Paper paper=new Paper();
//                        paper=new Paper(paperRealmResults.get(0));
//                        new setQuestionsWithJsoup().execute(paper);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e("mDatabasePapersononCancelled","::"+databaseError.getMessage());
                    }
                });




            }

//            Realm realm = null;
//            try { // I could use try-with-resources here
//                realm = Realm.getDefaultInstance();
//                realm.executeTransaction(new Realm.Transaction() {
//                    @Override
//                    public void execute(Realm realm) {
//                        for(Paper p:papers) {
//                            realm.insertOrUpdate(p);
//                        }
//                    }
//                });
//            } finally {
//                if(realm != null) {
//                    realm.close();
//                }
//            }
//
////            ArrayList<Paper> paperList=new ArrayList<>();
//            Realm realm11 = Realm.getDefaultInstance();
//            RealmResults<Paper> paperRealmResults=realm11.where(Paper.class).findAll();
//            for (Paper paper:paperRealmResults)
//            {
//                Log.e("DataRecivedRalmpap", "Sucss::"+paper.paperId);
//                Log.e("DataRecivedRalmpap", "Sucss::"+paper.paperData);
////                paperList.add(realm11.copyFromRealm(paper));
//                new setQuestionsWithJsoup().execute(realm11.copyFromRealm(paper));
//            }


            return papers;
        }

        @Override
        protected void onPostExecute(final ArrayList<Paper> papers) {
            super.onPostExecute(papers);
//            Realm realm = null;
//            try { // I could use try-with-resources here
//                realm = Realm.getDefaultInstance();
//                realm.executeTransaction(new Realm.Transaction() {
//                    @Override
//                    public void execute(Realm realm) {
//                        for(Paper p:papers) {
//                            realm.insertOrUpdate(p);
//                        }
//                    }
//                });
//            } finally {
//                if(realm != null) {
//                    realm.close();
//                }
//            }

//            ArrayList<Paper> paperList=new ArrayList<>();
//            Realm realm11 = Realm.getDefaultInstance();
//            RealmResults<Paper> paperRealmResults=realm11.where(Paper.class).findAll();
//            for (Paper paper:paperRealmResults)
//            {
//                Log.e("DataRecivedRalmpap", "Sucss::"+paper.paperId);
//                Log.e("DataRecivedRalmpap", "Sucss::"+paper.paperData);
////                paperList.add(realm11.copyFromRealm(paper));
//                new setQuestionsWithJsoup().execute(realm11.copyFromRealm(paper));
//            }


        }
    }

    class setQuestionsWithJsoup extends AsyncTask<Paper, Void, String> {


        @Override
        protected String doInBackground(Paper papers[]) {

//            Log.e("CalledsetQuestionsWithJsoup","");
            Document document = Jsoup.parse(papers[0].paperData);
            Elements nodeBlogStats = document.getElementsByClass("question-data");
            ArrayList<Element> elementArrayList=new ArrayList<>();
            ArrayList<Element> elementArrayPara=new ArrayList<>();
            HashMap<String,Integer> hashMap=new HashMap<>();
//        for (Element row : nodeBlogStats) {
            for (int e=0;e<nodeBlogStats.size();e++) {
//            Log.e("DataParsedAPI","::"+e+"::"+nodeBlogStats.get(e).attr("referenced_by"));

                if(!"".equalsIgnoreCase(nodeBlogStats.get(e).attr("referenced_by").toString())){
                    elementArrayPara.add(nodeBlogStats.get(e));
                    String[] items = nodeBlogStats.get(e).attr("referenced_by").toString().split(",");
                    for(int i=0;i<items.length;i++){
                        hashMap.put(items[i],elementArrayPara.size()-1);
                    }
                }else {
                    elementArrayList.add(nodeBlogStats.get(e));
                }

//            Element div =  row.select("tbody").get(0);
//            Elements rows = div.select("tr");
//            for (Element element : rows) {
//                Log.e("RawPrinted","::"+element.toString());
//                //do stuff here
//            }
            }




            final ArrayList<Qnew> qnews=new ArrayList<>();

            for (int i=0;i<elementArrayList.size();i++) {
                final Qnew qnew=new Qnew();
                qnew.Qid=papers[0].paperId+"_"+i;
                qnew.Pid=papers[0].paperId;
                qnew.Qno=i;


                Document documentQ = Jsoup.parse(elementArrayList.get(i).toString());
//                Elements elementQ = documentQ.getElementsByClass("question");
//                qnew.QData=elementQ.toString();
                Elements elementAnswer = documentQ.getElementsByClass("commenthidden");
                qnew.AnsData=elementAnswer.toString();



//                Log.e("RawPrintedQQ::",i+"::"+elementArrayList.get(i).toString());
                BlankFragment blankFragment = null;
                if(hashMap.containsKey(""+(i+1))){
                    Elements elementQ = elementArrayList.get(i).getElementsByClass("question");
                    Element para=elementArrayPara.get(hashMap.get(""+(i+1)));
                    qnew.QData=para+elementQ.toString();
//                    blankFragment=BlankFragment.newInstance(elementArrayList.get(i).toString(),para+"</br>",this);
                }else {
                    qnew.QData=elementArrayList.get(i).getElementsByClass("question").toString();
//                    blankFragment=BlankFragment.newInstance(elementArrayList.get(i).toString(),"",this);
                }

                Elements elementOptions = documentQ.getElementsByClass("options");

                Element div =  elementOptions.get(0).select("tbody").get(0);
//                Log.e("QdataNew","Div::"+elementOptions.get(0).attr("righ-answer"));
                qnew.RightAns=elementOptions.get(0).attr("righ-answer");

                Elements rows = div.select("tr");

                ArrayList<String> list = new ArrayList<String>();
                for (Element element : rows) {
                    Elements td = element.select("td");
                    list.add(td.text().toString());
                }
                JSONArray jsArray = new JSONArray(list);

                qnew.optionsString=jsArray.toString();

//                Log.e("DataOfQnew::","::"+qnew.Qid+"::"+qnew.QData);
//                Log.e("DataOfQnew::","::"+qnew.Qid+"::"+qnew.Qno+"::"+qnew.Pid+"::"+qnew.optionsString);
//                Log.e("DataOfQnew::","::"+qnew.Qid+"::"+qnew.AnsData);

//                +qnew.Qno+"::"+qnew.QData+"::"+qnew.AnsData+"::"+qnew.optionsString

//            BlankFragment blankFragment=BlankFragment.newInstance(elementArrayList.get(i).toString());
//                sectionPagerAdapter.addFragment(blankFragment);
//              public  String Qid; => paperId+i
//    public  String Pid; =>pid
//    public  String Qno ; => i
//    public  String QData; =>
//    public  String AnsData;
//    public  String optionsString;
//    public  String paraId;
                qnews.add(qnew);
            }

            Realm realm = null;



            try { // I could use try-with-resources here
                realm = Realm.getDefaultInstance();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
//                            for(Paper p:papers) {
                        realm.insertOrUpdate(qnews);
//                            }
                    }
                });
            } finally {
                if(realm != null) {
                    realm.close();
                }
            }

//            ArrayList<Qnew> paperList=new ArrayList<>();
            Realm realm11 = Realm.getDefaultInstance();
            RealmResults<Qnew> paperRealmResults=realm11.where(Qnew.class).findAll();
            for (Qnew paper:paperRealmResults)
            {
                Log.e("DataRecivedQadded", "Sucss::"+paper.Qid+"::"+paper.Qno+"::"+paper.Pid+"::"+paper.optionsString);
//                Log.e("DataRecivedRalmpap", "Sucss::"+paper.paperData);
//                paperList.add(realm11.copyFromRealm(paper));
            }

//            Log.e("DataOfQnew::","::"+qnew.Qid+"::"+qnew.Qno+"::"+qnew.Pid+"::"+qnew.optionsString);

            //elemetn-Q, and para

            //  Document document = Jsoup.parse(this.getArguments().getString("title"));
            //        Elements nodeBlogStats = document.getElementsByClass("question");
            //
            //        Elements answer = document.getElementsByClass("commenthidden");
//Document document = Jsoup.parse(data);
//        Elements nodeBlogStats = document.getElementsByClass("options");
//
//        Log.e("DataRightAns","::"+nodeBlogStats.get(0).attr("righ-answer"));
//
//        RadioGroup ll = new RadioGroup(this.getContext());
//        ll.setOrientation(LinearLayout.VERTICAL);
//
//        Element div =  nodeBlogStats.get(0).select("tbody").get(0);
//        Elements rows = div.select("tr");

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            redirectToScreen();
        }
    }


    //to show data in SqliteHelper
//    public void dumpDb() {
//        try {
//            String inFileName = "/data/data/" + getPackageName() + "/databases/" + DatabaseHelper.DATABASE_NAME;
//            String outFileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + DatabaseHelper.DATABASE_NAME;
//
//            InputStream dbToCreate;
//            dbToCreate = new FileInputStream(inFileName);
//
//            if (new File(inFileName).exists()) {
//                OutputStream myOutput = new FileOutputStream(outFileName);
//                byte[] buffer = new byte[1024];
//                int length;
//                while ((length = dbToCreate.read(buffer)) > 0) {
//                    myOutput.write(buffer, 0, length);
//                }
//                dbToCreate.close();
//                myOutput.flush();
//                myOutput.close();
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    protected void onPause() {
        super.onPause();
//        dumpDb();
    }

    private void redirectToScreen() {
        if (Utils.checkInternet(this)) {
//            String fav_data = Application.getPrefranceData(BestofferConstant.fav_categories);
            Intent intent = null;
            intent = new Intent(SplashScreenActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else
//            startActivity(new Intent(SplashScreenActivity.this, NoInternetConnectionActivity_RestartApp.class));

        finish();

    }


    class setCategoryData extends AsyncTask<DataSnapshot, Void, String> {
        @Override
        protected String doInBackground(DataSnapshot[] params) {
            try {
                DataSnapshot dataSnapshot = params[0];
                Map<String, String> map = (Map<String,String>) dataSnapshot.child(Constants.Papers).getValue();
                Map<String, String> mapNotif= (Map<String,String>) dataSnapshot.child(Constants.Notif).getValue();
                Map<String, Long> notifIdList= (Map<String,Long>) dataSnapshot.child(Constants.NotifIdList).getValue();
                Map<String, Long> paperIdList =(Map<String,Long>) dataSnapshot.child(Constants.paperIdList).getValue();

                final ArrayList<NotifIdList> NotifIds = new ArrayList<>();
                for (Map.Entry<String, Long> notifId : notifIdList.entrySet()){
                    NotifIdList notif=new NotifIdList();
                    notif.id=notifId.getKey();;
                    notif.timestamp=notifId.getValue();
                    NotifIds.add(notif);
                }

                RealmResults<NotifIdList> notifIdListRealmResults1=Realm.getDefaultInstance().where(NotifIdList.class).findAll();
                ArrayList<NotifIdList> notifIdListArrayList=new ArrayList<>();
                notifIdListArrayList.addAll(notifIdListRealmResults1);

                Set set=new HashSet();
                for(int i=0;i<notifIdListArrayList.size();i++){
                    set.add(notifIdListArrayList.get(i).id);
                }

                if(NotifIds.size()>0){
                    for(int i=0;i<NotifIds.size();i++){
                        if(set.contains(NotifIds.get(i))){  //contained  //check time stamp //if time stamp is different than recorded then update paper with that id
                            for(int v=0;v<notifIdListArrayList.size();v++){
                                if(notifIdListArrayList.get(i).id.equalsIgnoreCase(NotifIds.get(i).id)){

                                }
                            }
                        }else {  //not contained //new id --> need to add new Paper to DB
                            getNotifFirebase(NotifIds.get(i).id);
                        }
                    }
                }

                Realm realm2 = null;
                try { // I could use try-with-resources here
                    realm2 = Realm.getDefaultInstance();
                    realm2.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            for(NotifIdList n:NotifIds) {
                                realm.insertOrUpdate(n);
                            }
                        }
                    });
                } finally {
                    if(realm2 != null) {
                        realm2.close();
                    }
                }

                RealmResults<NotifIdList> notifIdListRealmResults=Realm.getDefaultInstance().where(NotifIdList.class).findAll();
                for (NotifIdList notif:notifIdListRealmResults)
                {
                    Log.e("DataRecivedNotifIdList", "Sucss::"+notif.id);
                    Log.e("DataRecivedNotifIdList", "Sucss::"+notif.timestamp);
                }
//        NotifIdList
//                PaperIdList

                //NotifIdList data save to the realm local db
                final ArrayList<PaperIdList> PaperIds = new ArrayList<>();
                for (Map.Entry<String, Long> notifId : paperIdList.entrySet()){
                    //Get user map
                    String key = notifId.getKey();
                    Long value=notifId.getValue();
                    PaperIdList notif=new PaperIdList();
                    notif.id=key;
                    notif.timestamp=value;
                    PaperIds.add(notif);
                }
                Realm realm3 = null;
                try { // I could use try-with-resources here
                    realm3 = Realm.getDefaultInstance();
                    realm3.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            for(PaperIdList n:PaperIds) {
                                realm.insertOrUpdate(n);
                            }
                        }
                    });
                } finally {
                    if(realm3 != null) {
                        realm3.close();
                    }
                }

                RealmResults<PaperIdList> paperIdListRealmResults=Realm.getDefaultInstance().where(PaperIdList.class).findAll();
                for (PaperIdList notif:paperIdListRealmResults)
                {
                    Log.e("DataRecivedPaperIdList", "Sucss::"+notif.id);
                    Log.e("DataRecivedPaperIdList", "Sucss::"+notif.timestamp);
                }




                final ArrayList<Paper> papers = new ArrayList<>();
                for (Map.Entry<String, String> entry : map.entrySet()){
                    //Get user map
                    String key = entry.getKey();
                    String value=entry.getValue();
                    Paper paper=new Paper();
                    paper.paperId=key;
                    paper.paperData=value;
                    papers.add(paper);
                }
                Realm realm = null;
                try { // I could use try-with-resources here
                    realm = Realm.getDefaultInstance();
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            for(Paper p:papers) {
                                realm.insertOrUpdate(p);
                            }
                        }
                    });
                } finally {
                    if(realm != null) {
                        realm.close();
                    }
                }

                RealmResults<Paper> paperRealmResults=Realm.getDefaultInstance().where(Paper.class).findAll();
                for (Paper paper:paperRealmResults)
                {
                    Log.e("DataRecivedRalm", "Sucss::"+paper.paperId);
                    Log.e("DataRecivedRalm", "Sucss::"+paper.paperData);
                }


                final ArrayList<Notif> notifs = new ArrayList<>();
                for (Map.Entry<String, String> entryN : mapNotif.entrySet()){
                    //Get user map
                    String key = entryN.getKey();
                    String value=entryN.getValue();
                    Notif notif=new Notif();
                    notif.notifId=key;
                    notif.notifData=value;
                    notifs.add(notif);
                }
                Realm realm1 = null;
                try { // I could use try-with-resources here
                    realm1 = Realm.getDefaultInstance();
                    realm1.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            for(Notif n:notifs) {
                                realm.insertOrUpdate(n);
                            }
                        }
                    });
                } finally {
                    if(realm1 != null) {
                        realm1.close();
                    }
                }

                RealmResults<Notif> notifRealmResults=Realm.getDefaultInstance().where(Notif.class).findAll();
                for (Notif notif:notifRealmResults)
                {
                    Log.e("DataRecivedRalmNotif", "Sucss::"+notif.notifId);
                    Log.e("DataRecivedRalmNotif", "Sucss::"+notif.notifData);
                }







//                SQLiteDatabase dbTransaction = null;
//                //TODO add image path and object
//                JSONObject data = params[0].getJSONObject(BestofferConstant.data);
//                JSONObject subdata = data.getJSONObject(BestofferConstant.data);
////                JSONObject popup = subdata.getJSONObject("popup");
////                Application.setPreferences("popup",popup.toString());
//                JSONArray categoriesJsonArray = subdata.getJSONArray(BestofferConstant.categories);
//                JSONArray storesJsonArray = subdata.getJSONArray(BestofferConstant.stores);
//                String category_path = subdata.getString(BestofferConstant.category_path) + "/";
//                Application.setPreferences(BestofferConstant.category_path, category_path);
//                String blackimgpathstr = subdata.getString(BestofferConstant.black_icon_path);
////                String redimgpathstr = subdata.getString(BestofferConstant.red_icon_path);
//                String storepath = subdata.getString(BestofferConstant.logo_path);
//                Application.setPreferences(BestofferConstant.store_logo_path, storepath);
//
//
//                if (subdata.has(BestofferConstant.fav_categories)) {
//                    String fav_cat = subdata.getString(BestofferConstant.fav_categories);
//                    Application.setPreferences(BestofferConstant.fav_categories, fav_cat);
//                }
//
//                try {
//                    // start db transaction
//
//                    dbTransaction = Application.getDB().getReadableDatabase();
//                    dbTransaction.beginTransaction();
//
//                    if (categoriesJsonArray.length() > 0) {
//                        try {
//                            List<offers_category> offers_categories_array = Application.getGson().fromJson(categoriesJsonArray.toString(),
//                                    new TypeToken<List<offers_category>>() {
//                                    }.getType());
//
//                            if (offers_categories_array != null && offers_categories_array.size() > 0) {
//                                for (int i = 0; i < offers_categories_array.size(); i++) {
//
//                                    final offers_category dataOfOffer = offers_categories_array.get(i);
//                                    dataOfOffer.setSelected(blackimgpathstr + "/" + dataOfOffer.getImage_url());
//                                    dataOfOffer.setDeselected(blackimgpathstr + "/" + dataOfOffer.getImage_url());
//
//                                    Application.getDB().insertupdate_offers_category(dataOfOffer);
//                                }
//
//                            }
//                        } catch (JsonSyntaxException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//
//
//                    if (storesJsonArray.length() > 0 && storesJsonArray != null) {
//                        try {
//                            List<Stores> stores_array = Application.getGson().fromJson(storesJsonArray.toString(),
//                                    new TypeToken<List<Stores>>() {
//                                    }.getType());
//
//                            if (stores_array != null && stores_array.size() > 0) {
//                                for (int i = 0; i < stores_array.size(); i++) {
//
//                                    final Stores store = stores_array.get(i);
//                                    store.setSocial_icon(storepath + "/" + store.getSocial_icon());
//                                    store.setStore_logo(storepath + "/" + store.getStore_logo());
//
//                                    Application.getDB().insertupdate_Stores(store);
//                                }
//
//                            }
//
//                        } catch (JsonSyntaxException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//
//
//                    dbTransaction.setTransactionSuccessful();
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    dbTransaction.setTransactionSuccessful();
//                } finally {
//                    // end transaction
//                    dbTransaction.endTransaction();
//
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            SPLASH_TIME_OUT = 0;
//                        if (goToHome)
//            gotoHome();
        }
    }

    private void getNotifFirebase(final String s) {
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
//        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                Log.e("DataRecived", "Sucss::"+dataSnapshot.child("Papers").getValue().toString());
//                parseNotifAddToDB(s,dataSnapshot.child(Constants.Notif).child(""+s).getValue(String.class));
                new AddNotifToDB(s,dataSnapshot).execute(dataSnapshot);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("DataRecived", "onCancelled", databaseError.toException());
            }
        });
    }


//    private void parseNotifAddToDB(String s, String value) {
//        if(!"".equalsIgnoreCase(s) && !"".equalsIgnoreCase(value)){
//            new AddNotifToDB().execute(s,v);
//            final Notif notif=new Notif();
////            notif.notifId=s;
////            notif.notifData=value;
////            Realm realm = null;
////            try { // I could use try-with-resources here
////                realm = Realm.getDefaultInstance();
////                realm.executeTransaction(new Realm.Transaction() {
////                    @Override
////                    public void execute(Realm realm) {
////                        realm.insertOrUpdate(notif);
////                    }
////                });
////            } finally {
////                if(realm != null) {
////                    realm.close();
////                }
////            }
//        }
//    }

    private class AddNotifToDB extends AsyncTask<DataSnapshot, Void, Long> {
        String key;DataSnapshot dataSnapshot;
        public AddNotifToDB(String s,DataSnapshot v) {
        this.key=s;
        this.dataSnapshot=v;
        }

        protected Long doInBackground(DataSnapshot s[]) {
            Realm realm = Realm.getDefaultInstance();
            try {
                String key_value=dataSnapshot.child(Constants.Notif).child(""+s).getValue(String.class);
                if(!"".equalsIgnoreCase(key) && !"".equalsIgnoreCase(key_value)){
                    final Notif notif=new Notif();
                    notif.notifId=key;
                    notif.notifData=key_value;
                    realm.insertOrUpdate(notif);
                }
                return null;
            }catch (Exception e){
                e.printStackTrace();
            } finally {
                realm.close();
            }
            return null;
        }

        protected void onPostExecute(Long orderId) {
            // Back on the Android mainThread
            // do something with orderId such as query Realm
            // for the order and perform some operation with it.
        }
    }

//    protected Void doInBackground(Void... params) {
//    Realm realm = Realm.getDefaultInstance();
//    try {
//        // ... Use the Realm instance ...
//    } finally {
//        realm.close();
//    }
//
//    return null;
//}

    private void AddPaperToDB(String s, String value) {
        if(!"".equalsIgnoreCase(s) && !"".equalsIgnoreCase(value)){
            final Paper paper=new Paper();
            paper.paperId=s;
            paper.paperData=value;
            Realm realm = null;
            try { // I could use try-with-resources here
                realm = Realm.getDefaultInstance();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.insertOrUpdate(paper);
                    }
                });
            } finally {
                if(realm != null) {
                    realm.close();
                }
            }
        }
    }

    private void setApplicationVersion() {
        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String versionStr = "Version " + pInfo.versionName;
            txtVersion.setText(versionStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy(); }


//    public Action getIndexApiAction() {
//        return Actions.newView("SplashScreen Page", Uri.parse("http://[ENTER-YOUR-URL-HERE]").toString());
//    }
}
