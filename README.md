# vrc

Java implementation based on [vrchatapi.github.io](https://github.com/vrchatapi/vrchatapi.github.io)

## Disclaimer

This is the official response of the VRChat Team (from Tupper more specifically) on the usage of the VRChat API.

> Use of the API using applications other than the approved methods (website, VRChat application) are not officially supported. You may use the API for your own application, but keep these guidelines in mind:
> * We do not provide documentation or support for the API.
> * Do not make queries to the API more than once per 60 seconds.
> * Abuse of the API may result in account termination.
> * Access to API endpoints may break at any given time, with no warning.

## Description

This package provides an API client that can be used against the VRChat API.
Not all requests are implemented yet, have a look at the *VRChatApiClient* interface to see all currently available methods.

### Current version

		<dependency>
			<groupId>com.github.Ox0017.vrc</groupId>
			<artifactId>vrc-api</artifactId>
			<version>1.10-SNAPSHOT</version>
		</dependency>

### Build

		mvn clean install

requires [maven](https://maven.apache.org/download.cgi)

### Usage

Example:

		VRChatApiClient client = new VRChatApiClientImpl();
		
		VrcRequestContext context = new VrcRequestContext();
		context.setUsername("");
		context.setPassword("");
		
		CurrentUserDto currentUser = client.getCurrentUser(context);

The constructor supports also providing a custom httpClient if required (the default constructor creates an httpClient with 2000 ms timeout)
