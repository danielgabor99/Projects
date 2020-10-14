CREATE DATABASE GYMNASTICS
USE GYMNASTICS

CREATE TABLE Category(CatID SMALLINT PRIMARY KEY IDENTITY(1,1),CatName Varchar(30))
CREATE TABLE Championship(ChID SMALLINT PRIMARY KEY IDENTITY(1,1),ChName Varchar(30),CatID SMALLINT REFERENCES Category(CatID))
CREATE TABLE Affiliation(AID SMALLINT PRIMARY KEY IDENTITY(1,1),AName Varchar(30),Adesc Varchar(30))
CREATE TABLE Gymnast(GID SMALLINT PRIMARY KEY IDENTITY(1,1),GFname Varchar(30),Glname Varchar(30), Gbirth Date, AID SMALLINT REFERENCES Affiliation(AID))
CREATE TABLE MedalsWonInCh(GID SMALLINT REFERENCES Gymnast(GID),ChID SMALLINT REFERENCES Championship(ChID),Medals INT,PRIMARY KEY(GID,ChID))

INSERT Category VALUES('Cat1'),('Cat2'),('Cat3')
INSERT Championship VALUES ('Ch1',1),('Ch2',1)
INSERT Affiliation VALUES ('A1','AAA1'),('A2','AAA2')
INSERT Gymnast VALUES ('Andrei','Cojoc','10-01-1999',1),('Bianca','Lavric','10-01-1999',1),('Simona','Halep','10-01-1999',2)
INSERT MedalsWonInCh VALUES(1,1,3)

SELECT* FROM MedalsWonInCh
EXEC uspAdd 'Bianca','Ch2','10'


CREATE OR ALTER PROC uspAdd @g Varchar(30), @ch Varchar(30), @n INT
AS
	DECLARE @gid SMALLINT=(SELECT Gymnast.GID FROM Gymnast WHERE Gymnast.GFname=@g)
	DECLARE @chid SMALLINT=(SELECT Championship.ChID FROM Championship WHERE Championship.ChName=@ch)
	IF @chid IS NULL OR @gid IS NULL
	BEGIN
		RAISERROR('no championship / gymnast', 16,1)
		RETURN -1
	END

	IF EXISTS(SELECT * FROM MedalsWonInCh WHERE MedalsWonInCh.ChID=@chid AND MedalsWonInCh.GID=@gid)
		BEGIN
			UPDATE MedalsWonInCh
			SET Medals=@n
			WHERE MedalsWonInCh.ChID=@chid AND MedalsWonInCh.GID=@gid
		END
	ELSE
		BEGIN
			INSERT MedalsWonInCh(GID,ChID,Medals) VALUES (@gid,@ch,@n)
		END

CREATE OR ALTER VIEW vShowAll
AS
	SELECT G.GFname, G.Glname
	FROM Gymnast G
	WHERE NOT EXISTS
	(SELECT Ch.ChID
	FROM Championship Ch
	EXCEPT
	SELECT MWC.ChID
	FROM MedalsWonInCh MWC
	WHERE MWC.GID=G.GID)

SELECT * FROM vShowAll

CREATE OR ALTER FUNCTION ufShow(@M INT, @C INT)
RETURNS TABLE
RETURN
	SELECT G.GFname,G.Glname
	FROM Gymnast G
	WHERE EXISTS
	(SELECT MWC.GID
	FROM MedalsWonInCh MWC
	WHERE MWC.GID=G.GID
	GROUP BY MWC.GID
	HAVING COUNT(*)>@C AND SUM(MWC.Medals)>@M)
	
SELECT * FROM ufShow(10,1)