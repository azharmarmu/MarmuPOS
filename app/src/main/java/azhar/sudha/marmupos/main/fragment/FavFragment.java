package azhar.sudha.marmupos.main.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import azhar.sudha.marmupos.R;
import azhar.sudha.marmupos.main.adapter.ItemListAdapter;
import azhar.sudha.marmupos.main.model.HashListModel;
import azhar.sudha.marmupos.utils.Constants;
import azhar.sudha.marmupos.utils.DBUtils;

@SuppressWarnings({"unchecked", "ConstantConditions"})
public class FavFragment extends Fragment {


    public FavFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_common, container, false);
        prepareItemLists();
        return rootView;
    }

    List<HashListModel> itemList;

    private void prepareItemLists() {
        itemList = new ArrayList<>();
        final LinearLayout noFav = rootView.findViewById(R.id.no_fav);
        final LinearLayout noAll = rootView.findViewById(R.id.no_all);
        noFav.setVisibility(View.GONE);
        noAll.setVisibility(View.GONE);
        DatabaseReference itemRef = DBUtils.DATABASE.getReference(DBUtils.mAuth.getCurrentUser().getUid()).child(Constants.ITEM);
        itemRef.keepSynced(true);
        itemRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    noFav.setVisibility(View.GONE);

                    HashMap<String, Object> itemListMap = (HashMap<String, Object>) dataSnapshot.getValue();
                    for (String key : itemListMap.keySet()) {
                        HashMap<String, Object> itemDetails = (HashMap<String, Object>) itemListMap.get(key);
                        if ((boolean) itemDetails.get(Constants.ITEM_FAV)) {
                            itemList.add(new HashListModel(key, itemListMap.get(key)));
                        }
                    }
                    populateView();
                } else {
                    noFav.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(Constants.ERROR, databaseError.getMessage());
            }
        });
    }

    private void populateView() {
        RecyclerView.Adapter adapter = new ItemListAdapter(getContext(), itemList);
        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);
        recyclerView.removeAllViews();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}
