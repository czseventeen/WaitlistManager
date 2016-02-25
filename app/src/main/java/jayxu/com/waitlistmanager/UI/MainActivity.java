package jayxu.com.waitlistmanager.UI;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.Calendar;
import java.util.Date;

import jayxu.com.waitlistmanager.MODEL.UsefulConstants;
import jayxu.com.waitlistmanager.R;


public class MainActivity extends ActionBarActivity  {
    private static final int REQUEST_CODE_CAPTURE_IMAGE = 1;
    private static final int REQUEST_CODE_CREATOR = 2;
    private static final int REQUEST_CODE_RESOLUTION = 3;
    private static final String TAG = MainActivity.class.getSimpleName();
    private ParseUser CurrentParseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);
//        Calendar myCal= Calendar.getInstance();
//        Date Today12am=myCal.getTime();
//        Today12am.setHours(0);
//        Today12am.setMinutes(0);
//        Today12am.setSeconds(0);
//        //check to see if there is any existing
//        ParseQuery<ParseObject> waitlistQuery= ParseQuery.getQuery(UsefulConstants.Parse_String_Parties);
//        waitlistQuery.whereEqualTo(UsefulConstants.Parse_String_Restaurant, ParseUser.getCurrentUser());
//        waitlistQuery.whereGreaterThanOrEqualTo(UsefulConstants.Parse_String_updatedAt,Today12am );
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_activity, new WaitlistFragment())
                    .commit();
        ActionBar ab=getSupportActionBar();
        ab.setTitle(getString(R.string.todaysWaitlist));
        CurrentParseUser=ParseUser.getCurrentUser();

    }

    @Override
    protected void onResume(){
        super.onResume();

    }

    @Override
    protected void onPause() {

        super.onPause();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.action_add){
            AlertDialog add_dialog=onCreateDialog();
            add_dialog.show();
        }
        else if(id == R.id.action_logoff){
            ParseUser.logOut();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_CAPTURE_IMAGE:
                // Called after a photo has been taken.
                if (resultCode == Activity.RESULT_OK) {
                    // Store the image data as a bitmap for writing later.
                }
                break;
            case REQUEST_CODE_CREATOR:
                // Called after a file is saved to Drive.
                if (resultCode == RESULT_OK) {
//                    Log.i(TAG, "Image successfully saved.");
//                    mBitmapToSave = null;
//                    // Just start the camera again for another photo.
//                    startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE),
//                            REQUEST_CODE_CAPTURE_IMAGE);
                }
                break;
        }
    }
    public AlertDialog onCreateDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        final View dialogView=inflater.inflate(R.layout.dialog_signin, null);
        builder.setView(dialogView)
                // Add action buttons
                .setTitle(R.string.add_new_party_title)
                .setPositiveButton(R.string.Add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        TextView guestName=(TextView)dialogView.findViewById(R.id.new_guest_name);
                        TextView partySize=(TextView)dialogView.findViewById(R.id.new_party_number);
                        TextView guestPhone=(TextView)dialogView.findViewById(R.id.new_phone_number);
                        String guest_name=guestName.getText().toString();
                        String party_Size=partySize.getText().toString();
                        String guest_Phone=guestPhone.getText().toString();
                        ParseObject obj=new ParseObject(UsefulConstants.Parse_String_Parties);
                        obj.put(UsefulConstants.Parse_String_GuestName,guest_name);
                        obj.put(UsefulConstants.Parse_String_PartySize,party_Size);
                        if(guest_Phone!="") {
                            obj.put(UsefulConstants.Parse_String_GuestPhone, guest_Phone);
                        }
                        obj.put(UsefulConstants.Parse_String_Restaurant, ParseUser.getCurrentUser());
                        obj.saveInBackground();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        return builder.create();
    }


//    @Override
//    public void onConnectionFailed(ConnectionResult result) {
//        // Called whenever the API client fails to connect.
//        Log.i(TAG, "GoogleApiClient connection failed: " + result.toString());
//        if (!result.hasResolution()) {
//            // show the localized error dialog.
//            GoogleApiAvailability.getInstance().getErrorDialog(this, result.getErrorCode(), 0).show();
//            return;
//        }
//        // The failure has a resolution. Resolve it.
//        // Called typically when the app is not yet authorized, and an
//        // authorization
//        // dialog is displayed to the user.
//        try {
//            result.startResolutionForResult(this, REQUEST_CODE_RESOLUTION);
//        } catch (IntentSender.SendIntentException e) {
//            Log.e(TAG, "Exception while starting resolution activity", e);
//        }
//    }






//    @Override
//    public void onConnected(Bundle connectionHint) {
//        Log.i(TAG, "API client connected.");
////        if (mBitmapToSave == null) {
////            // This activity has no UI of its own. Just start the camera.
////            startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE),
////                    REQUEST_CODE_CAPTURE_IMAGE);
////            return;
////        }
////        saveFileToDrive();
//    }
//
//    @Override
//    public void onConnectionSuspended(int cause) {
//        Log.i(TAG, "GoogleApiClient connection suspended");
//    }
}
