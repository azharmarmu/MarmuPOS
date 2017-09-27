package azhar.sudha.marmupos.main.view;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import azhar.sudha.marmupos.R;
import azhar.sudha.marmupos.main.MainActivity;
import azhar.sudha.marmupos.main.fragment.AllFragment;
import azhar.sudha.marmupos.main.fragment.FavFragment;
import azhar.sudha.marmupos.utils.Constants;
import azhar.sudha.marmupos.utils.ViewUtils;

/**
 * Created by azharuddin on 20/9/17.
 */

@SuppressWarnings("ConstantConditions")
public class Sales {

    public void evaluate(final MainActivity activity, View itemView) {
        RelativeLayout charge = itemView.findViewById(R.id.charge);
        TextView chargeText = itemView.findViewById(R.id.charge_text);
        final double chargeAmount = Double.parseDouble(ViewUtils.Stringify(chargeText)
                .toLowerCase().replace("charge", "").trim());
        charge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chargeAmount > 0) {
                    //activity.startActivity(new Intent(activity, PaymentActivity.class));
                }
            }
        });

        ViewPager viewPager = itemView.findViewById(R.id.viewpager);
        setupViewPager(activity, viewPager);

        TabLayout tabLayout = itemView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        //tab related icon
        tabLayout.getTabAt(0).setIcon(activity.getDrawable(R.drawable.ic_menu_items));
        tabLayout.getTabAt(1).setIcon(activity.getDrawable(R.drawable.ic_star));

    }

    private void setupViewPager(MainActivity activity, ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(activity.getSupportFragmentManager());
        adapter.addFragment(new AllFragment(), Constants.ALL);
        adapter.addFragment(new FavFragment(), Constants.FAV);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }


        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
