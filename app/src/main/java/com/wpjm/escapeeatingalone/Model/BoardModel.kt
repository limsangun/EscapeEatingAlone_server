package com.wpjm.escapeeatingalone.Model

import android.icu.text.CaseMap

class BoardModel{
    var profile:Int = 0
    var name: String = ""
    var title: String = ""
    var contents: String = ""
    var date: String = ""

    constructor(profile:Int, title:String, contents:String, date:String){
        this.profile = profile
        this.title = title
        this.contents = contents
        this.date = date
    }

    constructor(name: String, title:String, contents:String, date:String){
        this.name = name
        this.title = title
        this.contents = contents
        this.date = date
    }
}
