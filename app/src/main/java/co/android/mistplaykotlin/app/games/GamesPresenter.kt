package co.android.mistplay.app.games

import co.android.mistplay.app.games.GamesContract.Presenter
import co.android.mistplay.app.models.DataModel
import co.android.mistplay.app.models.ImageModel
import co.android.mistplay.app.util.DataParserUtil
import java.util.*

class GamesPresenter(view: GamesContract.View) : Presenter {
    private val view: GamesContract.View = view
    private val dataModelList: ArrayList<DataModel> = ArrayList()

    //Parse data
    override fun getDataModel(json: String) {
        val data = DataParserUtil.parse(json)
        for ((key, value) in data) {
            dataModelList.add(DataModel(key, (value as ArrayList<ImageModel>)))
        }

        //Show Views
        if (view.isActive()) {
            view.showViews(dataModelList)
        }
    }

    //Initialize the presenter
    init {

    }
}