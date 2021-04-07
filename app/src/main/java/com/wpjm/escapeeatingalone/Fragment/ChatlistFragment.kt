package com.wpjm.escapeeatingalone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wpjm.escapeeatingalone.Adapter.FragmentChatlistAdapter
import com.wpjm.escapeeatingalone.Model.ChatlistModel
import com.wpjm.escapeeatingalone.databinding.FragmentChatBinding

class ChatlistFragment : Fragment() {
    private var mBinding: FragmentChatBinding? = null
    private val binding get() = mBinding!!

    lateinit var reyclerView: RecyclerView
    private val list = ArrayList<ChatlistModel>()
    private val adapter: FragmentChatlistAdapter = FragmentChatlistAdapter(list)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentChatBinding.inflate(inflater, container, false)
        var frgmentChatlistView = inflater.inflate(R.layout.fragment_chat, container, false)
        return frgmentChatlistView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        reyclerView = view.findViewById(R.id.fragmentchat_recyclerView)
        val adapter = FragmentChatlistAdapter(list)
        reyclerView.layoutManager = LinearLayoutManager(activity)
        reyclerView.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        list.add(ChatlistModel(R.drawable.user, "고기", "우프진먹 외 3명", "11/11"))
        list.add(ChatlistModel(R.drawable.user, "일식", "3개", "11/11"))
        list.add(ChatlistModel(R.drawable.user, "중식", "5개", "11/11"))
        list.add(ChatlistModel(R.drawable.user, "양식", "1개", "11/11"))
        list.add(ChatlistModel(R.drawable.user, "분식", "7개", "11/11"))
        list.add(ChatlistModel(R.drawable.user, "십식", "2개", "11/11"))
        adapter.notifyDataSetChanged()
    }
}