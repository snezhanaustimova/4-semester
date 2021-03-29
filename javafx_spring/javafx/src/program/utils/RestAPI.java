package program.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import program.models.Person;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RestAPI {
    private static final String SERVER_URL = "http://127.0.0.1:8080";
    private static final String SERVER_GET_PERSONS = SERVER_URL + "/api/persons";

    public void createPerson(Person person) {
        HttpClass.postRequest(SERVER_GET_PERSONS, person.toJson());
    }

    public List<Person> getPersons() {
        List<Person> result = new ArrayList<>();
        String buffer = HttpClass.getRequest(SERVER_GET_PERSONS);
        JsonArray jsonResult = JsonParser.parseString(buffer).getAsJsonArray();

        for (int i = 0; i < jsonResult.size(); i++) {
            JsonObject currentPerson = jsonResult.get(i).getAsJsonObject();
            String firstName = currentPerson.get("firstName").getAsString();
            String lastName = currentPerson.get("lastName").getAsString();
            String city = currentPerson.get("city").getAsString();
            String street = currentPerson.get("street").getAsString();
            Integer postalCode = currentPerson.get("postalCode").getAsInt();
            System.out.println(postalCode);
            LocalDate birthday = DateUtil.parse(currentPerson.get("birthday").getAsString());
            Integer id = currentPerson.get("id").getAsInt();

            Person newPerson = new Person(firstName, lastName, city, street, postalCode, birthday, id);
            result.add(newPerson);
        }
        return result;
    }

    public void updatePerson(Person person) {
        Integer id = person.getId();
        String jsonString = person.toJson();
        HttpClass.putRequest(SERVER_GET_PERSONS + "/" + id, jsonString);
    }

    public boolean deletePerson(Person person) {
        Integer id = person.getId();
        if (id == null)
            return false;
        return HttpClass.deleteRequest(SERVER_GET_PERSONS + "/" + id);
    }
}
