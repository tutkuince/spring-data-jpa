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
