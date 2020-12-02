package com.example.tablayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //initalize variable
    TabLayout tabLayout;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assign variable

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        //initialize array
        ArrayList<String> arrayList = new ArrayList<>();

        //add title in array
        arrayList.add("Statistics");
        arrayList.add("Timeslots");
        arrayList.add("Classlist");

        //prepare viewpager
        prepareViewPager(viewPager,arrayList);

        //setup with view pager
        tabLayout.setupWithViewPager(viewPager);
        


    }

    private void prepareViewPager(ViewPager viewPager, ArrayList<String> arrayList) {

        //initialize adapter
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager());
        //initialize main fragment
        MainFragment fragment = new MainFragment();

        for(int i=0;i<arrayList.size(); i++){
            //initialize bundle
            Bundle bundle = new Bundle();
            //put string
            bundle.putString("title", arrayList.get(i));
            //set arguments
            fragment.setArguments(bundle);
            //add fragment
            adapter.addFragment(fragment, arrayList.get(i));
            //define new fragment
            fragment = new MainFragment();
        }

        //set Adapter
        viewPager.setAdapter(adapter);


    }

    private class MainAdapter extends FragmentPagerAdapter {

        //initialize array list
        ArrayList<String> arrayList = new ArrayList<>();
        List<Fragment> fragmentList = new ArrayList<>();

        //create constructor
        public void addFragment(Fragment fragment, String title){

            arrayList.add(title);
            fragmentList.add(fragment);
        }



        public MainAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            //return fragment position
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            //return fragment list size

            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            //return array list position

            return arrayList.get(position);
        }
    }
}