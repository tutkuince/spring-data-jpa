SELECT
    department_id,
    COUNT(employee_id) as count_employee
FROM employees
GROUP BY department_id
HAVING COUNT(employee_id) >= 5;

-- Find employee count that employee's salary is greater than 5000 by department
SELECT
    department_id,
    COUNT(employee_id) as count_employee
FROM employees
WHERE salary >= 5000
GROUP BY department_id;


SELECT department_id,
       COUNT(employee_id) as count_employee
FROM employees
WHERE salary >= 5000
GROUP BY department_id
HAVING COUNT(employee_id) >= 5;
