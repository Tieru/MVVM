package ru.vtb.android.mvvm

import dagger.Component
import ru.vtb.android.mvvm.example.login.ui.AuthFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(fragment: AuthFragment)

}