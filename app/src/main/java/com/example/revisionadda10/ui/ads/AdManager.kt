package com.example.revisionadda10.ui.ads

import android.app.Activity
import android.content.Context
import androidx.compose.runtime.*
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import kotlinx.coroutines.delay
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class AdManager(private val context: Context) {
    private var interstitialAd: InterstitialAd? = null
    
    // Production Interstitial Ad Unit ID (for MCQ/Quiz)
    // Test ID: "ca-app-pub-3940256099942544/1033173712"
    // Production ID: "ca-app-pub-7382226404157727/6610694621"
    private val interstitialAdUnitId = "ca-app-pub-7382226404157727/6610694621"
    
    suspend fun waitForAdMobInitialization() {
        // Wait a bit for AdMob to initialize
        delay(2000)
    }
    
    fun loadInterstitialAd() {
        android.util.Log.d("AdManager", "Loading interstitial ad with unit ID: $interstitialAdUnitId")
        
        // Wait a bit before loading to ensure AdMob is initialized
        android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
            try {
                val adRequest = AdRequest.Builder().build()
                
                InterstitialAd.load(
                    context,
                    interstitialAdUnitId,
                    adRequest,
                    object : InterstitialAdLoadCallback() {
                        override fun onAdLoaded(ad: InterstitialAd) {
                            android.util.Log.d("AdManager", "✅ Interstitial ad loaded successfully")
                            interstitialAd = ad
                        }
                        
                        override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                            android.util.Log.e("AdManager", "❌ Interstitial ad failed to load")
                            android.util.Log.e("AdManager", "Error: ${loadAdError.message}")
                            android.util.Log.e("AdManager", "Error code: ${loadAdError.code}")
                            android.util.Log.e("AdManager", "Error domain: ${loadAdError.domain}")
                            loadAdError.responseInfo?.let {
                                android.util.Log.e("AdManager", "Response info: ${it.responseId}")
                            }
                            interstitialAd = null
                            
                            // Retry after delay
                            android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                                loadInterstitialAd()
                            }, 5000)
                        }
                    }
                )
            } catch (e: Exception) {
                android.util.Log.e("AdManager", "Exception loading ad: ${e.message}", e)
            }
        }, 2000) // Wait 2 seconds for AdMob initialization
    }
    
    fun showInterstitialAd(activity: Activity, onAdDismissed: () -> Unit = {}) {
        interstitialAd?.let { ad ->
            ad.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    interstitialAd = null
                    onAdDismissed()
                    // Load next ad
                    loadInterstitialAd()
                }
                
                override fun onAdFailedToShowFullScreenContent(p0: com.google.android.gms.ads.AdError) {
                    interstitialAd = null
                    onAdDismissed()
                    loadInterstitialAd()
                }
            }
            ad.show(activity)
        } ?: run {
            onAdDismissed()
            loadInterstitialAd()
        }
    }
    
    fun isInterstitialAdLoaded(): Boolean {
        return interstitialAd != null
    }
}

@Composable
fun rememberAdManager(): AdManager {
    val context = androidx.compose.ui.platform.LocalContext.current
    return remember { AdManager(context) }
}

