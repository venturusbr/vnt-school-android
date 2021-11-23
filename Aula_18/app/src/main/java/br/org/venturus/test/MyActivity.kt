package br.org.venturus.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

internal class MyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun myFunction(text: String?) {
        val safeText = text ?: return

        safeText.length
    }
}

