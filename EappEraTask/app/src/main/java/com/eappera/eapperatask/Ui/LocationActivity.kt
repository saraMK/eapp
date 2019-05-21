package com.eappera.eapperatask.Ui

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.eappera.eapperatask.R
import com.eappera.eapperatask.Serivices.LocationService
import kotlinx.android.synthetic.main.activity_location.*


class LocationActivity : AppCompatActivity(), View.OnClickListener {



    lateinit var intentService:Intent
    override fun onClick(view: View?) {
        if (view ==start_btn) {
            if (check()) {
                 startService(intentService)
             }

        }
        else {
            stopService(intentService)
            Toast.makeText(this,"Stop",Toast.LENGTH_SHORT).show()

         }
     }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        intentService=Intent(this, LocationService::class.java)
         check()
        start_btn.setOnClickListener(this)
        stop_btn.setOnClickListener(this)
    }


    private fun check():Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
        if (checkLocationPermission()) {
                return true
             }
        } else {
            showGPSDisabledAlertToUser()
        }

        return false
    }

    private fun showGPSDisabledAlertToUser() {

        var alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("GPS is disabled in your device. Would you like to enable it?")
            .setCancelable(false)
            .setPositiveButton("OK",
                DialogInterface.OnClickListener() { dialogInterface: DialogInterface, i: Int ->
                    var callGPSSettingIntent = Intent(
                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS
                    );
                    startActivityForResult(callGPSSettingIntent,1);

                });


        var alert: AlertDialog = alertDialogBuilder.create();
        alert.show();
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        check()

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
         check()
    }


    fun checkLocationPermission(): Boolean {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    1
                )


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    1
                )
            }


            return false
        } else {

            return true
        }
    }


}
