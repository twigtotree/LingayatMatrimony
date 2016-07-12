package com.twigtotree.lingayatmatrimony.Utility;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sgoud1 on 7/10/16.
 */
public class SharedPreferenceUtil {

    public static final String PREFS = "prefs";

    public static final String PERSISTED_USERID = "userid";
    public static final String SIGN_UP_EXPERIENCE_PREF_KEY ="signupflow";

    public static void skipSignUpDetailsExperienceState(boolean skip,Context applicationContext) {
        if (null != applicationContext) {
            SharedPreferences sharedPreferences = applicationContext
                    .getSharedPreferences(PREFS,
                            Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(SIGN_UP_EXPERIENCE_PREF_KEY, skip);
            editor.commit();
        }
    }
    public static boolean isSkipSignUpDetailsExperienceState(Context applicationContext) {
        if (null != applicationContext) {
            SharedPreferences sharedPreferences = applicationContext
                    .getSharedPreferences(PREFS,
                            Context.MODE_PRIVATE);
            return sharedPreferences.getBoolean(SIGN_UP_EXPERIENCE_PREF_KEY,
                    false);
        }
        return false;
    }

}
