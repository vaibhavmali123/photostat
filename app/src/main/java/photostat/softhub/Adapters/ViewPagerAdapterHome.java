package photostat.softhub.Adapters;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import photostat.softhub.FragmentCopies;
import photostat.softhub.FragmentPhoto;
import photostat.softhub.FragmentSize;
import photostat.softhub.FragmentTotal;

public class ViewPagerAdapterHome extends FragmentPagerAdapter {


    ArrayList<Fragment>fragments = new ArrayList<>();
    ArrayList<String>strings =  new ArrayList<>();


    public ViewPagerAdapterHome(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0:
                return new FragmentPhoto();
            case 1:
                return new FragmentSize();
            case 2:
                return new FragmentCopies();
            case 3:
                return new FragmentTotal();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void add(Fragment fr,String str){

        fragments.add(fr);
        strings.add(str);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return strings.get(position);
    }
}