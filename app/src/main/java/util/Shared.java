package util;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class Shared {

    public static final String KEY_TABLE1 = "TABLE1";
    public static final String KEY_TABLE2 = "TABLE2";
    public static final String KEY_TABLE3 = "TABLE3";
    public static final String KEY_TABLE4 = "TABLE4";
    public static final String KEY_TABLE5 = "TABLE5";
    public static final String KEY_TABLE6 = "TABLE6";
    public static final String KEY_TABLE7 = "TABLE7";
    public static final String KEY_TABLE8 = "TABLE8";
    public static final String KEY_TABLE9 = "TABLE9";
    public static final String KEY_RESERVED_TABLES = "RESERVED_TABLES";
    public static final String KEY_NOT_RESERVED_TABLES = "NOT_RESERVED_TABLES";

    private SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public Shared(final Context activity){
        preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        editor = preferences.edit();
    }

    public final void put(final String key, final Object value) {
        if (value instanceof String){
            editor.putString(key,(String)value);
        }
        else if (value instanceof Boolean){
            editor.putBoolean(key, (Boolean)value);
        }
        else if (value instanceof Long){
            editor.putLong(key, (Long)value);
        }
        else if(value instanceof Integer){
            editor.putInt(key, (Integer)value);
        }
        else if (value instanceof Float){
            editor.putFloat(key, (Float)value);
        }
        else {
            throw new Error("Tipo de dados inválido.");
        }

        editor.apply();
    }

    public final String getString(final String key){
        return preferences.getString(key, null);
    }

    public final int getInt(final String key){
        return preferences.getInt(key, 0);
    }

    public final boolean getBoolean(final String key) {
        return preferences.getBoolean(key, false);
    }

    public final void remove (final String key){
        editor.remove(key);
        editor.apply();
    }
}