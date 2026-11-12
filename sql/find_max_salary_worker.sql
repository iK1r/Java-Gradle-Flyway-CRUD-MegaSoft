SELECT name AS NAME, salary AS SALARY
FROM worker
WHERE salary = (SELECT MAX(salary) FROM worker)
ORDER BY name;
