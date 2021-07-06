package com.target.targetcasestudy

import android.util.Log
import androidx.test.InstrumentationRegistry
import com.google.gson.Gson
import com.target.targetcasestudy.model.Products
import java.io.IOException

/**
 * This class would be used to get JSON data from an asset file in order to fake the response
 * from the service, and prevent the app waiting or reyling on real services.
 */
class TestUtils {
    private fun getProductsListData(filename: String): Products {
        val json = getResponseData("assets/fake_list_response.json")
        val gson = Gson()
        return gson.fromJson(json, Products::class.java)
    }

    private fun getResponseData(filename: String): String? {
        var json: String? = null
        try {
            val inputStream = InstrumentationRegistry.getContext().assets.open(filename)
            val buffer = ByteArray(inputStream.available())
            inputStream.read(buffer)
            inputStream.close()

            json = String(buffer, charset("UTF-8"))
        } catch(e: IOException) {
            Log.e(this::class.java.name, e.toString())
        }
        return json
    }
}