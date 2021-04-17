package id.conversion.app.ui.pin

import org.junit.Assert
import org.junit.Test

class PinCodeViewModelTest {

    private var pinCode: String = "123456"

    @Test
    fun isValidPinCode() {
        Assert.assertEquals(6, pinCode.length)
    }

    @Test
    fun isPinCodeErased() {
        val expectedResult = (pinCode.length - 1)
        pinCode.dropLast(1)

        Assert.assertEquals(expectedResult, pinCode.length)
    }

    @Test
    fun isPinCodeCleared() {
        pinCode = ""
        Assert.assertEquals(0, pinCode.length)
    }

}