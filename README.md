# customer-service-token-management
<h2>Technologies and tools used</h2>
1.  Java 8</br>
2.  Spring Boot</br>
3.  Postgres 9.5</br>
4.  RabbitMQ 3.6.15</br>
5.  Maven </br>
6.  Tomcat 9 </br>
<h2> End Points </h2>
1. Creating a bank :-Below mentioned API end point creates a bank </br>
POST: http://localhost:8080/api/bank </br>
sample request body </br>
```json
{
			
        "bankName": "LenaDenaBank",
        "location": "Bombay"
       
 } 
``` 
sample request response </br>
```json
{
    "bankId": 1,
    "bankName": "lenaDenaBank",
    "location": "Bombay"
}
```



  
