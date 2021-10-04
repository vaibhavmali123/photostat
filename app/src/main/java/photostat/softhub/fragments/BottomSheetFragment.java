package photostat.softhub.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import photostat.softhub.Adapters.PackageListAdapter;
import photostat.softhub.R;
import photostat.softhub.models.FetchedListOfPackages;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BottomSheetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BottomSheetFragment extends BottomSheetDialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //Button details;
    RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<FetchedListOfPackages> listOfPackages;
    //String subcategroy_name = "Prewedding Shooting";
    String subcategroy_name;
    ImageView our_package_back;

    public BottomSheetFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BottomSheetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BottomSheetFragment newInstance(String param1, String param2) {
        BottomSheetFragment fragment = new BottomSheetFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        Intent intent = getActivity().getIntent();
        subcategroy_name = getActivity().getIntent().getStringExtra("subcategory_name");
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);
        // Inflate the layout for this fragment

        //details = rootView.findViewById(R.id.details);
        /*details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),PackageDetailsActivity.class);
                startActivity(intent);
            }
        });*/
        our_package_back = rootView.findViewById(R.id.our_packages_back);

        our_package_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
                bottomSheetFragment.dismiss();*/
              /*  Intent intent = new Intent(getContext(),SubcategoryActivity.class);
                startActivity(intent);*/

            }
        });
        BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
        recyclerView = rootView.findViewById(R.id.br);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        listOfPackages = new ArrayList<>();

        adapter = new PackageListAdapter(listOfPackages,getActivity());
        recyclerView.setAdapter(adapter);
        loadRecyclerViewData();

        return rootView;
    }

    private void loadRecyclerViewData() {

        String uri = getResources().getString(R.string.base_url)+"package_list.php";
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                uri,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        String data = "Y";
                        try {
//                            JSONObject JO = new JSONObject(s);
                            JSONArray JA = new JSONArray(s);
//                            String code = JO.getString("data_code");
                            if(JA.length()>0){

                                for(int i=0; i<JA.length(); i++){
                                    JSONObject JO1 = JA.getJSONObject(i);
                                    FetchedListOfPackages flp = new FetchedListOfPackages(JO1.getString("id"),
                                            (JO1.getString("package_name")),
                                            JO1.getString("package_details"));
                                    listOfPackages.add(flp);
                                }

                                adapter = new PackageListAdapter(listOfPackages,getActivity());
                                recyclerView.setAdapter(adapter);


                            }else{

                                Toast.makeText(getActivity(), "check", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        // Toast.makeText(getContext(), "Error.Response",Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();

                params.put("subcategory_name",subcategroy_name);

                return params;
            }
        };
        queue.add(stringRequest);
    }


}