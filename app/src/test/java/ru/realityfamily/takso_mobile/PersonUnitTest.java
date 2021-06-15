package ru.realityfamily.takso_mobile;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Response;
import ru.realityfamily.takso_mobile.Models.AuthData;
import ru.realityfamily.takso_mobile.Models.Person;
import ru.realityfamily.takso_mobile.Network.MyRetrofit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class PersonUnitTest {
    @Test
    public void getDriverInfo() {
        AuthData authData = AuthData.getInstance();
        authData.setLogin("driver");
        authData.setPassword("driver");

        try {
            Response<AuthData> responseToken = new MyRetrofit().login(authData).execute();
            assertTrue(responseToken.isSuccessful());
            assertNotNull(responseToken.body().getToken());
            authData.setToken(responseToken.body().getToken());

            Response<Person> responsePerson = new MyRetrofit().getDriverInfo(authData.getToken()).execute();
            assertTrue(responsePerson.isSuccessful());
            assertNotNull(responsePerson.body().getName());
            assertEquals("Test", responsePerson.body().getName());
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void postDriverInfo() {
        AuthData authData = AuthData.getInstance();
        authData.setLogin("driver");
        authData.setPassword("driver");

        Person person = Person.getInstance();
        person.setName("Test");
        person.setLastName("Testov");
        person.setCodeName("123");

        try {
            Response<AuthData> responseToken = new MyRetrofit().login(authData).execute();
            assertTrue(responseToken.isSuccessful());
            assertNotNull(responseToken.body().getToken());
            authData.setToken(responseToken.body().getToken());

            Response<Person> responsePerson = new MyRetrofit().postDriverInfo(authData.getToken(), person).execute();
            assertTrue(responsePerson.isSuccessful());
            assertNotNull(responsePerson.body().getCodeName());
            assertEquals("123", responsePerson.body().getCodeName());
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }
}
