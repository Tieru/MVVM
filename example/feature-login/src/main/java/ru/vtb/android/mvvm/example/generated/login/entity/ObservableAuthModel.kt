package ru.vtb.android.mvvm.example.generated.login.entity

import io.reactivex.subjects.BehaviorSubject
import ru.vtb.android.mvvm.example.login.entity.AuthModel
import kotlin.properties.Delegates

class ObservableAuthModel : AuthModel() {

    val loginSubject = BehaviorSubject.create<String>()
    val passwordSubject = BehaviorSubject.create<String>()

    override var login: String by Delegates.observable(initialValue = "") { property, oldValue, newValue ->
        loginSubject.onNext(newValue)
    }

    override var password: String by Delegates.observable(initialValue = "") { property, oldValue, newValue ->
        passwordSubject.onNext(newValue)
    }
}
