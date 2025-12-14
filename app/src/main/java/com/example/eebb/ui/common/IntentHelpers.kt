package com.example.eebb.ui.common

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment

fun Fragment.openLink(url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    startActivity(intent)
}

fun Fragment.composeEmail(address: String, subject: String) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, arrayOf(address))
        putExtra(Intent.EXTRA_SUBJECT, subject)
    }
    startActivity(intent)
}

fun Fragment.openLocation(query: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$query"))
    startActivity(intent)
}
