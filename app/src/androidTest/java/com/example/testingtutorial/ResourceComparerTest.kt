package com.example.testingtutorial

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test



class ResourceComparerTest{

    /**
     * Initializing using global scope can affect your Object if there is something like a counter
     * inside the class, and a test might increase it while other test needs the default value
     * to avoid this, use the before and after annotation
     */
    // private var resourceComparer = ResourceComparer()

    /**
     * use the following code pattern
     * @Before helps use to run a code before any of the line of test code runs
     * @After helps to run a code after we are done with that test function
     */

    lateinit var   resourceComparer : ResourceComparer

    @Before
    fun setup(){
        resourceComparer = ResourceComparer()
    }




    @Test
    fun stringResourceSameAsGivenStringReturnTrue(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComparer.isEqual(context, R.string.app_name, "TestingTutorial" )
       assertThat(result).isTrue()
    }

    @Test
    fun stringResourceSameAsGivenStringReturnFalse(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComparer.isEqual(context, R.string.app_name, "testingTutorialnew" )
        assertThat(result).isFalse()

    }
}