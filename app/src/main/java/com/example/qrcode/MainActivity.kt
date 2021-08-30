package com.example.qrcode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private lateinit var qrcodeLogo   : ImageView
    private lateinit var intro        : TextView
    private lateinit var generateQR   : Button
    private lateinit var scanQR       : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        qrcodeLogo  = findViewById(R.id.imgQrcodeLogo)
        intro       = findViewById(R.id.txtIntro)
        generateQR  = findViewById(R.id.btGenerateQR)
        scanQR      = findViewById(R.id.btScanQR)

        generateQR.setOnClickListener{
           val intent = Intent(this@MainActivity,GenerateQRCode::class.java)
            startActivity(intent)
        }
        scanQR.setOnClickListener{
            val intent = Intent(this@MainActivity,ScanQRCode::class.java)
            startActivity(intent)
        }
    }
}