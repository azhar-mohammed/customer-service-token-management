# customer-service-token-management
<h2>Technologies and tools used</h2>
1.  Java 8</br>
2.  Spring Boot</br>
3.  Postgres 9.5</br>
4.  RabbitMQ 3.6.15</br>
5.  Maven </br>
6.  Tomcat 9 </br>
<h2> End Points </h2>

```
1. Creating a bank :-Below mentioned API end point creates a bank </br>
POST: http://localhost:8080/api/bank </br>
sample request and response </br>

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

POST:http://localhost:8080/api/branch </br>
sample request and response </br>


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
POST:http://localhost:8080/api/counter </br>
sample request and response </br>

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
POST: http://localhost:8080/api/token </br>
sample request </br>

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


    





  
