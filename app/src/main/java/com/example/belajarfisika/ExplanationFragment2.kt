package com.example.belajarfisika

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import android.widget.MediaController

class ExplanationFragment2 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_explanation2, container, false)

        val videoView = rootView.findViewById<VideoView>(R.id.videoView)
        val videoPath = "android.resource://" + requireActivity().packageName + "/" + R.raw.kasus_2
        val videoUri = Uri.parse(videoPath)

        videoView.setVideoURI(videoUri)

        val mediaController = MediaController(requireContext())
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)

        return rootView
    }
}