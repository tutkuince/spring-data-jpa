-- SQL RIGHT JOIN Keyword
--
-- The RIGHT JOIN keyword returns all records from the right table (table2), and the matching records from the left table (table1).
-- The result is 0 records from the left side, if there is no match.

SELECT
    *
FROM products as p
RIGHT JOIN categories as c on p.category_id = c.category_id;
