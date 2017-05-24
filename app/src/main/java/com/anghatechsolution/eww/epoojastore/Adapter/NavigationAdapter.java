/*
package com.anghatechsolution.eww.epoojastore.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.anghatechsolution.eww.epoojastore.Fragment.FragmentAccount;
import com.anghatechsolution.eww.epoojastore.Fragment.Mainfragment;
import com.anghatechsolution.eww.epoojastore.Fragment.FragmentPoojakit;
import com.anghatechsolution.eww.epoojastore.Fragment.FragmentPoojaService;
import com.anghatechsolution.eww.epoojastore.Fragment.PoojaFragement;

*/
/**
 * Created by eww on 3/23/2017.
 *//*


public class NavigationAdapter extends FragmentStatePagerAdapter
{
    int mNumOfTabs;
    public NavigationAdapter(FragmentManager fm,int NumOfTabs) {
        super(fm);
        this.mNumOfTabs =NumOfTabs;
        //super(fm);
    }

    */
/**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     *//*

    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0:
                Mainfragment mainfragment=new Mainfragment();
                return mainfragment;
            case 1:
                FragmentPoojaService fragmentPoojaService = new FragmentPoojaService();
                return fragmentPoojaService;
            case 2:
                FragmentPoojakit fragmentPoojakit =new FragmentPoojakit();
                return fragmentPoojakit;
            case 3:
                PoojaFragement poojaFragement =new PoojaFragement();
                return poojaFragement;
            case 4:
                FragmentAccount accountFragment= new FragmentAccount();
                return accountFragment;
        }
        return null;
    }

    */
/**
     * Return the number of views available.
     *//*

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
*/
