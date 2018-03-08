package app.gitsquare.com.gitsquare.Util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import app.gitsquare.com.gitsquare.R;

/**
 * Created by admin on 08-03-2018.
 */

public class AppUtil {

    public static boolean isNetworkAvailable(Activity activity) {

        ConnectivityManager connectivityManager
                = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void showToast(Activity activity,String message)
    {
        View layout = activity.getLayoutInflater().inflate(R.layout.toast_layout,null);

        TextView txt= (TextView) layout.findViewById(R.id.textviewToastMessage);

        txt.setText(message);

        Toast toast = new Toast(activity);

        toast.setDuration(Toast.LENGTH_SHORT);

        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);

        toast.setView(layout);//setting the view of custom toast layout

        toast.show();
    }
}
