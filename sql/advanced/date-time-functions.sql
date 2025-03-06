-- Date/Time Functions

SELECT current_date;

SELECT current_time;

SELECT now();

-- DATE_TRUNC() function
-- The PostgreSQL DATE_TRUNC() function is used to truncate a timestamp to the specified precision, such as hour, day, week, month, etc.
-- Truncate to Year: Truncate a timestamp to the beginning of the year.
-- Truncate to Month: Truncate a timestamp to the beginning of the month.
-- Truncate to Day: Truncate a timestamp to the beginning of the day.
-- Truncate to Hour: Truncate a timestamp to the beginning of the hour.
-- Truncate to Minute: Truncate a timestamp to the beginning of the minute.
-- Truncate to Second: Truncate a timestamp to the beginning of the second.

SELECT
    bookingdate,
    date_trunc('year', bookingdate),
    date_trunc('month', bookingdate),
    date_trunc('day', bookingdate),
    date_trunc('hour', bookingdate),
    date_trunc('minute', bookingdate),
    date_trunc('second', bookingdate)
FROM bookings;

-- EXTRACT Function
-- EXTRACT() function is a powerful tool used to retrieve specific components of a date or time value.
SELECT EXTRACT(YEAR FROM '2024-06-01 12:30:15'::TIMESTAMP); -- 2024
SELECT EXTRACT(DAY FROM '2024-06-01 12:30:15'::TIMESTAMP); -- 1

SELECT
    bookingdate,
    extract(month from bookingdate)
FROM bookings;

-- AGE() function => subtracts two timestamps, producing a symbolic result that uses years and months.
SELECT age('2022-06-03 11:30:15'::timestamp, '2022-06-01 12:30:15'::timestamp);
SELECT '2022-06-03 11:30:15'::timestamp - '2022-06-01 12:30:15'::timestamp;
SELECT EXTRACT(DAY FROM '2022-06-03 11:30:15'::timestamp - '2022-06-01 12:30:15'::timestamp);


-- Müşterinin son siparişi üzerinden kaç gün geçmiş.
SELECT
    bookingdate,
    current_date,
    extract(DAY FROM current_date - bookingdate)
FROM bookings;

-- date_part
SELECT date_part('month', '2022-06-01 12:30:20'::timestamp);
SELECT extract(MONTH FROM '2022-06-01 12:30:20'::timestamp);