package photostat.softhub.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import photostat.softhub.R;
import photostat.softhub.SubcategoryActivity;

public class CopyFragment extends Fragment {

    public CopyFragment() {
        // Required empty public constructor
    }
    public static CopyFragment newInstance(String param1, String param2) {
        CopyFragment fragment = new CopyFragment();
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
        return inflater.inflate(R.layout.fragment_copy, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button=getView().findViewById(R.id.btnPhotoCopy);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), SubcategoryActivity.class);
                i.putExtra("selectedcategory", "soft copy hard copy");
                startActivity(i);

            }
        });

    }
}