package bbt.com.notecarrier.helper

import android.content.Context
import com.gun0912.tedpermission.TedPermission
import com.gun0912.tedpermission.PermissionListener
import android.support.annotation.NonNull


class Functions {


    public fun setPermission(context: Context, permissions: Array<String>, permissionListener: PermissionListener?) { // ? => non null

        if (permissions.size == 0 && permissionListener != null) {
            return
        }
        TedPermission(context)
                .setPermissionListener(permissionListener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(*permissions)
                .check()
    }

//    fun(val context: Context, permissions: String[])
}