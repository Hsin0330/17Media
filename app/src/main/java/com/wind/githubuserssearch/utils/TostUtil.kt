package com.wind.githubuserssearch.utils

import android.content.Context
import android.os.Handler
import android.widget.Toast
import android.os.Looper


object ToastUtil {

    fun show(context: Context, resId: Int) {
        show(context, context.getString(resId))
    }

    fun show(context: Context, resId: Int, vararg formatArgs: Any) {
        show(context, context.getString(resId, formatArgs))
    }

    fun show(context: Context, charsequence: CharSequence) {
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(context, charsequence, Toast.LENGTH_SHORT).show()
        }
    }
}