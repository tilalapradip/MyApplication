package quizcounter.geeks.compete.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import quizcounter.geeks.compete.myapplication.CustomeView.ItemClickSupport;
import quizcounter.geeks.compete.myapplication.RealmUtils.Paper;
import quizcounter.geeks.compete.myapplication.RealmUtils.Qnew;
import quizcounter.geeks.compete.myapplication.ad.AdSetup;

public class TestActivity extends AppCompatActivity {
    String id="";
    ViewPager viewPager;
    RecyclerView rv_qno_list;
    QnoAdapter qnoAdapter;
    String APIResponse;
    SectionFragmentPagerAdapter sectionPagerAdapter;
    ArrayList<Qnew> qnewArrayList=new ArrayList<>();
    Toolbar toolbar;
    LinearLayoutManager layoutManager;
    Button btn_previous,btn_next,btn_end;
    TextView tvTitle;
    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        viewPager=findViewById(R.id.viewpager_test);
        rv_qno_list=findViewById(R.id.rv_qno_list);
        toolbar=findViewById(R.id.toolbar);
        tvTitle=findViewById(R.id.title);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        String title= getIntent().getExtras().getString("title");
        tvTitle.setText(title);

        sectionPagerAdapter=new SectionFragmentPagerAdapter(getSupportFragmentManager());
//        viewPager.setOffscreenPageLimit(50);
        viewPager.setOffscreenPageLimit(2);

        id= getIntent().getExtras().getString("id");

//        Paper paper=Realm.getDefaultInstance().where(Paper.class).equalTo("paperId",id).findFirst();
//        APIResponse=paper.paperData;
//        Log.e("PAperData", "onCreate: "+paper.toString());
//        parseData();

        btn_previous=findViewById(R.id.btn_previous);
        btn_next=findViewById(R.id.btn_next);
        btn_end=findViewById(R.id.btn_end);


        btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(-1);
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(1);
            }
        });

        getQuestionsRealm(id);

        adView=findViewById(R.id.ad_banner_view_main);
        adView.setVisibility(View.VISIBLE);

        if (adView != null) {
            AdSetup.setAd(adView);
        }

        ItemClickSupport.addTo(rv_qno_list).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
//                Intent intent=new Intent(context,TestActivity.class);
//                intent.putExtra("id",((Paper)papers.get(position)).paperId);
//                intent.putExtra("title",((Paper)papers.get(position)).paperTitle);
//                context.startActivity(intent);
                viewPager.setCurrentItem(position);
                qnoAdapter.setSelected(position);

            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                layoutManager.scrollToPosition(position);
                qnoAdapter.setSelected(position);
//                hsl.smoothScrollTo(scrollX, 0);
//                layoutManager.smoothScrollToPosition(,0);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
                if(ViewPager.SCROLL_STATE_IDLE == state){
                    //Scrolling finished. Do something.
                }
            }
        });




        new CountDownTimer(100000, 1000) {

            public void onTick(final long millisUntilFinished) {
//                mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
                long seconds = millisUntilFinished/1000;//convert to seconds
                long minutes = seconds / 60;//convert to minutes
//                long hours = minutes / 60;//convert to hours

                if(minutes > 0)//if we have minutes, then there might be some remainder seconds
                    seconds = seconds % 60;//seconds can be between 0-60, so we use the % operator to get the remainder
//                if(hours > 0)
//                    minutes = minutes % 60;//similar to seconds
//                String time = formatNumber(hours) + ":" + formatNumber(minutes) + ":" +
                String time = formatNumber(minutes) + ":" +
                        formatNumber(seconds);
                tvTitle.setText(time);//set value to text
                Log.e("TimeREmainedTest","::"+time);
//                Realm realm=Realm.getDefaultInstance();
//                Paper paper=realm.where(Paper.class).equalTo("paperId",id).findFirst();
//                paper.timeElapsed=millisUntilFinished;
//                realm.copyToRealmOrUpdate(paper);
//                realm.close();

                final Realm myRealm = Realm.getDefaultInstance();
                final Paper paper=myRealm.where(Paper.class).equalTo("paperId",id).findFirst();
                myRealm.executeTransaction(new Realm.Transaction() {
                    @Override    public void execute(Realm realm) {
                        try {
                            paper.timeElapsed=millisUntilFinished;
                            // the listObject has contain updated data in the List
                            myRealm.copyToRealmOrUpdate(paper);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }finally {
                            myRealm.close();
                        }
                    }
                });
            }

            public void onFinish() {
                tvTitle.setText("done!");
                openUpdatePopUp();
            }
        }.start();
    }

    private String formatNumber(long value){
        if(value < 10)
            return "0" + value;
        return value + "";
    }



    private void getQuestionsRealm(String id) {
//        UGC_NET_Paper_1_July_2019
        Realm realm=Realm.getDefaultInstance();
        RealmResults<Qnew> qnewRealmResults=realm.where(Qnew.class).equalTo("Pid",id).findAll();
        qnewArrayList.addAll(realm.copyFromRealm(qnewRealmResults));
        realm.close();
        setDataTofragments();

        //        .equalTo("Pid",id)
//        for(int i=0;i<qnewRealmResults.size();i++){
//            qnewRealmResults
//            Log.e("qnewRealmResults::",id+"::"+qnewRealmResults.get(i).Pid);
//        }
//        Log.e("qnewRealmResults::",id+"::"+qnewRealmResults.size());
    }

    void setDataTofragments(){
        for(int i=0;i<qnewArrayList.size();i++){
            sectionPagerAdapter.addFragment(BlankFragment.newInstance(qnewArrayList.get(i),this));
        }
        viewPager.setAdapter(sectionPagerAdapter);
        initview();
        setHomePageAdapter();
    }

    public void onItemClick(int i){
        viewPager.setCurrentItem(viewPager.getCurrentItem()+i);
    }

//    void parseData(){
//        Document document = Jsoup.parse(APIResponse);
//        Elements nodeBlogStats = document.getElementsByClass("question-data");
//        ArrayList<Element> elementArrayList=new ArrayList<>();
//        ArrayList<Element> elementArrayPara=new ArrayList<>();
//        HashMap<String,Integer> hashMap=new HashMap<>();
////        for (Element row : nodeBlogStats) {
//        for (int e=0;e<nodeBlogStats.size();e++) {
////            Log.e("DataParsedAPI","::"+e+"::"+nodeBlogStats.get(e).attr("referenced_by"));
//
//            if(!"".equalsIgnoreCase(nodeBlogStats.get(e).attr("referenced_by").toString())){
//                elementArrayPara.add(nodeBlogStats.get(e));
//                String[] items = nodeBlogStats.get(e).attr("referenced_by").toString().split(",");
//                for(int i=0;i<items.length;i++){
//                    hashMap.put(items[i],elementArrayPara.size()-1);
//                }
//            }else {
//                elementArrayList.add(nodeBlogStats.get(e));
//            }
//        }
//
//        for (int i=0;i<elementArrayList.size();i++) {
//            BlankFragment blankFragment = null;
//            if(hashMap.containsKey(""+(i+1))){
//                Element para=elementArrayPara.get(hashMap.get(""+(i+1)));
//                blankFragment=BlankFragment.newInstance(elementArrayList.get(i).toString(),para+"</br>",this);
//            }else {
//                blankFragment=BlankFragment.newInstance(elementArrayList.get(i).toString(),"",this);
//            }
////            BlankFragment blankFragment=BlankFragment.newInstance(elementArrayList.get(i).toString());
//            sectionPagerAdapter.addFragment(blankFragment);
//        }
//        viewPager.setAdapter(sectionPagerAdapter);
//    }


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

            if (qnoAdapter == null) {
                qnoAdapter = new QnoAdapter(this);//PaperListActivity.
                rv_qno_list.setAdapter(qnoAdapter);
            } else {
                rv_qno_list.setAdapter(qnoAdapter);
            }
            try {
                if (qnewArrayList != null && qnewArrayList.size() > 0) {
                    qnoAdapter.setSize(qnewArrayList.size());
                    rv_qno_list.setVisibility(View.VISIBLE);
                    qnoAdapter.setSelected(0);
//                    noResultFound.setVisibility(View.GONE);
                } else {
                    rv_qno_list.setVisibility(View.GONE);
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
            layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//            layoutManager.setStackFromEnd(true);
//            layoutManager.setReverseLayout(true);
            rv_qno_list.setLayoutManager(layoutManager);
            rv_qno_list.setNestedScrollingEnabled(false);
            rv_qno_list.setHasFixedSize(true);
//            txtnoNotification.setText(getResources().getString(R.string.no_notification));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_test,menu);
        return true;
//        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
        if(item.getItemId()==R.id.action_submit){
//            Toast.makeText(this, "Cliked submit", Toast.LENGTH_SHORT).show();
            //check if any questions attempted
            openUpdatePopUp();
        }
        return true;
    }

    Dialog dialog;
    private void openUpdatePopUp() {
        try {
//            Log.e("popUpPojoURLTOCOPY", "::" + popUpPojo.getImage());
            dialog = new Dialog(TestActivity.this, R.style.AppTheme);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.update_application_layout);
//            dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#66000000")));
//            dialog.getWindow().setBackgroundDrawable();
            Button btn_update_app = dialog.findViewById(R.id.update_application_update_button1);
            TextView tv_update_message = dialog.findViewById(R.id.update_application_update_message);
            tv_update_message.setText("SUBMIT TEST");
            btn_update_app.setText("SUBMIT");
//            tv_update_message.setText(Application.getPrefranceData(force_update_message));
            btn_update_app.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
//                        Realm realm=Realm.getDefaultInstance();
//                        Paper paper=realm.where(Paper.class).equalTo("paperId",id).findFirst();
//                        paper.isAttempted=true;
//                        realm.copyToRealmOrUpdate(paper);
//                        realm.close();
                        final Realm myRealm = Realm.getDefaultInstance();
                        final Paper paper=myRealm.where(Paper.class).equalTo("paperId",id).findFirst();

                        myRealm.executeTransaction(new Realm.Transaction() {
                            @Override    public void execute(Realm realm) {
                                try {
                                    paper.isAttempted=true;
                                    // the listObject has contain updated data in the List
                                    myRealm.copyToRealmOrUpdate(paper);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }finally {
                                    myRealm.close();
                                }
                            }
                        });
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
}
