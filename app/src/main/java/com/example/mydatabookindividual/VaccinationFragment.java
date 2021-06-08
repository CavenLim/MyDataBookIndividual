package com.example.mydatabookindividual;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class VaccinationFragment extends Fragment {
    private static final int MODE_PRIVATE = 0 ;
    ActionBar ab;
    Button btnVaccBio;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle drawerToggle;
    TextView tvShow;
    private SharedPreferences savedText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.vaccinationfragment, container, false);
        FloatingActionButton fab = view.findViewById(R.id.fab3);
        ab = ((AppCompatActivity)getActivity()).getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        drawerLayout = getActivity().findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(getActivity(),drawerLayout,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        tvShow = view.findViewById(R.id.tvShowFrag);
        btnVaccBio = view.findViewById(R.id.btnFragVacc);

        btnVaccBio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                // Get the layout inflater
                LayoutInflater inflater = requireActivity().getLayoutInflater();
                View view2 = inflater.inflate(R.layout.customalert2,null);

                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                builder.setView(inflater.inflate(R.layout.customalert2, null))
                        // Add action buttons

                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                // sign in the user ...
                                Dialog newDialog = (Dialog) dialog;
                                EditText newBio = newDialog.findViewById(R.id.etNewAnni);
                                if(!newBio.getText().toString().isEmpty()) {
                                    tvShow.setText(newBio.getText().toString());
                                }
                                else{
                                    Toast.makeText(getContext(),"Enter Something!",Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                builder.create().show();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.START);

            }
        });
   return view;
    }
    @Override
    public void onPause() {
        SharedPreferences.Editor editor = savedText.edit();
        editor.putString("savedText2",tvShow.getText().toString());
        editor.commit();
        super.onPause();
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onResume() {
        savedText = getActivity().getSharedPreferences("savedText2",MODE_PRIVATE);
        tvShow.setText(savedText.getString("savedText2",""));
        super.onResume();
    }
}