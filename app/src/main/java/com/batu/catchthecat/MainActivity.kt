package com.batu.catchthecat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Random

class MainActivity : AppCompatActivity() {

    var score = 0
    var imageArray = ArrayList<ImageView>()
    var runnable : Runnable  = Runnable {  }
    var handler : Handler = Handler(Looper.getMainLooper())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageArray.add(imageView)
        imageArray.add(imageView2)
        imageArray.add(imageView3)
        imageArray.add(imageView4)
        imageArray.add(imageView5)
        imageArray.add(imageView6)
        imageArray.add(imageView7)
        imageArray.add(imageView8)
        imageArray.add(imageView9)

        hideImages()


        //CountDown Timer
        object : CountDownTimer(15400,1000){
            override fun onTick(p0: Long) {
                timeText.text = "Time : "+p0/1000
            }

            override fun onFinish() {
                timeText.text = "Time : 0"

                handler.removeCallbacks(runnable)

                for (imgView in imageArray){
                    imgView.visibility = View.INVISIBLE
                }

                //Alert
                val alert = AlertDialog.Builder(this@MainActivity)

                alert.setTitle("Game Over")
                alert.setMessage("Restart the Game ?")
                alert.setPositiveButton("Yes"){dialog,which->
                    //Restart
                    val intent = intent
                    finish()
                    startActivity(intent)
                }
                alert.setNegativeButton("No"){dialog,which->
                    Toast.makeText(this@MainActivity,"Game Over",Toast.LENGTH_LONG).show()
                }
                alert.show()
            }
        }.start()
    }

    fun hideImages(){

        runnable = object : Runnable{
            override fun run() {
                for (imgView in imageArray){
                    imgView.visibility = View.INVISIBLE
                }

                var random = Random()
                val randomIndex = random.nextInt(9)
                imageArray[randomIndex].visibility = View.VISIBLE

                handler.postDelayed(runnable,350)
            }
        }
        handler.post(runnable)
    }

    fun increaseScore(view:View){
        score++
        scoreText.text = "Score $score"
    }
}