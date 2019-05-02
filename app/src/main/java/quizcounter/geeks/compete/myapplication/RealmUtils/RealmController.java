package quizcounter.geeks.compete.myapplication.RealmUtils;

import android.app.Activity;
import android.app.Application;
import io.realm.Realm;

/**
 * Created by espl on 24/11/17.
 */

public class RealmController {
    private static RealmController instance;
    private Realm realm;


    public RealmController(Application application) {
        realm = Realm.getDefaultInstance();
    }

//    public RealmController(Context application) {
//        realm = Realm.getDefaultInstance();
//    }

    public static RealmController with(Application application) {

        if (instance == null) {
            instance = new RealmController(application);
        }
        return instance;
    }

    public static RealmController getInstance() {
        return instance;
    }

    //Refresh the realm istance
    public void refresh() {
        realm.refresh();
    }

//    public Realm getRealm() {
//
//        return realm;
//    }



//    public static RealmController with(Fragment fragment) {
//
//        if (instance == null) {
//            instance = new RealmController(fragment.getActivity().getApplication());
//        }
//        return instance;
//    }

//    public static RealmController with(Activity activity) {
//
//        if (instance == null) {
//            instance = new RealmController(activity.getApplication());
//        }
//        return instance;
//    }




    //clear all objects from Book.class
//    public void clearAllAccounts() {
//        realm.beginTransaction();
//        realm.clear(Paper.class);
//        realm.commitTransaction();
//    }
//
//    //clear all objects from Book.class
//    public void clearAllRegisteredUser() {
//
//        realm.beginTransaction();
//        realm.clear(RegisteredUser.class);
//        realm.commitTransaction();
//    }
//
//    //clear all objects from Book.class
//    public void clearAllAccountEntry() {
//
//        realm.beginTransaction();
//        realm.clear(AccountEntry.class);
//        realm.commitTransaction();
//    }
//
//
//    //find all objects in the Book.class
//    public RealmResults<Account> getAccounts() {
//        return realm.where(Account.class).findAll();
//    }
//
//    //query a single item with the given id
////    public Account getBook(String id) {
////
////        return realm.where(Account.class).equalTo("id", id).findFirst();
////    }
//
//
//    public ArrayList<RegisteredUser> getAllRegisteredUsers() {
//
//        RealmResults<RegisteredUser> registeredUsers=realm.where(RegisteredUser.class).findAll();
//
//        ArrayList<RegisteredUser> registeredUserList=new ArrayList<>();
//        for (int i=0;i<registeredUsers.size();i++){
//            registeredUserList.add(registeredUsers.get(i));
//            System.out.println("retrived entry with id"+registeredUserList.get(i).getUserId());
//        }
//        return registeredUserList;
//    }
//
////    Retrive location list
//    public ArrayList<LocationAddress> getAllLocation() {
//
//        RealmResults<LocationAddress> locationAddresses =realm.where(LocationAddress.class).findAll();
//
//        ArrayList<LocationAddress> location =new ArrayList<>();
//        for (int i=0;i<locationAddresses.size();i++){
//            location.add(locationAddresses.get(i));
////            System.out.println("retrived entry with id"+registeredUserList.get(i).getUserId());
//        }
//        return location;
//    }
//
//    public LocationAddress getLocation(long locationId) {
// LocationAddress locationAddresses =realm.where(LocationAddress.class).equalTo("locId",locationId).findFirst();
//        return locationAddresses;
//    }
//
//    public RegisteredUser getRegisteredUserFromRealm(String id) {
//        RealmResults<RegisteredUser> users=realm.where(RegisteredUser.class).equalTo("userId", id).findAll();
//
//        return users.get(0);
//    }
//
//    public Account getAccountFromRealm(String id) {
//        return realm.where(Account.class).equalTo("accountId", id).findFirst();
//    }
//
//
//    public  ArrayList<Account> getActiveAccountFromRealm() {
//
//        RealmResults<Account> realmResults = realm.where(Account.class).equalTo("typeOfAccount","L").equalTo("accountStatus","active").findAll();
//        ArrayList<Account> accounts = new ArrayList<>();
//        for (int i=0;i<realmResults.size();i++){
//            accounts.add(realmResults.get(i));
//        }
//        return accounts;
//                //realm.where(Account.class).equalTo("typeOfAccount","L").equalTo("accountStatus","active").findAll();
//    }
//
//
//    public  RealmResults<Account> getAllUsersActiveAccountFromRealm(String userId, String typeOfAccount) {
//        return realm.where(Account.class).equalTo("userId",userId).equalTo("typeOfAccount",typeOfAccount).equalTo("accountStatus","active").findAll();
//    }
//
//
//    public ArrayList<Account> getCloseAccount(String status, String type){
//
//        RealmResults<Account> realmResults = realm.where(Account.class).equalTo("accountStatus",status).equalTo("typeOfAccount",type).findAll();
//        ArrayList<Account> accounts = new ArrayList<>();
//        for (int i=0;i<realmResults.size();i++){
//            accounts.add(realmResults.get(i));
//        }
//        return accounts;
//    }
//
//    public boolean hasClosedAccounts(String userId){
//        RealmResults<AccountEntry> realmResults = realm.where(AccountEntry.class).equalTo("accountStatus","close").findAll();
//        ArrayList<Account> accounts = new ArrayList<>();
////        for (int i=0;i<realmResults.size();i++){
////            accounts.add(realmResults.get(i));
////        }
//        if(realmResults.size()>0){
//            return true;
//        }else {
//            return false;
//        }
//    }
//
//
//    public boolean hasActiveAccounts(String userId){
//        RealmResults<AccountEntry> realmResults = realm.where(AccountEntry.class).equalTo("accountStatus","active").findAll();
//        ArrayList<Account> accounts = new ArrayList<>();
////        for (int i=0;i<realmResults.size();i++){
////            accounts.add(realmResults.get(i));
////        }
//        if(realmResults.size()>0){
//            return true;
//        }else {
//            return false;
//        }
//    }
//
//    public List<AccountEntry> getAllAccountEntries() {
//        RealmResults<AccountEntry> accountEntries=realm.where(AccountEntry.class).findAll();
//        List<AccountEntry> accountEntryList=new ArrayList<>();
//        for (int i=0;i<accountEntries.size();i++){
//            accountEntryList.add(accountEntries.get(i));
//            System.out.println("retrived entry with id"+accountEntryList.get(i).getEntryId());
//        }
//        return accountEntryList;
//    }
//
//    public ArrayList<AccountEntry> getAllAccountEntriesBetweenDates(Date from, Date to) {
//        RealmResults<AccountEntry> accountEntries=realm.where(AccountEntry.class).between("dueDate",from,to).equalTo("isPaid",false).findAll();  //removed --> .notEqualTo("type","BP").notEqualTo("type","LP")
//        ArrayList<AccountEntry> accountEntryList=new ArrayList<>();
//        for (int i=0;i<accountEntries.size();i++){
//            accountEntryList.add(accountEntries.get(i));
//            System.out.println("retrived entry with id"+accountEntryList.get(i).getEntryId());
//        }
//        return accountEntryList;
//    }
//
//
//
//
//    //check if Book.class is empty
//    public boolean hasBooks() {
//
//        return !realm.allObjects(Account.class).isEmpty();
//    }
//
//    public void addAccountToRealm(Account account) {
//        realm.beginTransaction();
//        realm.copyToRealm(account);
//        realm.commitTransaction();
//    }
//
//    public void addLocationToRealm(LocationAddress locationAddress) {
//        realm.beginTransaction();
//        realm.copyToRealm(locationAddress);
//        realm.commitTransaction();
//    }
//
//    public void editLocationToRealm(LocationAddress locationAddress){
//        LocationAddress location = realm.where(LocationAddress.class).equalTo("locId", locationAddress.getLocId()).findFirst();
//        realm.beginTransaction();
//        location.setLocAddress(locationAddress.getLocAddress());
//        location.setLocnMobileNumber(locationAddress.getLocnMobileNumber());
//        location.setLocCity(locationAddress.getLocCity());
//        location.setLocState(locationAddress.getLocState());
//        location.setPincode(locationAddress.getPincode());
//        realm.commitTransaction();
//    }
//
//
//    public void addNewRegisteredUserToRealm(RegisteredUser registeredUser) {
//
//            realm.beginTransaction();
//            realm.copyToRealm(registeredUser);
//            realm.commitTransaction();
//    }
//
//
//    public RegisteredUser existingUserAccount(String mobileNumber){
//        RealmResults<RegisteredUser> realmResults = realm.where(RegisteredUser.class).findAll();
//        RegisteredUser userExisted=null;
//        //boolean available = false;
//        for (int i = 0;i<realmResults.size();i++){
//            if (realmResults.get(i).getNumber().equalsIgnoreCase(mobileNumber)) {
//                userExisted=realmResults.get(i);
//          //      available = true;
//                break;
//            }
//        }
//        return userExisted;
//    }
//
//    public void addAccountEntriesToRealm(List<AccountEntry> accountEntryList) {
//        for(AccountEntry ae: accountEntryList){
//            realm.beginTransaction();
//            realm.copyToRealm(ae);
//            realm.commitTransaction();
//            System.out.println("added entry with id"+ae.getEntryId());
//            realm.refresh();
//        }
//
//    }
//
//    public RegisteredUser getRegisteredUser(String userId){
//        return realm.where(RegisteredUser.class).equalTo("userId", userId).findFirst();
//    }
//
//    public AccountEntry getAccountPrincipleEntry(String accountId ){
//        return realm.where(AccountEntry.class).equalTo("accountId", accountId).equalTo("type","P").findFirst();
//    }
//
//
//    public void addNewExtendedAccountToRealm(AccountExtended accountExtended) {
//        realm.beginTransaction();
//        realm.copyToRealm(accountExtended);
//        realm.commitTransaction();
//    }
//
//    public void updateExtendedIdToAccount(String accountId,String extendedId,int totalTerm) {
//
//    realm.beginTransaction();
//
//        RealmResults<Account> result = realm
//                .where(Account.class).equalTo("accountId", accountId).findAll();
//
//        if (result.size() == 1) {
//            result.get(0).setNewAccountId(accountId);
//            result.get(0).setTerm(totalTerm);
//            result.get(0).setExtend(true);
//        }
//        realm.commitTransaction();
//
//    }
//
//    public void addLatestBAckUpToRealm(LatestBackUp latestBackUp) {
//        realm.beginTransaction();
//        realm.copyToRealm(latestBackUp);
//        realm.commitTransaction();
//    }
//
//    public ArrayList<LatestBackUp> getAllLatestBackUp() {
//
//        RealmResults<LatestBackUp> backUpRealmResults=realm.where(LatestBackUp.class).findAll();
//
//        ArrayList<LatestBackUp> latestBackUpArrayList=new ArrayList<>();
//        for (int i=0;i<backUpRealmResults.size();i++){
//            latestBackUpArrayList.add(backUpRealmResults.get(i));
//            System.out.println("retrived backup latest with id"+latestBackUpArrayList.get(i).getModifiedDate());
//        }
//        return latestBackUpArrayList;
//    }
//
//
//
////    //query example
////    public RealmResults<Account> queryedBooks() {
////
////        return realm.where(Account.class)
////                .contains("author", "Author 0")
////                .or()
////                .contains("title", "Realm")
////                .findAll();
////
////    }

}



