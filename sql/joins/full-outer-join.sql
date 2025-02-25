-- SQL FULL OUTER JOIN Keyword
--
-- The FULL OUTER JOIN keyword returns all records when there is a match in left (table1) or right (table2) table records.
--
-- Tip: FULL OUTER JOIN and FULL JOIN are the same.

SELECT
    *
FROM orders as o
FULL OUTER JOIN employees as e on o.employee_id = e.employee_id;