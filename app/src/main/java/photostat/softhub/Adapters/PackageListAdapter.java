package photostat.softhub.Adapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import photostat.softhub.PackageDetailsActivity;
import photostat.softhub.R;
import photostat.softhub.models.FetchedListOfPackages;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class PackageListAdapter extends RecyclerView.Adapter<PackageListAdapter.ViewHolder> {

    private List<FetchedListOfPackages> listOfPackages;
    private Context context;

    public PackageListAdapter(List<FetchedListOfPackages> listOfPackages, Context context) {
        this.listOfPackages = listOfPackages;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.packages, parent, false);


        context = parent.getContext();
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final FetchedListOfPackages listOfPackages1 = listOfPackages.get(position);

        holder.package_name.setText(listOfPackages1.getPackageName());
        holder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, PackageDetailsActivity.class);
                intent.putExtra("package_name",listOfPackages1.getPackageName());
                intent.putExtra("package_details",listOfPackages1.getPackageDetails());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listOfPackages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView package_name;
        Button details;


        public ViewHolder(View itemView) {
            super(itemView);

            package_name = (TextView) itemView.findViewById(R.id.package_name);
            details = itemView.findViewById(R.id.details);

        }
    }
}
