package com.example.d8765.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.d8765.R
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.json.JSONObject

class DashboardFragment : Fragment() {


   var liveData : MutableLiveData<JSONObject> = MutableLiveData<JSONObject>()


    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = dashtetx;
        })
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getQuery();
    }

    fun getQuery() {

        val queue = Volley.newRequestQueue(this.context)
        val url: String =  "http://192.168.0.12:8080/dist/webid/data/User/Ivan.Malinin/home/disk%20D/andr-php-mysql/app/apk_book.php?q=134"
        var resp:String ="";
        val stringReq = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                resp = response.toString();
                var strResp = response.toString()
                val jsonObj: JSONObject = JSONObject(strResp)
//                var title:String =  jsonObj.get("title").toString()
//                text_home.setText(title)
                liveData.postValue(jsonObj)
                text_dashboard.text= response.toString()
            },
            Response.ErrorListener {
                resp = ""
            })
        queue.add(stringReq)
    }


}
