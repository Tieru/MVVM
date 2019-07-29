package ru.vtb.android.mvvm.example.generated.login.entity

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import ru.vtb.android.mvvm.example.login.domain.AuthUseCase
import ru.vtb.android.mvvm.example.login.entity.AuthModel
import ru.vtb.android.mvvm.example.login.presentation.AuthViewModel


interface AuthBinding {

    val model: AuthModel

    fun onLoading(bind: (Boolean) -> Unit): Disposable
    fun onSubmitEnabled(bind: (Boolean) -> Unit): Disposable
    fun onError(bind: (String) -> Unit): Disposable

    fun auth()
}


class AuthBindingImpl(
    authUseCase: AuthUseCase, model: ObservableAuthModel = ObservableAuthModel()
) : AuthViewModel(authUseCase, model), AuthBinding {

    private val isLoadingObservable: Observable<Boolean> = isLoadingSubject
    private val errorObservable: Observable<String> = errorSubject
    private val isSubmitEnabledObservable: Observable<Boolean> = isSubmitEnabledSubject

    override fun onLoading(bind: (Boolean) -> Unit): Disposable {
        return isLoadingObservable
            .distinctUntilChanged()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(bind)
    }

    override fun onError(bind: (String) -> Unit): Disposable {
        return errorObservable
            .distinctUntilChanged()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(bind)
    }

    override fun onSubmitEnabled(bind: (Boolean) -> Unit): Disposable {
        return isSubmitEnabledObservable
            .distinctUntilChanged()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(bind)
    }
}
