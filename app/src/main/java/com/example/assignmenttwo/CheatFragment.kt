package com.example.assignmenttwo


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.assignmenttwo.databinding.FragmentCheatBinding
import kotlinx.android.synthetic.main.fragment_cheat.*

/**
 * A simple [Fragment] subclass.
 */



class CheatFragment : Fragment() {
    private lateinit var binding: FragmentCheatBinding

    private lateinit var navController: NavController
    lateinit var question: String
    lateinit var answer: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (savedInstanceState != null) {
            answer = savedInstanceState.getString(KEY_ANSWER, "")
            question = savedInstanceState.getString(KEY_QUESTION, "")
        }
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_cheat,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()

        var args = CheatFragmentArgs.fromBundle(arguments!!)

        binding.question = args.question
        binding.answer = args.answer

        binding.apply {
            cheatButton.setOnClickListener{
                answerText.visibility = View.VISIBLE
                answerText.text = answer

            }

            cancelButton.setOnClickListener{
                activity!!.onBackPressed()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_QUESTION, question_textView.text.toString())
        outState.putString(KEY_ANSWER, answer_text.text.toString())
    }


}
