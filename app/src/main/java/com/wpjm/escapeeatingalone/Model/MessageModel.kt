package com.wpjm.escapeeatingalone.Model

class MessageModel {
    var users = hashMapOf<String, Boolean>() // 채팅방 유저들
    var comments = hashMapOf<String, Boolean>() // 채팅방 내용

    class Comment {
        var uid:String = ""
        var message:String = ""
    }
}
