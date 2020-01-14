**Restaurant Vote system**
----
This is education example of my REST API from the task below
https://github.com/JavaWebinar/topjava/blob/doc/doc/graduation.md
___

##Show todays restaurants menus
----
  Returns a list of json data about all updated  restaurants menus.

* **URL** :  /rest/info
* **Method:**  `GET`
  
*  **URL Params**: none

* **Data Params** :none

* **Success Response:**

  * **Code:** 200 <br />
  * **Example Request:** curl --location --request GET 'http://localhost:8080/voting/rest/info'

 ##Show top10 restaurants menus from all history
----
* **URL** :  /rest/info/top10
* **Method:**  `GET`
  
*  **URL Params**: none

* **Data Params** :none

* **Success Response:**

  * **Code:** 200 <br />
    **Example Request:** curl --location --request GET 'http://localhost:8080/voting/rest/info/top10'
    
  ##Show top10  menus from one restaurant
  Get 10's most popular menus from one restaurant 
 ----
 * **URL** :  /rest/info/top10/{id}
 * **Method:**  `GET`
   
 *  **URL Params**: id
 
 * **Data Params** :none
 
 * **Success Response:**
 
   * **Code:** 200 <br />
     **Example Request:** curl --location --request GET 'http://localhost:8080/voting/rest/info/top10/3'   
        
    