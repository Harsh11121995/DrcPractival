package com.example.drcpract

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drcpract.RoomDb.DatabaseClient
import com.example.drcpract.RoomDb.Task
import com.example.drcpract.api.ApiClient
import com.example.drcpract.api.ApiInterface
import com.example.drcpract.databinding.ActivityMainBinding
import com.example.drcpract.model.Results
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.File

class MainActivity : AppCompatActivity(), OnItemClickListenerData {

    companion object {
        val taskListArray = ArrayList<Task>()
    }

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    //    val taskListArray = ArrayList<Task>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (doesDatabaseExist(this, "EmployeeData")) {
            Log.e("Database : ", "True")
            getData()
        } else {
            Log.e("Database : ", "False")
            fetchData()
        }
    }

    @SuppressLint("CheckResult")
    private fun fetchData() {
        try {
            val apiInterface = ApiClient.client?.create(ApiInterface::class.java)
            apiInterface?.getData(
                "AIzaSyBSNyp6GQnnKlrMr7hD2HGiyF365tFlK5U",
                "23.03744,72.566",
                "distance",
                "bakery",
                "true"
            )!!
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    try {
                        if (result != null) {
                            saveData(result.results)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }, { error ->
                    try {
                        error.printStackTrace()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun saveData(arraylist: ArrayList<Results>) {

        var arrayList = ArrayList<Task>()
        Thread {

            for (i in 0 until arraylist.size) {
                val task = Task()
                task.name = arraylist[i].name
                task.lat = arraylist[i].geometry!!.location!!.lat.toString()
                task.lng = arraylist[i].geometry!!.location!!.lng.toString()
                task.vicinity = arraylist[i].vicinity
                arrayList.add(task)
            }
            //adding to database
            var respone = DatabaseClient.getInstance(applicationContext)?.appDatabase!!
                .taskDao()!!
                .insertAllData(arrayList)

            Log.e("Response : ", respone[0].toString())

            getData()

        }.start()

    }

    private fun getData() {
        Thread {
            val taskList = DatabaseClient
                .getInstance(applicationContext)!!
                .appDatabase!!
                .taskDao()!!
                .all

            if (taskList.size > 0) {
                Log.e("taskList==>", taskList[0].name!!)
                taskListArray.clear()
                taskListArray.addAll(taskList)
            }

            runOnUiThread {
                // Stuff that updates the UI
                binding.rcvData.layoutManager = LinearLayoutManager(this)
                val mAdapterList = UserAdapter(this, taskListArray, this)
                binding.rcvData.adapter = mAdapterList
            }
        }.start()
    }

    private fun doesDatabaseExist(context: Context, dbName: String): Boolean {
        val dbFile: File = context.getDatabasePath(dbName)
        return dbFile.exists()
    }

    override fun onItemClick(view: View?, position: Int) {
        val intent = Intent(this,MapsActivity::class.java)
        intent.putExtra("lat",taskListArray[position].lat)
        intent.putExtra("lng",taskListArray[position].lng)
        intent.putExtra("title",taskListArray[position].name)
        startActivity(intent)
    }

}