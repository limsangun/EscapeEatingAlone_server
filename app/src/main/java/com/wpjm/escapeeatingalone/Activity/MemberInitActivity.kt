package com.wpjm.escapeeatingalone.Activity

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.wpjm.escapeeatingalone.Model.MemberInfo
import com.wpjm.escapeeatingalone.R
import com.wpjm.escapeeatingalone.databinding.ActivityMemberInitBinding
import java.util.*


class MemberInitActivity: AppCompatActivity() {
    private var mBinding: ActivityMemberInitBinding? = null
    private val binding get() = mBinding!!
    private var user = FirebaseAuth.getInstance().currentUser
    private var db = FirebaseFirestore.getInstance()
    val itemList = arrayListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_init)
        mBinding = ActivityMemberInitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val docRef: DocumentReference = db.collection("users").document(user!!.getUid())
        docRef.get().addOnCompleteListener { task ->

            if (task.isSuccessful) {
                val document = task.result
                if (document != null){
                    if (document!!.exists()) { // 개인정보가 존재하면
                        db.collection("users").get()
                            .addOnSuccessListener { result ->
                                itemList.clear()
                                for (document in result) {  // 가져온 문서들은 result에 들어감
                                    val item =document["name"] as String
                                    itemList.add(item)
                                    Log.d("ItemNames","${itemList}")
                                }


                            }
                    } else { // 개인정보가 존재하지 않으면

                    }
                }
            } else {
                Log.e("msg", "에러")
            }
        }
        var name = binding.memberinitActivityEdittextName.getText().toString()
        var phoneNumber = binding.memberinitActivityEdittextPhonenumber.getText().toString()
        var birthDay = binding.memberinitActivityEdittextBirthday.getText().toString()
        var address = binding.memberinitActivityEdittextAddress.getText().toString()

        //프로필 사진 설정
        binding.selectphotoButtonRegister.setOnClickListener{
            val intent=Intent(Intent.ACTION_PICK)
            intent.type="image/*"
            startActivityForResult(intent,0)
        }
        // 확인 버튼 눌렀을 때
        binding.memberinitActivityButtonCheck.setOnClickListener(View.OnClickListener {
            uploadImageToFirebaseStorage()

        })
    }
    var selectedPhotoUri: Uri? =null

    private fun saveUserToFirebaseDatabase(profileImageUrl: String,name:String,phoneNumber:String,birthDay:String,address:String) {

        if (name.length > 0 && name !in itemList &&
            phoneNumber.length > 0 &&
            birthDay.length > 0 &&
            address.length > 0 ) {


            // member
            Log.d("ImageTest","DDDDDDDDDDDD:${profileImageUrl}")
            var memberInfo = MemberInfo(profileImageUrl, name, phoneNumber, birthDay, address)


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

        } else {
            if (name in itemList ){
                Toast.makeText(this, "닉네임이 중복되었습니다.", Toast.LENGTH_SHORT).show()
            }

            else {Toast.makeText(this, "회원정보를 입력해주세요", Toast.LENGTH_SHORT).show()
                Log.e("msg", "회원정보를 입력해주세요")
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode==0 && resultCode== Activity.RESULT_OK && data != null){
            Log.d("RegisterActivity","Photo was selected")
            selectedPhotoUri = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)
            val bitmapDrawable= BitmapDrawable(bitmap)
            binding.selectphotoImageviewRegister.setImageBitmap(bitmap)

            binding.selectphotoButtonRegister.alpha = 0f
        }

    }

    private fun uploadImageToFirebaseStorage(){
        if (selectedPhotoUri==null) {
            saveUserToFirebaseDatabase("",binding.memberinitActivityEdittextName.getText().toString(),
                binding.memberinitActivityEdittextPhonenumber.getText().toString(),
                binding.memberinitActivityEdittextBirthday.getText().toString(),
                binding.memberinitActivityEdittextAddress.getText().toString())
            return
        }
        val filename= UUID.randomUUID().toString()
        val ref=FirebaseStorage.getInstance().getReference("/images/$filename")

        ref.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                Log.d("RegisterActivity","Successfully upload image: ${it.metadata?.path}")
                ref.downloadUrl.addOnSuccessListener {
                    it.toString()
                    Log.d("RegisterActivity","File Location: $it")
                    saveUserToFirebaseDatabase(it.toString(),binding.memberinitActivityEdittextName.getText().toString(),
                        binding.memberinitActivityEdittextPhonenumber.getText().toString(),
                        binding.memberinitActivityEdittextBirthday.getText().toString(),
                        binding.memberinitActivityEdittextAddress.getText().toString())

                }
            }
            .addOnFailureListener{
                Log.d("RegisterActivity","Failed to upload image to storage: ${it.message}")
            }

    }



    // 뒤로가기 버튼 눌렀을 때
    override fun onBackPressed() {
        super.onBackPressed()
        finish() // 앱 종료
    }

}