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
    
    // Use test ad unit ID until account is approved, then switch to production ID
    private val interstitialAdUnitId = "ca-app-pub-3940256099942544/1033173712" // Test Interstitial Ad Unit ID
    // Production: "ca-app-pub-7382226404157727/1773053499"
    
    fun loadInterstitialAd() {
        val adRequest = AdRequest.Builder().build()
        
        InterstitialAd.load(
            context,
            interstitialAdUnitId,
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: InterstitialAd) {
                    interstitialAd = ad
                }
                
                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
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

