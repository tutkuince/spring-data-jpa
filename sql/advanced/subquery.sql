-- Subquery
-- A subquery is a SQL query nested inside a larger query.

-- CTE (Common Table Expressions)

-- 10'dan daha az ürünü olan kategorilerin isimlerini getir

SELECT COALESCE(c.category_name, 'No Category Name'),
       COUNT(p.product_id) as product_count
FROM products as p
         LEFT JOIN categories as c on p.category_id = c.category_id
GROUP BY c.category_name
HAVING COUNT(p.product_id) <= 10;

-- Subquery
SELECT *
FROM (SELECT COALESCE(c.category_name, 'No Category Name'),
             COUNT(p.product_id) as product_count
      FROM products as p
               LEFT JOIN categories as c on p.category_id = c.category_id
      GROUP BY c.category_name) as q
WHERE product_count <= 10;

-- CTE
-- WITH product_count as (
--     SELECT COALESCE(c.category_name, 'No Category Name'),
--            COUNT(p.product_id) as product_count
--     FROM products as p
--              LEFT JOIN categories as c on p.category_id = c.category_id
--     GROUP BY c.category_name
-- )
--
-- SELECT
--     *
-- FROM product_count
-- WHERE product_count <= 10;

-- CTE v2
WITH product_count as (
    SELECT
        category_id,
        COUNT(product_id)
    FROM products
    WHERE category_id IS NOT NULL
    GROUP BY category_id
),
    category_list as (
        SELECT
            category_id,
            category_name
        FROM categories
    )

SELECT
    category_name,
    product_count
FROM product_count as pc
LEFT JOIN category_list as cl on cl.category_id = pc.category_id
WHERE product_count <= 10;
