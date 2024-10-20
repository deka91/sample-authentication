package ch.dk.sampleauthentication.core.di

import ch.dk.sampleauthentication.feature.confirmation.domain.repository.ConfirmationRepository
import ch.dk.sampleauthentication.feature.confirmation.domain.usecase.ConfirmationUseCases
import ch.dk.sampleauthentication.feature.confirmation.domain.usecase.LoadUserProfile
import ch.dk.sampleauthentication.feature.registration.data.validation.EmailValidatorImpl
import ch.dk.sampleauthentication.feature.registration.domain.repository.RegistrationRepository
import ch.dk.sampleauthentication.feature.registration.domain.usecase.*
import ch.dk.sampleauthentication.feature.registration.domain.validation.EmailValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Deniz Kalem on 18.10.2024
 */
@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideValidateName(): ValidateName = ValidateName()

    @Singleton
    @Provides
    fun provideEmailValidator(): EmailValidator = EmailValidatorImpl()

    @Provides
    @Singleton
    fun provideValidateEmail(emailValidator: EmailValidator): ValidateEmail = ValidateEmail(emailValidator)

    @Provides
    @Singleton
    fun provideValidateBirthday(): ValidateBirthday = ValidateBirthday()

    @Provides
    @Singleton
    fun provideSaveUserProfile(registrationRepository: RegistrationRepository): SaveUserProfile =
        SaveUserProfile(registrationRepository = registrationRepository)

    @Provides
    @Singleton
    fun provideLoadUserProfile(confirmationRepository: ConfirmationRepository): LoadUserProfile =
        LoadUserProfile(confirmationRepository)

    @Provides
    @Singleton
    fun provideRegistrationUseCases(
        validateName: ValidateName,
        validateEmail: ValidateEmail,
        validateBirthday: ValidateBirthday,
        saveUserProfile: SaveUserProfile
    ): RegistrationUseCases =
        RegistrationUseCases(
            validateName = validateName,
            validateEmail = validateEmail,
            validateBirthday = validateBirthday,
            saveUserProfile = saveUserProfile
        )

    @Provides
    @Singleton
    fun provideConfirmationUseCases(loadUserProfile: LoadUserProfile): ConfirmationUseCases =
        ConfirmationUseCases(loadUserProfile)
}