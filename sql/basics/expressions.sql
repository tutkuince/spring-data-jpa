-- CASE WHEN Expressions

-- group employees by salaries
-- salary < 8000, salary >= 8000 & salary < 10000, salary >= 10000

SELECT first_name,
       last_name,
       salary,
       CASE
           WHEN salary < 8000 THEN 'Low Salary'
           WHEN salary >= 8000 AND salary < 10000 THEN 'High Salary'
--            WHEN salary >= 10000 then 'Manager'
           ELSE 'Manager'
       END as salary_segment
FROM employees;
