package dev.milic.catagenttracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import dev.milic.catagenttracker.worker.CatFurGroomingWorker
import dev.milic.catagenttracker.worker.CatLitterBoxSittingWorker
import dev.milic.catagenttracker.worker.CatStretchingWorker

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val networkConstraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED).build()

        val catAgentId = "CatAgent1"

        val catStretchingRequest = OneTimeWorkRequest.Builder(CatLitterBoxSittingWorker::class.java)
                .setConstraints(networkConstraints)
                .setInputData(getCatAgentIdInputData(CatStretchingWorker.INPUT_DATA_CAT_AGENT_ID, catAgentId))
                .build()

        val catFurGroomingRequest = OneTimeWorkRequest.Builder(CatFurGroomingWorker::class.java)
                .setConstraints(networkConstraints)
                .setInputData(getCatAgentIdInputData(CatFurGroomingWorker.INPUT_DATA_CAT_AGENT_ID, catAgentId))
                .build()
    }

    private fun getCatAgentIdInputData(catAgentIdKey: String, catAgentIdValue: String) =
            Data.Builder().putString(catAgentIdKey, catAgentIdValue).build()
}