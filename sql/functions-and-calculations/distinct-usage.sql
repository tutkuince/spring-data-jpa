-- DISTINCT
-- The SELECT DISTINCT statement is used to return only distinct (different) values.


SELECT DISTINCT (manager_id)
FROM employees;

SELECT
    manager_id,
    COUNT(employee_id)
FROM employees
GROUP BY manager_id;

SELECT COUNT(DISTINCT manager_id)
FROM employees;

