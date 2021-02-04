@RETIREMENT_CALC
Feature: KiwiSaver Retirement Calculator

  Scenario: To test all fields in the retirement calculator has information icon
    Given user navigates to KiwiSaver Retirement Calculator Page
    Then  user should see all the information icons beside each field

  Scenario Outline: To test all fields in the retirement calculator has information icon For 'Employed' Status
    Given user navigates to KiwiSaver Retirement Calculator Page
    When  user enters below information in the calculator
      | EmploymentStatus   |
      | <EmploymentStatus> |
    Then  user should see all the information icons beside each field

    Examples:
      | EmploymentStatus |
      | Employed         |

  Scenario Outline: To test all fields in the retirement calculator has information icon
    Given user navigates to KiwiSaver Retirement Calculator Page
    When  user clicks information icon besides <fieldName>
    Then  the message '<informationMessage>' is displayed below the <fieldName> field

    Examples:
      | fieldName   | informationMessage                                                                                        |
      | Current age | This calculator has an age limit of 64 years old as you need to be under the age of 65 to join KiwiSaver. |

  Scenario Outline: To test retirement calculator shows KiwiSaver Projections for all user selections
    Given user navigates to KiwiSaver Retirement Calculator Page
    When  user enters below information in the calculator
      | CurrentAge   | EmploymentStatus   | AnnualIncome   | KiwiSaverMemberContribution   | KiwiSaverBalance   | VoluntaryContributions   | Frequency   | RiskProfile   | SavingsGoal   |
      | <CurrentAge> | <EmploymentStatus> | <AnnualIncome> | <KiwiSaverMemberContribution> | <KiwiSaverBalance> | <VoluntaryContributions> | <Frequency> | <RiskProfile> | <SavingsGoal> |
    Then  show user what their KiwiSaver Projected balance would be at retirement

    Examples:
      | CurrentAge | EmploymentStatus | AnnualIncome | KiwiSaverMemberContribution | KiwiSaverBalance | VoluntaryContributions | Frequency   | RiskProfile  | SavingsGoal |
      | 30         | Employed         | 82000        | 4%                          |                  |                        |             | Defensive    |             |
      | 45         | Self-employed    |              |                             | 100000           | 90                     | Fortnightly | Conservative | 290000      |
      | 55         | Not employed     |              |                             | 140000           | 10                     | Annually    | Balanced     | 200000      |
