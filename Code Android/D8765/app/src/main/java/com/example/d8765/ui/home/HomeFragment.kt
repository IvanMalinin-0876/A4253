package com.example.d8765.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.d8765.R
import kotlinx.android.synthetic.main.fragment_home.*
import org.json.JSONObject

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {

        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getQuery();

    }


    fun getQuery() {

        val queue = Volley.newRequestQueue(this.context)
        val url: String =  "http://192.168.0.12:8080/dist/webid/data/User/Ivan.Malinin/home/disk%20D/andr-php-mysql/app/apk_query.php?q=134"
        var resp:String ="";
        val stringReq = StringRequest(Request.Method.GET, url,
            Response.Listener<String> { response ->
                resp = response.toString();
                var strResp = response.toString()
                val jsonObj: JSONObject = JSONObject(strResp)
                var title:String =  jsonObj.get("title").toString()
                text_home.setText(title)

            },
            Response.ErrorListener {
                resp = ""
            })
        queue.add(stringReq)
    }

}
