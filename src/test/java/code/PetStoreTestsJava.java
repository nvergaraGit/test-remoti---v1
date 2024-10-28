package code;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import pages.Elements;

import static io.restassured.config.LogConfig.logConfig;
import static io.restassured.config.RestAssuredConfig.config;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class PetStoreTestsJava {

    Elements elementsObject = new Elements();
    public PetStoreTestsJava() throws Throwable {

    }

    @Given("^Open test automation PetStore$")
    public void open_test_automation_PetStore(){
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

    }

    @When("^Test getIdNamePet$")
    public void test_getIdNamePet(){
        RestAssured.
                given().
                when().
                get("/pet/1").
                then().
                statusCode(200).
                body("id", equalTo(1)).
                body("name", equalTo("doggie"));
    }

    @When("^Test addNewPet$")
    public void test_addNewPet(){
        String newPet = "{ \"id\": 12345, \"name\": \"MyNewPet\", \"status\": \"available\" }";
        RestAssured.
        given().
                header("Content-Type", "application/json").
                body(newPet).
                when().
                post("/pet").
                then().
                statusCode(200).
                body("id", equalTo(12345)).
                body("name", equalTo("MyNewPet")).
                body("status", equalTo("available"));
    }

    @When("^updatePet$")
    public void updatepet(){
        String updatedPet = "{ \"id\": 12345, \"name\": \"MyUpdatedPet\", \"status\": \"sold\" }";

        RestAssured.
        given().
                header("Content-Type", "application/json").
                body(updatedPet).
                when().
                put("/pet").
                then().
                statusCode(200).
                body("id", equalTo(12345)).
                body("name", equalTo("MyUpdatedPet")).
                body("status", equalTo("sold"));

    }

    @When("^deletePet$")
    public void deletepet(){
        int petId = 12345;

        RestAssured.
        given().
                when().
                delete("/pet/" + petId).
                then().
                statusCode(200).
                body("message", equalTo(String.valueOf(petId)));

    }

    @Given("^Open test perfomance PetStore$")
    public void open_test_perfomance_PetStore(){
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @When("^Test checkTime$")
    public void test_checkTime(){

        // Realiza la solicitud y mide el tiempo de respuesta
        long responseTime = RestAssured
                .given()
                .when()
                .get("/pet/1")
                .time(); // Obtiene el tiempo en milisegundos

        System.out.println("Tiempo de respuesta: " + responseTime + " ms");

        // Puedes agregar una aserción para asegurarte de que el tiempo de respuesta sea aceptable
        assert responseTime < 2000 : "El tiempo de respuesta es mayor a 2 segundos";
    }

    @When("^Test multipleRequests$")
    public void test_multipleRequests(){

        int numberOfRequests = 100;
        long totalResponseTime = 0;

        // Enviar varias solicitudes secuenciales
        for (int i = 0; i < numberOfRequests; i++) {
            long responseTime = RestAssured
                    .given()
                    .when()
                    .get("/pet/1")
                    .time();

            totalResponseTime += responseTime;
            System.out.println("Solicitud " + (i + 1) + " - Tiempo de respuesta: " + responseTime + " ms");
        }

        long averageResponseTime = totalResponseTime / numberOfRequests;
        System.out.println("Tiempo de respuesta promedio: " + averageResponseTime + " ms");

        // Asegurarse de que el tiempo promedio de respuesta sea aceptable
        assert averageResponseTime < 2000 : "El tiempo de respuesta promedio es mayor a 2 segundos";
    }

    @When("^Test checkThreads$")
    public void test_checkThreads() throws InterruptedException {

        int numberOfThreads = 10; // Número de solicitudes concurrentes
        Thread[] threads = new Thread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(() -> {
                long responseTime = RestAssured
                        .given()
                        .when()
                        .get("/pet/1")
                        .time();
                System.out.println("Tiempo de respuesta en thread: " + responseTime + " ms");
            });

            threads[i].start(); // Iniciar cada thread
        }

        // Esperar a que todas las solicitudes terminen
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Todas las solicitudes concurrentes han terminado.");

    }

    @When("^Test simulateStress$")
    public void test_simulateStress() throws InterruptedException {

        int numberOfThreads = 50; // Simular una alta carga con 50 solicitudes concurrentes
        Thread[] threads = new Thread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(() -> {
                long responseTime = RestAssured
                        .given()
                        .when()
                        .get("/pet/1")
                        .time();
                System.out.println("Tiempo de respuesta en thread: " + responseTime + " ms");
            });

            threads[i].start(); // Iniciar cada thread
        }

        // Esperar a que todas las solicitudes terminen
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Prueba de estrés completada.");

    }

    @When("^Test requestsPerSecond$")
    public void test_requestsPerSecond(){

        int numberOfRequests = 100;
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < numberOfRequests; i++) {
            RestAssured.given().get("/pet/1");
        }

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        double requestsPerSecond = (numberOfRequests / (totalTime / 1000.0));

        System.out.println("Throughput: " + requestsPerSecond + " peticiones por segundo");

    }


}
