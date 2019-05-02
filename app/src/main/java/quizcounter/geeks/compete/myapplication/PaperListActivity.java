package quizcounter.geeks.compete.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import quizcounter.geeks.compete.myapplication.Notifications.NotificationListItemRecyclerViewAdapter;
import quizcounter.geeks.compete.myapplication.RealmUtils.Notif;
import quizcounter.geeks.compete.myapplication.RealmUtils.Paper;
import quizcounter.geeks.compete.myapplication.Utils.Constants;

public class PaperListActivity extends AppCompatActivity {

    LinearLayoutManager layoutManager;
    PaperListAdapter notificationAdapter;
//    TextView txtnoNotification;
    RecyclerView notificationList;
    String type="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paper_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        notificationList = findViewById(R.id.paperList);
//        setSupportActionBar(toolbar);
        initview();
        getPaperFromDB();
    }

    private void getPaperFromDB() {
        try {
            if(getIntent().hasExtra(Constants.type)){
                type=getIntent().getStringExtra(Constants.type);
                Log.e("TypeExrraRecived","::"+type);
            }

//            RealmResults<Paper> notifRealmResults=Realm.getDefaultInstance().where(Paper.class).findAll();
//            setNotificationAdapter(notifRealmResults);
            setNotificationAdapter();
//            initDB();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //set notification list adapter
//    private void setNotificationAdapter( RealmResults<Paper> notifRealmResults) {
    private void setNotificationAdapter() {
        try {

            if (notificationAdapter == null) {
                notificationAdapter = new PaperListAdapter(this);//PaperListActivity.
                notificationList.setAdapter(notificationAdapter);
            } else {
                notificationList.setAdapter(notificationAdapter);
            }

            try {
                if(type.equalsIgnoreCase(Constants.Notifications)){
                    RealmResults<Notif> notifRealmResults=Realm.getDefaultInstance().where(Notif.class).findAll();
                    ArrayList<Object> notifs=new ArrayList<>();
                    notifs.addAll(notifRealmResults);
                    if (notifs != null && notifs.size() > 0) {
                        notificationAdapter.addNotification(notifs,0);
                        notificationList.setVisibility(View.VISIBLE);
//                    noResultFound.setVisibility(View.GONE);
                    } else {
                        notificationList.setVisibility(View.GONE);
//                    noResultFound.setVisibility(View.VISIBLE);
                    }
                }else {
                    RealmResults<Paper> notifRealmResults=Realm.getDefaultInstance().where(Paper.class).findAll();
                    ArrayList<Object> notifs=new ArrayList<>();
                    notifs.addAll(notifRealmResults);
                    if (notifs != null && notifs.size() > 0) {
                        notificationAdapter.addNotification(notifs,1);
                        notificationList.setVisibility(View.VISIBLE);
//                    noResultFound.setVisibility(View.GONE);
                    } else {
                        notificationList.setVisibility(View.GONE);
//                    noResultFound.setVisibility(View.VISIBLE);
                    }
                }
//                RealmResults<Paper> notifRealmResults=Realm.getDefaultInstance().where(Paper.class).findAll();
//                ArrayList<Paper> notifs=new ArrayList<>();
//                notifs.addAll(notifRealmResults);
//                if (notifs != null && notifs.size() > 0) {
//
//                    notificationAdapter.addNotification(notifs);
//                    notificationList.setVisibility(View.VISIBLE);
////                    noResultFound.setVisibility(View.GONE);
//                } else {
//                    notificationList.setVisibility(View.GONE);
////                    noResultFound.setVisibility(View.VISIBLE);
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initview() {
        try {
            //set recvycler view
            layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            layoutManager.setStackFromEnd(true);
            layoutManager.setReverseLayout(true);
            notificationList.setLayoutManager(layoutManager);
            notificationList.setNestedScrollingEnabled(true);
            notificationList.setHasFixedSize(true);
//            txtnoNotification.setText(getResources().getString(R.string.no_notification));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void onClikitem(String id){
        Intent intent=new Intent(this,TestActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
