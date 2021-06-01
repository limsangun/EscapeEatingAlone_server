package com.wpjm.escapeeatingalone.Model

import java.io.Serializable

class MenuList(val name: String?,      // 장소명
                 val road: String?,      // 도로명 주소
                 val address: String?,   // 지번 주소
                 val menuType: String?) :Serializable
