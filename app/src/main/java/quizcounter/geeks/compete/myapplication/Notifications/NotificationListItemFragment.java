package quizcounter.geeks.compete.myapplication.Notifications;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import quizcounter.geeks.compete.myapplication.R;
import quizcounter.geeks.compete.myapplication.RealmUtils.Notif;


public class NotificationListItemFragment extends Fragment {
// extends BaseFragment


    LinearLayoutManager layoutManager;
    NotificationListItemRecyclerViewAdapter notificationAdapter;
//    @BindView(R.id.no_result_text)
    TextView txtnoNotification;

//    @BindView(R.id.no_result_found)
//    FrameLayout noResultFound;

//    @BindView(R.id.notificationList)
    RecyclerView notificationList;

//    @BindView(R.id.notiswipeRefreshLayout)
//    SwipeRefreshLayout swipeRefreshLayout;

//    Unbinder unbinder;
//    @BindView(R.id.ad_banner_view_main)

//    AdView adBannerViewMain;
//    @BindView(R.id.ad_layout)

    LinearLayout adLayout;
    private List<Notif> notificationArray;
//    private JSONObject messageJSOn = null;
//    private NotificationPOJO notificationDatapojo = new NotificationPOJO();

    private notification_itemclick notification_itemclick;

    public NotificationListItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static NotificationListItemFragment newInstance(String notificationdataobject) {
//        notificationObject = notificationdataobject;
//        NotificationListItemFragment fragment = new NotificationListItemFragment();
//        return fragment;
        return new NotificationListItemFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        ConstantFunctions.setNotificationCount(0);
//        Application.setPreferences(CommanConstants.notification_count, "0");
//        Log.e("notifListCalled","NotificationListItemFragment_onCreate" );
//        Log.e("notifListitmCalled", "NotificationListItemFragment_onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notificationlistitem_list, container, false);
//        unbinder = ButterKnife.bind(this, view);
//        adBannerViewMain.setVisibility(View.VISIBLE);
//        AdSetup.setAd(adBannerViewMain);
        notificationList=view.findViewById(R.id.notificationList);
        notificationList=view.findViewById(R.id.notificationList);
        notificationList=view.findViewById(R.id.notificationList);
        notificationList=view.findViewById(R.id.notificationList);
        notificationList=view.findViewById(R.id.notificationList);

        initview(view);
//        Application.setPreferences(CommanConstants.CURRUENT_FRAGMENT, CommanConstants.NOTIFICATIONLIST_FRAGMENT);
//        notificationArray = new ArrayList();
//        getNotificationFromDB();
//        setPullToRefresh();

//        ItemClickSupport.addTo(notificationList).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
//            @Override
//            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
//                try {
//
//                    Log.e("notifListCalled", "Clickedd Item");
//                    notification_itemclick.notificationData(notificationAdapter.getNotificationpos(position).getDataobject());
//                    //delete notification from db as well as from list
////                    deletefromlist(notificationAdapter.getNotificationpos(position).getId(), position);
//                    updatefromlist(notificationAdapter.getNotificationpos(position).getId(), position);
////                    getActivity().finish();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
        return view;
    }

//    public void openAlertdialoge() {
//        try {
//            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
//            alertDialog.setMessage(CommanConstants.clearnotifications);
//            alertDialog.setCancelable(true);
//
//            alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int id) {
//                    dialog.dismiss();
//                    clearallfromDB();
//                    ConstantFunctions.setNotificationCount(0);
//                    Application.setPreferences(CommanConstants.notification_count, "0");
//
//                }
//            });
//            alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int id) {
//                    dialog.dismiss();
//                }
//            });
//            AlertDialog alert11 = alertDialog.create();
//            alert11.show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    public void clearallfromDB() {
//        try {
//        try {
//            dbTransaction = Application.getDB().getReadableDatabase();
//            dbTransaction.beginTransaction();
//            //delete
//            Application.getDB().deleteAll(Application.getDB());
//            notificationAdapter.notifyDataSetChanged();
//            dbTransaction.setTransactionSuccessful();
//
//            noResultFound.setVisibility(View.VISIBLE);
//
//            notificationList.setVisibility(View.GONE);
//            setActionsearch(actionclearAll, false);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            if(dbTransaction != null && dbTransaction.inTransaction()){//inTransation
//                dbTransaction.setTransactionSuccessful();
//            }
//
//        } finally {
//            // end transaction
//            if(dbTransaction != null && dbTransaction.inTransaction()) {//inTransation
//                dbTransaction.endTransaction();
//            }
//        }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }

    private void getNotificationFromDB() {
        try {
            RealmResults<Notif> notifRealmResults=Realm.getDefaultInstance().where(Notif.class).findAllAsync();
            setNotificationAdapter(notifRealmResults);
//            initDB();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //set notification list adapter
    private void setNotificationAdapter( RealmResults<Notif> notifRealmResults) {
        try {
            ArrayList<Notif> notifs=new ArrayList<>();
            notifs.addAll(notifRealmResults);

            if (notificationAdapter == null) {
                notificationAdapter = new NotificationListItemRecyclerViewAdapter(getActivity());
                notificationList.setAdapter(notificationAdapter);
            } else {
                notificationList.setAdapter(notificationAdapter);

            }

            try {
                if (notifs != null && notifs.size() > 0) {
                    notificationAdapter.addNotification(notifs);
                    notificationList.setVisibility(View.VISIBLE);
//                    noResultFound.setVisibility(View.GONE);
                } else {
                    notificationList.setVisibility(View.GONE);
//                    noResultFound.setVisibility(View.VISIBLE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //set ui of list
    private void initview(View view) {
        try {
            //set recvycler view
            layoutManager = new LinearLayoutManager(getActivity());
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

    //get all notification list from internal db
//    private void initDB() {
//        try {
//            if (notificationAdapter.getItemCount() > 0) {
//                notificationAdapter.clearAllList();
//                notificationArray.clear();
//            }
//            dbTransaction = Application.getDB().getReadableDatabase();
//            Log.e("NotificationListItemF", "::" + "beginTrnas");
//            dbTransaction.beginTransaction();
//            /**
//             * initialize database
//             */
//
//            List<NotificationPOJO> notificationPOJOS=getAllNotificationList(Application.getDB());
//
//            if(notificationPOJOS.size()>0){
//                notificationArray.addAll(notificationPOJOS);
//            }
//
////            for (NotificationPOJO notificationpojo : getAllNotificationList(Application.getDB())) {
////                notificationArray.add(notificationpojo);
////            }
//
//            setNotificationList();
//            dbTransaction.setTransactionSuccessful();
//        } catch (Exception e) {
//            e.printStackTrace();
//            dbTransaction.setTransactionSuccessful();
//
//        } finally {
//            // end transaction
//            dbTransaction.endTransaction();
//            Log.e("NotificationListItemF1", "::" + "endTrnas");
//        }
//        swipeRefreshLayout.setRefreshing(false);
//    }

    //set list in recyclerview


//    private void setPullToRefresh() {
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                notificationArray.clear();
//                getNotificationFromDB();
////                ConstantFunctions.setNotificationCount(0);
////                Application.setPreferences(CommanConstants.notification_count, "0");
////                setActionsearch(notification, false);
//            }
//        });
//    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//            notification_itemclick = (notification_itemclick) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
//        }
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        Activity activity;
//        if (context instanceof Activity){
//            activity=(Activity) context;
//            try {
//                notification_itemclick = (notification_itemclick)activity;
//            } catch (ClassCastException e) {
//                throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
//            }
//        }
//    }
//
    @Override
    public void onDetach() {
        super.onDetach();
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == CommanConstants.notificationdataconstant) {
//            if (data != null) {
//                Bundle notificationDataIntent = data.getExtras();
//                if (notificationDataIntent != null) {
//                    if (notificationDataIntent.containsKey(FCMConstancts.notification_data)) {
//                        String notificationmsg = null;
//                        notificationmsg = notificationDataIntent.getString(FCMConstancts.notification_data);
//                        try {
////                        i1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            JSONObject messageJSOn = new JSONObject(notificationmsg);
////                            notificationDatapojo = getNotificationObject(messageJSOn.toString());
////                            notificationDatapojo.setUrl("http://www.theappguruz.com/blog/android-facebook-integration-tutorial");
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }
//    }

    //update item on click of item
//    private void updatefromlist(int id, int position) {
//        try {
//            dbTransaction = Application.getDB().getReadableDatabase();
//            dbTransaction.beginTransaction();
//            NotificationPOJO notificationPOJO = Application.getDB().updateNotification(Application.getDB(), id);
//            notificationAdapter.updatefromdb(position);
//            if (notificationAdapter.getItemCount() > 0) {
//                notificationList.setVisibility(View.VISIBLE);
//                noResultFound.setVisibility(View.GONE);
//            } else {
//                notificationList.setVisibility(View.GONE);
//                noResultFound.setVisibility(View.VISIBLE);
//            }
////            notificationAdapter.notifyDataSetChanged();
//            dbTransaction.setTransactionSuccessful();
//        } catch (Exception e) {
//            e.printStackTrace();
//            dbTransaction.setTransactionSuccessful();
//        } finally {
//            // end transaction
//            dbTransaction.endTransaction();
//        }
//    }

    @Override
    public void onResume() {
        super.onResume();

//        setActionsearch(notification, false);
//        setActionsearch(action_location, false);
//        toolbarsetCityLocationMenu( 0);
//        setToolbarTitle(getString(R.string.Notifications));
//        onBackPress();
//
//        Log.e("notifListitmCalled","NotificationListItemFragment_onResume" );
////        notification.setEnabled(false);
//
//        if (notificationAdapter != null && notificationAdapter.getItemCount() > 0) {
//            setNotificationAdapter();
//            setActionsearch(actionclearAll, true);
//        } else {
//            setActionsearch(actionclearAll, false);
//        }
        refreshMenu();
//        onBackPress();
    }

    public void refreshMenu() {
//        setActionsearch(notification, false);
//        setActionsearch(action_location, false);
//        toolbarsetCityLocationMenu(0);
//        setToolbarTitle(getString(R.string.Notifications));
        Log.e("notifListitmCalled", "NotificationListItemFragment_onResume");
//        notification.setEnabled(false);

//        if (notificationAdapter != null && notificationAdapter.getItemCount() > 0) {
//            setNotificationAdapter();
            getNotificationFromDB();
//            setActionsearch(actionclearAll, true);
//        } else {
//            setActionsearch(actionclearAll, false);
//        }
    }

    @Override
    public void onPause() {
        super.onPause();
//        setActionsearch(action_location, false);
//        notification.setEnabled(true);
//        setActionsearch(actionclearAll, true);

//        setActionsearch(actionsearch, false);
//        setActionsearch(actionclearAll, false);
//
//        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        activity.getSupportActionBar().setDisplayShowHomeEnabled(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        setActionsearch(actionclearAll, false);
//        setActionsearch(actionclearAll, false);


    }

    @Override
    public void onStart() {
        super.onStart();

        //commented on 13-7-2018 pradip
        //        if (analytics != null)
//            analytics.screenNavigated(CommanConstants.NotificationListItemFragment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        unbinder.unbind();
    }
    //
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        getActivity().getMenuInflater().inflate(R.menu.notification_menu, menu);
//
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_clear_all:
//                openAlertdialoge();
//                return true;
//        }
//
//            return super.onOptionsItemSelected(item);
//    }

    //delete item on click of item
//    private void deletefromlist(int id, int position) {
//        try {
//            dbTransaction = Application.getDB().getReadableDatabase();
//            dbTransaction.beginTransaction();
//            NotificationPOJO notificationPOJO = Application.getDB().deleteNotification(Application.getDB(), id);
//
//            notificationAdapter.removefromdb(position);
//            if (notificationAdapter.getItemCount() > 0) {
//                notificationList.setVisibility(View.VISIBLE);
//                noResultFound.setVisibility(View.GONE);
//
//
//                setActionsearch(actionclearAll, true);
//            } else {
//                notificationList.setVisibility(View.GONE);
//                noResultFound.setVisibility(View.VISIBLE);
//
//                setActionsearch(actionclearAll, false);
//            }
//            notificationAdapter.notifyDataSetChanged();
//            dbTransaction.setTransactionSuccessful();
//        } catch (Exception e) {
//            e.printStackTrace();
//            dbTransaction.setTransactionSuccessful();
//        } finally {
//            // end transaction
//            dbTransaction.endTransaction();
//        }
//    }

}
