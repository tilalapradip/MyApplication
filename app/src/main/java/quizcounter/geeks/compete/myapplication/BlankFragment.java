package quizcounter.geeks.compete.myapplication;


import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.ads.AdView;

import org.json.JSONArray;
import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import quizcounter.geeks.compete.myapplication.CustomeView.ItemClickSupport;
import quizcounter.geeks.compete.myapplication.RealmUtils.Qnew;
import quizcounter.geeks.compete.myapplication.ad.AdSetup;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {
    LinearLayoutManager layoutManager;
    WebView webView;
    WebView webview_answer;
    AdView adView;
    //    WebView webViewPara;
//    RadioGroup radiogroup;
    OptionsAdapter optionsAdapter;
    RecyclerView rv_options;
//    Button btn_show_ans;
    ArrayList<String> list = new ArrayList<String>();
//    Button btn_previous,btn_next,btn_end;
    static Context mContext;
    Qnew qnewFrag;
//<meta name="viewport" content="width=device-width, initial-scale=1">
//    work_sans.xml
    String head="<html>\n" +
            "<head>\n" +"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">"+
            "<title>Help page for this application</title>\n" +
            "<style type=\"text/css\">\n" +
            "body {\n" +
            "    background-color: #FFFFFF;\n" +
            "}\n" +
            " \n" +
            "@font-face {\n" +
            "    font-family: MyFont;\n" +
            "    src: url(\"file:///android_asset/fonts/worksansregular.ttf\");\n" +
            "}\n" +
            " \n" +
            "p,body {\n" +
            "    color: #000000;\n" +
            "    font-family: MyFont;\n" +
            "}\n" +
            "</style>\n" +
            "</head>\n" +
            "<body>\n"+"<p>";

//    style="font-family: roboto, Verdana, sans-serif"
// "    src: url(\"file:///android_asset/fonts/worksansregular.ttf\");\n" +

    String headerEnd=
            "</p>" +
                    " \n" +
            "</body>\n" +
            "</html>";

    int deviceHeight;

    public BlankFragment() {
        // Required empty public constructor
    }
    public static BlankFragment newInstance(Qnew qnew, Context context) {
        BlankFragment myFragment = new BlankFragment();
        Bundle args = new Bundle();
        mContext=context;
        myFragment.qnewFrag=qnew;
        return myFragment;
    }

//    public static BlankFragment newInstance(String categories,String para,Context context) {
//        BlankFragment myFragment = new Blankattr(Fragment();
//        Bundle args = new Bundle();
//        mContext=context;
//        args.putString("title",categories);
//        args.putString("para",para);
////        args.putString(CommanConstants.category_id,categories.id);
//        myFragment.setArguments(args);
//        return myFragment;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
        // Inflate the layout for this fragmRent
        View view=inflater.inflate(R.layout.fragment_blank, container, false);
        webView=view.findViewById(R.id.webview_main);
//        webview_answer=view.findViewById(R.id.webview_answer);
//        btn_show_ans=view.findViewById(R.id.btn_show_ans);
//        webViewPara=view.findViewById(R.id.webview_para);
//        radiogroup=view.findViewById(R.id.radiogroup);
        rv_options=view.findViewById(R.id.rv_options);

        initview();

//        btn_show_ans.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(webview_answer.getVisibility()==View.VISIBLE){
//                    webview_answer.setVisibility(View.GONE);
//                }else {
//                    webview_answer.setVisibility(View.VISIBLE);
//                }
//            }
//        });

        //        adView=view.findViewById(R.id.ad_banner_view_main);
//        adView.setVisibility(View.VISIBLE);
//
//        if (adView != null) {
//            AdSetup.setAd(adView);
//        }

        Toast.makeText(mContext, qnewFrag.Qno+""+"Right:"+qnewFrag.RightAns, Toast.LENGTH_SHORT).show();

//        btn_previous=view.findViewById(R.id.btn_previous);
//        btn_next=view.findViewById(R.id.btn_next);
//        btn_end=view.findViewById(R.id.btn_end);
//
//
//        btn_end.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        btn_previous.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ((TestActivity)mContext).onItemClick(-1);
//            }
//        });
//
//        btn_next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ((TestActivity)mContext).onItemClick(1);
//            }
//        });


        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        deviceHeight= displayMetrics.heightPixels;

        addRadioButtons();

//        if(getUserVisibleHint()){ // fragment is visible
//            setUpWebview_answer(head+qnewFrag.AnsData+headerEnd);
            setUpWEbview(head+""+qnewFrag.QData+headerEnd);
//        }
        AdSetup.showAddWithInterval();
        ItemClickSupport.addTo(rv_options).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
//                Intent intent=new Intent(context,TestActivity.class);
//                intent.putExtra("id",((Paper)papers.get(position)).paperId);
//                intent.putExtra("title",((Paper)papers.get(position)).paperTitle);
//                context.startActivity(intent);
                optionsAdapter.setPosAndNotify(position);

                qnewFrag.RightAns=""+(position+1);
                final Realm myRealm;


                myRealm = Realm.getDefaultInstance();

                myRealm.executeTransaction(new Realm.Transaction() {
                    @Override    public void execute(Realm realm) {
                        try {

                            // the listObject has contain updated data in the List
                            myRealm.copyToRealmOrUpdate(qnewFrag);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }finally {
                            myRealm.close();
                        }
                    }
                });

                Realm myrealm = Realm.getDefaultInstance();
                myRealm.where(Qnew.class).equalTo("Qid",qnewFrag.Qid).findFirst();
                Log.e("ClickedqnewFrag","::"+qnewFrag.RightAns);

            }
        });

        return view;
//        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onPause() {
        super.onPause();

        final Realm myRealm;
        myRealm = Realm.getDefaultInstance();
        myRealm.executeTransaction(new Realm.Transaction() {
            @Override    public void execute(Realm realm) {
                try {

                    // the listObject has contain updated data in the List
                    myRealm.copyToRealmOrUpdate(qnewFrag);

                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    myRealm.close();
                }
            }
        });
    }


//    @Override
//    public void setUserVisibleHint(boolean isFragmentVisible_) {
//        super.setUserVisibleHint(true);
//        if (this.isVisible()) {
//            setUpWebview_answer(head+qnewFrag.AnsData+headerEnd);
//            setUpWEbview(head+""+qnewFrag.QData+headerEnd);
//        }
//    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("OnResumeCalledFor","::OnResumeCalledFor FRAGMENT "+qnewFrag.Qno);
//        Log.e("Datarecivedrag","::"+this.getArguments().getString("title"));

//        Document document = Jsoup.parse(this.getArguments().getString("title"));
//        Elements nodeBlogStats = document.getElementsByClass("question");
//
//        Elements answer = document.getElementsByClass("commenthidden");


//        if(!"".equalsIgnoreCase(this.getArguments().getString("para"))){
//            webViewPara.setVisibility(View.VISIBLE);

//            ViewGroup.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//            params.height = (int) (.75*deviceHeight);
//            webViewPara.setLayoutParams(params);

//            setUpWebViewPara(head+this.getArguments().getString("para")+""+headerEnd);
//        }else {
//            webViewPara.setVisibility(View.GONE);
//        }


//        setUpWebview_answer(head+qnewFrag.AnsData+headerEnd);
//
////        setUpWEbview(head+this.getArguments().getString("para")+""+qnewFrag.QData+headerEnd);
//        setUpWEbview(head+""+qnewFrag.QData+headerEnd);

//        setUpWEbview(head+nodeBlogStats.get(0).toString()+headerEnd);
    }



    public void addRadioButtons() {
//        String data=this.getArguments().getString("title");
//
//        Document document = Jsoup.parse(data);
//        Elements nodeBlogStats = document.getElementsByClass("options");
//
//        Log.e("DataRightAns","::"+nodeBlogStats.get(0).attr("righ-answer"));
//
        list=new ArrayList<String>();
//        RadioGroup ll = new RadioGroup(this.getContext());
//        ll.setOrientation(LinearLayout.VERTICAL);
//
//        Element div =  nodeBlogStats.get(0).select("tbody").get(0);
//        Elements rows = div.select("tr");

        String options=qnewFrag.optionsString;

        JSONArray arr = null;




        try {
            arr = new JSONArray(options);
            for(int i = 0; i < arr.length(); i++){
                list.add(arr.get(i).toString());
//                qnoAdapter.addNotification(arr.get(i).toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        setHomePageAdapter();



//        for (String s: list) {
////            Log.e("RawPrinted","::"+s);
//            AppCompatRadioButton rdbtn = new AppCompatRadioButton(this.getContext());
//            rdbtn.setId(View.generateViewId());
//            rdbtn.setTextSize(16);
//            rdbtn.setTextColor(getResources().getColor(R.color.black));
//
//            ColorStateList colorStateList = new ColorStateList(
//                    new int[][]{
//                            new int[]{-android.R.attr.state_checked},
//                            new int[]{android.R.attr.state_checked}
//                    },
//                    new int[]{
//
//                            ContextCompat.getColor(this.getContext(),R.color.textColordarkPrimaryDark)
//                            , ContextCompat.getColor(this.getContext(),R.color.textColordarkPrimaryDark),
//                    }
//            );
//
//            rdbtn.setSupportButtonTintList(colorStateList);
//
//            RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            params.setMargins(5, 0, 5, 0);
//            rdbtn.setLayoutParams(params);
//
////            Elements td = element.select("td");
//            rdbtn.setText(s);
//            ll.addView(rdbtn);
////            }
//        }
//        radiogroup.addView(ll);

//        for (Element element : rows) {
//
//
//            Log.e("RawPrinted","::"+element.text());
//            //do stuff here
////            for (int i = 1; i <= number; i++) {
//            AppCompatRadioButton rdbtn = new AppCompatRadioButton(this.getContext());
//                rdbtn.setId(View.generateViewId());
//
//
////            AppCompatRadioButton rb;
////            rb = new AppCompatRadioButton(mContext);
//
//            ColorStateList colorStateList = new ColorStateList(
//                    new int[][]{
//                            new int[]{-android.R.attr.state_checked},
//                            new int[]{android.R.attr.state_checked}
//                    },
//                    new int[]{
//
//                            ContextCompat.getColor(this.getContext(),R.color.textColordarkPrimaryDark)
//                            , ContextCompat.getColor(this.getContext(),R.color.textColordarkPrimaryDark),
//                    }
//            );
//            rdbtn.setSupportButtonTintList(colorStateList);
//
//                RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                params.setMargins(5, 0, 5, 0);
//                rdbtn.setLayoutParams(params);
//
//                Elements td = element.select("td");
//                rdbtn.setText(td.text());
//                ll.addView(rdbtn);
////            }
//        }
//        radiogroup.addView(ll);
//        for (int row = 0; row < 1; row++) {
//            RadioGroup ll = new RadioGroup(this.getContext());
//            ll.setOrientation(LinearLayout.VERTICAL);
//
//            for (int i = 1; i <= number; i++) {
//                RadioButton rdbtn = new RadioButton(this.getContext());
//                rdbtn.setId(View.generateViewId());
//                rdbtn.setText("Radio " + rdbtn.getId());
//                ll.addView(rdbtn);
//            }
//            radiogroup.addView(ll);
//        }
    }

//    private void setUpWebViewPara(String storeContent) {
//        try {
//            webViewPara.setWebViewClient(new myWebClient());
////            webView.setWebChromeClient(new myWebChromeClient());
//
//            webViewPara.setWebChromeClient(new WebChromeClient() {
//                public void onProgressChanged(WebView view, int progress) {
////                    webprogress.setVisibility(View.VISIBLE);
//                    //Make the bar disappear after URL is loaded, and changes string to Loading...
////                    webprogress.setProgress(progress);
////                    setProgress(progress * 100);
//                    //Make the bar disappear after URL is loaded
//                    // Return the app name after finish loading
////                    if (progress == 100)
////                        webprogress.setVisibility(View.GONE);
//                }
//            });
//            webViewPara.getSettings().setJavaScriptEnabled(true);
//            webViewPara.setVerticalScrollBarEnabled(true);
////            webView.getSettings().t
////            webView.getSettings().setAllowContentAccess(true);
////            webView.getSettings().setAllowFileAccess(true);
////            webView.getSettings().setAllowFileAccessFromFileURLs(true);
////            webView.getSettings().setBlockNetworkImage(true);
////
////            webView.loadUrl(storeUrl);
//
////            webView.loadData(storeContent, "text/html; charset=utf-8", "utf-8");
//            webViewPara.loadDataWithBaseURL(null, storeContent,"text/html","utf-8", null);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    //webView setup
    private void setUpWEbview(String storeContent) {
        try {
            webView.setWebViewClient(new myWebClient());
//            webView.setWebChromeClient(new myWebChromeClient());

            webView.setWebChromeClient(new WebChromeClient() {
                public void onProgressChanged(WebView view, int progress) {
//                    webprogress.setVisibility(View.VISIBLE);
                    //Make the bar disappear after URL is loaded, and changes string to Loading...
//                    webprogress.setProgress(progress);
//                    setProgress(progress * 100);
                    //Make the bar disappear after URL is loaded
                    // Return the app name after finish loading
//                    if (progress == 100)
//                        webprogress.setVisibility(View.GONE);
                }
            });
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setDefaultFontSize(16);

//            webView.setVerticalScrollBarEnabled(false);
//            webView.setHorizontalScrollBarEnabled(false);
//            webView.getSettings().setLoadWithOverviewMode(true);
//            webView.getSettings().setUseWideViewPort(true);
            //            webView.getSettings().t
//            webView.getSettings().setAllowContentAccess(true);
//            webView.getSettings().setAllowFileAccess(true);
//            webView.getSettings().setAllowFileAccessFromFileURLs(true);
//            webView.getSettings().setBlockNetworkImage(true)
//            webView.loadUrl(storeUrl);
//            webView.loadData(storeContent, "text/html; charset=utf-8", "utf-8");
            webView.loadDataWithBaseURL(null, storeContent,"text/html","utf-8", null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private void setUpWebview_answer(String storeContent) {
//        try {
//            webview_answer.setWebViewClient(new myWebClient());
////            webView.setWebChromeClient(new myWebChromeClient());
//
//            webview_answer.setWebChromeClient(new WebChromeClient() {
//                public void onProgressChanged(WebView view, int progress) {
////                    webprogress.setVisibility(View.VISIBLE);
//                    //Make the bar disappear after URL is loaded, and changes string to Loading...
////                    webprogress.setProgress(progress);
////                    setProgress(progress * 100);
//                    //Make the bar disappear after URL is loaded
//                    // Return the app name after finish loading
////                    if (progress == 100)
////                        webprogress.setVisibility(View.GONE);
//                }
//            });
//            webview_answer.getSettings().setJavaScriptEnabled(true);
//            webview_answer.getSettings().setDefaultFontSize(16);
////            webview_answer.getSettings().setLoadWithOverviewMode(true);
////            webview_answer.setVerticalScrollBarEnabled(false);
////            webview_answer.setHorizontalScrollBarEnabled(false);
////            webview_answer.setOnTouchListener(new View.OnTouchListener() {
////                @Override
////                public boolean onTouch(View v, MotionEvent event) {
////                    return (event.getAction() == MotionEvent.ACTION_MOVE);
////                }
////            });
////
////            webview_answer.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//
////            webview_answer.getSettings().setUseWideViewPort(true);
////            webView.getSettings().t
////            webView.getSettings().setAllowContentAccess(true);
////            webView.getSettings().setAllowFileAccess(true);
////            webView.getSettings().setAllowFileAccessFromFileURLs(true);
////            webView.getSettings().setBlockNetworkImage(true)
////            webView.loadUrl(storeUrl);
////            webView.loadData(storeContent, "text/html; charset=utf-8", "utf-8");
////            String data = "<html><head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"></head></html>";
//
//            webview_answer.loadDataWithBaseURL(null, storeContent,"text/html","utf-8", null);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    //creatw webview as Web client; need to override all methods
    public class myWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
//            webprogress.setVisibility(View.VISIBLE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
//            webprogress.setVisibility(View.GONE);
        }
    }

    private void initview() {
        try {
            //set recvycler view
            layoutManager = new LinearLayoutManager(this.getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//            layoutManager.setStackFromEnd(true);
//            layoutManager.setReverseLayout(true);
            rv_options.setLayoutManager(layoutManager);
            rv_options.setNestedScrollingEnabled(false);
            rv_options.setHasFixedSize(true);
//            txtnoNotification.setText(getResources().getString(R.string.no_notification));
        } catch (Exception e) {
            e.printStackTrace();
        }
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

            if (optionsAdapter == null) {
                optionsAdapter = new OptionsAdapter(this.getActivity());//PaperListActivity.
                rv_options.setAdapter(optionsAdapter);
            } else {
                rv_options.setAdapter(optionsAdapter);
            }
            try {
                if (list != null && list.size() > 0) {
                    optionsAdapter.addNotification(list);
                    rv_options.setVisibility(View.VISIBLE);
//                    noResultFound.setVisibility(View.GONE);
                } else {
                    rv_options.setVisibility(View.GONE);
//                    noResultFound.setVisibility(View.VISIBLE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
