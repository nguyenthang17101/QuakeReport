package com.example.quakereportx

import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.Charset
import javax.net.ssl.HttpsURLConnection

object NetworkUtils {
    fun <T> fetch(urlString: String, parse: (String) -> T): T {
        var jsonResponse = ""
        try {
            jsonResponse = makeHttpsRequest(urlString)

        } catch (e: IOException) {
            Log.e("NetworkUtils", "request address Error: $urlString ", e)
        }

        return parse(jsonResponse)
    }

    @Throws(IOException::class)
    fun makeHttpsRequest(urlString: String): String {
        var jsonResponse = ""
        val url = URL(urlString)
        var connection: HttpURLConnection? = null
        var inputStream: InputStream? = null
        try {
            connection = url.openConnection() as HttpsURLConnection
            connection.requestMethod = "GET"
            connection.setRequestProperty("Accept", "application/json")
            connection.connect()

            if (connection.responseCode == HttpsURLConnection.HTTP_OK) {
                inputStream = connection.inputStream
                jsonResponse = readFromStream(inputStream)
            }
        } catch (e: IOException) {
            Log.e("NetworkUtils", "Retrieving JSON results Error", e)
        } finally {
            connection?.disconnect()
            inputStream?.close()
        }
        return jsonResponse
    }

    @Throws(IOException::class)
    private fun readFromStream(inputStream: InputStream?): String {
        val output = StringBuilder()
        if (inputStream != null) {
            val inputStreamReader = InputStreamReader(inputStream, Charset.forName("UTF-8"))
            val reader = BufferedReader(inputStreamReader)
            var line: String? = reader.readLine()
            while (line != null) {
                output.append(line)
                line = reader.readLine()
            }
        }
        return output.toString()
    }
}