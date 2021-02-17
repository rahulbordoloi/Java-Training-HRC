# Allowing File Transfer
SET GLOBAL local_infile=1;

# Displaying Film Table
SELECT * FROM film;

# SQL Query to Check out Number of Columns of a Table
SELECT COUNT(*) AS Number_Of_Columns FROM INFORMATION_SCHEMA.COLUMNS
WHERE table_schema = 'sakila' AND TABLE_NAME = 'film';

# Checking out Number of Rows
SELECT COUNT(*) AS Number_Of_Rows FROM film;

# Selecting out Unique Languages
SELECT DISTINCT(language_id) FROM film;

# Using OFFSET and LIMIT
SELECT * FROM film LIMIT 99, 2;

# Displaying Film Table Joined with Language
SELECT 
film_data.film_id,
film_data.title,
film_data.description,
film_data.release_year,
lang.name AS `language`,
film_data.original_language_id,
film_data.rental_duration,
film_data.rental_rate,
film_data.length,
film_data.replacement_cost,
film_data.rating,
film_data.special_features,
film_data.last_update,
film_data.director
FROM film AS film_data
LEFT JOIN `language` AS lang ON film_data.language_id = lang.language_id;

# Taking out Unique Special Features from Film Table
SELECT DISTINCT(special_features) FROM film;
