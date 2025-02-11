-- Managing missing data with COALESCE function
-- Return the first non-null value in a list:
SELECT first_name || ' ' ||last_name as full_name,
       phone_number,
       phone_number_2,
       COALESCE(phone_number, 'No First Phone Number') as first_phone,
       COALESCE(phone_number, phone_number_2),
       COALESCE(phone_number, phone_number_2, 'No Phone Number')
FROM employees;
