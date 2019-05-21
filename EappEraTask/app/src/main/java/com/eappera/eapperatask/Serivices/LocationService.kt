package com.eappera.eapperatask.Serivices

import android.Manifest
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.location.LocationManager
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.content.Context
import android.util.Log


class LocationService: Service() {
    val BROADCAST_ACTION = "Start"
     var locationManager: LocationManager? = null
    lateinit var listener: MylocationListener
    var intent: Intent? = null

     override fun onCreate() {
        super.onCreate()
        intent = Intent(BROADCAST_ACTION)
    }

    override fun onStart(intent: Intent, startId: Int) {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        listener = MylocationListener(applicationContext)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.d("onLocationChanged","onLocationChanged");
            return
        }
        locationManager!!.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 4000, 0f, listener)
        locationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER, 4000, 0f, listener)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onDestroy() {
         super.onDestroy()
        Log.v("STOP_SERVICE", "DONE")
         locationManager!!.removeUpdates(listener)
         listener.stop(null)
        stopSelf()
    }
}