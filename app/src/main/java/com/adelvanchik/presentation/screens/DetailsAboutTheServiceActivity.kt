package com.adelvanchik.presentation.screens

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.adelvanchik.databinding.ActivityDetailsAboutTheServiceBinding
import com.adelvanchik.domain.entity.Item
import com.squareup.picasso.Picasso

class DetailsAboutTheServiceActivity : AppCompatActivity() {

    private var _binding: ActivityDetailsAboutTheServiceBinding? = null
    private val binding: ActivityDetailsAboutTheServiceBinding
        get() = _binding ?: throw RuntimeException("ActivityDetailsAboutTheServiceBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailsAboutTheServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        parseArgs()
        clickUrlServiceOnCLickListener()
        backBtnClickListener()
    }

    private fun clickUrlServiceOnCLickListener() {
        binding.tvServiceUrl.setOnClickListener {
            val url = binding.tvServiceUrl.text.toString()
            if (url.startsWith("http://") || url.startsWith("https://")) {
                val intentBrowser = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intentBrowser)
            } else {
                Toast.makeText(
                    applicationContext,
                    "Неверный формал URL",
                    Toast.LENGTH_SHORT)
                    .show()
            }

        }
    }

    private fun backBtnClickListener() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun parseArgs() {
        with(binding) {
            tvNameService.text = intent.getStringExtra(NAME_KEY)
            tvNameServiceInToolbar.text = intent.getStringExtra(NAME_KEY)
            Picasso.get().load(intent.getStringExtra(ICON_KEY)).into(ivIconService)
            tvDescriptionService.text =intent.getStringExtra(DESCRIPTION_KEY)
            tvServiceUrl.text = intent.getStringExtra(URL_KEY)
            tvServiceUrl.paintFlags = binding.tvServiceUrl.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val NAME_KEY = "name_key"
        private const val ICON_KEY = "icon_key"
        private const val URL_KEY = "url_key"
        private const val DESCRIPTION_KEY = "description_key"
        fun newIntent(context: Context, item: Item): Intent {
            val intent = Intent(context, DetailsAboutTheServiceActivity::class.java)
            intent.putExtra(NAME_KEY, item.name)
            intent.putExtra(ICON_KEY, item.icon_url)
            intent.putExtra(URL_KEY, item.service_url)
            intent.putExtra(DESCRIPTION_KEY, item.description)
            return intent
        }
    }

}