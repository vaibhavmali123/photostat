package photostat.softhub;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import photostat.softhub.models.FetchedListOfSize;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentSize#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSize extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Session session;
    TextView size;
    int index=0;


    ImageView previous,next;

    /*private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;*/
    private List<FetchedListOfSize> listOfSizes;

    public FragmentSize() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentSize.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentSize newInstance(String param1, String param2) {
        FragmentSize fragment = new FragmentSize();
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
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_size, container, false);

        session = new Session(getContext());

        size = rootView.findViewById(R.id.size);

        previous = rootView.findViewById(R.id.pre);
        next = rootView.findViewById(R.id.next);


       /* recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewSize);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));*/

        listOfSizes = new ArrayList<>();

        loadRecyclerViewData();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  if (listOfSizes.size()>=index){
                    index = ++index;
                    size.setText(listOfSizes.get(index).getSize());
                }else{
                    Toast.makeText(getActivity(), "check", Toast.LENGTH_SHORT).show();
                }*/
                if (index<listOfSizes.size()-1){
                    index = ++index;
                    size.setText(listOfSizes.get(index).getSize());

                    session.setFormat(listOfSizes.get(index).getPrice().toString());
                    Log.d("price",session.prefs.getString("format",null));

                }else{
                    Toast.makeText(getActivity(), "no more sizes available", Toast.LENGTH_LONG).show();
                }
                /*for (index=0;index<listOfSizes.size();index++){

                    size.setText(listOfSizes.get(index).getSize());

                }*/


            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*if (listOfSizes.size()>=index){
                    index = --index;
                    size.setText(listOfSizes.get(index).getSize());
                }else{
                    Toast.makeText(getActivity(), "check", Toast.LENGTH_SHORT).show();
                }*/
                /*for (index=listOfSizes.size()-1;index<listOfSizes.size();index--){

                    size.setText(listOfSizes.get(index).getSize());

                }*/

                if (index>0){
                    index = --index;
                    size.setText(listOfSizes.get(index).getSize());

                    session.setFormat(listOfSizes.get(index).getPrice().toString());
                    Log.d("price",session.prefs.getString("format",null));
                }else{
                    Toast.makeText(getActivity(), "please check more size", Toast.LENGTH_SHORT).show();
                }

            }
        });


        return rootView;
    }

    private void loadRecyclerViewData() {

        String uri = getResources().getString(R.string.base_url)+"size_price.php";
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
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
                                    FetchedListOfSize flp = new FetchedListOfSize(JO1.getString("id"),
                                            (JO1.getString("size")),
                                            JO1.getString("prize"));
                                    listOfSizes.add(flp);

                                }
                                size.setText(listOfSizes.get(index).getSize());
                                session.setFormat(listOfSizes.get(index).getPrice().toString());
                                Log.d("price",session.prefs.getString("format",null));

                               /* adapter = new SizeListAdapter(listOfSizes,getApplicationContext());
                                recyclerView.setAdapter(adapter);*/
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
                });
        queue.add(stringRequest);
    }

    }
