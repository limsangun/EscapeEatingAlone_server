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
        /*
        list.add(ChatlistModel(R.drawable.user, "원조경성삼겹살", "우진먹 외 4명", "03/11"))
        list.add(ChatlistModel(R.drawable.user, "우쭈쭈", "우진먹 외 4명", "03/10"))
        list.add(ChatlistModel(R.drawable.user, "의정부 부대찌개", "우진먹 외 4명", "03/09"))
        list.add(ChatlistModel(R.drawable.user, "영진돼지국밥", "우진먹 외 2명", "03/08"))
        list.add(ChatlistModel(R.drawable.user, "청년다방", "우진먹 외 3명", "03/08"))
        list.add(ChatlistModel(R.drawable.user, "파스타", "우진먹 외 3명", "03/11"))
        adapter.notifyDataSetChanged()*/
    }
}