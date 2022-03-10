Feature: Search
  As a user,
  I want to use the Search

  Scenario Outline: I want to be able to search via the <Scenario Name>
    Given I opened the Search page
    When I search for 'Digital' via the <Scenario Name>
    Then the Search Results page is opened
    And search results are shown
    Examples:
      | Scenario Name       |
      | navigation bar      |
      | search results page |

  Scenario: I want to get search results matching my input
    Given I opened the Search page
    When I search for 'Digital' via the search results page
    Then the results match the search term 'Digital'

  Scenario: I want to get a meaningful error message if no results could be found
    Given I opened the Search page
    When I search for 'ddsds asd asdas' via the search results page
    Then the error message 'Your search returned no results, please try with a different search term or phrase.' is shown

#  Scenario: I want to get a meaningful error message if no results could be found for a different term
#    Given I opened the Search page
#    When I search for 'ddsds asd asdacco' via the search results page
#    Then the error message 'Your search returned no results, please try with a different search term or phrase.' is shown
