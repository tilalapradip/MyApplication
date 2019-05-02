package quizcounter.geeks.compete.myapplication;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import quizcounter.geeks.compete.myapplication.R;
import quizcounter.geeks.compete.myapplication.RealmUtils.Notif;

public class QnoAdapter extends RecyclerView.Adapter<QnoAdapter.ViewHolder> {

    private final List<String> mValues = new ArrayList<>();
    Context context;
    int noOfQs;
    int mSelected=-1;

    public QnoAdapter(Activity context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.qno_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        holder.tv_option_item.setText(mValues.get(position));
        holder.tv_option_item.setText(""+(position+1));
        if(mSelected==position){
            holder.tv_option_item.setBackground(ContextCompat.getDrawable(context,R.drawable.circle_border_text));
        }else {
            holder.tv_option_item.setBackground(ContextCompat.getDrawable(context,R.drawable.circular_text));
        }
    }

//    public NotificationPOJO getNotificationpos(int position) {
//        return mValues.get(position);
//    }

    //add profile data
//    public void addNotification(ArrayList<String> notificationpojo) {
    public void setSize(int noOfQs) {
        try {
            this.noOfQs=noOfQs;
//            mValues.addAll(notificationpojo);
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setSelected(int position){
        int temp=mSelected;
        mSelected=position;
        if(temp!=-1){
            notifyItemChanged(temp);
        }
        notifyItemChanged(mSelected);
    }

//    public void addNotification(String notificationpojo) {
//        try {
//            mValues.add(notificationpojo);
//            notifyItemChanged(mValues.size()-1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    public void clearAllList() {
//        try {
//            //add delete query
//            mValues.clear();
//            notifyDataSetChanged();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public int getItemCount() {
//        return mValues.size();
        return noOfQs;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public String mItem;
        TextView tv_option_item;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tv_option_item = mView.findViewById(R.id.tv_option_item);
        }

    }
}
