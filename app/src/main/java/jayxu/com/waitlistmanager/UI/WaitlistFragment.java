package jayxu.com.waitlistmanager.UI;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

import jayxu.com.waitlistmanager.Adapter.MyGuestsAdapter;
import jayxu.com.waitlistmanager.MODEL.Parties;
import jayxu.com.waitlistmanager.R;

/**
 * Created by Yuchen on 2/21/2016.
 */
public class WaitlistFragment extends Fragment {
    public WaitlistFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_waitlist, container, false);

        Parties temp1=new Parties("Jay", "3", "8");
        Parties temp2=new Parties("Summer", "2", "9");
        Parties temp3=new Parties("Kage", "5", "3");
        ArrayList<Parties> list =new ArrayList<>();
        list.add(temp1);
        list.add(temp2);
        list.add(temp3);

        MyGuestsAdapter adapter=new MyGuestsAdapter(list);
        RecyclerView recyclerView=(RecyclerView)rootView.findViewById(R.id.parties_recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(true);

        return rootView;
    }
}
