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
import com.google.android.gms.ads.MobileAds
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialize AdMob
        android.util.Log.d("AdMob", "Initializing AdMob SDK...")
        MobileAds.initialize(this) { initializationStatus ->
            android.util.Log.d("AdMob", "AdMob initialization completed")
            val statusMap = initializationStatus.adapterStatusMap
            for (adapterClass in statusMap.keys) {
                val status = statusMap[adapterClass]
                android.util.Log.d("AdMob", "Adapter: $adapterClass, Status: ${status?.initializationState}, Description: ${status?.description}")
            }
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