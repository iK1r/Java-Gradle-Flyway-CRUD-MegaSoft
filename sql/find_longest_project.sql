WITH project_durations AS (
    SELECT name, DATEDIFF(MONTH, start_date, finish_date) AS month_count
    FROM project
)
SELECT name AS NAME, month_count AS MONTH_COUNT
FROM project_durations
WHERE month_count = (SELECT MAX(month_count) FROM project_durations)
ORDER BY name;
