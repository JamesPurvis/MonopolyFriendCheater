package me.jamespurvis.monopolyfriendcheater



import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_layout)

        val button1 = findViewById<Button>(R.id.button2)
        val button2 = findViewById<Button>(R.id.button3)
        val button3 = findViewById<Button>(R.id.button4)

        button1.setOnClickListener {
            openAppInfoForGooglePlayServices();
        };


        button2.setOnClickListener {
            openAppInfoForMonopoly();
        };

        button3.setOnClickListener {
            openLink();
        }


    }

    private fun openAppInfoForMonopoly()
    {
        try {

            val packageName = "com.scopely.monopolygo"
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            intent.data = Uri.parse("package:$packageName")


            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                //Handle mogo not installed
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun openAppInfoForGooglePlayServices() {
        try {
            val packageName = "com.google.android.gms" // Package name for Google Play Services

            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            intent.data = Uri.parse("package:$packageName")


            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                //handle googleplay not installed
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun openLink() {
        val url = "https://s.scope.ly/ugP7FEEgUE4" //game link
        val packageName = "org.mozilla.firefox"

        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)


        val manager = packageManager
        val isFirefoxInstalled = try {
            manager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }

        if (isFirefoxInstalled) {
            intent.setPackage(packageName)
        } else {
            //use default browser
            intent.setPackage(null)
        }

        startActivity(intent)
    }

}