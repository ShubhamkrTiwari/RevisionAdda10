package com.example.revisionadda10.ui.screens.pdf

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun PDFViewerScreen(
    navController: NavController,
    pdfUrl: String
) {
    val context = LocalContext.current
    val primaryColor = Color(0xFF2196F3)
    
    // Decode URL - replace encoded characters
    val decodedUrl = remember(pdfUrl) {
        pdfUrl.replace("_SLASH_", "/")
            .replace("_COLON_", ":")
            .replace("_", "/")
    }
    
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var viewerMethod by remember { mutableStateOf(0) } // 0: Google Docs, 1: PDF.js, 2: Direct HTML embed
    var hasContent by remember { mutableStateOf(false) }
    
    // Timeout mechanism - if loading takes too long, show error
    LaunchedEffect(isLoading) {
        if (isLoading) {
            kotlinx.coroutines.delay(15000) // 15 seconds timeout
            if (isLoading && !hasContent) {
                isLoading = false
                if (viewerMethod < 2) {
                    viewerMethod++
                    isLoading = true
                } else {
                    errorMessage = "PDF is taking too long to load. The file may be large or the URL may be invalid. Try 'Open in Browser'."
                }
            }
        }
    }
    
    // Try multiple PDF viewer methods
    val pdfViewerUrls = remember(decodedUrl, viewerMethod) {
        if (decodedUrl.startsWith("http")) {
            when (viewerMethod) {
                0 -> {
                    // Primary: Use Google Docs viewer
                    "https://docs.google.com/viewer?url=${java.net.URLEncoder.encode(decodedUrl, "UTF-8")}&embedded=true"
                }
                1 -> {
                    // Alternative: Use Mozilla PDF.js viewer (CDN version)
                    "https://mozilla.github.io/pdf.js/web/viewer.html?file=${java.net.URLEncoder.encode(decodedUrl, "UTF-8")}"
                }
                2 -> {
                    // Direct HTML embed with iframe
                    "data:text/html;charset=utf-8," + java.net.URLEncoder.encode("""
                        <!DOCTYPE html>
                        <html>
                        <head>
                            <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=5.0">
                            <style>
                                body { margin: 0; padding: 0; overflow: hidden; background: #525252; }
                                iframe { width: 100%; height: 100vh; border: none; }
                            </style>
                        </head>
                        <body>
                            <iframe src="$decodedUrl" type="application/pdf"></iframe>
                        </body>
                        </html>
                    """.trimIndent(), "UTF-8")
                }
                else -> decodedUrl
            }
        } else {
            decodedUrl
        }
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        text = "PDF Viewer",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                actions = {
                    TextButton(
                        onClick = {
                            try {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(decodedUrl))
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                context.startActivity(intent)
                            } catch (e: Exception) {
                                errorMessage = "No PDF viewer app found. Please install a PDF reader."
                            }
                        }
                    ) {
                        Text("Open in Browser", style = MaterialTheme.typography.labelMedium)
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            primaryColor.copy(alpha = 0.1f),
                            MaterialTheme.colorScheme.surface
                        )
                    )
                )
        ) {
            AndroidView(
                factory = { ctx ->
                    WebView(ctx).apply {
                        webViewClient = object : WebViewClient() {
                            override fun onPageStarted(view: WebView?, url: String?, favicon: android.graphics.Bitmap?) {
                                super.onPageStarted(view, url, favicon)
                                isLoading = true
                                errorMessage = null
                            }
                            
                            override fun onPageFinished(view: WebView?, url: String?) {
                                super.onPageFinished(view, url)
                                isLoading = false
                                hasContent = true
                                errorMessage = null
                                
                                // Inject JavaScript to check if PDF content is visible
                                view?.evaluateJavascript("""
                                    (function() {
                                        var iframe = document.querySelector('iframe');
                                        var embed = document.querySelector('embed');
                                        var hasPdf = iframe || embed || document.body.innerText.length > 100;
                                        return hasPdf;
                                    })();
                                """.trimIndent()) { result ->
                                    if (result == "false" || result == null) {
                                        // No content detected, try next viewer
                                        if (viewerMethod < 2) {
                                            viewerMethod++
                                            isLoading = true
                                            hasContent = false
                                        }
                                    }
                                }
                            }
                            
                            override fun onReceivedError(
                                view: WebView?,
                                request: WebResourceRequest?,
                                error: android.webkit.WebResourceError?
                            ) {
                                super.onReceivedError(view, request, error)
                                if (request?.isForMainFrame == true) {
                                    isLoading = false
                                    if (viewerMethod < 2) {
                                        // Try next viewer method
                                        viewerMethod++
                                        isLoading = true
                                        // Reload with new viewer
                                        android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                                            val newUrl = when (viewerMethod) {
                                                1 -> "https://mozilla.github.io/pdf.js/web/viewer.html?file=${java.net.URLEncoder.encode(decodedUrl, "UTF-8")}"
                                                2 -> "data:text/html;charset=utf-8," + java.net.URLEncoder.encode("""
                                                    <!DOCTYPE html>
                                                    <html>
                                                    <head>
                                                        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                                        <style>body { margin: 0; padding: 0; } iframe { width: 100%; height: 100vh; border: none; }</style>
                                                    </head>
                                                    <body><iframe src="$decodedUrl"></iframe></body>
                                                    </html>
                                                """.trimIndent(), "UTF-8")
                                                else -> decodedUrl
                                            }
                                            view?.loadUrl(newUrl)
                                        }, 500)
                                    } else {
                                        errorMessage = "Unable to load PDF. The file may not be accessible. Try 'Open in Browser'."
                                    }
                                }
                            }
                            
                            override fun onReceivedHttpError(
                                view: WebView?,
                                request: WebResourceRequest?,
                                errorResponse: WebResourceResponse?
                            ) {
                                super.onReceivedHttpError(view, request, errorResponse)
                                if (request?.isForMainFrame == true) {
                                    when (errorResponse?.statusCode) {
                                        403, 401 -> {
                                            isLoading = false
                                            errorMessage = "Access denied. PDF may require authentication. Try 'Open in Browser'."
                                        }
                                        404 -> {
                                            isLoading = false
                                            if (viewerMethod < 2) {
                                                viewerMethod++
                                                isLoading = true
                                            } else {
                                                errorMessage = "PDF not found. The file may have been moved or deleted."
                                            }
                                        }
                                        else -> {
                                            if (viewerMethod < 2) {
                                                viewerMethod++
                                                isLoading = true
                                            } else {
                                                isLoading = false
                                                errorMessage = "Unable to load PDF (Error: ${errorResponse?.statusCode}). Try 'Open in Browser'."
                                            }
                                        }
                                    }
                                }
                            }
                            
                            override fun shouldOverrideUrlLoading(
                                view: WebView?,
                                request: WebResourceRequest?
                            ): Boolean {
                                // Allow PDF URLs to load
                                return false
                            }
                        }
                        
                        settings.apply {
                            javaScriptEnabled = true
                            domStorageEnabled = true
                            databaseEnabled = true
                            loadWithOverviewMode = true
                            useWideViewPort = true
                            builtInZoomControls = true
                            displayZoomControls = true
                            setSupportZoom(true)
                            allowFileAccess = true
                            allowContentAccess = true
                            allowFileAccessFromFileURLs = true
                            allowUniversalAccessFromFileURLs = true
                            mixedContentMode = android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                            cacheMode = android.webkit.WebSettings.LOAD_DEFAULT
                            // Set user agent to avoid blocking
                            userAgentString = "Mozilla/5.0 (Linux; Android 10; Mobile) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Mobile Safari/537.36"
                        }
                        
                        // Load PDF with proper headers
                        val headers = mapOf(
                            "User-Agent" to "Mozilla/5.0 (Linux; Android 10; Mobile) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.120 Mobile Safari/537.36",
                            "Accept" to "application/pdf,text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
                            "Accept-Language" to "en-US,en;q=0.9"
                        )
                        
                        loadUrl(pdfViewerUrls, headers)
                    }
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                update = { view ->
                    // Update WebView when viewer method or URL changes
                    val currentUrl = when (viewerMethod) {
                        0 -> "https://docs.google.com/viewer?url=${java.net.URLEncoder.encode(decodedUrl, "UTF-8")}&embedded=true"
                        1 -> "https://mozilla.github.io/pdf.js/web/viewer.html?file=${java.net.URLEncoder.encode(decodedUrl, "UTF-8")}"
                        2 -> "data:text/html;charset=utf-8," + java.net.URLEncoder.encode("""
                            <!DOCTYPE html>
                            <html>
                            <head>
                                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                <style>body { margin: 0; padding: 0; } iframe { width: 100%; height: 100vh; border: none; }</style>
                            </head>
                            <body><iframe src="$decodedUrl"></iframe></body>
                            </html>
                        """.trimIndent(), "UTF-8")
                        else -> decodedUrl
                    }
                    
                    if (view.url != currentUrl && !isLoading) {
                        val headers = mapOf(
                            "User-Agent" to "Mozilla/5.0 (Linux; Android 10; Mobile) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Mobile Safari/537.36",
                            "Accept" to "application/pdf,text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"
                        )
                        view.loadUrl(currentUrl, headers)
                    }
                }
            )
            
            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.9f)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(24.dp)
                    ) {
                        CircularProgressIndicator(color = primaryColor)
                        Text(
                            text = "Loading PDF...",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = when (viewerMethod) {
                                0 -> "Using Google Docs viewer..."
                                1 -> "Trying PDF.js viewer..."
                                2 -> "Trying direct embed..."
                                else -> "Loading..."
                            },
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
            
            errorMessage?.let { error ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surface),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                        modifier = Modifier.padding(24.dp)
                    ) {
                        Text(
                            text = "⚠️",
                            style = MaterialTheme.typography.displayMedium
                        )
                        Text(
                            text = error,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(horizontal = 16.dp),
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                        Text(
                            text = "PDF URL: ${decodedUrl.take(50)}...",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(horizontal = 16.dp),
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Button(
                                onClick = { 
                                    isLoading = true
                                    errorMessage = null
                                    viewerMethod = 0 // Reset to first viewer
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = primaryColor
                                )
                            ) {
                                Text("Retry")
                            }
                            OutlinedButton(
                                onClick = {
                                    try {
                                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(decodedUrl))
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                        context.startActivity(intent)
                                    } catch (e: Exception) {
                                        errorMessage = "No PDF viewer app found. Please install a PDF reader."
                                    }
                                }
                            ) {
                                Text("Open in Browser")
                            }
                        }
                    }
                }
            }
        }
    }
}

