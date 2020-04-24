package com.mafh.myregisterv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class viewDataTabs : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_data_tabs_)

        val view_pager= findViewById<ViewPager>(R.id.view_pager_main)
        val tab_layout= findViewById<TabLayout>(R.id.tab_layout_main)

        val daily_view_frag= dailyViewFrag()
        val weekly_view_frag= weeklyViewFrag()
        val monthly_view_frag= monthlyViewFrag()
        val yearly_view_frag=yearlyViewFrag()


        tab_layout.setupWithViewPager(view_pager)

        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.addFragments(daily_view_frag,"Daily")
        viewPagerAdapter.addFragments(weekly_view_frag,"Weekly")
        viewPagerAdapter.addFragments(monthly_view_frag,"Monthly")
        viewPagerAdapter.addFragments(yearly_view_frag,"Yearly")
        view_pager.adapter=viewPagerAdapter


    }
}

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm){


    val fragmentList = arrayListOf<Fragment>()
    val fragment_titles= arrayListOf<String>()

    override fun getItem(position: Int): Fragment {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return fragmentList.get(position)
    }

    override fun getCount(): Int {
        //TODO("not implemented")
        // To change body of created functions use File | Settings | File Templates.
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragment_titles.get(position)
    }

    public fun addFragments(fragment:Fragment, title:String){
        fragmentList.add(fragment)
        fragment_titles.add(title)

    }

}
