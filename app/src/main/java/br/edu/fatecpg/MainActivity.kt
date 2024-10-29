package br.edu.fatecpg

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.fatecpg.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        auth = Firebase.auth
        
        binding.btnCadastrar.setOnClickListener {

            val email = binding.edtEmail.text.toString()
            val senha = binding.edtSenha.text.toString()

            auth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this){ task ->
                    if(task.isSuccessful){
                        Toast.makeText(this, "Login Efetuado com Sucesso!", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this, SecondActivity::class.java)

                        val bundle = Bundle()
                        bundle.putString("Email", email)
                        intent.putExtras(bundle)

                        startActivity(intent)

                    }
                    else {
                        Toast.makeText(this, "Erro!", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        binding.txvCadastro.setOnClickListener {

            val email = binding.edtEmail.text.toString()
            val senha = binding.edtSenha.text.toString()

            auth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "createUserWithEmail:success")
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
        }

    }
}