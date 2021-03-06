package quizcounter.geeks.compete.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import quizcounter.geeks.compete.myapplication.CustomeView.ItemClickSupport;
import quizcounter.geeks.compete.myapplication.RealmUtils.HomeItemModel;
import quizcounter.geeks.compete.myapplication.RealmUtils.Notif;
import quizcounter.geeks.compete.myapplication.RealmUtils.Paper;
import quizcounter.geeks.compete.myapplication.RealmUtils.PaperIdList;
import quizcounter.geeks.compete.myapplication.Utils.Constants;
import quizcounter.geeks.compete.myapplication.wooplrWebViewRedirect.WooplrWebViewActivity;

public class HomePageAdapter  extends RecyclerView.Adapter<HomePageAdapter.ViewHolder> {

    private final List<HomeItemModel> mValues = new ArrayList<>();
    public SQLiteDatabase dbTransaction = null;

    Context context;

    public HomePageAdapter(Activity context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_adapter_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

//        holder.iv_clock.setBackgroundResource(R.drawable.ic_clock_circular_outline);
//        holder.iv_date.setBackgroundResource(R.drawable.ic_calendar);
        holder.mItem = mValues.get(position);
//        String data = Application.getGson().toJson(holder.mItem);
//        String title=mValues.get(position).paperTitle;
        holder.txt_notiTitle.setText(mValues.get(position).title);




        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);
        holder.recyclerView.setLayoutManager(layoutManager);
        holder.recyclerView.setNestedScrollingEnabled(true);
        holder.recyclerView.setHasFixedSize(true);

        if(mValues.get(position).title.equalsIgnoreCase(Constants.Papers)){
            holder.txt_view_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,PaperListActivity.class);
                    intent.putExtra(Constants.type,Constants.Papers);
                    context.startActivity(intent);
                }
            });

            Log.e("mValuesgetposition",""+mValues.get(position).itemIds.get(0));
            Log.e("mValuesgetposition",""+mValues.get(position).itemIds.get(1));
            final Paper paper0= Realm.getDefaultInstance().where(Paper.class).equalTo("paperId",mValues.get(position).itemIds.get(0)).findFirst();
            Paper paper1= Realm.getDefaultInstance().where(Paper.class).equalTo("paperId",mValues.get(position).itemIds.get(1)).findFirst();

            final ArrayList<Object> papers=new ArrayList<>();
            papers.add(paper0);
            papers.add(paper1);

            PaperListAdapter paperListAdapter=new PaperListAdapter((Activity)context);
            holder.recyclerView.setAdapter(paperListAdapter);

            ItemClickSupport.addTo(holder.recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                    Intent intent=new Intent(context,TestActivity.class);
                    intent.putExtra("id",((Paper)papers.get(position)).paperId);
                    intent.putExtra("title",((Paper)papers.get(position)).paperTitle);
                    context.startActivity(intent);
                }
            });

            paperListAdapter.addNotification(papers,1);
        }else if(mValues.get(position).title.equalsIgnoreCase(Constants.Notifications)){
            Log.e("mValuesgetposition",""+mValues.get(position).itemIds.get(0));
            Log.e("mValuesgetposition",""+mValues.get(position).itemIds.get(1));
            Notif paper0= Realm.getDefaultInstance().where(Notif.class).equalTo("notifId",mValues.get(position).itemIds.get(0)).findFirst();
            Notif paper1= Realm.getDefaultInstance().where(Notif.class).equalTo("notifId",mValues.get(position).itemIds.get(1)).findFirst();

            final ArrayList<Notif> papers=new ArrayList<>();
            papers.add(paper0);
            papers.add(paper1);

            holder.txt_view_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent=new Intent(context, WooplrWebViewActivity.class);
//                    intent.putExtra(Constants.webview_title,((Notif)papers.get(position)).notifIdTitle);
//                    intent.putExtra(Constants.urlWooplrWebView,((Notif)papers.get(position)).notifData);
//                    context.startActivity(intent);
                    Intent intent=new Intent(context,PaperListActivity.class);
                    intent.putExtra(Constants.type,Constants.Notifications);
                    context.startActivity(intent);
                }
            });


            NotifListAdapter paperListAdapter=new NotifListAdapter((Activity)context);
            holder.recyclerView.setAdapter(paperListAdapter);
            paperListAdapter.addNotification(papers);

            ItemClickSupport.addTo(holder.recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                    Intent intent=new Intent(context,WooplrWebViewActivity.class);
                    intent.putExtra(Constants.webview_title,((Notif)papers.get(position)).notifIdTitle);
                    intent.putExtra(Constants.urlWooplrWebView,((Notif)papers.get(position)).notifData);
                    context.startActivity(intent);
                }
            });
        }

//        holder.txt_notiDesc.setText(Html.fromHtml(String.valueOf(Html.fromHtml(mValues.get(position).paperTitle))));
//        if(holder.mItem.paperData.length()>30){
//            holder.txt_notiDesc.setText(holder.mItem.paperData.substring(0,30));
//        }else {
//            holder.txt_notiDesc.setText(holder.mItem.paperData);
//        }
//        String desc=mValues.get(position).;
//        holder.txt_notiDesc.setText(desc);
//        holder.txt_notiDesc.setText(mValues.get(position).paperDesc);

//        holder.mView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ((PaperListActivity)context).onClikitem(holder.mItem.paperId);
//            }
//        });

//        holder.txt_date.setText("20-11-2019");
//        holder.txt_time.setText("02:12 PM");

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
//            holder.iv_clock.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_clock_circular_outline));
//            holder.iv_date.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_calendar));
//        }

//        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
//            layout.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ready) );
//        } else {
//            layout.setBackground(ContextCompat.getDrawable(context, R.drawable.ready));
//        }

//        Drawable drawable = holder.iv_clock.getBackground();
//        drawable.setColorFilter(new PorterDuffColorFilter(Color.parseColor("#757575"),PorterDuff.Mode.SRC_IN));
//        //            drawable.setColor(Color.parseColor("#757575"));
//
//        Drawable gradientDrawable = holder.iv_date.getBackground();
//        gradientDrawable.setColorFilter(new PorterDuffColorFilter(Color.parseColor("#757575"),PorterDuff.Mode.SRC_IN));


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
    public void addNotification(List<HomeItemModel> notificationpojo) {
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
//    public int getItemCount() {
//        return 9;
//    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public HomeItemModel mItem;
        RecyclerView recyclerView;
        //        ImageView img_notification,iv_clock,iv_date;
        TextView txt_notiTitle;
        TextView txt_view_all;
//        TextView txt_time;
//        TextView txt_date;

        public ViewHolder(View view) {
            super(view);
            mView = view;
//            img_notification = mView.findViewById(R.id.img_notification);
            txt_notiTitle = mView.findViewById(R.id.txt_home_item_title);
            recyclerView = mView.findViewById(R.id.rv_home_subitem);
            txt_view_all = mView.findViewById(R.id.txt_view_all);
//            txt_notiDesc = mView.findViewById(R.id.txt_paper_desc);
//            txt_time = mView.findViewById(R.id.txt_time);
//            txt_date = mView.findViewById(R.id.txt_date);
//            iv_clock = mView.findViewById(R.id.iv_clock);
//            iv_date = mView.findViewById(R.id.iv_date);
        }

    }
}


