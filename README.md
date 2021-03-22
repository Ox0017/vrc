# VRChatApiClient

Java implementation based on [vrchatapi.github.io](https://github.com/vrchatapi/vrchatapi.github.io)

## Disclaimer

Using this API is allowed for non malicious usage. Abuse of the API may result in account termination!

This is the official response of the VRChat Team (from Tupper more specifically) on the usage of the VRChat API.

> Use of the API using applications other than the approved methods (website, VRChat application) are not officially supported. You may use the API for your own application, but keep these guidelines in mind:
> * We do not provide documentation or support for the API.
> * Do not make queries to the API more than once per 60 seconds.
> * Abuse of the API may result in account termination.
> * Access to API endpoints may break at any given time, with no warning.

## Description

This package provides an API client that can be used against the VRChat API.
Have a look at the [VRChatApiClient](https://github.com/Ox0017/vrc/blob/main/vrc-api/src/main/java/com/github/Ox0017/vrc/VRChatApiClient.java) interface to see all available methods.

### Usage

Example:

	VRChatApiClient client = new VRChatApiClientImpl();
	
	VrcRequestContext context = new VrcRequestContext();
	context.setUsername("username");
	context.setPassword("password");
	
	CurrentUserDto currentUser = client.getCurrentUser(context);

The constructor supports also providing a custom httpClient if required (the default constructor creates an httpClient with 2000 ms timeout)
