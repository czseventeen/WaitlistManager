package jayxu.com.waitlistmanager.MODEL;

/**
 * Created by Yuchen on 2/21/2016.
 */
public class Parties {
    private String mName;
    private String mNumberofGuestsInThisParty;
    private String mSecondsWaited;
    private String mPhoneNumber;
    private String mEmail;

    public Parties() {
        this.mName = "";
        this.mNumberofGuestsInThisParty = "";
        this.mSecondsWaited = "";
        this.mPhoneNumber = "";
        this.mEmail = "";
    }

    public Parties(String mName, String mNumberofGuestsInThisParty, String mSecondsWaited) {
        this.mName = mName;
        this.mNumberofGuestsInThisParty = mNumberofGuestsInThisParty;
        this.mSecondsWaited = mSecondsWaited;
    }

    public Parties(String mName, String mNumberofGuestsInThisParty, String mSecondsWaited, String mPhoneNumber, String mEmail) {
        this.mName = mName;
        this.mNumberofGuestsInThisParty = mNumberofGuestsInThisParty;
        this.mSecondsWaited = mSecondsWaited;
        this.mPhoneNumber = mPhoneNumber;
        this.mEmail = mEmail;
    }


    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmNumberofGuestsInThisParty() {
        return mNumberofGuestsInThisParty;
    }

    public void setmNumberofGuestsInThisParty(String mNumberofGuestsInThisParty) {
        this.mNumberofGuestsInThisParty = mNumberofGuestsInThisParty;
    }

    public String getmSecondsWaited() {
        return mSecondsWaited;
    }

    public void setmSecondsWaited(String mSecondsWaited) {
        this.mSecondsWaited = mSecondsWaited;
    }

    public String getmPhoneNumber() {
        return mPhoneNumber;
    }

    public void setmPhoneNumber(String mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }
}
