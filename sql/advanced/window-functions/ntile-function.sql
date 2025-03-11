-- NTILE()
-- SQL NTILE() function is a window function that distributes rows of an ordered partition into a pre-defined number of roughly equal groups.

-- NTILE(number_of_groups_to_split_data_between) OVER (
--     PARTITION BY column1_name
--     ORDER BY column2_name DESC
-- )

-- If we say today is 2021-05-27 and segment each customer, we want to do a marketing activity for that.