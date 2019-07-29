package ru.vtb.android.mvvm.example.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import io.reactivex.disposables.CompositeDisposable
import ru.vtb.android.mvvm.example.core.ext.addTo
import ru.vtb.android.mvvm.example.core.ext.setOnTextChangedListener
import ru.vtb.android.mvvm.example.generated.login.entity.AuthBinding
import ru.vtb.android.mvvm.login.impl.R
import javax.inject.Inject

class AuthFragment : Fragment() {

    @Inject
    lateinit var binding: AuthBinding

    private lateinit var loginEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var errorStatusInputLayout: TextInputLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var submitButton: Button

    private val disposables = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginEditText = view.findViewById(R.id.etLogin)
        passwordEditText = view.findViewById(R.id.etPassword)
        errorStatusInputLayout = view.findViewById(R.id.tvAuthStatus)
        progressBar = view.findViewById(R.id.progressBar)
        submitButton = view.findViewById(R.id.btnSubmit)

        bind()
        applyListeners()
    }

    private fun bind() {
        binding.onLoading(::onLoadingChange).addTo(disposables)
        binding.onSubmitEnabled(::onSubmitEnabledChange).addTo(disposables)
        binding.onError(::onStatusChange).addTo(disposables)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    private fun applyListeners() {
        submitButton.setOnClickListener { binding.auth() }
        loginEditText.setOnTextChangedListener { binding.model.login = it }
        passwordEditText.setOnTextChangedListener { binding.model.password = it }
    }

    private fun onLoadingChange(isLoading: Boolean) {
        progressBar.isVisible = isLoading
        submitButton.isEnabled = !isLoading
        loginEditText.isEnabled = !isLoading
        passwordEditText.isEnabled = !isLoading
    }

    private fun onSubmitEnabledChange(isSubmitEnabled: Boolean) {
        submitButton.isEnabled = isSubmitEnabled
    }

    private fun onStatusChange(statusError: String) {
        if (statusError.isBlank()) {
            errorStatusInputLayout.isErrorEnabled = false
        } else {
            errorStatusInputLayout.isErrorEnabled = true
            errorStatusInputLayout.error = statusError
        }
    }
}