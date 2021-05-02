package com.wpjm.escapeeatingalone.Model

class MemberInfo {
    var profileImageUrl:String?=null
    var name:String? = null
    var phoneNumber:String? = null
    var birthDay:String? = null
    var address:String? = null
    var uid:String? = null

    constructor(profileImageUrl:String?,name: String?, phoneNumber: String?, birthDay: String?, address: String?) {
        this.profileImageUrl=profileImageUrl
        this.name = name
        this.phoneNumber = phoneNumber
        this.birthDay = birthDay
        this.address = address
    }

}