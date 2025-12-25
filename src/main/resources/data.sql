-- 10 TRAINEES
INSERT INTO "user" (role, phone_number, email, password, date_of_birth, full_name, gender, is_trainee, goal, height, weight, is_trainer, is_businesses)
VALUES
    ( 'TRAINEE', '0901000001', 'trainee1@example.com', '$2a$10$7rbrq6v/AInY/U1Nu/Kpeu.x654dTf6fHA9iVUtJ2nHSZb2Ag.nSm', '1995-05-15', 'Alice Johnson', 'FEMALE', true, 'Weight Loss', '165cm', '60kg', false, false),
    ( 'TRAINEE', '0901000002', 'trainee2@example.com', '$2a$10$7rbrq6v/AInY/U1Nu/Kpeu.x654dTf6fHA9iVUtJ2nHSZb2Ag.nSm', '1992-10-20', 'Bob Smith', 'MALE', true, 'Muscle Gain', '180cm', '85kg', false, false),
    ( 'TRAINEE', '0901000003', 'trainee3@example.com', '$2a$10$7rbrq6v/AInY/U1Nu/Kpeu.x654dTf6fHA9iVUtJ2nHSZb2Ag.nSm', '1998-01-12', 'Charlie Davis', 'MALE', true, 'Endurance', '175cm', '70kg', false, false),
    ( 'TRAINEE', '0901000004', 'trainee4@example.com', '$2a$10$7rbrq6v/AInY/U1Nu/Kpeu.x654dTf6fHA9iVUtJ2nHSZb2Ag.nSm', '1994-03-25', 'Diana Prince', 'FEMALE', true, 'Flexibility', '160cm', '52kg', false, false),
    ( 'TRAINEE', '0901000005', 'trainee5@example.com', '$2a$10$7rbrq6v/AInY/U1Nu/Kpeu.x654dTf6fHA9iVUtJ2nHSZb2Ag.nSm', '1990-07-30', 'Ethan Hunt', 'MALE', true, 'General Fitness', '182cm', '78kg', false, false),
    ( 'TRAINEE', '0901000006', 'trainee6@example.com', '$2a$10$7rbrq6v/AInY/U1Nu/Kpeu.x654dTf6fHA9iVUtJ2nHSZb2Ag.nSm', '1996-12-05', 'Fiona Glenanne', 'FEMALE', true, 'Strength', '168cm', '58kg', false, false),
    ( 'TRAINEE', '0901000007', 'trainee7@example.com', '$2a$10$7rbrq6v/AInY/U1Nu/Kpeu.x654dTf6fHA9iVUtJ2nHSZb2Ag.nSm', '1993-09-14', 'George Miller', 'MALE', true, 'Weight Loss', '170cm', '90kg', false, false),
    ( 'TRAINEE', '0901000008', 'trainee8@example.com', '$2a$10$7rbrq6v/AInY/U1Nu/Kpeu.x654dTf6fHA9iVUtJ2nHSZb2Ag.nSm', '1997-04-22', 'Hannah Abbott', 'FEMALE', true, 'Muscle Gain', '162cm', '55kg', false, false),
    ( 'TRAINEE', '0901000009', 'trainee9@example.com', '$2a$10$7rbrq6v/AInY/U1Nu/Kpeu.x654dTf6fHA9iVUtJ2nHSZb2Ag.nSm', '1991-11-11', 'Ian Wright', 'MALE', true, 'Marathon Training', '178cm', '72kg', false, false),
    ( 'TRAINEE', '0901000010', 'trainee10@example.com', '$2a$10$7rbrq6v/AInY/U1Nu/Kpeu.x654dTf6fHA9iVUtJ2nHSZb2Ag.nSm', '1999-02-28', 'Jenny Kim', 'FEMALE', true, 'Yoga', '158cm', '48kg', false, false);

-- 20 TRAINERS
INSERT INTO "user" ( role, phone_number, email, password, full_name, gender, is_trainee, is_trainer, certificate, bio, specialty, experience_year, is_businesses)
VALUES
    ( 'TRAINER', '0902000001', 'trainer1@fit.com', '$2a$10$7rbrq6v/AInY/U1Nu/Kpeu.x654dTf6fHA9iVUtJ2nHSZb2Ag.nSm', 'John Matrix', 'MALE', false, true, 'NASM-CPT', 'Former commando turned trainer.', 'Bodybuilding', 10, false),
    ( 'TRAINER', '0902000002', 'trainer2@fit.com', '$2a$10$7rbrq6v/AInY/U1Nu/Kpeu.x654dTf6fHA9iVUtJ2nHSZb2Ag.nSm', 'Sarah Connor', 'FEMALE', false, true, 'ACE Fitness', 'High intensity survival training.', 'HIIT', 8, false),
    ( 'TRAINER', '0902000003', 'trainer3@fit.com', '$2a$10$7rbrq6v/AInY/U1Nu/Kpeu.x654dTf6fHA9iVUtJ2nHSZb2Ag.nSm', 'Mike Mentzer', 'MALE', false, true, 'IFBB Pro', 'Heavy duty training specialist.', 'Powerlifting', 15, false),
    ( 'TRAINER', '0902000004', 'trainer4@fit.com', '$2a$10$7rbrq6v/AInY/U1Nu/Kpeu.x654dTf6fHA9iVUtJ2nHSZb2Ag.nSm', 'Ellen Ripley', 'FEMALE', false, true, 'CrossFit L3', 'Resilience and stamina expert.', 'CrossFit', 5, false),
    ( 'TRAINER', '0902000005', 'trainer5@fit.com', '$2a$10$7rbrq6v/AInY/U1Nu/Kpeu.x654dTf6fHA9iVUtJ2nHSZb2Ag.nSm', 'Bruce Wayne', 'MALE', false, true, 'Global Cert', 'Master of multiple disciplines.', 'Martial Arts', 12, false),
    ( 'TRAINER', '0902000006', 'trainer6@fit.com', '$2a$10$7rbrq6v/AInY/U1Nu/Kpeu.x654dTf6fHA9iVUtJ2nHSZb2Ag.nSm', 'Lara Croft', 'FEMALE', false, true, 'Adventure Sport', 'Agility and climbing focus.', 'Mobility', 7, false),
    ( 'TRAINER', '0902000007', 'trainer7@fit.com', '$2a$10$7rbrq6v/AInY/U1Nu/Kpeu.x654dTf6fHA9iVUtJ2nHSZb2Ag.nSm', 'Rocky Balboa', 'MALE', false, true, 'Boxing Fed', 'Never give up attitude.', 'Boxing', 20, false),
    ( 'TRAINER', '0902000008', 'trainer8@fit.com', '$2a$10$7rbrq6v/AInY/U1Nu/Kpeu.x654dTf6fHA9iVUtJ2nHSZb2Ag.nSm', 'Natasha Romanoff', 'FEMALE', false, true, 'Gymnastics Pro', 'Elite level flexibility.', 'Gymnastics', 9, false),
    ( 'TRAINER', '0902000009', 'trainer9@fit.com', '$2a$10$7rbrq6v/AInY/U1Nu/Kpeu.x654dTf6fHA9iVUtJ2nHSZb2Ag.nSm', 'Steve Rogers', 'MALE', false, true, 'Army Physical', 'Classic strength training.', 'Strength & Conditioning', 11, false),
    ( 'TRAINER', '0902000010', 'trainer10@fit.com', '$2a$10$7rbrq6v/AInY/U1Nu/Kpeu.x654dTf6fHA9iVUtJ2nHSZb2Ag.nSm', 'Wanda Maximoff', 'FEMALE', false, true, 'Yoga Alliance', 'Mind and body connection.', 'Yoga', 4, false),
    ( 'TRAINER', '0902000011', 'trainer11@fit.com', '$2a$10$7rbrq6v/AInY/U1Nu/Kpeu.x654dTf6fHA9iVUtJ2nHSZb2Ag.nSm', 'Tony Stark', 'MALE', false, true, 'TechFit', 'Modern equipment specialist.', 'Circuit Training', 6, false),
    ( 'TRAINER', '0902000012', 'trainer12@fit.com', '$2a$10$7rbrq6v/AInY/U1Nu/Kpeu.x654dTf6fHA9iVUtJ2nHSZb2Ag.nSm', 'Carol Danvers', 'FEMALE', false, true, 'AirForce Fit', 'Explosive power training.', 'Plyometrics', 8, false),
    ( 'TRAINER', '0902000013', 'trainer13@fit.com', '$2a$10$7rbrq6v/AInY/U1Nu/Kpeu.x654dTf6fHA9iVUtJ2nHSZb2Ag.nSm', 'Arthur Curry', 'MALE', false, true, 'Swim Coach', 'Aquatic fitness specialist.', 'Swimming', 10, false),
    ( 'TRAINER', '0902000014', 'trainer14@fit.com', '$2a$10$7rbrq6v/AInY/U1Nu/Kpeu.x654dTf6fHA9iVUtJ2nHSZb2Ag.nSm', 'Diana Prince', 'FEMALE', false, true, 'Olympus Cert', 'Ancient strength techniques.', 'Athletics', 15, false),
    ( 'TRAINER', '0902000015', 'trainer15@fit.com', '$2a$10$7rbrq6v/AInY/U1Nu/Kpeu.x654dTf6fHA9iVUtJ2nHSZb2Ag.nSm', 'Barry Allen', 'MALE', false, true, 'Track Pro', 'Speed and agility coach.', 'Sprinting', 3, false),
    ( 'TRAINER', '0902000016', 'trainer16@fit.com', '$2a$10$7rbrq6v/AInY/U1Nu/Kpeu.x654dTf6fHA9iVUtJ2nHSZb2Ag.nSm', 'Selina Kyle', 'FEMALE', false, true, 'Cat-Fit', 'Balance and stealth movement.', 'Pilates', 6, false),
    ( 'TRAINER', '0902000017', 'trainer17@fit.com', '$2a$10$7rbrq6v/AInY/U1Nu/Kpeu.x654dTf6fHA9iVUtJ2nHSZb2Ag.nSm', 'Logan Howlett', 'MALE', false, true, 'Survivalist', 'Endurance and recovery.', 'Calisthenics', 25, false),
    ( 'TRAINER', '0902000018', 'trainer18@fit.com', '$2a$10$7rbrq6v/AInY/U1Nu/Kpeu.x654dTf6fHA9iVUtJ2nHSZb2Ag.nSm', 'Jean Grey', 'FEMALE', false, true, 'Zen Master', 'Focus and breathwork.', 'Meditation', 10, false),
    ( 'TRAINER', '0902000019', 'trainer19@fit.com', '$2a$10$7rbrq6v/AInY/U1Nu/Kpeu.x654dTf6fHA9iVUtJ2nHSZb2Ag.nSm', 'Thor Odinson', 'MALE', false, true, 'Asgard Heavy', 'Heavy lifting only.', 'Strongman', 100, false),
    ( 'TRAINER', '0902000020', 'trainer20@fit.com', '$2a$10$7rbrq6v/AInY/U1Nu/Kpeu.x654dTf6fHA9iVUtJ2nHSZb2Ag.nSm', 'Peter Parker', 'MALE', false, true, 'NY Fitness', 'Agile and functional movement.', 'Parkour', 2, false);

-- 5 BUSINESSES
INSERT INTO "user" ( role, phone_number, email, password, is_trainee, is_trainer, is_businesses, address, tax_code, business_name)
VALUES
    ( 'BUSINESS', '0903000001', 'contact@goldgym.com', '$2a$10$7rbrq6v/AInY/U1Nu/Kpeu.x654dTf6fHA9iVUtJ2nHSZb2Ag.nSm', false, false, true, '123 Muscle Ave, NY', 'TAX001', 'Golds Gym'),
    ( 'BUSINESS', '0903000002', 'info@yoga-studio.com', '$2a$10$7rbrq6v/AInY/U1Nu/Kpeu.x654dTf6fHA9iVUtJ2nHSZb2Ag.nSm', false, false, true, '456 Zen Garden, SF', 'TAX002', 'Serenity Yoga'),
    ( 'BUSINESS', '0903000003', 'support@crossfit-box.com', '$2a$10$7rbrq6v/AInY/U1Nu/Kpeu.x654dTf6fHA9iVUtJ2nHSZb2Ag.nSm', false, false, true, '789 Industrial Way, CHI', 'TAX003', 'The Iron Box'),
    ( 'BUSINESS', '0903000004', 'hello@fitness-first.com', '$2a$10$7rbrq6v/AInY/U1Nu/Kpeu.x654dTf6fHA9iVUtJ2nHSZb2Ag.nSm', false, false, true, '101 Corporate Plaza, LDN', 'TAX004', 'Fitness First'),
    ( 'BUSINESS', '0903000005', 'admin@swim-center.com', '$2a$10$7rbrq6v/AInY/U1Nu/Kpeu.x654dTf6fHA9iVUtJ2nHSZb2Ag.nSm', false, false, true, '202 Aquatic Blvd, SYD', 'TAX005', 'Blue Wave Swim Center');

-- ADMIN USER
-- Email: admin@fitconnect.com
-- Password: admin123
INSERT INTO "user" (role, phone_number, email, password, full_name, is_trainee, is_trainer, is_businesses)
VALUES ('ADMIN', '0900000000', 'admin@fitconnect.com', '$2a$10$7rbrq6v/AInY/U1Nu/Kpeu.x654dTf6fHA9iVUtJ2nHSZb2Ag.nSm', 'Admin User', false, false, false);

-- 10 PRODUCTS
INSERT INTO "product" (name, description, price, image, stock_quantity, category) VALUES
('Whey Protein Isolate', 'Sữa tăng cơ tinh khiết, hấp thụ nhanh', 1500000, 'https://cdn.xaxi.vn/tpcn/img/muscletech-isowhey-100-whey-protein-isolate-vanilla-5-lbs-2-27-kg-135924-631656717617.jpg', 50, 'PROTEIN'),
('C4 Original Pre-Workout', 'Tăng cường năng lượng và sự tập trung trước khi tập', 750000, 'https://product.hstatic.net/200000597515/product/c4-original-pre-workout_7e6b13c8bc884404b462d4a43a952518.jpg', 30, 'PRE_WORKOUT'),
('BCAA 5000 Powder', 'Hỗ trợ phục hồi cơ bắp và chống dị hóa', 600000, 'https://www.sporter.com/media/catalog/product/o/n/on_bcaa_5000_40sv_unflavored_1.jpg', 40, 'AMINO_ACIDS'),
('Creatine Monohydrate', 'Tăng sức mạnh và kích thước cơ bắp', 450000, 'https://bizweb.dktcdn.net/100/011/344/products/nutrabio-micronized-creatine-monohydrate-60-servings-lemon-burst.jpg?v=1760429966957', 100, 'CREATINE'),
('Lipo-6 Black Ultra', 'Hỗ trợ đốt mỡ và giảm cân hiệu quả', 850000, 'https://m.media-amazon.com/images/I/712qJJZF4xL._AC_UF1000,1000_QL80_.jpg', 25, 'WEIGHT_MANAGEMENT'),
('Serious Mass 12lbs', 'Sữa tăng cân cho người gầy lâu năm', 1650000, 'https://www.wheystore.vn/images/products/2023/12/15/large/serious-mass-12lbs_1702628517.jpg', 15, 'MASS_GAINER'),
('Multivitamin Daily', 'Bổ sung đầy đủ vitamin và khoáng chất thiết yếu', 350000, 'https://bizweb.dktcdn.net/100/407/286/products/vien-uong-vitamin-tong-hop-kirkland-signature-daily-multivitamin.jpg?v=1604457556127', 60, 'VITAMINS_MINERALS'),
('ZMA Recovery & Sleep', 'Cải thiện giấc ngủ và phục hồi hormone tự nhiên', 550000, 'https://snac.com/cdn/shop/products/ZMA-5_grande.jpg?v=1629790482', 20, 'RECOVERY_SLEEP'),
('Energy Endurance Gel', 'Bổ sung năng lượng tức thì trong khi tập', 50000, 'https://product.hstatic.net/200001007715/product/sprint---isotonic-energy-gel-_-caffeine--fruit-burst_7270946d580c4426a4d0904b40652594.jpg', 200, 'ENERGY_ENDURANCE'),
('Casein Protein Night', 'Protein hấp thụ chậm giúp nuôi cơ ban đêm', 1400000, 'https://www.nutritionwarehouse.co.nz/cdn/shop/files/0001s_0001_maxs-anabolic-night-rich-chocolate-mousse-900g_600x600.jpg?v=1723087597', 10, 'PROTEIN');


-- 50 PACKAGE RECORDS
-- Note: Assuming Trainer IDs start from 11 to 30 based on your previous inserts
INSERT INTO "package" (name, price, description, type, thumb_nail_url, is_active, duration, trainer_id)
VALUES
-- Trainer 1 (John Matrix - Bodybuilding)
('Elite Bodybuilding Foundation', '2000000', '12-week intensive mass building program.', 'STRENGTH', 'https://example.com/thumb1.jpg', true, 90, 11),
('Pro Contest Prep', '5000000', 'Advanced stage preparation and dieting.', 'STRENGTH', 'https://example.com/thumb2.jpg', true, 120, 11),
-- Trainer 2 (Sarah Connor - HIIT)
('Survival HIIT Blast', '1200000', 'High intensity interval training for fat loss.', 'CARDIO', 'https://example.com/thumb3.jpg', true, 30, 12),
('Endurance Warrior', '1500000', 'Build stamina that lasts all day.', 'CARDIO', 'https://example.com/thumb4.jpg', true, 45, 12),
-- Trainer 3 (Mike Mentzer - Powerlifting)
('Heavy Duty Basics', '1800000', 'The science of high intensity lifting.', 'POWER', 'https://example.com/thumb5.jpg', true, 60, 13),
('Deadlift Specialist', '900000', 'Focus purely on improving your pull.', 'POWER', 'https://example.com/thumb6.jpg', true, 30, 13),
-- Trainer 4 (Ellen Ripley - CrossFit)
('Resilience 101', '1400000', 'CrossFit fundamentals for beginners.', 'CROSSFIT', 'https://example.com/thumb7.jpg', true, 30, 14),
('Alien Stamina', '2200000', 'Advanced metabolic conditioning.', 'CROSSFIT', 'https://example.com/thumb8.jpg', true, 60, 14),
-- Trainer 5 (Bruce Wayne - Martial Arts)
('Shadow Striking', '3000000', 'Martial arts conditioning and discipline.', 'COMBAT', 'https://example.com/thumb9.jpg', true, 90, 15),
('Defense Mastery', '2500000', 'Practical self-defense training.', 'COMBAT', 'https://example.com/thumb10.jpg', true, 60, 15),
-- Trainer 6 (Lara Croft - Mobility)
('Agility Flow', '1100000', 'Dynamic stretching and movement.', 'MOBILITY', 'https://example.com/thumb11.jpg', true, 30, 16),
('Climbers Strength', '1600000', 'Upper body and grip focus.', 'STRENGTH', 'https://example.com/thumb12.jpg', true, 45, 16),
-- Trainer 7 (Rocky Balboa - Boxing)
('Golden Gloves Intro', '800000', 'Basic boxing technique and cardio.', 'BOXING', 'https://example.com/thumb13.jpg', true, 30, 17),
('12 Rounds Cardio', '1500000', 'Conditioning like a pro boxer.', 'BOXING', 'https://example.com/thumb14.jpg', true, 45, 17),
-- Trainer 8 (Natasha Romanoff - Gymnastics)
('Core Assassin', '1300000', 'Elite core strength and stability.', 'GYMNASTICS', 'https://example.com/thumb15.jpg', true, 30, 18),
('Flexibility Pro', '1700000', 'Advanced split and bridge training.', 'FLEXIBILITY', 'https://example.com/thumb16.jpg', true, 60, 18),
-- Trainer 9 (Steve Rogers - Strength & Conditioning)
('Old School Strength', '1900000', 'Foundational barbell movements.', 'STRENGTH', 'https://example.com/thumb17.jpg', true, 60, 19),
('Leadership Fitness', '2500000', 'Comprehensive body conditioning.', 'GENERAL', 'https://example.com/thumb18.jpg', true, 90, 19),
-- Trainer 10 (Wanda Maximoff - Yoga)
('Mind-Body Flow', '1000000', 'Stress relief through Vinyasa.', 'YOGA', 'https://example.com/thumb19.jpg', true, 30, 20),
('Ethereal Balance', '1400000', 'Advanced balance and breathing.', 'YOGA', 'https://example.com/thumb20.jpg', true, 45, 20),
-- Trainer 11 (Tony Stark - Circuit Training)
('High-Tech Circuits', '2200000', 'Efficient full body circuit training.', 'CIRCUIT', 'https://example.com/thumb21.jpg', true, 30, 21),
('System Optimization', '3500000', 'Biometric focused performance plan.', 'CIRCUIT', 'https://example.com/thumb22.jpg', true, 90, 21),
-- Trainer 12 (Carol Danvers - Plyometrics)
('Explosive Jump', '1600000', 'Increase your vertical and power.', 'PLYO', 'https://example.com/thumb23.jpg', true, 45, 22),
('Supernova Power', '2800000', 'Total body explosive power.', 'PLYO', 'https://example.com/thumb24.jpg', true, 60, 22),
-- Trainer 13 (Arthur Curry - Swimming)
('Aqua Strength', '1400000', 'Resistance training in water.', 'SWIMMING', 'https://example.com/thumb25.jpg', true, 30, 23),
('Deep Sea Endurance', '2100000', 'Long distance swimming prep.', 'SWIMMING', 'https://example.com/thumb26.jpg', true, 60, 23),
-- Trainer 14 (Diana Prince - Athletics)
('Amazonian Strength', '3000000', 'Functional strength for longevity.', 'STRENGTH', 'https://example.com/thumb27.jpg', true, 90, 24),
('Olympic Spirit', '4000000', 'Competitive track and field prep.', 'ATHLETICS', 'https://example.com/thumb28.jpg', true, 120, 24),
-- Trainer 15 (Barry Allen - Sprinting)
('Quick Start', '950000', 'Acceleration and reaction time.', 'SPEED', 'https://example.com/thumb29.jpg', true, 14, 25),
('Flash Speed', '1800000', 'Sprinting mechanics for athletes.', 'SPEED', 'https://example.com/thumb30.jpg', true, 45, 25),
-- Trainer 16 (Selina Kyle - Pilates)
('Stealth Movement', '1300000', 'Pilates for core and posture.', 'PILATES', 'https://example.com/thumb31.jpg', true, 30, 26),
('Cat-Like Reflexes', '1600000', 'Focus on balance and coordination.', 'PILATES', 'https://example.com/thumb32.jpg', true, 45, 26),
-- Trainer 17 (Logan Howlett - Calisthenics)
('Weapon X Strength', '2000000', 'Advanced bodyweight mastery.', 'CALISTHENICS', 'https://example.com/thumb33.jpg', true, 60, 27),
('Unyielding Recovery', '1500000', 'Training for joints and longevity.', 'CALISTHENICS', 'https://example.com/thumb34.jpg', true, 45, 27),
-- Trainer 18 (Jean Grey - Meditation)
('Zen Focus', '800000', 'Meditation for athlete performance.', 'MENTAL', 'https://example.com/thumb35.jpg', true, 30, 28),
('Phoenix Rising', '2500000', 'Complete mental and physical reset.', 'WELLNESS', 'https://example.com/thumb36.jpg', true, 90, 28),
-- Trainer 19 (Thor Odinson - Strongman)
('Mjolnir Lift', '2500000', 'Extreme heavy weight training.', 'STRONGMAN', 'https://example.com/thumb37.jpg', true, 60, 29),
('God of Thunder Strength', '5000000', 'The ultimate strength challenge.', 'STRONGMAN', 'https://example.com/thumb38.jpg', true, 180, 29),
-- Trainer 20 (Peter Parker - Parkour)
('Urban Agility', '1100000', 'Beginner parkour and safe landing.', 'PARKOUR', 'https://example.com/thumb39.jpg', true, 30, 30),
('Web-Slinger Mobility', '1800000', 'Advanced wall runs and flips.', 'PARKOUR', 'https://example.com/thumb40.jpg', true, 60, 30),
-- Additional Mixed Packages to reach 50
('Weight Loss Kickstart', '1000000', 'General cardio and diet plan.', 'GENERAL', 'https://example.com/thumb41.jpg', true, 30, 11),
('Morning Yoga Flow', '600000', 'Start your day with energy.', 'YOGA', 'https://example.com/thumb42.jpg', true, 15, 20),
('Powerlifting Prep', '2200000', '1 rep max improvement program.', 'POWER', 'https://example.com/thumb43.jpg', true, 60, 13),
('Functional 50', '1500000', 'Fitness for people over 50.', 'GENERAL', 'https://example.com/thumb44.jpg', true, 45, 19),
('Home HIIT No Equipment', '500000', 'Burn fat anywhere.', 'HIIT', 'https://example.com/thumb45.jpg', true, 21, 12),
('Post-Injury Recovery', '2500000', 'Safe return to sport.', 'REHAB', 'https://example.com/thumb46.jpg', true, 60, 27),
('Marathon Milestone', '3000000', 'Run your first 42k.', 'CARDIO', 'https://example.com/thumb47.jpg', true, 120, 25),
('Muscle Maintenance', '1200000', 'Preserve gains during busy months.', 'STRENGTH', 'https://example.com/thumb48.jpg', true, 30, 11),
('Summer Shred', '1800000', 'Look your best for the beach.', 'WEIGHT_LOSS', 'https://example.com/thumb49.jpg', true, 45, 12),
('Intro to Calisthenics', '1000000', 'Learn the pull-up and dip.', 'CALISTHENICS', 'https://example.com/thumb50.jpg', true, 30, 27);


-- MULTIPLE ORDERS PER TRAINEE
-- Ensure `status` column can accept enum string values if it currently exists as smallint
ALTER TABLE IF EXISTS "order"
  ALTER COLUMN status TYPE varchar USING status::text;

INSERT INTO "order" (payment_method, status, total_price, shipping_address, order_date, trainee_id)
VALUES
    -- Alice (Trainee ID: 1) has 3 orders
    ('CREDIT_CARD', 'FINISHED', 1500000, '123 Maple St, NY', '2023-09-01 10:00:00', 1),
    ('CREDIT_CARD', 'FINISHED', 750000,  '123 Maple St, NY', '2023-10-05 14:00:00', 1),
    ('E-WALLET',    'PENDING',  450000,  '123 Maple St, NY', '2023-11-20 09:30:00', 1),

    -- Bob (Trainee ID: 2) has 2 orders
    ('CASH',        'FINISHED', 3000000, '456 Oak Ave, SF',  '2023-08-15 16:20:00', 2),
    ('CREDIT_CARD', 'CANCELLED', 1650000, '456 Oak Ave, SF',  '2023-12-01 11:00:00', 2);

-- ORDER ITEMS FOR THE ABOVE
INSERT INTO "order_item" (quantity, order_id, product_id)
VALUES
    -- Alice's 1st Order (Order ID: 6 based on previous sequence)
    (1, 1, 1), -- Whey Protein
    -- Alice's 2nd Order
    (1, 2, 2), -- Pre-workout
    -- Alice's 3rd Order
    (1, 3, 4), -- Creatine

    -- Bob's 1st Order (Order ID: 9)
    (2, 4, 1), -- 2x Whey Protein
    -- Bob's 2nd Order
    (1, 5, 6); -- Serious Mass

---- SAMPLE PAYMENTS (tùy tên bảng/cột của bạn)
INSERT INTO "payments" (user_id, amount, currency, status, stripe_session_id, created_at)
VALUES
  (1, 299000, 'vnd', 'PENDING',   'cs_test_seed_001', now()),
  (1, 299000, 'vnd', 'COMPLETED', 'cs_test_seed_002', now()),
  (2, 799000, 'vnd', 'CANCELLED', 'cs_test_seed_003', now());

---- SAMPLE CONVERSATIONS (trainee_id, trainer_id)
INSERT INTO "conversation" (trainee_id, trainer_id, last_message_content, last_message_at)
VALUES
  (1, 11, 'Dạ anh, em cảm ơn!', now() - interval '1 day'),
  (2, 12, 'Em sẽ tập đúng lịch anh nhé!', now() - interval '2 hours'),
  (3, 13, 'Anh cho em hỏi về chế độ ăn ạ?', now() - interval '30 minutes'),
  (1, 14, 'Ok em, hẹn gặp buổi sau!', now() - interval '3 days');

---- SAMPLE MESSAGES
INSERT INTO "messages" (conversation_id, sender_id, content, message_type, send_at, is_read)
VALUES
  -- Conversation 1: Alice (id=1) với John Matrix (id=11)
  (1, 1, 'Anh PT ơi, em muốn giảm mỡ 4 tuần!', 'TEXT', now() - interval '2 days', true),
  (1, 11, 'Ok em. Anh set lịch 4 buổi/tuần nhé!', 'TEXT', now() - interval '2 days' + interval '5 minutes', true),
  (1, 1, 'Dạ anh, em cảm ơn!', 'TEXT', now() - interval '1 day', true),

  -- Conversation 2: Bob (id=2) với Sarah Connor (id=12)
  (2, 2, 'Chào chị, em muốn tăng cơ ạ', 'TEXT', now() - interval '1 day', true),
  (2, 12, 'Chào em! Chị sẽ lên giáo án HIIT cho em nhé', 'TEXT', now() - interval '1 day' + interval '10 minutes', true),
  (2, 2, 'Em sẽ tập đúng lịch anh nhé!', 'TEXT', now() - interval '2 hours', false),

  -- Conversation 3: Charlie (id=3) với Mike Mentzer (id=13)
  (3, 3, 'Anh ơi cho em hỏi về powerlifting ạ', 'TEXT', now() - interval '1 hour', true),
  (3, 13, 'Em muốn hỏi gì cứ hỏi nhé!', 'TEXT', now() - interval '45 minutes', true),
  (3, 3, 'Anh cho em hỏi về chế độ ăn ạ?', 'TEXT', now() - interval '30 minutes', false),

  -- Conversation 4: Alice (id=1) với Ellen Ripley (id=14)
  (4, 1, 'Chị ơi em muốn học CrossFit', 'TEXT', now() - interval '5 days', true),
  (4, 14, 'Ok em, chị có lớp vào T3 và T5', 'TEXT', now() - interval '5 days' + interval '30 minutes', true),
  (4, 1, 'Dạ em đăng ký T3 ạ', 'TEXT', now() - interval '4 days', true),
  (4, 14, 'Ok em, hẹn gặp buổi sau!', 'TEXT', now() - interval '3 days', true);
