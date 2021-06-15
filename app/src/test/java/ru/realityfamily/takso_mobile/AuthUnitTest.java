package ru.realityfamily.takso_mobile;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.realityfamily.takso_mobile.DI.ServiceLocator;
import ru.realityfamily.takso_mobile.Models.AuthData;
import ru.realityfamily.takso_mobile.Network.MyRetrofit;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class AuthUnitTest {
    @Test
    public void CorrectAuth() {
        AuthData authData = AuthData.getInstance();
        authData.setLogin("driver");
        authData.setPassword("driver");

        try {
            Response<AuthData> response = new MyRetrofit().login(authData).execute();
            assertTrue(response.isSuccessful());
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void IncorrectAuth() {
        AuthData authData = AuthData.getInstance();
        authData.setLogin("admin");
        authData.setPassword("admin");

        try {
            Response<AuthData> response = new MyRetrofit().login(authData).execute();
            assertFalse(response.isSuccessful());
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }
}