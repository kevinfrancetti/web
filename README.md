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
            "title": "Un titolo",
            "description": "Una descrizione",
            "category": {
                "name": "Nome della categoria"
            },
            "author": {
            "name": "Nome del autore",
            }
        }
        ```
* Modifica post:
    * `PUT` http://isin03.dti.supsi.ch:81/francetti/item/{id}
