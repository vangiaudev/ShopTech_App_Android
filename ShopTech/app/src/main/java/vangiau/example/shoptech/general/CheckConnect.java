package vangiau.example.shoptech.general;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.squareup.picasso.Picasso;

public class CheckConnect {
    public static boolean haveNetworkConnection(Context context) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
    public static void ToastMess(Context context, String notify){
        FancyToast.makeText(context,notify,FancyToast.LENGTH_LONG,FancyToast.DEFAULT,false).show();
    }
    public static void ToastMessError(Context context, String notify){
        FancyToast.makeText(context,notify,FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
    }
    public static void ToastMessSuccess(Context context, String notify){
        FancyToast.makeText(context,notify,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
    }
    public static void ToastMessWarning(Context context, String notify){
        FancyToast.makeText(context,notify,FancyToast.LENGTH_LONG,FancyToast.WARNING,false).show();
    }
    public static void ToastMessConfusing(Context context, String notify){
        FancyToast.makeText(context,notify,FancyToast.LENGTH_LONG,FancyToast.CONFUSING,false).show();
    }
}
