package com.example.revisionadda10.ui.ads

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError

@Composable
fun AdBanner(
    // Use test ad unit ID until account is approved, then switch to production ID
    adUnitId: String = "ca-app-pub-3940256099942544/6300978111", // Test Banner Ad Unit ID
    // Production: "ca-app-pub-7382226404157727/7457281551"
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var adView by remember { mutableStateOf<AdView?>(null) }
    
    LaunchedEffect(Unit) {
        Log.d("AdBanner", "Initializing ad with unit ID: $adUnitId")
    }
    
    AndroidView(
        factory = { ctx ->
            AdView(ctx).apply {
                setAdSize(AdSize.BANNER)
                this.adUnitId = adUnitId
                
                adListener = object : AdListener() {
                    override fun onAdLoaded() {
                        Log.d("AdBanner", "Ad loaded successfully for unit: $adUnitId")
                    }
                    
                    override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                        Log.e("AdBanner", "Ad failed to load for unit: $adUnitId")
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
                }
                
                val adRequest = AdRequest.Builder().build()
                loadAd(adRequest)
                adView = this
            }
        },
        update = { view ->
            // Update if needed
        },
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
    )
}

@Composable
fun AdBannerCard(
    // Use test ad unit ID until account is approved, then switch to production ID
    adUnitId: String = "ca-app-pub-3940256099942544/6300978111", // Test Banner Ad Unit ID
    // Production: "ca-app-pub-7382226404157727/7457281551"
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

