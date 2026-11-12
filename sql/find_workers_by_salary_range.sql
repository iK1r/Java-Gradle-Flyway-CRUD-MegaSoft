-- Employees whose salaries are between the given values (inclusive)
SELECT name AS NAME, salary AS SALARY
FROM worker
WHERE salary BETWEEN ? AND ?
ORDER BY salary DESC, name;
