package locationprovider.sample.com.locationprovider.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public final class PermissionUtils {

    private PermissionUtils() {
    }

    private static boolean hasMashMallow() {
        return VERSION.SDK_INT >= VERSION_CODES.M;
    }


    public static void requestPermissions(Activity activity, String[] permissions, int requestCode) {
        if (PermissionUtils.hasMashMallow() && PermissionUtils.checkManifestPermission(activity, permissions)) {
            ActivityCompat.requestPermissions(activity, permissions, requestCode);
        }
    }


    public static boolean shouldShownPermissionRationale(Activity activity, String permission) {
       return  ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
    }


    public static boolean hasPermission(Activity mActivity, String permission) {
        return ActivityCompat.checkSelfPermission(mActivity, permission) == PackageManager.PERMISSION_GRANTED;
    }


    private static boolean checkManifestPermission(Context context, String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                return true;
            }
        }
        return false;
    }
}
