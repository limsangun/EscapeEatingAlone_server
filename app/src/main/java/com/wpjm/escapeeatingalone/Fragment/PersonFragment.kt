package com.wpjm.escapeeatingalone


import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import com.wpjm.escapeeatingalone.Adapter.FragmentPersonAdapter
import com.wpjm.escapeeatingalone.Model.PersonModel
import com.wpjm.escapeeatingalone.databinding.ActivityAddFriendBinding
import com.wpjm.escapeeatingalone.databinding.FragmentPersonBinding


class PersonFragment : Fragment() {
    private var mBinding: FragmentPersonBinding? = null
    private val binding get() = mBinding!!
    private var mBindings: ActivityAddFriendBinding? = null
    private val bindings get() = mBindings!!
    var firestore : FirebaseFirestore? = null
    private var user = FirebaseAuth.getInstance().currentUser
    private var db = FirebaseFirestore.getInstance()
    private val database by lazy { FirebaseDatabase.getInstance() }
    private val mRootRef = FirebaseDatabase.getInstance().getReference()
    private val conditionRef = mRootRef.child("friend")



    lateinit var reyclerView:RecyclerView
    private val list = ArrayList<PersonModel>()
    private val adapter:FragmentPersonAdapter = FragmentPersonAdapter(list)

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentPersonBinding.inflate(inflater, container, false)
        var frgmentPersonView = inflater.inflate(R.layout.fragment_person, container, false)
        var fab = frgmentPersonView.findViewById<Button>(R.id.fab)
        var name=""
        db.collection("users").document(user!!.getUid()).get()
                .addOnSuccessListener { result ->
                    name=result["name"] as String
                    binding.fragmentpersonTextView23.setText(name)
                    frgmentPersonView.findViewById<TextView>(R.id.fragmentperson_textView23).setText(
                            name
                    )
                }
        return frgmentPersonView

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        reyclerView = view.findViewById(R.id.fragmentperson_recyclerView)
        val adapter = FragmentPersonAdapter(list)
        reyclerView.layoutManager = LinearLayoutManager(activity)
        reyclerView.adapter = adapter

        var button = view.findViewById<Button>(R.id.fab)
        button.setOnClickListener {
            var dialogview = LayoutInflater.from(context).inflate(R.layout.activity_add_friend, null)
            var builder = AlertDialog.Builder(context).setView(dialogview)
                    .setTitle("Add Contact")
            var alert = builder.show()
            dialogview.findViewById<Button>(R.id.btn_add).setOnClickListener {
                alert.dismiss()
                val name: String = bindings.dialogNameId.text.toString()
                bindings.dialogNameId.getText().toString()
                val database : FirebaseDatabase = FirebaseDatabase.getInstance()
                val myRef : DatabaseReference = database.getReference("friend")

                myRef.addValueEventListener(object : ValueEventListener {

                    override fun onDataChange(snapshot: DataSnapshot) {

                    }

                    override fun onCancelled(error: DatabaseError) {
                        println("Failed to read value.")
                    }

                })


/*
                db.collection("users")
                        .whereEqualTo("name", name)
                        .get()
                        .addOnSuccessListener { querysnapshot ->
                            if (querysnapshot != null) {
                                for (dc in querysnapshot.documents) {
                                    val name = dc["name"].toString()

                                }
                            }
                        }*/
            }
        }

    }
/*
    private fun addfriend() {

        var dialogview = LayoutInflater.from(context).inflate(
            R.layout.activity_add_friend,
            null
        )
        final String email = editFriendEmail.getText().toString();
        final String myEmail = PreferenceUtil.getString(this, Const.SP_EMAIL);

        // 현재 내 아이디일 경우
        if(email.equals(myEmail)){
            Toast.makeText(AddFriendActivity.this, "내 아이디입니다", Toast.LENGTH_SHORT).show();
            return;
        }

        // 데이터베이스에서 전체 친구 목록 가져와서 입력한 아이디 존재하는지 검사
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                String friendEmail = StringUtil.recoverEmailComma(snapshot.getKey());
                // 존재할 경우 내 데이터베이스에 추가
                if(email.equals(friendEmail)){
                    userRef.child(StringUtil.replaceEmailComma(myEmail)).child("friend_list").child(snapshot.getKey()).setValue(snapshot.getValue());
                    Toast.makeText(AddFriendActivity.this, friendEmail+"님이 추가되었습니다", Toast.LENGTH_SHORT).show();
                    ifExists = true;
                }
            }
                // 친구가 추가되었으면 액티비티 종료
                if(ifExists){
                    finish();
                } else {
                    Toast.makeText(AddFriendActivity.this, "일치하는 아이디가 없습니다", Toast.LENGTH_SHORT).show();
                }
            }

    }*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}