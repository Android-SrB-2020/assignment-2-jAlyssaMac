package com.example.assignmenttwo


import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.*
import androidx.appcompat.widget.AppCompatImageButton
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.assignmenttwo.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_cheat.*


/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var navController: NavController
    lateinit var question: String
    lateinit var answer: String

    //Creating list with all questions for the app
    private val questionBank = listOf(
        Question(R.string.question_1, false),
        Question(R.string.question_2, true),
        Question(R.string.question_3, true),
        Question(R.string.question_4, false),
        Question(R.string.question_5, false),
        Question(R.string.question_6, true),
        Question(R.string.question_7, false),
        Question(R.string.question_8, true),
        Question(R.string.question_9, false),
        Question(R.string.question_10, false),
        Question(R.string.question_11, false),
        Question(R.string.question_12, true),
        Question(R.string.question_13, false),
        Question(R.string.question_14, true),
        Question(R.string.question_15, false),
        Question(R.string.question_16, false),
        Question(R.string.question_17, true),
        Question(R.string.question_18, false),
        Question(R.string.question_19, false),
        Question(R.string.question_20, true))

    //Creating variables for all controls and the question index
    var questionIndex = 0

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
            R.layout.fragment_main,
            container,
            false
        )
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, navController)
                ||super.onOptionsItemSelected(item)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()



        binding.apply {
            questionView.setText(questionBank[questionIndex].TextResId)
            nextButton.setOnClickListener{nextQuestion()}
            backButton.setOnClickListener{previousQuestion()}
            trueButton.setOnClickListener{isCorrectAnswer()}
            falseButton.setOnClickListener{isAnswerFalse()}

            cheatButton.setOnClickListener{
                navController.navigate(MainFragmentDirections.actionMainFragmentToCheatFragment(questionView.text.toString(), questionBank[questionIndex].answer.toString()))
            }


        }
    }

    /**
     * Method going to the next question
     */
    private fun nextQuestion(){
        questionIndex = (questionIndex + 1) % 20
        binding.questionView.setText(questionBank[questionIndex].TextResId)
    }

    /**
     * Method that goes to the previous question
     */
    private fun previousQuestion(){

        if(questionIndex == 0){
            questionIndex = questionBank.count() - 1
        }
        else{
            questionIndex = (questionIndex - 1)
        }

        binding.questionView.setText(questionBank[questionIndex].TextResId)

    }

    /**
     * Method Checking whether the answer is true
     */
    private fun isCorrectAnswer(){
        if(questionBank[questionIndex].answer){
            makeText(activity, "Correct!!!",
            LENGTH_SHORT
            ).show()
        }else {
            makeText(activity,"YOU ARE WRONG!",
                LENGTH_SHORT
            ).show()
        }
    }

    /**
     * Method checking whether the answer is false
     */
    private fun isAnswerFalse(){
        if(!questionBank[questionIndex].answer){
            makeText(activity, "Correct!!!",
                LENGTH_SHORT
            ).show()
        }else{
            makeText(activity, "YOU ARE WRONG!",
                LENGTH_SHORT
            ).show()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_QUESTION, question_textView.text.toString())
        outState.putString(KEY_ANSWER, answer_text.text.toString())
    }
}
