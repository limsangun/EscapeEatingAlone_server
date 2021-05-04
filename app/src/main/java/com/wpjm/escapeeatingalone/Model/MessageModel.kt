package com.wpjm.escapeeatingalone.Model

import android.text.Editable

class MessageModel {
//    var profile:Int = 0
    var name: String = ""
    var contents: String = ""
    var timeStamp: String = ""

    constructor(name: String, contents: String, timeStamp: String) {
//        this.profile = profile
        this.name = name
        this.contents = contents
        this.timeStamp = timeStamp
    }
}
