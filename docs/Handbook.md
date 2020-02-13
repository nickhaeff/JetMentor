JetMentor Team Handbook

Overview :
Using Android Studio for our project development and github for version control.

Version Control Standards :
-Branches
  + One branch per issue/ticket
  + Branch names are issue/ticket ID followed by title/name in UpperCamelCase
    ex : 0023AddLoginValidation
-Commit messages
  + required for commits
  + should be concise description of changes made
-Pull Request (PR)
  + When issue acceptance criteria is met PR is made.
  + PR title starts by referencing issue ID.
  + Description should touch on each acceptance criteria and relevant details to completion.
  + PR's should be approved by at least 2 members before being merged.
-Code Standards :
  + Variable names = Our version of Hungarian Case
    ex = integer -> i_BankBalance string -> s_Name
  + Function names = UpperCamelCase
    ex = GetBankBalance()
  + Class Definitions = UpperCamelCase
    ex = Animal PictureFrame
  + All names should be descriptive and concise. Long names are better than nondescriptive ones.
  + Align curly braces on same indent
    ex =
      if()
        {

        }
  + Function parameters should be each parameter on a new line unless entire function call/description can be read without scrolling.
    ex =
      GetBankBalance(
        acccountNumber,
        pinNumber,
        userName)

Code Review :
