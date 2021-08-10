package com.dms.pmsandroid

import com.dms.pmsandroid.feature.login.model.LoginRequest
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MockTest {
    @Test
    fun loginRequestTest(){
        val p : LoginRequest = mock(LoginRequest::class.java)
        `when`(p.email).thenReturn("test@naver.com")
        `when`(p.password).thenReturn("dddddddd")

        assertEquals(p.email,"test@naver.com")
        assertEquals(p.password,"dddddddd")
    }


}