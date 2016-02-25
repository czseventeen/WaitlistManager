package jayxu.com.waitlistmanager.UI;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import jayxu.com.waitlistmanager.Adapter.MyGuestsAdapter;
import jayxu.com.waitlistmanager.MODEL.Parties;
import jayxu.com.waitlistmanager.MODEL.UsefulConstants;
import jayxu.com.waitlistmanager.R;

/**
 * Created by Yuchen on 2/21/2016.
 */
public class WaitlistFragment extends Fragment {
    private List<ParseObject> query_results;
    private View rootView;
    private Calendar myCal;
    TimeZone myTimeZone;

    public WaitlistFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myCal=Calendar.getInstance();
        myTimeZone=myCal.getTimeZone();
        final Date currentDate=myCal.getTime();
        Date Today12am=myCal.getTime();
        Today12am.setHours(0);
        Today12am.setMinutes(0);
        Today12am.setSeconds(0);

        rootView = inflater.inflate(R.layout.fragment_waitlist, container, false);
        ParseQuery<ParseObject> waitlistQuery= ParseQuery.getQuery(UsefulConstants.Parse_String_Parties);
        waitlistQuery.whereEqualTo(UsefulConstants.Parse_String_Restaurant, ParseUser.getCurrentUser());
        waitlistQuery.whereGreaterThanOrEqualTo(UsefulConstants.Parse_String_createdAt,Today12am );
        waitlistQuery.orderByAscending(UsefulConstants.Parse_String_createdAt);
        waitlistQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
             if(e==null){
                 query_results=objects;
                 Log.d("Got em!!!!", objects.toString());
                 ArrayList<Parties> today_party_list=new ArrayList<>();
                 for(ParseObject temp : query_results){
                     Parties party_temp=new Parties();
                     party_temp.setmName(temp.getString(UsefulConstants.Parse_String_GuestName));
                     party_temp.setmPhoneNumber(temp.getString(UsefulConstants.Parse_String_GuestPhone));
                     party_temp.setmEmail(temp.getString(UsefulConstants.Parse_String_GuestEmail));
                     party_temp.setmNumberofGuestsInThisParty(temp.getString(UsefulConstants.Parse_String_PartySize));


                     //use the date the entry was created minus the current date to get how long the guest has been waiting for.
                     Date CreatedDate=temp.getCreatedAt();
                     long waitedTime=currentDate.getTime()-CreatedDate.getTime();
                     //convert milliseconds to minutes
                     waitedTime=waitedTime/60000;
                     party_temp.setmSecondsWaited(waitedTime+" min");

//                     Log.d("Addding!!!!!!!!!!", party_temp.toString());
                     today_party_list.add(party_temp);



                 }
                 MyGuestsAdapter adapter=new MyGuestsAdapter(today_party_list);
                 RecyclerView recyclerView=(RecyclerView)rootView.findViewById(R.id.parties_recyclerView);
                 recyclerView.setAdapter(adapter);
                 recyclerView.setLayoutManager(new LinearLayoutManager(WaitlistFragment.this.getContext()));
                 recyclerView.setHasFixedSize(true);

             }else{
                 Log.d("ERROOOOOR!", e.getMessage());
             }
            }
        });
        // loop through the Query response and grab all the parties asscioated with this restaurant.



//        Parties temp1=new Parties("Jay", "3", "8");
//        Parties temp2=new Parties("Summer", "2", "9");
//        Parties temp3=new Parties("Kage", "5", "3");
//        ArrayList<Parties> list =new ArrayList<>();
//        list.add(temp1);
//        list.add(temp2);
//        list.add(temp3);


        return rootView;
    }
}
