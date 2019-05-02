package quizcounter.geeks.compete.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import quizcounter.geeks.compete.myapplication.RealmUtils.HomeItemModel;
import quizcounter.geeks.compete.myapplication.RealmUtils.NotifIdList;
import quizcounter.geeks.compete.myapplication.RealmUtils.Paper;
import quizcounter.geeks.compete.myapplication.RealmUtils.PaperIdList;
import quizcounter.geeks.compete.myapplication.Utils.Constants;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
//    TextView btn_notif_more_home;
//    TextView btn_papers_more_home;
    RecyclerView rv_home;
    ArrayList<HomeItemModel> homeItemModels=new ArrayList<>();
    LinearLayoutManager layoutManager;
    //    HomePageAdapter homePageAdapter;
    HomePageAdapter homePageAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);

        rv_home=view.findViewById(R.id.rv_home);
        initview();


        initialiseData();

        setHomePageAdapter();
//        btn_notif_more_home=view.findViewById(R.id.btn_notif_more_home1);
//        btn_papers_more_home=view.findViewById(R.id.btn_papers_more_home);
//        return inflater.inflate(R.layout.fragment_home, container, false);
//        btn_notif_more_home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                ((MainActivity)getActivity()).replaceFragment();
////                NotificationListItemFragment notificationListItemFragment=new NotificationListItemFragment();
////                getSupportFragmentManager()
////                        .beginTransaction()
////                        .replace(R.id.frame_main, homeFragment)
////                        .addToBackStack(homeFragment.getClass().getSimpleName())
////                        .commit();
//            }
//        });
//
//        btn_papers_more_home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(),PaperListActivity.class));
//            }
//        });

        return view;
    }

    private void initialiseData() {
        HomeItemModel homeItemModelPapers=new HomeItemModel();
        homeItemModelPapers.title= Constants.Papers;
        RealmResults<PaperIdList> paperIdListRealmResults1= Realm.getDefaultInstance().where(PaperIdList.class).findAll();
        homeItemModelPapers.itemIds.add(paperIdListRealmResults1.get(0).id);
        homeItemModelPapers.itemIds.add(paperIdListRealmResults1.get(1).id);

        HomeItemModel homeItemModelNotif=new HomeItemModel();
        homeItemModelNotif.title=Constants.Notifications;
        RealmResults<NotifIdList> notifIdListRealmResults1= Realm.getDefaultInstance().where(NotifIdList.class).findAll();
        homeItemModelNotif.itemIds.add(notifIdListRealmResults1.get(0).id);
        homeItemModelNotif.itemIds.add(notifIdListRealmResults1.get(1).id);
        homeItemModels.add(homeItemModelPapers);
        homeItemModels.add(homeItemModelNotif);
//        HomeItemModel homeItemModelNotif=new HomeItemModel();
//        homeItemModelNotif.title="Notifications";
//        RealmResults<NotifIdList> notifIdListRealmResults1= Realm.getDefaultInstance().where(NotifIdList.class).findAllAsync();
//        homeItemModelNotif.itemIds.add(notifIdListRealmResults1.get(0).id);
//        homeItemModelNotif.itemIds.add(notifIdListRealmResults1.get(1).id);

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void setHomePageAdapter() {
        try {
//            ArrayList<HomeItemModel> homeItemModels=new ArrayList<>();
////            notifs.addAll(notifRealmResults);
//            HomeItemModel homeItemModel=new HomeItemModel();
//            homeItemModel.title="Notificaitons";
//
//            HomeItemModel homeItemModel1=new HomeItemModel();
//            homeItemModel1.title="Papers";
//            homeItemModels.add(homeItemModel);
//            homeItemModels.add(homeItemModel1);

            if (homePageAdapter == null) {
                homePageAdapter = new HomePageAdapter(this.getActivity());//PaperListActivity.
                rv_home.setAdapter(homePageAdapter);
            } else {
                rv_home.setAdapter(homePageAdapter);
            }
            try {
                if (homeItemModels != null && homeItemModels.size() > 0) {
                    homePageAdapter.addNotification(homeItemModels);
                    rv_home.setVisibility(View.VISIBLE);
//                    noResultFound.setVisibility(View.GONE);
                } else {
                    rv_home.setVisibility(View.GONE);
//                    noResultFound.setVisibility(View.VISIBLE);
                }
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
            layoutManager = new LinearLayoutManager(this.getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            layoutManager.setStackFromEnd(true);
            layoutManager.setReverseLayout(true);
            rv_home.setLayoutManager(layoutManager);
            rv_home.setNestedScrollingEnabled(true);
            rv_home.setHasFixedSize(true);
//            txtnoNotification.setText(getResources().getString(R.string.no_notification));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
