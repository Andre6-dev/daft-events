-- Inserting data into roles table
INSERT INTO user_management.roles (role_name, permission)
VALUES ('ROLE_USER', 'READ:USER,READ:CUSTOMER'),
       ('ROLE_MANAGER', 'READ:USER,READ:CUSTOMER,UPDATE:USER,UPDATE:CUSTOMER'),
       ('ROLE_ADMIN', 'READ:USER,READ:CUSTOMER,CREATE:USER,CREATE:CUSTOMER,UPDATE:USER,UPDATE:CUSTOMER'),
       ('ROLE_SYSADMIN',
        'READ:USER,READ:CUSTOMER,CREATE:USER,CREATE:CUSTOMER,UPDATE:USER,UPDATE:CUSTOMER,DELETE:USER,DELETE:CUSTOMER');

-- Inserting data into auth_user table
INSERT INTO user_management.users (user_id, username, email, password_hash, first_name, last_name, document_number,
                                   phone_number, address, profile_url, enabled, non_locked, using_mfa, created_at,
                                   updated_at)
VALUES (1, 'johndoe', 'johndoe@example.com', 'hash1', 'John', 'Doe', 'DOC123456789', '555-1234',
        '123 Main St, Anytown, USA',
        'https://images.squarespace-cdn.com/content/v1/5eb48d3fef49df153d320521/1618032587947-LCJQDE1Q9CGKRRM774H2/Daft+Punk+Toy+Face+II+.jpg',
        'true', 'true', 'false', NOW(), NOW()),

       (2, 'janedoe', 'janedoe@example.com', 'hash2', 'Jane', 'Doe', 'DOC987654321', '555-5678',
        '456 Maple St, Anytown, USA',
        'https://images.squarespace-cdn.com/content/v1/5eb48d3fef49df153d320521/1618032587947-LCJQDE1Q9CGKRRM774H2/Daft+Punk+Toy+Face+II+.jpg',
        'true', 'true', 'true', NOW(), NOW()),

       (3, 'admin', 'admin@example.com', 'hash3', 'Admin', 'User', 'DOC112233445', '555-0000',
        '789 Oak St, Anytown, USA',
        'https://images.squarespace-cdn.com/content/v1/5eb48d3fef49df153d320521/1618032587947-LCJQDE1Q9CGKRRM774H2/Daft+Punk+Toy+Face+II+.jpg',
        'true', 'true', 'true', NOW(), NOW()),

       (4, 'michael', 'michael@example.com', 'hash4', 'Michael', 'Smith', 'DOC223344556', '555-2222',
        '101 Pine St, Anytown, USA',
        'https://images.squarespace-cdn.com/content/v1/5eb48d3fef49df153d320521/1618032587947-LCJQDE1Q9CGKRRM774H2/Daft+Punk+Toy+Face+II+.jpg',
        'true', 'true', 'false', NOW(), NOW()),

       (5, 'emily', 'emily@example.com', 'hash5', 'Emily', 'Jones', 'DOC334455667', '555-3333',
        '202 Birch St, Anytown, USA',
        'https://images.squarespace-cdn.com/content/v1/5eb48d3fef49df153d320521/1618032587947-LCJQDE1Q9CGKRRM774H2/Daft+Punk+Toy+Face+II+.jpg',
        'true', 'true', 'true', NOW(), NOW()),

       (6, 'chris', 'chris@example.com', 'hash6', 'Chris', 'Brown', 'DOC445566778', '555-4444',
        '303 Cedar St, Anytown, USA',
        'https://images.squarespace-cdn.com/content/v1/5eb48d3fef49df153d320521/1618032587947-LCJQDE1Q9CGKRRM774H2/Daft+Punk+Toy+Face+II+.jpg',
        'true', 'true', 'false', NOW(), NOW()),

       (7, 'jessica', 'jessica@example.com', 'hash7', 'Jessica', 'Davis', 'DOC556677889', '555-5555',
        '404 Elm St, Anytown, USA',
        'https://images.squarespace-cdn.com/content/v1/5eb48d3fef49df153d320521/1618032587947-LCJQDE1Q9CGKRRM774H2/Daft+Punk+Toy+Face+II+.jpg',
        'true', 'true', 'true', NOW(), NOW()),

       (8, 'david', 'david@example.com', 'hash8', 'David', 'Miller', 'DOC667788990', '555-6666',
        '505 Spruce St, Anytown, USA',
        'https://images.squarespace-cdn.com/content/v1/5eb48d3fef49df153d320521/1618032587947-LCJQDE1Q9CGKRRM774H2/Daft+Punk+Toy+Face+II+.jpg',
        'true', 'true', 'false', NOW(), NOW()),

       (9, 'laura', 'laura@example.com', 'hash9', 'Laura', 'Wilson', 'DOC778899001', '555-7777',
        '606 Willow St, Anytown, USA',
        'https://images.squarespace-cdn.com/content/v1/5eb48d3fef49df153d320521/1618032587947-LCJQDE1Q9CGKRRM774H2/Daft+Punk+Toy+Face+II+.jpg',
        'true', 'true', 'true', NOW(), NOW()),

       (10, 'daniel', 'daniel@example.com', 'hash10', 'Daniel', 'Taylor', 'DOC889900112', '555-8888',
        '707 Poplar St, Anytown, USA',
        'https://images.squarespace-cdn.com/content/v1/5eb48d3fef49df153d320521/1618032587947-LCJQDE1Q9CGKRRM774H2/Daft+Punk+Toy+Face+II+.jpg',
        'true', 'true', 'false', NOW(), NOW());


-- Inserting data into users_roles table
INSERT INTO user_management.user_roles (user_id, role_id)
VALUES (1, 1), -- John Doe is a User
       (2, 2), -- Jane Smith is a Manager
       (3, 3), -- Mike Johnson is an Admin
       (4, 4), -- Emily Brown is a SysAdmin
       (5, 1), -- David Lee is an Admin
       (6, 2), -- Sarah Wilson is a Manager
       (7, 3), -- Tom Taylor is an Admin
       (8, 4), -- Lisa Anderson is a SysAdmin
       (9, 1), -- Chris Martin is an Admin
       (10, 2); -- Emma Garcia is a Manager