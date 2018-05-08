# customer-service-token-management
# Technologies and tools used
1.  Java 8</br>
2.  Spring Boot</br>
3.  Postgres 9.5</br>
4.  RabbitMQ 3.6.15</br>
5.  Maven </br>
6.  Tomcat 9 </br>
# End Points

```
1. Creating a bank :

POST: http://localhost:8080/api/bank 
sample request and response 

{
			
        "bankName": "LenaDenaBank",
        "location": "Bombay"
       
 }  

{
    "bankId": 1,
    "bankName": "lenaDenaBank",
    "location": "Bombay"
}

2.Creating a branch: 

POST:http://localhost:8080/api/branch 
sample request and response 


{
	"branchName":"lenadenaGachibowliBranch",
	"location":"Gachibowli",
	"bankId":1
}

{ 
   "branchId":2,
   "branchName":"lenadenaGachibowliBranch",
   "location":"Gachibowli",
   "bankId":1
}


3. Creating a counter
POST:http://localhost:8080/api/counter 
sample request and response 

 {
			
        "counterName": "PREMIUM-deposit-counter",
        "counterServices": ["DEPOSIT"],
        "counterType": "PREMIUM",
         "branchId": 2

    }
 
 
 {
    "counterName": "PREMIUM-deposit-counter",
    "counterServices": [
        "DEPOSIT"
    ],
    "counterType": "PREMIUM",
    "branchId": 2
}   

4.Creating a Token
POST: http://localhost:8080/api/token 
sample request 

  {  
   "tokenType":"PREMIUM",
   "customer":{  
      "branchId":"2",
      "customerName":"john",
      "phoneNumber":"9494940808",
      "address":"sanatnagar",
      "customerType":"PREMIUM",

   },
   "requiredServices":[  
      "DEPOSIT",
      "WITHDRAW"
   ]
}

```

# Class Diagram 
![Class Diagram](https://github.com/azhar-mohammed/customer-service-token-management/blob/master/model.jpg)

# DB Schema 
![Schema Design](https://github.com/azhar-mohammed/customer-service-token-management/blob/master/db-design.png)





  
