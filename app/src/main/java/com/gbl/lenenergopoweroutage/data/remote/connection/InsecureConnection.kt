package com.gbl.lenenergopoweroutage.data.remote.connection

import android.annotation.SuppressLint
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class InsecureConnection : SiteConnection {

    // Ignore certificates, because site "rosseti-lenenergo.ru" uses custom certificate
    @SuppressLint("TrustAllX509TrustManager", "CustomX509TrustManager")
    private val insecureTrustManager = arrayOf<TrustManager>(object : X509TrustManager {
        override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {}

        override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {}

        override fun getAcceptedIssuers(): Array<X509Certificate> = emptyArray()
    })

    private val insecureSslContext by lazy {
        SSLContext
            .getInstance("SSL")
            .apply { init(null, insecureTrustManager, SecureRandom()) }
    }

    override fun getJsoupDocument(url: String): Document =
        Jsoup.connect(url).sslSocketFactory(insecureSslContext.socketFactory).get()
}