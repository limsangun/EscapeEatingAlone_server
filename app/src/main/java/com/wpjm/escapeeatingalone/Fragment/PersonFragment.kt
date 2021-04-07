package com.wpjm.escapeeatingalone

import android.content.Context
import android.os.Bundle
import android.provider.Contacts
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wpjm.escapeeatingalone.Adapter.FragmentPersonAdapter
import com.wpjm.escapeeatingalone.Model.PersonModel
import com.wpjm.escapeeatingalone.databinding.FragmentPersonBinding

class PersonFragment : Fragment() {
    private var mBinding: FragmentPersonBinding? = null
    private val binding get() = mBinding!!

    lateinit var reyclerView:RecyclerView
    private val list = ArrayList<PersonModel>()
    private val adapter:FragmentPersonAdapter = FragmentPersonAdapter(list)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentPersonBinding.inflate(inflater, container, false)
        var frgmentPersonView = inflater.inflate(R.layout.fragment_person, container, false)
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
        list.add(PersonModel(R.drawable.android, "김드로", "3개"))
        list.add(PersonModel(R.drawable.android, "나드로", "3개"))
        list.add(PersonModel(R.drawable.android, "박드로", "3개"))
        list.add(PersonModel(R.drawable.android, "이드로", "3개"))
        list.add(PersonModel(R.drawable.android, "임드로", "3개"))
        list.add(PersonModel(R.drawable.android, "윤드로", "3개"))
        adapter.notifyDataSetChanged()
    }
}