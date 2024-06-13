package sns.example.suitmediatest.ui.firstScreen

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import sns.example.suitmediatest.R
import sns.example.suitmediatest.databinding.ActivityFirstScreenBinding
import sns.example.suitmediatest.ui.secondScreen.SecondScreen

class FirstScreen : AppCompatActivity() {

    private lateinit var binding: ActivityFirstScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAction()
    }

    private fun initAction() {
        binding.btnCheck.setOnClickListener {
            val palindrome = binding.editPalindrome.text.toString()

            if (palindrome.isNotEmpty()) {
                if (isPalindrome(palindrome)) {
                    Toast.makeText(this, getString(R.string.is_palindrome), Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(this, getString(R.string.not_palindrome), Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(this, getString(R.string.empty_palindrome), Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnNext.setOnClickListener {
            val name = binding.editName.text.toString()
            val intent = Intent(this@FirstScreen, SecondScreen::class.java)
            intent.putExtra(SecondScreen.EXTRA_NAME, name)

            if (name.isEmpty()) {
                Toast.makeText(this, getString(R.string.name_cannot_empty), Toast.LENGTH_SHORT)
                    .show()
            } else {
                startActivity(intent)
            }
        }
    }

    private fun isPalindrome(palindrome: String): Boolean {
        val input = palindrome.lowercase().replace(" ", "")
        val length = input.length
        for (i in 0 until length / 2) {
            if (input[i] != input[length - i - 1]) {
                return false
            }
        }
        return true
    }
}