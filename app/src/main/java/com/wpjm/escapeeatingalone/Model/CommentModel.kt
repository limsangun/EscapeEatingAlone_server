package com.wpjm.escapeeatingalone.Model

class CommentModel {
    var profile: Int = 0
    var name: String = ""
    var contents: String = ""
    var timestamp: String = ""
    var boardTimeStamp: String =""

    constructor(profile: Int, name: String, contents: String, timestamp: String, boardTimeStamp: String) {
        this.profile = profile
        this.name = name
        this.contents = contents
        this.timestamp = timestamp
        this.boardTimeStamp = boardTimeStamp
    }

    constructor(name: String, contents: String, timestamp: String, boardTimeStamp: String) {
        this.name = name
        this.contents = contents
        this.timestamp = timestamp
        this.boardTimeStamp = boardTimeStamp
    }
}