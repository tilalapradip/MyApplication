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
import android.support.v7.widget.CardView;
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

public class OptionsAdapter extends RecyclerView.Adapter<OptionsAdapter.ViewHolder> {

    private List<String> mValues = new ArrayList<>();
    Context context;
//    LightBlue
    int mSelected=-1;

    public OptionsAdapter(Activity context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.option_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tv_option_item.setText(mValues.get(position));
        if(mSelected==position){
            holder.cv_option.setBackgroundColor(ContextCompat.getColor(context,R.color.LightBlue));
        }else {
            holder.cv_option.setBackgroundColor(ContextCompat.getColor(context,R.color.white));
        }

//        holder.cv_option.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mSelected=position;
//                notifyDataSetChanged();
//            }
//        });
    }


    void setPosAndNotify(int position){
        mSelected=position;
        notifyDataSetChanged();
    }

//    public NotificationPOJO getNotificationpos(int position) {
//        return mValues.get(position);
//    }

    //add profile data
    public void addNotification(ArrayList<String> notificationpojo) {
        try {
            mValues=new ArrayList<>();
            mValues.addAll(notificationpojo);
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public String mItem;
        TextView tv_option_item;
        CardView cv_option;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tv_option_item = mView.findViewById(R.id.tv_option_item);
            cv_option = mView.findViewById(R.id.cv_option);
        }

    }
}
