ALTER TABLE Coches
ADD id INT IDENTITY(0, 1) PRIMARY KEY
GO

CREATE TABLE cochesMatriculados (
    id INT PRIMARY KEY IDENTITY(1, 1),
    idCar INT REFERENCES Coches(id),
    matricula VARCHAR(7)
)
GO

CREATE OR ALTER VIEW randomv
AS SELECT RAND(CONVERT(varbinary, NEWID())) s
GO

CREATE OR ALTER FUNCTION random (@min INT, @max INT)
RETURNS INT
AS BEGIN
    RETURN ( (@max - @min + 1) * (select s from randomv) + @min )
END
GO

CREATE OR ALTER FUNCTION dbo.generarMatricula()
RETURNS VARCHAR(7)
AS
BEGIN
    DECLARE @dict VARCHAR(21) = 'BCDFGHJKLMNPQRSTVWXYZ#'

    DECLARE @numeros VARCHAR(4) = RIGHT('0000' + CAST(dbo.random(1, 9999) AS VARCHAR(5)), 4)
    DECLARE @letra1 CHAR(1) = SUBSTRING(@dict, dbo.random(1, 20), 1)
    DECLARE @letra2 CHAR(1) = SUBSTRING(@dict, dbo.random(1, 20), 1)
    DECLARE @letra3 CHAR(1) = SUBSTRING(@dict, dbo.random(1, 20), 1)

    RETURN @numeros + @letra1 + @letra2 + @letra3
END
GO

SELECT dbo.generarMatricula() AS Matricula;
GO

TRUNCATE TABLE cochesMatriculados
GO

DECLARE @i INT = 0

WHILE @i < 50000
BEGIN
    INSERT INTO cochesMatriculados (idCar, matricula)
    VALUES (dbo.random(0, 2302), dbo.generarMatricula())

    SET @i = @i + 1;
END;
GO

CREATE OR ALTER VIEW cochesGasolina
AS SELECT CM.matricula, C.Modelo FROM cochesMatriculados CM
INNER JOIN Coches C
    ON C.id = CM.idCar
WHERE C.Tipo = 'Gasolina'
GO

SELECT * FROM cochesGasolina
GO

CREATE OR ALTER VIEW cochesDiesel
AS SELECT CM.matricula, C.Modelo FROM cochesMatriculados CM
INNER JOIN Coches C
    ON C.id = CM.idCar
WHERE C.Tipo = 'Diesel'
GO

CREATE OR ALTER VIEW cochesElectricos
AS SELECT CM.matricula, C.Modelo FROM cochesMatriculados CM
INNER JOIN Coches C
    ON C.id = CM.idCar
WHERE C.Tipo = 'Electrico'
GO

SELECT C.Marca, COUNT(C.Tipo) FROM Coches C
INNER JOIN cochesMatriculados CM
    ON C.id = CM.idCar
GROUP BY C.Tipo, C.Marca
