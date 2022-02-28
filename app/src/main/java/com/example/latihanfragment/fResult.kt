package com.example.latihanfragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fResult.newInstance] factory method to
 * create an instance of this fragment.
 */
class fResult : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val _tFinalGrade = view.findViewById<TextView>(R.id.tFinalGrade)
        val _tMessage = view.findViewById<TextView>(R.id.tMessage)

        if (arguments != null) {
            var txtFinalGrade = arguments?.getString("Score")
            if (txtFinalGrade!!.toInt() < 0) {
                txtFinalGrade = "0"
            }
            _tFinalGrade.text = txtFinalGrade
            if (txtFinalGrade!!.toInt() <= 15) {
                _tFinalGrade.setTextColor(Color.parseColor("#fc3503"))
                _tMessage.text = "Better luck next time..."
            } else {
                _tFinalGrade.setTextColor(Color.parseColor("#2dd62d"))
                _tMessage.text = "Not bad, yeah?"
            }
        }

        val _bResultPlay = view.findViewById<Button>(R.id.bResultPlay)
        _bResultPlay.setOnClickListener {
            val mfPlay = fPlay()
            val mFragmentManager = parentFragmentManager
            mFragmentManager.beginTransaction().apply {
                replace(R.id.frameContainer, mfPlay, fPlay::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }

        val _bResultHome = view.findViewById<Button>(R.id.bResultHome)
        _bResultHome.setOnClickListener {
            val mfHome = fHome()
            val mFragmentManager = parentFragmentManager
            mFragmentManager.beginTransaction().apply {
                replace(R.id.frameContainer, mfHome, fHome::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }

        val _bHistory = view.findViewById<Button>(R.id.bResultHistory)
        _bHistory.setOnClickListener {
            val mfHistory = fHistory()
            val mFragmentManager = parentFragmentManager
            mFragmentManager.beginTransaction().apply {
                replace(R.id.frameContainer, mfHistory, fHistory::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_f_result, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fResult.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fResult().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}