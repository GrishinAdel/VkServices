package com.adelvanchik.presentation.screens

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.adelvanchik.databinding.ActivityMainBinding
import com.adelvanchik.presentation.recycleview.ServiceListAdapter

class MainActivity() : AppCompatActivity() {

    private lateinit var serviceListAdapter: ServiceListAdapter

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding ?: throw RuntimeException("ActivityMainBinding == null")

    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecycleView()
        clickServicesListener()
        clickReplyListener()
        observerLiveDataOfServices()

        loadData()

    }



    private fun observerLiveDataOfServices() {
        viewModel.listServicesLiveData.observe(this) {
            if (it!=null) {
                serviceListAdapter.submitList(it)
                binding.btnReply.visibility = View.INVISIBLE
            } else {
                Toast.makeText(this, "Ошибка загрузки данных", Toast.LENGTH_SHORT).show()
                with(binding.btnReply) {
                    visibility = View.VISIBLE
                }
            }
        }
    }

    private fun clickReplyListener() {
        with(binding.btnReply) {
            setOnClickListener {
                loadData()
            }
            visibility = View.INVISIBLE
        }
    }

    private fun loadData() {
        viewModel.getListServices()
    }

    private fun clickServicesListener() {
        serviceListAdapter.showDetailsAboutTheService = {
            val intent = DetailsAboutTheServiceActivity.newIntent(this, it)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun setupRecycleView() {
        serviceListAdapter = ServiceListAdapter()
        binding.rvServices.adapter = serviceListAdapter
    }
}