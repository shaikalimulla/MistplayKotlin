package co.android.mistplay.app.games

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import co.android.mistplaykotlin.R
import co.android.mistplay.app.adapters.AdapterRecycler
import co.android.mistplay.app.games.GamesContract.Presenter
import co.android.mistplay.app.models.DataModel
import co.android.mistplay.app.util.DataParserUtil
import java.util.*

class GamesActivity : AppCompatActivity(), GamesContract.View {
    private var presenter: Presenter? = null
    private var recyclerView: RecyclerView? = null
    private var adapterRecycler: AdapterRecycler? = null
    private var isActive = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Start presenter
        presenter = GamesPresenter(this)

        //Initiate Recyclerview
        val layoutManager = LinearLayoutManager(this.applicationContext,
                LinearLayoutManager.VERTICAL, false)
        recyclerView = findViewById(R.id.main_list)
        recyclerView?.layoutManager = layoutManager
        recyclerView?.isNestedScrollingEnabled = true

        //Load json data
        val jsonData = DataParserUtil.loadJSONFromAsset(this.applicationContext)
        jsonData?.let { (presenter as GamesPresenter).getDataModel(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        isActive = false
    }

    //Load recyclerview adapter
    override fun showViews(dataModelList: ArrayList<DataModel>) {
        adapterRecycler = AdapterRecycler(this.applicationContext, dataModelList)
        recyclerView!!.adapter = adapterRecycler
    }

    //Verify activity is alive or destroyed. Return true when activity is alive.
    override fun isActive(): Boolean {
        return isActive
    }
}