import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.geoquiz.Question
import com.example.geoquiz.R

private const val TAG = "QuizViewModel"

class QuizViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    var currentIndex: Int
        get() = savedStateHandle.get("currentIndex") ?: 0
        set(value) = savedStateHandle.set("currentIndex", value)

    var isCheater: Boolean
        get() = savedStateHandle.get("isCheater") ?: false
        set(value) = savedStateHandle.set("isCheater", value)

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
        isCheater = false
    }

    fun moveToPrevious() {
        currentIndex = (currentIndex - 1 + questionBank.size) % questionBank.size
        isCheater = false
    }
}
