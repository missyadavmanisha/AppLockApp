package com.codingblocks.applockfinal;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

public class ApplicationListAdapter extends RecyclerView .Adapter<RecyclerView .ViewHolder> {

   private List<AppInfo> installedApps = new ArrayList();
    private Context context;
   private SharedPreferences sharedPreference;
   private String requiredAppsType;
 private    Set<String> set = new HashSet<>();
  private   Set<String>set1=new HashSet<>();

    public ApplicationListAdapter(List<AppInfo> appInfoList, Context context, String requiredAppsType) {
        installedApps = appInfoList;
        this.context = context;
        this.requiredAppsType = requiredAppsType;
        sharedPreference = context.getSharedPreferences(APPLockConstants.MyPREFERENCES, MODE_PRIVATE);
       Set<String >strings=  sharedPreference.getStringSet(APPLockConstants.LOCKED,null);
        if(strings!=null) {
            List<String> list = new ArrayList<String>(strings);
            for (int i = 0; i < list.size(); i++) {
               set.add(list.get(i));
            }
            Log.e("SET",""+set.size());
        }

      //  set1=sharedPreference.getStringSet(APPLockConstants.UNLOCKED,null);


          /*  if (requiredAppsType.matches(APPLockConstants.LOCKED)) {
                installedApps.clear();
                installedApps.addAll(lockedFilteredAppList);
            } else if (requiredAppsType.matches(APPLockConstants.UNLOCKED)) {
                installedApps.clear();
                installedApps.addAll(unlockedFilteredAppList);
            }*/

    }

    // Provide a reference to the views for each data item
// Complex data items may need more than one view per item, and
// you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        public TextView applicationName;
        public CardView cardView;
        public ImageView icon;
        public Switch switchView;

        public ViewHolder(View v) {
            super(v);
            applicationName = (TextView) v.findViewById(R.id.applicationName);
            cardView = (CardView) v.findViewById(R.id.card_view);
            icon = (ImageView) v.findViewById(R.id.icon);
            switchView = (Switch) v.findViewById(R.id.switchView);
        }
    }


    @Override
    public ApplicationListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return  new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        final AppInfo appInfo = installedApps.get(i);
        final ViewHolder viewHolder1=(ViewHolder) viewHolder;
        viewHolder1.applicationName.setText(appInfo.getName());
        viewHolder1.icon.setBackgroundDrawable(appInfo.getIcon());

        viewHolder1.switchView.setOnCheckedChangeListener(null);
      /*  if (checkLockedItem(appInfo.getPackageName())) {
            viewHolder1.switchView.setChecked(true);
        } else {
       viewHolder1.switchView.setChecked(false);
        }*/

        if (checkLockedItem(appInfo.getPackageName())) {
         viewHolder1.switchView.setChecked(true);
        } else {
           viewHolder1.switchView.setChecked(false);
        }

     viewHolder1.switchView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor= sharedPreference.edit();
                if (isChecked) {
                 //   AppLockLogEvents.logEvents(AppLockConstants.MAIN_SCREEN, "Lock Clicked", "lock_clicked", appInfo.getPackageName());
                   set.add(appInfo.getPackageName());
                   editor.putStringSet( APPLockConstants.LOCKED,set);
                } else {
                    set1.add(appInfo.getPackageName());
                    editor.putStringSet(APPLockConstants.UNLOCKED,set1);
                  //  AppLockLogEvents.logEvents(AppLockConstants.MAIN_SCREEN, "Unlock Clicked", "unlock_clicked", appInfo.getPackageName());
                }
                editor.apply();

            }
        });




    }


    /*Checks whether a particular app exists in SharedPreferences*/
    public boolean checkLockedItem(String checkApp) {
        boolean check = false;
        Set<String >locked = sharedPreference.getStringSet(APPLockConstants.LOCKED,null) ;
        if (locked != null) {
            for (String lock : locked) {
                if (lock.equals(checkApp)) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }


    // Replace the contents of a view (invoked by the layout manager)




        @Override
        public int getItemCount () {
            return installedApps.size();
        }



}
