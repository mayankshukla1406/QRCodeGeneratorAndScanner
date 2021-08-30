package com.example.qrcode

import android.graphics.Point
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.*
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.loader.content.Loader
import com.google.zxing.WriterException
import org.w3c.dom.Text

class GenerateQRCode : AppCompatActivity() {
    private lateinit var qrText  : TextView
    private lateinit var imgQRCode : ImageView
    private lateinit var generateCode : EditText
    private lateinit var generateQRCode : Button
    private lateinit var qrEncoder : QRGEncoder
    var width : Int = 0
    var height : Int = 0
    var dimen : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_qrcode)
        qrText  = findViewById(R.id.txtCode)
        imgQRCode = findViewById(R.id.imgQrcode)
        generateCode = findViewById(R.id.etGenerateCode)
        generateQRCode = findViewById(R.id.btGenerateCode)

        generateQRCode.setOnClickListener{
            val data = generateCode.text.toString()
            if(data.isEmpty())
            {
                Toast.makeText(this@GenerateQRCode,"Enter some text",Toast.LENGTH_LONG).show()
            }
            else
            {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.R)
                {
                    val windowMetrics = windowManager.currentWindowMetrics
                    val insets = windowMetrics.windowInsets
                        .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
                    width = windowMetrics.bounds.width()
                    height = windowMetrics.bounds.height()
                    dimen = if(width<height) width else height
                    dimen = dimen * 3 /4
                }
                else
                {
                    val manager = getSystemService(WINDOW_SERVICE) as WindowManager
                    val display = manager.defaultDisplay
                    val point = Point()
                    display.getSize(point)
                    width = point.x
                    height = point.y
                    dimen = if (width<height) width else height
                    dimen = dimen * 3 /4
                }
                qrEncoder = QRGEncoder(data,null,QRGContents.Type.TEXT,dimen)
                try {
                    val bitmap = qrEncoder.encodeAsBitmap()
                    qrText.visibility = View.GONE
                    imgQRCode.setImageBitmap(bitmap)
                }
                catch (e:WriterException)
                {
                    e.printStackTrace()
                }
            }
        }
    }
}