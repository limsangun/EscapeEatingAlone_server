package com.wpjm.escapeeatingalone.Activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.wpjm.escapeeatingalone.Model.MessageModel
import com.wpjm.escapeeatingalone.R
import com.wpjm.escapeeatingalone.databinding.ActivityMessageBinding
import java.util.*

class MessageActivity : AppCompatActivity() {
    private var mBinding: ActivityMessageBinding? = null
    private val binding get() = mBinding!!
    private val user = FirebaseAuth.getInstance().currentUser
    private var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)
        mBinding = ActivityMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 시작주기
//        db.collection("users")
//            .whereEqualTo("uid", "${user!!.getUid()}")
//            .get()
//            .addOnSuccessListener { documents ->
//                for (document in documents) {
//                    for(item in document.data){
//                        Log.e("msg", "${item.value}")
//                    }
//                }
//
//            }
//            .addOnFailureListener { exception ->
//                Log.e("msg", exception.toString())
//            }


        // 전송버튼 눌렀을 때
        binding.messageEdittextSend.setOnClickListener(View.OnClickListener {
            var uid = user?.getUid() // 채팅을 요구하는 아이디
            var destinationUid = user?.getUid() // 채팅을 요구하는 아이디
            var messageModel: MessageModel = MessageModel()
            messageModel.users.put(uid!!, true)
            messageModel.users.put("FnwZg4OeNYNsuQ8bChfniWl32y23", true)   //(destinationUid!!, true)

            db.collection("chatrooms").document(user!!.getUid())
                .set(messageModel) // Firebase Cloud Store 삽입
                .addOnSuccessListener { // 성공할 때
                    var comment : MessageModel.Comment = MessageModel.Comment()
                    comment.uid = uid
                    comment.message = binding.messageEdittextMessage.getText().toString()
                    FirebaseDatabase.getInstance().getReference().child("classrooms")
                        .child("J1yXZ61e80PAk91s9w0D7P2DJyr2").child("comments").push().setValue(comment)
                }
                .addOnFailureListener { // 실패할 때
                    Toast.makeText(this, "회원정보 등록 실패", Toast.LENGTH_SHORT).show()
                    Log.e("msg", "회원정보 등록 실패")
                }

        })
    }

}

