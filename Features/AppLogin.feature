
@tag
Feature: User Login

    Scenario: User should be able to login successfully with credentials
    Given Load Login page
    When Enter Valid username and password
    Then User should be succesfully logged into home page

    Scenario: Regsiter a patient
    Given Load Login page
    When Enter Valid username and password
    And User should be succesfully logged into home page
    And Click on Register a patient tile
    And Enter demographics details
    And Enter contactinformation
    And Enter retaionship details
    Then Clicks on confirm button Patient should be register successfully
    
    Scenario: Update a patient
    Given Load Login page
    When Enter Valid username and password
    And User should be succesfully logged into home page
    And Click on Find Patient Record
    And Enter search by patient name
    And Select search patient
    And Click on edit
    And Update patient name and save
    Then Clicks on confirm and patient name should be update successfully
