package ru.vtb.android.mvvm.example.login.domain

import io.reactivex.Observable
import io.reactivex.Single
import java.lang.RuntimeException
import java.util.concurrent.TimeUnit

interface AuthUseCase {

    fun execute(login: String, password: String): Observable<Boolean>

}

class AuthUseCaseImpl : AuthUseCase {

    override fun execute(login: String, password: String): Observable<Boolean> {
        val isSuccess = System.currentTimeMillis() % 2 == 0L
        val result = Observable.just(true)
            .delay(2, TimeUnit.SECONDS)

        return if (isSuccess) {
            result
        } else {
            result.map { throw RuntimeException("Неудача. Попробуй снова!") }
        }
    }
}
