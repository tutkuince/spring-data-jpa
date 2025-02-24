-- SQL LEFT JOIN Keyword
--
-- The LEFT JOIN keyword returns all records from the left table (table1), and the matching records from the right table (table2).
-- The result is 0 records from the right side, if there is no match.

-- List of customer orders
SELECT
    c.company_name,
    COUNT(o.order_id) as count_ourder,
FROM orders as o
LEFT JOIN customers as c on o.customer_id = c.customer_id
GROUP BY company_name;

-- müşterilerin sipariş geçtiği toplam tutar

SELECT
    c.company_name,
    SUM(od.unit_price * od.quantity * (1 - od.discount))
FROM orders as o
LEFT JOIN order_details as od on o.order_id = od.order_id
LEFT JOIN customers as c on o.customer_id = c.customer_id
GROUP BY c.company_name;


-- Her ürünün ismini ve kategorisini getir.
SELECT
    *
FROM products as p
LEFT JOIN categories as c on c.category_id = p.category_id;