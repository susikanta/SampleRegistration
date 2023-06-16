# Sample registration and image uploader REST API

To start this service, execute the below command:

    mvn spring-boot:run

## Register user providing basic information user name and password.
POST request 
	
	POST curl --location 'https://localhost:8443/registration/register' \
--header 'Content-Type: application/json' \
--data '{
    "username": "test1",
    "password":"test123"
}'


## Show All uploaded images
GET request

    GET curl --location 'https://localhost:8443/images' \
	--header 'userId: test1'

The base link to show all images attach to user Id.

Request body :: None

Response body ::
```json
{
    "status": {
        "code": 200,
        "message": "success"
    },
    "data": []
}
```

## New Upload Job - using multipart
POST request 

    POST curl --location 'https://localhost:8443/images/upload' \
--header 'userId: test1' \
--form 'image=@"/C:/Susikanta/Synchrony/dolphin.jpeg"'

Response body::


## Image upload using 
POST request
curl --location 'https://localhost:8443/images/upload' \
--header 'userId: test1' \
--header 'Content-Type: application/json' \
--data '{
    "urls": [
        "https://farm3.staticflickr.com/2879/11234651086_681b3c2c00_b_d.jpg"
    ]
}'

Response body

## Delete image
DELETE request 

	DELETE curl --location --request DELETE 'https://localhost:8443/images/delete/1cQObAbgwwZ9i2E' \
--header 'userId;'

response body
{
    "status": {
        "code": "200",
        "message": "success"
    },
    "data": null
}


