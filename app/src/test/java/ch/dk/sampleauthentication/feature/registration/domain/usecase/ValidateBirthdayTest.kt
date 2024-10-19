package ch.dk.sampleauthentication.feature.registration.domain.usecase

import ch.dk.sampleauthentication.R
import ch.dk.sampleauthentication.core.presentation.util.UiText
import ch.dk.sampleauthentication.feature.registration.domain.validation.ValidationResult
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Created by Deniz Kalem on 19.10.2024
 */
class ValidateBirthdayTest {

    private lateinit var validateBirthday: ValidateBirthday

    @Before
    fun setup() {
        validateBirthday = ValidateBirthday()
    }

    @Test
    fun `Blank birthday is not valid and returns error text for blank birthday`() {
        // GIVEN
        val name = "  "

        // ACTION
        val actual = validateBirthday.invoke(name)

        // ASSERTION
        val expected = ValidationResult(isSuccessful = false, error = UiText.LocalString(R.string.error_birthday_blank))
        assertEquals(expected.isSuccessful, actual.isSuccessful)
        assertEquals((expected.error as UiText.LocalString).id, (actual.error as UiText.LocalString).id)
    }

    @Test
    fun `Empty birthday is not valid and returns error text for blank birthday`() {
        // GIVEN
        val name = ""

        // ACTION
        val actual = validateBirthday.invoke(name)

        // ASSERTION
        val expected = ValidationResult(isSuccessful = false, error = UiText.LocalString(R.string.error_birthday_blank))
        assertEquals(expected.isSuccessful, actual.isSuccessful)
        assertEquals((expected.error as UiText.LocalString).id, (actual.error as UiText.LocalString).id)
    }

    @Test
    fun `Birthday with wrong format is not valid and returns error text for invalid birthday`() {
        // GIVEN
        val name = "1.1.99"

        // ACTION
        val actual = validateBirthday.invoke(name)

        // ASSERTION
        val expected = ValidationResult(isSuccessful = false, error = UiText.LocalString(R.string.error_birthday_not_valid))
        assertEquals(expected.isSuccessful, actual.isSuccessful)
        assertEquals((expected.error as UiText.LocalString).id, (actual.error as UiText.LocalString).id)
    }
}