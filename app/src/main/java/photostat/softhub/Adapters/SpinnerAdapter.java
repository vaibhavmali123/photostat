package photostat.softhub.Adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import android.widget.ArrayAdapter;

public class SpinnerAdapter extends ArrayAdapter {


    public SpinnerAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public int getCount(){

        int count = super.getCount();
        return count>0 ? count-1 : count ;
    }

}
