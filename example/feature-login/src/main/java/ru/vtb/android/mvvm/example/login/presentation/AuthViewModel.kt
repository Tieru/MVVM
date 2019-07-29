package ru.vtb.android.mvvm.example.login.presentation

import android.annotation.SuppressLint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import ru.vtb.android.mvvm.annotations.ViewModel
import ru.vtb.android.mvvm.core.presentation.BaseViewModel
import ru.vtb.android.mvvm.example.core.ext.addTo
import ru.vtb.android.mvvm.example.generated.login.entity.ObservableAuthModel
import ru.vtb.android.mvvm.example.login.domain.AuthUseCase
import ru.vtb.android.mvvm.example.login.entity.AuthModel

@SuppressLint("CheckResult")
@ViewModel
open class AuthViewModel(private val authUseCase: AuthUseCase, model: ObservableAuthModel) :
    BaseViewModel<AuthModel>(model) {

    private val disposables = CompositeDisposable()

    val isLoadingSubject = BehaviorSubject.createDefault(false)
    val isSubmitEnabledSubject = BehaviorSubject.createDefault(false)
    val errorSubject = BehaviorSubject.createDefault("")

    init {
        model.loginSubject.subscribe { onLoginPassChanged() }.addTo(disposables)
        model.passwordSubject.subscribe { onLoginPassChanged() }.addTo(disposables)
    }

    fun auth() {
        isLoadingSubject.onNext(true)
        errorSubject.onNext("")
        authUseCase.execute(model.login, model.password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onAuthCompleted, ::onAuthFailure)
            .addTo(disposables)
    }

    private fun onAuthCompleted(success: Boolean) {
        isLoadingSubject.onNext(false)
        // TODO: routing to main screen
    }

    private fun onAuthFailure(error: Throwable) {
        isLoadingSubject.onNext(false)
        errorSubject.onNext(error.message ?: "Неизвестная ошибка")
    }

    private fun onLoginPassChanged() {
        val isInputFull = !(model.login.isBlank() or model.password.isBlank())
        isSubmitEnabledSubject.onNext(isInputFull)
    }

}
