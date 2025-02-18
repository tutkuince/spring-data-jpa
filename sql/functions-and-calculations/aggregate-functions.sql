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

-- Find all employee count, earning more than $9000 and ratio
SELECT
    COUNT(*) as total_employee,
    COUNT(case when salary >= 9000 then 1 else null end) as high_earner,
    COUNT(case when salary >= 9000 then 1 else null end) * 1.0 / count(*) as ratio
FROM employees;

-- Find employee count by department
SELECT department_id,
       COUNT(employee_id)
FROM employees
GROUP BY department_id
ORDER BY department_id; -- we can give index number as 1 for "group by" and "order by"

-- department_id, manager_id count of employees
SELECT department_id,
       manager_id,
       COUNT(employee_id) as count_employee
FROM employees
GROUP BY 1, 2; -- GROUP BY department_id, manager_id;
