package com.wpjm.escapeeatingalone.Model

class FriendsList {
    var friendNames:MutableList<String>?=null
    var profileImageUrl:MutableList<String>?=null
    constructor(friendNames: MutableList<String>?) {
        this.friendNames=friendNames
        this.profileImageUrl=profileImageUrl
    }
}