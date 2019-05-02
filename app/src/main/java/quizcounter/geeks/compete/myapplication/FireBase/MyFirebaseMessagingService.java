package quizcounter.geeks.compete.myapplication.FireBase;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import org.json.JSONObject;
import java.net.URL;
import java.util.Random;
import quizcounter.geeks.compete.myapplication.MainActivity;
import quizcounter.geeks.compete.myapplication.R;

import static quizcounter.geeks.compete.myapplication.FireBase.FCMConstancts.detail_description;
import static quizcounter.geeks.compete.myapplication.FireBase.FCMConstancts.image;
import static quizcounter.geeks.compete.myapplication.FireBase.FCMConstancts.message;
import static quizcounter.geeks.compete.myapplication.FireBase.FCMConstancts.redirect_url;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";
    final Handler handler = new Handler();
    Bitmap bitmap;
//    private static final String actionLiked = "liked";
//    public static String FCM = null;
//    public static String getFCM() { return FCM; }
//    public static void refreshData() { }
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        JSONObject json = null;
        Log.e("remoteMessage11:", "::"+remoteMessage.getData());
        try {
                sendNotification(remoteMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void onNewToken(String s) {
////        super.onNewToken(s);
//        Log.e("FCM_s= ", "Token::" + s);
//        FCM = s;
//        if (s != null) {
//            Application.setPreferences(CommanConstants.app_fcm_token, s);
//        }
//    }

//        public SlidersPOJO getNotificationObject(String response) {
//        Gson gson = new GsonBuilder().create();
//        return gson.fromJson(response, SlidersPOJO.class);
//    }
    public Bitmap getBitmapfromUrl(String imageUrl) {
        try {
            if (imageUrl != null && !imageUrl.isEmpty()) {
                URL url = new URL(imageUrl);
                bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            }
            return bitmap;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    private void sendNotification(RemoteMessage messageBody) {
            int timeStamp = (int) System.currentTimeMillis();
            Intent intent = new Intent(this, MainActivity.class);
            Bundle bundle;
            bundle = new Bundle();

            intent.putExtras(bundle);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, timeStamp, intent,
                    PendingIntent.FLAG_ONE_SHOT);
            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//            SlidersPOJO notificationData = getNotificationObject(messageBody.toString());
//            bitmap = getBitmapfromUrl(notificationData.offer_image);
            bitmap = getBitmapfromUrl(messageBody.getData().get(image));

//        bitmap = ((BitmapDrawable) ResourcesCompat.getDrawable(Application.get().getResources(), R.drawable.space_app_image, null)).getBitmap();
//        try {
//            bitmap= GlideApp.
//                    with(this)
//                    .asBitmap()
//                    .load(messageBody.getData().get("image"))
//                    .submit()
//                    .get();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        Bitmap theBitmap = Glide.
//                with(this).
//                asBitmap().
//                load("http://....").
//                into(100, 100). // Width and height
//                get();
            String news_id=messageBody.getData().get("news_id");
            Log.e("push remoteMessage33:- ", "::"+news_id);

        String detailDescription=messageBody.getData().get(detail_description);
        String redirectUrl=messageBody.getData().get(redirect_url);
//        Article article=new Article();

//        article.news_url=redirectUrl;
//        article.news_full_detail=detailDescription;
//        article.news_image_url=messageBody.getData().get(image);
//        article.news_description=messageBody.getData().get(message);
//        article.news_title=messageBody.getData().get(title);
//        article.news_source=messageBody.getData().get(news_source);

//        Gson gson = new Gson();
//        Type type = new TypeToken<Article>() {}.getType();
//        String json = gson.toJson(article, type);

            NotificationManager notificationManager = null;

            notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                String id = "id_product";
                CharSequence name = "Product";
                String description = "Notifications regarding our products";
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel mChannel = new NotificationChannel(id, name, importance);
                mChannel.setDescription(description);
                mChannel.enableLights(true);
                mChannel.setLightColor(Color.RED);
                notificationManager.createNotificationChannel(mChannel);
            }

                Intent intent11 = new Intent(this, MainActivity.class);
                Bundle bundle11 = new Bundle();
//                bundle11.putString(FCMConstancts.notification_data_list,json);

                intent11.putExtras(bundle11);
                intent11.setAction("com.elitech.offers.coupons.deals.NOTIFICATION");
                intent11.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                PendingIntent pendingIntent12 = PendingIntent.getActivity(this, timeStamp, intent11, PendingIntent.FLAG_ONE_SHOT);
                NotificationCompat.Builder notificationBuilder12 = null;

                    if (messageBody.getData().get(image).length() > 0) {
                        Log.e("LogImageSide",":::1");
                        Log.e("LogImageSide",":::1"+bitmap.getWidth());
                        long when = System.currentTimeMillis();
//                        notificationBuilder12 = new NotificationCompat.Builder(getApplicationContext(), "id_product")
//                                .setContentTitle(messageBody.getData().get(title))
//                                .setContentText(""+ Html.fromHtml(messageBody.getNotification().getBody(),0))
//.setContentText(Html.fromHtml(Html.fromHtml(messageBody.getNotification().getBody()).toString()))
//.setContentText(messageBody.getData().get(message))
//                                .setContentIntent(pendingIntent12)
//                                .setSmallIcon(R.drawable.space_app_image1)
//                                .setAutoCancel(true)
//                                .setPriority(Notification.PRIORITY_HIGH)
//                                .setWhen(when)
//                                .setStyle(new NotificationCompat.BigPictureStyle()
//                                        .bigPicture(bitmap).setSummaryText(Html.fromHtml(Html.fromHtml(messageBody.getString(FCMConstancts.message)).toString())))
//                                        .bigPicture(bitmap).setSummaryText(Html.fromHtml(Html.fromHtml(messageBody.getNotification().getBody()).toString())));

//                                        .bigPicture(bitmap).setSummaryText(messageBody.getData().get(title)));

//                                .setChannelId(id);
//                                            notificationManager.notify(1, notificationBuilder12.build());
//                                            notificationManager.notify(new Random().nextInt(61000) + 20, notificationBuilder12.build());

//                    } else {
//                        Log.e("LogImageSide",":::0");
//            if(messageBody.getNotification()!=null){
//                notificationBuilder12 = new NotificationCompat.Builder(this,"id_product")
//                        .setSmallIcon(R.drawable.space_app_image1)
//                        .setContentTitle(messageBody.getData().get(title))
//                                .setContentText(messageBody.getData().get(message))
//                                .setContentText(Html.fromHtml(Html.fromHtml(messageBody.getNotification().getBody()).toString()))
//                        .setContentText(""+Html.fromHtml(messageBody.getNotification().getBody().toString()))
//                        .setAutoCancel(true)
//                        .setPriority(Notification.PRIORITY_HIGH)
//                        .setContentIntent(pendingIntent12);

//                notificationManager.notify(1, notificationBuilder12.build());
//                notificationManager.notify(new Random().nextInt(61000) + 20, notificationBuilder12.build());
//            }
                    }
    }
}