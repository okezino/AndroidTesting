package com.example.testingtutorial

object HomeWork {
    /**
     * Fibonacci series
     * f(0) = 0
     * f(1) = 1
     * f(n) = f(n-2) + f(n-1)
      */

    fun fib(n : Int) : Long{

        if(n < 2) return n.toLong()

        var a = 0L
        var b = 1L
        var c = 1L
       for(i in 1..n-2){
            c = a + b
            a = b
            b = c
        }
        return c
    }

    /**
     * Matching bracket algorithm
     */

    fun matchingBracket(bracket : String) : Boolean{
        return (bracket.count { it == '(' } == bracket.count { it == ')' })
    }

}