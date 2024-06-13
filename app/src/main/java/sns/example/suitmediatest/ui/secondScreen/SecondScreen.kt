package sns.example.suitmediatest.ui.secondScreen

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import sns.example.suitmediatest.databinding.ActivitySecondScreenBinding
import sns.example.suitmediatest.ui.thirdScreen.ThirdScreen

class SecondScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySecondScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private val launcherThirdScreen =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK && it.data != null) {
                val selectedUser = it.data?.getStringExtra(EXTRA_SELECTED_NAME) ?: ""
                binding.selectedUser.text = selectedUser
            }
        }

    private fun initUI() {
        val username = intent.getStringExtra(EXTRA_NAME) ?: ""
        binding.tvUsername.text = username

        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.btnChooseUser.setOnClickListener {
            val intent = Intent(this@SecondScreen, ThirdScreen::class.java)
            launcherThirdScreen.launch(intent)
        }
    }

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_SELECTED_NAME = "extra_selected_name"
        const val RESULT_OK = 200
    }
}