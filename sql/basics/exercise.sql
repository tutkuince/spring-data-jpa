-- You work as the company's HR analyst. The year-end performance assessment is approaching and the Board of Directors asked you to prepare a employee profile report.
-- The report should include the following information:

-- Write the SQL query that creates an output in the following structure from the database:
-- Full_name: The full name of the employee (first_name and last_name combined, all capital letters)
-- email: Employee's email address
-- Experience_Level: Seniority level (Seniority "Senior", then "new")
-- salary_level: salary category
--      * 15000+: "senior"
--      * 10000-14999: "high"
--      * 5000-9999: "Middle"
--      * 5000 six: "start"
-- Contact_info: Communication Information (If you don't have a phone number, the email address will be used)
-- Salary_status: Salary information (0 or null "will be" updated ")

SELECT
    CONCAT(UPPER(first_name), ' ', UPPER(last_name)) as full_name,
    email,
    CASE
        WHEN hire_date < '1997-01-01' THEN 'Senior'
        ELSE 'New'
        END as experience_level,
    CASE
        WHEN NULLIF(salary, 0) >= 15000 THEN 'Senior'
        WHEN NULLIF(salary, 0) >= 10000 THEN 'High'
        WHEN NULLIF(salary, 0) >= 5000 THEN 'Middle'
        WHEN NULLIF(salary, 0) < 5000 THEN 'Start'
        ELSE 'Needs to be updated'
        END as salary_level,
    COALESCE(phone_number, email) as contact_info,
    CASE
        WHEN NULLIF(salary, 0) IS NULL THEN 'Needs to be updated'
        ELSE CAST(salary AS VARCHAR)
        END as salary_status
FROM employees
ORDER BY salary DESC;