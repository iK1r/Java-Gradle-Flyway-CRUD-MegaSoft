SELECT
    p.name AS NAME,
    DATEDIFF(MONTH, p.start_date, p.finish_date)
        * COALESCE(SUM(w.salary), 0) AS PRICE
FROM project p
LEFT JOIN project_worker pw ON pw.project_id = p.id
LEFT JOIN worker w ON w.id = pw.worker_id
GROUP BY p.id, p.name, p.start_date, p.finish_date
ORDER BY PRICE DESC, NAME;
