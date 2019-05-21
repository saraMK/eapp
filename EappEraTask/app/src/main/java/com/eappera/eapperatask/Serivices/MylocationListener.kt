package com.eappera.eapperatask.Serivices

import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.util.Log
import android.widget.Toast


class MylocationListener(context:Context) : LocationListener {
    var context:Context?=context
    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
        Log.d("onLocationChanged",p0)    }

    override fun onProviderDisabled(provider: String) {
      //  Toast.makeText(getApplicationContext(), "Gps Disabled", Toast.LENGTH_SHORT).show()
        Log.d("onLocationChanged",provider)
    }


    override fun onProviderEnabled(provider: String) {
       // Toast.makeText(getApplicationContext(), "Gps Enabled", Toast.LENGTH_SHORT).show()
        Log.d("onLocationChanged",provider)

    }

    override fun onLocationChanged(location: Location?) {
        Log.d("onLocationChanged",location.toString() )

        if (context!= null)
        Toast.makeText(context, location.toString(), Toast.LENGTH_SHORT).show()
     }

    fun stop (context_: Context?){
        context=context_
    }


}