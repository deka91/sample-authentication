package ch.dk.sampleauthentication.feature.registration.domain.usecase

import ch.dk.sampleauthentication.R
import ch.dk.sampleauthentication.core.presentation.util.UiText
import ch.dk.sampleauthentication.feature.registration.domain.validation.EmailValidator
import ch.dk.sampleauthentication.feature.registration.domain.validation.ValidationResult
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

/**
 * Created by Deniz Kalem on 19.10.2024
 */
class ValidateEmailTest {

    private lateinit var validateEmail: ValidateEmail
    private lateinit var emailValidator: EmailValidator

    @Before
    fun setup() {
        emailValidator = Mockito.mock(EmailValidator::class.java)
        validateEmail = ValidateEmail(emailValidator)
    }

    @Test
    fun `Blank email is not valid and returns error text for blank email`() {
        // GIVEN
        val name = "  "

        // ACTION
        val actual = validateEmail.invoke(name)

        // ASSERTION
        val expected = ValidationResult(isSuccessful = false, error = UiText.LocalString(R.string.error_email_blank))
        assertEquals(expected.isSuccessful, actual.isSuccessful)
        assertEquals((expected.error as UiText.LocalString).id, (actual.error as UiText.LocalString).id)
    }

    @Test
    fun `Empty email is not valid and returns error text for blank email`() {
        // GIVEN
        val name = ""

        // ACTION
        val actual = validateEmail.invoke(name)

        // ASSERTION
        val expected = ValidationResult(isSuccessful = false, error = UiText.LocalString(R.string.error_email_blank))
        assertEquals(expected.isSuccessful, actual.isSuccessful)
        assertEquals((expected.error as UiText.LocalString).id, (actual.error as UiText.LocalString).id)
    }

    @Test
    fun `Email without @ character is not valid and returns error text for invalid email`() {
        // GIVEN
        val name = "abcde.ch"

        // ACTION
        val actual = validateEmail.invoke(name)

        // ASSERTION
        val expected = ValidationResult(isSuccessful = false, error = UiText.LocalString(R.string.error_email_not_valid))
        assertEquals(expected.isSuccessful, actual.isSuccessful)
        assertEquals((expected.error as UiText.LocalString).id, (actual.error as UiText.LocalString).id)
    }
}