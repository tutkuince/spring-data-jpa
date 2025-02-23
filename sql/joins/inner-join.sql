-- INNER JOIN
--
-- The INNER JOIN keyword selects records that have matching values in both tables.

-- Hangi müşteri hangi siparişi vermiş? order_id, company_name, order_date

SELECT
    o.order_id,
    c.company_name,
    o.order_date
FROM orders as o
INNER JOIN customers as c on o.customer_id = c.customer_id;


-- Her ürünün ismini ve kategori ismini getir.

SELECT
    p.product_name,
    c.category_name
FROM products as p
INNER JOIN categories as c on p.category_id = c.category_id;