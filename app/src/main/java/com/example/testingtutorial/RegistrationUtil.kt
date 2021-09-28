package com.example.testingtutorial

object RegistrationUtil {

    private var existingUsers = listOf("peter","simon")

    /**
     * the input is not valid if the
     * ...userName / Password is empty
     * ...userName is not an existing user
     * ...confirm password and password are not same
     * ...password is contains less than 2 digits
     */
    fun validateRegistrationInput(
        userName : String,
        password : String,
        confirmPassword : String
    ) : Boolean{

        if(userName.isBlank() || password.isBlank()) return false
        if(userName in existingUsers) return false
        if(password != confirmPassword) return false
        if(password.length < 2) return false


        return true
    }
}