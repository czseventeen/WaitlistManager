package jayxu.com.waitlistmanager.UI;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

/**
 * Created by Yuchen on 2/22/2016.
 */
public class WaitlistApplication extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this);
//        ParseObject testObject = new ParseObject("TestObject");
//        testObject.put("foo", "bar");
//        testObject.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                Log.d("ParseError was", e.getMessage());
//            }
//        });

    }

}
