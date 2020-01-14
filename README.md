**Restaurant Vote system**
----
This is education example of my REST API from the task below </br>
https://github.com/JavaWebinar/topjava/blob/doc/doc/graduation.md
___

Show todays restaurants menus
----
  </t>Returns a list of menu data about all updated  restaurants menus.
  Menu data is represented by List of __MenuTO__ objects, which including short restaurant info and meals items. </br>
  Example __MealTO__ Object:
```json
   {    "restaurant_id":2,
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
                "price":702 }   ]}

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
  </t>Returns a list of 10 tops __MenuTO__ objects with max __voted__ value.
* **URL** :  /rest/info/top10
* **Method:**  `GET`
  
*  **URL Params**: none

* **Data Params** :none

* **Success Response:**

  * **Code:** 200 <br />
  
* **Example Request:** curl --location --request GET 'http://localhost:8080/voting/rest/info/top10'
    
 Show top10  menus from one restaurant
 ----
 </t>Returns a list of 10 tops __MenuTO__ objects restaurant with __restaurant_id__ .

 * **URL** :  /rest/info/top10/{restaurant_id}
 * **Method:**  `GET`
   
 *  **URL Params**: restaurant_id
 
 * **Data Params** :none
 
 * **Success Response:**
 
   * **Code:** 200 <br />
   
*  **Example Request:** curl --location --request GET 'http://localhost:8080/voting/rest/info/top10/3'  

Show restaurant menu 
-----
</t>Returns a  _MenuTO_ objects  with __menu_id__ . 
        
 * **URL** :  /rest/info/menu/{menu_id}
 * **Method:**  `GET`
   
 *  **URL Params**: menu_id
 
 * **Data Params** :none
 
 * **Success Response:**
 
   * **Code:** 200 <br /> 
   
 *  **Example Request:** curl --location --request GET 'http://localhost:8080/voting/rest/info/menu/10'  

Show voting history
---
</t>Returns a list of  data about all voted  restaurants  menu with  __voted__ value more than 0.
  Data is represented by  __RestrntVoteHistory__ objects, which including date and list of __RestrntVoteInfo__ object. </br>
  Example __RestrntVoteInfo__  Object:
  ```json
     { 
        "voted": 1,
        "restaurantName": "Mishan",
         "restaurnt_id": 2,
         "menu_id": 10
     }
```


 * **URL** :  /rest/info/votehist
 * **Method:**  `GET`
   
 *  **URL Params**: none
 
 * **Data Params** :none
 
 * **Success Response:**
 
   * **Code:** 200 <br />
   
*  **Example Request:** curl --location --request GET 'http://localhost:8080/voting/rest/info/votehist'  


Show restaurant voting history
------
</t>Returns a list of  data about vote history for restaurant and it menu with  __restaurant_id__ value.
 
 * **URL** :  /rest/info/votehist/{restaurant_id}
 * **Method:**  `GET`
   
 *  **URL Params**: restaurant_id
 
 * **Data Params** :none
 
 * **Success Response:**
 
   * **Code:** 200 <br />
   
*  **Example Request:** curl --location --request GET 'http://localhost:8080/voting/rest/info/votehist/2'  
   