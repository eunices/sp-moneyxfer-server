# Readme for moneyXfer web services app

This is a moneyXfer web services app that allows you to transfer money to others. Create for PDC2 of Specialist Diploma in Mobile Applications.

## Student details
Soh Jia Yu, Eunice (p7265742)

## For assignment submission purposes
1. Eclipse Java Project archive (zip file): **ENTIRE FOLDER**
2. MySQL Export (Data and Structure): `data_dump.sql`
3. Postman collection with link to the various APIs to test: `postman.json`

Authentication is using JWT.

## App folder structure

It is RESTful and follows Tiered Application Design consisting of Java `src/`:

- Web services (routes) `resources/` ~~or `api/`~~ involving the routing of the APIs
- Models (data objects) `models/`  ~~or `entities/`~~ involving the data structure of the models
- Data persistence (database connection) `db/` ~~or `database/`~~ 
- Business logic ~~`managers/`~~ or `services/` involving the models

With authentication `auth/` and a "main" application file `application/`.

## Deployment on heroku using ClearDB

https://young-refuge-19913.herokuapp.com/

- To deploy use `mvn clean heroku:deploy-war`

## Endpoints

- POST `/users` Signup
- POST `/auth` Login
- POST `/transactions` Add transactions
  ```json
    {
      "amount": 142,
      "bankAccount": "testingbankaccount2",
      "senderId": "bar@test.com",
      "recipientId": "foo@test.com"
    }
  ```
- PUT `/transactions` Update transactions
  ```json
    {
      "amount": 123,
      "bankAccount": "sdfasdfasdfads",
      "senderId": "bar@test.com",
      "recipientId": "foo@test.com"
    }
  ```
- GET `/users/<userId>` Get profile information
- GET `/v2/users/<userId>` Get profile information (version 2)
- GET `/v2/users/<userId>/transactions?page=<page>&pageSize=<pageSize>` Get transactions

- GET `/v2/users/<userId>/contacts` Get contacts
- POST `/v2/users/<userId>/contacts` Add contact
- DELETE `/users/<userId>/contact/<contactIdToDelete>` Delete contact
- POST `/contact` Add contact

## Learning points

### To create new project 
- Create new dynamic web project, select `web.xml` option
- Convert to maven project which creates a `pom.xml`
- Add dependencies to `pom.xml`
- Config `web-inf.xml` file with routes and servlet 
- Add Java class in `src` as route and application
- Add project to glassfish server

### Authentication
- Can use basic or JWT authentication
- Filters must be defined in the app

### Profile
- Concepts learnt:
  - Content negotiation: to send in json/ xml
  - Versioning of API
  - Object oriented programming using models
- Created two versions, one with all the data (transactions and contacts), the other providing links.

### Transactions and contacts
- Prevent duplicate entries for contacts

## Todos:
- Fix: Date time format for transactionDate
- Fix: Consuming XML for POSTing new user
- Improvement: Array of contacts to be added
