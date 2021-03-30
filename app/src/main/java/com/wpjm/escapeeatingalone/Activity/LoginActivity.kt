package com.wpjm.escapeeatingalone.Activity

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
import com.wpjm.escapeeatingalone.R
import com.wpjm.escapeeatingalone.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private var mBinding: ActivityLoginBinding? = null
    private val binding get() = mBinding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()


        // 회원가입 버튼 누룰 때
        binding.loginActivityButtonSignup.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        })

        // 로그인 버튼 누를 때
        binding.loginActivityButtonLogin.setOnClickListener(View.OnClickListener {

            if (binding.loginActivityEdittextEmail.length() > 0 && binding.loginActivityEdittextEmail.length() > 0) {
                auth.signInWithEmailAndPassword(
                    binding.loginActivityEdittextEmail.getText().toString(),
                    binding.loginActivityEdittextPassword.getText().toString()
                )
                    .addOnCompleteListener(this, OnCompleteListener<AuthResult> {  task ->
                        @NonNull
                        if(task.isSuccessful){
                            val intent1 = Intent(this, MainActivity::class.java)
                            startActivity(intent1)
                            Log.e("msg", "로그인 성공")
                            Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                            finish()
                        } else {
                            Log.e("msg", "로그인 실패")
                            Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
                        }
                    })
            }

        })
    }
}