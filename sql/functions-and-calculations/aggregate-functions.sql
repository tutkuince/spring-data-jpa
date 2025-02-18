-- AGGREGATE FUNCTIONS
-- COUNT, SUM, MIN, MAX, AVG

-- COUNT() - returns the number of rows in a set
SELECT COUNT(employee_id) as employee_count
FROM employees;
-- 40

SELECT
    COUNT(phone_number) as employee_count
FROM employees;
-- 34

SELECT
    COUNT(*) as has_null_numbers
FROM employees
WHERE phone_number IS NULL;
-- 6

