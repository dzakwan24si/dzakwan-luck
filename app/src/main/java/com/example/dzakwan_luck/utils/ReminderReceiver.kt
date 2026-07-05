package com.example.dzakwan_luck.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.dzakwan_luck.Home.HomeFragment
import com.example.dzakwan_luck.MainActivity

class ReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val title = intent.getStringExtra("title") ?: "Pengingat Bina Desa"
        val message = intent.getStringExtra("message") ?: "Waktunya mengecek tugas Anda!"
        val targetClassName = intent.getStringExtra("target_activity")

        val targetIntent = if (!targetClassName.isNullOrEmpty()) {
            val clazz = Class.forName(targetClassName)
            Intent(context, clazz).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        } else {
            Intent(context, HomeFragment::class.java) // Activity default jika terjadi error
        }

        NotificationHelper.showNotification(
            context = context,
            title = title,
            message = message,
            intent = targetIntent
        )
    }
}