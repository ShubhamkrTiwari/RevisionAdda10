package com.example.revisionadda10.ui.ads

import android.app.Activity
import android.content.Context
import androidx.compose.runtime.*
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class AdManager(private val context: Context) {
    private var interstitialAd: InterstitialAd? = null
    
    // Production Interstitial Ad Unit ID
    private val interstitialAdUnitId = "ca-app-pub-7382226404157727/1773053499"
    
    fun loadInterstitialAd() {
        val adRequest = AdRequest.Builder().build()
        
        android.util.Log.d("AdManager", "Loading interstitial ad with unit ID: $interstitialAdUnitId")
        
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
                }
            }
        )
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

