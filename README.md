**Restaurant Vote system**
----
This is education example of my REST API from the task below </br>
https://github.com/JavaWebinar/topjava/blob/doc/doc/graduation.md
___

Show todays restaurants menus
----
  Returns a list of menu data about all updated  restaurants menus.
  Menu data is represented by List of MenuTO objects, which including short restaurant info and meals items. </br>
  Example MealTO Object:
```json
   {
        "restaurant_id":2,
        "restaurant_name":"Mishan",
        "menuDate":"2018-08-29",
        "voted":1,
        "menu_id":10,
        "menu":[
            {   "id":28,
                "description":"Тунец"
                ,"price":502    },
            {   "id":29,
                "description":"Мармелад",
                "price":1002    },
            {   "id":30,
                "description":"Индейка",
                "price":702 }                ]}

```
  

* **URL** :  /rest/info
* **Method:**  `GET`
  
*  **URL Params**: none

* **Data Params** :none

* **Success Response:**

  * **Code:** 200 <br />
  
* **Example Request:** curl --location --request GET 'http://localhost:8080/voting/rest/info'
_________

 Show top10 restaurants menus from all history
----
* **URL** :  /rest/info/top10
* **Method:**  `GET`
  
*  **URL Params**: none

* **Data Params** :none

* **Success Response:**

  * **Code:** 200 <br />
  
* **Example Request:** curl --location --request GET 'http://localhost:8080/voting/rest/info/top10'
    
 Show top10  menus from one restaurant
 ----
  Get 10's most popular menus from one restaurant 

 * **URL** :  /rest/info/top10/{restaurantID}
 * **Method:**  `GET`
   
 *  **URL Params**: restaurantID
 
 * **Data Params** :none
 
 * **Success Response:**
 
   * **Code:** 200 <br />
   
*  **Example Request:** curl --location --request GET 'http://localhost:8080/voting/rest/info/top10/3'  

Show restaurant menu information
-----
Get detail Information about restaurant menu with {menuID}  
        
 * **URL** :  /rest/info/menu/{menuID}
 * **Method:**  `GET`
   
 *  **URL Params**: menuID
 
 * **Data Params** :none
 
 * **Success Response:**
 
   * **Code:** 200 <br /> 
   * **Content:** '{"restaurant_id":2,"restaurant_name":"Mishan","menuDate":"2018-08-29","voted":1,"menu_id":10,"menu":[{"id":28,"description":"Тунец","price":502},{"id":29,"description":"Мармелад","price":1002},{"id":30,"description":"Индейка","price":702}]}'

*  **Example Request:** curl --location --request GET 'http://localhost:8080/voting/rest/info/menu/10'  

Show voting history
---
Get all history information   

 * **URL** :  /rest/info/votehist
 * **Method:**  `GET`
   
 *  **URL Params**: restaurantID
 
 * **Data Params** :none
 
 * **Success Response:**
 
   * **Code:** 200 <br />
   
*  **Example Request:** curl --location --request GET 'http://localhost:8080/voting/rest/info/votehist'  


Show restaurant voting history
------
Get all history information for restaurant with {restaurantID} 

 * **URL** :  /rest/info/votehist/{restaurantID}
 * **Method:**  `GET`
   
 *  **URL Params**: restaurantID
 
 * **Data Params** :none
 
 * **Success Response:**
 
   * **Code:** 200 <br />
   
*  **Example Request:** curl --location --request GET 'http://localhost:8080/voting/rest/info/votehist/2'  
   