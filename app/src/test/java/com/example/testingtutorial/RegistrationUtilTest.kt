package com.example.testingtutorial
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtilTest {

    @Test
    fun `empty username return false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            " ",
            "123",
            "123"
        )

        assertThat(result).isFalse()

    }

    @Test
    fun `valid username and correctly repeated password`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "philip",
            "123",
            "123"
        )

        assertThat(result).isTrue()
    }

    @Test
    fun `already existing user return false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "peter",
            "123",
            "123"
        )

        assertThat(result).isFalse()
    }

    @Test
    fun `empty password return false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "philip",
            "",
            ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `incorrect password return false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "philip",
            "123",
            "12"
        )
        assertThat(result).isFalse()

    }

    @Test
    fun `password contain less than 2 digit return false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "philip",
            "1",
            "1"
        )
        assertThat(result).isFalse()
    }

}