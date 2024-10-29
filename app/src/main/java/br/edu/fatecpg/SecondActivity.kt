package br.edu.fatecpg

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.fatecpg.databinding.ActivitySecondBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = intent.extras
        val CurrentEmail = bundle?.getString("Email")

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.txvEmail.text = CurrentEmail
        
        
        binding.btnExcluir.setOnClickListener {
            val user = Firebase.auth.currentUser!!

            user.delete()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "User account deleted.")
                        Toast.makeText(this, "Usuário Excluído!", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
        }
        
        binding.btnRedefinir.setOnClickListener {
            val user = Firebase.auth.currentUser

            user!!.sendEmailVerification()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "Email sent.")
                        Toast.makeText(this, "E-mail enviado!", Toast.LENGTH_SHORT).show()
                    }
                }
        }


    }
}