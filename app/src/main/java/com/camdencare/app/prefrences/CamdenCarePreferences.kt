package com.camdencare.app.prefrences

import com.camdencare.app.prefrences.BasePreference
import android.content.Context
import com.camdencare.app.utilities.STR_PREF_NAME

class CamdenCarePreferences(private val context: Context) :
    BasePreference(context, STR_PREF_NAME) {

    companion object {
        const val DEF_VALUE_STRING = ""

        const val PREF_KEY_MRN = "mrn_key"
        const val PREF_KEY_NAME = "name_key"
        //todo:define other keys
    }

    fun isLogin(): Boolean {
        val mrn = getMrn()
        return !mrn.isEmpty()
    }

    fun saveMRN(mrn: String) {
        putString(PREF_KEY_MRN, mrn)
    }

    fun saveName(name: String) {
        putString(PREF_KEY_NAME, name)
    }

    fun saveAge(age: String) {
        putString("age_key", age)
    }

    fun getMrn(): String {
        return getString("mrn_key", "")!!
    }

    fun getName(): String {
        return getString("name_key", "")!!
    }

    fun getAge(): String {
        return getString("age_key", "")!!
    }

    fun logout() {
        clearSharedPrefs()
    }
}