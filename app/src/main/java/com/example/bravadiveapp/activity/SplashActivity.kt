package com.example.bravadiveapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bravadiveapp.R

import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        iVBottom.setImageResource(R.mipmap.fondo)
        iVLogo.setImageResource(R.mipmap.logopng)

        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            startActivity(MapsActivity.getIntent(this@SplashActivity))
            finish()
        }
    }

}








