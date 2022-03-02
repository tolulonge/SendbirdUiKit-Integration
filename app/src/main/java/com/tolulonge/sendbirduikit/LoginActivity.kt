package com.tolulonge.sendbirduikit

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.sendbird.android.SendBird
import com.sendbird.android.SendBirdException
import com.sendbird.android.User
import com.sendbird.uikit.SendBirdUIKit
import com.sendbird.uikit.widgets.WaitingDialog
import com.tolulonge.sendbirduikit.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.buttonLoginConnect.setOnClickListener {
            var userId: String = binding.edittextLoginUserId.text.toString()
            userId = userId.replace("\\s".toRegex(), "")
            val userNickname: String = binding.edittextLoginUserNickname.text.toString()
            connectToSendBird(userId, userNickname)
        }
        binding.edittextLoginUserId.setSelectAllOnFocus(true)
        binding.edittextLoginUserNickname.setSelectAllOnFocus(true)

        // Display current SendBird and app versions in a TextView
        val sdkVersion: String = java.lang.String.format(
            getResources().getString(R.string.all_app_version),
            BaseApplication.VERSION, SendBird.getSDKVersion()
        )
        binding.textLoginVersions.text = sdkVersion
    }

    /**
     * Attempts to connect a user to SendBird.
     * @param userId    The unique ID of the user.
     * @param userNickname  The user's nickname, which will be displayed in chats.
     */
    private fun connectToSendBird(userId: String, userNickname: String) {
        if (TextUtils.isEmpty(userId) || TextUtils.isEmpty(userNickname)) {
            return
        }
        (application as BaseApplication).setUserId(userId)
        (application as BaseApplication).setUserNickname(userNickname)
        // Show the loading indicator
        WaitingDialog.show(this);
        SendBirdUIKit.connect { user: User?, e: SendBirdException? ->
            if (e != null) {
                WaitingDialog.dismiss()
                return@connect
            }
            WaitingDialog.dismiss()
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}