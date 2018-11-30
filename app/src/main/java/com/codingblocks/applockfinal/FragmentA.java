package com.codingblocks.applockfinal;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.audiofx.BassBoost;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class FragmentA extends Fragment {
    static String requiredAppsType;
    Button Chamber,batterysaving;
    SharedPreferences sharedPreferences;

    public static FragmentA newInstance(String requiredApps) {
        requiredAppsType = requiredApps;
        FragmentA f = new FragmentA();
        return (f);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences=getContext().getSharedPreferences(APPLockConstants.MyPREFERENCES,Context.MODE_PRIVATE);



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fargment_a, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Chamber =view.findViewById(R.id.button_chamber);
        batterysaving=view.findViewById(R.id.button_batterysaving);
        Chamber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),Chamberclass.class);
                startActivity(i);
            }
        });

         batterysaving.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
if(sharedPreferences.getString(APPLockConstants.IS_BATERY_ACTIVATE,"false")!="true")
{
                 new AlertDialog.Builder(getContext())
                         .setMessage("To enable Power saving mode,please allow Accessibility services.The service is only used to remind users with disabilities to unlock apps ,and reduce battery usage .Please be assured that Applock will never use it to access your private data")
                         .setCancelable(true)
                         .setPositiveButton("ACTIVATE", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialog, int which) {
                                 SharedPreferences.Editor editor=sharedPreferences.edit();
                                 editor.putString(APPLockConstants.IS_BATERY_ACTIVATE,"true");
                                 editor.apply();
                                 Intent i = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                                 startActivity(i);
                                 }
                                 })
                         .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialog, int which) {
                                 Toast.makeText(getContext(), "CANCEL CLICKED", Toast.LENGTH_SHORT).show();
                                 }
                                 })
                         .show();
}
else {
    Toast.makeText(getContext(),"Already enabled",Toast.LENGTH_SHORT).show();
}
                 }
                 });
        RecyclerView recyclerView = view.findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ApplicationListAdapter hetrogeneousAdapter = new ApplicationListAdapter(((MainActivity) getActivity()).getListOfInstalledApp(getActivity()), (AppCompatActivity) getActivity(), requiredAppsType);

        recyclerView.setAdapter(hetrogeneousAdapter);


    }

}
