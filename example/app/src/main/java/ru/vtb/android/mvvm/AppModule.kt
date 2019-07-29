package ru.vtb.android.mvvm

import dagger.Module
import dagger.Provides
import ru.vtb.android.mvvm.example.generated.login.entity.AuthBindingImpl
import ru.vtb.android.mvvm.example.generated.login.entity.AuthBinding
import ru.vtb.android.mvvm.example.login.domain.AuthUseCase
import ru.vtb.android.mvvm.example.login.domain.AuthUseCaseImpl

@Module
class AppModule {

    @Provides
    fun provideAuthUseCase(): AuthUseCase = AuthUseCaseImpl()

    @Provides
    fun provideAuthViewModelBinder(useCase: AuthUseCase): AuthBinding = AuthBindingImpl(useCase)
}