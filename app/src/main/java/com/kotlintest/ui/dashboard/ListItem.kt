package com.kotlintest.ui.dashboard

import org.json.JSONException
import org.json.JSONObject


class ListItem(
    val title: String,
    val description: String,
    val imageUrl: String,
    val instructionUrl: String,
    val label: String) {

  companion object {

    fun getListItemsFromFile(filename: String, context: DashboardFragment): ArrayList<ListItem> {
      val listItemList = ArrayList<ListItem>()

      try {
        // Load data
        val jsonString = loadJsonFromAsset("listitem.json", context)
        val json = JSONObject(jsonString)
        val listItems = json.getJSONArray("listItems")

        // Get Recipe objects from data
        (0 until listItems.length()).mapTo(listItemList) {
            ListItem(listItems.getJSONObject(it).getString("title"),
              listItems.getJSONObject(it).getString("description"),
              listItems.getJSONObject(it).getString("image"),
              listItems.getJSONObject(it).getString("url"),
              listItems.getJSONObject(it).getString("dietLabel"))
        }
      } catch (e: JSONException) {
        e.printStackTrace()
      }

      return listItemList
    }

    private fun loadJsonFromAsset(filename: String, context: DashboardFragment): String? {
      var json: String? = null

      try {
        val inputStream = context.getResources().getAssets().open(filename)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        json = String(buffer, Charsets.UTF_8)
      } catch (ex: java.io.IOException) {
        ex.printStackTrace()
        return null
      }

      return json
    }
  }
}