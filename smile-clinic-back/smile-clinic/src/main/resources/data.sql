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

    --------------CONTRASEÑAS DE LOS USUARIOS-------------------

    ------------- Alice - alice123 - StrongPass1!@ -------------
    ------------- Bob - bobbyJ - SecureKey99$% -----------------
    ------------- Charlie - charlieB - A1b2C3d4@#%^ ------------
    ------------- Diana - dianaGM - TestPass_98* ---------------
    ------------- Eve - eveWhite - SuperSafe12&( ---------------
    ------------- Frank - frankScott - MyPass#45!Safe ----------
    ------------- PRUEBA - pruebaScott - MyPass#45!Safe ----------

-- ************************************************************** --

-- Create testing users
INSERT INTO users (id, first_name, last_name1, last_name2, username, dni, email, password)
VALUES
    (1, 'Alice', 'Smith', 'Johnson', 'alice123', '12345678A', 'alice.smith@example.com', '$2a$10$zLbgL7uYep14e0HY31IdJOVy1Wzs8.01MuVC1Tb1aTvENPYhnHcam'),
    (2, 'Bob', 'Johnson', NULL, 'bobbyJ', '23456789B', 'bob.johnson@example.com', '$2a$10$zX1hwM8A5iS6LV4zsnHTyudAL/F4zIMzOapiZ59tVfesF.tSx9ZZC'),
    (3, 'Charlie', 'Brown', 'Taylor', 'charlieB', '34567890C', 'charlie.brown@example.com', '$2a$10$EoFHRl80Pfo8O5JJKt9Y0ufqQbeAbruD27aTYlgO2fzaybulFDWUi'),
    (4, 'Diana', 'Green', 'Miller', 'dianaGM', '45678901D', 'diana.green@example.com', '$2a$10$k2qeyegq4pwUvii9mZj5/.s3JOMknzqOiBvjmO20.Bj4oMv1eSVkK'),
    (5, 'Eve', 'White', NULL, 'eveWhite', '56789012E', 'eve.white@example.com', '$2a$10$Jt91UM/Ef/qq2ejFPuA4huNBc.fCXoVyjCKR1E9zNywn/n57FD6oG'),
    (6, 'Frank', 'Black', 'Scott', 'frankScott', '67890123F', 'frank.black@example.com', '$2a$10$83/nvpaXPyYocT/63nWhdOC9C20oSZY7UndfzruhTXDdAc39qDGHu'),
    (7, 'PRUEBA', 'Black', 'Scott', 'pruebaScott', '67890123P', 'prueba.black@example.com', '$2a$10$83/nvpaXPyYocT/63nWhdOC9C20oSZY7UndfzruhTXDdAc39qDGHu');

-- Create testing clinics
INSERT INTO clinics (clinic_id, name, address, phone_number, email, image, postal_code)
VALUES
    (8, 'Clínica Dental Sonrisa', 'Calle Mayor 10, Sevilla', '954000111', 'info@dentalsonrisa.com', 'sonrisa.jpg', 41003),
    (9, 'Centro Médico Salud Total', 'Avenida Andalucía 23, Sevilla', '954111222', 'info@saludtotal.com', 'saludtotal.jpg', 41003),
    (10, 'Clínica Bella Vida', 'Calle Sierpes 45, Sevilla', '954222333', 'info@bellavida.com', 'bellavida.jpg', 41001),
    (11, 'Odontologia Avanzada', 'Calle Feria 5, Sevilla', '954333444', 'contacto@avanzada.com', 'avanzada.jpg', 41015),
    (12, 'Centro Boca Sana', 'Plaza Nueva 12, Sevilla', '954444555', 'info@bocasana.com', 'bocasana.jpg', 41004);

-- Assigning relation between Clinics & Users with their respective roles

-------------------- **ALICE** -------------------
-- (CLINIC_ADMIN) - Clínica Dental Sonrisa
INSERT INTO user_clinic_role (user_clinic_role_id, user_id, clinic_id, role_id) VALUES (NEXTVAL('seq_user_clinic_role'), 1, 8, 1);  -- Alice, CLINIC_ADMIN, Clínica Dental Sonrisa
INSERT INTO user_clinic_role (user_clinic_role_id, user_id, clinic_id, role_id) VALUES (NEXTVAL('seq_user_clinic_role'), 1, 8, 2);  -- Alice, CLINIC_ADMIN, Clínica Dental Sonrisa

-- (CLINIC_DENTIST) - Centro Médico Salud Total
INSERT INTO user_clinic_role (user_clinic_role_id, user_id, clinic_id, role_id) VALUES (NEXTVAL('seq_user_clinic_role'), 1, 9, 2);  -- Alice, CLINIC_DENTIST, Centro Médico Salud Total

-------------------- **BOB** -------------------
-- (CLINIC_ADMIN) - Centro Médico Salud Total
INSERT INTO user_clinic_role (user_clinic_role_id, user_id, clinic_id, role_id) VALUES (NEXTVAL('seq_user_clinic_role'), 2, 9, 1);  -- Bob, CLINIC_ADMIN, Centro Médico Salud Total

-- (CLINIC_ADMIN) - Clínica Dental Sonrisa
INSERT INTO user_clinic_role (user_clinic_role_id, user_id, clinic_id, role_id) VALUES (NEXTVAL('seq_user_clinic_role'), 2, 8, 1);  -- Bob, CLINIC_ADMIN, Clínica Dental Sonrisa

-------------------- **CHARLIE** -------------------
-- (CLINIC_RECEPTIONIST) - Clínica Dental Sonrisa
INSERT INTO user_clinic_role (user_clinic_role_id, user_id, clinic_id, role_id) VALUES (NEXTVAL('seq_user_clinic_role'), 3, 8, 3);  -- Charlie, CLINIC_RECEPTIONIST, Clínica Dental Sonrisa

-- (CLINIC_ADMIN) - Centro Médico Salud Total
INSERT INTO user_clinic_role (user_clinic_role_id, user_id, clinic_id, role_id) VALUES (NEXTVAL('seq_user_clinic_role'), 3, 9, 1);  -- Charlie, CLINIC_ADMIN, Centro Médico Salud Total

-------------------- **DIANA** -------------------
-- (CLINIC_DENTIST) - Centro Médico Salud Total
INSERT INTO user_clinic_role (user_clinic_role_id, user_id, clinic_id, role_id) VALUES (NEXTVAL('seq_user_clinic_role'), 4, 9, 2);  -- Diana, CLINIC_DENTIST, Centro Médico Salud Total

-- (CLINIC_RECEPTIONIST) - Clínica Dental Sonrisa
INSERT INTO user_clinic_role (user_clinic_role_id, user_id, clinic_id, role_id) VALUES (NEXTVAL('seq_user_clinic_role'), 4, 8, 3);  -- Diana, CLINIC_RECEPTIONIST, Clínica Dental Sonrisa

-------------------- **EVE** -------------------
-- (CLINIC_RECEPTIONIST) - Centro Médico Salud Total
INSERT INTO user_clinic_role (user_clinic_role_id, user_id, clinic_id, role_id) VALUES (NEXTVAL('seq_user_clinic_role'), 5, 9, 3);  -- Eve, CLINIC_RECEPTIONIST, Centro Médico Salud Total

-- (CLINIC_ADMIN) - Clínica Dental Sonrisa
INSERT INTO user_clinic_role (user_clinic_role_id, user_id, clinic_id, role_id) VALUES (NEXTVAL('seq_user_clinic_role'), 5, 8, 1);  -- Eve, CLINIC_ADMIN, Clínica Dental Sonrisa

-------------------- **FRANK** -------------------
-- (CLINIC_DENTIST) - Clínica Dental Sonrisa
INSERT INTO user_clinic_role (user_clinic_role_id, user_id, clinic_id, role_id) VALUES (NEXTVAL('seq_user_clinic_role'), 6, 8, 2);  -- Frank, CLINIC_DENTIST, Clínica Dental Sonrisa

-- (CLINIC_RECEPTIONIST) - Centro Médico Salud Total
INSERT INTO user_clinic_role (user_clinic_role_id, user_id, clinic_id, role_id) VALUES (NEXTVAL('seq_user_clinic_role'), 6, 9, 3);  -- Frank, CLINIC_RECEPTIONIST, Centro Médico Salud Total

-- Tratamientos
INSERT INTO treatments(id, name, price, notes, clinic_id)
VALUES
    (13, 'Limpieza Dental', 50.0, 'Limpieza dental profesional', 8),
    (14, 'Blanqueamiento Dental', 150.0, 'Blanqueamiento dental con luz LED', 8),
    (15, 'Ortodoncia', 2000.0, 'Ortodoncia invisible', 8),
    (16, 'Endodoncia', 500.0, 'Endodoncia de un conducto', 8),
    (17, 'Implante Dental', 1000.0, 'Implante dental de titanio', 8),
    (18, 'Carillas Dentales', 300.0, 'Carillas dentales de porcelana', 8),
    (19, 'Prótesis Dental', 500.0, 'Prótesis dental removible', 9),
    (20, 'Corona Dental', 300.0, 'Corona dental de porcelana', 9),
    (21, 'Extracción Dental', 100.0, 'Extracción de muela del juicio', 9),
    (22, 'Empaste Dental', 50.0, 'Empaste dental de composite', 9);

-- Insertar enfermedades previas
INSERT INTO previous_diseases (id, name) VALUES (NEXTVAL('patients_seq'), 'Hipertensión arterial');
INSERT INTO previous_diseases (id, name) VALUES (NEXTVAL('patients_seq'), 'Diabetes');
INSERT INTO previous_diseases (id, name) VALUES (NEXTVAL('patients_seq'), 'Asma');
INSERT INTO previous_diseases (id, name) VALUES (NEXTVAL('patients_seq'), 'Enfermedades del corazón');
INSERT INTO previous_diseases (id, name) VALUES (NEXTVAL('patients_seq'), 'VIH+');
INSERT INTO previous_diseases (id, name) VALUES (NEXTVAL('patients_seq'), 'Hepatitis A');
INSERT INTO previous_diseases (id, name) VALUES (NEXTVAL('patients_seq'), 'Hepatitis B');
INSERT INTO previous_diseases (id, name) VALUES (NEXTVAL('patients_seq'), 'Hepatitis C');
INSERT INTO previous_diseases (id, name) VALUES (NEXTVAL('patients_seq'), 'Hepatitis D');
INSERT INTO previous_diseases (id, name) VALUES (NEXTVAL('patients_seq'), 'Hepatitis E');

INSERT INTO tooth (id, code, name) VALUES
    (1,  '11', 'Incisivo central superior derecho'),
    (2,  '12', 'Incisivo lateral superior derecho'),
    (3,  '13', 'Canino superior derecho'),
    (4,  '14', 'Primer premolar superior derecho'),
    (5,  '15', 'Segundo premolar superior derecho'),
    (6,  '16', 'Primer molar superior derecho'),
    (7,  '17', 'Segundo molar superior derecho'),
    (8,  '18', 'Tercer molar superior derecho'),

    (9,  '21', 'Incisivo central superior izquierdo'),
    (10, '22', 'Incisivo lateral superior izquierdo'),
    (11, '23', 'Canino superior izquierdo'),
    (12, '24', 'Primer premolar superior izquierdo'),
    (13, '25', 'Segundo premolar superior izquierdo'),
    (14, '26', 'Primer molar superior izquierdo'),
    (15, '27', 'Segundo molar superior izquierdo'),
    (16, '28', 'Tercer molar superior izquierdo'),

    (17, '31', 'Incisivo central inferior izquierdo'),
    (18, '32', 'Incisivo lateral inferior izquierdo'),
    (19, '33', 'Canino inferior izquierdo'),
    (20, '34', 'Primer premolar inferior izquierdo'),
    (21, '35', 'Segundo premolar inferior izquierdo'),
    (22, '36', 'Primer molar inferior izquierdo'),
    (23, '37', 'Segundo molar inferior izquierdo'),
    (24, '38', 'Tercer molar inferior izquierdo'),

    (25, '41', 'Incisivo central inferior derecho'),
    (26, '42', 'Incisivo lateral inferior derecho'),
    (27, '43', 'Canino inferior derecho'),
    (28, '44', 'Primer premolar inferior derecho'),
    (29, '45', 'Segundo premolar inferior derecho'),
    (30, '46', 'Primer molar inferior derecho'),
    (31, '47', 'Segundo molar inferior derecho'),
    (32, '48', 'Tercer molar inferior derecho');

-- Insertar historias clínicas
INSERT INTO medical_history (id, allergies) VALUES (1, 'Ninguna');
INSERT INTO medical_history (id, allergies) VALUES (2, 'Penicilina');
INSERT INTO medical_history (id, allergies) VALUES (3, 'Mariscos');
INSERT INTO medical_history (id, allergies) VALUES (4, 'Polen');
INSERT INTO medical_history (id, allergies) VALUES (5, 'Lácteos');
INSERT INTO medical_history (id, allergies) VALUES (6, 'Gluten');
INSERT INTO medical_history (id, allergies) VALUES (7, 'Ninguna');
INSERT INTO medical_history (id, allergies) VALUES (8, 'Ibuprofeno');
INSERT INTO medical_history (id, allergies) VALUES (9, 'Aspirina');
INSERT INTO medical_history (id, allergies) VALUES (10, 'Picaduras de abeja');

ALTER SEQUENCE medical_history_seq RESTART WITH 11;

-- Insertar pacientes (sin columna allergies)
INSERT INTO patients (id, first_name, last_name1, last_name2, dni, email, phone_number, clinic_id, medical_history_id) VALUES
    (23, 'Pablo', 'García', 'Martínez', '12345678A', 'pablo@garcia.com', '123123123', 8, 1),
    (24, 'María', 'López', 'Gómez', '23456789B', 'maria@lop.com', '321321321', 9, 2),
    (25, 'Luis', 'Ramírez', 'Soto', '34567890C', 'luis@ramirez.com', '111222333', 8, 3),
    (26, 'Ana', 'Moreno', 'Díaz', '45678901D', 'ana@moreno.com', '444555666', 9, 4),
    (27, 'Carlos', 'Jiménez', 'Rey', '56789012E', 'carlos@jimenez.com', '777888999', 8, 5),
    (28, 'Lucía', 'Torres', 'Rivas', '67890123F', 'lucia@torres.com', '123321123', 9, 6),
    (29, 'Javier', 'Hernández', 'Cruz', '78901234G', 'javier@hernandez.com', '654987321', 8, 7),
    (30, 'Elena', 'Pérez', 'Sanz', '89012345H', 'elena@perez.com', '789789789', 9, 8),
    (31, 'Diego', 'Ortega', 'Lozano', '90123456I', 'diego@ortega.com', '456456456', 8, 9),
    (32, 'Sara', 'Navarro', 'Blanco', '01234567J', 'sara@navarro.com', '987987987', 9, 10);

-- Relación de enfermedades previas con las historias clínicas
-- Pablo (id=1): Hepatitis B, C
INSERT INTO medical_history_previous_diseases VALUES (1, 7);
INSERT INTO medical_history_previous_diseases VALUES (1, 8);

-- María (id=2): Hepatitis A, E
INSERT INTO medical_history_previous_diseases VALUES (2, 6);
INSERT INTO medical_history_previous_diseases VALUES (2, 10);

-- Luis (id=3): Diabetes, VIH+
INSERT INTO medical_history_previous_diseases VALUES (3, 2);
INSERT INTO medical_history_previous_diseases VALUES (3, 5);

-- Ana (id=4): Asma
INSERT INTO medical_history_previous_diseases VALUES (4, 3);

-- Carlos (id=5): Hipertensión, Enfermedades del corazón
INSERT INTO medical_history_previous_diseases VALUES (5, 1);
INSERT INTO medical_history_previous_diseases VALUES (5, 4);

-- Lucía (id=6): Hepatitis D, VIH+
INSERT INTO medical_history_previous_diseases VALUES (6, 9);
INSERT INTO medical_history_previous_diseases VALUES (6, 5);

-- Javier (id=7): Asma, Hepatitis A
INSERT INTO medical_history_previous_diseases VALUES (7, 3);
INSERT INTO medical_history_previous_diseases VALUES (7, 6);

-- Elena (id=8): Diabetes, Hepatitis B
INSERT INTO medical_history_previous_diseases VALUES (8, 2);
INSERT INTO medical_history_previous_diseases VALUES (8, 7);

-- Diego (id=9): Hepatitis C, E
INSERT INTO medical_history_previous_diseases VALUES (9, 8);
INSERT INTO medical_history_previous_diseases VALUES (9, 10);

-- Sara (id=10): Hipertensión, Asma, VIH+
INSERT INTO medical_history_previous_diseases VALUES (10, 1);
INSERT INTO medical_history_previous_diseases VALUES (10, 3);
INSERT INTO medical_history_previous_diseases VALUES (10, 5);

INSERT INTO treatments_instance (id, name, price, notes, patient_id) VALUES
(1, 'Limpieza dental', 35.0, 'Limpieza profunda y remoción de sarro', 23),
(2, 'Limpieza dental', 50.0, 'Relleno de caries con resina compuesta', 23),
(3, 'Extracción dental', 70.0, 'Extracción simple de pieza dental', 23),
(4, 'Extracción de muela del juicio', 120.0, 'Extracción quirúrgica', 23),
(5, 'Blanqueamiento dental', 150.0, 'Tratamiento cosmético con gel de peróxido', 23),
(6, 'Endodoncia', 200.0, 'Tratamiento de conducto en pieza molar', 23),
(7, 'Colocación de corona', 300.0, 'Corona de porcelana sobre pieza endodonciada', 23),
(8, 'Implante dental', 900.0, 'Implante de titanio con corona', 23),
(9, 'Ortodoncia', 1500.0, 'Colocación de brackets metálicos', 23),
(10, 'Retiro de brackets', 100.0, 'Extracción de aparatología fija', 23);
ALTER SEQUENCE treatment_instance_seq RESTART WITH 11;

-- Entradas para medical_record_entries (todos con user_id = 1)
INSERT INTO medical_record_entries (id, date_time, observations, user_id, treatment_instance_id, medical_history_id)
VALUES
(1, '2025-05-10 09:00:00', 'Chequeo general sin hallazgos.', 1, 1, 1),
(2, '2025-05-10 10:15:00', 'Paciente con erupción, se sospecha reacción a penicilina.', 1, 2, 2),
(3, '2025-05-10 11:00:00', 'Paciente refiere reacción a mariscos tras anestesia.', 1, 1, 1),
(4, '2025-05-11 08:45:00', 'Congestión nasal y estornudos frecuentes.', 1, 3, 4),
(5, '2025-05-11 09:30:00', 'Paciente indica intolerancia a lácteos.', 1, 2, 1),
(6, '2025-05-11 10:00:00', 'Dolor estomacal tras consumir gluten.', 1, 1, 1),
(7, '2025-05-12 08:00:00', 'Sin antecedentes relevantes.', 1, 3, 7),
(8, '2025-05-12 10:30:00', 'Reacción previa a ibuprofeno.', 1, 1, 8),
(9, '2025-05-12 11:45:00', 'Paciente con antecedentes de reacción a aspirina.', 1, 2, 9),
(10,'2025-05-13 09:00:00', 'Paciente refiere hinchazón por picadura de abeja.', 1, 3, 10);
ALTER SEQUENCE medical_record_seq RESTART WITH 11;

-- Relación con dientes (medical_record_entry_tooth)
-- Asociaciones variadas
INSERT INTO medical_record_entry_tooth (medical_record_entry_id, tooth_id) VALUES
(1, 11), (1, 12),
(2, 13), (2, 14), (2, 15),
(3, 21),
(4, 22), (4, 23),
(5, 31), (5, 32),
(6, 17), (6, 21),
(7, 25),
(8, 26), (8, 27),
(9, 28), (9, 29),
(10, 30), (10, 31), (10, 32);
