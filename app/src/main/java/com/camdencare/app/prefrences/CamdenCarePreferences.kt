package com.camdencare.app.prefrences

import com.camdencare.app.prefrences.BasePreference
import android.content.Context
import com.camdencare.app.utilities.STR_PREF_NAME

class CamdenCarePreferences(private val context: Context) :
    BasePreference(context, STR_PREF_NAME) {

    fun isLogin():Boolean{
        val mrn=getMrn()
        return !mrn.isEmpty()
    }
    fun saveMRN(mrn:String){
        putString("mrn_key",mrn)
    }
    fun saveName(name:String){
        putString("name_key",name)
    }
    fun saveAge(age:String){
        putString("age_key",age)
    }
    fun getMrn():String{
        return getString("mrn_key","")!!
    }
    fun getName():String{
        return getString("name_key","")!!
    }
    fun getAge():String{
        return getString("age_key","")!!
    }
    fun logout(){
        clearSharedPrefs()
    }
}