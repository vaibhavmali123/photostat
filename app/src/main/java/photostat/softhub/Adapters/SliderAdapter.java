package photostat.softhub.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import photostat.softhub.R;
import photostat.softhub.models.FetchedListOfImages;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.ViewHolder> {


    private List<FetchedListOfImages> listItems;
    private Context context;




    public SliderAdapter(List<FetchedListOfImages> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }



//    @NonNull
//    @Override
//    public ContactAdapterBanner.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.row_banner_image, parent, false);
//
//        context = parent.getContext();
//        return new ViewHolder(v);
//
//    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_slider_image, parent, false);


        context = parent.getContext();
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final FetchedListOfImages listItem = listItems.get(position);

        //Picasso.with(context).load(listItem.getImage()).into(holder.Image);
        Picasso.get().load(listItem.getImage()).into(holder.Image);

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView Image;


        public ViewHolder(View itemView) {
            super(itemView);

            Image = (ImageView) itemView.findViewById(R.id.sliderImage);

        }
    }

}
