package photostat.softhub;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentTotal#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTotal extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    int total;

    TextView total_txt;
    Session session;
    int copies,price;

    public FragmentTotal() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentTotal.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentTotal newInstance(String param1, String param2) {
        FragmentTotal fragment = new FragmentTotal();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_total, container, false);

        session = new Session(getContext());

        total_txt = rootView.findViewById(R.id.total);

        copies = Integer.parseInt(session.prefs.getString("format",null));
        price = Integer.parseInt(session.prefs.getString("copies",null));
        total = copies*price;
        total_txt.setText(String.valueOf(total));

        /*Log.d("p",session.prefs.getString("format",null));
        Log.d("c",session.prefs.getString("copies",null));*/

        //total_txt .setText(String.valueOf(total));
        /*total = getArguments().getInt("copies");

        total_txt.setText(String.valueOf(total));*/

        // Inflate the layout for this fragment
        return rootView;
    }

}