# Allowing File Transfer
SET GLOBAL local_infile=1;

# Displaying Film Table
SELECT * FROM film;

# SQL Query to Check out Number of Columns of a Table
SELECT COUNT(*) AS Number_Of_Columns FROM INFORMATION_SCHEMA.COLUMNS
WHERE table_schema = 'sakila' AND TABLE_NAME = 'film';

# Selecting out Unique Languages
SELECT DISTINCT(language_id) FROM film;

# Using OFFSET and LIMIT
SELECT * FROM film LIMIT 99, 2;
