@CucumberPetStoreTests
Feature: PetStoreTests

  Scenario: Test automation PetStore
    Given Open test automation PetStore
    When Test addNewPet
    When updatePet
    When deletePet
    When Test getIdNamePet

  Scenario: Test perfomance PetStore
    Given Open test perfomance PetStore
    When Test checkTime
    When Test multipleRequests
    When Test checkThreads
    When Test simulateStress
    When Test requestsPerSecond


