**Restaurant Vote system**
----
This is education example of my REST API from the task below </br>

Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) **without frontend**.

The task is:

Build a voting system for deciding where to have lunch.

 * 2 types of users: admin and regular users
 * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
 * Menu changes each day (admins do the updates)
 * Users can vote on which restaurant they want to have lunch at
 * Only one vote counted per user
 * If user votes again the same day:
    - If it is before 11:00 we asume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides new menu each day.

As a result, provide a link to github repository.

It should contain the code and README.md with API documentation and curl commands to get data for voting and vote.

## REST API

##### The section below has public access  without  any credentions. It starts with $ROOT_PATH/rest/info.


### Show todays restaurants menus

  Returns a list of menu data about all updated  restaurants menus.
  Menu data is represented by list of  objects, which including short restaurant info and meals items. </br>
    
* **URL** :  `/rest/info`
* **Method:**  `GET`
* **Example Request:** `curl --location --request GET 'http://localhost:8080/voting/rest/info'`
* **Success Response:** ` 200 OK` 
  * **Content:** `List of menu objects. Example of object:`
```json
   {    "restaurant_id": "[Integer]",
        "restaurant_name":"[String]",
        "menuDate":"[LocalDate]",
        "voted":"[Integer]",
        "menu_id":"[Integer]",
        "menu":[
            {   "id":"[Integer]",
                "description":"[String]"
                ,"price":"[Integer]"    },
            {   "id":"[Integer]",
                "description":"[String]",
                "price":"[Integer]"    },
            {   "id":"[Integer]",
                "description":"[String]",
                "price":"[Integer]" }   ]}

```
  
 _________

### Show top10 restaurants menus from all history


 Returns a list of 10 tops menus  with max `voted` value.
* **URL** :  `/rest/info/top10`
* **Method:**  `GET`
* **Example Request:** `curl --location --request GET 'http://localhost:8080/voting/rest/info/top10'`
* **Success Response:** `200 OK` 
  * **Content:** `List of menu objects.`
  
 --------   
 
 ### Show top10  menus from one restaurant
 
 
 Returns a list of 10 tops menus restaurants with `restaurant_id`

 * **URL** :  `/rest/info/top10/{restaurant_id}`
 * **Method:**  `GET`
 *  **URL Params**: `restaurant_id`
 *  **Example Request:** `curl --location --request GET 'http://localhost:8080/voting/rest/info/top10/3' `
 *  **Success Response:** ` 200 OK` 
    * **Content:** `List of menu objects.`
  --------------------------
  
### Show restaurant menu


Returns a  menu   with  `menu_id` . 
        
 * **URL** :  `/rest/info/menu/{menu_id}`
 * **Method:**  `GET`
 *  **URL Params**: `menu_id`
 *  **Example Request:** `curl --location --request GET 'http://localhost:8080/voting/rest/info/menu/10' `
 *  **Success Response:** `200 OK` 
    * **Content:** `Menu object.`
 --------------------------------------

### Show voting history

  Returns a list of  data about all   menus restaurants,  where  `voted` value more than zero.
  Data is represented by list of objects. One object is including date and list of `voteResult` for each restaurant menu.  
  
 * **URL** :  `/rest/info/votehist`
 * **Method:**  `GET`
 * **Example Request:** `curl --location --request GET 'http://localhost:8080/voting/rest/info/votehist'`  
 * **Success Response:** `200 OK` <br />
    * **Content:** `List of vote history objects. Example of object:`
 ```json
    {
        "date": "[LocalDate]",
        "voteResult": [
            {
              "voted": "[Integer]",
              "restaurantName": "[String]",
              "restaurnt_id": "[Integer]",
              "menu_id": "[Integer]"
            },
            {
              "voted": "[Integer]",
              "restaurantName": "[String]",
              "restaurnt_id": "[Integer]",
              "menu_id": "[Integer]"
            }
         
        ]
    }
```
--------------

### Show restaurant voting history


Returns a list of  data about vote history for restaurant and it menu with  `restaurant_id` value.
 
 * **URL** :  `/rest/info/votehist/{restaurant_id}`
 * **Method:**  `GET`
 *  **URL Params**: `restaurant_id`
 *  **Example Request:** `curl --location --request GET 'http://localhost:8080/voting/rest/info/votehist/2'  `
 *  **Success Response:** `200 OK` <br />
    * **Content:** `List of vote history objects.`  
 
___
  
##### The section below is for register user and  all features that is has. To authorise request use basic authorization. It starts with $ROOT_PATH/rest/profile
___

### Register new User

Register user by sending form data. Login value is unique, other fields have length from 2 to 100. 

Example:

 * **URL** :  `/rest/profile/register`
 * **Method:**  `POST`
 * **Data Params** :
 ```json
  {   "login": "[String]",
      "pass":"[String]",
      "name": "[String]",
      "surName": "[String]"
  }
  ```
 * **Example Request:** 
 `curl -s -X POST -d '{"login": "newUser", "pass":"pass", "name":"name1","surName": "surname1"}'  -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/voting/rest/profile/register `    
 * **Success Response:** `201 CREATED` <br />
   * **Content:**  `User info object:`
     ```json
     {   "id": "[Integer]",
         "login": "[String]",
         "name": "[String]",
         "surName": "[String]",
         "enabled": "[boolean]",
         "registered": "[Date]",
         "roles": "[Role]"
     }
     ```
 * **Error Response:** `422 UNPROCESSABLE ENTITY`
   * **Content:** `Error object. Example:`
```json
    {
        "url": "[String]",
        "type": "[ErrorType]",
        "detail": "[String]"
    }
```
--------------
 ### Show user profile

 
 * **URL** :  `/rest/profile`
 * **Method:**  `GET`
 * **Example Request:** `curl -L -X GET 'http://localhost:8080/voting/rest/profile' -u user1:pass`
 * **Success Response:** ` 200 OK` <br />
   * **Content:**  `User info object.`
 * **Error Response:** ` 401 UNAUTHORIZED `
 ___   
 ### Update user 
   
  
  * **URL** :  `/rest/profile`
  * **Method:**  `PUT`
  * **Data Params** : `not all field are required`
       ```json
         {      
                "pass":"[String]",    
                "name": "[String]",   
                "surName": "[String]"
         }
       ```
  * **Example Request:** `curl -L -X PUT -d '{"name": "newname", "pass":"newpass"}'  -H 'Content-Type:application/json;charset=UTF-8' 'http://localhost:8080/voting/rest/profile' -u user1:pass`
  * **Success Response:** ` 200 OK` <br />
    * **Content:**  `User info object.`
  * **Error Response:**
      * **Code:**  401 `UNAUTHORIZED `
      * **Code:**  422 `UNPROCESSABLE ENTITY`
        * **Content:** `Error object.`
 ___   
 ### Vote for restaurant
    
  Making vote for restaurant with `restaurant_id` parameter. If time is acceptable for voting you get `You has voted  for restaurant with id = {restaurant_id}` or `Sorry. It's too late to vote`.   
   * **URL** :  `/rest/profile/vote?id={restaurant_id}`
   * **Method:**  `GET`
   * **GET Params**: `restaurant_id`
   * **Example Request:** `curl -L -X GET -H 'Content-Type:application/json;charset=UTF-8' 'http://localhost:8080/voting/rest/profile/vote?id=3' -u user1:pass`
   * **Success Response:**
       * **Code:** 202 `ACCEPTED` <br />
         * **Content:**  <br/>
            - `You  voted  for restaurant with id = 3` 
            - `Sorry. It's too late to vote`
       
   * **Error Response:**
        * **Code:**  401 `UNAUTHORIZED `
        * **Code:**  400 `Bad Request`
          * **Content:** `Error object.`

  ___   
  ### Get user voting history
  
  
   * **URL** :  `/rest/profile/history`
   * **Method:**  `GET`
   * **Example Request:** `curl -L -X GET  'http://localhost:8080/voting/rest/profile/history' -u user2:pass`
   * **Success Response:** `200 OK` <br />
   * **Content:**  
```json
    [  
        {
        "restaurant_id": "[Integer]",
        "date": "[LocalDate1]"
        },
        {
        "restaurant_id": "[Integer]",
        "date": "[LocalDate2]"
        }
        
    ]
```  
  * **Error Response:** ` 401 UNAUTHORIZED`  
_____
       
##### The section below is for admin and  all features that is has. To authorise request use basic authorization. It starts with $ROOT_PATH/rest/admin . Admin can update his profile  and manage  users profiles and resturants. If use  user credentials,  server will back 403  error. If credential is not exist server back 401 error
______

### Show users
   * **URL** :  `/rest/admin/users`
   * **Method:**  `GET`
   * **Example Request:** `curl -L -X GET  'http://localhost:8080/voting/rest/admin/users' -u admin:111`
   * **Success Response:** ` 200 OK` <br />
     * **Content:** `List of user objects.`
______________________
### Show one user profile
        
* **URL** :  `/rest/admin/users/{userID}`
* **Method:**  `GET`
* **URL Params**: `userID`
* **Example Request:** `curl -L -X GET  'http://localhost:8080/voting/rest/admin/users/1' -u admin:111`
* **Success Response:** `200 OK` <br />
  * **Content:** `User object.`
* **Error Response:** `400 Bad Request`
  * **Content:** `Error object.`
 __________
#### Create admin user
 
Register new  admin  profile. Login value is unique, other fields have length from 2 to 100.

 * **URL** :  `/rest/admin/users`
 * **Method:**  `POST`
 * **Data Params** :
 ```json
  {   "login": "[String]",
      "pass":"[String]",
      "name": "[String]",
      "surName": "[String]"
  }
  ```
 * **Example Request:** 
 `curl -s -X POST -d '{"login": "admin2", "pass":"111", "name":"adminName","surName": "adminSurname"}'  -H 'Content-Type:application/json;charset=UTF-8' 'http://localhost:8080/voting/rest/admin/users'  -u admin:111 `    
 * **Success Response:** `201 CREATED` <br />
   * **Content:**  `User info object:`
     ```json
     {   "id": "[Integer]",
         "login": "[String]",
         "name": "[String]",
         "surName": "[String]",
         "enabled": "[boolean]",
         "registered": "[Date]",
         "roles": "[Role]"
     }
     ```
 * **Error Response:** `422 UNPROCESSABLE ENTITY`
   * **Content:** `Error object.`
----
   
### Disable/enable user profile
   
   Change field `enabled` in user profile. Disabled user can't authorise.
   
   * **URL** :  `/rest/admin/users/{userID}?enabled={boolean}`
   * **Method:**  `PATCH`
   * **URL Params**: `userID`
   * **Get Params**: `enabled`
   * **Example Request:**  `curl -s -X PATCH  'http://localhost:8080/voting/rest/admin/users/3?enabled=false'  -u admin:111 `    
   * **Success Response:** `204 No Content` <br /> 
   * **Error Response:**  `400 Bad Request` 
     * **Content:** `Error object.`
   
  ------
 ### Update  user
  
    
   * **URL** :  `/rest/admin/user/{userID}`
   * **Method:**  `PUT`
   * **URL Params**: `userID`
   * **Data Params** : `not all field are required`
       ```json
           {      
                  "pass":"[String]",    
                  "name": "[String]",   
                  "surName": "[String]" 
           }
        ```
   * **Example Request:** `curl -L -X PUT -d '{"name": "newname", "pass":"newpass"}'  -H 'Content-Type:application/json;charset=UTF-8' 'http://localhost:8080/voting/rest/admin/users/3' -u admin:111`
  * **Success Response:** ` 200 OK` <br />
    * **Content:**  `User info object.`
  * **Error Response:**
      * **Code:**  400 `Bad Request`
        * **Content:** `Error object.`
     
  -----
 ### Delete user
  

  * **URL** :  `/rest/admin/user/{userID}`
  * **Method:**  `DELETE`
  * **URL Params**: `userID`
  * **Example Request:** `curl -L -X DELETE  'http://localhost:8080/voting/rest/admin/users/3' -u admin:111`
  * **Success Response:** `204 No Content` <br />
  * **Error Response:** `400 Bad Request`
     * **Content:** `Error object.`
        
   ---------------------
   ### Show restaurants
   
   
   Show list of restaurants with short information about them timezones and if restaurants have updated menu.
      Timezone is nessesary to determinate the time vote border. Timezone must be in GMT format ( for example GMT+10:00) This time can be change in `app.properties` file before compilation. The defaulf value is 11:00 according task conditions.
    <br/>
     

   * **URL** :  `/rest/admin/restaurants`
   * **Method:**  `GET`
   * **Example Request:** `curl -L -X GET  'http://localhost:8080/voting/rest/admin/restaurants' -u admin:111`
   * **Success Response:** ` 200 OK` 
     *  **Content:** `List of restaurant short info objects. Example of object:`
        ```json
          {
                  "id":"[Integer]" ,
                  "name": "[String]",
                  "timezone": "[GMT timezone]",
                  "isMenuUpdated": "[boolean]"
          }
        ```
 
   ------------------
   ### Show one restaurant
    

  * **URL** :  `/rest/admin/restaurants/{id}`
  * **Method:**  `GET`
  * **URL Params**: `id`
  * **Example Request:** `curl -L -X GET  'http://localhost:8080/voting/rest/admin/restaurants/3' -u admin:111`
  * **Success Response:** ` 200 OK` <br />
    * **Content:** `Restaurant short info object.`
  * **Error Response:** `400 Bad Request`
     * **Content:** `Error object.`
        
   
  _____________________
            
 #### Create restaurant
  
 * **URL** :  `/rest/admin/restaurants`
 * **Method:**  `POST`
 * **Data Params** :
 ```json
{
	"name":"[String]",
	"timezone":"[GMT timezone]"
}
```
   * **Example Request:** `curl -L -X POST -d '{"name": "newname", "timezone":"GMT+10:00"}'  -H 'Content-Type:application/json;charset=UTF-8' 'http://localhost:8080/voting/rest/admin/restaurants' -u admin:111`
   * **Success Response:** ` 201 CREATED`  <br />  
     * **Content:** `Restaurant short info object.`
   * **Error Response:** `422 UNPROCESSABLE ENTITY`
      * **Content:** `Error object.`
-----------------
 
 ### Update restaurant information
  
  * **URL** :  `/rest/admin/restaurants/{id}`
  * **Method:**  `PUT`
  * **URL Params**: `id`
  * **Data Params** :
 ```json
 {
 	"name":"[String]",
 	"timezone":"[GMT timezone]"
 }
 ```
   * **Example Request:** `curl -L -X PUT -d '{"name": "newname", "timezone":"GMT+10:00"}'  -H 'Content-Type:application/json;charset=UTF-8' 'http://localhost:8080/voting/rest/admin/restaurants/3' -u admin:111`
   * **Success Response:** ` 200 OK` <br />
     * **Content:** `Restaurant short info object.`
   * **Error Response:** `400 Bad Request`
     * **Content:** `Error object.`
___________________________________________
   
### Delete restaurant
   
   * **URL** :  `/rest/admin/restaurants/{id}`
   * **Method:**  `DELETE`
   *  **URL Params**: `id`
   * **Example Request:** `curl -L -X DELETE 'http://localhost:8080/voting/rest/admin/restaurants/3' -u admin:111`
   * **Success Response:** `204 No Content` <br /> 
   * **Error Response:** `400 Bad Request`
     * **Content:** `Error object.`
  
 -----------------------------------------
 
### Show  menu for single  restaurant
 
 
 Get detail info about menu and meals for one restaurant 
   * **URL** :  `/rest/admin/restaurants/{restaurant_id}/meals`
   * **Method:**  `GET`
   *  **URL Params**: `restaurant_id`
   * **Example Request:** `curl -L -X GET 'http://localhost:8080/voting/rest/admin/restaurants/2/meals' -u admin:111`
   * **Success Response:** ` 200 OK` <br /> 
     * **Content:** `Menu Object. Example:`
   ```json
      {    "restaurant_id": "[Integer]",
           "restaurant_name":"[String]",
           "menuDate":"[LocalDate]",
           "voted":"[Integer]",
           "menu_id":"[Integer]",
           "menu":[
               {   "id":"[Integer]",
                   "description":"[String]"
                   ,"price":"[Integer]"    },
               {   "id":"[Integer]",
                   "description":"[String]",
                   "price":"[Integer]"    }
               
       ]   }
   
   ```
   * **Error Response:** `400 Bad Request`
     * **Content:** `Error object.`
  ---------------------------
 ### Add meal to menu
 
  Create  meal item for restaurant. 
   * **URL** :  `/rest/admin/restaurants/{restaurant_id}/meals`
   * **Method:**  `POST`
   *  **URL Params**: `restaurant_id`
   * **Data Params** :
   ```json
    {
  	"description":"[String]",
    "price":"[Integer]"
    }
   ```
   * **Example Request:** `curl -L -X POST -d '{"description": "newmeal", "price":"100"}'  -H 'Content-Type:application/json;charset=UTF-8' 'http://localhost:8080/voting/rest/admin/restaurants/2/meals' -u admin:111`
   * **Success Response:** `Code: 201 CREATED` <br />
     *  **Content:** `Meal Object info. Reply example:`
 ```json
{
    "id": "[Integer]",
    "description": "[String]",
    "price": "[Integer]",
    "menu_id": "[Integer]",
    "restaurant_id": "[Integer]"
}
  ```
   * **Error Response:** `422 UNPROCESSABLE ENTITY`,
                         `400 Bad Request`
     * **Content:** `Error object.`
 ----------------------------------
 
 ### Update meal
 
 Update meal by its `id` by `restaurant_id`
  * **URL** :  `/rest/admin/restaurants/{restaurant_id}/meals/{id}`
  * **Method:**  `PUT`
  *  **URL Params**: `restaurant_id` , `id`
  * **Data Params** :
 ```json
      {
    	 "description":"[String]",
          "price":"[Integer]"
      }
  
  ```
   * **Example Request:** `curl -L -X PUT -d '{"description": "newmeal", "price":"100"}'  -H 'Content-Type:application/json;charset=UTF-8' 'http://localhost:8080/voting/rest/admin/restaurants/3/meals/43' -u admin:111`
   * **Success Response :** ` 20O OK` <br />
      *  **Content:** `Meal Object info`
   * **Error Response:** `422 UNPROCESSABLE ENTITY or 400 Bad Request`
        * **Content:** `Error object.`
   
  -------------------
  
  ### Delete meal
   
  Delete meal by its `id` and by `restaurant_id`
  
  * **URL** :  `/rest/admin/restaurants/{restaurant_id}/meals/{id}`
  * **Method:**  `DELETE`
  * **URL Params**: `restaurant_id` , `id`
  * **Example Request:** `curl -L -X DELETE 'http://localhost:8080/voting/rest/admin/restaurants/3' -u admin:111`
  * **Success Response:** ` 204 No Content ` <br />
  * **Error Response:** `400 Bad Request`
    * **Content:** `Error object.`
   --------------------------------
  ### Delete todays's menu for restaurant
   
   
   * **URL** :  `/rest/admin/restaurants/{restaurant_id}/meals`
   * **Method:**  `DELETE`
   *  **URL Params**: `restaurant_id`
   * **Example Request:** `curl -L -X DELETE 'http://localhost:8080/voting/rest/admin/restaurants/3/meals' -u admin:111`
   * **Success Response:** ` 204 No Content ` <br />
   * **Error Response:** `400 Bad Request`
     * **Content:** `Error object.`