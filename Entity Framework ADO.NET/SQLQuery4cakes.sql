CREATE DATABASE CAKES4
USE CAKES4
CREATE TABLE Chef(CID SMALLINT PRIMARY KEY IDENTITY(1,1),CName Varchar(30), Cgender Varchar(2),Cbirth Date)
CREATE TABLE Typee(TID SMALLINT PRIMARY KEY IDENTITY(1,1),TName Varchar(30), TDescription Varchar(30))
CREATE TABLE Cake(CakeID SMALLINT PRIMARY KEY IDENTITY(1,1),CShape Varchar(30),Weightt INT, Price INT,TID SMALLINT REFERENCES Typee(TID))
CREATE TABLE Orderr(OID SMALLINT PRIMARY KEY IDENTITY(1,1),Odate DATE)
CREATE TABLE OrderCakes(OID SMALLINT REFERENCES Orderr(OID), CakeID SMALLINT REFERENCES Cake(CakeID), OrderNumber INT,PRIMARY KEY(OID,CakeID))
CREATE TABLE ChefCake(CID SMALLINT REFERENCES Chef(CID),CakeID SMALLINT REFERENCES Cake(CakeID),PRIMARY KEY(CID,CakeID))

INSERT Chef Values('a','m','10-02-1999'),('b','m','10-02-1999'),('c','m','10-02-1999')
INSERT Typee Values('t1','assad')
INSERT Cake Values('c1',100,10,1),('c2',100,10,1),('c3',100,10,1)
INSERT ChefCake Values (1,1),(1,2),(2,1), (1,3)
INSERT Orderr Values('12-10-2012'),('12-10-2012'),('12-10-2012')
INSERT OrderCakes Values(1,1,9), (2,1,10)

CREATE OR ALTER PROC uspAddCake @oid INT,@cakeid INT,@p INT
AS
	IF EXISTS (SELECT * FROM OrderCakes WHERE OrderCakes.OID=@oid AND OrderCakes.CakeID=@cakeid)
		BEGIN
			update OrderCakes
			set OrderNumber =@p
			WHERE OrderCakes.CakeID=@cakeid AND OrderCakes.OID=@oid
		END
	ELSE
		BEGIN
			INSERT OrderCakes(OID,CakeID,OrderNumber) VALUES(@oid,@cakeid,@p)
		END
SELECT * FROM OrderCakes
EXEC uspAddCake '1','3','11'


SELECT * FROM ChefCake
SELECT * FROM Chef
SELECT * FROM Cake
CREATE OR ALTER FUNCTION ufShowChefs()
RETURNS TABLE
RETURN
	SELECT C.CName
	FROM Chef C
	WHERE NOT EXISTS
	(SELECT Cak.CakeID
	FROM Cake Cak
	EXCEPT
	SELECT CC.CakeID
	FROM ChefCake CC
	WHERE C.CID=CC.CID)

SELECT * FROM ufShowChefs()