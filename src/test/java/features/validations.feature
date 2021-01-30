Feature: Validating Title API's

Scenario: Verify retrieving title is being succesfully returned using GetTitleAPI
	Given GetTitle Api headers and "rem alias distinctio quo quis" query param
	When user calls GetUserId Api with get http request
	Then all the posts returned have the same author
	