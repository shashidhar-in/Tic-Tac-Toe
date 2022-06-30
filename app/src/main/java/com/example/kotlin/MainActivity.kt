package com.example.kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*


class MainActivity : AppCompatActivity() {
    lateinit var Player1:EditText
    lateinit var Player2:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startBtn=findViewById(R.id.startGameBtn) as Button
         Player1=findViewById<EditText>(R.id.Player1)
         Player2=findViewById<EditText>(R.id.Player2)



        startBtn.setOnClickListener{
            if (ifempty()){
                Toast.makeText(this@MainActivity,"starting game",Toast.LENGTH_SHORT).show();
                val intent=Intent(this,BoardActivity::class.java)

                val player1name=Player1.text.toString()
                val player2name=Player2.text.toString()

                intent.putExtra("p1",player1name)
                intent.putExtra("p2",player2name)

                startActivity(intent)
                finish()
            }

        }

    }
    fun ifempty(): Boolean {
        if(Player1.text.toString().trim().isEmpty()){
            Player1.setError("Enter A Player Name")
            return false
        }
        if(Player2.text.toString().trim().isEmpty()){
            Player2.setError("Enter A Player Name")
            return false
        }
        return true
    }

}