# customer-service-token-management
Problem: A bank, ABC Bank, has many branches and customers. Each branch has a limited number of
teller counters. ABC Bank provides a better customer experience to their premium customers by providing priority over non premium customers.In order to achieve this, ABC Bank has decided to introduce new machines to manage queues at each of their branches

# Tech Stack
1.  Java 8</br>
2.  Spring Boot</br>
3.  Postgres 9.5</br>
4.  RabbitMQ 3.6.15</br>
5.  Maven </br>
6.  Tomcat 9 </br>

# Assumptions and Implementation Details

1.There are two different type of counters premium and regular.Premium counters are reserved for premium customers and premium customers will be served only at premium counters.

2.For each operation a different queue is created in RabbitMQ .Counters are treated as consumers which bind to these queues.A counter can perform any number of operations so each counter can be binded to multiple queues.

3.Once token is created it is put into its specific operation queue,if a counter which can perform the type of operation required by the token is free it would consume the token from the queue and perform the specific operation on it.Counter is not preassigned to a token as we are not aware how much time it would take to a process a individual token so as when counters become free they would consume and process the tokens present in the queues.

4.A customer can opt for multiple services while token is issued,based on the order of the services selected by customer the token would be intially queued .Once the first operation or the service required by the customer is provided ,the token would be requed for second operation or service requested by the customer.


# End Points

```
1. Creating a bank :

POST: http://localhost:8080/api/bank 
sample request and response 

{
			
        "bankName": "LenaDenaBank",
        "location": "Hyderabad"
       
 }  

{
    "bankId": 1,
    "bankName": "lenaDenaBank",
    "location": "Hyderabad"
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





  
