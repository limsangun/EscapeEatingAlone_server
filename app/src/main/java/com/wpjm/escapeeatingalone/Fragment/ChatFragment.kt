package com.wpjm.escapeeatingalone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wpjm.escapeeatingalone.databinding.FragmentChatBinding

class ChatFragment : Fragment() {
    private var mBinding: FragmentChatBinding? = null
    private val binding get() = mBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_chat, container, false)
        mBinding = FragmentChatBinding.inflate(inflater, container, false)
        return view
    }
}