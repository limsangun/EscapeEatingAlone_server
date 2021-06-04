package com.wpjm.escapeeatingalone.Activity

import android.os.Build
import androidx.annotation.RequiresApi
import org.w3c.dom.Document
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import org.w3c.dom.Element
import javax.xml.parsers.DocumentBuilderFactory


@RequiresApi(Build.VERSION_CODES.N)
fun main() {
    //api 정보를 가지고 있는 주소
    var url : String="http://apis.data.go.kr/6260000/BusanTblFnrstrnStusService/getTblFnrstrnStusInfo?serviceKey=SkzUgJzO5Ju61s661QVhT7zHnZghYrBq2kymfg8v46g%2FSFN7VcgiWR3KYtaWyjRvZhfoBRizMSz6%2FiOwK9KOtA%3D%3D&numOfRows=600&pageNo=1"
    //기본적으로 xml형태를 가지게 된다. xml을 파싱하기 위한 코드.
    //만약 본인은 json으로 해야한다! 싶으면 url 끝에 &_type=json 사용. 물론 내 코드에는 안된다.
    val xml : Document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url)

    xml.documentElement.normalize()
    println("Root element : "+xml.documentElement.nodeName)

    //찾고자 하는 데이터가 어느 노드 아래에 있는지 확인
    val list:NodeList=xml.getElementsByTagName("item")

    for(i in 0..list.length-1){
        var n:Node=list.item(i)
        if(n.getNodeType()==Node.ELEMENT_NODE){
            val elem=n as Element
            val map=mutableMapOf<String,String>()

            for(j in 0..elem.attributes.length - 1)
            {
                map.putIfAbsent(elem.attributes.item(j).nodeName, elem.attributes.item(j).nodeValue)
            }
            //list.length-1 (건축물 대장의 경우 디폴트 = 10)만큼 얻고자 하는 태그의 정보를 가져온다
            println("가게이름 : ${elem.getElementsByTagName("bsnsNm").item(0).textContent}" + "         " + "음식형태 : ${elem.getElementsByTagName("bsnsCond").item(0).textContent}")
        }
    }
}