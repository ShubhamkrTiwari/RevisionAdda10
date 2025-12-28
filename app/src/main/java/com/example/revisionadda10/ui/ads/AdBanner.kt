package com.example.revisionadda10.ui.ads

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError

@Composable
fun AdBanner(
    // Production Banner Ad Unit ID
    // Test ID: "ca-app-pub-3940256099942544/6300978111"
    // Production ID: "ca-app-pub-7382226404157727/7457281551"
    adUnitId: String = "ca-app-pub-7382226404157727/7457281551",
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    
    LaunchedEffect(adUnitId) {
        Log.d("AdBanner", "Initializing ad with unit ID: $adUnitId")
    }
    
    AndroidView(
        factory = { ctx ->
            Log.d("AdBanner", "Creating AdView for unit: $adUnitId")
            AdView(ctx).apply {
                setAdSize(AdSize.BANNER)
                this.adUnitId = adUnitId
                
                adListener = object : AdListener() {
                    override fun onAdLoaded() {
                        Log.d("AdBanner", "✅ Ad loaded successfully for unit: $adUnitId")
                    }
                    
                    override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                        Log.e("AdBanner", "❌ Ad failed to load for unit: $adUnitId")
                        Log.e("AdBanner", "Error: ${loadAdError.message}")
                        Log.e("AdBanner", "Error code: ${loadAdError.code}")
                        Log.e("AdBanner", "Error domain: ${loadAdError.domain}")
                        loadAdError.responseInfo?.let {
                            Log.e("AdBanner", "Response info: ${it.responseId}")
                        }
                    }
                    
                    override fun onAdOpened() {
                        Log.d("AdBanner", "Ad opened")
                    }
                    
                    override fun onAdClosed() {
                        Log.d("AdBanner", "Ad closed")
                    }
                    
                    override fun onAdImpression() {
                        Log.d("AdBanner", "Ad impression recorded")
                    }
                }
                
                // Load ad after ensuring AdMob is initialized
                android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                    try {
                        val adRequest = AdRequest.Builder().build()
                        Log.d("AdBanner", "Loading ad request for unit: $adUnitId")
                        loadAd(adRequest)
                    } catch (e: Exception) {
                        Log.e("AdBanner", "Error loading ad: ${e.message}", e)
                    }
                }, 2000) // Increased delay to ensure AdMob is initialized
            }
        },
        update = { view ->
            // Reload if ad unit ID changed
            if (view.adUnitId != adUnitId) {
                Log.d("AdBanner", "Updating ad unit ID from ${view.adUnitId} to $adUnitId")
                view.adUnitId = adUnitId
                try {
                    val adRequest = AdRequest.Builder().build()
                    view.loadAd(adRequest)
                } catch (e: Exception) {
                    Log.e("AdBanner", "Error reloading ad: ${e.message}", e)
                }
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
    )
    
    // Cleanup on dispose
    DisposableEffect(adUnitId) {
        onDispose {
            // Cleanup handled by AndroidView lifecycle
            Log.d("AdBanner", "AdBanner disposed for unit: $adUnitId")
        }
    }
}

@Composable
fun AdBannerCard(
    // Production Banner Ad Unit ID
    adUnitId: String = "ca-app-pub-7382226404157727/7457281551",
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = androidx.compose.material3.MaterialTheme.colorScheme.surface
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            AdBanner(adUnitId = adUnitId)
        }
    }
}

