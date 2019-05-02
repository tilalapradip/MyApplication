package quizcounter.geeks.compete.myapplication.NetworkUtility;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import quizcounter.geeks.compete.myapplication.MyApplication;
import quizcounter.geeks.compete.myapplication.VolleyCallBack;
import quizcounter.geeks.compete.myapplication.VolleySingleton;


/**
 * Created by espl on 27/9/16.
 */
public class NetworkUtility {

    public static final String jsondata = "jsondata";

    public static final String appPref = "appPref";
    //    public static String API_Version = "v1";
    public static String API_Version = "v1";
    private static int timeOut = 90000;

    public static void makeSimplerequest(final String url, final VolleyCallBack volleyCallBack){
        if (Utils.checkInternetConnection(MyApplication.get())) {

            Log.e("URLa::", "::"+url);
//            Log.e("PARAMS-Sa::", "::"+data.toString());
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            // Display the first 500 characters of the response string.
//                            textView.setText("Response is: "+ response.substring(0,500));
                            Log.e("PARAMS-Sa::", "::"+response);
                            volleyCallBack.onSuccess(response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
//                    textView.setText("That didn't work!");
                    Log.e("VolleyError:","::"+error.toString());
                }

            }){
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> headers = new HashMap();
//                    headers.put("Accept", "application/json");
                    headers.put("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
//                    accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8
//                    headers.put("content-type", "application/json; charset=UTF-8");
                    //application/json
//                    headers.put("Authorization", "Bearer "+"eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImVjNWJjYzFhZGUwNjk3YmQ1NmJiN2I5NzVjMTg0ZDk2MWNhYzNkZGE2MzZkMmE2MTJkNzIxMzA2ZWZkN2EwYmZlMmFjY2I3MWUwZDFmOWQ4In0.eyJhdWQiOiI3IiwianRpIjoiZWM1YmNjMWFkZTA2OTdiZDU2YmI3Yjk3NWMxODRkOTYxY2FjM2RkYTYzNmQyYTYxMmQ3MjEzMDZlZmQ3YTBiZmUyYWNjYjcxZTBkMWY5ZDgiLCJpYXQiOjE1MzY5MjIzNzEsIm5iZiI6MTUzNjkyMjM3MSwiZXhwIjoxODUyNTQxNTcxLCJzdWIiOiIzNiIsInNjb3BlcyI6W119.eZOHbG1SKBmYA-2JBqP6OMV5SEwBo5q_ZNCgpnv_qxGwKe6q-mHEhc3PbqisDjHbJafBsb2Drri50aa5hXfvpG7BtOe4pAK8CgYRly4erISmIRJWVQ4UvEa2bzUfSB3CnK95pg4XModkMLs4_b-knWZmT-1SOgqHXv8zwXz5U9-stQXla0aKuOHIJj2AowDfDhIfpx6ICwUC6KTXYfuGsCFqiEyUhGiwoMD5SbYLO4bHbFSYMuxmniiuk_bdtr0SeYXwjNmlY71T8-D8Ntx2IZpeoWkYVzXo2za-iThEVaPMIG7m1ebfpah4CATlPp1LcXNFENZxFfCwxJUEFS6rIe4BuIj56RrH80lwm7Xm9KlGSTYXL3P-0iGv6S63fxOlkrateBTgIlKV9IyioP9bVss_SOY5NIJwO0Dzgrgnmf0kwTz_bN3t1TsPxbIGxIzq_m23G1UGgw-syT50n4eEednm_pZO8RK89y4lXsTObh9QN3nOPxPeacy4Vt20M13gpGLl0K6ZfpFEf8OTg0yKBzebnFeIjR1MmLEfQYTGhRVRX0r8MjGQFpwVBuVk6wdVBEILqXsRapKZkMPWPQPU1uYe24MLzUcazU6NAaYIZW44NvfrLxJxmDPvFZ3To6Q25bt_jaXcJqK0igvD0KhgPSYF8KDoTC0vR1k-KCSnyBU");
//                    headers.put("Authorization", "Bearer " + MyApplication.getPrefranceData(SignUpConstants.LOGIN_RESPONSE_TOKEN));
                    return headers;
                }
//                content-type: application/json; charset=UTF-8
            };


            stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        2500
            VolleySingleton.getInstance(MyApplication.get()).addToRequestQueue(stringRequest);
        }
    }
      /*check application is in foreground or background*/
    public static boolean isAppOnForeground(Context context, String appPackageName) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }
        final String packageName = appPackageName;
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND && appProcess.processName.equals(packageName)) {
                //                Log.e("app",appPackageName);
                return true;
            }
        }
        return false;
    }

    public static String generateCacheKeyWithParam(String url, Map<String, String> params) {
        for (Map.Entry<String, String> entry : params.entrySet()) {
            url += entry.getKey() + "=" + entry.getValue();
        }
        return url;
    }
}

