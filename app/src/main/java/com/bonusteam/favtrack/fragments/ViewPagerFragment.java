package com.bonusteam.favtrack.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bonusteam.favtrack.R;
import com.bonusteam.favtrack.adapters.SectionsAdapter;
import com.bonusteam.favtrack.fragments.viewpagertabs.Favorite;
import com.bonusteam.favtrack.fragments.viewpagertabs.General;
import com.bonusteam.favtrack.utilities.Utilidades;

/**
 * Created by Deryan Cruz on 10/07/2018.
 */


public class ViewPagerFragment extends Fragment {
    View vista;
    private AppBarLayout appBar;
    private TabLayout tab;
    private ViewPager viewPager;
    SectionsAdapter adapter;
    private FragmentManager fm;

    public ViewPagerFragment() {
        fm = getFragmentManager();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista=inflater.inflate(R.layout.view_pager, container, false);
        viewPager = vista.findViewById(R.id.idViewPagerInformacion);

        SectionsAdapter adapter=new SectionsAdapter(getChildFragmentManager());
        adapter.addFragment(new General(),"Generals");
        adapter.addFragment(new Favorite(),"Favorite");
        viewPager.setAdapter(adapter);
        tab = vista.findViewById(R.id.tabLayout);
        tab.setTabGravity(TabLayout.GRAVITY_FILL);
        tab.setupWithViewPager(viewPager,true);
        /*if(Utilidades.rotacion==0){
            View parent= (View) container.getParent();

            if(appBar==null){
                appBar= (AppBarLayout) parent.findViewById(R.id.appBar);
                tab=vista.findViewById(R.id.tabLayout);
                tab.setTabTextColors(Color.parseColor("#000000"),Color.parseColor("#000000"));
                appBar.addView(tab);


                viewPager= (ViewPager) vista.findViewById(R.id.idViewPagerInformacion);
                llenarViewPager(viewPager);
                viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                    }
                });
                tab.setupWithViewPager(viewPager);
            }
            tab.setTabGravity(TabLayout.GRAVITY_FILL);
        }else{
            Utilidades.rotacion=1;
        }*/
        return vista;

    }

    private void llenarViewPager(ViewPager viewPager) {
        SectionsAdapter adapter=new SectionsAdapter(getChildFragmentManager());
        adapter.addFragment(new General(),"Generals");
        adapter.addFragment(new Favorite(),"Favorite");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        /*if(Utilidades.rotacion==0){
            appBar.removeView(tab);
        }*/

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

}
