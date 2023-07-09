package com.example.wello

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Animatable2
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.firebase.auth.FirebaseAuth


class SplashScreenActivity : Activity() {
    private lateinit var auth: FirebaseAuth
    private var image: ImageView? = null
    private var animation: AnimatedVectorDrawable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        val intent = Intent(this@SplashScreenActivity, LoginActivity::class.java)

        auth = FirebaseAuth.getInstance()

//        val intent = if (auth.currentUser != null) {
//            Intent(this@SplashScreenActivity, MainActivity::class.java)
//        } else {
//            Intent(this@SplashScreenActivity, LoginActivity::class.java)
//        }

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()

//        setContentView(R.layout.activity_splash_screen)
//        image = findViewById<View>(R.id.imageSplash) as ImageView
//        image?.visibility = View.VISIBLE
//        val d = image!!.drawable
//        if (d is AnimatedVectorDrawable) {
//            animation = d
//            animation!!.start()
//            animation!!.registerAnimationCallback(object : Animatable2.AnimationCallback() {
//                override fun onAnimationEnd(drawable: Drawable?) {
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
//                    startActivity(intent)
//                    finish()
//                }
//            })
//        }




//        if (Build.VERSION.SDK_INT < 30) {
//            setContentView(R.layout.activity_splash_screen)
//            image = findViewById<View>(R.id.imageSplash) as ImageView
//            val animatedVectorDrawable = getDrawable(R.drawable.splash_screen_icon_animated) as AnimatedVectorDrawable?
//            image!!.visibility = View.VISIBLE // Set the visibility to VISIBLE
//            image!!.setImageDrawable(animatedVectorDrawable)
//            animatedVectorDrawable!!.start()
//            animatedVectorDrawable.registerAnimationCallback(object : Animatable2.AnimationCallback() {
//                override fun onAnimationEnd(drawable: Drawable?) {
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
//                    startActivity(intent)
//                    finish()
//                }
//            })
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
//            startActivity(intent)
//            finish()
//        }

//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
//        startActivity(intent)
//        finish()
    }

//    override fun onStart() {
//        auth = FirebaseAuth.getInstance()
//
//        val intent = if (auth.currentUser != null) {
//            Intent(this@SplashScreenActivity, MainActivity::class.java)
//        } else {
//            Intent(this@SplashScreenActivity, LoginActivity::class.java)
//        }
//
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
//            super.onStart()
//            val d = image!!.drawable
//            if (d is AnimatedVectorDrawable) {
//                animation = d
//                animation!!.start()
//                animation!!.registerAnimationCallback(object : Animatable2.AnimationCallback() {
//                    override fun onAnimationEnd(drawable: Drawable?) {
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
//                        startActivity(intent)
//                        finish()
//                    }
//                })
//            }
//        }
//    }
}


//
//class SplashScreenActivity : Activity() {
//    private lateinit var auth: FirebaseAuth
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        auth = FirebaseAuth.getInstance()
//
//        val intent = if (auth.currentUser != null) {
//            Intent(this@SplashScreenActivity, MainActivity::class.java)
//        } else {
//            Intent(this@SplashScreenActivity, LoginActivity::class.java)
//        }
//
//
//        //Thread.sleep(1000)
//
//
////        // Set up an OnPreDrawListener to the root view.
////        val content: View = findViewById(android.R.id.content)
////        content.viewTreeObserver.addOnPreDrawListener(
////            object : ViewTreeObserver.OnPreDrawListener {
////                override fun onPreDraw(): Boolean {
////                    if (auth.currentUser != null) {
////                        return if (MainActivityViewModel.isReady) {
////                            // The content is ready. Start drawing.
////                            content.viewTreeObserver.removeOnPreDrawListener(this)
////                            true
////                        } else {
////                            // The content isn't ready. Suspend.
////                            false
////                        }
////                    }
////                    else
////                    {
////                        return if (LoginActivityViewModel.isReady) {
////                            // The content is ready. Start drawing.
////                            content.viewTreeObserver.removeOnPreDrawListener(this)
////                            true
////                        } else {
////                            // The content isn't ready. Suspend.
////                            false
////                        }
////                    }
////                }
////            }
////        )
//
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
////            splashScreen.setOnExitAnimationListener { splashScreenView ->
////                // Create your custom animation.
////                val slideUp = ObjectAnimator.ofFloat(
////                    splashScreenView,
////                    View.TRANSLATION_Y,
////                    0f,
////                    -splashScreenView.height.toFloat()
////                )
////                slideUp.interpolator = AnticipateInterpolator()
////                slideUp.duration = 200L
////
////                // Call SplashScreenView.remove at the end of your custom animation.
////                slideUp.doOnEnd {
////                    splashScreenView.remove()
////
////                    // Finish the activity after the animation completes.
////                    finish()
////                }
////
////                // Run your animation.
////                slideUp.start()
////            }
////        }
//
//
//        //super.onCreate(savedInstanceState)
//        // Set the layout for the content view.
//
//
////        val nightModeFlags = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
////        if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
////            setTheme(R.style.Theme_Eq_bachelor_thesis)
////        }
//
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
//        startActivity(intent)
//        finish()
//    }
//}
//package com.example.eq_bachelor_thesis
//
//import android.animation.ObjectAnimator
//import android.app.Activity
//import android.content.Intent
//import android.content.res.Configuration
//import android.os.Build
//import android.os.Bundle
//import android.view.View
//import android.view.ViewTreeObserver
//import android.view.animation.AnticipateInterpolator
//import androidx.core.animation.doOnEnd
//import com.google.firebase.auth.FirebaseAuth
//import java.time.Duration
//import java.time.Instant





