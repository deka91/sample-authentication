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
class ValidateNameTest {

    private lateinit var validateName: ValidateName

    @Before
    fun setup() {
        validateName = ValidateName()
    }

    @Test
    fun `Blank name is not valid and returns error text for blank name`() {
        // GIVEN
        val name = "  "

        // ACTION
        val actual = validateName(name)

        // ASSERTION
        val expected = ValidationResult(isSuccessful = false, error = UiText.LocalString(R.string.error_name_blank))
        assertEquals(expected.isSuccessful, actual.isSuccessful)
        assertEquals((expected.error as UiText.LocalString).id, (actual.error as UiText.LocalString).id)
    }

    @Test
    fun `Empty name is not valid and returns error text for blank name`() {
        // GIVEN
        val name = ""

        // ACTION
        val actual = validateName(name)

        // ASSERTION
        val expected = ValidationResult(isSuccessful = false, error = UiText.LocalString(R.string.error_name_blank))
        assertEquals(expected.isSuccessful, actual.isSuccessful)
        assertEquals((expected.error as UiText.LocalString).id, (actual.error as UiText.LocalString).id)
    }

    @Test
    fun `Name with 1 character is valid and returns success and no error text`() {
        // GIVEN
        val name = "a"

        // ACTION
        val actual = validateName(name)

        // ASSERTION
        val expected = ValidationResult(isSuccessful = true, error = null)
        assertEquals(expected.isSuccessful, actual.isSuccessful)
        assertEquals(expected.error, expected.error)
    }

    @Test
    fun `Name with more than 1 character is valid and returns success and no error text`() {
        // GIVEN
        val name = "abcd"

        // ACTION
        val actual = validateName(name)

        // ASSERTION
        val expected = ValidationResult(isSuccessful = true, error = null)
        assertEquals(expected.isSuccessful, actual.isSuccessful)
        assertEquals(expected.error, expected.error)
    }

}