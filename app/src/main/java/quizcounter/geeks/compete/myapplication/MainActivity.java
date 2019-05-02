package quizcounter.geeks.compete.myapplication;

import android.app.Application;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;


import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.support.design.widget.NavigationView;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;
import quizcounter.geeks.compete.myapplication.Notifications.NotificationListItemFragment;
import quizcounter.geeks.compete.myapplication.RealmUtils.Notif;
import quizcounter.geeks.compete.myapplication.RealmUtils.NotifIdList;
import quizcounter.geeks.compete.myapplication.RealmUtils.Paper;
import quizcounter.geeks.compete.myapplication.RealmUtils.PaperIdList;
import quizcounter.geeks.compete.myapplication.Utils.Constants;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, ClickPaper {
    ViewPager viewPager;
    String APIResponse;
    SectionPagerAdapter sectionPagerAdapter;
    private DatabaseReference mDatabase;
    Dialog dialog;
    //    FrameLayout frame_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        frame_main = findViewById(R.id.frame_main);
//        openUpdatePopUp();
        MobileAds.initialize(this, getResources().getString(R.string.YOUR_ADMOB_APP_ID));

        HomeFragment homeFragment=new HomeFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_main, homeFragment)
                .addToBackStack(homeFragment.getClass().getSimpleName())
                .commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        viewPager=findViewById(R.id.viewpager_main);

        sectionPagerAdapter=new SectionPagerAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(50);


//        try(Realm realmInstance = Realm.getDefaultInstance()) {
//            realmInstance.executeTransaction((realm) -> realm.insertOrUpdate(dog));
//        }

//        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int i, float v, int i1) {
//
//            }
//
//            @Override
//            public void onPageSelected(int i) {  }
//
//            @Override
//            public void onPageScrollStateChanged(int i) {  }
//        });

//        NetworkUtility.makeSimplerequest("https://geekscompete.blogspot.com/2018/11/ugc-net-july-2018-solved-paper-1.html", new VolleyCallBack() {
//            @Override
//            public void onSuccess(String result) {
//                Log.e("CalledAPI","::"+"onSuccess");
//            APIResponse=result;
//            parsethedataReponse();
//            }
//        });


//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

//        getPostData();

//        mDatabase = FirebaseDatabase.getInstance().getReference("UGC_NET_Paper_1_July_2018");

//        mDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Log.e("DataRecived::","::"+dataSnapshot.getValue(String.class));
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.e("DataRecived::","Error::"+databaseError.toString());
//            }
//        });

        mDatabase = FirebaseDatabase.getInstance().getReference();
//        DatabaseReference ref = mDatabase.child("UGC_NET_Paper_1_July_2018");

//        Log.e("DataRecived","::"+ref.child("desc"));

//        Query phoneQuery = ref.orderByChild(phoneNo).equalTo("+923336091371");
//        ref.child("desc").addValueEventListener(new ValueEventListener() {
//        mDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
////                Log.e("DataRecived", "Sucss::"+dataSnapshot.child("Papers").getValue().toString());
//
////                collectDataPandN((Map<String,String>) dataSnapshot.child(Constants.Papers).getValue(),(Map<String,String>) dataSnapshot.child(Constants.Notif).getValue()
////                        ,(Map<String,Long>) dataSnapshot.child(Constants.NotifIdList).getValue(),(Map<String,Long>) dataSnapshot.child(Constants.paperIdList).getValue());
//
//                //                collectDataP((Map<String,String>) dataSnapshot.child("Notif").getValue());
////                Log.e("DataRecived", "Sucss::"+dataSnapshot.child("UGC_NET_Paper_1_July_2018").child("desc").getValue(String.class));
////                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
////                    user = singleSnapshot.getValue(User.class); //                }
////                APIResponse=dataSnapshot.getValue(String.class);
////                APIResponse=dataSnapshot.child("Papers").child("UGC_NET_Paper_1_July_2018").child("desc").getValue(String.class);
////                NotifIdList //paperIdList //                Map<String,String> NotifIdList=(Map<String,String>) dataSnapshot.child("NotifIdList").getValue();
////                Map<String,String> paperIdList=(Map<String,String>) dataSnapshot.child("paperIdList").getValue();
////                Log.e("DataRecivedNotifIdList", "onCancelled"+NotifIdList.toString());
////                Log.e("DataRecivedNotifIdList", "onCancelled"+paperIdList.toString());
////            parsethedataReponse();
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.e("DataRecived", "onCancelled", databaseError.toException());
//            }
//        });
    }


    private void collectDataPandN(Map<String,String> map,Map<String,String> mapNotif,Map<String,Long> notifIdList,Map<String,Long> paperIdList) {
        //NotifIdList data save to the realm local db
        final ArrayList<NotifIdList> NotifIds = new ArrayList<>();
        for (Map.Entry<String, Long> notifId : notifIdList.entrySet()){
            NotifIdList notif=new NotifIdList();
            notif.id=notifId.getKey();;
            notif.timestamp=notifId.getValue();
            NotifIds.add(notif);
        }

        RealmResults<NotifIdList> notifIdListRealmResults1=Realm.getDefaultInstance().where(NotifIdList.class).findAllAsync();
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

        RealmResults<NotifIdList> notifIdListRealmResults=Realm.getDefaultInstance().where(NotifIdList.class).findAllAsync();
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

        RealmResults<PaperIdList> paperIdListRealmResults=Realm.getDefaultInstance().where(PaperIdList.class).findAllAsync();
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

        RealmResults<Paper> paperRealmResults=Realm.getDefaultInstance().where(Paper.class).findAllAsync();
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

        RealmResults<Notif> notifRealmResults=Realm.getDefaultInstance().where(Notif.class).findAllAsync();
        for (Notif notif:notifRealmResults)
        {
            Log.e("DataRecivedRalmNotif", "Sucss::"+notif.notifId);
            Log.e("DataRecivedRalmNotif", "Sucss::"+notif.notifData);
        }




//        Log.e("DataRecived", "Sucss::"+papers.get(0).paperId);
//        Log.e("DataRecived", "Sucss::"+papers.get(1).paperId);
//        Log.e("DataRecived", "Sucss::"+papers.get(0).paperData);
//        Log.e("DataRecived", "Sucss::"+papers.get(1).paperData);

//        System.out.println(papers.toString());
    }

    private void getNotifFirebase(final String s) {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                Log.e("DataRecived", "Sucss::"+dataSnapshot.child("Papers").getValue().toString());

                parseNotifAddToDB(s,dataSnapshot.child(Constants.Notif).child(""+s).getValue(String.class));
          }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("DataRecived", "onCancelled", databaseError.toException());
            }
        });
    }

    private void parseNotifAddToDB(String s, String value) {
        if(!"".equalsIgnoreCase(s) && !"".equalsIgnoreCase(value)){
        final Notif notif=new Notif();
        notif.notifId=s;
        notif.notifData=value;
        Realm realm = null;
        try { // I could use try-with-resources here
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                        realm.insertOrUpdate(notif);
                }
            });
        } finally {
            if(realm != null) {
                realm.close();
            }
        }
      }
    }

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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void getPostData() {

        //API key : AIzaSyDQ0ypPCiHy1BaNcA2Ev5kg8GgNdsZ6U-k
//        /https://www.googleapis.com/blogger/v3/blogs/2959264048547561683/posts/bypath
//id:  2959264048547561683
//?key=YOUR-API-KEY
//        https://www.googleapis.com/blogger/v3/blogs/2399953/posts/bypath?path=/2011/08/latest-updates-august-1st.html&key=YOUR-API-KEY

//        NetworkUtility.makeSimplerequest("https://geekscompete.blogspot.com/2018/11/ugc-net-july-2018-solved-paper-1.html", new VolleyCallBack() {
//        NetworkUtility.makeSimplerequest("https://www.googleapis.com/blogger/v3/blogs/2959264048547561683/posts/bypath?path=/2019/04/ugc-net-cs-2018-july-ii-question-22_97.html&key=AIzaSyDQ0ypPCiHy1BaNcA2Ev5kg8GgNdsZ6U-k", new VolleyCallBack() {
//        NetworkUtility.makeSimplerequest("https://www.googleapis.com/blogger/v3/blogs/2959264048547561683/posts/bypath?path=/2019/04/ugc-net-cs-2018-july-ii-question-22_97.html&key=AIzaSyDQ0ypPCiHy1BaNcA2Ev5kg8GgNdsZ6U-k", new VolleyCallBack() {
//            @Override
//            public void onSuccess(String result) {
//                Log.e("CalledAPI","::"+"onSuccess"+result);
//            APIResponse=result;
////            parsethedataReponse();
//            }
//        });

        // The BlogId for the http://buzz.blogger.com/ blog.
//        String BUZZ_BLOG_ID = "2399953";
//
//// The URL path component for a buzz post.
//        String BUZZ_POST_PATH = "/2012/01/engage-with-your-readers-through.html";
//
//// Configure the Java API Client for Installed Native App
//        HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
//        JsonFactory JSON_FACTORY = new JacksonFactory();
//
//// Configure the Installed App OAuth2 flow.
//        Credential credential = OAuth2Native.authorize(HTTP_TRANSPORT,
//                JSON_FACTORY, new LocalServerReceiver(),
//                Arrays.asList(BloggerScopes.BLOGGER));
//
//// Construct the Blogger API access facade object.
//        Blogger blogger = Blogger.builder(HTTP_TRANSPORT, JSON_FACTORY)
//                .setApplicationName("Blogger-PostsGetByPath-Snippet/1.0")
//                .setHttpRequestInitializer(credential).build();
//
//// The request action.
//        Blogger.Posts.GetByPath postsGetByPathAction = blogger.posts().getByPath(BUZZ_BLOG_ID);
//        postsGetByPathAction.setPath(BUZZ_POST_PATH);
//
//// Restrict the result content to just the data we need.
//        postsGetByPathAction.setFields("content,published,title");
//
//// This step sends the request to the server.
//        Post post = postsGetByPathAction.execute();
//
//// Now we can navigate the response.
//        System.out.println("Title: " + post.getTitle());
//        System.out.println("Published: " + post.getPublished());
//        System.out.println("Content: " + post.getContent());
    }

    void parsethedataReponse(){
//        final Paper paper=new Paper();
//        paper.paperId="1";
//        paper.paperData="";
//
//        Realm realm = null;
//        try { // I could use try-with-resources here
//            realm = Realm.getDefaultInstance();
//            realm.executeTransaction(new Realm.Transaction() {
//                @Override
//                public void execute(Realm realm) {
//                    realm.insertOrUpdate(paper);
//                }
//            });
//        } finally {
//            if(realm != null) {
//                realm.close();
//            }
//        }

        Document document = Jsoup.parse(APIResponse);
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

        for (int i=0;i<elementArrayList.size();i++) {
            BlankFragment blankFragment = null;
                if(hashMap.containsKey(""+(i+1))){
                Element para=elementArrayPara.get(hashMap.get(""+(i+1)));
//                blankFragment=BlankFragment.newInstance(elementArrayList.get(i).toString(),para+"</br>",this);
            }else {
//                blankFragment=BlankFragment.newInstance(elementArrayList.get(i).toString(),"",this);
            }


//            BlankFragment blankFragment=BlankFragment.newInstance(elementArrayList.get(i).toString());
//            sectionPagerAdapter.addFragment(blankFragment);
        }

//        viewPager.setAdapter(sectionPagerAdapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
    {
        // Handle navigation view item clicks here.
        int id = menuItem.getItemId();
        final String appPackageName = MyApplication.get().getPackageName();

        if (id == R.id.nav_home) {
//            Intent intent=new Intent(this,PaperListActivity.class);
//            intent.putExtra(Constants.type,Constants.Notifications);
//            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.nav_notif) {
            Intent intent=new Intent(this,PaperListActivity.class);
            intent.putExtra(Constants.type,Constants.Notifications);
            startActivity(intent);
        } else if (id == R.id.nav_papers) {
            Intent intent=new Intent(this,PaperListActivity.class);
            intent.putExtra(Constants.type,Constants.Papers);
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            String sub = "Hey, Get this application for UGC NET & ISRO updates & Previous year papers and More.\n";
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, sub);
            i.putExtra(Intent.EXTRA_TEXT, sub + "\n" + Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName));
//            i.putExtra(Intent.EXTRA_TEXT, sub + "\n" + "https://goo.gl/JtjJWE");
            startActivity(Intent.createChooser(i, "Share URL"));
        }
//        else if (id == R.id.nav_rate_us ){
//            try {
//                this.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
//            } catch (android.content.ActivityNotFoundException anfe) {
//                this.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
//            }
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
//        return false;
    }

    @Override
    public void onClickPaperItem() {

    }

    public class SectionPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            return mFragmentList.get(position);
        }

        public void addFragment(Fragment fragment) {
            mFragmentList.add(fragment);
//            mFragmentTitleList.add(title);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public void onItemClick(int i){
        viewPager.setCurrentItem(viewPager.getCurrentItem()+i);
    }

    void replaceFragment(){
        NotificationListItemFragment listItemFragment = new NotificationListItemFragment();
        try {
//                listItemFragment = (NotificationListItemFragment) getSupportFragmentManager().findFragmentById(R.id.frame_main);
//                if (listItemFragment instanceof NotificationListItemFragment) {
//                    listItemFragment.refreshMenu();
//                }
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, listItemFragment).addToBackStack(listItemFragment.getClass().getSimpleName()).commit();
        } catch (ClassCastException e) {
            e.printStackTrace();
            // not that fragment
        }
    }

    private void openUpdatePopUp() {
        try {
//            Log.e("popUpPojoURLTOCOPY", "::" + popUpPojo.getImage());
            dialog = new Dialog(MainActivity.this, R.style.AppTheme);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.update_application_layout);
//            dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#66000000")));
//            dialog.getWindow().setBackgroundDrawable();
            Button btn_update_app = dialog.findViewById(R.id.update_application_update_button1);
            TextView tv_update_message = dialog.findViewById(R.id.update_application_update_message);
//            tv_update_message.setText(Application.getPrefranceData(force_update_message));
            btn_update_app.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        updateApplication();
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateApplication() {
        final String appPackageName = getPackageName(); // package name of the app
        try {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
