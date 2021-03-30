package com.wpjm.escapeeatingalone.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.wpjm.escapeeatingalone.R
import com.wpjm.escapeeatingalone.databinding.ActivityMemberInitBinding


class MemberInitActivity: AppCompatActivity() {
    private var mBinding: ActivityMemberInitBinding? = null
    private val binding get() = mBinding!!
    private var user = FirebaseAuth.getInstance().currentUser
    private var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_init)
        mBinding = ActivityMemberInitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var name = binding.memberinitActivityEdittextName.getText().toString()
        var phoneNumber = binding.memberinitActivityEdittextPhonenumber.getText().toString()
        var birthDay = binding.memberinitActivityEdittextBirthday.getText().toString()
        var address = binding.memberinitActivityEdittextAddress.getText().toString()

        // 확인 버튼 눌렀을 때
        binding.memberinitActivityButtonCheck.setOnClickListener(View.OnClickListener {
            if (binding.memberinitActivityEdittextName.getText().toString().length > 0 &&
                binding.memberinitActivityEdittextPhonenumber.getText().toString().length > 0 &&
                binding.memberinitActivityEdittextBirthday.getText().toString().length > 0 &&
                binding.memberinitActivityEdittextAddress.getText().toString().length > 0) {

                // meber
                var memberInfo = MemberInfo(binding.memberinitActivityEdittextName.getText().toString(),
                                            binding.memberinitActivityEdittextPhonenumber.getText().toString(),
                                            binding.memberinitActivityEdittextBirthday.getText().toString(),
                                            binding.memberinitActivityEdittextAddress.getText().toString() )

                if (user != null) { // Firebase Cloud Store에 user 정보가 없으면
                    db.collection("users").document(user!!.getUid()).set(memberInfo) // Firebase Cloud Store 삽입
                        .addOnSuccessListener { // 성공할 때
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            Toast.makeText(this, "회원정보 등록 성공", Toast.LENGTH_SHORT).show()
                            Log.e("msg", "회원정보 등록 성공")
                            finish()
                        }
                        .addOnFailureListener { // 실패할 때
                            Toast.makeText(this, "회원정보 등록 실패", Toast.LENGTH_SHORT).show()
                            Log.e("msg", "회원정보 등록 실패")
                        }
                } else { // Firebase Cloud Store에 user 정보가 있으면
                    Log.e("msg", "유저가 존재함")
                }
            } else {
                Toast.makeText(this, "회원정보를 입력해주세요", Toast.LENGTH_SHORT).show()
                Log.e("msg", "회원정보를 입력해주세요")
            }
        })
    }

    // 뒤로가기 버튼 눌렀을 때
    override fun onBackPressed() {
        super.onBackPressed()
        finish() // 앱 종료
    }
    
}