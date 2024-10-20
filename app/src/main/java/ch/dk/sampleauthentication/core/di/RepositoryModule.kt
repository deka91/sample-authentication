package ch.dk.sampleauthentication.core.di

import ch.dk.sampleauthentication.feature.confirmation.data.repository.ConfirmationRepositoryImpl
import ch.dk.sampleauthentication.feature.confirmation.domain.repository.ConfirmationRepository
import ch.dk.sampleauthentication.feature.registration.data.repository.RegistrationRepositoryImpl
import ch.dk.sampleauthentication.feature.registration.domain.repository.RegistrationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideRegistrationRepository(registrationRepositoryImpl: RegistrationRepositoryImpl): RegistrationRepository

    @Singleton
    @Binds
    abstract fun provideConfirmationRepository(confirmationRepositoryImpl: ConfirmationRepositoryImpl): ConfirmationRepository
}