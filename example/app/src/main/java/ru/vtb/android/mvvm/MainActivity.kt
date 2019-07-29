package ru.vtb.android.mvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.vtb.android.mvvm.example.login.ui.AuthFragment


/**
 * Основной экран приложения
 */
class MainActivity : AppCompatActivity() {


    /**
     * Inject зависимостей, начальная навигация
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = AuthFragment()

        val component = DaggerAppComponent.builder().build()
        component.inject(fragment)

        supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit()
    }
}
