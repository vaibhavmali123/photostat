package photostat.softhub.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import photostat.softhub.R;
import photostat.softhub.models.FetchedListOfOrders;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ViewHolder> {

    private List<FetchedListOfOrders> listOfOrders;
    private Context context;

    public OrderListAdapter(List<FetchedListOfOrders> listOfOrders, Context context) {
        this.listOfOrders = listOfOrders;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_orders, parent, false);


        context = parent.getContext();
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final FetchedListOfOrders listOfOrders1 = listOfOrders.get(position);

        holder.firstName.setText(listOfOrders1.getFirstName());
        holder.lastName.setText(listOfOrders1.getLastName());
        holder.mobileNumber.setText(listOfOrders1.getMobileNumber());
        holder.packageName.setText(listOfOrders1.getPackageName());
        holder.cityName.setText(listOfOrders1.getCityName());
        holder.eventName.setText(listOfOrders1.getEventName());
        holder.address.setText(listOfOrders1.getEventAddress());
        holder.date.setText(listOfOrders1.getOrderDate());
    }

    @Override
    public int getItemCount() {
        return listOfOrders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView firstName;
        public TextView lastName;
        public TextView mobileNumber;
        public TextView packageName;
        public TextView cityName;
        public TextView eventName;
        public TextView address;
        public TextView date;



        public ViewHolder(View itemView) {
            super(itemView);

           firstName = itemView.findViewById(R.id.first_name);
           lastName = itemView.findViewById(R.id.last_name);
           mobileNumber = itemView.findViewById(R.id.mobile);
           packageName = itemView.findViewById(R.id.packageName);
           cityName = itemView.findViewById(R.id.cityName);
           eventName = itemView.findViewById(R.id.eventName);
           address = itemView.findViewById(R.id.address);
           date = itemView.findViewById(R.id.date);

        }
    }
}
