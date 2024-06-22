CREATE TABLE IF NOT EXISTS Task (
    task_id INT PRIMARY KEY,
    task_name VARCHAR(100) NOT NULL,
    task_description VARCHAR(500),
    task_dueDate DATE
);

INSERT INTO Task (task_id, task_name, task_description, task_dueDate) VALUES
    (1, 'Task 1', 'Description for Task 1', '2024-06-20'),
    (2, 'Task 2', 'Description for Task 2', '2024-06-21'),
    (3, 'Task 3', 'Description for Task 3', '2024-06-22');
