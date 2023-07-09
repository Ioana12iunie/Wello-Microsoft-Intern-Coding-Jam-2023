package com.example.wello.ui.register

import android.content.ActivityNotFoundException
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
import com.example.wello.databinding.FragmentRegisterTabBinding
import com.google.android.gms.tasks.Task
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterTabFragment : Fragment() {
    private lateinit var email : TextInputLayout
    private lateinit var password : TextInputLayout
    private lateinit var emailtext: TextInputEditText
    private lateinit var passwordtext: TextInputEditText
    private lateinit var registerButton: Button
    private lateinit var forgotPassword : TextView
    private var _binding: FragmentRegisterTabBinding? = null
    private val binding get() = _binding!!

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register_tab, container, false)
        _binding = FragmentRegisterTabBinding.inflate(inflater, container, false)


        email = view.findViewById(R.id.reg_email)
        emailtext = view.findViewById(R.id.reg_email_input)
        password = view.findViewById(R.id.reg_password)
        passwordtext = view.findViewById(R.id.reg_password_input)
        registerButton = view.findViewById(R.id.registerButton)
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

        binding.regPasswordInput.doOnTextChanged {
                text, start, before, count ->
            if (text!!.length >= 20) {
                binding.regPassword.error = "Character limit reached!"
            } else if (text.length < 20) {
                if (text.length < 8) {
                    binding.regPassword.error = "Must contain at least 8 characters"
                }
                else if (!text.matches(".*[A-Z].*".toRegex())) {
                    binding.regPassword.error = "Must contain at least one uppercase character"
                }
                else if (!text.matches(".*[a-z].*".toRegex())) {
                    binding.regPassword.error = "Must contain at least one lowercase character"
                }
                else if (!text.matches(".*[@#\$%^&+=-].*".toRegex())) {
                    binding.regPassword.error = "Must contain at least one special character : @#\$%^&+=-"
                }
                else if (!text.matches(".*[0-9].*".toRegex())) {
                    binding.regPassword.error = "Must contain at least one digit"
                }
                else
                {
                    binding.regPassword.error = null
                }
            }
        }

        registerButton.setOnClickListener {
            val txtEmail = emailtext.text.toString()
            val txtPassword = passwordtext.text.toString()

            if (txtEmail.isEmpty() || txtPassword.isEmpty()) {
                Toast.makeText(context, "Empty credentials!", Toast.LENGTH_SHORT).show()
            } else {
                registerUser(txtEmail, txtPassword)
            }
        }

        return view
    }


    private fun registerUser(email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        context,
                        "Register successful!",
                        Toast.LENGTH_SHORT
                    ).show()


                    val intent = Intent(context, MainActivity::class.java)
                    try {
                        startActivity(intent)
                    } catch (e: ActivityNotFoundException) {
                        Toast.makeText(context, "An error occured", Toast.LENGTH_SHORT).show()
                    }

                } else {
                    val exception: Exception? = task.exception
                    if (exception is FirebaseAuthException) {
                        val errorCode: String = (exception).errorCode
                        if (errorCode == "ERROR_WEAK_PASSWORD") {
                            Toast.makeText(
                                context,
                                "Weak password!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
            .addOnFailureListener { e: Exception ->
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
    }
}