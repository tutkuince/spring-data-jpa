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
