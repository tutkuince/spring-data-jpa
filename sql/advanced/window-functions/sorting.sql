-- ROW_NUMBER
-- Determines the ordinal number of the current row within a group of rows, counting from 1, based on the ORDER BY expression in the OVER clause.
-- If the optional PARTITION BY clause is present, the ordinal numbers are reset for each group of rows.
-- Rows with equal values for the ORDER BY expressions receive the different row numbers nondeterministically.
-- ROW_NUMBER () OVER
-- (
--     [ PARTITION BY expr_list ]
--     [ ORDER BY order_list ]
-- )

-- List the orders of each customer in date order.

SELECT
    contactid,
    bookingdate,
    to_char(bookingdate, 'YYYY-MM-DD') as booking_day,
    ROW_NUMBER() OVER (PARTITION BY contactid ORDER BY to_char(bookingdate, 'YYYY-MM-DD')) as row_num
FROM bookings;