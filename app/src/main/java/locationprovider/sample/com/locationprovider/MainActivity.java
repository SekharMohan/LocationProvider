package locationprovider.sample.com.locationprovider;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

import android.os.Bundle;
import android.widget.Toast;

import java.security.Permission;

import locationprovider.sample.com.locationprovider.service.LocationService;
import locationprovider.sample.com.locationprovider.utils.PermissionUtils;

public class MainActivity extends Activity {

    private static int LOCATION_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();


    }

    private void checkPermission() {
if(PermissionUtils.hasPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
    startService();
}else {
    requestPermission();
}



    }

    private void requestPermission() {

        PermissionUtils.requestPermissions(this,new String[]{Manifest.permission.ACCESS_CHECKIN_PROPERTIES},LOCATION_REQUEST_CODE);
    }

    private void startService() {
        Intent serviceIntent = new Intent(this,LocationService.class);
        startService(serviceIntent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startService();

        }else {
            Toast.makeText(this,"Permission is not granded. We can not proceed!",Toast.LENGTH_LONG);
        }
    }
}
