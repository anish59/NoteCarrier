package bbt.com.notecarrier

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import bbt.com.notecarrier.helper.Functions
import com.gun0912.tedpermission.PermissionListener
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.util.*


class MainActivity : AppCompatActivity() {
    val PICK_CAMERA_IMAGE = 1
    private var destination: File? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Functions().setPermission(
                this,
                arrayOf(
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),
                object : PermissionListener {
                    override fun onPermissionGranted() {
                        setContentView(R.layout.activity_main)

                        actionListeners()
                    }

                    override fun onPermissionDenied(deniedPermissions: ArrayList<String>) {
                        Toast.makeText(this@MainActivity, "Please give permissions", Toast.LENGTH_LONG).show()
                    }
                })


    }

    private fun actionListeners() {
        fabAddImage.setOnClickListener {
          /*  Toast.makeText(this, "Open Camera", Toast.LENGTH_LONG).show()
            val name = "img_" + DateFormatterHelper.parseDate(Calendar.getInstance(), DateFormatterHelper.fileSaveDateFormate)
            destination = File(
                    getExternalStorageDirectory(), name + ".jpg"
            )

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            *//*val packageAccessPath =this.getApplicationContext().getPackageName() + ".provider"
            val photoURI = FileProvider.getUriForFile(
                this, packageAccessPath,destination
            )
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)*//*
            startActivityForResult(intent, PICK_CAMERA_IMAGE)*/

            val pictureIntent = Intent(
                    MediaStore.ACTION_IMAGE_CAPTURE
            )
            if (pictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(pictureIntent, PICK_CAMERA_IMAGE)
            }

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            PICK_CAMERA_IMAGE -> {

                var uri: Uri? = data!!.data
                Log.e("Image path", uri.toString())

                if ( data.extras != null) {
                    val imageBitmap = data.extras.get("data") as Bitmap
                    imgTask.setImageBitmap(imageBitmap)
                }

            }
        }
    }

}
