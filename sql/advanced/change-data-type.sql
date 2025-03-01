-- Changing Data Types

-- CAST
-- En fazla rezervasyon yapılan tarih
SELECT bookingdate,
       CAST(bookingdate as date),
       bookingdate::date,
       COUNT(*)
FROM bookings
GROUP BY bookingdate
ORDER BY 4;


SELECT CAST('123' as integer);
-- avg, sum, min, max

SELECT CAST(123 as text);

SELECT CAST('Hello, World!' as varchar(5));

SELECT CAST('Hello' as char(10));

SELECT CAST('true' as boolean);

SELECT CAST('2024-08-31' as date);

SELECT CAST('2024-08-31 15:30:00' as timestamp);

-- TO_CHAR
SELECT
    bookingdate,
    bookingdate::date,
    to_char(bookingdate, 'YYYY-MM'),
    to_char(bookingdate, 'YY-MM'),
    to_char(bookingdate, 'YY-MM-DD'),
    to_char(bookingdate, 'YYYY-MM-DD'),
    to_char(bookingdate, 'Day'),
    to_char(bookingdate, 'Month')
FROM bookings;