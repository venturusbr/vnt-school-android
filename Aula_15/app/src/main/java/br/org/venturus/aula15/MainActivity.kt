package br.org.venturus.aula15

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import br.org.venturus.aula15.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainNotification = MainNotification()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainNotification.createNotificationChannel(this)
        binding.buttonShowNotification.setOnClickListener {
            mainNotification.showNotification(this)
        }

        val permissionLauncher = registerForActivityResult(RequestPermission()) { isGranted ->
            if (isGranted) {
                toast("Permissão recebida")
            } else {
                toast("Explicar para o usuário o porquê da permissão")
            }
        }

        binding.buttonRequestPermission.setOnClickListener {
            requestPermission(permissionLauncher)
        }
    }

    private fun requestPermission(requestPermission: ActivityResultLauncher<String>) = when {
        ContextCompat
            .checkSelfPermission(this, PERMISSION) == PackageManager.PERMISSION_GRANTED -> {
            // Usar a API
        }

        shouldShowRequestPermissionRationale(PERMISSION) -> {
            toast("Explicar para o usuário o porquê da permissão")
        }

        else -> {
            requestPermission.launch(PERMISSION)
        }
    }

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    private companion object {
        private const val PERMISSION = Manifest.permission.READ_EXTERNAL_STORAGE
    }
}