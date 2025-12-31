package com.example.revisionadda10

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.revisionadda10.ui.navigation.NavGraph
import com.example.revisionadda10.ui.theme.RevisionAdda10Theme
import com.example.revisionadda10.utils.ThemeManager
import com.example.revisionadda10.utils.VideoPreFetchHelper
import com.example.revisionadda10.ui.ads.AppOpenAdManager
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    companion object {
        var appOpenAdManager: AppOpenAdManager? = null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialize AdMob with better error handling
        android.util.Log.d("AdMob", "Initializing AdMob SDK...")
        try {
            // Set request configuration (optional - for testing)
            val requestConfiguration = RequestConfiguration.Builder()
                .build()
            MobileAds.setRequestConfiguration(requestConfiguration)
            
            MobileAds.initialize(this) { initializationStatus ->
                android.util.Log.d("AdMob", "✅ AdMob initialization completed")
                val statusMap = initializationStatus.adapterStatusMap
                for (adapterClass in statusMap.keys) {
                    val status = statusMap[adapterClass]
                    val state = status?.initializationState
                    android.util.Log.d("AdMob", "Adapter: $adapterClass, Status: $state, Description: ${status?.description}")
                }
                android.util.Log.d("AdMob", "✅ AdMob initialization status logged")
                
                // Initialize App Open Ad Manager after AdMob is initialized
                if (appOpenAdManager == null) {
                    android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                        try {
                            appOpenAdManager = AppOpenAdManager(application)
                            android.util.Log.d("AdMob", "✅ App Open Ad Manager initialized")
                            android.util.Log.d("AdMob", "✅ App Open Ad will show on app start/resume")
                        } catch (e: Exception) {
                            android.util.Log.e("AdMob", "❌ Error initializing AppOpenAdManager: ${e.message}", e)
                            e.printStackTrace()
                        }
                    }, 5000) // Wait 5 seconds for AdMob to fully initialize
                }
            }
        } catch (e: Exception) {
            android.util.Log.e("AdMob", "❌ Error initializing AdMob: ${e.message}", e)
            e.printStackTrace()
        }
        
        enableEdgeToEdge()
        
        // Pre-fetch YouTube videos for all chapters in background
        lifecycleScope.launch {
            android.util.Log.d("MainActivity", "Starting video pre-fetch in background...")
            VideoPreFetchHelper.preFetchAllVideos(lifecycleScope)
        }
        
        setContent {
            // Use remember to read theme preference and make it mutable
            var isDarkMode by remember { mutableStateOf(ThemeManager.isDarkMode(this@MainActivity)) }
            
            RevisionAdda10Theme(darkTheme = isDarkMode) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    // Pass theme update function to NavGraph
                    NavGraph(
                        navController = navController,
                        onThemeChange = { newValue ->
                            isDarkMode = newValue
                        }
                    )
                }
            }
        }
    }
}