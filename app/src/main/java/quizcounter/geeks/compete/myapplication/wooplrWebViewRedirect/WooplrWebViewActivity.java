package quizcounter.geeks.compete.myapplication.wooplrWebViewRedirect;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import quizcounter.geeks.compete.myapplication.R;
import quizcounter.geeks.compete.myapplication.ad.AdSetup;

import static quizcounter.geeks.compete.myapplication.Utils.Constants.urlWooplrWebView;
import static quizcounter.geeks.compete.myapplication.Utils.Constants.webview_title;

public class WooplrWebViewActivity extends AppCompatActivity {
//    SmoothProgressBar progressBar;
//    @BindView(R.id.web_wooplr)
    WebView web_wooplr;
//    @BindView(R.id.toolbar)
    Toolbar toolbar;
//    @BindView(R.id.title)
    TextView title;
    String urlString;
    String titleString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wooplr_web_view);

        web_wooplr=findViewById(R.id.web_wooplr);
        toolbar=findViewById(R.id.toolbar);
        title=findViewById(R.id.title);

        try {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getIntentData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        AdSetup.showAddWithInterval();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (web_wooplr.canGoBack()) {
                        web_wooplr.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
//                MyApplication.setPreferences(deal_category, "");
                finish();
                break;
//            case R.id.action_search:
//                Intent search = new Intent(this, SearchActivityV2.class);
//                startActivityForResult(search, 100);
//                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        unbinder.unbind();
    }

    private void getIntentData() {
        try {
            Intent i = getIntent();
            if (i.hasExtra(webview_title) && i.hasExtra(urlWooplrWebView)) {
                titleString = i.getStringExtra(webview_title);
                urlString = i.getStringExtra(urlWooplrWebView);
//                settitleinToolbar();
                title.setText(titleString);
                setUpWEbview();
                //Open when need CustomTabs
//                openURlInCustomTabs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUpWEbview() {
        try {
            web_wooplr.setWebViewClient(new myWebClient());
//            webView.setWebChromeClient(new myWebChromeClient());

            web_wooplr.setWebChromeClient(new WebChromeClient() {
                public void onProgressChanged(WebView view, int progress) {
//                    if(progressBar!=null)
//                    progressBar.setVisibility(View.VISIBLE);
                    //Make the bar disappear after URL is loaded, and changes string to Loading...
//                    if(progressBar!=null)
//                    progressBar.setProgress(progress);
//                    setProgress(progress * 100);
                    //Make the bar disappear after URL is loaded
                    // Return the app name after finish loading
//                    if (progress == 100 && progressBar!=null)
//                        progressBar.setVisibility(View.GONE);

                }
            });

            web_wooplr.setVerticalScrollBarEnabled(true);
            web_wooplr.setHorizontalScrollBarEnabled(true);

            web_wooplr.clearCache(true);
            web_wooplr.clearHistory();
            web_wooplr.getSettings().setJavaScriptEnabled(true);
            web_wooplr.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            web_wooplr.getSettings().setDomStorageEnabled(true);
            web_wooplr.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            web_wooplr.getSettings().setDisplayZoomControls(true);
            web_wooplr.getSettings().setAllowFileAccess(true);
            web_wooplr.getSettings().setAllowFileAccessFromFileURLs(true);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                web_wooplr.getSettings().setMixedContentMode(2);
            }
            web_wooplr.getSettings().setAllowContentAccess(true);

//            webView.getSettings().setAllowFileAccess(true);
//            webView.getSettings().setAllowFileAccessFromFileURLs(true);
//            webView.getSettings().setBlockNetworkImage(true);
//
//            web_wooplr.loadUrl(urlString);
//            web_wooplr.loadData(urlString,"text/html", "UTF-8");
            Log.e("web_wooplrString","::"+urlString);
            web_wooplr.loadData(urlString, "text/html; charset=utf-8", "utf-8");
//            txtBlogDescWeb.loadDataWithBaseURL("", data.getDescription2(), "text/html", "UTF-8", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class myWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
//            if(progressBar!=null)
//            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
//            if(progressBar!=null)
//            progressBar.setVisibility(View.GONE);
        }
    }
}
