package com.ryadamir.movieapp.ui.settings

import android.annotation.SuppressLint
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.*
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.ryadamir.movieapp.BuildConfig
import com.ryadamir.movieapp.R
import kotlinx.android.synthetic.main.fragment_settings.*
import java.io.File


class SettingsFragment : Fragment(R.layout.fragment_settings) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setVersion()
        setClearCacheAction()
    }

    private fun setClearCacheAction() {
        clear_cach.setOnClickListener {
            AlertDialog.Builder(context!!)
                .setTitle(resources.getString(R.string.confirmation))
                .setMessage(resources.getString(R.string.do_clear_cach))
                .setPositiveButton(
                    resources.getString(R.string.yes)
                ) { _, _ ->
                    try {
                        deleteCache()
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.ache_cleared),
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                .setNegativeButton(resources.getString(R.string.no), null)
                .show()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setVersion() {
        try {
            val pInfo = context!!.packageManager.getPackageInfoCompat(requireContext().packageName)
            val versionNum = pInfo.versionName
            if (BuildConfig.DEBUG) {
                version.text = ("version $versionNum")
            } else {
                version.text = ("version $versionNum")
            }
        } catch (e: PackageManager.NameNotFoundException) {
            version.visibility = (View.GONE)
            e.printStackTrace()
        }
    }

    private fun PackageManager.getPackageInfoCompat(
        packageName: String,
        flags: Int = 0
    ): PackageInfo =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(flags.toLong()))
        } else {
            @Suppress("DEPRECATION") getPackageInfo(packageName, flags)
        }

    private fun deleteCache() {
        try {
            val dir = requireContext().cacheDir
            dir.deleteDir()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    private fun File?.deleteDir(): Boolean {
        return if (this != null && isDirectory) {
            val children = list()
            if (children != null) {
                for (i in children.indices) {
                    val success = File(this, children[i]).deleteDir()
                    if (!success) {
                        return false
                    }
                }
            }
            delete()
        } else if (this != null && isFile) {
            delete()
        } else {
            false
        }
    }
}