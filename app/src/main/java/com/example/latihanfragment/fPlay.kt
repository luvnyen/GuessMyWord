package com.example.latihanfragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer
import android.widget.EditText
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fPlay.newInstance] factory method to
 * create an instance of this fragment.
 */
class fPlay : Fragment() {
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

        val _cTime = view.findViewById<Chronometer>(R.id.cTimer)
        _cTime.start()

        var name = String()
        if (arguments != null) {
            name = arguments?.getString("Name")!!
        }

        val wordsArray = arrayOf<String>("android", "ios", "windows", "linux", "ubuntu")
        wordsArray.shuffle()
        var guessedWordArray = arrayOf<String>()

        var wordToBeGuessed = ""
        var exist = false

        for (word in wordsArray) {
            exist = false
            for (guessedWord in guessedWordArray) {
                if (word == guessedWord) {
                    exist = true
                    break;
                }
            }
            if (!exist) {
                wordToBeGuessed = word
                break;
            } else {
                exist = false
            }
        }

        // val wordToBeGuessed = wordsArray.random()
        var grade = 15

        val _tGuess = view.findViewById<TextView>(R.id.tTopic)
        var tFirstIndexRandom = (0..wordToBeGuessed.length - 1).random()
        var tSecondIndexRandom = (0..wordToBeGuessed.length - 1).random()
        while (tFirstIndexRandom == tSecondIndexRandom) {
            tSecondIndexRandom = (0..wordToBeGuessed.length - 1).random()
        }
        var tBlur = wordToBeGuessed.toCharArray()
        tBlur[tFirstIndexRandom] = '_'
        tBlur[tSecondIndexRandom] = '_'
        var tBlurNew = ""
        for (c in tBlur) {
            tBlurNew += c + " "
        }
        _tGuess.text = tBlurNew

        val _tGrade = view.findViewById<TextView>(R.id.tGrade)
        val _eGuess = view.findViewById<EditText>(R.id.eGuess)

        val _bGuess = view.findViewById<Button>(R.id.bGuess)
        _bGuess.setOnClickListener {
            if (_eGuess.text.toString().trim() == "") {
                _eGuess.setError("The item tebak kata cannot be empty.")
            } else {
                if (_eGuess.text.toString().trim().lowercase() != wordToBeGuessed) {
                    _tGrade.setTextColor(Color.parseColor("#fc3503"))
                    grade -= 5
                    if (grade <= 0) {
                        grade = 0
                        _cTime.stop()

                        val mBundle = Bundle()
                        mBundle.putString("Score", grade.toString())
                        mBundle.putString("Name", name)
                        mBundle.putString("Time", _cTime.text.toString())

                        val mfResult = fResult()
                        mfResult.arguments = mBundle

                        val mfHistory = fHistory()
                        mfHistory.arguments = mBundle

                        val mFragmentManager = parentFragmentManager
                        mFragmentManager.beginTransaction().apply {
                            replace(R.id.frameContainer, mfHistory, fHistory::class.java.simpleName)
                            commit()
                        }
                        mFragmentManager.beginTransaction().apply {
                            replace(R.id.frameContainer, mfResult, fResult::class.java.simpleName)
                            addToBackStack(null)
                            commit()
                        }

                    }
                    _tGrade.text = "Wrong! Grade: ${grade.toString()} (-5)"
                } else if (_eGuess.text.toString().trim().lowercase() == wordToBeGuessed) {
                    _tGrade.setTextColor(Color.parseColor("#13ad13"))
                    grade += 10
                    guessedWordArray += wordToBeGuessed
                    _tGrade.text = "Correct! Grade: ${grade.toString()} (+10)"
                }

                if (wordsArray.size == guessedWordArray.size) {
                    _cTime.stop()

                    val mBundle = Bundle()
                    mBundle.putString("Score", grade.toString())
                    mBundle.putString("Name", name)
                    mBundle.putString("Time", _cTime.text.toString())

                    val mfResult = fResult()
                    mfResult.arguments = mBundle

                    val mfHistory = fHistory()
                    mfHistory.arguments = mBundle

                    val mFragmentManager = parentFragmentManager
                    mFragmentManager.beginTransaction().apply {
                        replace(R.id.frameContainer, mfHistory, fHistory::class.java.simpleName)
                        commit()
                    }
                    mFragmentManager.beginTransaction().apply {
                        replace(R.id.frameContainer, mfResult, fResult::class.java.simpleName)
                        addToBackStack(null)
                        commit()
                    }
                }

                for (word in wordsArray) {
                    exist = false
                    for (guessedWord in guessedWordArray) {
                        if (word == guessedWord) {
                            exist = true
                            break;
                        }
                    }
                    if (!exist) {
                        wordToBeGuessed = word
                        break;
                    } else {
                        exist = false
                    }
                }

                tFirstIndexRandom = (0..wordToBeGuessed.length - 1).random()
                tSecondIndexRandom = (0..wordToBeGuessed.length - 1).random()
                while (tFirstIndexRandom == tSecondIndexRandom) {
                    tSecondIndexRandom = (0..wordToBeGuessed.length - 1).random()
                }
                tBlur = wordToBeGuessed.toCharArray()
                tBlur[tFirstIndexRandom] = '_'
                tBlur[tSecondIndexRandom] = '_'
                tBlurNew = ""
                for (c in tBlur) {
                    tBlurNew += c + " "
                }
                _tGuess.text = tBlurNew
                _eGuess.text.clear()
            }
        }

        val _bSurrender = view.findViewById<Button>(R.id.bSurrender)
        _bSurrender.setOnClickListener {
            _cTime.stop()

            val mBundle = Bundle()
            mBundle.putString("Score", grade.toString())
            mBundle.putString("Name", name)
            mBundle.putString("Time", _cTime.text.toString())

            val mfResult = fResult()
            mfResult.arguments = mBundle

            val mfHistory = fHistory()
            mfHistory.arguments = mBundle

            val mFragmentManager = parentFragmentManager
            mFragmentManager.beginTransaction().apply {
                replace(R.id.frameContainer, mfHistory, fHistory::class.java.simpleName)
                commit()
            }
            mFragmentManager.beginTransaction().apply {
                replace(R.id.frameContainer, mfResult, fResult::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }

        val _bPlayHome = view.findViewById<Button>(R.id.bPlayHome)
        _bPlayHome.setOnClickListener {
            _cTime.stop()

            val mfHome = fHome()
            val mFragmentManager = parentFragmentManager
            mFragmentManager.beginTransaction().apply {
                replace(R.id.frameContainer, mfHome, fHome::class.java.simpleName)
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
        return inflater.inflate(R.layout.fragment_f_play, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fPlay.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fPlay().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}