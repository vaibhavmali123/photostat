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
import photostat.softhub.models.FetchedListOfSubcategory;
import photostat.softhub.R;

public class PreWeddingPhotography extends Fragment {

    private ImageView imageViePrewWeddingPhoto;
    int index=0;
    ImageButton btnSkipNext,btnSkipPrevious;
    private List<FetchedListOfSubcategory> listOfSubcategories=new ArrayList<>();
    private ProgressDialog progressDialog;
    private ImageView imageViewSlider;
    Button buttonPreWedding;
    public JSONObject JObject;
    public JSONArray jsonArray;
    private ArrayList<String> category=new ArrayList<>();
    private JSONArray result;
    private LinearLayout linearLayoutCategory;
    CarouselView carouselViewLivePhoto;


    public PreWeddingPhotography() {
    }

    public static PreWeddingPhotography newInstance(String param1, String param2) {
        PreWeddingPhotography fragment = new PreWeddingPhotography();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSubCategories();
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
            params.put("category_name", "Pre wedding photography");
            return params;
        }
        };
        queue.add(stringRequest);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_pre_wedding_photography, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonPreWedding =getView().findViewById(R.id.btnPreWedding);
        imageViePrewWeddingPhoto=getView().findViewById(R.id.imagePreWeddingPhoto);
        imageViePrewWeddingPhoto.setImageResource(R.drawable.wedding_baner);

        buttonPreWedding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), SubcategoryActivity.class);
                i.putExtra("selectedcategory", "Pre wedding photography");
                startActivity(i);

            }
        });
    }
}
