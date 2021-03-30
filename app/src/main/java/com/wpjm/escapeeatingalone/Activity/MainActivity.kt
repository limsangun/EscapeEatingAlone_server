package com.wpjm.escapeeatingalone.Activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.wpjm.escapeeatingalone.R
import com.wpjm.escapeeatingalone.databinding.ActivityMainBinding
import com.wpjm.escapeeatingalone.Activity.ChattingActivity as ChattingActivity


class MainActivity : AppCompatActivity() {
    private var mBinding: ActivityMainBinding? = null
    private val binding get() = mBinding!!
    private val user = FirebaseAuth.getInstance().currentUser
    private var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 기본 시작 주기
        if (user == null) { // 파이어베이스 유저가 존재하지 않으면
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        } else { // 파이어베이스 유저가 존재하면
            val docRef: DocumentReference = db.collection("users").document(user.getUid())
            docRef.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val document = task.result
                    if (document != null){
                        if (document!!.exists()) { // 개인정보가 존재하면
                            // cloud firestore로부터 이름 읽어오기
                            Log.e("name", "${document.data}")
                            binding.mainActivityTextviewName.setText(document.id)

                        } else { // 개인정보가 존재하지 않으면
                            val intent1 = Intent(this, MemberInitActivity::class.java)
                            startActivity(intent1)
                            finish()
                        }
                    }
                } else {
                    Log.e("msg", "에러")
                }
            }

        }

        // 로그아웃 버튼 눌렀을 때
        binding.mainActivityButtonLogout.setOnClickListener(View.OnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent2 = Intent(this, LoginActivity::class.java)
            startActivity(intent2)
            finish()
        })

        // 채팅하기 버튼 눌렀을 때
        binding.mainActivityButtonChatting.setOnClickListener(View.OnClickListener {
            var intent4 = Intent(this, ChattingActivity::class.java)
            startActivity(intent4)
            finish()
        })

    }

    // Intent
    private fun gotoActivity(c:Activity) {
        var intent3 = Intent(this, c::class.java)
        startActivity(intent3)
        finish()
    }


}
