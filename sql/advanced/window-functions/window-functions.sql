-- Window Functions
-- Window functions are functions that divide the data into parts (partition) or order and perform calculations on these parts
-- When performing aggregation operations such as GROUP BY, instead of grouping all the data and returning a single result, it calculates a specific range for each row.

-- (window function) OVER (window specification)
-- (window function): The OVER() expression is where the window functions are used and this expression defines how the window is created.
-- (window specification): Specifies in which range, in which order and in which partition the window function will run.

-- Window Functions - Partitioning
-- sum(Revenue) OVER (Partition by Channel)

-- <window function> ( [expression] ) OVER(
--  [PARTITION BY partition_expression, ...]
--  [ORDER BY sort_expression [ASC|DESC], ...]
--  [ROWS frame_specification]
-- )

-- Window Functions with Aggregate
-- AVG(column_name) OVER (PARTITION BY column_name)

-- With each employee information, show the average salary information of the relevant department.

-- solution without window function
with avg_salary as (SELECT
    department_id,
    ROUND(AVG(salary), 2) as d_avg_salary
FROM employees
GROUP BY department_id)

SELECT
    e.first_name,
    e.last_name,
    e.department_id,
    d.department_name,
    e.salary,
    d_avg_salary
FROM employees as e
LEFT JOIN avg_salary as a_s on e.department_id = a_s.department_id
LEFT JOIN departments as d on d.department_id = e.department_id;


-- solution with window function

SELECT
    e.first_name,
    e.last_name,
    d.department_id,
    d.department_name,
    e.salary,
    AVG(e.salary) OVER () as avg_salary,
    AVG(e.salary) OVER (PARTITION BY e.department_id) as dep_avg_salary,
    SUM(e.salary) OVER (PARTITION BY e.department_id) as dep_sum_salary
FROM employees as e
LEFT JOIN departments as d on e.department_id = d.department_id;
