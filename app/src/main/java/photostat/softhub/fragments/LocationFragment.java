package photostat.softhub.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.synnapps.carouselview.CarouselView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import photostat.softhub.SubcategoryActivity;
import photostat.softhub.utils.Config;
import photostat.softhub.Description;
import photostat.softhub.models.FetchedListOfSubcategory;
import photostat.softhub.R;
public class LocationFragment extends Fragment {
    TextView textViewTitle;
    private ImageView imageViewLivePhoto;
    int index=0;
    ImageButton btnSkipNext,btnSkipPrevious;
    private List<FetchedListOfSubcategory> listOfSubcategories=new ArrayList<>();
    private ProgressDialog progressDialog;
    private ImageView imageViewSlider;
    Button buttonCategoryTitle;
    public JSONObject JObject;
    public JSONArray jsonArray;
    private ArrayList<String> category=new ArrayList<>();
    private JSONArray result;
    private LinearLayout linearLayoutCategory;
    CarouselView carouselViewLivePhoto;
    int sliderImages[]={R.drawable.action_cart,R.drawable.action_cart,R.drawable.action_cart,R.drawable.action_cart,R.drawable.action_cart};
    public LocationFragment() {
        // Required empty public constructor
    }

    public static LocationFragment newInstance(String param1, String param2) {
        LocationFragment fragment = new LocationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getSubCategories();
    }

    private void getSubCategories() {

        String uri = getResources().getString(R.string.base_url)+"subcategory_list.php";
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                uri,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.d("DDD",s.toString());

                        String data = "Y";
                        try {
                            JSONObject JO = new JSONObject(s);

                            if(JO.getString("status_code").equals("200")){
                                JSONArray JA = JO.getJSONArray("subcategory_list");

                                for(int i=0; i<JA.length(); i++){
                                    JSONObject JO1 = JA.getJSONObject(i);
                                    FetchedListOfSubcategory flp = new FetchedListOfSubcategory(JO1.getString("id"),
                                            JO1.getString("subcategory_name"),
                                            JO1.getString("description"),
                                            JO1.getString("icon"));
                                    listOfSubcategories.add(flp);
                                }

                            Log.d("DDD f",listOfSubcategories.get(index).getSubcategory_name());
                                if (listOfSubcategories.size()>=0){

//                                    buttonCategoryTitle.setText(listOfSubcategories.get(0).getSubcategory_name());

                                }
                                // progressDialog.dismiss();
                            }else{
                                progressDialog.dismiss();
                                Toast.makeText(getContext(), JO.getString("message"), Toast.LENGTH_LONG).show();
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
                }){@Override
        public Map<String, String> getParams() {
            Map<String, String> params = new HashMap<>();
            params.put("category_name", "Live photography");
            return params;
        }
        };
        queue.add(stringRequest);

    }

    private void getCategory(JSONArray result) {
        for (int i=0; i<result.length();i++){

            try{
                JSONObject jsonObject = result.getJSONObject(i);
                category.add(jsonObject.getString(Config.TAG_CATEGORYNAME));
                Log.d("REEE",category.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageViewLivePhoto=getView().findViewById(R.id.imageLivePhoto);
        buttonCategoryTitle=getView().findViewById(R.id.btnCategoryTitle);
        btnSkipNext=getView().findViewById(R.id.skipNextBtn);
        btnSkipPrevious=getView().findViewById(R.id.skipPreviousBtn);
        imageViewLivePhoto.setImageResource(R.drawable.action_cart);
        imageViewSlider=getView().findViewById(R.id.imageLivePhoto);

        linearLayoutCategory =view.findViewById(R.id.temporaryCategory);
        if (linearLayoutCategory!=null){
            linearLayoutCategory.setVisibility(View.INVISIBLE);
        }

        btnSkipNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index=++index;
                if (index<listOfSubcategories.size()){
                    Log.d("DDD", String.valueOf(index));
                    Log.d("DDD",listOfSubcategories.get(index).getSubcategory_name());
                }
            }
        });
        btnSkipPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (index!=0){
                    index=--index;
                    Log.d("DDD", String.valueOf(index));
                    Log.d("DDD",listOfSubcategories.get(index).getSubcategory_name());
                }
            }
        });

        buttonCategoryTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DDD",listOfSubcategories.get(index).getSubcategory_name());
                Intent i = new Intent(getContext(), SubcategoryActivity.class);
                i.putExtra("selectedcategory", "Live photography");
                i.putExtra("description", listOfSubcategories.get(index).getSubcategory_description());
                startActivity(i);

            }
        });
    }
}