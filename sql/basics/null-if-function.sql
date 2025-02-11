-- NULLIF
-- The NULLIF() function returns NULL if two expressions are equal, otherwise it returns the first expression.

SELECT first_name || ' ' || last_name              as full_name,
       salary,
       salary_new,
       ((salary_new - salary) / NULLIF(salary, 0)) as raise
FROM employees;
