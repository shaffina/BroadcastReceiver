package com.example.broadcast

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.example.broadcast.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val channelId = "TEST NOTIF"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNotification.setOnClickListener {

            val flag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                PendingIntent.FLAG_IMMUTABLE
            } else {
                0
            }

            val intent = Intent(this, NotifReceiver::class.java)
                .putExtra("MESSAGE", "Opening App")

            val requestCode = System.currentTimeMillis().toInt() // uniq code untuk membedakan pending intent dari sebelumnya

            val pendingIntent = PendingIntent.getBroadcast(
                this,
                requestCode,
                intent,
                flag
            )

            val image = BitmapFactory.decodeResource(resources, R.drawable.riizegag)
            val builder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.notif)
                .setContentTitle("BRIIZE DAY")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .addAction(0, "Watch Now", pendingIntent)
                .setStyle(
                    NotificationCompat.BigPictureStyle()
                        .bigPicture(image)
                        .bigLargeIcon(null)
                        .setBigContentTitle("BRIIZE DAY")
                        .setSummaryText("RIIZE 라이즈 'Get A Guitar (English Ver.)' Lyric Video")
                )

            val notifManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val notifChannel = NotificationChannel(
                    channelId,
                    "Notification Test",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                with(notifManager) {
                    createNotificationChannel(notifChannel)
                    notify(0, builder.build())
                }
            } else {
                notifManager.notify(0, builder.build())
            }
        }

        binding.btnUpdate.setOnClickListener {

            val flag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                PendingIntent.FLAG_IMMUTABLE
            } else {
                0
            }

            val intent = Intent(this, NotifReceiver::class.java)
                .putExtra("MESSAGE", "Opening App")

            val requestCode = System.currentTimeMillis().toInt() // Use a unique request code

            val pendingIntent = PendingIntent.getBroadcast(
                this,
                requestCode,
                intent,
                flag
            )

            val image1 = BitmapFactory.decodeResource(resources, R.drawable.sealook1)
            val builder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.notif)
                .setContentTitle("BRIIZE DAY")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .addAction(0, "Watch Now", pendingIntent)
                .setStyle(
                    NotificationCompat.BigPictureStyle()
                        .bigPicture(image1)
                        .bigLargeIcon(null)
                        .setBigContentTitle("BRIIZE DAY")
                        .setSummaryText("라이즈 (RIIZE) - 'HAPPY! HAPPY! HAPPY!' (씰룩 OST) MV")
                )

            val notifManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val notifChannel = NotificationChannel(
                    channelId,
                    "Notification Test",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                with(notifManager) {
                    createNotificationChannel(notifChannel)
                    notify(0, builder.build())
                }
            } else {
                notifManager.notify(0, builder.build())
            }
        }
    }
}