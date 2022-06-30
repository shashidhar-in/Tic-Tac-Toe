package com.example.kotlin
import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class BoardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board)
        val Player1=findViewById<TextView>(R.id.player1Name)
        val Player2=findViewById<TextView>(R.id.player2Name)
        val player1name=intent.getStringExtra("p1")
        val player2name=intent.getStringExtra("p2")
        Player1.text=player1name
        Player2.text=player2name
    }


    var activePlayer = 1
    var gameIsActive = true
    var count = 0
    var gameState = intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)
    var winningPositions = arrayOf(
        intArrayOf(0, 1, 2),
        intArrayOf(3, 4, 5),
        intArrayOf(6, 7, 8),
        intArrayOf(0, 3, 6),
        intArrayOf(1, 4, 7),
        intArrayOf(2, 5, 8),
        intArrayOf(0, 4, 8),
        intArrayOf(2, 4, 6)
    )


    fun dropIn(view: View) {

        val counter =view as ImageView
        val txt=findViewById<TextView>(R.id.winner1)
        val layout=findViewById<LinearLayout>(R.id.winner)


        val tappedcounter=counter.tag.toString().toInt()
        var mediaPlayer =MediaPlayer.create(this,R.raw.clicksound)
        if (mediaPlayer.isPlaying){
            mediaPlayer.reset()
        }
        else{
            mediaPlayer.start()

        }
        if(gameState[tappedcounter]==2 && gameIsActive){

            if (activePlayer==1){
                flip1()
                counter.setImageResource(R.drawable.player1)
                activePlayer=0
                count++
                gameState[tappedcounter]=1
            }
            else{
                flip2()
                counter.setImageResource(R.drawable.player2)
                activePlayer=1
                count++
                gameState[tappedcounter]=0
            }
            counter.translationY= -1000f
            counter.animate().translationYBy(1000f).duration=500
            for (winningposition in winningPositions){
                if (gameState[winningposition[0]] == gameState[winningposition[1]] && gameState[winningposition[1]] == gameState[winningposition[2]] && gameState[winningposition[0]] != 2
                ){
                    if(gameState[winningposition[0]]==0) txt.text=intent.getStringExtra("p2")+" Won the game"
                    else if (gameState[winningposition[0]]==1)txt.text=intent.getStringExtra("p1")+" Won the game"

                    layout.visibility=View.VISIBLE
                    gameIsActive=false
                }
            }

        }
        if(gameIsActive&& count ==9){
            txt.text="Draw"
            layout.visibility=View.VISIBLE
            gameIsActive=false
        }

    }

    private fun flip2() {
        val Player1=findViewById<TextView>(R.id.player1Name)
        val Player2=findViewById<TextView>(R.id.player2Name)
        Player1.setTextColor(Color.parseColor("#007500"))
        Player2.setTextColor(Color.parseColor("#525252"))

    }

    private fun flip1() {
        val Player1 = findViewById<TextView>(R.id.player1Name)
        val Player2 = findViewById<TextView>(R.id.player2Name)
        Player1.setTextColor(Color.parseColor("#525252"))
        Player2.setTextColor(Color.parseColor("#007500"))

    }
    fun playAgain(view: View) {
        flip2()
    activePlayer=1
        gameIsActive=true
        count=0
        val linearLayout=findViewById<LinearLayout>(R.id.winner)
        val  gridLayout=findViewById<GridLayout>(R.id.gridLayout)
        for(i in gameState.indices){
            gameState[i]=2
        }
        linearLayout.visibility=View.VISIBLE
        for(i in 0 until gridLayout.childCount){
            (gridLayout.getChildAt(i) as ImageView).setImageResource(0)
        }

    }



}