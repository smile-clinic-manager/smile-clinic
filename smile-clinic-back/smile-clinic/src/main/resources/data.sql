-- SCRIPT SQL DE INICIALIZACIÓN DE LA BASE DE DATOS

-- Insert permissions
INSERT INTO permissions (permission_id, name) VALUES (1, 'READ_PATIENT_RECORDS');
INSERT INTO permissions (permission_id, name) VALUES (2, 'WRITE_PATIENT_RECORDS');
INSERT INTO permissions (permission_id, name) VALUES (3, 'DELETE_PATIENT_RECORDS');
INSERT INTO permissions (permission_id, name) VALUES (4, 'MANAGE_USERS');
INSERT INTO permissions (permission_id, name) VALUES (5, 'SCHEDULE_APPOINTMENTS');

-- Insert roles
INSERT INTO role (role_id, name) VALUES (1, 'CLINIC_ADMIN');
INSERT INTO role (role_id, name) VALUES (2, 'CLINIC_DENTIST');
INSERT INTO role (role_id, name) VALUES (3, 'CLINIC_RECEPTIONIST');

-- Assign permissions to roles
-- CLINIC_ADMIN has all permissions
INSERT INTO roles_permissions (role_id, permission_id) VALUES (1, 1);  -- READ_PATIENT_RECORDS
INSERT INTO roles_permissions (role_id, permission_id) VALUES (1, 2);  -- WRITE_PATIENT_RECORDS
INSERT INTO roles_permissions (role_id, permission_id) VALUES (1, 3);  -- DELETE_PATIENT_RECORDS
INSERT INTO roles_permissions (role_id, permission_id) VALUES (1, 4);  -- MANAGE_USERS
INSERT INTO roles_permissions (role_id, permission_id) VALUES (1, 5);  -- SCHEDULE_APPOINTMENTS

-- CLINIC_DENTIST can read and write patient records
INSERT INTO roles_permissions (role_id, permission_id) VALUES (2, 1);  -- READ_PATIENT_RECORDS
INSERT INTO roles_permissions (role_id, permission_id) VALUES (2, 2);  -- WRITE_PATIENT_RECORDS

-- CLINIC_RECEPTIONIST can schedule appointments and read patient records
INSERT INTO roles_permissions (role_id, permission_id) VALUES (3, 1);  -- READ_PATIENT_RECORDS
INSERT INTO roles_permissions (role_id, permission_id) VALUES (3, 5);  -- SCHEDULE_APPOINTMENTS

-- Create testing users
/*
 CONTRASEÑAS DE LOS USUARIOS:

    Alice - alice123 - StrongPass1!@
    Bob - bobbyJ - SecureKey99$%
    Charlie - charlieB - A1b2C3d4@#%^
    Diana - dianaGM - TestPass_98*
    Eve - eveWhite - SuperSafe12&(
    Frank - frankScott - MyPass#45!Safe

*/

INSERT INTO users (id, first_name, last_name1, last_name2, username, dni, email, password)
VALUES
    (1, 'Alice', 'Smith', 'Johnson', 'alice123', '12345678A', 'alice.smith@example.com', '$2a$10$zLbgL7uYep14e0HY31IdJOVy1Wzs8.01MuVC1Tb1aTvENPYhnHcam'),
    (2, 'Bob', 'Johnson', NULL, 'bobbyJ', '23456789B', 'bob.johnson@example.com', '$2a$10$zX1hwM8A5iS6LV4zsnHTyudAL/F4zIMzOapiZ59tVfesF.tSx9ZZC'),
    (3, 'Charlie', 'Brown', 'Taylor', 'charlieB', '34567890C', 'charlie.brown@example.com', '$2a$10$EoFHRl80Pfo8O5JJKt9Y0ufqQbeAbruD27aTYlgO2fzaybulFDWUi'),
    (4, 'Diana', 'Green', 'Miller', 'dianaGM', '45678901D', 'diana.green@example.com', '$2a$10$k2qeyegq4pwUvii9mZj5/.s3JOMknzqOiBvjmO20.Bj4oMv1eSVkK'),
    (5, 'Eve', 'White', NULL, 'eveWhite', '56789012E', 'eve.white@example.com', '$2a$10$Jt91UM/Ef/qq2ejFPuA4huNBc.fCXoVyjCKR1E9zNywn/n57FD6oG'),
    (6, 'Frank', 'Black', 'Scott', 'frankScott', '67890123F', 'frank.black@example.com', '$2a$10$83/nvpaXPyYocT/63nWhdOC9C20oSZY7UndfzruhTXDdAc39qDGHu');

-- Create testing clinics
INSERT INTO clinics (clinic_id, name, address, phone_number, email, image, postal_code)
VALUES
    (1, 'Alice', 'Smith', 'Johnson', 'alice123', '12345678A', 'alice.smith@example.com', '$2a$10$zLbgL7uYep14e0HY31IdJOVy1Wzs8.01MuVC1Tb1aTvENPYhnHcam', 'CLINIC_ADMIN'),
    (2, 'Bob', 'Johnson', NULL, 'bobbyJ', '23456789B', 'bob.johnson@example.com', '$2a$10$zX1hwM8A5iS6LV4zsnHTyudAL/F4zIMzOapiZ59tVfesF.tSx9ZZC', 'CLINIC_DENTIST'),
    (3, 'Charlie', 'Brown', 'Taylor', 'charlieB', '34567890C', 'charlie.brown@example.com', '$2a$10$EoFHRl80Pfo8O5JJKt9Y0ufqQbeAbruD27aTYlgO2fzaybulFDWUi', 'CLINIC_ADMIN'),
    (4, 'Diana', 'Green', 'Miller', 'dianaGM', '45678901D', 'diana.green@example.com', '$2a$10$k2qeyegq4pwUvii9mZj5/.s3JOMknzqOiBvjmO20.Bj4oMv1eSVkK', 'CLINIC_DENTIST'),
    (5, 'Eve', 'White', NULL, 'eveWhite', '56789012E', 'eve.white@example.com', '$2a$10$Jt91UM/Ef/qq2ejFPuA4huNBc.fCXoVyjCKR1E9zNywn/n57FD6oG', 'CLINIC_ADMIN'),
    (6, 'Frank', 'Black', 'Scott', 'frankScott', '67890123F', 'frank.black@example.com', '$2a$10$83/nvpaXPyYocT/63nWhdOC9C20oSZY7UndfzruhTXDdAc39qDGHu', 'CLINIC_DENTIST');

INSERT INTO clinics (id, name, address, phone_number, email, image, postal_code)
VALUES
    (8, 'Clínica Dental Sonrisa', 'Calle Mayor 10, Sevilla', '954000111', 'info@dentalsonrisa.com', 'sonrisa.jpg', 41003),
    (9, 'Centro Médico Salud Total', 'Avenida Andalucía 23, Sevilla', '954111222', 'info@saludtotal.com', 'saludtotal.jpg', 41003),
    (10, 'Clínica Bella Vida', 'Calle Sierpes 45, Sevilla', '954222333', 'info@bellavida.com', 'bellavida.jpg', 41001),
    (11, 'Odontologia Avanzada', 'Calle Feria 5, Sevilla', '954333444', 'contacto@avanzada.com', 'avanzada.jpg', 41015),
    (12, 'Centro Boca Sana', 'Plaza Nueva 12, Sevilla', '954444555', 'info@bocasana.com', 'bocasana.jpg', 41004);

INSERT INTO treatments(id, name, price, notes)
VALUES
    (13, 'Limpieza Dental', 50.0, 'Limpieza dental profesional'),
    (14, 'Blanqueamiento Dental', 150.0, 'Blanqueamiento dental con luz LED'),
    (15, 'Ortodoncia', 2000.0, 'Ortodoncia invisible'),
    (16, 'Endodoncia', 500.0, 'Endodoncia de un conducto'),
    (17, 'Implante Dental', 1000.0, 'Implante dental de titanio'),
    (18, 'Carillas Dentales', 300.0, 'Carillas dentales de porcelana'),
    (19, 'Prótesis Dental', 500.0, 'Prótesis dental removible'),
    (20, 'Corona Dental', 300.0, 'Corona dental de porcelana'),
    (21, 'Extracción Dental', 100.0, 'Extracción de muela del juicio'),
    (22, 'Empaste Dental', 50.0, 'Empaste dental de composite');

INSERT INTO clinic_treatments(clinic_id, treatment_id)
VALUES
    (8, 13), (8, 14), (8, 15), (8, 16),
    (9, 17), (9, 18), (9, 19), (9, 20), (9, 21), (9, 22),
    (10, 13), (10, 14),
    (11, 21), (11, 22),
    (12, 13), (12, 14), (12, 15), (12, 16), (12, 17), (12, 18), (12, 19), (12, 20), (12, 21), (12, 22);
