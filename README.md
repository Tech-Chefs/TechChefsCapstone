# Tech-Chefs

### Summary

The purpose of our app is for users to be able to post recipes online and receive a shopping list based on recipes that they choose to make.

#### Plan:

Create Plan (4hrs)

- Creating the wireframe, schedule of events, communicating team name, and setting up repositories.

### Components:

#### Database (4hrs)

#### Tables:

- Main tables
  - Ingredient
    - Represents a type of ingredient that can be added to a recipe or shopping list
    - Categorized to account for dietary restrictions
  - Recipe
    - Recipes submitted by users
  - Unit
    - A unit of measurement (volume or weight)
  - User
    - Represents an account on the app
  - Shopping List
    - A curated shopping list based on a specific set of recipes
- Merged tables
  - Unit-Unit
    - Represents the relationship between two units of the same type (volume/weight)
  - Ingredient-Unit-Unit
    - Represents the relationship between a volume unit and a weight unit based on a given ingredient (could possibly be replaced with table that specifies unit relationship, i.e. oz-fl.oz.)
  - Recipe-Ingredient
    - The measurement of a specific ingredient within a recipe
  - List Item
    - The amount of a specific ingredient in the list

#### Data

Repositories:

- Ingredient Repository
  - findAll
    - Inp: none
    - Out: List \<Ingredient\>
    - Returns all ingredients from the database
  - findById
    - Inp: int
    - Out: Ingredient
    - Returns an ingredient given its id
  - Add
    - inp: Ingredient
    - Out: Ingredient
    - Adds an ingredient to the database; returns the ingredient with its new id if successful and null otherwise
  - Update
    - Inp: Ingredient
    - Out: boolean
    - Updates an ingredient in the database; returns value of success
  - DeleteById
    - Inp: int
    - Out: boolean
    - Deletes an ingredient from the database; returns value of success
- Recipe Repository
  - findAll
    - Inp: none
    - Out: List \<Recipe\>
    - Returns all recipes from the database
  - findById
    - Inp: int
    - Out: Recipe
    - Returns a recipe given its id
  - findByUserId
    - Inp: int
    - Out: List \<Recipe\>
    - Returns all recipes submitted by a specific user
  - Add
    - inp: Recipe
    - Out: Recipe
    - Adds a recipe to the database; returns the recipe with its new id if successful and null otherwise
  - Update
    - Inp: Recipe
    - Out: boolean
    - Updates a recipe in the database; returns value of success
  - DeleteById
    - Inp: int
    - Out: boolean
    - Deletes a recipe from the database; returns value of success

#### Service (12hr)

- Recipe Service (3hr)
  - Full CRUD implementation
- Ingredient Service (3hr)
  - Full CRUD implementation
- UserService (3hr)
  - Full CRUD implementation
- ShoppingListService (3hr)
  - Full CRUD implementation

#### Controllers (12hr)

- Recipes Controller (3hr)
- Ingredient Controller (3hr)
- Shopping List Controller (3hr)
- User Controller (3hr)

#### Models (12hr)

- Ingredient (3hr)
  - Variables:
    - Id: Unique identifier of an ingredient
    - Name: Name of an ingredient
    - Sub-Ingredients: List of ingredients that are considered a "type" of this ingredient
    - Boolean flags to represent dietary restrictions of an ingredient
- Recipe (3hr)
  - Variables:
    - Id: Unique identifier of a recipe
    - Name: Name of a recipe
    - Ingredients: Map with keys of ingredients and values of measurements
    - Steps: List of directions for the recipe
- Unit (3hr)
  - Variables:
    - Id: Unique identifier of a unit
    - Short Name: Shortened name of a unit (i.e. tsp)
    - Long Name: Longer name of a unit (i.e. teaspoon)
- Measurement (3hr)
  - Variables:
    - Unit: The specific unit of measurement
    - Measurement: The amount of specified units

#### Back-End Security (4hr)

#### Testing (12hr)

- Recipe CRUD test (3hr)
- Ingredient CRUD test (3hr)
- Shopping List CRUD test (3hr)
- User Service CRUD test (3hr)

#### Front-End Design (20hr)

- html (2hr)
- View all page (4hr)
- Recipe page (4hr)
- Login page (2hr)
- Grocery list (4hr)

- Unique aspect to implement: Animation (GreenSock, etc.) (4hr)

#### Front-End Security(4hr)

#### Front-End HTTP Request Testing (2hr)

- Recipe CRUD test (.5hr)

- Ingredient CRUD test (.5hr)
- Shopping List CRUD test (.5hr)
- User Service CRUD test (.5hr)

________________________

**Technical Requirements**

1. Manage 4-7 database tables (entities) that are independent concepts. A simple bridge table doesn't count.
2. Relational database for data management
3. Spring Boot, MVC (@RestController), JdbcTemplate, Testing
4. An HTML and CSS UI built with React
5. Sensible layering and pattern choices
6. At least one UI secured by role
7. A full test suite that covers the domain and data layers.

**Must Implement One of These:**

- deployment to a cloud service: AWS, Azure, GCP
- JavaScript map API
- JavaScript productivity libraries: Lodash, Dinero.js, or even an existing but unfamiliar library like Intl.DateTimeFormat
- alternative JavaScript front-end frameworks: Angular, Vue.js, Svelte
- TypeScript
- WebSocket communication: used Stomp, but Stomp isn't necessary
- animations: used GreenSock, but there are many alternatives
- SVG-enhanced UI
- Java productivity libraries: Lombok, Mockito (advanced usage), or Guava
- in-browser audio
- JPA
- alternative data stores: PostgreSQL, MongoDB, Hadoop
- hardware-based projects: Raspberry Pi smart mirror, Arduino-based home security
- modest machine learning: used an open-source chess move evaluator, be careful with this one -- it's not possible to learn even simple machine learning from scratch in a couple of weeks while you're building a substantial application
- web scraping

**Deliverables:**

1. A schedule of concrete tasks (at most 4 hours per task) that represent all work required to finish your project along with task statuses
2. Diagrams: database schema, class, layer, flow
3. Wireframes: roughly sketch your UI and how one view transitions to another. You can also use design tools to create wireframes.
4. A short presentation, 4 to 6 slides, describing who you are, how you found programming, and your project
5. Complete project source code free of compiler errors
6. A schema creation script along with any DML for data needed to run the application (security roles, default data, etc)
7. If it isn't straight-forward, instructions to set up and run your application
8. A complete test suite with all tests passing