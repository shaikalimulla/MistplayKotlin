package co.android.mistplay.app.games

import co.android.mistplay.app.models.DataModel
import java.util.*

interface GamesContract {
    //To handle view
    interface View {
        fun isActive(): Boolean
        fun showViews(dataModelList: ArrayList<DataModel>)
    }

    //Presenter to handle business logic
    interface Presenter {
        fun getDataModel(json: String)
    }
}