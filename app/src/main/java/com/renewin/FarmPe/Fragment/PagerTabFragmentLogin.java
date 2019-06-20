package com.renewin.Xohri.Fragment;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


/**
 * Created by Belal on 2/3/2016.
 */
//Extending FragmentStatePagerAdapter
public class PagerTabFragmentLogin extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public PagerTabFragmentLogin(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
           /* case 0:
                ScheduledTabFragment scheduledTabFragment=new ScheduledTabFragment();
                return scheduledTabFragment;*/

            case 0:
                System.out.println("gggfggggggggggg");
                Aboutusdetail_fragment tab1 = new Aboutusdetail_fragment();
                return tab1;
            case 1:
                System.out.println("llllllllllllllllllll1");
                PrivacyPoIciesdetail_fragment tab2 = new PrivacyPoIciesdetail_fragment();
                return tab2;
            case 2:
                System.out.println("gggfggggggggggg");
                Terms_Condition_detail_fragment tab3 = new Terms_Condition_detail_fragment();
                return tab3;


            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }


}