package quizcounter.geeks.compete.myapplication.ad;

import android.app.Application;
import android.util.Log;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import quizcounter.geeks.compete.myapplication.MyApplication;
import quizcounter.geeks.compete.myapplication.R;

import static quizcounter.geeks.compete.myapplication.Utils.Constants.interstatial_ad_show_count;

/**
 * Created by elitech04i7 on 18/7/17.
 */

public class AdSetup {
    public static String TesttingDeviceID = "39AB1C986D52FDFA9012D3486BD2706A";// redmi 2
    final static InterstitialAd mInterstitialAd = new InterstitialAd(MyApplication.get());

    public static void setAd(final com.google.android.gms.ads.AdView adView) {
        try {
            if (adView.getAdUnitId() != null && adView.getAdSize() != null) {
                MobileAds.initialize(MyApplication.get(), MyApplication.get().getResources().getString(R.string.YOUR_ADMOB_APP_ID));

                final AdRequest adRequest = new AdRequest.Builder()
                        .addTestDevice(TesttingDeviceID)
                        .build();
                adView.loadAd(adRequest);
//            adView.setVisibility(View.GONE);
                adView.setAdListener(new AdListener() {
                    @Override
                    public void onAdLoaded() {
                        Log.e("adViewmainBanner","::Sucess");
                        adView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAdFailedToLoad(int i) {
                        adView.setVisibility(View.GONE);
                        // load ad request
                        Log.e("adViewmainBanner","::failed");
                        final AdRequest adRequest = new AdRequest.Builder().addTestDevice(TesttingDeviceID).build();
                        try {
                            adView.loadAd(adRequest);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void  showAddWithInterval(){
//        int i_count=MyApplication.getPrefranceDataInt(interstatial_ad_show_count);
//        i_count=i_count+1;
//        MyApplication.setPreferences(interstatial_ad_show_count,i_count);
//
//        if(i_count==15){
//            Log.e("CountAdNO","::"+i_count);
//            MyApplication.setPreferences(interstatial_ad_show_count,0);
//            showInterstatial();
//        }
    }


    public static void setInterstitial() {
        try {
//            final InterstitialAd mInterstitialAd = new InterstitialAd(Application.get());
            if (mInterstitialAd.getAdUnitId() == null) {
                mInterstitialAd.setAdUnitId(MyApplication.get().getString(R.string.interstitialAd));
            }
//            AdRequest adRequestInter = new AdRequest.Builder().addTestDevice("BF4502CDB7107BF49E3E41382A69DA7B").build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadInterstatial() {
        try {
            setInterstitial();

            AdRequest adRequestInter = new AdRequest.Builder().build();

            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
//                    mInterstitialAd.show();
                    Log.e("mInterstetial onloaded", "onloaded isLoaded" + mInterstitialAd.isLoaded());
                }

                @Override
                public void onAdFailedToLoad(int i) {
//                    super.onAdFailedToLoad(i);
                    Log.e("mInterstetial onloaded", "onloaded onAdFailedToLoad");
                }

                @Override
                public void onAdOpened() {
//                    super.onAdOpened();
                }

                @Override
                public void onAdClosed() {
//                    super.onAdClosed();
                }

                @Override
                public void onAdClicked() {
//                    super.onAdClicked();
                }

            });
            mInterstitialAd.loadAd(adRequestInter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void showInterstatial() {
        try {
            Log.e("mInterstetial show ", "isLoaded" + mInterstitialAd.isLoaded());
            if (mInterstitialAd.isLoaded() && mInterstitialAd.getAdUnitId() != null) {
                mInterstitialAd.show();
            } else {

                loadInterstatial();
////            setInterstitial();
//            AdRequest adRequestInter = new AdRequest.Builder().build();
//            mInterstitialAd.loadAd(adRequestInter);
//
//            mInterstitialAd.setAdListener(new AdListener() {
//                @Override
//                public void onAdLoaded() {
////                    mInterstitialAd.show();
//                    Log.e("mInterstetial onloaded","onloaded isLoaded"+mInterstitialAd.isLoaded());
//                }
//
//                @Override
//                public void onAdFailedToLoad(int i) {
////                    super.onAdFailedToLoad(i);
//                    Log.e("mInterstetial onloaded","onloaded onAdFailedToLoad");
//                }
//
//                @Override
//                public void onAdOpened() {
////                    super.onAdOpened();
//                }
//
//                @Override
//                public void onAdClosed() {
////                    super.onAdClosed();
//                }
//
//                @Override
//                public void onAdClicked() {
////                    super.onAdClicked();
//                }
//
//            });

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
