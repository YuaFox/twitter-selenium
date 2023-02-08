# Twitter-Selenium

Fuck apis, use Twitter without api

## Requirements

- Firefox

## Current features
- Tweet
- Read recent replies

## Compile

### Linux
```./gradlew jar```

## How to use with code

Create a client
```java
TwitterClient client = TwitterClient.createClient(true);
```

Use the methods available to tweet
```java
client.sendTweet(new Tweet().setText("Hello Selenium!").setFile(new File("fox.jpg")));
```