-- Insert sample data into the 'students' table with snake_case column names

/*
 CONTRASEÑAS DE LOS USUARIOS:

    Alice - alice123 - StrongPass1!@
    Bob - bobbyJ - SecureKey99$%
    Charlie - charlieB - A1b2C3d4@#%^
    Diana - dianaGM - TestPass_98*
    Eve - eveWhite - SuperSafe12&(
    Frank - frankScott - MyPass#45!Safe

*/

INSERT INTO users (id, first_name, last_name1, last_name2, username, dni, email, password, role)
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
