package br.org.venturus.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.org.venturus.test.databinding.ActivityMainBinding

internal class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()
    }

    private fun initListeners() = with(binding) {
        buttonDone.setOnClickListener {
            val name = edittextName.text.toString()
            val greeting = getString(R.string.greeting_message, name)

            if (name.trim().isNotEmpty()) {
                textviewGreeting.text = greeting
            } else {
                textviewGreeting.text = ""
            }
        }
    }
}