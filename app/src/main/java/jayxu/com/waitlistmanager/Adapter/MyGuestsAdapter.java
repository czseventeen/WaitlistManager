package jayxu.com.waitlistmanager.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import jayxu.com.waitlistmanager.MODEL.Parties;
import jayxu.com.waitlistmanager.R;

/**
 * Created by Yuchen on 2/21/2016.
 */
public class MyGuestsAdapter extends RecyclerView.Adapter<MyGuestsAdapter.MyGuestsViewHolder>{
    private ArrayList<Parties> mParties;
    public MyGuestsAdapter(ArrayList<Parties> parties){
        mParties=parties;
    }
    //default constructor
    public MyGuestsAdapter(){
        mParties=new ArrayList<>();
    }

    @Override
    public MyGuestsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.guests_list, parent, false);
        MyGuestsViewHolder viewHolder=new MyGuestsViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyGuestsViewHolder holder, int position) {
        holder.bindData(mParties.get(position));
    }

    @Override
    public int getItemCount() {
        return mParties.size();
    }

    public class MyGuestsViewHolder extends RecyclerView.ViewHolder {
        public TextView mName;
        public TextView mNumberOfPpl;
        public TextView mTimeWaited;

        public MyGuestsViewHolder(View itemView) {
            super(itemView);
            mName=(TextView)itemView.findViewById(R.id.Name_textview);
            mNumberOfPpl=(TextView)itemView.findViewById(R.id.numOfppl_textview);
            mTimeWaited=(TextView)itemView.findViewById(R.id.waitedTime_textview);
        }

        public void bindData(Parties party){
            String name=party.getmName();
            String num_of_ppl=party.getmNumberofGuestsInThisParty();
            String time_waited=party.getmSecondsWaited();
            mName.setText(name);
            mNumberOfPpl.setText(num_of_ppl);
            mTimeWaited.setText(time_waited);
        }
    }


}
