package com.example.get_together;

import java.util.ArrayList;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.Window;
import android.widget.Toast;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;

public class MainActivity extends FragmentActivity 
				implements GooglePlayServicesClient.ConnectionCallbacks,
				GooglePlayServicesClient.OnConnectionFailedListener{
	private final static int  CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
	LocationClient mLocationClient;
	Location mCurrentLocation;
	LocationRequest mLocationRequest;
	ArrayList<Location> list = new ArrayList<Location>();
	private static String msg;
	private static final int MILLISECONDS_PER_SECOND = 1000; // Milliseconds per second
	public static final int UPDATE_INTERVAL_IN_SECONDS = 5;// Update frequency in seconds
	private static final long UPDATE_INTERVAL =  MILLISECONDS_PER_SECOND * UPDATE_INTERVAL_IN_SECONDS;  // Update frequency in milliseconds
	private static final int FASTEST_INTERVAL_IN_SECONDS = 1;  // The fastest update frequency, in seconds
	// A fast frequency ceiling in milliseconds
	private static final long FASTEST_INTERVAL = MILLISECONDS_PER_SECOND * FASTEST_INTERVAL_IN_SECONDS;
	//private EditText text; 
	
	private GoogleMap mMap;
	private Marker my_location;
	
	//this flag is very important. when you move your map camera to find place A, 
	//the camera location updated function in the
	//onLocationChanged shouldn't be called.
	//when you click the myLocation button, camera location updated function should 
	//be able to work again.
	private boolean camera_changed;
	
	public static class ErrorDialogFragment extends DialogFragment {
		private Dialog mDialog;
		public ErrorDialogFragment() {
			super();
			mDialog = null;                                                 // Default constructor. Sets the dialog field to null
		}
		public void setDialog(Dialog dialog) {
			mDialog = dialog;                                        // Set the dialog to display
		}
		
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
        return mDialog;                                                              // Return a Dialog to the DialogFragment.
    }
}
private final class MyLocationListener implements LocationListener {
     @Override
	     public void onLocationChanged(Location location) {
	         // Report to the UI that the location was updated
	         mCurrentLocation =location;
	         Context context = getApplicationContext();
	
	         msg = Double.toString(location.getLatitude()) + "," +  Double.toString(location.getLongitude());
	         list.add(mCurrentLocation);
	
	         Toast.makeText(context, msg,Toast.LENGTH_LONG).show();
	        moveToCurrentPosition(location.getLatitude(),location.getLongitude());
	        //I need to seed the position to server in this function.
	        //And get other people's position from server in this function.
	        //remember that you should use multi-thread tech.
	        //For example: Runnable interface
	      }
     }
	private void moveToCurrentPosition(double latitude, double longtitude)
	{
		LatLng current_location= new LatLng(latitude, longtitude);
		my_location.setPosition(current_location);
		if (camera_changed == false)
		{
			mMap.animateCamera(CameraUpdateFactory.newLatLng(current_location));
		}
	}

@Override
protected void onActivityResult(
        int requestCode, int resultCode, Intent data) {
    // Decide what to do based on the original request code
    switch (requestCode) {
        case CONNECTION_FAILURE_RESOLUTION_REQUEST :
        /*
         * If the result code is Activity.RESULT_OK, try
         * to connect again
         */
            switch (resultCode) {
                case Activity.RESULT_OK :
                /*
                 * Try the request again
                 */
                break; 
            }
    }
}

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
     setContentView(R.layout.activity_main);
    mLocationClient = new LocationClient(this, this, this);   /* Create a new location client, using the enclosing class to handle callbacks     */
    mLocationClient.connect();
    mLocationRequest = LocationRequest.create();
    mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);    // Use high accuracy
    mLocationRequest.setInterval(UPDATE_INTERVAL);  // Setting the update interval to 
    mLocationRequest.setFastestInterval(FASTEST_INTERVAL);  // Set the fastest update interval to
    mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
    mMap.setMyLocationEnabled(true);
    camera_changed = false;
    
    mMap.setOnCameraChangeListener(new OnCameraChangeListener() {
        public void onCameraChange(CameraPosition arg0) {
        	camera_changed = false;
         }
   });
    mMap.setOnMyLocationButtonClickListener(new OnMyLocationButtonClickListener(){
    	public boolean onMyLocationButtonClick(){
    		return camera_changed = false;
    		//it should be false, but there's something bad happened.
    	}
    });

}

@Override
protected void onDestroy() {
    mLocationClient.disconnect();  
}

/* Called by Location Services when the request to connect the client finishes successfully. At this point, you can request the current location or start periodic updates */
@Override
public void onConnected(Bundle dataBundle) {
    if (mMap != null) {
    	mCurrentLocation = mLocationClient.getLastLocation();
    	LatLng latlng = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
    	my_location = mMap.addMarker(new MarkerOptions()
        .position(latlng)
        .title("My Location")
        .snippet("゛ニャンニャンです゛")
        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
    	//HUE_BLUE, HUE_YLLOW, HUE_ROSE, HUE_RED, HUE_GREEN
        //.icon(BitmapDescriptorFactory.fromResource(android.R.drawable.presence_online)));
    	mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 16));
    }
	
    LocationListener locationListener = new MyLocationListener();
    mLocationClient.requestLocationUpdates(mLocationRequest,locationListener);

}

/* Called by Location Services if the connection to the location client drops because of an error.*/
@Override
public void onDisconnected() {
    Toast.makeText(this, "Disconnected. Please re-connect.",
            Toast.LENGTH_SHORT).show();

}

//Called by Location Services if the at tempt to Location Services fails. 
@Override
public void onConnectionFailed(ConnectionResult connectionResult) {
    /*
     * Google Play services can resolve some errors it detects.If the error has a resolution, try sending an Intent to start a Google Play services activity that can resolve
     * error. */
    if (connectionResult.hasResolution()) {
        try {
            // Start an Activity that tries to resolve the error
            connectionResult.startResolutionForResult(this,CONNECTION_FAILURE_RESOLUTION_REQUEST);


            /*
             * Thrown if Google Play services canceled the original
             * PendingIntent
             */
        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();

        }
    } else {
        /*  * If no resolution is available, display a dialog to the user with the error. */
        showErrorDialog(connectionResult.getErrorCode());
    }
}
private boolean showErrorDialog(int errorCode) {
    int resultCode =
            GooglePlayServicesUtil.
                    isGooglePlayServicesAvailable(this);
    // If Google Play services is available
    if (ConnectionResult.SUCCESS == resultCode) {
        // In debug mode, log the status

        // Continue
        return true;
    // Google Play services was not available for some reason
    } else {
            Dialog errorDialog = GooglePlayServicesUtil.getErrorDialog(errorCode,this,
                    CONNECTION_FAILURE_RESOLUTION_REQUEST);
            // If Google Play services can provide an error dialog
            if (errorDialog != null) {
                        // Create a new DialogFragment for the error dialog
                        ErrorDialogFragment errorFragment =  new ErrorDialogFragment();
                        // Set the dialog in the DialogFragment
                        errorFragment.setDialog(errorDialog);
                        // Show the error dialog in the DialogFragment
                        errorFragment.show(getSupportFragmentManager(),"Location Updates");

                        } return false;
            }
        }

}