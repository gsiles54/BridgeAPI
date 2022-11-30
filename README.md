# BridgeAPI

BridgeAPI is an REST API designed to allowed the user to create trello cards via POST methods on the endpoints that are exposed.
Following the provided exercise, you can call the API by doing a POST request on the "/" endpoint.

3 types of cards have been defined

-Bug

-ManualTask

-Issue


You can send a json body format with any of those tasks and the endpoint is going to take care of the parsing and creation of the Trello card.

Althought for clarity 3 more endpoints have been added such as

https://localhost:8080/bug

https://localhost:8080/issue

https://localhost:8080/task


In this case, each endpoint is expecting their  own task counterpart, but this also allows more clarity and variety to the options that have been presented.
![image](https://user-images.githubusercontent.com/38226033/204690021-9121c745-8ed4-4e94-990a-f465323a4ac0.png)


Each request must be done like in the example.

You need to add some parameters in the project before getting started

Inside the "application.properties" file you will find the following.

-trello.boardId: Id of the board that you are going to use. If you are not sure what your id is then you can add ".json" at the end of your board url
Example : https://trello.com/b/EnpRiV5p/tableroprueba.json

-trello.token: token of your API trello, you need to follow the instruction on https://developer.atlassian.com/cloud/trello/guides/rest-api/api-introduction/ to get your token

-trello.publicKey: the publicKey of your API trello, same instructions that before.

PostMAN is also a good choice to perform POST request against this service.

Once the Springboot application is started you can send this json example

{
    "type" : "bug",
    "description" : "example"
}

To localhost:8080/bug

![image](https://user-images.githubusercontent.com/38226033/204694207-d75cb6b5-52e4-4615-95f3-104f11b3c6a4.png)


You would get the title of the new card once the operation is completed.
