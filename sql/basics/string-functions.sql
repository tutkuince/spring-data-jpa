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
FROM employees;

-- Your company has been purchased by UDEMY and all e-mail addresses need to be converted into a new format.
-- The new format will be in the form of "surname.ad@udemy.com" (eg smith.john@udemy.com).
-- The IT department asked you to prepare a list of available and new e-mail addresses.
-- Gaps will be removed for employees with double names or surnames (eg dehaan.lex@udemy.com for 'Lex de Haan').
SELECT CONCAT(first_name, ' ', last_name)                                                                 as full_name,
       email                                                                                              as old_email,
       CONCAT(LOWER(REPLACE(last_name, ' ', '')), '.', LOWER(REPLACE(first_name, ' ', '')), '@udemy.com') as new_email
FROM employees;
