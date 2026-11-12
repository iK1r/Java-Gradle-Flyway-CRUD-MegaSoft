-- MegaSoft: sample data (run after init_db.sql)

INSERT INTO worker (id, name, birthday, level, salary) VALUES
    (1, 'Mia Chen', '2003-08-02', 'Trainee', 650),
    (2, 'Noah Miller', '2002-04-16', 'Trainee', 800),
    (3, 'Ava Brown', '1999-09-20', 'Junior', 1600),
    (4, 'Ethan Wilson', '1998-11-30', 'Junior', 2000),
    (5, 'Sofia Garcia', '1995-02-14', 'Middle', 3800),
    (6, 'Lucas Davis', '1993-05-09', 'Middle', 4500),
    (7, 'Amelia Clark', '1988-06-21', 'Senior', 6800),
    (8, 'Oliver Smith', '1984-12-05', 'Senior', 9100),
    (9, 'Emma Johnson', '1990-07-14', 'Senior', 9100),
    (10, 'James Taylor', '1978-01-01', 'Senior', 7200),
    (11, 'Lily Moore', '2004-01-15', 'Trainee', 750),
    (12, 'Henry King', '2004-01-15', 'Junior', 1400);

INSERT INTO client (id, name) VALUES
    (1, 'Northstar Group'),
    (2, 'Blue Ocean Ltd'),
    (3, 'Bright Future Inc'),
    (4, 'Green Valley LLC'),
    (5, 'Summit Partners');

INSERT INTO project (id, client_id, name, start_date, finish_date) VALUES
    (1, 1, 'Website Redesign', '2021-01-01', '2021-07-01'),
    (2, 1, 'Mobile Banking', '2020-02-01', '2021-08-01'),
    (3, 1, 'Analytics Platform', '2019-03-01', '2024-03-01'),
    (4, 2, 'CRM System', '2022-04-01', '2023-04-01'),
    (5, 2, 'Cloud Migration', '2019-06-01', '2023-06-01'),
    (6, 2, 'Support Portal', '2023-07-01', '2024-04-01'),
    (7, 3, 'Inventory Manager', '2020-09-01', '2025-09-01'),
    (8, 3, 'E-commerce Platform', '2021-10-01', '2024-04-01'),
    (9, 4, 'Logistics Dashboard', '2022-05-01', '2024-05-01'),
    (10, 5, 'Booking System', '2024-01-01', '2025-01-01');

INSERT INTO project_worker (project_id, worker_id) VALUES
    (1, 1), (1, 3),
    (2, 2), (2, 4), (2, 5),
    (3, 6), (3, 7), (3, 8), (3, 9),
    (4, 3), (4, 5), (4, 10),
    (5, 2), (5, 6),
    (6, 11),
    (7, 5), (7, 8), (7, 9), (7, 10), (7, 12),
    (8, 1), (8, 6), (8, 7),
    (9, 4), (9, 9),
    (10, 2), (10, 5), (10, 8), (10, 12);

-- Old records have explicit IDs; make new identity values start after them.
ALTER TABLE worker ALTER COLUMN id RESTART WITH 13;
ALTER TABLE client ALTER COLUMN id RESTART WITH 6;
ALTER TABLE project ALTER COLUMN id RESTART WITH 11;
