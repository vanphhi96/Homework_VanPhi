package android.trantan.freemusic11.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.trantan.freemusic11.fragments.DowloadFragment;
import android.trantan.freemusic11.fragments.FavouriteFragment;
import android.trantan.freemusic11.fragments.MusicTypeFragment;

/**
 * Created by vanph on 18/11/2017.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return  new MusicTypeFragment();
            case 1:
                return new FavouriteFragment();
            case 2:
                return new DowloadFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
