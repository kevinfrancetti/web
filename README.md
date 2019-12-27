# WEB-APP
***

Lista endpoints:

* Lista posts:
    
    *  `GET` http://isin03.dti.supsi.ch:81/francetti/item
    
* Inserimento nuovo post:
    * `POST` http://isin03.dti.supsi.ch:81/francetti/item
     
        header:
                
        ```
        Content-Type: application/json
        ```
        
        body:
        
        ```json
        {
            "title": "Vendo cani",
            "description": "Cucciolata di cani malati",
            "category": {
                "name": "C1"
            },
            "author": {
            "name": "Jupiter",
            }
        }
        ```
* Modifica post:
    * `PUT` http://isin03.dti.supsi.ch:81/francetti/item/{id}
