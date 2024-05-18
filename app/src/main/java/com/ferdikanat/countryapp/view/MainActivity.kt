package com.ferdikanat.countryapp.view


import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ferdikanat.countryapp.R
import com.ferdikanat.countryapp.adapter.CountryAdapter
import com.ferdikanat.countryapp.databinding.ActivityMainBinding
import com.ferdikanat.countryapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel : MainViewModel
    private lateinit var binding : ActivityMainBinding
    private var countryAdapter = CountryAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.countryRV.adapter = countryAdapter
        binding.countryRV.layoutManager = LinearLayoutManager(this)
        viewModel.getDataFromAPI()
        setObserves()
    }

    private fun setObserves(){
        viewModel.countryData.observe(this) { list -> countryAdapter.updateList(list) }

        viewModel.countryLoad.observe(this) { loading ->
            if (loading) {
                binding.loadingPB.visibility = View.VISIBLE
            } else {
                binding.loadingPB.visibility = View.GONE
            }
        }

        viewModel.countryError.observe(this) { error ->
            if (error)
                binding.errorTV.visibility = View.VISIBLE
            else
                binding.errorTV.visibility = View.GONE
        }

    }
}