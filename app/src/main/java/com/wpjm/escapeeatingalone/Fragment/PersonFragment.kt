package com.wpjm.escapeeatingalone

import android.os.Bundle


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.wpjm.escapeeatingalone.Adapter.FragmentPersonAdapter
import com.wpjm.escapeeatingalone.Model.PersonModel
import com.wpjm.escapeeatingalone.databinding.FragmentPersonBinding

class PersonFragment : Fragment() {
    private var mBinding: FragmentPersonBinding? = null
    private val binding get() = mBinding!!
    var firestore : FirebaseFirestore? = null
    private var user = FirebaseAuth.getInstance().currentUser
    private var db = FirebaseFirestore.getInstance()


    lateinit var reyclerView:RecyclerView
    private val list = ArrayList<PersonModel>()
    private val adapter:FragmentPersonAdapter = FragmentPersonAdapter(list)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentPersonBinding.inflate(inflater, container, false)
        var frgmentPersonView = inflater.inflate(R.layout.fragment_person, container, false)
        var name=""
        db.collection("users").document(user!!.getUid()).get()
                .addOnSuccessListener { result ->
                    name=result["name"] as String
                    binding.fragmentpersonTextView23.setText(name)
                    frgmentPersonView.findViewById<TextView>(R.id.fragmentperson_textView23).setText(name)
                }
        return frgmentPersonView

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        reyclerView = view.findViewById(R.id.fragmentperson_recyclerView)
        val adapter = FragmentPersonAdapter(list)
        reyclerView.layoutManager = LinearLayoutManager(activity)
        reyclerView.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}