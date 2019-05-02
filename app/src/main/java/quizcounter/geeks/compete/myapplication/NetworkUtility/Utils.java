package quizcounter.geeks.compete.myapplication.NetworkUtility;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import quizcounter.geeks.compete.myapplication.MyApplication;

/**
 * The type TagUtils.
 */
public class Utils {

    private static ProgressDialog pDialog;


    /**
     * Check Internet Connection
     * <p>
     * base activity
     *
     * @return boolean TagUtils
     */
    // check internet connection
    public static boolean checkInternetConnection(Context context) {
        try {
            ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            boolean status = conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() && conMgr.getActiveNetworkInfo().isConnected();
            if (status)
                return status;
            else {
//                if (!CommanConstants.isNoInternetScreenOpen) {
//                    CommanConstants.isNoInternetScreenOpen = true;
//                   // if (!isAppOnForeground(context,context.getPackageName())){
//                        Intent i1 = new Intent(context, NoInternetConnectionActivity.class);
//                        i1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        context.startActivity(i1);
//                        Utils.showAlertToast(MyApplication.get(), "Internet connection is not available.");
////                    }
//                }
                return status;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkInternet(Context context) {
        try {
            ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            boolean status = conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() && conMgr.getActiveNetworkInfo().isConnected();
            if (status)
                return status;
            else {
                Utils.showAlertToast(MyApplication.get(), "Internet connection is not available.");
                return status;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Show alert toast.
     *
     * @param context the context
     * @param text    the text
     */
// for display tost in application
    public static void showAlertToast(Context context, String text) {
        try {
            if (text.length() > 0) {
                Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                TextView v = toast.getView().findViewById(android.R.id.message);
                if (v != null) v.setGravity(Gravity.CENTER);
                toast.show();
            }
        } catch (Exception e) {
        }
    }

}