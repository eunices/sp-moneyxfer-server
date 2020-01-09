# Readme for moneyXfer web services app

This is a moneyXfer web services app that allows you to transfer money to others. Create for PDC2 of Specialist Diploma in Mobile Applications.

## Student details
Name: Eunice Soh

## For assignment submission purposes
1. Eclipse Java Project archive (zip file): **ENTIRE FOLDER**
2. MySQL Export (Data and Structure): `data_dump.sql`
3. Postman collection with link to the various APIs to test: `postman_collection.json`

Authentication is using JWT.

## App folder structure

It is RESTful and follows Tiered Application Design consisting of Java `src/`:

- Web services (routes) `resources/` ~~or `api/`~~ involving the routing of the APIs
- Models (data objects) `models/`  ~~or `entities/`~~ involving the data structure of the models
- Data persistence (database connection) `db/` ~~or `database/`~~ 
- Business logic `managers/` or ~~`services/`~~ involving the models

With authentication `auth/` and a "main" application file `application/`.

## Endpoints

- POST `/users` Signup
- GET `/users/<userId>` Get profile information
- GET `/v2/users/<userId>` Get profile information (version 2)
- POST `/auth` Login
- POST `/transactions` Transactions
  ```json
  { 
      "Amount": 100,
      "Currency": "SGD",
      "BankAccount": <bankAccNo>,
      "RecipientId": <userid>,
      "SenderId": <userid>
  }
  ```
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

### Profile page
- Concepts learnt:
  - Content negotiation: to send in json/ xml
  - Versioning of API
  - Object oriented programming using models
- Created two versions, one with all the data (transactions and contacts), the other providing links.