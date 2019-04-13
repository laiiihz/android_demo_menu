package tech.laihz.android_demo_menu

import android.app.Activity
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val mSharedPreferences=getSharedPreferences("setting", Context.MODE_PRIVATE)
        val mEdit=mSharedPreferences.edit()
        setTheme(mSharedPreferences.getInt("theme",R.style.AppTheme))
        setContentView(R.layout.activity_settings)
        val cbIsChecked=mSharedPreferences.getBoolean("checkboxCheck",false)
        checkBox_night.isChecked=cbIsChecked
        checkBox_night.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                mEdit.putInt("theme",R.style.DarkTheme)
                mEdit.putBoolean("checkboxCheck",true)
                mEdit.apply()

            }else {
                mEdit.putInt("theme",R.style.AppTheme)
                mEdit.putBoolean("checkboxCheck",false)
                mEdit.apply()

            }

        }

        button_recreate.setOnClickListener {
            recreate()
        }


    }
}

