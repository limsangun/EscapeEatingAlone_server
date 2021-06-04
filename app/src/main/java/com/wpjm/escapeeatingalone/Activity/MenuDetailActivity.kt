package com.wpjm.escapeeatingalone.Activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

//            val menuDetailList = arrayListOf(MenuDetailModel(R.drawable.rice, elem?.getElementsByTagName("bsnsNm")?.item(0)!!.textContent, elem?.getElementsByTagName("bsnsCond")?.item(0)!!.textContent))
//            Log.e("test2", menuDetailList[0].name)
//
//            val filtedMenuDetailList = menuDetailList.filter {
//                it.type.equals(intent.getStringExtra("MenuType").toString())
//            }
//            binding.meniDetailActivityRecyclerView.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
//            binding.meniDetailActivityRecyclerView.setHasFixedSize(true)
//            binding.meniDetailActivityRecyclerView.adapter=MenuDetailAdapter(filtedMenuDetailList as ArrayList<MenuDetailModel>)

        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: ParserConfigurationException) {
            e.printStackTrace()
        } catch (e: SAXException) {
            e.printStackTrace()
        }



//        val url: String =
//            "http://apis.data.go.kr/6260000/BusanTblFnrstrnStusService/getTblFnrstrnStusInfo?serviceKey=SkzUgJzO5Ju61s661QVhT7zHnZghYrBq2kymfg8v46g%2FSFN7VcgiWR3KYtaWyjRvZhfoBRizMSz6%2FiOwK9KOtA%3D%3D&numOfRows=600&pageNo=1"
//        val xml: Document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url)
//        xml.documentElement.normalize()
//        val list: NodeList = xml.getElementsByTagName("item")
//
//        for (i in 0..list.length - 1) {
//            var n: Node = list.item(i)
//            if (n.getNodeType() == Node.ELEMENT_NODE) {
//                val elem = n as Element
//                val map = mutableMapOf<String, String>()
//
//                for (j in 0..elem.attributes.length - 1) {
//                    map.putIfAbsent(
//                        elem.attributes.item(j).nodeName,
//                        elem.attributes.item(j).nodeValue
//                    )
//                }
//
//                var menuDetailList = arrayListOf(
//                    MenuDetailModel(R.drawable.rice, elem.getElementsByTagName("bsnsNm").item(0).textContent, elem.getElementsByTagName("bsnsCond").item(0).textContent
//                    )
//                )
//
//                var filtedMenuDetailList = menuDetailList.filter {
//                    it.type.equals(intent.getStringExtra("MenuType").toString())
//                }
//
//                binding.meniDetailActivityRecyclerView.layoutManager =
//                    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//                binding.meniDetailActivityRecyclerView.setHasFixedSize(true)
//                binding.meniDetailActivityRecyclerView.adapter =
//                    MenuDetailAdapter(filtedMenuDetailList as ArrayList<MenuDetailModel>)
//            }
//        }


//        var menuDetailList= arrayListOf(
//            MenuDetailModel(R.drawable.rice, "우쭈쭈", "경성대 쭈꾸미집","한식"),
//            MenuDetailModel(R.drawable.rice, "우쭈쭈", "경성대 쭈꾸미집","한식"),
//            MenuDetailModel(R.drawable.rice, "미진축산", "경성대 삼겹살집", "한식"),
//            MenuDetailModel(R.drawable.rice, "영진돼지국밥", "경성대 돼지국밥", "한식"),
//            MenuDetailModel(R.drawable.rice, "차이나타운", "경성대 중국집", "중식"),
//            MenuDetailModel(R.drawable.rice, "겐로쿠", "경성대 우동", "일식"),
//            MenuDetailModel(R.drawable.rice, "노랑통닭", "경성대 치킨", "치킨"),
//            MenuDetailModel(R.drawable.rice, "미스터피자", "경성대 피자", "피자"),
//            MenuDetailModel(R.drawable.rice, "멕시카나", "경성대 치킨", "치킨"),
//            MenuDetailModel(R.drawable.rice, "보리밭", "경성대 밥골", "한식"),
//            MenuDetailModel(R.drawable.rice, "청년다방", "경성대 분식", "분식"),
//            MenuDetailModel(R.drawable.rice, "맥도날드", "경성대 맥도널드", "패스트푸드"),
//            MenuDetailModel(R.drawable.rice, "스타벅스", "경성대 스타벅스", "카페"),
//            MenuDetailModel(R.drawable.rice, "동대문엽기떡볶이", "경성대 엽떡", "패스트푸드")
//        )
//
//        var filtedMenuDetailList = menuDetailList.filter {
//            it.type.equals(intent.getStringExtra("MenuType").toString())
//        }
//
//        binding.meniDetailActivityRecyclerView.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
//        binding.meniDetailActivityRecyclerView.setHasFixedSize(true)
//        binding.meniDetailActivityRecyclerView.adapter=MenuDetailAdapter(filtedMenuDetailList as ArrayList<MenuDetailModel>)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}