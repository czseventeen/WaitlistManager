package jayxu.com.waitlistmanager.UI;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

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
//        testObject.saveInBackground();

    }

}
