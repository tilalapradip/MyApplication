package quizcounter.geeks.compete.myapplication.Notifications;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
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

public class NotificationListItemRecyclerViewAdapter extends RecyclerView.Adapter<NotificationListItemRecyclerViewAdapter.ViewHolder> {

    private final List<Notif> mValues = new ArrayList<>();
    public SQLiteDatabase dbTransaction = null;
    Context context;

    public NotificationListItemRecyclerViewAdapter(Activity context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_notificationlistitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.mItem = mValues.get(position);
//        String data = Application.getGson().toJson(holder.mItem);
        holder.txt_notiTitle.setText(holder.mItem.notifData.substring(0,20));
//        holder.txt_notiDesc.setText(Html.fromHtml(Html.fromHtml(holder.mItem.notifData.substring(0,30)).toString().trim()));
        holder.txt_notiDesc.setText(holder.mItem.notifData.substring(0,30));

        holder.txt_date.setText("20-11-2019");
        holder.txt_time.setText("02:12 PM");

//        holder.txt_date.setText(holder.mItem.getFormated_date());
//        if (holder.mItem.getImage() != null) {
//
//            Display mDisplay = ((Activity) context).getWindowManager().getDefaultDisplay();
////            final int width = mDisplay.getWidth();
////            int widthh=(width*10)/100;
////            holder.img_notification.getLayoutParams().height=widthh;
////            holder.img_notification.getLayoutParams().width=(width*20)/100;
////            GlideApp.with(context)
////                    .load(holder.mItem.getImage())
////                    .diskCacheStrategy(DiskCacheStrategy.ALL)
////                    .error(R.drawable.ocd_placeholder)
////                    .placeholder(R.drawable.ocd_placeholder)
////                    .into(holder.img_notification);
////            Glide.with(context)
////                    .load(holder.mItem.getImage())
////                    .diskCacheStrategy(DiskCacheStrategy.ALL)
////                    .error(R.drawable.ocd_placeholder)
////                    .ic_placeholder(R.drawable.ocd_placeholder)
////                    .into(holder.img_notification);
//        }





//        final int sdk = android.os.Build.VERSION.SDK_INT;
//        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
//            holder.iv_clock.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ready) );
//        } else {
            holder.iv_clock.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_clock_circular_outline));
            holder.iv_date.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_calendar));
//        }

//        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
//            layout.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ready) );
//        } else {
//            layout.setBackground(ContextCompat.getDrawable(context, R.drawable.ready));
//        }

        Drawable drawable = holder.iv_clock.getBackground();
        drawable.setColorFilter(new PorterDuffColorFilter(Color.parseColor("#757575"),PorterDuff.Mode.SRC_IN));
        //            drawable.setColor(Color.parseColor("#757575"));

        Drawable gradientDrawable = holder.iv_date.getBackground();
        gradientDrawable.setColorFilter(new PorterDuffColorFilter(Color.parseColor("#757575"),PorterDuff.Mode.SRC_IN));


//        iv_clock
//        GradientDrawable drawable = (GradientDrawable)rel_working.getBackground();
//        drawable.setColor(bgdrawable);


//        if (mValues.get(position).getIsread() != null && mValues.get(position).getIsread()) {
//            holder.txt_notiTitle.setTextColor(context.getResources().getColor(R.color.search_divider));
//            holder.txt_notiDesc.setTextColor(context.getResources().getColor(R.color.search_divider));
//            holder.txt_date.setTextColor(context.getResources().getColor(R.color.search_divider));
//            holder.txt_time.setTextColor(context.getResources().getColor(R.color.search_divider));
//        } else {
//            holder.txt_notiTitle.setTextColor(context.getResources().getColor(R.color.black));
//            holder.txt_notiDesc.setTextColor(context.getResources().getColor(R.color.black));
//            holder.txt_date.setTextColor(context.getResources().getColor(R.color.black));
//            holder.txt_time.setTextColor(context.getResources().getColor(R.color.black));
//        }

    }

//    public NotificationPOJO getNotificationpos(int position) {
//        return mValues.get(position);
//    }

    //add profile data
    public void addNotification(List<Notif> notificationpojo) {
        try {
            mValues.addAll(notificationpojo);
            notifyDataSetChanged();
//            notifyItemInserted(mValues.size() - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearAllList() {
        try {
            //add delete query
            mValues.clear();
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public List<Notif> getAllNotificationList() {
//        return mValues;
//    }

    public void removefromdb(int position) {
        try {
            //add delete query
            this.mValues.remove(position);
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatefromdb(int position) {
        try {
            //add update query
//            this.mValues.get(position).setIsread(true);
//            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public Notif mItem;
        ImageView img_notification,iv_clock,iv_date;
        TextView txt_notiTitle;
        TextView txt_notiDesc;
        TextView txt_time;
        TextView txt_date;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            img_notification = mView.findViewById(R.id.img_notification);
            txt_notiTitle = mView.findViewById(R.id.txt_noti_title);
            txt_notiDesc = mView.findViewById(R.id.txt_noti_desc);
            txt_time = mView.findViewById(R.id.txt_time);
            txt_date = mView.findViewById(R.id.txt_date);
            iv_clock = mView.findViewById(R.id.iv_clock);
            iv_date = mView.findViewById(R.id.iv_date);
        }

    }
}
