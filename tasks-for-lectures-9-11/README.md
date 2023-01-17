
# Films System (API)
(one director has many films)
### How does it work?

CRUD operations:
1) ***POST***
   - **/api/films** - create new film
   ```
   {
        "title": "your title",
        "rating": "0.9",
        "releaseDate": "2023-01-01",
        "directorId": 1
   }
   ```
   
2) ***GET***
   - **/api/films/{id}** - get film by id
   - **/api/films** - get all films
   - **/api/directors** - get all directors 

3) ***PUT***
   - **/api/films/{id}** - update film by id
    ```
   {
        "title": "new title",
        "rating": "new rating",
        "releaseDate": "new releaseDate",
        "directorId": "new directorId"
   }
   ```

4) ***DELETE***
   - **/api/books/{id}** - delete film by id