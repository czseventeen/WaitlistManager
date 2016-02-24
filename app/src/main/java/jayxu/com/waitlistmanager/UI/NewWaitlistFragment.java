package jayxu.com.waitlistmanager.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import jayxu.com.waitlistmanager.R;

/**
 * Created by Yuchen on 2/21/2016.
 */
public class NewWaitlistFragment extends Fragment {

    public NewWaitlistFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_newlist, container, false);
        TextView Newlist_textview=(TextView)rootView.findViewById(R.id.Newlist_textbox);
        Newlist_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Switch to the Waitlist Fragment
                getFragmentManager().beginTransaction()
                        .replace(R.id.main_activity, new WaitlistFragment())
                        .commit();
            }
        });
        return rootView;
    }
}
