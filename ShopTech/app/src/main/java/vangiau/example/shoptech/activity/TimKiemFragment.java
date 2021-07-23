package vangiau.example.shoptech.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import vangiau.example.shoptech.R;
import vangiau.example.shoptech.adapter.TimKiemAdapter;
import vangiau.example.shoptech.model.DanhSachSanPham;


public class TimKiemFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    RecyclerView   recview;
    SearchView     searchView;
    TimKiemAdapter adapter;

    public TimKiemFragment() {
        // Required empty public constructor
    }

    public static TimKiemFragment newInstance(String param1, String param2) {
        TimKiemFragment fragment = new TimKiemFragment();
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
        View view  = inflater.inflate(R.layout.fragment_tim_kiem, container, false);
        recview    = view.findViewById(R.id.rvKetQuaTimKiem);
        searchView = view.findViewById(R.id.svTimKiem);
        recview.setLayoutManager(new LinearLayoutManager(getContext()));
        searchView.setIconifiedByDefault(true);
        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.requestFocusFromTouch();

        FirebaseRecyclerOptions<DanhSachSanPham> options =
                new FirebaseRecyclerOptions.Builder<DanhSachSanPham>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("San Pham"), DanhSachSanPham.class)
                        .build();
        adapter = new TimKiemAdapter(options);
        recview.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processSearch(s.toLowerCase());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processSearch(s.toLowerCase());
                return false;
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
    private void processSearch(String s)
    {
        FirebaseRecyclerOptions<DanhSachSanPham> options =
                new FirebaseRecyclerOptions.Builder<DanhSachSanPham>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("San Pham").orderByChild("tenthietbi").startAt(s.toLowerCase()).endAt(s+"\uf8ff"), DanhSachSanPham.class)
                        .build();

        adapter=new TimKiemAdapter(options);
        adapter.startListening();
        recview.setAdapter(adapter);

    }
}