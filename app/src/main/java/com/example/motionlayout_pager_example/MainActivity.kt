package com.example.motionlayout_pager_example

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.constraintlayout.motion.widget.MotionLayout

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var currentPosition = 0
    private val itemList = listOf(
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        setupMotion()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupMotion() {
        motionLayout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionTrigger(motionLayout: MotionLayout?, triggerId: Int, positive: Boolean, progress: Float) {
            }

            override fun onTransitionStarted(motionLayout: MotionLayout?, startedId: Int, endId: Int) {
            }

            override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) {
            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                when (currentId) {
                    R.id.move_left_to_right -> {
                        if (currentPosition > 0) {
                            currentPosition--
                        } else {
                            currentPosition = itemList.lastIndex
                        }
                        motionLayout?.progress = 0F
                        updateView()
                    }
                    R.id.move_right_to_left -> {
                        if (currentPosition < itemList.lastIndex) {
                            currentPosition++
                        } else {
                            currentPosition = 0
                        }
                        motionLayout?.progress = 0F
                        updateView()
                    }
                }
            }
        })
        updateView()
    }

    @SuppressLint("SetTextI18n")
    private fun updateView() {
        centerTextView.text = "Item\n${itemList[currentPosition]}"

        rightTextView.text = if (currentPosition == itemList.lastIndex) {
            "Item\n${itemList.first()}"
        } else {
            "Item\n${itemList[currentPosition + 1]}"
        }

        leftTextView.text = if (currentPosition == 0) {
            "Item\n${itemList.last()}"
        } else {
            "Item\n${itemList[currentPosition - 1]}"
        }
    }
}
