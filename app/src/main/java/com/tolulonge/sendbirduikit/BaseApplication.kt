package com.tolulonge.sendbirduikit

import android.app.Application
import com.sendbird.android.SendBirdException
import com.sendbird.android.handlers.InitResultHandler
import com.sendbird.uikit.SendBirdUIKit
import com.sendbird.uikit.adapter.SendBirdUIKitAdapter
import com.sendbird.uikit.interfaces.UserInfo

class BaseApplication : Application() {
    private var userIdd: String? = null
    private var userNickname: String? = null
   companion object{
      const val VERSION = "3.0.40"
   }
    override fun onCreate() {
        super.onCreate()
        SendBirdUIKit.init(object : SendBirdUIKitAdapter {
            override fun getAppId(): String {
                return "15928B2D-1B7E-4AD0-93ED-C9235953DBEE"
            }

            override fun getAccessToken(): String {
                return "null"
            }

            override fun getUserInfo(): UserInfo {
                return (object : UserInfo{
                    override fun getUserId(): String? {
                        return userIdd
                    }

                    override fun getNickname(): String? {
                        return userNickname
                    }

                    override fun getProfileUrl(): String {
                       return ""
                    }

                })
            }

            override fun getInitResultHandler(): InitResultHandler {
                return object : InitResultHandler{
                    override fun onInitFailed(e: SendBirdException) {

                    }

                    override fun onInitSucceed() {

                    }

                    override fun onMigrationStarted() {

                    }

                }
            }

        }, this)


    }

    fun setUserId(userId: String){
        this.userIdd = userId
    }


    fun setUserNickname(userNickname : String) {
        this.userNickname = userNickname;
    }
}