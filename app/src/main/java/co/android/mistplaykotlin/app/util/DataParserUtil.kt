package co.android.mistplay.app.util

import android.content.Context
import co.android.mistplay.app.models.ImageModel
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.util.*

object DataParserUtil {
    //Parse json data
    fun parse(jsonStr: String): LinkedHashMap<String, ArrayList<ImageModel>> {
        val resultMap = LinkedHashMap<String, ArrayList<ImageModel>>()
        var gameList = ArrayList<ImageModel>()
        try {
            val jsonArray = JSONArray(jsonStr)
            for (i in 0 until jsonArray.length()) {
                val listObject = jsonArray.getJSONObject(i)
                //Fetch list Title
                var listTitle = listObject.getString("list_title")
                //Remove \r\n or white spaces
                listTitle = listTitle.trim { it <= ' ' }.replace("\\s{2,}".toRegex(), " ")
                val games = listObject["games"] as JSONArray
                for (j in 0 until games.length()) {
                    val gameObject = games.getJSONObject(j)
                    //Fetch image Title
                    var gameTitle = gameObject.getString("title")
                    //Remove \r\n or white spaces
                    gameTitle = gameTitle.trim { it <= ' ' }.replace("\\s{2,}".toRegex(), " ")
                    //Fetch image url
                    var imageUrl = gameObject.getString("img")
                    //Remove \r\n or white spaces
                    imageUrl = imageUrl.trim { it <= ' ' }.replace("\\s".toRegex(), "")
                    val imageModel = ImageModel(imageUrl, gameTitle)
                    gameList.add(imageModel)
                }
                //Store data in map
                resultMap[listTitle] = gameList
                //Reset gameList
                gameList = ArrayList()
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return resultMap
    }

    fun loadJSONFromAsset(context: Context): String? {
        var json: String? = null
        json = try {
            //Open JSON file from Assets folder
            val file = context.assets.open("lists.json")
            val size = file.available()
            val buffer = ByteArray(size)
            file.read(buffer)
            file.close()
            String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return json
        }
        return json
    }
}