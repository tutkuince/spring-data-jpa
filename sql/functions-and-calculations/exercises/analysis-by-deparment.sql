-- Department Based Analysis SQL Practice
--
-- You work as the company's financial analyst. The management wants to examine the salary distribution in each department and to identify high -budget departments.
-- Only departments with more than 3 employees should be included in the report.

-- Notes:
-- 1. Results should be listed from large to small according to the total salary budget.
-- 2. Only departments with more than 3 employees should be shown.
-- 3. Null or 0 salaries should not be included in the calculations
-- 4. Column names should be exactly as mentioned above
-- 5. The resentful values of the salaries should not be on out

SELECT department_id,
       COUNT(employee_id)    as employee_count,
       SUM(salary)           as total_salary,
       MIN(salary)           as min_salary,
       MAX(salary)           as max_salary,
       ROUND(AVG(salary), 0) as avg_salary
FROM employees
WHERE NULLIF(salary, 0) IS NOT NULL
GROUP BY department_id
HAVING COUNT(department_id) > 3
ORDER BY total_salary DESC;