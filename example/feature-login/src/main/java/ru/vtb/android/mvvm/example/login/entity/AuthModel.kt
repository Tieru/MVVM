package ru.vtb.android.mvvm.example.login.entity

import ru.vtb.android.mvvm.annotations.Model

@Model
open class AuthModel {
    open var login: String = ""
    open var password: String = ""
}
