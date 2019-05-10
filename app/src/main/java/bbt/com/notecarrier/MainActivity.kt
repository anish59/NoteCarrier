package bbt.com.notecarrier

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.os.Environment.getExternalStorageDirectory
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import bbt.com.notecarrier.helper.Functions
import com.gun0912.tedpermission.PermissionListener
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.util.ArrayList


class MainActivity : AppCompatActivity() {
    val PICK_CAMERA_IMAGE = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Functions().setPermission(this, arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE), object : PermissionListener {
            override fun onPermissionGranted() {

            }

            override fun onPermissionDenied(deniedPermissions: ArrayList<String>) {
//                showToast(getActivity(), "Permission Denied")
            }
        })


        setContentView(R.layout.activity_main)

        actionListeners()

    }

    private fun actionListeners() {
        imgCapture.setOnClickListener {
            Toast.makeText(this, "Open Camera", Toast.LENGTH_LONG).show()
        }


        val name = "img"
        var destination = File(
            getExternalStorageDirectory(), name + ".jpg"
        )

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        /*val packageAccessPath =this.getApplicationContext().getPackageName() + ".provider"
        val photoURI = FileProvider.getUriForFile(
            this, packageAccessPath,destination
        )
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)*/
        startActivityForResult(intent, PICK_CAMERA_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            PICK_CAMERA_IMAGE ->
                Toast.makeText(this, "hey", Toast.LENGTH_LONG).show()
        }
    }

}
