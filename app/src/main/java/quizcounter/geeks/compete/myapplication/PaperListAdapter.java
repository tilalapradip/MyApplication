package quizcounter.geeks.compete.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import quizcounter.geeks.compete.myapplication.RealmUtils.Notif;
import quizcounter.geeks.compete.myapplication.RealmUtils.Paper;
import quizcounter.geeks.compete.myapplication.Utils.Constants;
import quizcounter.geeks.compete.myapplication.wooplrWebViewRedirect.WooplrWebViewActivity;

public class PaperListAdapter extends RecyclerView.Adapter<PaperListAdapter.ViewHolder> {

    private final List<Object> mValuesNotif = new ArrayList<>();
//    private final List<Paper> mValuesPaper = new ArrayList<>();

    public SQLiteDatabase dbTransaction = null;
    Context context;
    int type=-1;

    public PaperListAdapter(Activity context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.paper_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return type;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
            int type=getItemViewType(position);


//        holder.iv_clock.setBackgroundResource(R.drawable.ic_clock_circular_outline);
//        holder.iv_date.setBackgroundResource(R.drawable.ic_calendar);
        holder.mItem = mValuesNotif.get(position);
//        String data = Application.getGson().toJson(holder.mItem);

//        if(holder.mItem instanceof Paper){
        if(type==0){
            final Notif paper= (Notif) holder.mItem;
            String title= paper.notifIdTitle;
            holder.txt_notiTitle.setText(title);
            String desc= paper.notifDesc;
            holder.txt_notiDesc.setText(desc);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    ((PaperListActivity)context).onClikitem(paper.);
                    Intent intent=new Intent(context, WooplrWebViewActivity.class);
                    intent.putExtra(Constants.webview_title,paper.notifIdTitle);
                    intent.putExtra(Constants.urlWooplrWebView,paper.notifData);
                    context.startActivity(intent);
                }
            });

        }else {
            final Paper paper= (Paper) holder.mItem;
            String title= paper.paperTitle;
            holder.txt_notiTitle.setText(title);
            String desc= paper.paperDesc;
            holder.txt_notiDesc.setText(desc);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((PaperListActivity)context).onClikitem(paper.paperId);
                }
            });
        }
    }

//    public NotificationPOJO getNotificationpos(int position) {
//        return mValuesNotif.get(position);
//    }
    //add profile data
    public void addNotification(List<Object> notificationpojo,int type) {
//    public void addNotification(List<Paper> notificationpojo) {
        try {
//            if(type==0){
                mValuesNotif.addAll(notificationpojo);
//            }else {
//                mValuesPaper.addAll(notificationpojo);
//            }
            this.type=type;
            notifyDataSetChanged();
//            notifyItemInserted(mValuesNotif.size() - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearAllList() {
        try {
            //add delete query
            mValuesNotif.clear();
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public List<Notif> getAllNotificationList() {
//        return mValuesNotif;
//    }

    public void removefromdb(int position) {
        try {
            //add delete query
            this.mValuesNotif.remove(position);
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatefromdb(int position) {
        try {
            //add update query
//            this.mValuesNotif.get(position).setIsread(true);
//            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount()
    {
//     if(type==0){
         return mValuesNotif.size();
//     }else {
//         return mValuesPaper.size();
//     }
    }
//    public int getItemCount() {
//        return 9;
//    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public Object mItem;
        ImageView img_notification,iv_clock,iv_date;
        TextView txt_notiTitle;
        TextView txt_notiDesc;
//        TextView txt_time;
//        TextView txt_date;

        public ViewHolder(View view) {
            super(view);
            mView = view;
//            img_notification = mView.findViewById(R.id.img_notification);
            txt_notiTitle = mView.findViewById(R.id.txt_paper_title);
            txt_notiDesc = mView.findViewById(R.id.txt_paper_desc);
//            txt_time = mView.findViewById(R.id.txt_time);
//            txt_date = mView.findViewById(R.id.txt_date);
//            iv_clock = mView.findViewById(R.id.iv_clock);
            iv_date = mView.findViewById(R.id.iv_date);
        }

    }
}
