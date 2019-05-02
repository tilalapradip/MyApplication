package quizcounter.geeks.compete.myapplication;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends android.app.Application {
    private static MyApplication _instance;
//    private static Gson gson = null;
    private static SharedPreferences _preferences;

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            _instance = this;
}catch (Exception e){
            e.printStackTrace();
        }

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("geekscompete.realm")
                .schemaVersion(1)
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
        Realm.getInstance(realmConfiguration);

        //Realm configs after migration
//         final RealmConfiguration configuration = new RealmConfiguration.Builder().name("sample.realm").schemaVersion(2).migration(new RealmMigrations()).build();
//        Realm.setDefaultConfiguration(configuration);
//        Realm.getInstance(configuration);
    }

//    public Realm getRealm() {
//        return realm;
//    }

    @Override
    public void onTerminate() {
        Realm.getDefaultInstance().close();
        super.onTerminate();
    }

//    public static Gson getGson() {
//        if (gson == null) {gson = new Gson();}
//    return gson;
//    }

    public static MyApplication get() {
        return _instance;
    }

//    private void setUpFonts() {
//        try {
//            ViewPump.init(ViewPump.builder()
//                    .addInterceptor(new CalligraphyInterceptor(
//                            new CalligraphyConfig.Builder()
////                                    .setDefaultFontPath("fonts/ProximaNovaRegular.ttf")
//                                    .setDefaultFontPath("fonts/GoogleSans-Regular.ttf")
//                                    .setFontAttrId(R.attr.fontPath)
//                                    .build()))
//                    .build());
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

    public static SharedPreferences getSharedPreferences() {
        if (_preferences == null)
            _preferences = PreferenceManager.getDefaultSharedPreferences(_instance);
        return _preferences;
    }

    public static void setPreferences(String key, String value) {
        getSharedPreferences().edit().putString(key, value).commit();
    }

    public static String getPrefranceData(String key) {
        return getSharedPreferences().getString(key, "");
    }

    public static void setPreferences(String key, int value) {
        getSharedPreferences().edit().putInt(key, value).commit();
    }

    public static int getPrefranceDataInt(String key) {
        return getSharedPreferences().getInt(key, 0);
    }

}