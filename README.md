**Restaurant Vote system**
----
This is education example of my REST API from the task below
https://github.com/JavaWebinar/topjava/blob/doc/doc/graduation.md
___

###Show todays restaurants menus
----
  Returns  list of json data about all updated  restaurants menus.

* **URL** :  /info
* **Method:**  `GET`
  
*  **URL Params**: none

* **Data Params** :none

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** [{"restaurant_id":3,"restaurant_name":"Ravshan","menuDate":"2020-01-14","voted":2,"menu_id":15,"menu":[{"id":43,"description":"Лаваш","price":502},{"id":44,"description":"Брутуч","price":1002},{"id":45,"description":"Пенце","price":702}]},{"restaurant_id":1,"restaurant_name":"Ashan","menuDate":"2020-01-14","voted":1,"menu_id":13,"menu":[{"id":37,"description":"Биг-Мак","price":500},{"id":38,"description":"Кола","price":1000},{"id":39,"description":"Фри","price":700}]},{"restaurant_id":2,"restaurant_name":"Mishan","menuDate":"2020-01-14","voted":0,"menu_id":14,"menu":[{"id":40,"description":"Суп","price":502},{"id":41,"description":"Креветка","price":1002},{"id":42,"description":"Пиво","price":702}]}]