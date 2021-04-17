package com.wpjm.escapeeatingalone.Model

class BoardModel{
    var profile:Int = 0
    var name: String = ""
    var title: String = ""
    var contents: String = ""
    var date: String = ""


    constructor(name: String, title:String, contents:String, date:String){
        this.name = name
        this.title = title
        this.contents = contents
        this.date = date
    }
}
