-- 10 TRAINEES
INSERT INTO "user" (role, phone_number, email, password, date_of_birth, full_name, gender, is_trainee, goal, height, weight, is_trainer, is_businesses)
VALUES
    ( 'TRAINEE', '0901000001', 'trainee1@example.com', 'password123', '1995-05-15', 'Alice Johnson', 'FEMALE', true, 'Weight Loss', '165cm', '60kg', false, false),
    ( 'TRAINEE', '0901000002', 'trainee2@example.com', 'password123', '1992-10-20', 'Bob Smith', 'MALE', true, 'Muscle Gain', '180cm', '85kg', false, false),
    ( 'TRAINEE', '0901000003', 'trainee3@example.com', 'password123', '1998-01-12', 'Charlie Davis', 'MALE', true, 'Endurance', '175cm', '70kg', false, false),
    ( 'TRAINEE', '0901000004', 'trainee4@example.com', 'password123', '1994-03-25', 'Diana Prince', 'FEMALE', true, 'Flexibility', '160cm', '52kg', false, false),
    ( 'TRAINEE', '0901000005', 'trainee5@example.com', 'password123', '1990-07-30', 'Ethan Hunt', 'MALE', true, 'General Fitness', '182cm', '78kg', false, false),
    ( 'TRAINEE', '0901000006', 'trainee6@example.com', 'password123', '1996-12-05', 'Fiona Glenanne', 'FEMALE', true, 'Strength', '168cm', '58kg', false, false),
    ( 'TRAINEE', '0901000007', 'trainee7@example.com', 'password123', '1993-09-14', 'George Miller', 'MALE', true, 'Weight Loss', '170cm', '90kg', false, false),
    ( 'TRAINEE', '0901000008', 'trainee8@example.com', 'password123', '1997-04-22', 'Hannah Abbott', 'FEMALE', true, 'Muscle Gain', '162cm', '55kg', false, false),
    ( 'TRAINEE', '0901000009', 'trainee9@example.com', 'password123', '1991-11-11', 'Ian Wright', 'MALE', true, 'Marathon Training', '178cm', '72kg', false, false),
    ( 'TRAINEE', '0901000010', 'trainee10@example.com', 'password123', '1999-02-28', 'Jenny Kim', 'FEMALE', true, 'Yoga', '158cm', '48kg', false, false);

-- 20 TRAINERS
INSERT INTO "user" ( role, phone_number, email, password, full_name, gender, is_trainee, is_trainer, certificate, bio, specialty, experience_year, is_businesses)
VALUES
    ( 'TRAINER', '0902000001', 'trainer1@fit.com', 'coachpass', 'John Matrix', 'MALE', false, true, 'NASM-CPT', 'Former commando turned trainer.', 'Bodybuilding', 10, false),
    ( 'TRAINER', '0902000002', 'trainer2@fit.com', 'coachpass', 'Sarah Connor', 'FEMALE', false, true, 'ACE Fitness', 'High intensity survival training.', 'HIIT', 8, false),
    ( 'TRAINER', '0902000003', 'trainer3@fit.com', 'coachpass', 'Mike Mentzer', 'MALE', false, true, 'IFBB Pro', 'Heavy duty training specialist.', 'Powerlifting', 15, false),
    ( 'TRAINER', '0902000004', 'trainer4@fit.com', 'coachpass', 'Ellen Ripley', 'FEMALE', false, true, 'CrossFit L3', 'Resilience and stamina expert.', 'CrossFit', 5, false),
    ( 'TRAINER', '0902000005', 'trainer5@fit.com', 'coachpass', 'Bruce Wayne', 'MALE', false, true, 'Global Cert', 'Master of multiple disciplines.', 'Martial Arts', 12, false),
    ( 'TRAINER', '0902000006', 'trainer6@fit.com', 'coachpass', 'Lara Croft', 'FEMALE', false, true, 'Adventure Sport', 'Agility and climbing focus.', 'Mobility', 7, false),
    ( 'TRAINER', '0902000007', 'trainer7@fit.com', 'coachpass', 'Rocky Balboa', 'MALE', false, true, 'Boxing Fed', 'Never give up attitude.', 'Boxing', 20, false),
    ( 'TRAINER', '0902000008', 'trainer8@fit.com', 'coachpass', 'Natasha Romanoff', 'FEMALE', false, true, 'Gymnastics Pro', 'Elite level flexibility.', 'Gymnastics', 9, false),
    ( 'TRAINER', '0902000009', 'trainer9@fit.com', 'coachpass', 'Steve Rogers', 'MALE', false, true, 'Army Physical', 'Classic strength training.', 'Strength & Conditioning', 11, false),
    ( 'TRAINER', '0902000010', 'trainer10@fit.com', 'coachpass', 'Wanda Maximoff', 'FEMALE', false, true, 'Yoga Alliance', 'Mind and body connection.', 'Yoga', 4, false),
    ( 'TRAINER', '0902000011', 'trainer11@fit.com', 'coachpass', 'Tony Stark', 'MALE', false, true, 'TechFit', 'Modern equipment specialist.', 'Circuit Training', 6, false),
    ( 'TRAINER', '0902000012', 'trainer12@fit.com', 'coachpass', 'Carol Danvers', 'FEMALE', false, true, 'AirForce Fit', 'Explosive power training.', 'Plyometrics', 8, false),
    ( 'TRAINER', '0902000013', 'trainer13@fit.com', 'coachpass', 'Arthur Curry', 'MALE', false, true, 'Swim Coach', 'Aquatic fitness specialist.', 'Swimming', 10, false),
    ( 'TRAINER', '0902000014', 'trainer14@fit.com', 'coachpass', 'Diana Prince', 'FEMALE', false, true, 'Olympus Cert', 'Ancient strength techniques.', 'Athletics', 15, false),
    ( 'TRAINER', '0902000015', 'trainer15@fit.com', 'coachpass', 'Barry Allen', 'MALE', false, true, 'Track Pro', 'Speed and agility coach.', 'Sprinting', 3, false),
    ( 'TRAINER', '0902000016', 'trainer16@fit.com', 'coachpass', 'Selina Kyle', 'FEMALE', false, true, 'Cat-Fit', 'Balance and stealth movement.', 'Pilates', 6, false),
    ( 'TRAINER', '0902000017', 'trainer17@fit.com', 'coachpass', 'Logan Howlett', 'MALE', false, true, 'Survivalist', 'Endurance and recovery.', 'Calisthenics', 25, false),
    ( 'TRAINER', '0902000018', 'trainer18@fit.com', 'coachpass', 'Jean Grey', 'FEMALE', false, true, 'Zen Master', 'Focus and breathwork.', 'Meditation', 10, false),
    ( 'TRAINER', '0902000019', 'trainer19@fit.com', 'coachpass', 'Thor Odinson', 'MALE', false, true, 'Asgard Heavy', 'Heavy lifting only.', 'Strongman', 100, false),
    ( 'TRAINER', '0902000020', 'trainer20@fit.com', 'coachpass', 'Peter Parker', 'MALE', false, true, 'NY Fitness', 'Agile and functional movement.', 'Parkour', 2, false);

-- 5 BUSINESSES
INSERT INTO "user" ( role, phone_number, email, password, is_trainee, is_trainer, is_businesses, address, tax_code, business_name)
VALUES
    ( 'BUSINESS', '0903000001', 'contact@goldgym.com', 'busipass', false, false, true, '123 Muscle Ave, NY', 'TAX001', 'Golds Gym'),
    ( 'BUSINESS', '0903000002', 'info@yoga-studio.com', 'busipass', false, false, true, '456 Zen Garden, SF', 'TAX002', 'Serenity Yoga'),
    ( 'BUSINESS', '0903000003', 'support@crossfit-box.com', 'busipass', false, false, true, '789 Industrial Way, CHI', 'TAX003', 'The Iron Box'),
    ( 'BUSINESS', '0903000004', 'hello@fitness-first.com', 'busipass', false, false, true, '101 Corporate Plaza, LDN', 'TAX004', 'Fitness First'),
    ( 'BUSINESS', '0903000005', 'admin@swim-center.com', 'busipass', false, false, true, '202 Aquatic Blvd, SYD', 'TAX005', 'Blue Wave Swim Center');