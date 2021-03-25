package com.wpjm.escapeeatingalone

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.wpjm.escapeeatingalone.Model.UserModel
import com.wpjm.escapeeatingalone.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private var mBinding: ActivitySignupBinding? = null
    private val binding get() = mBinding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        mBinding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance();

        // 회원가입 버튼 누룰 때
        binding.signupActivityButtonSignup.setOnClickListener(View.OnClickListener {
            if (binding.signupActivityEdittextEmail.length() > 0 && binding.signupActivityEdittextPassword.length() > 0 && binding.signupActivityEdittextPassword2.length() > 0 && binding.signupActivityEdittextName.length() > 0) {
                if(binding.signupActivityEdittextPassword.getText().toString().equals(binding.signupActivityEdittextPassword2.getText().toString())){
                    FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(
                            binding.signupActivityEdittextEmail.getText().toString(),
                            binding.signupActivityEdittextPassword.getText().toString()
                        )
                        .addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
                            @NonNull
                            if (task.isSuccessful) { // 회원가입 성공
                                var userModel:UserModel? = null
                                val user = auth.currentUser

//                                userModel.userName = binding.signupActivityEdittextName.getText().toString()

                                val intent = Intent(this, LoginActivity::class.java)
                                Toast.makeText(this, "회원가입 성공하였습니다.", Toast.LENGTH_SHORT).show()
                                startActivity(intent)
                                finish()
                            } else { // 회원가입 실패
                                Toast.makeText(this, "회원가입 실패하였습니다. 다시 시도해주세요", Toast.LENGTH_SHORT).show()
                            }
                        });
                }else{
                    Toast.makeText(this, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT)
                    Log.e("msg", "비밀번호가 일치하지 않습니다")
                }
            }else{
                Toast.makeText(this, "다시 한번 확인바랍니다", Toast.LENGTH_SHORT)
                Log.e("msg", "다시 한번 확인바랍니다")
            }
        })
    }
}