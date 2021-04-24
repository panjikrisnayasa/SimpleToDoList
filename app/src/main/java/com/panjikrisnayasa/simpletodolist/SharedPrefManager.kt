package com.panjikrisnayasa.simpletodolist

import android.content.Context
import android.content.SharedPreferences

class SharedPrefManager(private val mContext: Context?) {

    private val sharedPrefName = "simpleToDoListSharedPref"
    private val keyIsNameInputted = "isInputtedName"
    private val keyName = "name"

    private val mSharedPref: SharedPreferences? =
        mContext?.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE)

    companion object {
        private var mInstance: SharedPrefManager? = null

        @Synchronized
        fun getInstance(context: Context?): SharedPrefManager {
            if (mInstance == null) {
                mInstance =
                    SharedPrefManager(context)
            }
            return mInstance as SharedPrefManager
        }
    }

    fun setName(name: String) {
        val editor = mSharedPref?.edit()
        editor?.putString(keyName, name)
        editor?.apply()
    }

    fun getName(): String {
        return mSharedPref?.getString(keyName, "") as String
    }

    fun isNameInputted(): Boolean {
        return mSharedPref?.getBoolean(keyIsNameInputted, false) as Boolean
    }
}