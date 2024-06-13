package sns.example.suitmediatest.ui.thirdScreen

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import sns.example.suitmediatest.R
import sns.example.suitmediatest.adapter.LoadingStateAdapter
import sns.example.suitmediatest.adapter.UserAdapter
import sns.example.suitmediatest.databinding.ActivityThirdScreenBinding
import sns.example.suitmediatest.ui.secondScreen.SecondScreen
import sns.example.suitmediatest.utils.ViewModelFactory

class ThirdScreen : AppCompatActivity() {

    private lateinit var binding: ActivityThirdScreenBinding

    private val thirdViewModel: ThirdViewModel by viewModels {
        ViewModelFactory.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setItem()
    }

    private fun setItem() {
        val layoutManager = LinearLayoutManager(this)
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        val adapter = UserAdapter {
            val resultIntent = Intent()
            resultIntent.putExtra(
                SecondScreen.EXTRA_SELECTED_NAME,
                "${it.firstName} ${it.lastName}"
            )
            setResult(SecondScreen.RESULT_OK, resultIntent)
            finish()
        }

        lifecycleScope.launch {
            adapter.loadStateFlow.collect {
                when (it.refresh) {
                    is LoadState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is LoadState.NotLoading -> {
                        binding.progressBar.visibility = View.GONE
                        if (it.append.endOfPaginationReached && adapter.itemCount < 1) {
                            Toast.makeText(
                                this@ThirdScreen,
                                getString(R.string.empty_data),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    is LoadState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(
                            this@ThirdScreen,
                            getString(R.string.error),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        binding.swipeRefresh.apply {
            setOnRefreshListener {
                thirdViewModel.getUser()
                isRefreshing = false
            }
        }

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.rvUser.apply {
            this.layoutManager = layoutManager
            addItemDecoration(itemDecoration)
            this.adapter =
                adapter.withLoadStateFooter(footer = LoadingStateAdapter { adapter.retry() })
        }
        thirdViewModel.listUser.observe(this) { adapter.submitData(lifecycle, it) }
    }
}