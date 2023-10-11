INSERT INTO WORKER
(NAME, BIRTHDAY, LEVEL,SALARY)
VALUES
     ('Antoni', '1987-03-10', 'Senior', '3000'),
     ('David', '1999-05-06', 'Trainee', '1000'),
     ('Andi', '1996-09-20', 'Junior', '1200'),
     ('Meri', '1990-12-31', 'Middle', '2300'),
     ('Julia', '1989-07-27', 'Middle', '2200'),
     ('Simon', '1992-10-11', 'Middle', '2600'),
     ('Jerry', '1982-08-21', 'Trainee', '1100'),
     ('Jack', '1991-01-19', 'Senior', '4100'),
     ('Denis', '1993-02-15', 'Senior', '5100'),
     ('Catherine', '2000-09-03', 'Trainee', '1000');

INSERT INTO CLIENT
(NAME)
VALUES
         ('Amazon'),
         ('McDonalds'),
         ('IKEA'),
         ('Nike'),
         ('Coca-Cola');

INSERT INTO project
(CLIENT_ID, START_DATE, FINISH_DATE)
VALUES
         (1, '2022-01-01', '2023-05-01'),
         (1, '2023-02-01', '2023-06-01'),
         (2, '2023-01-01', '2023-07-01'),
         (3, '2021-04-01', '2023-08-01'),
         (3, '2022-05-01', '2023-09-01'),
         (3, '2023-06-01', '2023-10-01'),
         (4, '2022-07-01', '2023-11-01'),
         (4, '2023-08-01', '2023-12-01'),
         (5, '2020-09-01', '2023-09-01'),
         (5, '2023-01-01', '2024-02-01');

INSERT INTO project_worker (PROJECT_ID, WORKER_ID)
SELECT p.ID AS PROJECT_ID, w.ID AS WORKER_ID
FROM (SELECT ID FROM project) AS p
JOIN (SELECT ID FROM worker ORDER BY RAND() LIMIT FLOOR(1 + RAND() * 5)) AS w;

