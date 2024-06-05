package com.atos.ojo.config;

import java.io.IOException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

public class CustomClientHttpRequestFactory extends SimpleClientHttpRequestFactory {

	public CustomClientHttpRequestFactory() {
		super();
		try {

			// Create a custom SSL context that trusts all certificates
			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null, new TrustManager[] { new X509TrustManager() {
				public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
						throws java.security.cert.CertificateException {
				}

				public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
						throws java.security.cert.CertificateException {
				}

				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			} }, new java.security.SecureRandom());

			// Set the custom SSL context to the HTTPS connection
			HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

		} catch (Exception e) {
			throw new RuntimeException("Failed to configure SSL", e);
		}
	}

	@Override
	protected void prepareConnection(java.net.HttpURLConnection connection, String httpMethod) throws IOException {
		if (connection instanceof javax.net.ssl.HttpsURLConnection) {
			// Bypass hostname verification
			((javax.net.ssl.HttpsURLConnection) connection).setHostnameVerifier((hostname, session) -> true);
		}
		super.prepareConnection(connection, httpMethod);
	}
}
