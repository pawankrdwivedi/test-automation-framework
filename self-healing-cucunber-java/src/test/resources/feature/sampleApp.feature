Feature: Login page feature

  Scenario: Clear the form and resubmit the form
    Given user is on sample page
    When user selects title "Mr."
    And user enters first name "Pawan"
    And user enters last name "Dwivedi"
    And user enters email "pawan.dwivedi@irissoftware.com"
    And user enters address "Plot 42, Sector 142, Noida (U.P.)"
    And user enters phone "+91 9865012345"
    And user enters zip code "210310"
    And select receive company notification
    And click on Submit
