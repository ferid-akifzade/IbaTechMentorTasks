##Notes spring application

- `localhost:9005/api/login`
    - GET - get Ok -> Params = NONE
    - POST - Login -> Params = (email, password)
- `localhost:9005/api/register`
    - GET - OK -> Params = NONE
    - POST - Register -> Params = email, password
- `localhost:9005/api/notes`
    - GET - get all notes -> Params = NONE
    - POST - Add note -> Params =  note
    - PUT - Update note -> Params = noteId , newNote
    - DELETE - Delete note -> Params = noteId
    
- `Authorization` = `Token Bearer`
    - Authorization token must be added to Postman
    - Authorization token is receivable at login or register POST body