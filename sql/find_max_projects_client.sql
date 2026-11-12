WITH project_counts AS (
    SELECT c.name, COUNT(p.id) AS project_count
    FROM client c
    LEFT JOIN project p ON p.client_id = c.id
    GROUP BY c.id, c.name
)
SELECT name AS NAME, project_count AS PROJECT_COUNT
FROM project_counts
WHERE project_count = (SELECT MAX(project_count) FROM project_counts)
ORDER BY name;
