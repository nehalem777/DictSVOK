package nd.dictsv.Adaptor;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;


import nd.dictsv.Fragment.FavoriteFragment;
import nd.dictsv.Fragment.EditFragment;
import nd.dictsv.Fragment.SearchFragment;

/**
 * Created by ND on 9/7/2557.
 */
public class TabPageAdapter extends FragmentPagerAdapter {
    public TabPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        if(position == 0) {
            Log.i("pagerCheck","position 1 ");
            return new SearchFragment();
        }else if (position == 1 ) {
            Log.i("pagerCheck","position 2 ");
            return new FavoriteFragment();
        }else if(position == 2) {
            Log.i("pagerCheck", "position 3 ");
            return new EditFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        //get item count ตามจำนวน Tab
        return 3;
    }
}
