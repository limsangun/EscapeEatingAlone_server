package com.wpjm.escapeeatingalone.Activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.wpjm.escapeeatingalone.Model.MenuDetailModel
import com.wpjm.escapeeatingalone.R
import com.wpjm.escapeeatingalone.databinding.ActivityMenuDetailBinding
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.xml.sax.SAXException
import java.io.IOException
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException

class MenuDetailActivity : AppCompatActivity() {
    private var mBinding: ActivityMenuDetailBinding? = null
    private val binding get() = mBinding!!

    var empDataHashMap = HashMap<String, String>()
    var menuDetailList: ArrayList<HashMap<String, String>> = ArrayList()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_detail)
        mBinding = ActivityMenuDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            val istream = assets.open("test1.xml")
            val builderFactory = DocumentBuilderFactory.newInstance()
            val docBuilder = builderFactory.newDocumentBuilder()
            val doc = docBuilder.parse(istream)
            val nList = doc.getElementsByTagName("item")
            var elem : Element? = null
            var menuDetailList : ArrayList<MenuDetailModel> = arrayListOf<MenuDetailModel>()
            //reading the tag "item" of test1.xml file
//            val nList = doc.getElementsByTagName("item")

            for (i in 0 until nList.length-1) {
                val n:Node=nList.item(i)
                if (n.getNodeType()==Node.ELEMENT_NODE) {
                    elem=n as Element
                    val map = mutableMapOf<String, String>()

                    for(j in 0..elem.attributes.length-1) {
                        map.putIfAbsent(elem.attributes.item(j).nodeName, elem.attributes.item(j).nodeValue)
                    }
                    menuDetailList.add(MenuDetailModel(R.drawable.rice, elem.getElementsByTagName("bsnsNm").item(0).textContent, elem.getElementsByTagName("bsnsCond").item(0).textContent))
                    Log.e("test", menuDetailList[i].name)


                    val filtedMenuDetailList = menuDetailList.filter {
                        it.type.equals(intent.getStringExtra("MenuType").toString())
                    }
                    binding.meniDetailActivityRecyclerView.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
                    binding.meniDetailActivityRecyclerView.setHasFixedSize(true)
                    binding.meniDetailActivityRecyclerView.adapter=MenuDetailAdapter(filtedMenuDetailList as ArrayList<MenuDetailModel>)
                }
            }

        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: ParserConfigurationException) {
            e.printStackTrace()
        } catch (e: SAXException) {
            e.printStackTrace()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}