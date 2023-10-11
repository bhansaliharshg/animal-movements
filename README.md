# Programming challenge

## Background
In food animal systems, animals move to different farms as they age. Each farm has a unique ID and must keep a record of the movement of animals from one farm to another. Here, we present some fictitious records of movements among pig farms.

*Description of the data folder* 

*	*movements*: all records of animal movements 
    -  new_originpremid - column with the ID of the origin farm 
    -  new_destinationpremid - column with the ID of the destination farm 
    -  new_numitemsmovedcolumn - column with the number of moved animals

*	*population*: complete list of the farms
    -  premiseid - column with the ID of the farms
    -  total_animal - column with the current number of animals for the farm


## Challenge
The challenge is to create a system to visualize the movement records. This
system have to follow the requirements bellow:

- Has to be composed of 3 components: a REST API, a SPA WEB client, and a
  relational database;
- The relational database can be any database that you like, PostgreSQL, MariaDB
  etc..;
- The data provided in this repo should be imported into the database;
- The REST API has to written in Java, Python or Typescript. It can use any
  framework/library that you desmire;
- The Web Client have to written in Typecript, and you can use any *SPA
  framework/library* that you desire, ex Angular, React, etc...;
- Your submitted project should include instructions on how to run it;

The submitted project will be evaluated considering your experience. For example, a
person with a background in backend development will be evaluated with higher
expectations of the API and database code. A UI person will be evaluated with
higher expectations on the design of the UI.

Bonus points will be awarded for creativity and implementing things outside the
requirements, such as:
- having an authentication in the system
- Using docker
- Having a script to run all components
- Importing the supplied data into a well normalized schema

## Running the application

- Clone the project -

```
git clone https://github.com/bhansaliharshg/animal-movements

cd animal-movements
```

- The application is dockerized so we can directly run the appication using - 
```
docker compose build
docker compose up -d
```

- To stop the container use - 
```
docker-compose down
```

## Entities

The Spring boot (backend) uses two entities "Farm" and "Movement"

### Farm Entity

#### Attributes

The Farm class includes the following attributes:

- premiseId: The unique identifier for the farm.
- name: The name of the farm.
- address: The street address of the farm.
- city: The city where the farm is located.
- state: The state where the farm is located.
- postalCode: The postal code or ZIP code of the farm's location.
- latitude: The latitude coordinates of the farm's location.
- longitude: The longitude coordinates of the farm's location.
- totalAnimals: The total number of animals on the farm.

### Movement Entity

#### Attributes

The Movement class includes the following attributes:

- id: A unique identifier for the movement event.
- account: The account associated with the movement.
- movementReason: The reason for the movement.
- species: The species involved in the movement.
- origin: The farm from which the movement originates. This is a many-to-one relationship with the Farm class.
- destination: The farm to which the movement is destined. This is a many-to-one relationship with the Farm class.
- numberOfItemsMoved: The number of items being moved.
- shipmentDate: The date of the shipment.

## API Endpoints

### Get All Movements

- URL: 
  - `/movements`  
- Method: `GET`
- Response:
  - Status Code: 200 (OK) if successful, 400 (Bad Request) if there are errors.

### Get ALL Farms

- URL: `/farms`
- Method: `GET`
- Response:
  - Status Code: 200 (OK) if successful, 400 (Bad Request) if there are validation errors.

### Search Farm

- URLs: 
  - `/farm/{premiseId}`  
- Method: `GET`
- Query Parameters:
  - `premiseId` (optional, string): Filter farms by premiseId.
- Response:
  - Status Code: 200 (OK) if successful, 400 (Bad Request) if there are errors.
 
### Images

<img width="500" alt="img1" src="https://github.com/bhansaliharshg/animal-movements/blob/feff86470fad6e590d82f6eb8be9155c7ac00f89/screenshots/Farm%20Details%20Modal.png">
