package com.wind.githubuserssearch.widget

import android.os.Looper
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Handler
import com.wind.githubuserssearch.MyApplication
import com.wind.githubuserssearch.R
import com.wind.githubuserssearch.utils.ToastUtil


object WaitingView {

    private val sController = ViewController()

    fun show(context: Context) {
        if (sController.isHeld) return

        (context as Activity).runOnUiThread {
            dismiss()
            sController.acquire(context, 30000)
        }
    }

    fun show(context: Context, fallback: Runnable) {
        if (sController.isHeld) return

        (context as Activity).runOnUiThread {
            dismiss()
            sController.acquire(context, 30000, fallback)
        }
    }

    fun show(context: Context, timeout: Long) {
        if (sController.isHeld) return

        (context as Activity).runOnUiThread {
            dismiss()
            sController.acquire(context, timeout)
        }
    }

    fun showWithoutTimeout(context: Context) {
        if (sController.isHeld) return

        (context as Activity).runOnUiThread {
            dismiss()
            sController.acquire(context, 0)
        }
    }

    fun dismiss() {
        if (sController.isHeld) sController.release()
    }

    fun dismissWithError() {
        ToastUtil.show(MyApplication.getContext(), R.string.unknown_error)
        sController.release()
    }

    private class ViewController {
        var dialog: Dialog? = null
        var handler = Handler(Looper.getMainLooper())
        var fallback: Runnable? = null

        val timeoutTask: Runnable = Runnable {
            dismissWithError()
            if (fallback != null) fallback!!.run()
        }

        val isHeld: Boolean
            get() = dialog != null && dialog!!.isShowing

        @JvmOverloads
        fun acquire(context: Context, delayMillis: Long, fallback: Runnable? = null) {
            this.fallback = fallback
            dialog = Dialog(context, R.style.WaitingDialog)
            dialog!!.setCancelable(false)
            dialog!!.setContentView(R.layout.view_waiting)
            dialog!!.show()

            if (delayMillis > 0) {
                handler.removeCallbacks(timeoutTask)
                handler.postDelayed(timeoutTask, delayMillis)
            }
        }

        fun release() {
            if (dialog != null) dialog!!.dismiss()
            handler.removeCallbacks(timeoutTask)
        }
    }

}