package com.example.wello.ui.login

import com.example.wello.MainActivity
import com.example.wello.R
import com.example.wello.databinding.FragmentLoginTabBinding
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth

class LoginTabFragment : Fragment() {
    private lateinit var email : TextInputLayout
    private lateinit var password : TextInputLayout
    private lateinit var emailtext: TextInputEditText
    private lateinit var passwordtext: TextInputEditText
    private lateinit var loginButton: Button
    private lateinit var forgotPassword : TextView
    private var _binding: FragmentLoginTabBinding? = null
    private val binding get() = _binding!!

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login_tab, container, false)
        _binding = FragmentLoginTabBinding.inflate(inflater, container, false)


        email = view.findViewById(R.id.log_email)
        emailtext = view.findViewById(R.id.log_email_input)
        password = view.findViewById(R.id.log_password)
        passwordtext = view.findViewById(R.id.log_password_input)
        loginButton = view.findViewById(R.id.loginButton)
        forgotPassword = view.findViewById(R.id.forgotPassword)
        mAuth = FirebaseAuth.getInstance()

        emailtext.doOnTextChanged {
                text, start, before, count ->
            if (text!!.length >= 30) {
                email.error ="Character limit reached!"
            } else if (text.length < 30) {
                val regexPattern = "^[a-zA-Z0-9._%+-]+@(gmail|yahoo)\\.[a-zA-Z]{2,}$".toRegex()
                if (regexPattern.matches(emailtext.text.toString())) {
                    email.error = null
                }
                else {
                    email.error ="Invalid email adress!"
                }
            }
        }

        loginButton.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        binding.logPasswordInput.doOnTextChanged {
                text, start, before, count ->
            if (text!!.length >= 20) {
                binding.logPassword.error = "Character limit reached!"
            } else if (text.length < 20) {
                if (text.length < 8) {
                    binding.logPassword.error = "Must contain at least 8 characters"
                }
                else if (!text.matches(".*[A-Z].*".toRegex())) {
                    binding.logPassword.error = "Must contain at least one uppercase character"
                }
                else if (!text.matches(".*[a-z].*".toRegex())) {
                    binding.logPassword.error = "Must contain at least one lowercase character"
                }
                else if (!text.matches(".*[@#\$%^&+=-].*".toRegex())) {
                    binding.logPassword.error = "Must contain at least one special character : @#\$%^&+=-"
                }
                else if (!text.matches(".*[0-9].*".toRegex())) {
                    binding.logPassword.error = "Must contain at least one digit"
                }
                else
                {
                    binding.logPassword.error = null
                }
            }
        }

        binding.loginButton.setOnClickListener {
            val txtEmail = emailtext.text.toString()
            val txtPassword = passwordtext.text.toString()

            if (txtEmail.isEmpty() || txtPassword.isEmpty()) {
                Toast.makeText(context, "Empty credentials!", Toast.LENGTH_SHORT).show()
            } else {
                loginUser(txtEmail, txtPassword)
            }
        }

        return view
    }


    private fun loginUser(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val intent = Intent(activity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, "Please try again later", Toast.LENGTH_SHORT).show()
            }
    }
}