DROP TABLE IF EXISTS customer CASCADE;
CREATE TABLE customer
(
  customerid serial primary key,
  name VARCHAR(50),
  phonenumber VARCHAR(10),
  address VARCHAR(100),
  customertype VARCHAR(10)
);

DROP TABLE IF EXISTS token CASCADE;
CREATE TABLE  token (
    tokenid serial primary key,
    tokentype varchar(128) ,
    customerid int NOT NULL,
    servicetype VARCHAR(100),
    comments varchar(512) DEFAULT '',
    status varchar(128),
    FOREIGN KEY (customerid) REFERENCES customer(customerid)
);

DROP TABLE IF EXISTS counter CASCADE;
CREATE TABLE counter (
	counterid serial primary key,
	servicetype VARCHAR(100),
	countertype varchar(128) not null default 'REGULAR'
	);