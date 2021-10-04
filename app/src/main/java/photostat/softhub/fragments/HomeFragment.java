package photostat.softhub.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import photostat.softhub.R;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class HomeFragment extends Fragment {
    LinearLayout linearLayout;
    private CarouselView carouselView;
    private int sliderImages[]={R.drawable.wedding_baner,R.drawable.wedding_baner,R.drawable.wedding_baner,R.drawable.wedding_baner,R.drawable.wedding_baner};
    private BottomNavigationView bottomNavigationViewHome;
    private RecyclerView recyclerViewIndicator;
    RecyclerView.LayoutManager layoutManager;

    BottomNavigationView.OnNavigationItemSelectedListener navListener=new
            BottomNavigationView.OnNavigationItemSelectedListener() {        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // By using switch we can easily get
            // the selected fragment
            // by using there id.

                Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.location:
                    linearLayout.setVisibility(View.GONE);
                    selectedFragment = new LocationFragment();
                    break;
                case R.id.copy:
                    linearLayout.setVisibility(View.GONE);
                    selectedFragment = new CopyFragment();
                    break;
                case R.id.products:
                    linearLayout.setVisibility(View.GONE);
                    selectedFragment = new ProductsFragment();
                    break;
                case R.id.weddingPhotography:
                    linearLayout.setVisibility(View.GONE);
                    selectedFragment = new WeddingPhotographyFragment();
                    break;
                case R.id.preWeddingPhotography:
                    linearLayout.setVisibility(View.GONE);
                    selectedFragment = new PreWeddingPhotography();
                    break;
            }
            // It will help to replace the
            // one fragment to other.
                getFragmentManager()
                        .beginTransaction()
                        .remove(new HomeFragment());
                getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_home_container, selectedFragment)
                    .commit();
            return true;
        }
    };
    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bottomNavigationViewHome=getView().findViewById(R.id.bottom_nav_home);
        bottomNavigationViewHome.setOnNavigationItemSelectedListener(navListener);
        linearLayout=getView().findViewById(R.id.temporaryCategory);
        carouselView=getView().findViewById(R.id.carouselView);
        carouselView.setImageListener(imageListener);
        carouselView.setPageCount(sliderImages.length);
        recyclerViewIndicator=getView().findViewById(R.id.listIndicator);
        recyclerViewIndicator.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL,false);
      //  recyclerViewIndicator.setAdapter();
    }
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sliderImages[position]);
        }
    };
}
