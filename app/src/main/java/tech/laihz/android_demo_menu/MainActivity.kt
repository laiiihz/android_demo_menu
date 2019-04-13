package tech.laihz.android_demo_menu

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.widget.PopupMenu
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var presentKey=0
        var keyPressedCount=0
        var isCheckedCB=false
        val userSettings = getSharedPreferences("setting", Context.MODE_PRIVATE)
        val editor = userSettings.edit()
        editor.apply()
        val themeMain=userSettings.getInt("theme",0)
        setTheme(themeMain)
        setContentView(R.layout.activity_main)

        button_dlg.setOnClickListener {
            val ad:AlertDialog.Builder = AlertDialog.Builder(this)
            ad.setTitle("Title")
            ad.setMessage("this is a message")
            ad.setNegativeButton("取消"){ _, _ ->}
            ad.setPositiveButton("确定"){ _, _ ->}
            ad.show()
        }

        textView_open.setOnLongClickListener {
            textView_open.showContextMenu()
        }

        textView_open.setOnCreateContextMenuListener { _, v, _ ->
            var popMenu=PopupMenu(this,v)
            popMenu.inflate(R.menu.context_menu)
            popMenu.show()
        }

        button_act_show.setOnClickListener {
            supportActionBar?.show()
        }

        button_act_hide.setOnClickListener {
            supportActionBar?.hide()
        }

        checkBox_ui.setOnCheckedChangeListener { _, isChecked ->
            isCheckedCB = isChecked
        }

        et_input.setOnKeyListener { v, keyCode, event ->

            if(!isCheckedCB){
                if(event.action==KeyEvent.ACTION_DOWN){
                    tv_result.text=""
                    tv_result.append("keyCode:$keyCode\n")
                    tv_result.append("keyStatus:Down\n")
                    tv_result.append("keyPressedCount:$keyPressedCount\n")

                }
                if(event.action==KeyEvent.ACTION_UP){
                    if(keyCode!=presentKey){
                        presentKey=keyCode
                        keyPressedCount=0
                    }else{
                        keyPressedCount++
                    }
                    tv_result.text=""
                    tv_result.append("keyCode:$keyCode\n")
                    tv_result.append("keyStatus:UP\n")
                    tv_result.append("keyPressedCount:$keyPressedCount\n")

                }
            }


            true
        }



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
                R.id.item_settings->{
                    val intent=Intent()
                    intent.setClass(this,SettingsActivity::class.java)
                    startActivity(intent)
                    true
                }
                else->super.onOptionsItemSelected(item)
            }

    }




}


