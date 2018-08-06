package com.ming;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.ming.adapter.MyFragmentPagerAdapter;
import com.ming.fragement.DashFragment;
import com.ming.fragement.HomeFragment;
import com.ming.fragement.notificationFragment;
import java.util.ArrayList;

/**
 * Created by ming on 2018/8/6.
 */


public  class MainActivity extends AppCompatActivity {

    //private TextView mTextMessage;
    private ViewPager mViewPager;
    private ArrayList<Fragment> fragelist;
    //private MyPagerAdapter mviewadapter;
    private MyFragmentPagerAdapter mFragPageAdapter;
    private android.support.v4.app.Fragment homeFrage,dashFrage,notifiFrage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mViewPager=(ViewPager)findViewById(R.id.mViewPager);
        fragelist=new ArrayList<>();
        homeFrage=new HomeFragment();
        dashFrage=new DashFragment();
        notifiFrage=new notificationFragment();
        fragelist.add(homeFrage);
        fragelist.add(dashFrage);
        fragelist.add(notifiFrage);

        //LayoutInflater li=getLayoutInflater();
        //viewlist.add(li.inflate(R.layout.pageviewindicate,null,false));
        //viewlist.add(li.inflate(R.layout.pageviewindicate1,null,false));
        mFragPageAdapter=new MyFragmentPagerAdapter(getSupportFragmentManager(),fragelist);
        //mviewadapter=new MyPagerAdapter(viewlist);

        //mViewPager.setAdapter(mFragPageAdapter);
    }

}