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
            "name": "Nome del autore"
            }
        }
        ```
      
* Inserimento nuovo post secondo metodo:
    * `POST` http://isin03.dti.supsi.ch:81/francetti/item
    
        header:
                    
        ```
        Content-Type: application/x-www-form-urlencoded
        ```
      
        URL params:
        ```
        title=UnTitolo&author=UnAutore&description=UnaDescrizione
        ```

* Modifica post:
    * `PUT` http://isin03.dti.supsi.ch:81/francetti/item/{id}
    
        ```json
        {
            "title": "Un titolo",
            "description": "Una descrizione",
            "category": {
                "name": "Nome della categoria"
            },
            "author": {
            "name": "Nome del autore"
            }
        }
        ```    

    
* Eliminazione post:
    * `DELETE` http://isin03.dti.supsi.ch:81/francetti/item/{id}
