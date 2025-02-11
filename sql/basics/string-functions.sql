-- String Functions

-- CONCAT
SELECT
--     concat(first_name, ' ',last_name) full_name, -> as is not mandatory
    first_name || ' ' || last_name as full_name,
    phone_number
FROM
employees
WHERE phone_number IS NULL;

-- LEFT
-- Find first 3 digit of phone_number
SELECT left(phone_number, 3)
FROM employees
WHERE phone_number IS NOT NULL;


-- RIGHT
-- Find last 16 char of email
SELECT email,
       right(email, 16)
FROM employees;



-- LENGTH
-- Find length of first_name
SELECT first_name,
       length(first_name)
FROM employees;


-- LOWER, UPPER
SELECT lower(first_name),
       upper(last_name)
FROM employees;


-- REPLACE
-- change mail provider to @mail
SELECT replace(email, '@gmail', '@mail')
FROM employees;

-- SPLIT PART
-- get mail provider
SELECT
    email,
    split_part(email, '@', 2)
FROM employees

