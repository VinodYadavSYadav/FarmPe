package com.FarmPe.India.Fragment;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


/**
 * Created by Belal on 2/3/2016.
 */
//Extending FragmentStatePagerAdapter
public class PagerTabFragment extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public PagerTabFragment(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {

        System.out.println("poss"+position);

        //Returning the current tabs
        switch (position) {

           /* case 0:
                ScheduledTabFragment scheduledTabFragment=new ScheduledTabFragment();
                return scheduledTabFragment;*/

            case 0:
                System.out.println("gggfggggggggggg");
                InvitationsLeadsFragment tab1 = new InvitationsLeadsFragment();
                return tab1;
            case 1:
                System.out.println("llllllllllllllllllll1");
                InvitationsLeadsSentFragment tab2 = new InvitationsLeadsSentFragment();
                return tab2;




           /* case 4:
                return NewOrderFragment.newInstance();

            case 5:
                return NewOrderFragment.newInstance();*/


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