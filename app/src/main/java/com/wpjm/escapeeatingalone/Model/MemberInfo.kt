package com.wpjm.escapeeatingalone.Model

class MemberInfo {
    var name:String? = null
    var phoneNumber:String? = null
    var birthDay:String? = null
    var address:String? = null
    var uid:String? = null

    constructor(name: String?, phoneNumber: String?, birthDay: String?, address: String?) {
        this.name = name
        this.phoneNumber = phoneNumber
        this.birthDay = birthDay
        this.address = address
    }

}